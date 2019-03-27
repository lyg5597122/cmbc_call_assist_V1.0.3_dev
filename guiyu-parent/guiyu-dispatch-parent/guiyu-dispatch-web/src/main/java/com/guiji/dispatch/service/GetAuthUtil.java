package com.guiji.dispatch.service;

import com.guiji.dispatch.constant.AuthConstant;
import com.guiji.dispatch.enums.AuthLevelEnum;
import com.guiji.user.dao.entity.SysOrganization;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetAuthUtil {

    @Autowired
    private GetApiService getApiService;

    public String getUserIdByAuthLevel(Integer authLevel, String userId){
        if(null != authLevel && AuthLevelEnum.USER.getLevel() == authLevel){
            return null != userId ? userId : AuthConstant.superUserId;
        }else{
            return null;
        }
    }

    public String getOrgCodeByAuthLevel(Integer authLevel, String userId, String orgCode){
        if(null != authLevel && (AuthLevelEnum.ORG.getLevel() == authLevel || AuthLevelEnum.ORG_EXT.getLevel() == authLevel)){
            if(StringUtils.isEmpty(orgCode)){
                //获取用户ID
                userId = null != userId ? userId : AuthConstant.superUserId;
                //获取企业组织
                SysOrganization org = getApiService.getOrgByUserId(userId);
                return null != org ? org.getCode() : AuthConstant.superOrgCode;
            }else{
                return orgCode;
            }
        }else{
            return null;
        }
    }

}
