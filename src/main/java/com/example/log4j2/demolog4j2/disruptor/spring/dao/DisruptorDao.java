package com.example.log4j2.demolog4j2.disruptor.spring.dao;

import com.oracle.tools.packager.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Slf4j
public class DisruptorDao {

    public String s(String s) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Log.info("调用dao");
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            log.error("睡死了.......");
        }
        stopWatch.stop();
        log.info("dao调用结束,共耗时：{} 毫秒", stopWatch.getTotalTimeMillis());
        return s;
    }
}
