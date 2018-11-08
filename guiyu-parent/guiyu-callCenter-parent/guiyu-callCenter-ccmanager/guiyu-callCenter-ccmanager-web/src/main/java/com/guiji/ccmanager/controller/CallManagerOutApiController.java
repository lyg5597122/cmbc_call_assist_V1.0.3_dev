package com.guiji.ccmanager.controller;

import com.guiji.callcenter.dao.entity.LineInfo;
import com.guiji.ccmanager.api.ICallManagerOut;
import com.guiji.ccmanager.constant.Constant;
import com.guiji.ccmanager.entity.LineConcurrent;
import com.guiji.ccmanager.service.CallManagerOutService;
import com.guiji.ccmanager.service.LineInfoService;
import com.guiji.component.result.Result;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: 黎阳
 * @Date: 2018/10/30 0030 09:37
 * @Description:
 */
@RestController
public class CallManagerOutApiController implements ICallManagerOut {

    private final Logger log = LoggerFactory.getLogger(CallManagerOutApiController.class);

    @Autowired
    private LineInfoService lineInfoService;

    @Autowired
    private CallManagerOutService callManagerOutService;

    @Override
    @GetMapping(value="out/getLineInfos")
    public Result.ReturnData<List<LineConcurrent>> getLineInfos(String customerId){

        if(StringUtils.isBlank(customerId)){
            return Result.error(Constant.ERROR_PARAM);
        }
        List<LineInfo> lineInfos = lineInfoService.outLineinfos(customerId);
        List<LineConcurrent> resList = new ArrayList<LineConcurrent>();
        for(LineInfo lineInfo:lineInfos){
            LineConcurrent target = new LineConcurrent();
            target.setLineId(String.valueOf(lineInfo.getLineId()));
            target.setConcurrent(lineInfo.getMaxConcurrentCalls());
            resList.add(target);
        }
        return Result.ok(resList);
    }

    @Override
    @GetMapping(value="out/startCallPlan")
    public Result.ReturnData<Boolean> startCallPlan(String customerId, String tempId, String lineId) {

        if(StringUtils.isBlank(customerId) || StringUtils.isBlank(tempId) || StringUtils.isBlank(lineId) ){
            return Result.error(Constant.ERROR_PARAM);
        }

        Result.ReturnData<Object>  result = callManagerOutService.startcallplan(customerId,tempId,lineId);
        if(result.getCode().equals(Constant.SUCCESS_COMMON)){
            return Result.ok(true);
        }else{
            log.warn("callManagerOutService.startcallplan return code:"+result.getCode());
            return Result.error(result.getCode());
        }

    }
}
