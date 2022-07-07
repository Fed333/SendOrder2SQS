package org.fed333.example.sqs.order.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private String user;

    private GoodsType goodsType;

    private Double volume;

    private Integer items;

    private Integer orderTotal;
}
