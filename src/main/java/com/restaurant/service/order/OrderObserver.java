package com.restaurant.service.order;

import com.restaurant.entity.Order;

public interface OrderObserver {
    void update(Order order);
}
