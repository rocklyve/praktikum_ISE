package edu.kit.informatik.listings;
public abstract class wrongEnumP {
    private double balance;
    public wrongEnumP(double balance) { this.balance = balance; }

    double payEntryFee() {
        double fee = getEntryFee();
        balance = -fee;
        return fee;
    }
    abstract double getEntryFee();
}
