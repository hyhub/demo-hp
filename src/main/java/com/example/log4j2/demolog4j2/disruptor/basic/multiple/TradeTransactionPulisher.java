package com.example.log4j2.demolog4j2.disruptor.basic.multiple;

import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransaction;
import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

public class TradeTransactionPulisher implements Runnable {

    Disruptor<TradeTransaction> disruptor;

    private CountDownLatch countDownLatch;

    private int LOOP_COUNT = 10000000;

    public TradeTransactionPulisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
        this.disruptor = disruptor;
        this.countDownLatch = latch;
    }

    @Override
    public void run() {
        TradeTransactionEventTranslator translator = new TradeTransactionEventTranslator();
        for (int i = 0; i < LOOP_COUNT; i++) {
            disruptor.publishEvent(translator);
        }
        countDownLatch.countDown();
    }
}
