package com.abhijith.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.abhijith.test.entity.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceHistoryDAO {

    @Autowired
    private DynamoDBMapper mapper;

    public List<PriceHistory> findByDate(String date) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(date));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("currencyDate = :v1")
                .withExpressionAttributeValues(eav);

        return mapper.scan(PriceHistory.class, scanExpression);
    }

    public void save(List<PriceHistory> histories) {
        for (PriceHistory history : histories) {
            mapper.save(history);
        }
    }

}
