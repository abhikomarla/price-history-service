package com.abhijith.test.config;

import com.abhijith.test.entity.PriceHistory;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import javax.annotation.PostConstruct;

@Configuration
public class DynamoDBConfig {

    @Value("${amazon.dynamodb.endpoint}")
    private String dynamoDBEndPoint;

    @Value("${amazon.aws.accesskey}")
    private String accessKey;

    @Value("${amazon.aws.secretkey}")
    private String secretKey;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        AmazonDynamoDB amazonDynamoDB = new AmazonDynamoDBClient(new BasicAWSCredentials(accessKey, secretKey));
        if (!StringUtils.isEmpty(amazonDynamoDB)) {
            amazonDynamoDB.setEndpoint(dynamoDBEndPoint);
        }
        return amazonDynamoDB;
    }

    @Bean
    public DynamoDBMapper amazonDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    @PostConstruct
    public void initialize() {
        createPriceHistoryTable();
    }

    private void createPriceHistoryTable() {
        try {
            CreateTableRequest tableRequest = amazonDBMapper().generateCreateTableRequest(PriceHistory.class);

            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            tableRequest.getGlobalSecondaryIndexes().get(0).setProvisionedThroughput(
                    new ProvisionedThroughput(1L, 1L));

            amazonDynamoDB().createTable(tableRequest);
        } catch (ResourceInUseException e) {
            //ignore
        }
    }

}
