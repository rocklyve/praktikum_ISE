package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class MeaninglessConstantsP2 {
    private static final int MAX_CAPACITY = 42;

    private Bucket[] buckets;

    public MeaninglessConstantsP2() {
        this.buckets = new Bucket[MAX_CAPACITY];
    }

    public Bucket getFirst() {
        return this.buckets[0];
    }
}