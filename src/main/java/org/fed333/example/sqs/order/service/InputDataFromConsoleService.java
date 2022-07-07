package org.fed333.example.sqs.order.service;

import org.fed333.example.sqs.order.model.GoodsType;
import org.fed333.example.sqs.order.model.OrderModel;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class InputDataFromConsoleService {

    public OrderModel readOrder() {
        Scanner in = new Scanner(System.in);
        OrderModel model = new OrderModel();

        System.out.println("Make the order.");

        System.out.print("user: ");
        String user = in.nextLine();
        model.setUser(user);

        System.out.print("goodsType [LIQUIDS/COUNTABLE]: ");
        String goods = in.nextLine();
        GoodsType goodsType = GoodsType.valueOf(goods.toUpperCase());
        model.setGoodsType(goodsType);

        if (goodsType == GoodsType.COUNTABLE) {
            System.out.print("items: ");
            Integer items = Integer.parseInt(in.nextLine());
            model.setItems(items);
        } else if (goodsType == GoodsType.LIQUIDS) {
            System.out.print("volume: ");
            Double volume = Double.parseDouble(in.nextLine());
            model.setVolume(volume);
        }

        System.out.print("orderTotal: ");
        Integer orderTotal = Integer.parseInt(in.nextLine());
        model.setOrderTotal(orderTotal);

        return model;
    }
}