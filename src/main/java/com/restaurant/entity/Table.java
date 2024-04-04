package com.restaurant.entity;


class Table {
    private final int tableNumber;
    private boolean occupied;
    private boolean reserved;
    private int cap;
    private Order order;

    public Table(int tableNumber) {
        this.tableNumber = tableNumber;
        this.occupied = false;
        this.reserved = false;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }


}