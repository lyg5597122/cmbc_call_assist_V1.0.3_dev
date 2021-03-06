package com.guiji.callcenter.dao.entity;

import java.io.Serializable;

public class FsConfig implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fs_config.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fs_config.role_id
     *
     * @mbggenerated
     */
    private String roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fs_config.module
     *
     * @mbggenerated
     */
    private String module;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fs_config.bus_key
     *
     * @mbggenerated
     */
    private String busKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column fs_config.value
     *
     * @mbggenerated
     */
    private String value;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table fs_config
     *
     * @mbggenerated
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fs_config.id
     *
     * @return the value of fs_config.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fs_config.id
     *
     * @param id the value for fs_config.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fs_config.role_id
     *
     * @return the value of fs_config.role_id
     *
     * @mbggenerated
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fs_config.role_id
     *
     * @param roleId the value for fs_config.role_id
     *
     * @mbggenerated
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fs_config.module
     *
     * @return the value of fs_config.module
     *
     * @mbggenerated
     */
    public String getModule() {
        return module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fs_config.module
     *
     * @param module the value for fs_config.module
     *
     * @mbggenerated
     */
    public void setModule(String module) {
        this.module = module;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fs_config.bus_key
     *
     * @return the value of fs_config.bus_key
     *
     * @mbggenerated
     */
    public String getBusKey() {
        return busKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fs_config.bus_key
     *
     * @param busKey the value for fs_config.bus_key
     *
     * @mbggenerated
     */
    public void setBusKey(String busKey) {
        this.busKey = busKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column fs_config.value
     *
     * @return the value of fs_config.value
     *
     * @mbggenerated
     */
    public String getValue() {
        return value;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column fs_config.value
     *
     * @param value the value for fs_config.value
     *
     * @mbggenerated
     */
    public void setValue(String value) {
        this.value = value;
    }
}