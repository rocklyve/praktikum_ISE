package edu.kit.informatik.listings;

public class Account2 {
    int balance;

    Account2() {
        balance = 0;
    }

    public boolean transfer(int amount) {
        if (balance + amount > 0) {
            balance += amount;
            return true;
        } else {
            return false;
        }
    }

    public int getBalance() {
        return balance;
    }
}
