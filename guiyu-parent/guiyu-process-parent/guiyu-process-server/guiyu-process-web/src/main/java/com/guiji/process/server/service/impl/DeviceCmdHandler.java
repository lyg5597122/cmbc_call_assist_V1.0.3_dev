package com.guiji.process.server.service.impl;


import com.guiji.process.core.message.CmdMessageVO;
import com.guiji.process.core.vo.ProcessInstanceVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceCmdHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DeviceManageService deviceManageService;

    public void excute(CmdMessageVO cmdMessageVO)
    {
        if(cmdMessageVO == null)
        {
            return;
        }

        switch (cmdMessageVO.getCmdType()) {

            case REGISTER:

                doRegister(cmdMessageVO);
                break;

            case RESTART:

                break;

            case UNKNOWN:
                break;

            case START:
                break;

            case STOP:
                break;

            case HEALTH:

                doHealthStatus(cmdMessageVO);
                break;

            default:
                break;
        }

    }


    private void doHealthStatus(CmdMessageVO cmdMessageVO)
    {
        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();
        ProcessInstanceVO oldProcessInstanceVO = deviceManageService.getDevice(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort());
        if(oldProcessInstanceVO == null)
        {
            // 未注册过，不做
            return;
        }

        deviceManageService.updateStatus(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort(), processInstanceVO.getStatus());
    }


    private void doRegister(CmdMessageVO cmdMessageVO)
    {
        ProcessInstanceVO processInstanceVO = cmdMessageVO.getProcessInstanceVO();

        List<ProcessInstanceVO> lst = new ArrayList<ProcessInstanceVO>();

        if(processInstanceVO == null)
        {
            return;
        }

        ProcessInstanceVO oldProcessInstanceVO = deviceManageService.getDevice(processInstanceVO.getType(), processInstanceVO.getIp(), processInstanceVO.getPort());
        if(oldProcessInstanceVO != null)
        {
            return;
        }

        lst.add(processInstanceVO);
        deviceManageService.register(lst);
    }

}
