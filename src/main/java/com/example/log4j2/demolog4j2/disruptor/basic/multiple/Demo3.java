package com.example.log4j2.demolog4j2.disruptor.basic.multiple;

import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransaction;
import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransactionHandler;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;
import org.springframework.util.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int bufferSize = 1024;
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Disruptor<TradeTransaction> disruptor = new Disruptor<>(new EventFactory<TradeTransaction>() {
            @Override
            public TradeTransaction newInstance() {
                return new TradeTransaction();
            }
        }, bufferSize, executor, ProducerType.SINGLE, new BusySpinWaitStrategy());

        EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWith(new TradeTransactionHandler(), new TradeTransactionVasConsumer());
        TradeTransactionJMSNotifyHandler jmsNotifyHandler = new TradeTransactionJMSNotifyHandler();
        handlerGroup.then(jmsNotifyHandler);

        disruptor.start();

        CountDownLatch latch = new CountDownLatch(1);

        executor.submit(new TradeTransactionPulisher(latch, disruptor));
        latch.await();//等待生产者完事.
        disruptor.shutdown();
        executor.shutdown();

        System.out.println(stopWatch.getTotalTimeMillis());

    }
}
