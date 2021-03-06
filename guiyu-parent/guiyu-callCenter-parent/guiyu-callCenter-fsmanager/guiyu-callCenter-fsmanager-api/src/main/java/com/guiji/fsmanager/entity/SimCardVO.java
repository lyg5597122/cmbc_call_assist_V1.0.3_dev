package com.guiji.fsmanager.entity;

import java.io.Serializable;

public class SimCardVO implements Serializable {
    private int startCount;

    private int startPwd;

    private int countsStep;

    private int pwdStep;

    private int countNum;

    private String gatewayId;

    public int getStartCount() {
        return startCount;
    }

    public void setStartCount(int startCount) {
        this.startCount = startCount;
    }

    public int getStartPwd() {
        return startPwd;
    }

    public void setStartPwd(int startPwd) {
        this.startPwd = startPwd;
    }

    public int getCountsStep() {
        return countsStep;
    }

    public void setCountsStep(int countsStep) {
        this.countsStep = countsStep;
    }

    public int getPwdStep() {
        return pwdStep;
    }

    public void setPwdStep(int pwdStep) {
        this.pwdStep = pwdStep;
    }

    public int getCountNum() {
        return countNum;
    }

    public void setCountNum(int countNum) {
        this.countNum = countNum;
    }

    public String getGatewayId() {
        return gatewayId;
    }

    public void setGatewayId(String gatewayId) {
        this.gatewayId = gatewayId;
    }

    @Override
    public String toString() {
        return "SimCardVO{" +
                "startCount=" + startCount +
                ", startPwd=" + startPwd +
                ", countsStep=" + countsStep +
                ", pwdStep=" + pwdStep +
                ", countNum=" + countNum +
                ", gatewayId='" + gatewayId + '\'' +
                '}';
    }
}
