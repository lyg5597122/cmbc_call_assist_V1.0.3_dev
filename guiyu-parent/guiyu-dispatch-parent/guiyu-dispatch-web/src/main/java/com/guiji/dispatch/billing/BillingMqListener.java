package com.guiji.dispatch.billing;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guiji.dispatch.service.IPhonePlanQueueService;
import com.guiji.utils.JsonUtils;
import com.guiji.utils.RedisUtil;
import com.rabbitmq.client.Channel;

@Component
@RabbitListener(queues = "fanoutPushBilling.ARREARAGE")
public class BillingMqListener {

	static Logger logger = LoggerFactory.getLogger(BillingMqListener.class);

	@Autowired
	private RedisUtil redisUtils;
	
	@Autowired
	IPhonePlanQueueService phonePlanQueueService;

	@RabbitHandler
	public void process(String message, Channel channel, Message message2) {
		logger.info("BillingMqListener>>>>>>>>>>>>>>>>>>>"+message);
		if(message.equals("") || message ==null){
			logger.info("当前billing消费数据有问题");
			return;
		}
		ArrearageNotifyDto msgDto = JsonUtils.json2Bean(message, ArrearageNotifyDto.class);
		if (msgDto.getIsArrearage().equals(0)) {
			List<String> userIdList = (List<String>) redisUtils.get("USER_BILLING_DATA");
			if(userIdList ==null){
				userIdList = new ArrayList<>();
			}	
			if (userIdList.size() != 0) {
				for (int i = 0; i < msgDto.getUserIdList().size(); i++) {
					if (userIdList.contains(msgDto.getUserIdList().get(i))) {
						userIdList.remove(i);
					}
				}
			}
			redisUtils.set("USER_BILLING_DATA", userIdList);

		} else if (msgDto.getIsArrearage().equals(-1)) {
			// 如果是欠费判断当前redis里面是否存在 不存在的话添加
			List<String> userIdList = (List<String>) redisUtils.get("USER_BILLING_DATA");
			if(userIdList ==null){
				userIdList = new ArrayList<>();
			}
			for (String str : msgDto.getUserIdList()) {
				if (!userIdList.contains(str)) {
					userIdList.add(str);
					//如果用户欠费了 那么就回退当前用户的队列数据
					phonePlanQueueService.cleanQueueByUserId(str);
				}
			}
			redisUtils.set("USER_BILLING_DATA", userIdList);
		}
	}
}
