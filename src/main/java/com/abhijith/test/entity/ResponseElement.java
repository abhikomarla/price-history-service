package com.abhijith.test.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResponseElement implements Serializable {

    public ResponseElement() {
        this.profit = new BigDecimal(0);
    }

    private String currency;

    private String exchangeDate;

    private String purchaseTime;

    private String sellTime;

    private BigDecimal profit;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(final String exchangeDate) {
        this.exchangeDate = exchangeDate;
    }

    public String getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(final String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public String getSellTime() {
        return sellTime;
    }

    public void setSellTime(final String sellTime) {
        this.sellTime = sellTime;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(final BigDecimal profit) {
        this.profit = profit;
    }
}
