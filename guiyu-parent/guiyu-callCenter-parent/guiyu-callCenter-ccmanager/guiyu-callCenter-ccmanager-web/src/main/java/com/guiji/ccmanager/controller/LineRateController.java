package com.guiji.ccmanager.controller;

import com.guiji.ccmanager.api.ILineRate;
import com.guiji.ccmanager.entity.LineRateResponse;
import com.guiji.ccmanager.service.LineRateService;
import com.guiji.component.result.Result;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 线路了接通率，供调度中心使用
 */
@Validated
@RestController
public class LineRateController implements ILineRate {

    @Autowired
    LineRateService lineRateService;

    @Override
    public Result.ReturnData<LineRateResponse> getLineRate(@RequestParam(value = "lineId") Integer lineId,
                                                           @RequestParam(value = "startTime") Date startTime,
                                                           @RequestParam(value = "endTime") Date endTime) {

        LineRateResponse lineRateResponse = lineRateService.getLineRate(lineId, startTime, endTime);

        return Result.ok(lineRateResponse);
    }

    @Override
    public Result.ReturnData<List<LineRateResponse>> getLineRateAll(@RequestParam(value = "startTime") Date startTime,
                                                                    @RequestParam(value = "endTime") Date endTime){

        List<LineRateResponse> list =lineRateService.getLineRateAll(startTime, endTime);

        return Result.ok(list);
    }
}
