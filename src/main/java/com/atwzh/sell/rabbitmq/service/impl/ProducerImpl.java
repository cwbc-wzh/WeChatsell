package com.atwzh.sell.rabbitmq.service.impl;

import com.atwzh.sell.rabbitmq.entity.Mail;
import com.atwzh.sell.rabbitmq.service.Producer;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("producer")
public class ProducerImpl implements Producer {

	@Autowired
	RabbitTemplate rabbitTemplate;


	public void sendMail(String queue, Mail mail) {
		rabbitTemplate.setDefaultReceiveQueue(queue);
		rabbitTemplate.convertAndSend(queue,mail);
	}

}
