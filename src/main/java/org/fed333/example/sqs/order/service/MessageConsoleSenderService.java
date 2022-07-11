package org.fed333.example.sqs.order.service;

import org.fed333.example.sqs.order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageConsoleSenderService {

    @Autowired
    private PublishSNSMessageService snsMessageService;

    @Autowired
    private OrderModel2JsonConverterService jsonConverterService;

    @Autowired
    private InputDataFromConsoleService inputDataFromConsoleService;

    public void sendMessageFromConsole() {
        OrderModel orderModel = inputDataFromConsoleService.readOrder();
        String jsonMessage = jsonConverterService.toJsonString(orderModel);
        System.out.println("Send JSON message to SQS Orders queue.");
        snsMessageService.publishMessage(jsonMessage, orderModel.getGoodsType());
        System.out.println("Message: \n" + jsonMessage + "\n has been successfully sent.");
    }

}
