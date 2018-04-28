package com.example.log4j2.demolog4j2.disruptor.basic.personal;

import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;

public class EventProducer {

    private final RingBuffer<EventData> ringBuffer;

    public EventProducer(RingBuffer ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(ByteBuffer byteBuffer) {
        long sequence = ringBuffer.next();
        try {
            EventData eventData = ringBuffer.get(sequence);
            String id = String.valueOf(System.nanoTime());
            System.out.println("生产者生成id：" + id);
            eventData.setId(id);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
