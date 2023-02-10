package edu.kit.informatik.listings;

public class SavingsAccount2 extends codeCopyInheritanceP {
    private double interestRate;

    public SavingsAccount2(double rate) {
        interestRate = rate;
    }

    public void addPeriodicInterest() {
        deposit(getBalance() * interestRate / 100);
    }
}
