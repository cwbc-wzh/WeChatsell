package com.atwzh.sell.rabbitmq.service;


import com.atwzh.sell.rabbitmq.entity.Mail;

public interface Producer {
	public void sendMail(String queue, Mail mail);//向队列queue发送消息
}
