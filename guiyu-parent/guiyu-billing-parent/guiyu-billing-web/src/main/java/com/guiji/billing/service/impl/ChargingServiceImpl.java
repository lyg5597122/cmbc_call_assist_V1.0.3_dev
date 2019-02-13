package com.guiji.billing.service.impl;

import com.guiji.auth.api.IAuth;
import com.guiji.billing.constants.BusiTypeEnum;
import com.guiji.billing.dao.mapper.ext.BillingUserAcctMapper;
import com.guiji.billing.dto.CallChargingNotifyDto;
import com.guiji.billing.entity.BillingAcctChargingRecord;
import com.guiji.billing.entity.BillingAcctChargingTerm;
import com.guiji.billing.entity.BillingUserAcctBean;
import com.guiji.billing.enums.*;
import com.guiji.billing.exception.BaseException;
import com.guiji.billing.service.ChargingService;
import com.guiji.billing.utils.DaoHandler;
import com.guiji.billing.utils.IdWorker;
import com.guiji.billing.utils.ResHandler;
import com.guiji.component.result.Result;
import com.guiji.user.dao.entity.SysOrganization;
import com.guiji.user.dao.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 计费处理
 */
@Service
public class ChargingServiceImpl implements ChargingService {

    @Autowired
    private IAuth iAuth;

    @Autowired
    private BillingUserAcctMapper billingUserAcctMapper;

    @Autowired
    private IdWorker idWorker;

    /**
     * 通话计费处理
     * @param callChargingNotifyDto
     * @return
     */
    @Override
    public boolean charging(CallChargingNotifyDto callChargingNotifyDto) {
        boolean bool = false;
        if (null != callChargingNotifyDto){
            Integer userId = callChargingNotifyDto.getUserId();//用户ID
            Integer billSec = callChargingNotifyDto.getBillSec();//通话时长 秒
            String lineId = callChargingNotifyDto.getLineId()+"";   //通话线路
            String phone = callChargingNotifyDto.getPhone();    //号码
            Date beginTime = callChargingNotifyDto.getBeginTime();  //开始时间
            Date endTime = callChargingNotifyDto.getEndTime();  //结束时间
            //查询通话用户信息
            SysUser user = ResHandler.getResObj(iAuth.getUserById(Long.valueOf(userId)));
            //查询企业组织
            SysOrganization org = ResHandler.getResObj(iAuth.getOrgByUserId(Long.valueOf(userId)));
            if(null != user && null != org){
                String orgCode = org.getCode();
                //查询企业用户账户
                BillingUserAcctBean acct = billingUserAcctMapper.queryUserAcctByOrgCode(orgCode);
                if(null != acct) {
                    String accountId = acct.getAccountId();
                    BillingAcctChargingTerm acctChargingParam = new BillingAcctChargingTerm();
                    acctChargingParam.setAccountId(accountId);
                    acctChargingParam.setUserId(userId+"");
                    acctChargingParam.setChargingItemId(lineId);
                    List<BillingAcctChargingTerm> termList = billingUserAcctMapper.queryAcctChargingTermList(acctChargingParam, null);
                    if(null != termList && termList.size()>0) {
                        BillingAcctChargingTerm term = termList.get(0);
                        //通话时长
                        Long duration = Long.valueOf(billSec);
                        //此次通话消费金额
                        BigDecimal consumeAmount = this.getAmount(term, duration);
                        //账户可用金额
                        BigDecimal availableBalance = acct.getAvailableBalance();
                        //消费前账户金额
                        BigDecimal srcAmount = availableBalance;
                        //消费后账户金额
                        BigDecimal toAmount = availableBalance.subtract(consumeAmount);

                        BillingAcctChargingRecord chargingRecord = new BillingAcctChargingRecord();
                        chargingRecord.setChargingId(idWorker.getBusiId(BusiTypeEnum.BILLING_ACCT.getType()));
                        chargingRecord.setAccountId(accountId);
                        chargingRecord.setOperUserId(userId+"");
                        chargingRecord.setOperUserName(user.getUsername());
                        chargingRecord.setOperUserOrgCode(user.getOrgCode());
                        chargingRecord.setOperBeginTime(beginTime);
                        chargingRecord.setOperEndTime(endTime);
                        chargingRecord.setOperDuration(duration);
                        chargingRecord.setOperDurationM(this.getMins(duration));
                        chargingRecord.setOperDurationStr(this.getDurationStr(duration));

                        chargingRecord.setType(ChargingTypeEnum.CONSUME.getType());//消费
                        chargingRecord.setFeeMode(AcctChargingFeeModeEnum.CALL_RESUME.getFeeCode());//通话消费
                        chargingRecord.setUserChargingId(term.getUserChargingId());
                        chargingRecord.setAmount(consumeAmount);
                        chargingRecord.setSrcAmount(srcAmount);
                        chargingRecord.setToAmount(toAmount);
                        chargingRecord.setPhone(phone);
                        chargingRecord.setCreateTime(new Date());
                        chargingRecord.setDelFlag(SysDelEnum.NORMAL.getState());
                        //插入计费记录
                        bool = DaoHandler.getMapperBoolRes(billingUserAcctMapper.addAcctChargingRecord(chargingRecord));
                        if(bool) {
                            //计费扣减企业账户余额
                            bool = DaoHandler.getMapperBoolRes(billingUserAcctMapper.subAcctBalanceAmount(accountId, consumeAmount, new Date()));
                        }
                    }
                }
            }
            return bool;
        }else{
            throw new BaseException(SysDefaultExceptionEnum.NULL_PARAM_EXCEPTION.getErrorCode(),
                    SysDefaultExceptionEnum.NULL_PARAM_EXCEPTION.getErrorMsg());
        }
    }

    /**
     * 计算消费金额
     * @param term
     * @param duration
     * @return
     */
    private BigDecimal getAmount(BillingAcctChargingTerm term, Long duration){
        BigDecimal amount = BigDecimal.ZERO;
        //计费项启用，并且属于扣费类型
        if(term.getStatus() == AcctChargingStatusEnum.START_UP.getStatus()
                && term.getIsDeducted() == AcctChargingDeductedEnum.CHARGING.getStatus()){
            if(term.getUnitPrice() == ChargingUnitPriceTypeEnum.MINUTE.getType()){//按分钟计费
                //按分钟计算，不满一分钟算一分钟
                long mins = this.getMins(duration);
                amount = term.getPrice().multiply(new BigDecimal(mins));
            }
        }
        return amount;
    }

    /**
     * 计算通话时长：分钟
     * @param duration
     * @return
     */
    private long getMins(Long duration){
        return (duration%60 == 0)?duration/60:(duration/60 + 1);
    }

    /**
     * 通话时长描述
     * @param duration
     * @return
     */
    private String getDurationStr(Long duration){
        String str = "";
        if(duration>=3600L){
            long hours = duration/3600;
            long subMins = duration - (hours * 3600);
            long mins = subMins/60;
            long secs = duration - (hours * 3600) - (mins * 60);
            str = (hours>9?hours:("0"+hours)) + ":" + (mins>9?mins:("0"+mins)) + ":" + secs;
        }else if (duration>=60L){
            long mins = duration/60;
            long secs = duration - (mins * 60);
            str = (mins>9?mins:("0"+mins)) + ":" + secs;
        }else{
            str = "00:" + (duration>9?duration:("0"+duration));
        }
        return str;
    }
}