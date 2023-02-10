package edu.kit.informatik.listings;
public class uiloP {
    public static void main(String[] args) {
        Account2 account = new Account2();
        int amount = 0;
        if (account.transfer(amount)) {
            System.out.println("New balance: " + account.getBalance());
        } else {
            System.err.println("Error, unable to make transfer. Your balance is: " + account.getBalance());
        }
    }
}

