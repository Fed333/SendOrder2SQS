package org.fed333.example.sqs.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsoleSenderService {

    @Autowired
    private SendSQSMessageService sqsMessageService;

    @Autowired
    private OrderModel2JsonConverterService jsonConverterService;

    @Autowired
    private InputDataFromConsoleService inputDataFromConsoleService;

    public void sendMessageFromConsole() {
        String jsonMessage = jsonConverterService.toJsonString(inputDataFromConsoleService.readOrder());
        System.out.println("Send JSON message to SQS Orders queue.");
        sqsMessageService.sendMessage(jsonMessage);
        System.out.println("Message: \n" + jsonMessage + "\n has been successfully sent.");
    }

}
