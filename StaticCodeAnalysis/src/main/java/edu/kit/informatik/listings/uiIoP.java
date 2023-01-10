public static void main(String[]args) {
    // ...
    if( account.transfer(amount) ){
        System.out.println("New balance: "+account.getBalance());
    } else {
        System.err.println("Error, unable to make transfer. Your balance is: " + account.getBalance());
    }
}

public class Account {
    public boolean transfer(int amount) {
        if (balance + amount > 0) {
            balance += amount;
            return true;
        } else { return false; }
    }
}
