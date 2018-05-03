package com.hunter.mq.producer;

import com.hunter.mq.entity.MyMessage;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;

public interface Producer {
    public void produce(MyMessage message);
}
