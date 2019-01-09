package com.guiji.calloutserver.eventbus.handler;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;
import com.guiji.callcenter.dao.entity.CallOutDetailRecord;
import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.callcenter.dao.entity.CallOutRecord;
import com.guiji.calloutserver.eventbus.event.AfterCallEvent;
import com.guiji.calloutserver.manager.DispatchManager;
import com.guiji.calloutserver.manager.FsAgentManager;
import com.guiji.calloutserver.service.CallOutDetailRecordService;
import com.guiji.calloutserver.service.CallOutRecordService;
import com.guiji.fsagent.entity.RecordVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class AfterCallHandler {
    @Autowired
    CallOutDetailRecordService callOutDetailRecordService;

    @Autowired
    CallOutRecordService callOutRecordService;

    @Autowired
    FsAgentManager fsAgentManager;

    @Autowired
    DispatchManager dispatchManager;

    @Autowired
    AsyncEventBus asyncEventBus;
    //注册这个监听器
    @PostConstruct
    public void register() {
        asyncEventBus.register(this);
    }

    @Subscribe
    @AllowConcurrentEvents
    public void handleAfterCall(AfterCallEvent afterCallEvent) {
        try {
            log.info("收到AfterCallEvent事件[{}]", afterCallEvent);

            CallOutPlan callPlan = afterCallEvent.getCallPlan();
            Preconditions.checkArgument(callPlan != null, "null callPlan");

            //上传呼叫时长不为空的文件
            if (callPlan.getDuration() > 0) {
                log.info("录音文件大于0，开始上传， callId[{}]", callPlan.getCallId());
                //调用fsagent上传主录音文件
                CallOutRecord callRecord = callOutRecordService.findByCallId(callPlan.getCallId());
                log.info("待上传的主录音文件为[{}]", callRecord);
                uploadMainRecord(callRecord,Long.valueOf(callPlan.getCustomerId()));

                List<CallOutDetailRecord> callOutDetailRecords = callOutDetailRecordService.findByCallId(callPlan.getCallId());
                log.info("待上传的分支对话录音为[{}]", callOutDetailRecords);
                uploadDetailsRecord(callOutDetailRecords,Long.valueOf(callPlan.getCustomerId()));
            }else{
                log.warn("录音文件大小为0，上传失败，callId[{}]", callPlan.getCallId());
            }

        } catch (Exception ex) {
            //TODO: 报警，上传录音文件失败
            log.warn("在处理呼叫结果时出现异常", ex);
        }
    }

    /**
     * 上传主录音文件
     *
     * @param callOutRecord
     * @param userId
     */
    public void uploadMainRecord(CallOutRecord callOutRecord, Long userId) {
        log.info("开始上传主录音，callId[{}], file[{}]", callOutRecord.getCallId(), callOutRecord.getRecordFile());
        RecordVO recordVO = fsAgentManager.uploadRecord(String.valueOf(callOutRecord.getCallId()), callOutRecord.getRecordFile(), "mainrecord",userId);
        log.info("上传录音返回结果为[{}]", recordVO);
        callOutRecord.setRecordUrl(recordVO.getFileUrl());
        callOutRecordService.update(callOutRecord);
    }

    /**
     * 上传通话各个环节录音
     *
     * @param callOutDetailRecords
     * @param userId
     */
    public void uploadDetailsRecord(List<CallOutDetailRecord> callOutDetailRecords, Long userId) {
        String busiType = "detailrecord";
        boolean isEdit = false;
        for (CallOutDetailRecord detailRecord : callOutDetailRecords) {
            log.info("开始上传客户录音,callId[{}][{}]", detailRecord.getCallId(), detailRecord.getCallDetailId());
            //上传客户说话录音
            if (!Strings.isNullOrEmpty(detailRecord.getCustomerRecordFile())) {
                String fileId = "customer_" + detailRecord.getCallId() + "_" + detailRecord.getCallDetailId();
                RecordVO recordVO = fsAgentManager.uploadRecord(fileId, detailRecord.getCustomerRecordFile(), busiType, userId);
                log.info("上传客户说话录音[{}][{}]，返回结果为[{}]", fileId, detailRecord.getCustomerRecordFile(), recordVO);
                detailRecord.setCustomerRecordUrl(recordVO.getFileUrl());
                isEdit = true;
            }else{
                log.info("忽略客户说话录音，因文件为空");
            }

            log.info("开始上传座席录音， callId[{}][{}]", detailRecord.getCallId(), detailRecord.getCallDetailId());
            //上传座席说话录音
            if (!Strings.isNullOrEmpty(detailRecord.getAgentRecordFile())) {
                String fileId = "agent_" + detailRecord.getCallId() + "_" + detailRecord.getCallDetailId();
                RecordVO recordVO = fsAgentManager.uploadRecord(fileId, detailRecord.getAgentRecordFile(), busiType, userId);
                log.info("上传座席说话录音[{}][{}]，返回结果为[{}]", fileId, detailRecord.getAgentRecordFile(), recordVO);
                detailRecord.setAgentRecordUrl(recordVO.getFileUrl());
                isEdit = true;
            }

            if (isEdit) {
                callOutDetailRecordService.update(detailRecord);
            }
        }
    }
}
