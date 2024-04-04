package com.restaurant;

import com.restaurant.entity.MenuItem;
import com.restaurant.entity.Order;
import com.restaurant.entity.Restaurant;
import com.restaurant.enums.Category;
import com.restaurant.service.payment.*;

import java.util.Scanner;

public class Main {
    private static Restaurant restaurant;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        restaurant = Restaurant.initRestaurant();
        while (true) {
            printTask();
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addMenuItem(scanner);
                    break;
                case 2:
                    displayMenu();
                    break;
                case 3:
                    seatCustomer(scanner);
                    break;
                case 4:
                    markTableVacant(scanner);
                    break;
                case 5:
                    makeReservation(scanner);
                    break;
                case 6:
                    displayTables();
                    break;
                case 7:
                    placeOrder(scanner);
                    break;
                case 8:
                    displayOrder(scanner);
                    break;
                case 9:
                    updateMenuItemPrice(scanner);
                    break;
                case 10:
                    removeMenuItem(scanner);
                    break;
                case 11:
                    displayUpdatedMenu();
                    break;
                case 12:
                    scanner.close();
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    private static void printTask() {
        System.out.println("\n1. Add Menu Item");
        System.out.println("2. Display Menu");
        System.out.println("3. Seat Customer");
        System.out.println("4. Mark Table Vacant");
        System.out.println("5. Make Reservation");
        System.out.println("6. Display Tables");
        System.out.println("7. Place Order");
        System.out.println("8. Display Order");
        System.out.println("9. Update Menu Item Price");
        System.out.println("10. Remove Menu Item");
        System.out.println("11. Display Updated Menu");
        System.out.println("12. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addMenuItem(Scanner scanner) {
        System.out.print("Enter item id: ");
        String id = scanner.nextLine();
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter item description: ");
        String description = scanner.nextLine();
        System.out.print("Enter item price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter item category (APPETIZER, MAIN_COURSE, DESSERT): ");
        Category category = Category.valueOf(scanner.next());
        restaurant.addMenuItem(new MenuItem(id, name, description, price, category));
    }

    private static void displayMenu() {
        System.out.println("Menu:");
        restaurant.displayMenu();
    }

    private static void seatCustomer(Scanner scanner) {
        System.out.print("Enter table number to seat customer: ");
        int tableToSeat = scanner.nextInt();
        restaurant.seatCustomer(tableToSeat);
    }

    private static void markTableVacant(Scanner scanner) {
        System.out.print("Enter table number to mark vacant: ");
        int vacantTable = scanner.nextInt();
        restaurant.markTableVacant(vacantTable);
    }

    private static void makeReservation(Scanner scanner) {
        System.out.print("Enter table number to make reservation: ");
        int reservedTable = scanner.nextInt();
        restaurant.makeReservation(reservedTable);
    }

    private static void displayTables() {
        System.out.println("Table Status:");
        restaurant.displayTables();
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter table number for order: ");
        int tableNumber = scanner.nextInt();
        Order order = new Order(tableNumber);
        System.out.println("Menu:");
        restaurant.displayMenu();
        while (true) {
            System.out.print("Enter item id to add to order (type 'done' to finish): ");
            String itemName = scanner.next();
            if (itemName.equalsIgnoreCase("done")) {
                break;
            }
            MenuItem item = restaurant.menu.getMenuItem().stream()
                    .filter(i -> i.getId().equalsIgnoreCase(itemName))
                    .findFirst().orElse(null);
            if (item == null) {
                System.out.println("Invalid item id.");
                continue;
            }
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            order.addItem(item, quantity);
        }
        System.out.println("Select payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Card");
        System.out.println("3. Online");
        int paymentChoice = scanner.nextInt();
        PaymentStrategy paymentStrategy = null;
        switch (paymentChoice) {
            case 1:
                paymentStrategy = new CashPayment();
                break;
            case 2:
                paymentStrategy = new CardPayment();
                break;
            case 3:
                paymentStrategy = new OnlinePayment();
                break;
            default:
                System.out.println("Invalid payment method choice. Payment failed.");
                break;
        }
        if (paymentStrategy != null) {
            paymentStrategy.pay(order.calculateTotal());
        }
        restaurant.placeOrder(order);
    }

    private static void displayOrder(Scanner scanner) {
        System.out.print("Enter table number to display order: ");
        int tableNumber = scanner.nextInt();
        Order order = restaurant.getOrderByTableNumber(tableNumber);
        if (order != null) {
            System.out.println("Order for Table " + order.getTableNumber());
            for (MenuItem item : order.getItems()) {
                System.out.println("- " + item.getName() + " (Description: " + item.getDescription() + ")");
            }
            System.out.println("Total Amount: $" + order.calculateTotal());
        } else {
            System.out.println("No order found for the specified table number.");
        }
    }

    private static void updateMenuItemPrice(Scanner scanner) {
        System.out.print("Enter the item id to update price: ");
        String itemId = scanner.next();
        MenuItem itemToUpdate = restaurant.menu.getMenuItem().stream()
                .filter(i -> i.getId().equalsIgnoreCase(itemId))
                .findFirst().orElse(null);
        if (itemToUpdate != null) {
            System.out.print("Enter the new price: ");
            double newPrice = scanner.nextDouble();
            itemToUpdate.setPrice(newPrice);
            System.out.println("Price updated successfully.");
        } else {
            System.out.println("Invalid item id.");
        }
    }

    private static void removeMenuItem(Scanner scanner) {
        System.out.print("Enter the item id to remove: ");
        String itemId = scanner.next();
        MenuItem itemToRemove = restaurant.menu.getMenuItem().stream()
                .filter(i -> i.getId().equalsIgnoreCase(itemId))
                .findFirst().orElse(null);
        if (itemToRemove != null) {
            restaurant.removeMenuItem(itemToRemove);
            System.out.println("Item removed successfully.");
        } else {
            System.out.println("Invalid item id.");
        }
    }
    private static void displayUpdatedMenu() {
        System.out.println("Updated Menu:");
        restaurant.displayMenu();
    }
}
