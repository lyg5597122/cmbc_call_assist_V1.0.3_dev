package com.guiji.calloutserver.controller;

import com.google.common.eventbus.AsyncEventBus;
import com.guiji.callcenter.dao.entity.LineCount;
import com.guiji.calloutserver.api.ICallPlan;
import com.guiji.calloutserver.entity.CCException;
import com.guiji.calloutserver.eventbus.event.StartCallPlanEvent;
import com.guiji.calloutserver.manager.EurekaManager;
import com.guiji.calloutserver.service.LineCountService;
import com.guiji.component.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CallPlanController implements ICallPlan {

    @Autowired
    AsyncEventBus asyncEventBus;

    @Autowired
    LineCountService lineCountService;

    @Autowired
    EurekaManager eurekaManager;

    @Override
    public Result.ReturnData startCallPlan(String customerId, String tempId, Integer lineId) {
        log.info("收到启动呼叫计划请求，customerId[{}], tempId[{}],lineId[{}]", customerId, tempId, lineId);

//        List<LineCount> lineCountList = lineCountService.findByInstanceIdAndLineId("192.168.1.78:18024", lineId);
        List<LineCount> lineCountList = lineCountService.findByInstanceIdAndLineId(eurekaManager.getInstanceId(), lineId);
        if(lineCountList==null){
            //线路不存在
            //TODO: 报警，线路不存在
            log.warn("启动呼叫计划失败，线路不存在[{}]", lineId);
            return Result.error(CCException.LINE_NOT_EXIST);
        }

        if(lineCountList.size()>1){
            //TODO: 报警
            log.warn("启动计划失败，外呼服务[{}]的线路[{}]存在重复记录", eurekaManager.getInstanceId(), lineId);
            return Result.error(CCException.LINE_DUPLICATE);
        }

        LineCount currentLine = lineCountList.get(0);
        if(currentLine.getMaxConcurrentCalls()<=0){
            log.warn("启动计划失败，线路没有并发数[{}]", lineId);
            return Result.error(CCException.LINE_NO_CONCURRENTS);
        }

        //判断线路空闲并发数是否够用
        if(currentLine.getUsedConcurrentCalls()!=null && currentLine.getUsedConcurrentCalls()>0){
            //TODO: 报警
            log.warn("启动计划失败，线路正在使用中[{}]", lineId);
            return Result.error(CCException.LINE_BUSY);
        }
        asyncEventBus.post(new StartCallPlanEvent(customerId, tempId, currentLine));
        return Result.ok();
    }
}