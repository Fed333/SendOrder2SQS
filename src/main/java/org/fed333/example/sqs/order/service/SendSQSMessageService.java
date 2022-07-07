package org.fed333.example.sqs.order.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SendSQSMessageService {

    @Value("${queue.url}")
    private String queueUrl;

    @Value("${queue.group.id}")
    private String groupId;

    public void sendMessage(String message) {
        final AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();

        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message)
                .withMessageGroupId(groupId);
        sqs.sendMessage(send_msg_request);
    }

}
