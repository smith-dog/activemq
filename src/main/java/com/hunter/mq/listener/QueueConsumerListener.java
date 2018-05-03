package com.hunter.mq.listener;

import com.hunter.utils.MessageConversionUtil;
import javax.jms.Message;
import javax.jms.MessageListener;

public class QueueConsumerListener implements MessageListener {
    @Override
    public void onMessage(Message message) {
        String messageText = MessageConversionUtil.formatMessage(message);
        System.out.println("消费的QueueConsumer获取消息:" + messageText);
    }
}
