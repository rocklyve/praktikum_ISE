package edu.kit.informatik.listings;

import org.eclipse.core.internal.localstore.Bucket;

public class BucketList2 {
    private static final int FIRST_BUCKET_INDEX = 0;

    private Bucket[] buckets;

    public BucketList2() {
        this.buckets = new Bucket[42];
    }

    public Bucket getFirst() {
        return this.buckets[FIRST_BUCKET_INDEX];
    }
}