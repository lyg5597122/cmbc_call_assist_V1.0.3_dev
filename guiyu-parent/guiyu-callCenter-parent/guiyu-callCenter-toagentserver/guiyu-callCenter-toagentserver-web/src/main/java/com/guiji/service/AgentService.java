package com.guiji.service;

import com.guiji.callcenter.dao.entity.Agent;
import com.guiji.web.request.AgentRequest;
import com.guiji.web.request.CrmUserVO;
import com.guiji.web.response.Paging;
import com.guiji.web.response.QueryAgent;
import com.guiji.web.response.QueryCalls;
import com.guiji.web.response.QueryUser;

import java.util.List;

/**
 * @Auther: 魏驰
 * @Date: 2018/12/17 14:32
 * @Project：ccserver
 * @Description:
 */
public interface AgentService {
    boolean createAgent(AgentRequest request,Agent create,Long crmUserid);

    boolean updateAgent(String userId, AgentRequest request, Agent agent);

    boolean deleteAgent(String userId);

    boolean agentState(AgentRequest request, Agent agent);

    QueryAgent getAgent(String userId);

    Paging getAllAgent(Agent agent,String crmLoginId,String queueId, Integer page, Integer size);

    QueryCalls agentcalls(String userId);

    QueryUser getUser(Agent agent);

    void update(Agent agent);

    boolean isAgentLogin(Agent agent);

    /**
     * 座席是否处于通话状态
     * @param agentId
     * @return
     */
    boolean isAgentBusy(String agentId);

    /**
     * 向座席发送消息，让其退出verto登录
     * @param agent
     */
    void alertToLogout(Agent agent);

    List<Agent> findByOrgCode(String orgCode);

    void initCallcenter();

    Agent initUser(CrmUserVO crmUserVO);

}
