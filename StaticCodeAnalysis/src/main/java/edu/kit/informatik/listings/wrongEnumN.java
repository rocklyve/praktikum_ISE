public class Customer {
    private double balance; private CustomerType type;
    public Customer(double balance, CustomerType type) {
        this.balance = balance; this.type = type;
    }
    double payEntryFee() {
        double fee;
        switch (type) {
            case CHILD: fee = 5.00; break;
            case ADULT: fee = 10; break;
            case SENIOR: fee = 8; break;
        }
        balance = -fee;
        return fee;
    }
}
