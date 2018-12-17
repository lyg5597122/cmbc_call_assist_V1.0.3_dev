package com.guiji.callcenter.dao;

import com.guiji.callcenter.dao.entity.CallOutPlan;
import com.guiji.callcenter.dao.entity.CallOutPlanExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CallOutPlanMapper {
    int countByExample(CallOutPlanExample example);

    int deleteByExample(CallOutPlanExample example);

    int deleteByPrimaryKey(String callId);

    int insert(CallOutPlan record);

    int insertSelective(CallOutPlan record);

    List<CallOutPlan> selectByExample(CallOutPlanExample example);
    List<CallOutPlan> selectByExample4Encrypt(CallOutPlanExample example);

    CallOutPlan selectByPrimaryKey(String callId);

    int updateByExampleSelective(@Param("record") CallOutPlan record, @Param("example") CallOutPlanExample example);

    int updateByExample(@Param("record") CallOutPlan record, @Param("example") CallOutPlanExample example);

    int updateByPrimaryKeySelective(CallOutPlan record);

    int updateByPrimaryKey(CallOutPlan record);
}