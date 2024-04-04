package com.restaurant.entity;

import com.restaurant.enums.Category;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private static Restaurant instance;

    public Menu menu;
    private final List<Table> tables;
    private final Kitchen kitchen;

    private Restaurant() {
        menu = new Menu();
        tables = new ArrayList<>();
        kitchen = new Kitchen();
        addInitialMenuItems();
        initializeTables();
    }

    public static Restaurant initRestaurant() {
        if (instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }

    private void initializeTables() {
        // Initialize some tables
        for (int
             i = 1; i <= 10; i++) {
            addTable(new Table(i));
        }
    }

    public void addTable(Table table) {
        tables.add(table);
    }

    public void seatCustomer(int tableNumber) {
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setOccupied(true);
        }else{
            System.out.println("Invalid table no" +tableNumber);
        }
    }

    public void markTableVacant(int tableNumber) {
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setOccupied(false);
            table.setOccupied(false);
        }
    }

    public void makeReservation(int tableNumber) {
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setReserved(true);
            table.setOccupied(true);
        }
    }

    private Table getTable(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table;
            }
        }
        return null;
    }

    public void addMenuItem(MenuItem item) {
        menu.addItem(item);
        displayMenu();
    }

    public void removeMenuItem(MenuItem item) {
        menu.removeItem(item);
        displayMenu();
    }

    public void displayMenu() {
        for (MenuItem item : menu.getMenuItem()) {
            System.out.println(item);
        }
    }

    public void placeOrder(Order order) {
        int tableNumber = order.getTableNumber();
        Table table = getTable(tableNumber);
        if (table != null) {
            table.setOrder(order);
            System.out.println("Order placed for Table " + tableNumber + ". Total Amount: $" + order.calculateTotal());
            makeReservation(tableNumber);
            kitchen.notifyWaitStaffs(order);
        } else {
            System.out.println("Invalid table number.");
        }
    }


    public void displayTables() {
        for (Table table : tables) {
            String status = table.isOccupied() ? "Occupied" : "Vacant";
            System.out.println("Table " + table.getTableNumber() + " - " + status);
        }
    }

    public void addInitialMenuItems() {
        addMenuItem(new MenuItem("1","Spaghetti Carbonara", "Pasta with bacon, eggs, and cheese", 12.99, Category.MAIN_COURSE));
        addMenuItem(new MenuItem("2","Margherita Pizza", "Classic Italian pizza with tomato, mozzarella, and basil", 10.99, Category.MAIN_COURSE));
        addMenuItem(new MenuItem("3","Caesar Salad", "Romaine lettuce, croutons, parmesan cheese, and Caesar dressing", 8.99, Category.APPETIZER));
    }
    public Order getOrderByTableNumber(int tableNumber) {
        for (Table table : tables) {
            if (table.getTableNumber() == tableNumber) {
                return table.getOrder();
            }
        }
        return null;
    }
}
