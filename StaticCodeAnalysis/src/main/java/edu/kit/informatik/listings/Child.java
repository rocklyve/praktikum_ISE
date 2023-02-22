package edu.kit.informatik.listings;

public class Child extends wrongEnumP {
    public Child(double balance) {
        super(balance);
    }

    @Override
	double getEntryFee() {
        return 7.5;
    }
}
