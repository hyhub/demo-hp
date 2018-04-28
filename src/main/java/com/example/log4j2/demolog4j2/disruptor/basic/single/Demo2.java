package com.example.log4j2.demolog4j2.disruptor.basic.single;

import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransaction;
import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransactionHandler;
import com.lmax.disruptor.*;
import org.springframework.util.StopWatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用WorkerPool辅助创建消费者
 */
public class Demo2 {

    public static void main(String[] args) throws InterruptedException {
        StopWatch sw = new StopWatch();
        sw.start();
        int BUFFER_SIZE = 1024;
        int THREAD_NUMBERS = 4;
        EventFactory<TradeTransaction> eventFactory = new EventFactory<TradeTransaction>() {

            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        };

        RingBuffer<TradeTransaction> ringBuffer = RingBuffer.createSingleProducer(eventFactory, BUFFER_SIZE);

        SequenceBarrier sequenceBarrier = ringBuffer.newBarrier();

        ExecutorService service = Executors.newFixedThreadPool(THREAD_NUMBERS);
        WorkHandler<TradeTransaction> workHandlers = new TradeTransactionHandler();
        WorkerPool<TradeTransaction> workerPool = new WorkerPool<>(ringBuffer, sequenceBarrier, new IgnoreExceptionHandler(), workHandlers);
        workerPool.start(service);

        for (int i = 0; i < 8; i++) {
            long seq = ringBuffer.next();
            ringBuffer.get(seq).setPrice(Math.random() * 999);
            ringBuffer.publish(seq);
        }
        Thread.sleep(1000);
        workerPool.halt();
        service.shutdown();
        sw.stop();
        System.out.println("共耗时：" + sw.getTotalTimeMillis());
    }
}
