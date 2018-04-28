package com.example.log4j2.demolog4j2.disruptor.spring.controller;

import com.example.log4j2.demolog4j2.disruptor.spring.service.DisruptorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class DisruptorController {

    @Autowired
    private DisruptorService disruptorService;

    @RequestMapping(value = "disruptor")
    public String process() throws Exception {
        log.info("调用 controller process 方法，入参：{}");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        disruptorService.processDisruptor();
        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        log.info("constroller方法处理共耗时：{} 毫秒", totalTimeMillis);
        return "该服务共耗时：" + totalTimeMillis + " 毫秒";
    }
}
