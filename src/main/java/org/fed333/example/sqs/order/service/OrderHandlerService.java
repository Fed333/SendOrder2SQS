package org.fed333.example.sqs.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class OrderHandlerService {

    @Autowired
    private MessageConsoleSenderService consoleSender;

    public void handleOrders() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to the shop!");
        boolean run;
        do {
            System.out.print("Do you want to make the order? [Y/n]: ");
            run = parseAgreementAnswer(in.nextLine());
            if (run) {
                consoleSender.sendMessageFromConsole();
            }
        } while (run);
        System.out.println("Thanks for visiting us! Come back :)");
    }

    private boolean parseAgreementAnswer(String answer) {
        return answer.equalsIgnoreCase("Y");
    }

}
