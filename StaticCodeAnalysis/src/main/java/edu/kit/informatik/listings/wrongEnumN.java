package edu.kit.informatik.listings;
public class wrongEnumN {
    private double balance;
    private CustomerType type;
    public wrongEnumN(double balance, CustomerType type) {
        this.balance = balance; this.type = type;
    }
    double payEntryFee() {
        double fee = 0;
        switch (type) {
            case CHILD: fee = 5.00; break;
            case ADULT: fee = 10; break;
            case SENIOR: fee = 8; break;
        }
        balance = -fee;
        return fee;
    }
}

enum CustomerType {
    CHILD, ADULT, SENIOR
}
