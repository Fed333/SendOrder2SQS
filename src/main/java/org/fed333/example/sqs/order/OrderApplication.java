package org.fed333.example.sqs.order;

import org.fed333.example.sqs.order.service.MessageConsoleSenderService;
import org.fed333.example.sqs.order.service.OrderHandlerService;
import org.fed333.example.sqs.order.service.SendSQSMessageService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(OrderApplication.class, args);

        OrderHandlerService orderHandler = context.getBean(OrderHandlerService.class);

        orderHandler.handleOrders();

        System.out.println("Shut down the server.");
        context.close();
    }


    private static final String jsonMessage = "{\n" +
            "\t\"user\": \"Roman\",\n" +
            "\t\"goodsType\": \"countable\",\n" +
            "\t\"items\": \"2\",\n" +
            "\t\"orderTotal\": \"200\"\n" +
            "}";

}