package edu.kit.informatik.listings;

public class SavingsAccount {
    private double balance, rate;

    public SavingsAccount(double rate) {
        this.rate = rate;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }

    public void transfer(SavingsAccount other, double amount) {
        withdraw(amount);
        other.deposit(amount);
    }

    public void addPeriodicInterest() {
        balance += balance * rate / 100;
    }
}
