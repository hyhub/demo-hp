package com.example.log4j2.demolog4j2.job;

import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Log4j2
public class Job {
    /**
     * 1秒钟执行1次
     */
//    @Scheduled(fixedRate = 1 * 1000)
    public void logging() {
        log.debug("-------DEBUG---------");
        Date now = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        log.info(simpleDateFormat.format(now));
        log.error(now.getTime());
    }
}


