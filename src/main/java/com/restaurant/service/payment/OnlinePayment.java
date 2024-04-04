package com.restaurant.service.payment;

public class OnlinePayment implements PaymentStrategy {
    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " online.");
    }
}
