package edu.kit.informatik.listings;

public class codeCopyInheritanceN {
    private double balance;
    public void deposit(double amount) { balance += amount; }
    public void withdraw(double amount) { balance -= amount; }
    public double getBalance() { return balance; }
    public void transfer(codeCopyInheritanceN other, double amount) { withdraw(amount); other.deposit(amount); }
}
