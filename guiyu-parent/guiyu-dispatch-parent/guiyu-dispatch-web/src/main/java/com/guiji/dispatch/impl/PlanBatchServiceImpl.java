package com.guiji.dispatch.impl;

import com.guiji.common.exception.GuiyuException;
import com.guiji.common.model.SysFileReqVO;
import com.guiji.common.model.SysFileRspVO;
import com.guiji.component.result.Result;
import com.guiji.dispatch.batchimport.IBatchImportQueueHandlerService;
import com.guiji.dispatch.dao.DispatchPlanBatchMapper;
import com.guiji.dispatch.dao.entity.DispatchBatchLine;
import com.guiji.dispatch.dao.entity.DispatchPlan;
import com.guiji.dispatch.dao.entity.DispatchPlanBatch;
import com.guiji.dispatch.dao.entity.FileRecords;
import com.guiji.dispatch.dao.ext.PlanBatchOptMapper;
import com.guiji.dispatch.dto.JoinPlanDto;
import com.guiji.dispatch.dto.OptPlanDto;
import com.guiji.dispatch.entity.ExportFileRecord;
import com.guiji.dispatch.enums.*;
import com.guiji.dispatch.model.ExportFileDto;
import com.guiji.dispatch.service.GetApiService;
import com.guiji.dispatch.service.GetAuthUtil;
import com.guiji.dispatch.service.IExportFileService;
import com.guiji.dispatch.service.IPlanBatchService;
import com.guiji.dispatch.util.Constant;
import com.guiji.dispatch.util.DaoHandler;
import com.guiji.dispatch.util.DateTimeUtils;
import com.guiji.dispatch.vo.DownLoadPlanVo;
import com.guiji.user.dao.entity.SysUser;
import com.guiji.utils.IdGenUtil;
import com.guiji.utils.IdGengerator.SnowflakeIdWorker;
import com.guiji.utils.JsonUtils;
import com.guiji.utils.NasUtil;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class PlanBatchServiceImpl implements IPlanBatchService {

    private Logger logger = LoggerFactory.getLogger(PlanBatchServiceImpl.class);

    @Autowired
    private PlanBatchOptMapper planBatchMapper;

    @Autowired
    private GetAuthUtil getAuthUtil;

    @Autowired
    private GetApiService getApiService;

    @Autowired
    private IBatchImportQueueHandlerService batchImportQueueHandler;

    @Autowired
    private DispatchPlanBatchMapper dispatchPlanBatchMapper;

    @Autowired
    private IExportFileService exportFileService;

    /**
     * 删除计划任务
     * @param optPlanDto
     * @return
     */
    @Override
    public boolean delPlanBatch(OptPlanDto optPlanDto) {
        boolean bool = false;
        if(null != optPlanDto){
            //操作开始、结束时间
            if(!StringUtils.isEmpty(optPlanDto.getStartTime()) && !StringUtils.isEmpty(optPlanDto.getEndTime())) {
                optPlanDto.setStartTime(optPlanDto.getStartTime() + " " + DateTimeUtils.DEFAULT_DATE_START_TIME);
                optPlanDto.setEndTime(optPlanDto.getEndTime() + " " + DateTimeUtils.DEFAULT_DATE_END_TIME);
            }

            //如果不查batchId时，前端会传batchId=0
            if (optPlanDto.getBatchId() != null && optPlanDto.getBatchId() != 0) {
                optPlanDto.setBatchId(optPlanDto.getBatchId());
            }

            //获取权限
            Integer authLevel = optPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, optPlanDto.getUserId()));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel, optPlanDto.getOperOrgId()));//获取组织ID
        }

        //全选
        if(PlanOperTypeEnum.ALL.getType() == optPlanDto.getType()){
            if(null != optPlanDto){
                optPlanDto.setNocheckPlanUuid(null);
            }
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.delPlanBatchByParam(optPlanDto, new Date()));

        //只勾选
        }else if(PlanOperTypeEnum.CHECK.getType() == optPlanDto.getType()){
            if(null != optPlanDto
                    && null != optPlanDto.getCheckPlanUuid()
                    && optPlanDto.getCheckPlanUuid().size()>0){
                bool = DaoHandler.getMapperBoolRes(
                        planBatchMapper.delPlanBatchById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList(), new Date()));
            }

        //全选去勾
        }else if(PlanOperTypeEnum.NO_CHECK.getType() == optPlanDto.getType()){
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.delPlanBatchByParam(optPlanDto, new Date()));
        }
        return bool;
    }

    /**
     * 暂停计划任务
     * @param optPlanDto
     * @return
     */
    @Override
    public boolean suspendPlanBatch(OptPlanDto optPlanDto) {
        boolean bool = false;
        if(null != optPlanDto){
            //操作开始、结束时间
            if(!StringUtils.isEmpty(optPlanDto.getStartTime()) && !StringUtils.isEmpty(optPlanDto.getEndTime())) {
                optPlanDto.setStartTime(optPlanDto.getStartTime() + " " + DateTimeUtils.DEFAULT_DATE_START_TIME);
                optPlanDto.setEndTime(optPlanDto.getEndTime() + " " + DateTimeUtils.DEFAULT_DATE_END_TIME);
            }

            //如果不查batchId时，前端会传batchId=0
            if (optPlanDto.getBatchId() != null && optPlanDto.getBatchId() != 0) {
                optPlanDto.setBatchId(optPlanDto.getBatchId());
            }

            //获取权限
            Integer authLevel = optPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, optPlanDto.getUserId()));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel, optPlanDto.getOperOrgId()));//获取组织ID
        }

        //全选
        if(PlanOperTypeEnum.ALL.getType() == optPlanDto.getType()){
            if(null != optPlanDto){
                optPlanDto.setNocheckPlanUuid(null);
            }
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.suspendPlanBatchByParam(optPlanDto, new Date()));

            //只勾选
        }else if(PlanOperTypeEnum.CHECK.getType() == optPlanDto.getType()){
            if(null != optPlanDto
                    && null != optPlanDto.getCheckPlanUuid()
                    && optPlanDto.getCheckPlanUuid().size()>0){
                bool = DaoHandler.getMapperBoolRes(
                        planBatchMapper.suspendPlanBatchById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList(), new Date()));
            }

            //全选去勾
        }else if(PlanOperTypeEnum.NO_CHECK.getType() == optPlanDto.getType()){
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.suspendPlanBatchByParam(optPlanDto, new Date()));
        }
        return bool;
    }

    /**
     * 停止计划任务
     * @param optPlanDto
     * @return
     */
    @Override
    public boolean stopPlanBatch(OptPlanDto optPlanDto) {
        boolean bool = false;
        if(null != optPlanDto){
            //操作开始、结束时间
            if(!StringUtils.isEmpty(optPlanDto.getStartTime()) && !StringUtils.isEmpty(optPlanDto.getEndTime())) {
                optPlanDto.setStartTime(optPlanDto.getStartTime() + " " + DateTimeUtils.DEFAULT_DATE_START_TIME);
                optPlanDto.setEndTime(optPlanDto.getEndTime() + " " + DateTimeUtils.DEFAULT_DATE_END_TIME);
            }

            //如果不查batchId时，前端会传batchId=0
            if (optPlanDto.getBatchId() != null && optPlanDto.getBatchId() != 0) {
                optPlanDto.setBatchId(optPlanDto.getBatchId());
            }

            //获取权限
            Integer authLevel = optPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, optPlanDto.getUserId()));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel, optPlanDto.getOperOrgId()));//获取组织ID
        }

        //全选
        if(PlanOperTypeEnum.ALL.getType() == optPlanDto.getType()){
            if(null != optPlanDto){
                optPlanDto.setNocheckPlanUuid(null);
            }
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.stopPlanBatchByParam(optPlanDto, new Date()));

        //只勾选
        }else if(PlanOperTypeEnum.CHECK.getType() == optPlanDto.getType()){
            if(null != optPlanDto
                    && null != optPlanDto.getCheckPlanUuid()
                    && optPlanDto.getCheckPlanUuid().size()>0){
                bool = DaoHandler.getMapperBoolRes(
                        planBatchMapper.stopPlanBatchById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList(), new Date()));
            }

        //全选去勾
        }else if(PlanOperTypeEnum.NO_CHECK.getType() == optPlanDto.getType()){
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.stopPlanBatchByParam(optPlanDto, new Date()));
        }
        return bool;
    }

    /**
     * 恢复计划任务
     * @param optPlanDto
     * @return
     */
    @Override
    public boolean recoveryPlanBatch(OptPlanDto optPlanDto) {
        boolean bool = false;
        if(null != optPlanDto){
            //操作开始、结束时间
            if(!StringUtils.isEmpty(optPlanDto.getStartTime()) && !StringUtils.isEmpty(optPlanDto.getEndTime())) {
                optPlanDto.setStartTime(optPlanDto.getStartTime() + " " + DateTimeUtils.DEFAULT_DATE_START_TIME);
                optPlanDto.setEndTime(optPlanDto.getEndTime() + " " + DateTimeUtils.DEFAULT_DATE_END_TIME);
            }

            //如果不查batchId时，前端会传batchId=0
            if (optPlanDto.getBatchId() != null && optPlanDto.getBatchId() != 0) {
                optPlanDto.setBatchId(optPlanDto.getBatchId());
            }

            //获取权限
            Integer authLevel = optPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, optPlanDto.getUserId()));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel, optPlanDto.getOperOrgId()));//获取组织ID
        }

        //全选
        if(PlanOperTypeEnum.ALL.getType() == optPlanDto.getType()){
            if(null != optPlanDto){
                optPlanDto.setNocheckPlanUuid(null);
            }
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.recoveryPlanBatchByParam(optPlanDto, new Date()));

            //只勾选
        }else if(PlanOperTypeEnum.CHECK.getType() == optPlanDto.getType()){
            if(null != optPlanDto
                    && null != optPlanDto.getCheckPlanUuid()
                    && optPlanDto.getCheckPlanUuid().size()>0){
                bool = DaoHandler.getMapperBoolRes(
                        planBatchMapper.recoveryPlanBatchById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList(), new Date()));
            }

            //全选去勾
        }else if(PlanOperTypeEnum.NO_CHECK.getType() == optPlanDto.getType()){
            bool = DaoHandler.getMapperBoolRes(
                    planBatchMapper.recoveryPlanBatchByParam(optPlanDto, new Date()));
        }
        return bool;
    }

    /**
     * 批量加入计划任务
     * @param joinPlanDto
     * @return
     */
    @Override
    public boolean joinPlanBatch(JoinPlanDto joinPlanDto) {
        boolean bool = false;
        if(null != joinPlanDto){
            Long operUserId = Long.valueOf(joinPlanDto.getOperUserId());    //操作用户ID
            Integer operOrgId = joinPlanDto.getOperOrgId();     //企业组织ID
            String operOrgCode = joinPlanDto.getOperOrgCode();  //企业编码
            OptPlanDto optPlanDto = joinPlanDto.getOptPlan();
            DispatchPlan submitPlan = joinPlanDto.getDispatchPlan();

            // 查询用户名称
            SysUser sysUser = getApiService.getUserById(operUserId+"");
            if (null == sysUser) {
                throw new GuiyuException("用户不存在");
            }

            //线路入库
            DispatchPlanBatch batchPlan = new DispatchPlanBatch();
            batchPlan.setUserId(operUserId.intValue());
            batchPlan.setOrgCode(operOrgCode);
            batchPlan.setName(submitPlan.getBatchName());
            batchPlan.setBatchName(submitPlan.getBatchName());
            batchPlan.setStatusShow(Constant.BATCH_STATUS_SHOW);
            batchPlan.setStatusNotify(SyncStatusEnum.NO_SYNC.getStatus());
            batchPlan.setGmtCreate(new Date());
            batchPlan.setGmtModified(new Date());
            dispatchPlanBatchMapper.insert(batchPlan);
            Integer batchId = batchPlan.getId();
            //获取权限
            Integer authLevel = joinPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, operUserId+""));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel,operOrgId));//获取组织ID
            int limit = 30000;
            //查询条件列表（注意，号码去重）
            List<String> phoneList = planBatchMapper.getDisPhone(optPlanDto, limit);
            logger.info(">>>>>加入数量:{}", null != phoneList?phoneList.size():0);
            for(String phone : phoneList){
                this.pushPlanCreateMQ(submitPlan, batchId, phone, operUserId, operOrgId, operOrgCode);
            }

            //批量加入MQ
        //    this.batchJoin(joinPlanDto, batchPlan.getId());
            bool = true;
        }
        return bool;
    }

    protected void batchJoin(JoinPlanDto joinPlanDto, Integer batchId){
        Long operUserId = Long.valueOf(joinPlanDto.getOperUserId());    //操作用户ID
        Integer operOrgId = joinPlanDto.getOperOrgId();     //企业组织ID
        String operOrgCode = joinPlanDto.getOperOrgCode();  //企业编码
        OptPlanDto optPlanDto = joinPlanDto.getOptPlan();
        DispatchPlan submitPlan = joinPlanDto.getDispatchPlan();
        //获取权限
        Integer authLevel = joinPlanDto.getAuthLevel();//操作用户权限等级
        optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, operUserId+""));//获取用户ID,如果不是本人权限，则为null
        optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                optPlanDto.getOrgIdList()
                :getAuthUtil.getOrgIdsByAuthLevel(authLevel,operOrgId));//获取组织ID
        int limit = 30000;
        //查询条件列表（注意，号码去重）
        List<String> phoneList = planBatchMapper.getDisPhone(optPlanDto, limit);
        logger.info(">>>>>加入数量:{}", null != phoneList?phoneList.size():0);
        for(String phone : phoneList){
            this.pushPlanCreateMQ(submitPlan, batchId, phone, operUserId, operOrgId, operOrgCode);
        }
    }

    /**
     * 推送MQ
     * @param submitPlan
     * @param batchId
     * @param phone
     * @param userId
     * @param orgId
     * @param orgCode
     */
    private void pushPlanCreateMQ(DispatchPlan submitPlan, Integer batchId, String phone, Long userId, Integer orgId, String orgCode){
        try {
            DispatchPlan newPlan = new DispatchPlan();
            newPlan.setBatchId(batchId);
            newPlan.setParams(submitPlan.getParams());
            newPlan.setAttach(submitPlan.getAttach());
            newPlan.setPhone(phone);

            newPlan.setBatchId(batchId);
            newPlan.setUserId(userId.intValue());
            newPlan.setOrgCode(orgCode);
            newPlan.setOrgId(orgId);

            batchImportQueueHandler.add(newPlan);
        }catch(Exception e){
            logger.error("批量加入计划，单条加入MQ异常", e);
        }
    }

    @Override
    public boolean exportPlanBatch(OptPlanDto optPlanDto) {
        boolean bool = true;
        if(null != optPlanDto){
            //操作开始、结束时间
            if(!StringUtils.isEmpty(optPlanDto.getStartTime()) && !StringUtils.isEmpty(optPlanDto.getEndTime())) {
                optPlanDto.setStartTime(optPlanDto.getStartTime() + " " + DateTimeUtils.DEFAULT_DATE_START_TIME);
                optPlanDto.setEndTime(optPlanDto.getEndTime() + " " + DateTimeUtils.DEFAULT_DATE_END_TIME);
            }

            //如果不查batchId时，前端会传batchId=0
            if (optPlanDto.getBatchId() != null && optPlanDto.getBatchId() != 0) {
                optPlanDto.setBatchId(optPlanDto.getBatchId());
            }

            //获取权限
            Integer authLevel = optPlanDto.getAuthLevel();//操作用户权限等级
            optPlanDto.setUserId(getAuthUtil.getUserIdByAuthLevel(authLevel, optPlanDto.getUserId()));//获取用户ID,如果不是本人权限，则为null
            optPlanDto.setOrgIdList((null != optPlanDto.getOrgIdList() && optPlanDto.getOrgIdList().size()>0)?
                    optPlanDto.getOrgIdList()
                    :getAuthUtil.getOrgIdsByAuthLevel(authLevel, optPlanDto.getOperOrgId()));//获取组织ID
        }

        logger.info(">>>>>>>>>>>>>>>>>>start");
        /*logger.info(">>>>>>>>>>>>>>>>>>start exportPlanBatch");
        //导出上传文件
        this.exportFile(optPlanDto);
        logger.info(">>>>>>>>>>>>>>>>>>end exportPlanBatch");*/

       this.executeThread(optPlanDto);

    //    this.executeExecutor(optPlanDto);

        logger.info(">>>>>>>>>>>>>>>>>>end");
        return bool;
    }


    public void executeThread(OptPlanDto optPlanDto) {
        logger.info("start executeThread");
        try{
            asyncServiceExecutor.execute(new Runnable(){
                @Override
                public void run(){
                    //导出上传文件
                    exportFile(optPlanDto);
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeThread");
    }

    public void executeExecutor(OptPlanDto optPlanDto) {
        logger.info("start executeExecutor");
        try{
            Future future = executorService.submit(new Runnable(){
                @Override
                public void run() {
                    //导出上传文件
                    exportFile(optPlanDto);
                }

                public Object call() throws Exception {
                    //导出上传文件
                    exportFile(optPlanDto);
                    return "Callable Result";
                }
            });
            logger.info("future.get() = " + future.get());
        }catch(Exception e){
            e.printStackTrace();
        }
        logger.info("end executeExecutor");
    }

//    @Autowired
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Autowired
    private ThreadPoolTaskExecutor asyncServiceExecutor;

//    @Async("asyncImportPlanExecutor")
    public void exportFile(OptPlanDto optPlanDto){
        logger.info(">>>>>>>>>>>>>>start exportFile");
        List<DownLoadPlanVo> planList = new ArrayList<DownLoadPlanVo>();
        int count = 0;
        int limit = 1000000;
        //全选
        if(PlanOperTypeEnum.ALL.getType() == optPlanDto.getType()){
            if(null != optPlanDto){
                optPlanDto.setNocheckPlanUuid(null);
            }
            planList = planBatchMapper.queryExportPlanList(optPlanDto, limit);
            count = planBatchMapper.queryExportPlanCountList(optPlanDto);

        //只勾选
        }else if(PlanOperTypeEnum.CHECK.getType() == optPlanDto.getType()){
            if(null != optPlanDto
                    && null != optPlanDto.getCheckPlanUuid()
                    && optPlanDto.getCheckPlanUuid().size()>0){
                planList = planBatchMapper.queryExportPlanById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList(), limit);
                count = planBatchMapper.queryExportPlanCountById(optPlanDto.getCheckPlanUuid(), optPlanDto.getOrgIdList());
            }

        //全选去勾
        }else if(PlanOperTypeEnum.NO_CHECK.getType() == optPlanDto.getType()){
            planList = planBatchMapper.queryExportPlanList(optPlanDto, limit);
            count = planBatchMapper.queryExportPlanCountList(optPlanDto);
        }

        //增加导出文件记录
        String operUserId = optPlanDto.getOperUserId();
        String operOrgCode = optPlanDto.getOperOrgCode();
        ExportFileRecord recordRes = exportFileService.addExportFile(getExportFileData(operUserId, operOrgCode,count<limit?count:limit));

        File execlFile = null;	//生成文件
        File zipFile = null;		//压缩文件
        SysFileRspVO resFile = null;
        boolean bool = false;
        try {
            //生成execl
            execlFile = this.generateDownloadExcel(planList, 1);
            logger.info(">>>>>>>>>>>>>>生成execl success");
            //压缩文件
            zipFile = this.generateZipFile(execlFile);
            logger.info(">>>>>>>>>>>>>>生成zip success");
            //上传压缩文件
            resFile = this.uploadFile(zipFile);
            logger.info(">>>>>>>>>>>>>>上传 success :{}", JsonUtils.bean2Json(resFile));
            bool = true;
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //导出结果变更
            exportFileService.endExportFile(recordRes.getRecordId(),
                    bool ? ExportFileStatusEnum.FINISH.getStatus() : ExportFileStatusEnum.FAIL.getStatus(),
                    null != resFile ? resFile.getSkUrl() : null);
        }

        logger.info(">>>>>>>>>>>>>>end exportFile");
    }

    @Value("${file.tmpPath}")
    private String tmpPath;

    private File generateDownloadExcel(List<DownLoadPlanVo> planList, Integer isDesensitization)
            throws RowsExceededException, WriteException, IOException {

        File execFile = new File(tmpPath+ System.currentTimeMillis() + ".xls");
        OutputStream out = new FileOutputStream(execFile);
        Map<String, String> map = new HashMap<>();
        map.put("1", "计划中");
        map.put("2", "已完成");
        map.put("3", "已暂停");
        map.put("4", "已停止");
        WritableWorkbook wb = Workbook.createWorkbook(out);
        WritableSheet sheet = wb.createSheet("sheet1", 0);
        WritableCellFormat format = new WritableCellFormat();
        format.setBorder(Border.ALL, BorderLineStyle.THIN);
        format.setWrap(true);

        sheet.setColumnView(0, 12);
        sheet.setColumnView(1, 12);

        sheet.addCell(new Label(0, 0, "批次"));
        sheet.addCell(new Label(1, 0, "号码"));
        sheet.addCell(new Label(2, 0, "变量参数"));
        sheet.addCell(new Label(3, 0, "附件参数"));
        sheet.addCell(new Label(4, 0, "计划状态"));
        sheet.addCell(new Label(5, 0, "意向标签"));
        sheet.addCell(new Label(6, 0, "话术"));
        sheet.addCell(new Label(7, 0, "线路"));
        sheet.addCell(new Label(8, 0, "计划日期"));
        sheet.addCell(new Label(9, 0, "计划时间"));
        sheet.addCell(new Label(10, 0, "所属用户"));
        sheet.addCell(new Label(11, 0, "添加日期"));

        Map<Integer, String> batchLineMap = new HashMap<>();
        int len = planList.size();
        for (int i = 0; i < planList.size(); i++) {
            DownLoadPlanVo dispatchPlan = planList.get(i);

            int k = 0;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getBatchName()));
            k++;
            if(isDesensitization.equals(0)){
                String phoneNumber = dispatchPlan.getPhone().substring(0, 3) + "****"
                        + dispatchPlan.getPhone().substring(7, dispatchPlan.getPhone().length());
                sheet.addCell(new Label(k, i + 1,phoneNumber));
                k++;
            }else{
                sheet.addCell(new Label(k, i + 1, dispatchPlan.getPhone()));
                k++;
            }
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getParams()));//变量参数
            k++;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getAttach()));//附件参数
            k++;
            sheet.addCell(new Label(k, i + 1, map.get(String.valueOf(dispatchPlan.getStatusPlan()))));
            k++;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getResult()));//意向标签
            k++;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getRobotName()));
            k++;

            //查询线路
            String lineNames = "";
            /*if (!batchLineMap.containsKey(dispatchPlan.getBatchId())) {
                List<DispatchBatchLine> queryLinesByPlanUUID = lineServiceImpl.queryListByBatchId(dispatchPlan.getBatchId());

                if (queryLinesByPlanUUID != null && !queryLinesByPlanUUID.isEmpty()) {
                    String lineName = "";
                    //查询线路
                    for (DispatchBatchLine lines : queryLinesByPlanUUID) {
                        lineName = lineName + "" + lines.getLineName() + ",";
                    }

                    lineNames = lineName.substring(0, lineName.length() - 1);
                    batchLineMap.put(dispatchPlan.getBatchId(), lineNames);
                }
            } else {
                lineNames = batchLineMap.get(dispatchPlan.getBatchId());
            }*/

            sheet.addCell(new Label(k, i + 1, lineNames));
            k++;
            sheet.addCell(new Label(k, i + 1, String.valueOf(dispatchPlan.getCallData())));
            k++;
            sheet.addCell(new Label(k, i + 1, String.valueOf(dispatchPlan.getCallHour())));
            k++;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getUsername()));
            k++;
            sheet.addCell(new Label(k, i + 1, dispatchPlan.getAddTime()));
        }

        wb.write();
        wb.close();

        return execFile;
    }

    /**
     * 生成.zip文件;
     * @param excelFile
     * @return
     * @throws IOException
     */
    public File generateZipFile(File excelFile){
        File file = new File(this.tmpPath + File.separator + System.currentTimeMillis() +".zip");
        ZipOutputStream zipOutputStream = null;
        try {
            zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
            //	File[] files = new File(path).listFiles();
            FileInputStream fileInputStream = null;
            byte[] buf = new byte[1024];
            int len = 0;
		/*if(files!=null && files.length > 0){
			for(File excelFile:files){*/
            String fileName = excelFile.getName();
            fileInputStream = new FileInputStream(excelFile);
            //放入压缩zip包中;
            zipOutputStream.putNextEntry(new ZipEntry( fileName));//this.tmpPath + File.separator  +
            //读取文件;
            while ((len = fileInputStream.read(buf)) > 0) {
                zipOutputStream.write(buf, 0, len);
            }
            //关闭;
            zipOutputStream.closeEntry();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
			/*}
		}*/
        }catch(IOException ex){
            logger.error("生成.zip文件异常", ex);
            throw new GuiyuException("生成zip文件异常", ex);
        }catch(Exception e){
            logger.error("生成.zip文件异常", e);
            throw new GuiyuException("生成zip文件异常", e);
        }finally {
            if (zipOutputStream != null) {
                try {
                    zipOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return file;

		/*File f = new File(lj);
		InputStream istream = new FileInputStream(f);
		return istream;*/
    }

    /**
     * 上传nas文件
     * @param zipFile
     * @return
     */
    private SysFileRspVO uploadFile(File zipFile){
        try {
            Long fileRecordId = System.currentTimeMillis();
            SysFileReqVO sysFileReqVO = new SysFileReqVO();
            sysFileReqVO.setBusiId(System.currentTimeMillis() + "");
            sysFileReqVO.setBusiType("dispatch"); // 上传的影像文件业务类型
            sysFileReqVO.setSysCode("02"); // 文件上传系统码
            sysFileReqVO.setThumbImageFlag("0"); // 是否需要生成缩略图,0-无需生成，1-生成，默认不生成缩略图
            SysFileRspVO sysFileRsp = new NasUtil().uploadNas(sysFileReqVO, zipFile);
            System.out.println(JsonUtils.bean2Json(sysFileRsp));
            return sysFileRsp;
        }catch(Exception e){
            logger.error("上传nas异常", e);
            throw new GuiyuException("上传nas异常", e);
        }
    }

    /**
     * 封装
     * @return
     */
    private ExportFileDto getExportFileData(String userId, String orgCode, Integer count){
        ExportFileDto data = new ExportFileDto();
        data.setBusiId("dispatchBatchExport");
        data.setBusiType(BusiTypeEnum.DISPATCH.getType());
        data.setFileOriginalUrl(null);
        data.setFileType(FileTypeEnum.EXECL.getType());
        data.setTotalNum(count);
        SysUser user = getApiService.getUserById(userId);
        data.setUserId(userId);
        data.setOrgCode(orgCode);
        data.setCreateName(null != user?user.getUsername():null);
        data.setCreateTime(new SimpleDateFormat(DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_FULL).format(new Date()));
		/*data.setCreateName(fileRecords.getUserName());
		data.setCreateTime(new SimpleDateFormat(DateTimeUtils.DEFAULT_DATE_FORMAT_PATTERN_FULL).format(fileRecords.getCreateTime()));*/
        return data;
    }
}