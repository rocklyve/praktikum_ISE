package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class BucketList3 {
    private static final int FIRST_BUCKET_INDEX = 0;
    private static final int MAX_CAPACITY = 42;

    private Bucket[] buckets;

    public BucketList3() {
        this.buckets = new Bucket[MAX_CAPACITY];
    }

    public Bucket getFirst() {
        return this.buckets[FIRST_BUCKET_INDEX];
    }
}