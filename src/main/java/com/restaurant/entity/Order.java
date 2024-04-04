package com.restaurant.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
    private final List<MenuItem> items;
    private final Map<MenuItem, Integer> quantities;
    private final int tableNumber;

    public Order(int tableNumber) {
        this.tableNumber = tableNumber;
        items = new ArrayList<>();
        quantities = new HashMap<>();
    }

    public void addItem(MenuItem item, int quantity) {
        items.add(item);
        quantities.put(item, quantity);
    }

    public void removeItem(MenuItem item) {
        items.remove(item);
        quantities.remove(item);
    }

    public void updateQuantity(MenuItem item, int quantity) {
        quantities.put(item, quantity);
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<MenuItem, Integer> entry : quantities.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public double calculateTotalWithTax() {
        double total = calculateTotal();
        return total * 1.10;
    }
}
