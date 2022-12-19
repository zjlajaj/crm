package com.zjl.bo.statistic;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SaleInfo {
    private String name;
    private BigDecimal count;
    private String unit;
}
