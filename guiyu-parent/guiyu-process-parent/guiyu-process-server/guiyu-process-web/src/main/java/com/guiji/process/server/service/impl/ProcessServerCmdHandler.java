package com.guiji.process.server.service.impl;


import com.guiji.common.constant.RedisConstant;
import com.guiji.common.model.process.ProcessStatusEnum;
import com.guiji.guiyu.message.component.FanoutSender;
import com.guiji.guiyu.message.model.PublishBotstenceResultMsgVO;
import com.guiji.process.core.IProcessCmdHandler;
import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.vo.CmdTypeEnum;
import com.guiji.common.model.process.ProcessInstanceVO;
import com.guiji.process.server.dao.entity.SysProcess;
import com.guiji.process.server.dao.entity.SysProcessTask;
import com.guiji.process.server.service.ISysProcessTaskService;
import com.guiji.process.server.service.ISysProcessService;
import com.guiji.utils.JsonUtils;
import com.guiji.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProcessServerCmdHandler implements IProcessCmdHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProcessManageService processManageService;

    @Autowired
    private ISysProcessService sysProcessService;

    @Autowired
    private ISysProcessTaskService sysProcessTaskService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FanoutSender fanoutSender;

    public void excute(CmdMessageVO cmdMessageVO)
    {
        if(cmdMessageVO == null)
        {
            return;
        }

        logger.debug(cmdMessageVO.toString());

        switch (cmdMessageVO.getCmdType()) {
            case AGENTREGISTER:
                doRegister(cmdMessageVO);
                break;
            case REGISTER:
                doRegister(cmdMessageVO);
                break;
            case UNREGISTER:
                doUnRegister(cmdMessageVO);
                break;
            case RESTART:
                processManageService.cmd(cmdMessageVO.getProcessInstanceVO(), CmdTypeEnum.RESTART, cmdMessageVO.getParameters());
                break;
            case UNKNOWN:
                break;
            case START:
                processManageService.cmd(cmdMessageVO.getProcessInstanceVO(), CmdTypeEnum.START, cmdMessageVO.getParameters());
                break;
            case STOP:
                processManageService.cmd(cmdMessageVO.getProcessInstanceVO(), CmdTypeEnum.STOP, cmdMessageVO.getParameters());
                break;
            case HEALTH:
                doHealthStatus(cmdMessageVO);
                break;
            case PULBLISH_SELLBOT_BOTSTENCE:
                doPublishAfter(cmdMessageVO);
                break;
            case PULBLISH_FREESWITCH_BOTSTENCE:
                doPublishAfter(cmdMessageVO);
                break;
            case PUBLISH_ROBOT_BOTSTENCE:
                doPublishAfter(cmdMessageVO);
                break;
            case AFTER_RESTART:
                doRestartAfter(cmdMessageVO);
                break;
            default:
                break;
        }

    }


    private void doHealthStatus(CmdMessageVO cmdMessageVO)
    {

        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
        ProcessInstanceVO oldProcessInstanceVO = processManageService.getDevice(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort());
        if(oldProcessInstanceVO == null)
        {
            // 未注册过，不做
            return;
        }

        ProcessStatusEnum toStatus = processInstanceVO.getStatus();
        if(oldProcessInstanceVO.getStatus() == ProcessStatusEnum.BUSYING)
        {
            toStatus = ProcessStatusEnum.BUSYING;
        }

        processManageService.updateStatus(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort(), toStatus);
    }

    private void doAgentRegister(CmdMessageVO cmdMessageVO) {
        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
        // 存入数据库
        SysProcess sysProcess = new SysProcess();
        sysProcess.setIp(processInstanceVO.getIp());
        sysProcess.setPort(String.valueOf(processInstanceVO.getPort()));
        sysProcess.setName(processInstanceVO.getName());
        sysProcess.setProcessKey(processInstanceVO.getProcessKey());
        sysProcess.setStatus(processInstanceVO.getStatus().getValue());
        sysProcess.setType(processInstanceVO.getType().getValue());
        sysProcess.setCreateTime(new Date());
        sysProcess.setUpdateTime(new Date());
        sysProcessService.insert(sysProcess);

    }


    private void doRegister(CmdMessageVO cmdMessageVO)
    {
        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();

        List<ProcessInstanceVO> lst = new ArrayList<ProcessInstanceVO>();

        if(processInstanceVO == null)
        {
            return;
        }

        lst.add(processInstanceVO);
        processManageService.register(lst);
    }

    private void doUnRegister(CmdMessageVO cmdMessageVO)
    {
        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();

        List<ProcessInstanceVO> lst = new ArrayList<ProcessInstanceVO>();

        if(processInstanceVO == null)
        {
            return;
        }

        ProcessInstanceVO oldProcessInstanceVO = processManageService.getDevice(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort());
        if(oldProcessInstanceVO == null)
        {
            return;
        }

        lst.add(processInstanceVO);
        processManageService.unRegister(lst);
    }

    private void doPublishAfter(CmdMessageVO cmdMessageVO) {
        if (cmdMessageVO != null) {
            ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
            if (processInstanceVO != null){
                //发布MQ通知消息
                String tmplId =  cmdMessageVO.getParameters().get(1);
                PublishBotstenceResultMsgVO publishBotstenceResultMsgVO = new PublishBotstenceResultMsgVO();
                publishBotstenceResultMsgVO.setProcessTypeEnum(processInstanceVO.getType());
                publishBotstenceResultMsgVO.setTmplId(tmplId);
                if (cmdMessageVO.getCommandResult() != null) {
                    publishBotstenceResultMsgVO.setResult(Integer.valueOf(cmdMessageVO.getCommandResult()));
                }

                fanoutSender.send("fanoutPublishBotstence", JsonUtils.bean2Json(publishBotstenceResultMsgVO));

                //更新sys_process_task
                SysProcessTask sysProcessTask = new SysProcessTask();
                sysProcessTask.setResult(cmdMessageVO.getCommandResult());
                sysProcessTask.setResultContent(cmdMessageVO.getCommandResultDesc());
                sysProcessTask.setExecStatus(0);
                sysProcessTask.setReqKey(cmdMessageVO.getReqKey());
                sysProcessTask.setUpdateTime(new Date());
                sysProcessTaskService.update(sysProcessTask);
                // 删除缓存
                redisUtil.del(RedisConstant.REDIS_PROCESS_TASK_PREFIX + processInstanceVO.getIp()+"_" + processInstanceVO.getPort()+"_"+cmdMessageVO.getCmdType());
            }
        }
    }

    private void doRestartAfter(CmdMessageVO cmdMessageVO) {
        if (cmdMessageVO != null) {
            ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
            if (processInstanceVO != null){
                //更新sys_process_task
                SysProcessTask sysProcessTask = new SysProcessTask();
                sysProcessTask.setResult(cmdMessageVO.getCommandResult());
                sysProcessTask.setResultContent(cmdMessageVO.getCommandResultDesc());
                sysProcessTask.setExecStatus(0);
                sysProcessTask.setReqKey(cmdMessageVO.getReqKey());
                sysProcessTask.setUpdateTime(new Date());
                sysProcessTaskService.update(sysProcessTask);
                // 删除缓存
                redisUtil.del(RedisConstant.REDIS_PROCESS_TASK_PREFIX + processInstanceVO.getIp()+"_" + processInstanceVO.getPort()+"_"+cmdMessageVO.getCmdType());
            }
        }
    }

}
