package com.hunter.mq.consumer;

import javax.jms.Message;

public class Consumer {
    private int num;

    public void consume(Message message) {
        System.out.println(num+"号消费"+message);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
