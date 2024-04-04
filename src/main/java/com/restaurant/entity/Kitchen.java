package com.restaurant.entity;


import com.restaurant.service.order.OrderObserver;

import java.util.ArrayList;
import java.util.List;


class Kitchen {
    private List<OrderObserver> waitStaffs;
    public Kitchen() {
        waitStaffs = new ArrayList<>();
    }
    public void addObserver(OrderObserver observer) {
        waitStaffs.add(observer);
    }
    public void notifyWaitStaffs(Order order) {
        for (OrderObserver observer : waitStaffs) {
            observer.update(order);
        }
    }
}
