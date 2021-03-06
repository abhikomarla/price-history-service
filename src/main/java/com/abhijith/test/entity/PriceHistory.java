package com.abhijith.test.entity;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.math.BigDecimal;

@DynamoDBTable(tableName = "PriceHistory")
public class PriceHistory {

    private static final String DATE_INDEX = "Date-Index";

    public PriceHistory() { }

    public PriceHistory(final String currency, final String currencyDate,
                        final String time, final BigDecimal price) {
        this.currency = currency;
        this.currencyDate = currencyDate;
        this.time = time;
        this.price = price;
    }

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;

    @DynamoDBAttribute
    private String currency;

    @DynamoDBIndexHashKey(globalSecondaryIndexName = DATE_INDEX)
    private String currencyDate;

    @DynamoDBAttribute
    private String time;

    @DynamoDBAttribute
    private BigDecimal price;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    public String getCurrencyDate() {
        return currencyDate;
    }

    public void setCurrencyDate(final String currencyDate) {
        this.currencyDate = currencyDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }
}
