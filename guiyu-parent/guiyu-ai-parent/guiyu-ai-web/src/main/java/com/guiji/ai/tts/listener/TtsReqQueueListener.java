package com.guiji.ai.tts.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.guiji.ai.dao.TtsStatusMapperExt;
import com.guiji.ai.tts.handler.TranslateHandler;
import com.guiji.ai.tts.service.ITtsService;
import com.guiji.utils.RedisUtil;

@Component
public class TtsReqQueueListener implements ApplicationRunner{

	@Autowired
	RedisUtil redisUtil;
	@Autowired
	ITtsService ttsService;
	@Autowired
	TtsStatusMapperExt ttsStatusMapper_ext;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		new Thread(() -> {
            new TranslateHandler().run(ttsService, ttsStatusMapper_ext, redisUtil);
        }).start();
		
	}

}
