package com.guiji.process.server.service;

import com.guiji.process.core.vo.ProcessInstanceVO;
import com.guiji.process.core.vo.ProcessReleaseVO;

import java.util.List;

public interface IProceseScheduleService {

    /**
     * 获取可用的TTS
     * @param model 模型名称
     * @param requestCount 请求数量
     * @return
     */
    List<ProcessInstanceVO> getTTS(String model, int requestCount);

    /**
     * 获取所有可用的TTS
     * @return
     */
    List<ProcessInstanceVO> getTTS();


    /**
     * 获取可用的Sellbot
     * @param requestCount 请求数量
     * @return
     */
    List<ProcessInstanceVO> getSellbot(int requestCount);


    /**
     * 释放资源
     * @param processReleaseVO 释放资源列表
     * @return
     */
    boolean release(ProcessReleaseVO processReleaseVO);


    /**
     * 释放资源
     * @param deviceVOS 释放资源列表
     * @return
     */
    boolean releaseTts(String model, List<ProcessInstanceVO> deviceVOS);

    /**
     * 重新创建模型
     * @param srcModel
     * @param toModel
     * @param deviceVO
     * @return
     */
    void restoreTtsModel(String srcModel, String toModel, ProcessInstanceVO deviceVO);
}
