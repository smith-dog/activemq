package com.hunter.utils;

import com.hunter.mq.entity.MyMessage;
import org.apache.activemq.command.ActiveMQBytesMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public class MessageConversionUtil {

    public static String formatMessage(Message message){
        String text = null;
        if(message instanceof ActiveMQBytesMessage){

            byte[] data = ((ActiveMQBytesMessage)message).getContent().getData();
            text = new String(data);
        }

        if(message instanceof TextMessage){
            TextMessage msg = (TextMessage)message;
            try {
                text = msg.getText();
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }

        if(message instanceof ObjectMessage){
            ObjectMessage objectMessage = (ObjectMessage)message;
            MyMessage myMessage = null;
            try {
                //有多种自定义的消息可以再次进行类型判断
                myMessage = (MyMessage) objectMessage.getObject();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            if(myMessage != null) {
                text = new String(myMessage.getText());
            }
        }
        return text;
    }
}
