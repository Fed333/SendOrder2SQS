package org.fed333.example.sqs.order.service;

import org.fed333.example.sqs.order.model.GoodsType;
import org.fed333.example.sqs.order.model.OrderModel;
import org.springframework.stereotype.Service;

@Service
public class OrderModel2JsonConverterService {

    public String toJsonString(OrderModel model) {
        String json = "{";
        json += String.format("\n\t\"user\": \"%s\",", model.getUser());
        json += String.format("\n\t\"goodsType\": \"%s\",", model.getGoodsType());
        if (model.getGoodsType() == GoodsType.LIQUIDS) {
            json += String.format("\n\t\"volume\": %.2f,", model.getVolume());
        } else if (model.getGoodsType() == GoodsType.COUNTABLE) {
            json += String.format("\n\t\"items\": %d,", model.getItems());
        }
        json += String.format("\n\t\"orderTotal\": %d", model.getOrderTotal());
        json += "\n}";
        return json;
    }
}