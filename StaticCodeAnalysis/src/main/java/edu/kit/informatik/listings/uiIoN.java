public static void main(String[] args) {
    // ...
    int amount=Integer.parseInt(input);
    account.transfer(amount);
}

public class Account {
    public void transfer(int amount) {
        if (balance + amount > 0) {
            balance += amount;
            System.out.println("New balance: " + balance);
        } else {
            System.err.println("Error, unable to make transfer. Your balance is: " + balance);
        }
    }
}
