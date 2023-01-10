public abstract class Customer {
    private double balance;
    public Customer(double balance) { this.balance = balance; }

    double payEntryFee() {
        double fee = getEntryFee();
        balance = -fee;
        return fee;
    }
    abstract double getEntryFee();
}
public class Child extends Customer {
    public Child(double balance) { super(balance); }
    double getEntryFee() { return 7.5; }
}
