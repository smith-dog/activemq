package com.hunter.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQProducer {
    @Test
    public void testProducer() {
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://172.16.10.24:61616");
        Connection connection;
        try {
            connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建队列或者话题
            Topic topic = session.createTopic("HelloActiveMQ-topic");
            //创建生产者或者消费者
            MessageProducer producer = session.createProducer(topic);
            //发送消息
            for (int i = 0; i < 10; i++) {
                producer.send(session.createTextMessage("activeMQ,你好!" + i));
            }
            //提交操作
            session.commit();
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }


}
