package com.example.log4j2.demolog4j2.disruptor.spring.service.impl;

import com.example.log4j2.demolog4j2.disruptor.spring.dao.DisruptorDao;
import com.example.log4j2.demolog4j2.disruptor.spring.model.EventData;
import com.example.log4j2.demolog4j2.disruptor.spring.service.DisruptorService;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
@Slf4j
public class DisruptorServiceImpl implements DisruptorService, EventHandler<EventData> {

    @Autowired
    private DisruptorDao disruptorDao;

    private final static int THREAD_NUMBER = Runtime.getRuntime().availableProcessors();

    private final static int BUFF_SIZE = 1024;

    @Override
    public void onEvent(EventData eventData, long l, boolean b) throws Exception {
        log.info("消费者消息入参：eventData->{}, l->{}, b->{}", eventData, l, b);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String s = disruptorDao.s(eventData.getId());
        stopWatch.stop();
        log.info("ID：{}, 共耗时：{} 毫秒", s, stopWatch.getTotalTimeMillis());
        Thread.sleep(800);
    }

    /**
     * 利用disruptor处理业务
     */
    @Override
    public void processDisruptor() throws Exception {
        log.info("调用service，入参：{},开启{}线程处理", "", THREAD_NUMBER);
        ExecutorService executor = Executors.newFixedThreadPool(THREAD_NUMBER);
        Disruptor<EventData> disruptor = new Disruptor<>(EventData.EVENT_FACTORY, BUFF_SIZE, executor);
        disruptor.handleEventsWith(this);
        disruptor.start();
        RingBuffer<EventData> ringBuffer = disruptor.getRingBuffer();
        Future<Void> future = null;
        for (int i = 0; i < 40; i++) {
            future = executor.submit(() -> {
                String id = String.valueOf(System.nanoTime());
                Thread.sleep(1000);//模拟发布者获取消息耗时
                log.info("开始发布：{}", id);
                //给这个区块放入数据
                long seq = ringBuffer.next();
                ringBuffer.get(seq).setId(id);
                ringBuffer.publish(seq);
                log.info("ID：{}，发布结束", id);
                return null;
            });
        }
        future.get();//等待生产者结束
        executor.shutdown();//终止线程
        log.info("调用service，出参：{}");
    }
}
