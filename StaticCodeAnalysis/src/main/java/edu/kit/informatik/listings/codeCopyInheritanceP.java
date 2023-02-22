package edu.kit.informatik.listings;

public class codeCopyInheritanceP extends BankAccount {
    private double balance;
    @Override
	public void deposit(double amount) { balance += amount; }
    @Override
	public void withdraw(double amount) { balance -= amount; }
    @Override
	public double getBalance() { return balance; }
    public void transfer(codeCopyInheritanceP other, double amount) { withdraw(amount); other.deposit(amount); }
}

