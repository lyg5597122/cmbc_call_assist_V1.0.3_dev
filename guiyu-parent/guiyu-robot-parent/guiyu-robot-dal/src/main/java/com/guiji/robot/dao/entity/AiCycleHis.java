package com.guiji.robot.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class AiCycleHis implements Serializable {
    private String id;

    private String assignId;

    private Long userId;

    private String aiNo;

    private String templateId;

    private String assignDate;

    private String assignTime;

    private String taskbackDate;

    private String taskbackTime;

    private Date crtTime;

    private String callNum;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAssignId() {
        return assignId;
    }

    public void setAssignId(String assignId) {
        this.assignId = assignId == null ? null : assignId.trim();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAiNo() {
        return aiNo;
    }

    public void setAiNo(String aiNo) {
        this.aiNo = aiNo == null ? null : aiNo.trim();
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId == null ? null : templateId.trim();
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate == null ? null : assignDate.trim();
    }

    public String getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(String assignTime) {
        this.assignTime = assignTime == null ? null : assignTime.trim();
    }

    public String getTaskbackDate() {
        return taskbackDate;
    }

    public void setTaskbackDate(String taskbackDate) {
        this.taskbackDate = taskbackDate == null ? null : taskbackDate.trim();
    }

    public String getTaskbackTime() {
        return taskbackTime;
    }

    public void setTaskbackTime(String taskbackTime) {
        this.taskbackTime = taskbackTime == null ? null : taskbackTime.trim();
    }

    public Date getCrtTime() {
        return crtTime;
    }

    public void setCrtTime(Date crtTime) {
        this.crtTime = crtTime;
    }

    public String getCallNum() {
        return callNum;
    }

    public void setCallNum(String callNum) {
        this.callNum = callNum == null ? null : callNum.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", assignId=").append(assignId);
        sb.append(", userId=").append(userId);
        sb.append(", aiNo=").append(aiNo);
        sb.append(", templateId=").append(templateId);
        sb.append(", assignDate=").append(assignDate);
        sb.append(", assignTime=").append(assignTime);
        sb.append(", taskbackDate=").append(taskbackDate);
        sb.append(", taskbackTime=").append(taskbackTime);
        sb.append(", crtTime=").append(crtTime);
        sb.append(", callNum=").append(callNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}