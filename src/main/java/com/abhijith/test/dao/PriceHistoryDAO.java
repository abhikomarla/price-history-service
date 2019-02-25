package com.abhijith.test.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
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

    public List<PriceHistory> findByDate(final String date) {
        Map<String, AttributeValue> eav = new HashMap<>();
        eav.put(":v1", new AttributeValue().withS(date));

        System.out.println("Trying to find data for the day : " + date);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression("currencyDate = :v1")
                .withExpressionAttributeValues(eav);

        PaginatedScanList<PriceHistory> response = mapper.scan(PriceHistory.class, scanExpression);
        System.out.println("Successfully scanned the table with " + response.size() + " records");
        return response;
    }

    public void save(final List<PriceHistory> histories) {
        System.out.println("Trying to store the data");
        for (PriceHistory history : histories) {
            mapper.save(history);
        }
        System.out.println("Successfully stored the data");
    }

}
