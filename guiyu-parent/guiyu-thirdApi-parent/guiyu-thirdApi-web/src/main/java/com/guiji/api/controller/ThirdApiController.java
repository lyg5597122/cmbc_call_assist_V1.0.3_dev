package com.guiji.api.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.guiji.cloud.api.ILogin;
import com.guiji.common.model.Page;
import com.guiji.component.result.Result;
import com.guiji.component.result.Result.ReturnData;
import com.guiji.dispatch.api.IThirdApiOut;
import com.guiji.dispatch.model.CallPlanDetailRecordVO;
import com.guiji.dispatch.model.DispatchPlanApi;
import com.guiji.dispatch.model.DispatchPlanList;
import com.guiji.dispatch.model.PlanCallInfoCount;
import com.guiji.dispatch.model.PlanResultInfo;

@RestController
public class ThirdApiController {

	@Autowired
	private IThirdApiOut thirdApi;
	@Autowired
	private ILogin login;

	/**
	 * 查询通话记录
	 * 
	 * @param accessToken
	 * @param callId
	 * @return
	 */
	@GetMapping("/getCalldetail")
	public Result.ReturnData getCalldetail(@RequestParam(required = false, name = "phone") String phone,
			@RequestParam(required = false, name = "batch_number") String batchNumber,
			@RequestParam(required = false, name = "pagenum") String pagenum,
			@RequestParam(required = false, name = "pagesize") String pagesize) {
		JSONObject jsonObject = new JSONObject();
		
		if(phone ==null || phone == ""){
			return Result.error("0303020");
		}
		
		if(batchNumber ==null || batchNumber == ""){
			return Result.error("0303021");
		}
		if(pagenum ==null || pagenum == ""){
			return Result.error("0303022");
		}
		
		if(pagesize ==null || pagesize == ""){
			return Result.error("0303023");
		}
		
		if(batchNumber ==null || batchNumber == ""){
			return Result.error("0303021");
		}
		
		if (!isInteger(pagenum)) {
			return Result.error("0303024");
		}
		
		if (!isInteger(pagesize)) {
			return Result.error("0303025");
		}
		if (!isInteger(phone)) {
			return Result.error("0303011");
		}
		if (Integer.valueOf(pagesize) > 1000) {
			return Result.error("0303012");
		}
		if (Integer.valueOf(pagesize) <= 0) {
			pagesize = "1";
		}
		
		if(phone.length()!=11){
			return Result.error("0303028");
		}
		ReturnData<Page<CallPlanDetailRecordVO>> calldetail = thirdApi.getCalldetail(phone, batchNumber, Integer.valueOf(pagenum),
				Integer.valueOf(pagesize));
		jsonObject.put("data", calldetail.getBody());
		return Result.ok(jsonObject);
	}

	/**
	 * 通过批次号查询该批次的拨打情况
	 * 
	 * @param batchNumber
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	@GetMapping("/getCallInfoByBatchId")
	public Result.ReturnData getCallInfoByBatchId(
			@RequestParam(required = false, name = "batch_number") String batchNumber,
			@RequestParam(required = false, name = "pagenum") String pagenum,
			@RequestParam(required = false, name = "pagesize") String pagesize) {
		JSONObject jsonObject = new JSONObject();
		
		if(pagenum ==null || pagenum == ""){
			return Result.error("0303022");
		}
		
		if(pagesize ==null || pagesize == ""){
			return Result.error("0303023");
		}
		
		if (!isInteger(pagenum)) {
			return Result.error("0303024");
		}
		
		if (!isInteger(pagesize)) {
			return Result.error("0303025");
		}
		
		if(batchNumber==null || batchNumber ==""){
			return Result.error("0303021");
		}
		if (Integer.valueOf(pagesize) > 1000) {
			return Result.error("0303012");
		}
		if (Integer.valueOf(pagesize) <= 0) {
			pagesize = "1";
		}
		ReturnData<PlanCallInfoCount> getcall4BatchName = thirdApi.getcall4BatchName(batchNumber, Integer.valueOf(pagenum),  Integer.valueOf(pagesize));
		jsonObject.put("data", getcall4BatchName.getBody());
		return Result.ok(jsonObject);
	}

	/**
	 * 通过批次号查询该批次任务的号码列表
	 * 
	 * @param pagenum
	 * @param pagesize
	 * @return
	 */
	@GetMapping("/getPhonesByBatchNumber")
	public Result.ReturnData getphonesByBatchNumber(
			@RequestParam(required = false, name = "batch_number") String batchNumber,
			@RequestParam(required = false, name = "pagenum") String pagenum,
			@RequestParam(required = false, name = "pagesize") String pagesize) {
		JSONObject jsonObject = new JSONObject();
		
		if(pagenum ==null || pagenum == ""){
			return Result.error("0303022");
		}
		
		if(pagesize ==null || pagesize == ""){
			return Result.error("0303023");
		}
		
		if(batchNumber==null || batchNumber ==""){
			return Result.error("0303021");
		}
		if (!isInteger(pagenum)) {
			return Result.error("0303024");
		}
		
		if (!isInteger(pagesize)) {
			return Result.error("0303025");
		}
		
		if (Integer.valueOf(pagesize) > 1000) {
			return Result.error("0303012");
		}
		if (Integer.valueOf(pagesize) <= 0) {
			pagesize = "1";
		}
		ReturnData<Page<DispatchPlanApi>> queryDispatchPlan = thirdApi.queryDispatchPlan(batchNumber, Integer.valueOf(pagenum),
				Integer.valueOf(pagesize));
		jsonObject.put("data", queryDispatchPlan.getBody());
		return Result.ok(jsonObject);
	}

	/**
	 * 获取token
	 * 
	 * @param access_key
	 * @param secret_key
	 * @return
	 */
	@GetMapping("/getToken")
	public Result.ReturnData getToken(@RequestParam(required = false, name = "access_key") String access_key,
			@RequestParam(required = false, name = "secret_key") String secret_key) {
		if(access_key==null || access_key ==""){
			return Result.error("0303021");
		}
		
		if(secret_key==null || secret_key ==""){
			return Result.error("0303027");
		}
		
		return login.apiLogin(access_key, secret_key);
	}

	/**
	 * 第三方回调重试机制
	 * 
	 * @param access_key
	 * @param secret_key
	 * @return
	 */
	@GetMapping("/reTryThirdApi")
	public Result.ReturnData reTryThirdApi(@RequestParam(required = false, name = "user_id") String userId) {
		JSONObject jsonObject = new JSONObject();
		if (!isNumeric(userId)) {
			return Result.error("0303014");
		}
		ReturnData<Boolean> reTryThirdApi = thirdApi.reTryThirdApi(Long.valueOf(userId));
		jsonObject.put("data", reTryThirdApi.getBody());
		return Result.ok(jsonObject);
	}

	/**
	 * 添加任务
	 * 
	 * @param dispatchPlanList
	 * @return
	 */	
	@PostMapping("/addPhones")
	public Result.ReturnData addPhones(@RequestBody DispatchPlanList dispatchPlanList) {
		JSONObject json = new JSONObject();
		if (!isNumeric(dispatchPlanList.getLine())) {
			return Result.error("0303015");
		} else if (!isNumeric(dispatchPlanList.getIsClean())) {
			return Result.error("0303016");
		} else if (!isNumeric(dispatchPlanList.getCallHour())) {
			return Result.error("0303017");
		} else if (!isNumeric(dispatchPlanList.getCallDate())) {
			return Result.error("0303018");
		} else if (!isNumeric(dispatchPlanList.getUserId())) {
			return Result.error("0303019");
		}
		ReturnData<PlanResultInfo> insertDispatchPlanList = thirdApi.insertDispatchPlanList(dispatchPlanList);
		json.put("data", insertDispatchPlanList.getBody());
		return Result.ok(json);
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
		return pattern.matcher(str).matches();
	}

}
