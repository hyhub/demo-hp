package com.example.log4j2.demolog4j2.disruptor.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TradeTransaction {
    private String id;
    private double price;
}
