package com.guiji.dispatch.dto;

import com.guiji.dispatch.sys.PageDto;

import java.util.Date;

public class QueryExportFileRecordDto extends PageDto {

    /**
     * 记录ID唯一标识
     */
    private String recordId;

    /**
     * 业务唯一标识ID
     */
    private String busiId;

    /**
     * 业务类型  02-任务中心  03-呼叫中心
     */
    private String busiType;

    /**
     * 文件类型  1-execl文件 2-音频文件 3-视频文件
     */
    private Integer fileType;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 企业组织Code
     */
    private String orgCode;

    /**
     * 创建日期
     */
    private Date addTime;

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBusiId() {
        return busiId;
    }

    public void setBusiId(String busiId) {
        this.busiId = busiId;
    }

    public String getBusiType() {
        return busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public Integer getFileType() {
        return fileType;
    }

    public void setFileType(Integer fileType) {
        this.fileType = fileType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }
}
