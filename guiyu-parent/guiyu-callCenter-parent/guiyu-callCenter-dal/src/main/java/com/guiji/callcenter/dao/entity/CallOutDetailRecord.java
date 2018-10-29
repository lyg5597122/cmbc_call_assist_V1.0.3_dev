package com.guiji.callcenter.dao.entity;

import java.io.Serializable;

public class CallOutDetailRecord implements Serializable {
    private Integer callDetailId;

    private String callId;

    private String agentRecordFile;

    private String agentRecordUrl;

    private String botRecordFile;

    private String botRecordUrl;

    private String customerRecordFile;

    private String customerRecordUrl;

    private static final long serialVersionUID = 1L;

    public Integer getCallDetailId() {
        return callDetailId;
    }

    public void setCallDetailId(Integer callDetailId) {
        this.callDetailId = callDetailId;
    }

    public String getCallId() {
        return callId;
    }

    public void setCallId(String callId) {
        this.callId = callId == null ? null : callId.trim();
    }

    public String getAgentRecordFile() {
        return agentRecordFile;
    }

    public void setAgentRecordFile(String agentRecordFile) {
        this.agentRecordFile = agentRecordFile == null ? null : agentRecordFile.trim();
    }

    public String getAgentRecordUrl() {
        return agentRecordUrl;
    }

    public void setAgentRecordUrl(String agentRecordUrl) {
        this.agentRecordUrl = agentRecordUrl == null ? null : agentRecordUrl.trim();
    }

    public String getBotRecordFile() {
        return botRecordFile;
    }

    public void setBotRecordFile(String botRecordFile) {
        this.botRecordFile = botRecordFile == null ? null : botRecordFile.trim();
    }

    public String getBotRecordUrl() {
        return botRecordUrl;
    }

    public void setBotRecordUrl(String botRecordUrl) {
        this.botRecordUrl = botRecordUrl == null ? null : botRecordUrl.trim();
    }

    public String getCustomerRecordFile() {
        return customerRecordFile;
    }

    public void setCustomerRecordFile(String customerRecordFile) {
        this.customerRecordFile = customerRecordFile == null ? null : customerRecordFile.trim();
    }

    public String getCustomerRecordUrl() {
        return customerRecordUrl;
    }

    public void setCustomerRecordUrl(String customerRecordUrl) {
        this.customerRecordUrl = customerRecordUrl == null ? null : customerRecordUrl.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", callDetailId=").append(callDetailId);
        sb.append(", callId=").append(callId);
        sb.append(", agentRecordFile=").append(agentRecordFile);
        sb.append(", agentRecordUrl=").append(agentRecordUrl);
        sb.append(", botRecordFile=").append(botRecordFile);
        sb.append(", botRecordUrl=").append(botRecordUrl);
        sb.append(", customerRecordFile=").append(customerRecordFile);
        sb.append(", customerRecordUrl=").append(customerRecordUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}