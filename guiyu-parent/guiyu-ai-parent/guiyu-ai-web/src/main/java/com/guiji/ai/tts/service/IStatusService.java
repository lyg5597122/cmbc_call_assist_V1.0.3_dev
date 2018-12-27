package com.guiji.ai.tts.service;

import com.guiji.ai.vo.*;

public interface IStatusService
{

    /**
     * 根据busiId查询TTS处理状态
     * @param busId
     * @return
     * @throws Exception 
     */
    public String getTransferStatusByBusId(String busId);
    
    /**
     * 保存到tts_status表
     * @param ttsReqVO
     * @throws Exception 
     */
	public void saveTtsStatus(TtsReqVO ttsReqVO);

	/**
	 * 根据busId修改任务状态
	 * @param busId
	 * @param status
	 */
	public void updateStatusByBusId(String busId, String status);

	/**
	 * 获取任务列表
	 * @param taskListReqVO
	 * @return
	 */
	public TaskListRspVO getTaskList(TaskListReqVO taskListReqVO);

	/**
	 * 根据busId修改优先处理标志
	 * @param busId
	 */
	public void updateJumpFlagByBusId(String busId);

	/**
	 * 累计任务数
	 * @param taskReqVO
	 */
	public TaskRspVO getTasks(TaskReqVO taskReqVO);

	/**
	 * 待合成任务数（分模型）
	 * @return
	 */
	public TaskRspVO getWaitTasks();

	/**
	 * 失败率，成功率
	 */
	public RatioRspVO getRatio(RatioReqVO ratioReqVO);

	/**
	 * 累计任务数
	 * @param taskReqVO
	 */
	public TaskLineRspVO getTaskLine(TaskReqVO taskReqVO);
 
}
