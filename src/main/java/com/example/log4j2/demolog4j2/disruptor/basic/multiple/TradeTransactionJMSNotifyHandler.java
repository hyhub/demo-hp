package com.example.log4j2.demolog4j2.disruptor.basic.multiple;

import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransaction;
import com.lmax.disruptor.EventHandler;

public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction tradeTransaction, long l, boolean b) throws Exception {
        System.out.println("TradeTransactionJMSNotifyHandler");
    }
}
