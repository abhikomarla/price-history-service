package com.abhijith.test.entity;

import java.math.BigDecimal;

public class ProcessItem {

    private String purchaseTime;

    private String sellTime;

    private BigDecimal profit;

    public ProcessItem(final String purchaseTime, final String sellTime, final BigDecimal profit) {
        this.purchaseTime = purchaseTime;
        this.sellTime = sellTime;
        this.profit = profit;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public String getSellTime() {
        return sellTime;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
