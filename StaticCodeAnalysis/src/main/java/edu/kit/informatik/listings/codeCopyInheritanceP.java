package edu.kit.informatik.listings;

public class codeCopyInheritanceP extends BankAccount {
    private double balance;
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }
    public double getBalance() { return balance; }
    public void transfer(codeCopyInheritanceP other, double amount) { withdraw(amount); other.deposit(amount); }
}

