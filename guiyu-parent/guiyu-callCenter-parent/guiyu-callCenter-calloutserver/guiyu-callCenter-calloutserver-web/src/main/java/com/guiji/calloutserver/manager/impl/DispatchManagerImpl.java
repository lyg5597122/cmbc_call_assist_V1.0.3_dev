package com.guiji.calloutserver.manager.impl;

import com.google.common.base.Preconditions;
import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.calloutserver.enm.ECallDirection;
import com.guiji.calloutserver.enm.ECallState;
import com.guiji.calloutserver.manager.DispatchManager;
import com.guiji.calloutserver.helper.RequestHelper;
import com.guiji.calloutserver.manager.EurekaManager;
import com.guiji.component.result.Result;
import com.guiji.dispatch.api.IDispatchPlanOut;
import com.guiji.dispatch.model.DispatchPlan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: 魏驰
 * @Date: 2018/11/4 17:19
 * @Project：guiyu-parent
 * @Description:
 */
@Slf4j
@Service
public class DispatchManagerImpl implements DispatchManager {
    @Autowired
    IDispatchPlanOut iDispatchPlanOutApi;

    @Autowired
    EurekaManager eurekaManager;

    /**
     * 拉取呼叫计划
     *
     * @param customerId
     * @param requestNum
     * @param lineId
     * @return
     */
    @Override
    public List<CallOutPlan> pullCallPlan(String customerId, Integer requestNum, Integer lineId){
        List<CallOutPlan> callOutPlans = new ArrayList<>();
        try {
            Result.ReturnData disPatchResult = RequestHelper.loopRequest(new RequestHelper.RequestApi() {
                @Override
                public Result.ReturnData execute() {
                    Result.ReturnData returnData = iDispatchPlanOutApi.queryAvailableSchedules(Integer.parseInt(customerId), requestNum, lineId);
                    return returnData;
                }

                @Override
                public void onErrorResult(Result.ReturnData result) {
                    log.warn("获取调度中心呼叫计划出现异常[{}]", result);
                    //TODO: 报警
                }
            }, -1, 1, 1, 20);

            List<DispatchPlan> dispatchPlans  = (List<DispatchPlan>) disPatchResult.getBody();
            Preconditions.checkState(dispatchPlans!=null && dispatchPlans.size()>0, "从调度中心拿到的数据为空");
            callOutPlans = toCallPlan(dispatchPlans);
        }catch (Exception ex){
            log.warn("请求调度中心呼叫计划出现异常", ex);
            //TODO: 报警，请求调度中心数据异常
        }

        return callOutPlans;
    }

    /**
     * 将调度中心返回的数据，转为CalloutPlan
     * @param dispatchPlans
     * @return
     */
    private List<CallOutPlan> toCallPlan(List<DispatchPlan> dispatchPlans) {
        Preconditions.checkNotNull(dispatchPlans, "将调度数据转为callPlan出现异常，调度数据为空");
        List<CallOutPlan> callOutPlans = new ArrayList<>(dispatchPlans.size());
        for(DispatchPlan dispatchPlan: dispatchPlans){
            CallOutPlan callOutPlan = new CallOutPlan();
            callOutPlan.setCallState(ECallState.init.ordinal());
            callOutPlan.setCreateTime(new Date());
            callOutPlan.setCallDirection(ECallDirection.OUTBOUND.ordinal());
            callOutPlan.setCallId(dispatchPlan.getPlanUuid());
            callOutPlan.setPhoneNum(dispatchPlan.getPhone());
            callOutPlan.setCustomerId(dispatchPlan.getUserId().toString());
            callOutPlan.setLineId(dispatchPlan.getLine());
            callOutPlan.setServerid(eurekaManager.getInstanceId());
            callOutPlan.setHasTts(dispatchPlan.getIsTts()>0);
            callOutPlan.setTempId(dispatchPlan.getRobot());

            callOutPlans.add(callOutPlan);
        }

        return callOutPlans;
    }
}