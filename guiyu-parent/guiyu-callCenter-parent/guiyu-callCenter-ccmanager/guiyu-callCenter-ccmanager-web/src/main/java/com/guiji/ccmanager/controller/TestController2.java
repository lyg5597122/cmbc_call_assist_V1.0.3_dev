package com.guiji.ccmanager.controller;

import com.guiji.calloutserver.api.ICallPlan;
import com.guiji.calloutserver.entity.DispatchPlan;
import com.guiji.component.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class TestController2 {

    @Autowired
    ICallPlan iCallPlan;

    @Async
    public void test(){
        log.info("--------start-------");
        DispatchPlan dispatchPlan = new DispatchPlan();
        dispatchPlan.setBatchId(11);
        dispatchPlan.setLine(110);
        dispatchPlan.setOrgCode("1");
        dispatchPlan.setPhone("18600397859");
        dispatchPlan.setPlanUuid(UUID.randomUUID().toString().replace("-",""));
        dispatchPlan.setTempId("dk_52386_en");
        dispatchPlan.setTts(false);
        dispatchPlan.setUserId(22);

        List list = new ArrayList<>();
        list.add(dispatchPlan);
        Result.ReturnData result = iCallPlan.startMakeCall(list);

        log.info(">>>>>end,result[{}] ",result);
    }

}
