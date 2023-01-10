package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class BucketList4 {
    private static final int MAX_CAPACITY = 42;

    private Bucket[] buckets;

    public BucketList4() {
        this.buckets = new Bucket[MAX_CAPACITY];
    }

    public Bucket getFirst() {
        return this.buckets[0];
    }
}