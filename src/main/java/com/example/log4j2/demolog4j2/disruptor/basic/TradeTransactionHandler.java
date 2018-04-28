package com.example.log4j2.demolog4j2.disruptor.basic;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class TradeTransactionHandler implements EventHandler<TradeTransaction>, WorkHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long l, boolean b) throws Exception {
        this.onEvent(event);
    }

    @Override
    public void onEvent(TradeTransaction event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        log.info("商品价格:" + event.getPrice());
    }
}
