package com.atwzh.sell.rabbitmq.controller;

import com.atwzh.sell.rabbitmq.entity.Mail;


import com.atwzh.sell.rabbitmq.entity.TopicMail;
import com.atwzh.sell.rabbitmq.service.impl.ProducerImpl;
import com.atwzh.sell.rabbitmq.service.impl.PublisherImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class RabbitMQController {

    final
    ProducerImpl producer;

    final
    PublisherImpl publisher;

	public RabbitMQController(ProducerImpl producer, PublisherImpl publisher) {
		this.producer = producer;
		this.publisher = publisher;
	}

	@RequestMapping(value = "/produce", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void produce(@ModelAttribute("mail") Mail mail) throws Exception {
        producer.sendMail("myqueue", mail);
    }

    @RequestMapping(value = "/topic", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void topic(@ModelAttribute("mail") Mail mail) throws Exception {
        publisher.publishMail(mail);
    }

    @RequestMapping(value = "/direct", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void direct(@ModelAttribute("mail") TopicMail mail) {
        Mail m = new Mail(mail.getMailId(), mail.getCountry(), mail.getWeight());
        publisher.senddirectMail(m, mail.getRoutingkey());
    }

    @RequestMapping(value = "/mytopic", produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public void topic(@ModelAttribute("mail") TopicMail mail) {
        Mail m = new Mail(mail.getMailId(), mail.getCountry(), mail.getWeight());
        publisher.sendtopicMail(m, mail.getRoutingkey());
    }


    @RequestMapping("/")
    public String demo() {
        return "demo";
    }


}
