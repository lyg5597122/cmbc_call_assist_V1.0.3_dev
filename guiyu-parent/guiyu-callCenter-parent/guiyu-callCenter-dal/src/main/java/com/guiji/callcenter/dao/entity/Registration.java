package com.guiji.callcenter.dao.entity;

import java.io.Serializable;
import java.util.Date;

public class Registration implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.reg_id
     *
     * @mbggenerated
     */
    private Long regId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.creator
     *
     * @mbggenerated
     */
    private Long creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.customer_addr
     *
     * @mbggenerated
     */
    private String customerAddr;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.customer_mobile
     *
     * @mbggenerated
     */
    private String customerMobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.customer_name
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column registration.update_user
     *
     * @mbggenerated
     */
    private Long updateUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table registration
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.reg_id
     *
     * @return the value of registration.reg_id
     *
     * @mbggenerated
     */
    public Long getRegId() {
        return regId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.reg_id
     *
     * @param regId the value for registration.reg_id
     *
     * @mbggenerated
     */
    public void setRegId(Long regId) {
        this.regId = regId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.create_time
     *
     * @return the value of registration.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.create_time
     *
     * @param createTime the value for registration.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.creator
     *
     * @return the value of registration.creator
     *
     * @mbggenerated
     */
    public Long getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.creator
     *
     * @param creator the value for registration.creator
     *
     * @mbggenerated
     */
    public void setCreator(Long creator) {
        this.creator = creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.customer_addr
     *
     * @return the value of registration.customer_addr
     *
     * @mbggenerated
     */
    public String getCustomerAddr() {
        return customerAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.customer_addr
     *
     * @param customerAddr the value for registration.customer_addr
     *
     * @mbggenerated
     */
    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.customer_mobile
     *
     * @return the value of registration.customer_mobile
     *
     * @mbggenerated
     */
    public String getCustomerMobile() {
        return customerMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.customer_mobile
     *
     * @param customerMobile the value for registration.customer_mobile
     *
     * @mbggenerated
     */
    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.customer_name
     *
     * @return the value of registration.customer_name
     *
     * @mbggenerated
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.customer_name
     *
     * @param customerName the value for registration.customer_name
     *
     * @mbggenerated
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.remark
     *
     * @return the value of registration.remark
     *
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.remark
     *
     * @param remark the value for registration.remark
     *
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.update_time
     *
     * @return the value of registration.update_time
     *
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.update_time
     *
     * @param updateTime the value for registration.update_time
     *
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column registration.update_user
     *
     * @return the value of registration.update_user
     *
     * @mbggenerated
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column registration.update_user
     *
     * @param updateUser the value for registration.update_user
     *
     * @mbggenerated
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }
}