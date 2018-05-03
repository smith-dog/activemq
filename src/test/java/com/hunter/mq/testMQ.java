package com.hunter.mq;

import com.hunter.mq.entity.MyMessage;
import com.hunter.mq.producer.Producer;
import com.hunter.mq.producer.DefaultProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class testMQ {

    @Qualifier("queueProducer")
    @Autowired
    DefaultProducer queueProducer;
    @Qualifier("topicProducer")
    @Autowired
    DefaultProducer topicProducer;

    @Test
    public void testQueue(){
        produce(queueProducer);
        produce(topicProducer);
        while(true) {}
    }

    private void produce(Producer producer) {
        for(int i=0;i<10;i++){
            MyMessage message = new MyMessage();
            System.out.println("生产第"+i+"条消息！");
            message.setText("第"+i+"条消息！");
            producer.produce(message);
        }
    }


}