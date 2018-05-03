package com.hunter.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

public class ActiveMQConsumer {

    @Test
    public void testConsumer() {
        //连接工厂

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://172.16.10.30:61620");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            //注意这里与生产者不一样，需要打开连接
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列或者话题
            Queue queue = session.createQueue("Helloqqq");
            //创建生产者或者消费者
            MessageConsumer consumer = session.createConsumer(queue);
            //发送消息
            while (true) {
                TextMessage message = (TextMessage) consumer.receive(1000000000);
                if (message != null) {
                    System.out.println(message.getText());
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testConsumerWithListener() {
        //连接工厂
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
                ActiveMQConnection.DEFAULT_USER,
                ActiveMQConnection.DEFAULT_PASSWORD,
                "tcp://172.16.10.24:61616");
        Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
            //注意这里与生产者不一样，需要打开连接
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建队列或者话题
            Queue queue = session.createQueue("HelloActiveMQ");
            //创建生产者或者消费者
            MessageConsumer consumer = session.createConsumer(queue);
            //发送消息
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    TextMessage textMessage = (TextMessage) message;
                    try {
                        System.out.println(textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });
            while(true) {

            }

        } catch (JMSException e) {
            e.printStackTrace();
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
