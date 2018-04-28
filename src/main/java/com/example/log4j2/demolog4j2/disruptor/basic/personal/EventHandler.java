package com.example.log4j2.demolog4j2.disruptor.basic.personal;

public class EventHandler implements com.lmax.disruptor.EventHandler<EventData> {
    @Override
    public void onEvent(EventData eventData, long l, boolean b) throws Exception {
        System.out.println("消费者ID：" + eventData.getId());
        Thread.sleep(800);
    }
}
