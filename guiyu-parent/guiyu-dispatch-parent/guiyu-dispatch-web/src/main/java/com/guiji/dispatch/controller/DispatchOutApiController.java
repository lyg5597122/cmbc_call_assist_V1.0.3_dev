package com.guiji.dispatch.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guiji.common.model.Page;
import com.guiji.dispatch.api.IDispatchPlanOutApi;
import com.guiji.dispatch.dao.entity.DispatchPlanExample;
import com.guiji.dispatch.model.DispatchPlan;
import com.guiji.dispatch.service.IDispatchPlanService;

@RestController
public class DispatchOutApiController implements IDispatchPlanOutApi{
	
    @Autowired
    private IDispatchPlanService dispatchPlanService;

    /**
     * 完成
     *
     * @param planUuid 任务id
     * @return 响应报文
     * @throws Exception 异常
     */
	@Override
    @GetMapping(value="out/successSchedule")
	public boolean successSchedule(String planUuid)   {
		return dispatchPlanService.successSchedule(planUuid);
	}


    /**
     * 返回可以拨打的任务给呼叫中心
     *
     * @param schedule 请求参数
     * @return 响应报文
     * @throws Exception 异常
     */
	@Override
    @GetMapping(value="out/queryAvailableSchedules")
	public List<DispatchPlan> queryAvailableSchedules(Integer userId, int requestCount, int lineId) {
		List<com.guiji.dispatch.dao.entity.DispatchPlan> queryAvailableSchedules = dispatchPlanService.queryAvailableSchedules(userId, requestCount, lineId);
		List<DispatchPlan> list = new ArrayList<>();
		try {
			for (com.guiji.dispatch.dao.entity.DispatchPlan plan : queryAvailableSchedules) {
				DispatchPlan bean = new DispatchPlan();
				BeanUtils.copyProperties(plan, bean);
				list.add(bean);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
