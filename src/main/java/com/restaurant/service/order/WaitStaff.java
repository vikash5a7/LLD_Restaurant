package com.restaurant.service.order;

import com.restaurant.entity.Order;

public class WaitStaff implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("order is ready for the table "+ order.getTableNumber());
    }
}
