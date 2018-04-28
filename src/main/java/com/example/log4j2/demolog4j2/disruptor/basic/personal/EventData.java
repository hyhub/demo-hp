package com.example.log4j2.demolog4j2.disruptor.basic.personal;

import com.lmax.disruptor.EventFactory;
import lombok.Data;

@Data
public class EventData {
    private String id;

    public final static EventFactory<EventData> EVENT_FACTORY = () -> {
        return new EventData();
    };
}
