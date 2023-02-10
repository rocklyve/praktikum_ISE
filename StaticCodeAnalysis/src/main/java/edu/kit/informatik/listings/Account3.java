package edu.kit.informatik.listings;

public class Account3 {
    int balance;

    Account3() {
        balance = 0;
    }

    public void transfer(int amount) {
        if (balance + amount > 0) {
            balance += amount;
            System.out.println("New balance: " + balance);
        } else {
            System.err.println("Error, unable to make transfer. Your balance is: " + balance);
        }
    }
}
