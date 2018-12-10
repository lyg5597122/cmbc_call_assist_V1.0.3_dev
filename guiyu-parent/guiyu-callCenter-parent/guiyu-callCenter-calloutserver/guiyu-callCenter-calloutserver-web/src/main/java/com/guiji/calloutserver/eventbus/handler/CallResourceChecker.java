package com.guiji.calloutserver.eventbus.handler;

import com.google.common.base.Preconditions;
import com.google.common.eventbus.AsyncEventBus;
import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.calloutserver.eventbus.event.CallResourceReadyEvent;
import com.guiji.calloutserver.helper.RequestHelper;
import com.guiji.calloutserver.manager.FsAgentManager;
import com.guiji.calloutserver.service.CallOutPlanService;
import com.guiji.component.result.Result;
import com.guiji.robot.api.IRobotRemote;
import com.guiji.robot.model.AiCallApplyReq;
import com.guiji.robot.model.AiCallNext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: 魏驰
 * @Date: 2018/11/2 14:56
 * @Project：guiyu-parent
 * @Description: 用于检查呼叫相关的资源是否齐备，如模板、tts、sellbot等
 */
@Slf4j
@Component
public class CallResourceChecker {
    @Autowired
    AsyncEventBus asyncEventBus;
    @Autowired
    FsAgentManager fsAgentManager;
    @Autowired
    IRobotRemote robotRemote;
    @Autowired
    CallOutPlanService callOutPlanService;

    /**
     * 检查呼叫依赖的各项资源是否齐备，只有完全齐备，才允许进行后面的呼叫
     * @param callOutPlan
     */
    public void checkCallResources(CallOutPlan callOutPlan){
        //启动多个线程，用于检测各个资源的状态
        checkTemp(callOutPlan.getTempId());
//        checkTts(callOutPlan);
        // todo 这个出现异常要干嘛？？
        checkSellbot(callOutPlan);

        asyncEventBus.post(new CallResourceReadyEvent(callOutPlan));
        log.info("---------------------CallResourceReadyEvent post "+callOutPlan.getPhoneNum());
    }

    /**
     * 检查sellbot资源是否就位
     * @param callOutPlan
     */
    private void checkSellbot(CallOutPlan callOutPlan) {

        AiCallApplyReq aiCallApplyReq = new AiCallApplyReq();
        aiCallApplyReq.setPhoneNo(callOutPlan.getPhoneNum());
        aiCallApplyReq.setSeqid(callOutPlan.getCallId());
        aiCallApplyReq.setTemplateId(callOutPlan.getTempId());
        aiCallApplyReq.setUserId(callOutPlan.getCustomerId());


        Result.ReturnData<AiCallNext> returnData = null;
        try {
            returnData = RequestHelper.loopRequest(new RequestHelper.RequestApi() {
                @Override
                public Result.ReturnData execute() {
                    return robotRemote.aiCallApply(aiCallApplyReq);
                }

                @Override
                public void onErrorResult(Result.ReturnData result) {
                    //TODO: 报警
                    log.warn("申请机器人资源失败, 错误码为[{}]，错误信息[{}]", result.getCode(), result.getMsg());
                }
            }, 5, 1, 1,60,true);
        } catch (Exception e) {
            log.warn("在初始化fsline时出现异常", e);
        }

        //todo 我为什么要抛一个空指针呢
        Preconditions.checkNotNull(returnData, "null return data from sellbot");
        String aiNo = returnData.getBody().getAiNo();
        callOutPlan.setAiId(aiNo);
        callOutPlanService.update(callOutPlan);

    }

    /**
     * 检查tts是否就位
     * @param callOutPlan
     */
    private void checkTts(CallOutPlan callOutPlan) {
        //TODO:tts资源检查
    }

    /**
     * 检查模板是否就位
     */
    public void checkTemp(String tempId) {
        fsAgentManager.istempexist(tempId);
        fsAgentManager.getwavlength(tempId);
    }
}
