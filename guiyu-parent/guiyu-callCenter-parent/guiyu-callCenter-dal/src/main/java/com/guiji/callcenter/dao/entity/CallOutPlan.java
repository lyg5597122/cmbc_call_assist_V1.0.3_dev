package com.guiji.callcenter.dao.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

public class CallOutPlan implements Serializable {
    private BigInteger callId;

    private String planUuid;

    private String phoneNum;

    private Integer customerId;

    private String tempId;

    private Integer lineId;

    private String serverid;

    private String agentId;

    private Date agentAnswerTime;

    private String agentChannelUuid;

    private String agentGroupId;

    private Date agentStartTime;

    private Date createTime;

    private Date callStartTime;

    private Date hangupTime;

    private Date answerTime;

    private Integer duration;

    private Integer billSec;

    private Integer callDirection;

    private Integer callState;

    private Integer hangupDirection;

    private String accurateIntent;

    private String reason;

    private String hangupCode;

    private String remarks;

    private Boolean hasTts;

    private String aiId;

    private Integer freason;

    private Integer isdel;

    private Integer isread;

    private String orgCode;

    private Integer orgId;

    private Integer batchId;

    private Integer talkNum;

    private Integer isCancel;

    private Integer isAnswer;

    private Boolean intervened;

    private String lineName;

    private String params;

    private String enterprise;

    private String answerUser;

    private Date importTime;

    private static final long serialVersionUID = 1L;

    public BigInteger getCallId() {
        return callId;
    }

    public void setCallId(BigInteger callId) {
        this.callId = callId;
    }

    public String getPlanUuid() {
        return planUuid;
    }

    public void setPlanUuid(String planUuid) {
        this.planUuid = planUuid == null ? null : planUuid.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getTempId() {
        return tempId;
    }

    public void setTempId(String tempId) {
        this.tempId = tempId == null ? null : tempId.trim();
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getServerid() {
        return serverid;
    }

    public void setServerid(String serverid) {
        this.serverid = serverid == null ? null : serverid.trim();
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId == null ? null : agentId.trim();
    }

    public Date getAgentAnswerTime() {
        return agentAnswerTime;
    }

    public void setAgentAnswerTime(Date agentAnswerTime) {
        this.agentAnswerTime = agentAnswerTime;
    }

    public String getAgentChannelUuid() {
        return agentChannelUuid;
    }

    public void setAgentChannelUuid(String agentChannelUuid) {
        this.agentChannelUuid = agentChannelUuid == null ? null : agentChannelUuid.trim();
    }

    public String getAgentGroupId() {
        return agentGroupId;
    }

    public void setAgentGroupId(String agentGroupId) {
        this.agentGroupId = agentGroupId == null ? null : agentGroupId.trim();
    }

    public Date getAgentStartTime() {
        return agentStartTime;
    }

    public void setAgentStartTime(Date agentStartTime) {
        this.agentStartTime = agentStartTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCallStartTime() {
        return callStartTime;
    }

    public void setCallStartTime(Date callStartTime) {
        this.callStartTime = callStartTime;
    }

    public Date getHangupTime() {
        return hangupTime;
    }

    public void setHangupTime(Date hangupTime) {
        this.hangupTime = hangupTime;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getBillSec() {
        return billSec;
    }

    public void setBillSec(Integer billSec) {
        this.billSec = billSec;
    }

    public Integer getCallDirection() {
        return callDirection;
    }

    public void setCallDirection(Integer callDirection) {
        this.callDirection = callDirection;
    }

    public Integer getCallState() {
        return callState;
    }

    public void setCallState(Integer callState) {
        this.callState = callState;
    }

    public Integer getHangupDirection() {
        return hangupDirection;
    }

    public void setHangupDirection(Integer hangupDirection) {
        this.hangupDirection = hangupDirection;
    }

    public String getAccurateIntent() {
        return accurateIntent;
    }

    public void setAccurateIntent(String accurateIntent) {
        this.accurateIntent = accurateIntent == null ? null : accurateIntent.trim();
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getHangupCode() {
        return hangupCode;
    }

    public void setHangupCode(String hangupCode) {
        this.hangupCode = hangupCode == null ? null : hangupCode.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Boolean getHasTts() {
        return hasTts;
    }

    public void setHasTts(Boolean hasTts) {
        this.hasTts = hasTts;
    }

    public String getAiId() {
        return aiId;
    }

    public void setAiId(String aiId) {
        this.aiId = aiId == null ? null : aiId.trim();
    }

    public Integer getFreason() {
        return freason;
    }

    public void setFreason(Integer freason) {
        this.freason = freason;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public Integer getTalkNum() {
        return talkNum;
    }

    public void setTalkNum(Integer talkNum) {
        this.talkNum = talkNum;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(Integer isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Boolean getIntervened() {
        return intervened;
    }

    public void setIntervened(Boolean intervened) {
        this.intervened = intervened;
    }

    public String getLineName() {
        return lineName;
    }

    public void setLineName(String lineName) {
        this.lineName = lineName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(String enterprise) {
        this.enterprise = enterprise;
    }

    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public Date getImportTime() {
        return importTime;
    }

    public void setImportTime(Date importTime) {
        this.importTime = importTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", callId=").append(callId);
        sb.append(", planUuid=").append(planUuid);
        sb.append(", phoneNum=").append(phoneNum);
        sb.append(", customerId=").append(customerId);
        sb.append(", tempId=").append(tempId);
        sb.append(", lineId=").append(lineId);
        sb.append(", serverid=").append(serverid);
        sb.append(", agentId=").append(agentId);
        sb.append(", agentAnswerTime=").append(agentAnswerTime);
        sb.append(", agentChannelUuid=").append(agentChannelUuid);
        sb.append(", agentGroupId=").append(agentGroupId);
        sb.append(", agentStartTime=").append(agentStartTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", callStartTime=").append(callStartTime);
        sb.append(", hangupTime=").append(hangupTime);
        sb.append(", answerTime=").append(answerTime);
        sb.append(", duration=").append(duration);
        sb.append(", billSec=").append(billSec);
        sb.append(", callDirection=").append(callDirection);
        sb.append(", callState=").append(callState);
        sb.append(", hangupDirection=").append(hangupDirection);
        sb.append(", accurateIntent=").append(accurateIntent);
        sb.append(", reason=").append(reason);
        sb.append(", hangupCode=").append(hangupCode);
        sb.append(", remarks=").append(remarks);
        sb.append(", hasTts=").append(hasTts);
        sb.append(", aiId=").append(aiId);
        sb.append(", freason=").append(freason);
        sb.append(", isdel=").append(isdel);
        sb.append(", isread=").append(isread);
        sb.append(", orgCode=").append(orgCode);
        sb.append(", orgId=").append(orgId);
        sb.append(", batchId=").append(batchId);
        sb.append(", talkNum=").append(talkNum);
        sb.append(", isCancel=").append(isCancel);
        sb.append(", isAnswer=").append(isAnswer);
        sb.append(", intervened=").append(intervened);
        sb.append(", params=").append(params);
        sb.append(", lineName=").append(lineName);
        sb.append(", enterprise=").append(enterprise);
        sb.append(", answerUser=").append(answerUser);
        sb.append(", importTime=").append(importTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}