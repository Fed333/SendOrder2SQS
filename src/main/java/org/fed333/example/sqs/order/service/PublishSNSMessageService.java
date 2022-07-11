package org.fed333.example.sqs.order.service;


import org.fed333.example.sqs.order.model.GoodsType;
import org.springframework.beans.factory.annotation.Value;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.MessageAttributeValue;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;
import software.amazon.awssdk.services.sns.model.SnsException;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PublishSNSMessageService {

    final SnsClient client = SnsClient.builder()
            .region(Region.EU_CENTRAL_1)
            .credentialsProvider(ProfileCredentialsProvider.create())
            .build();

    @Value("${queue.group.id}")
    private String groupId;

    @Value("${sns.orders.topic.arn}")
    private String topicArn;

    public void publishMessage(String message, GoodsType goodsType) {

        try {
            Map<String, MessageAttributeValue> messageAttributes = new HashMap<>();
            PublishRequest request = PublishRequest.builder()
                    .message(message)
                    .messageAttributes(setGoodsType(messageAttributes, goodsType))
                    .messageGroupId(groupId)
                    .topicArn(topicArn)
                    .build();

            PublishResponse result = client.publish(request);
            System.out.println(result.messageId() + " Message sent. Status is " + result.sdkHttpResponse().statusCode());

        } catch (SnsException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
            System.exit(1);
        }
    }

    private Map<String, MessageAttributeValue> setGoodsType(Map<String, MessageAttributeValue> messageAttributes, GoodsType goodsType) {
        messageAttributes.put("goodsType", MessageAttributeValue.builder()
                .dataType("String.Array")
                .stringValue(String.format("[\"%s\"]", goodsType))
                .build());
        return messageAttributes;
    }

}
