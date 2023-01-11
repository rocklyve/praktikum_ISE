package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class MeaninglessConstantsN {
    private static final int CONSTANT = 0;
    private static final int FOURTY_TWO = 42;

    private Bucket[] buckets;

    public MeaninglessConstantsN() {
        this.buckets = new Bucket[FOURTY_TWO];
    }

    public Bucket getFirst() {
        return this.buckets[CONSTANT];
    }
}