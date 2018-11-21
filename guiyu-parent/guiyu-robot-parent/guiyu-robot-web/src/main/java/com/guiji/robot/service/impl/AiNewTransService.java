package com.guiji.robot.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.guiji.robot.dao.TtsWavHisMapper;
import com.guiji.robot.dao.entity.TtsWavHis;
import com.guiji.utils.StrUtils;

/** 
* @ClassName: AiNewTransService 
* @Description: 独立事务服务类，在一个service中独立另一个事务同个service没有效果的，此处单拉一个service用来处理这个问题
* @date 2018年11月21日 下午4:51:17 
* @version V1.0  
*/
@Service
public class AiNewTransService {
	@Autowired
	TtsWavHisMapper ttsWavHisMapper;
	
	/**
	 * 保存或者更新一TTS合成信息
	 * 独立事物
	 * @param ttsWavHis
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public TtsWavHis recordTtsWav(TtsWavHis ttsWavHis) {
		if(ttsWavHis != null) {
			if(StrUtils.isEmpty(ttsWavHis.getId())) {
				//如果主键为空，那么新增一条信息
				ttsWavHis.setCrtTime(new Date());
				ttsWavHisMapper.insert(ttsWavHis);
			}else {
				//主键不为空，更新信息
				ttsWavHisMapper.updateByPrimaryKey(ttsWavHis);
			}
		}
		return ttsWavHis;
	}
	
}
