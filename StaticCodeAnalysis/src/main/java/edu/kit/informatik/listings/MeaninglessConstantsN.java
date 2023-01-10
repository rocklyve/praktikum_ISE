package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class BucketList {
    private static final int CONSTANT = 0;
    private static final int FOURTY_TWO = 42;

    private Bucket[] buckets;

    public BucketList() {
        this.buckets = new Bucket[FOURTY_TWO];
    }

    public Bucket getFirst() {
        return this.buckets[CONSTANT];
    }
}