package com.example.log4j2.demolog4j2.disruptor.basic.multiple;

import com.example.log4j2.demolog4j2.disruptor.basic.TradeTransaction;
import com.lmax.disruptor.EventTranslator;

import java.util.Random;

public class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {

    private Random random = new Random();

    @Override
    public void translateTo(TradeTransaction tradeTransaction, long l) {
        this.generateTradeTransaction(tradeTransaction);
    }

    private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
        trade.setPrice(random.nextDouble() * 9999);
        return trade;
    }
}
