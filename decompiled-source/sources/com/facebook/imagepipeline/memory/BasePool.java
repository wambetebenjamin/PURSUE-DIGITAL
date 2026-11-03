package com.facebook.imagepipeline.memory;

import android.util.SparseArray;
import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Sets;
import com.facebook.common.internal.Throwables;
import com.facebook.common.logging.FLog;
import com.facebook.common.memory.MemoryTrimType;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.Pool;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
/* loaded from: classes8.dex */
public abstract class BasePool<V> implements Pool<V> {
    private final Class<?> TAG = getClass();
    private boolean mAllowNewBuckets;
    final SparseArray<Bucket<V>> mBuckets;
    final Counter mFree;
    public final Set<V> mInUseValues;
    final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    final PoolParams mPoolParams;
    private final PoolStatsTracker mPoolStatsTracker;
    final Counter mUsed;

    protected abstract V alloc(int i2);

    protected abstract void free(V v);

    protected abstract int getBucketedSize(int i2);

    protected abstract int getBucketedSizeForValue(V v);

    protected abstract int getSizeInBytes(int i2);

    protected void onParamsChanged() {
    }

    public BasePool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker poolStatsTracker) {
        this.mMemoryTrimmableRegistry = (MemoryTrimmableRegistry) Preconditions.checkNotNull(memoryTrimmableRegistry);
        PoolParams poolParams2 = (PoolParams) Preconditions.checkNotNull(poolParams);
        this.mPoolParams = poolParams2;
        this.mPoolStatsTracker = (PoolStatsTracker) Preconditions.checkNotNull(poolStatsTracker);
        this.mBuckets = new SparseArray<>();
        if (poolParams2.fixBucketsReinitialization) {
            initBuckets();
        } else {
            legacyInitBuckets(new SparseIntArray(0));
        }
        this.mInUseValues = Sets.newIdentityHashSet();
        this.mFree = new Counter();
        this.mUsed = new Counter();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void initialize() {
        this.mMemoryTrimmableRegistry.registerMemoryTrimmable(this);
        this.mPoolStatsTracker.setBasePool(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Nullable
    public synchronized V getValue(Bucket<V> bucket) {
        return bucket.get();
    }

    @Override // com.facebook.common.memory.Pool
    public V get(int i2) {
        V value;
        ensurePoolSizeInvariant();
        int bucketedSize = getBucketedSize(i2);
        synchronized (this) {
            Bucket<V> bucket = getBucket(bucketedSize);
            if (bucket != null && (value = getValue(bucket)) != null) {
                Preconditions.checkState(this.mInUseValues.add(value));
                int bucketedSizeForValue = getBucketedSizeForValue(value);
                int sizeInBytes = getSizeInBytes(bucketedSizeForValue);
                this.mUsed.increment(sizeInBytes);
                this.mFree.decrement(sizeInBytes);
                this.mPoolStatsTracker.onValueReuse(sizeInBytes);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (reuse) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(value)), Integer.valueOf(bucketedSizeForValue));
                }
                return value;
            }
            int sizeInBytes2 = getSizeInBytes(bucketedSize);
            if (!canAllocate(sizeInBytes2)) {
                throw new PoolSizeViolationException(this.mPoolParams.maxSizeHardCap, this.mUsed.mNumBytes, this.mFree.mNumBytes, sizeInBytes2);
            }
            this.mUsed.increment(sizeInBytes2);
            if (bucket != null) {
                bucket.incrementInUseCount();
            }
            V v = null;
            try {
                v = alloc(bucketedSize);
            } catch (Throwable th) {
                synchronized (this) {
                    this.mUsed.decrement(sizeInBytes2);
                    Bucket<V> bucket2 = getBucket(bucketedSize);
                    if (bucket2 != null) {
                        bucket2.decrementInUseCount();
                    }
                    Throwables.propagateIfPossible(th);
                }
            }
            synchronized (this) {
                Preconditions.checkState(this.mInUseValues.add(v));
                trimToSoftCap();
                this.mPoolStatsTracker.onAlloc(sizeInBytes2);
                logStats();
                if (FLog.isLoggable(2)) {
                    FLog.v(this.TAG, "get (alloc) (object, size) = (%x, %s)", Integer.valueOf(System.identityHashCode(v)), Integer.valueOf(bucketedSize));
                }
            }
            return v;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0080, code lost:
        r2.decrementInUseCount();
     */
    @Override // com.facebook.common.memory.Pool, com.facebook.common.references.ResourceReleaser
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void release(V r8) {
        /*
            r7 = this;
            com.facebook.common.internal.Preconditions.checkNotNull(r8)
            int r0 = r7.getBucketedSizeForValue(r8)
            int r1 = r7.getSizeInBytes(r0)
            monitor-enter(r7)
            com.facebook.imagepipeline.memory.Bucket r2 = r7.getBucketIfPresent(r0)     // Catch: java.lang.Throwable -> Lae
            java.util.Set<V> r3 = r7.mInUseValues     // Catch: java.lang.Throwable -> Lae
            boolean r3 = r3.remove(r8)     // Catch: java.lang.Throwable -> Lae
            r4 = 2
            if (r3 != 0) goto L3d
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lae
            java.lang.String r3 = "release (free, value unrecognized) (object, size) = (%x, %s)"
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> Lae
            r5 = 0
            int r6 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lae
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch: java.lang.Throwable -> Lae
            r4[r5] = r6     // Catch: java.lang.Throwable -> Lae
            r5 = 1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lae
            r4[r5] = r0     // Catch: java.lang.Throwable -> Lae
            com.facebook.common.logging.FLog.e(r2, r3, r4)     // Catch: java.lang.Throwable -> Lae
            r7.free(r8)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.PoolStatsTracker r8 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lae
            r8.onFree(r1)     // Catch: java.lang.Throwable -> Lae
            goto La9
        L3d:
            if (r2 == 0) goto L7e
            boolean r3 = r2.isMaxLengthExceeded()     // Catch: java.lang.Throwable -> Lae
            if (r3 != 0) goto L7e
            boolean r3 = r7.isMaxSizeSoftCapExceeded()     // Catch: java.lang.Throwable -> Lae
            if (r3 != 0) goto L7e
            boolean r3 = r7.isReusable(r8)     // Catch: java.lang.Throwable -> Lae
            if (r3 != 0) goto L52
            goto L7e
        L52:
            r2.release(r8)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mFree     // Catch: java.lang.Throwable -> Lae
            r2.increment(r1)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.BasePool$Counter r2 = r7.mUsed     // Catch: java.lang.Throwable -> Lae
            r2.decrement(r1)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.PoolStatsTracker r2 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lae
            r2.onValueRelease(r1)     // Catch: java.lang.Throwable -> Lae
            boolean r1 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lae
            if (r1 == 0) goto La9
            java.lang.Class<?> r1 = r7.TAG     // Catch: java.lang.Throwable -> Lae
            java.lang.String r2 = "release (reuse) (object, size) = (%x, %s)"
            int r8 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lae
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)     // Catch: java.lang.Throwable -> Lae
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lae
            com.facebook.common.logging.FLog.v(r1, r2, r8, r0)     // Catch: java.lang.Throwable -> Lae
            goto La9
        L7e:
            if (r2 == 0) goto L83
            r2.decrementInUseCount()     // Catch: java.lang.Throwable -> Lae
        L83:
            boolean r2 = com.facebook.common.logging.FLog.isLoggable(r4)     // Catch: java.lang.Throwable -> Lae
            if (r2 == 0) goto L9c
            java.lang.Class<?> r2 = r7.TAG     // Catch: java.lang.Throwable -> Lae
            java.lang.String r3 = "release (free) (object, size) = (%x, %s)"
            int r4 = java.lang.System.identityHashCode(r8)     // Catch: java.lang.Throwable -> Lae
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)     // Catch: java.lang.Throwable -> Lae
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> Lae
            com.facebook.common.logging.FLog.v(r2, r3, r4, r0)     // Catch: java.lang.Throwable -> Lae
        L9c:
            r7.free(r8)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.BasePool$Counter r8 = r7.mUsed     // Catch: java.lang.Throwable -> Lae
            r8.decrement(r1)     // Catch: java.lang.Throwable -> Lae
            com.facebook.imagepipeline.memory.PoolStatsTracker r8 = r7.mPoolStatsTracker     // Catch: java.lang.Throwable -> Lae
            r8.onFree(r1)     // Catch: java.lang.Throwable -> Lae
        La9:
            r7.logStats()     // Catch: java.lang.Throwable -> Lae
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lae
            return
        Lae:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> Lae
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.memory.BasePool.release(java.lang.Object):void");
    }

    @Override // com.facebook.common.memory.MemoryTrimmable
    public void trim(MemoryTrimType memoryTrimType) {
        trimToNothing();
    }

    protected boolean isReusable(V v) {
        Preconditions.checkNotNull(v);
        return true;
    }

    private synchronized void ensurePoolSizeInvariant() {
        boolean z;
        if (isMaxSizeSoftCapExceeded() && this.mFree.mNumBytes != 0) {
            z = false;
            Preconditions.checkState(z);
        }
        z = true;
        Preconditions.checkState(z);
    }

    private synchronized void legacyInitBuckets(SparseIntArray sparseIntArray) {
        Preconditions.checkNotNull(sparseIntArray);
        this.mBuckets.clear();
        SparseIntArray sparseIntArray2 = this.mPoolParams.bucketSizes;
        if (sparseIntArray2 != null) {
            for (int i2 = 0; i2 < sparseIntArray2.size(); i2++) {
                int keyAt = sparseIntArray2.keyAt(i2);
                this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), sparseIntArray2.valueAt(i2), sparseIntArray.get(keyAt, 0), this.mPoolParams.fixBucketsReinitialization));
            }
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private synchronized void initBuckets() {
        SparseIntArray sparseIntArray = this.mPoolParams.bucketSizes;
        if (sparseIntArray != null) {
            fillBuckets(sparseIntArray);
            this.mAllowNewBuckets = false;
        } else {
            this.mAllowNewBuckets = true;
        }
    }

    private void fillBuckets(SparseIntArray sparseIntArray) {
        this.mBuckets.clear();
        for (int i2 = 0; i2 < sparseIntArray.size(); i2++) {
            int keyAt = sparseIntArray.keyAt(i2);
            this.mBuckets.put(keyAt, new Bucket<>(getSizeInBytes(keyAt), sparseIntArray.valueAt(i2), 0, this.mPoolParams.fixBucketsReinitialization));
        }
    }

    private List<Bucket<V>> refillBuckets() {
        ArrayList arrayList = new ArrayList(this.mBuckets.size());
        int size = this.mBuckets.size();
        for (int i2 = 0; i2 < size; i2++) {
            Bucket<V> valueAt = this.mBuckets.valueAt(i2);
            int i3 = valueAt.mItemSize;
            int i4 = valueAt.mMaxLength;
            int inUseCount = valueAt.getInUseCount();
            if (valueAt.getFreeListSize() > 0) {
                arrayList.add(valueAt);
            }
            this.mBuckets.setValueAt(i2, new Bucket<>(getSizeInBytes(i3), i4, inUseCount, this.mPoolParams.fixBucketsReinitialization));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    void trimToNothing() {
        int i2;
        List arrayList;
        synchronized (this) {
            if (this.mPoolParams.fixBucketsReinitialization) {
                arrayList = refillBuckets();
            } else {
                arrayList = new ArrayList(this.mBuckets.size());
                SparseIntArray sparseIntArray = new SparseIntArray();
                for (int i3 = 0; i3 < this.mBuckets.size(); i3++) {
                    Bucket<V> valueAt = this.mBuckets.valueAt(i3);
                    if (valueAt.getFreeListSize() > 0) {
                        arrayList.add(valueAt);
                    }
                    sparseIntArray.put(this.mBuckets.keyAt(i3), valueAt.getInUseCount());
                }
                legacyInitBuckets(sparseIntArray);
            }
            this.mFree.reset();
            logStats();
        }
        onParamsChanged();
        for (i2 = 0; i2 < arrayList.size(); i2++) {
            Bucket bucket = (Bucket) arrayList.get(i2);
            while (true) {
                Object pop = bucket.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
            }
        }
    }

    synchronized void trimToSoftCap() {
        if (isMaxSizeSoftCapExceeded()) {
            trimToSize(this.mPoolParams.maxSizeSoftCap);
        }
    }

    synchronized void trimToSize(int i2) {
        int min = Math.min((this.mUsed.mNumBytes + this.mFree.mNumBytes) - i2, this.mFree.mNumBytes);
        if (min <= 0) {
            return;
        }
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Initial Size = %d; Bytes to free = %d", Integer.valueOf(i2), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes), Integer.valueOf(min));
        }
        logStats();
        for (int i3 = 0; i3 < this.mBuckets.size() && min > 0; i3++) {
            Bucket<V> valueAt = this.mBuckets.valueAt(i3);
            while (min > 0) {
                V pop = valueAt.pop();
                if (pop == null) {
                    break;
                }
                free(pop);
                min -= valueAt.mItemSize;
                this.mFree.decrement(valueAt.mItemSize);
            }
        }
        logStats();
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "trimToSize: TargetSize = %d; Final Size = %d", Integer.valueOf(i2), Integer.valueOf(this.mUsed.mNumBytes + this.mFree.mNumBytes));
        }
    }

    private synchronized Bucket<V> getBucketIfPresent(int i2) {
        return this.mBuckets.get(i2);
    }

    synchronized Bucket<V> getBucket(int i2) {
        Bucket<V> bucket = this.mBuckets.get(i2);
        if (bucket == null && this.mAllowNewBuckets) {
            if (FLog.isLoggable(2)) {
                FLog.v(this.TAG, "creating new bucket %s", Integer.valueOf(i2));
            }
            Bucket<V> newBucket = newBucket(i2);
            this.mBuckets.put(i2, newBucket);
            return newBucket;
        }
        return bucket;
    }

    Bucket<V> newBucket(int i2) {
        return new Bucket<>(getSizeInBytes(i2), Integer.MAX_VALUE, 0, this.mPoolParams.fixBucketsReinitialization);
    }

    synchronized boolean isMaxSizeSoftCapExceeded() {
        boolean z;
        z = this.mUsed.mNumBytes + this.mFree.mNumBytes > this.mPoolParams.maxSizeSoftCap;
        if (z) {
            this.mPoolStatsTracker.onSoftCapReached();
        }
        return z;
    }

    synchronized boolean canAllocate(int i2) {
        int i3 = this.mPoolParams.maxSizeHardCap;
        if (i2 > i3 - this.mUsed.mNumBytes) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        int i4 = this.mPoolParams.maxSizeSoftCap;
        if (i2 > i4 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            trimToSize(i4 - i2);
        }
        if (i2 > i3 - (this.mUsed.mNumBytes + this.mFree.mNumBytes)) {
            this.mPoolStatsTracker.onHardCapReached();
            return false;
        }
        return true;
    }

    private void logStats() {
        if (FLog.isLoggable(2)) {
            FLog.v(this.TAG, "Used = (%d, %d); Free = (%d, %d)", Integer.valueOf(this.mUsed.mCount), Integer.valueOf(this.mUsed.mNumBytes), Integer.valueOf(this.mFree.mCount), Integer.valueOf(this.mFree.mNumBytes));
        }
    }

    public synchronized Map<String, Integer> getStats() {
        HashMap hashMap;
        hashMap = new HashMap();
        for (int i2 = 0; i2 < this.mBuckets.size(); i2++) {
            hashMap.put(PoolStatsTracker.BUCKETS_USED_PREFIX + getSizeInBytes(this.mBuckets.keyAt(i2)), Integer.valueOf(this.mBuckets.valueAt(i2).getInUseCount()));
        }
        hashMap.put(PoolStatsTracker.SOFT_CAP, Integer.valueOf(this.mPoolParams.maxSizeSoftCap));
        hashMap.put(PoolStatsTracker.HARD_CAP, Integer.valueOf(this.mPoolParams.maxSizeHardCap));
        hashMap.put(PoolStatsTracker.USED_COUNT, Integer.valueOf(this.mUsed.mCount));
        hashMap.put(PoolStatsTracker.USED_BYTES, Integer.valueOf(this.mUsed.mNumBytes));
        hashMap.put(PoolStatsTracker.FREE_COUNT, Integer.valueOf(this.mFree.mCount));
        hashMap.put(PoolStatsTracker.FREE_BYTES, Integer.valueOf(this.mFree.mNumBytes));
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public static class Counter {
        private static final String TAG = "com.facebook.imagepipeline.memory.BasePool.Counter";
        int mCount;
        int mNumBytes;

        Counter() {
        }

        public void increment(int i2) {
            this.mCount++;
            this.mNumBytes += i2;
        }

        public void decrement(int i2) {
            int i3;
            int i4 = this.mNumBytes;
            if (i4 >= i2 && (i3 = this.mCount) > 0) {
                this.mCount = i3 - 1;
                this.mNumBytes = i4 - i2;
                return;
            }
            FLog.wtf(TAG, "Unexpected decrement of %d. Current numBytes = %d, count = %d", Integer.valueOf(i2), Integer.valueOf(this.mNumBytes), Integer.valueOf(this.mCount));
        }

        public void reset() {
            this.mCount = 0;
            this.mNumBytes = 0;
        }
    }

    /* loaded from: classes8.dex */
    public static class InvalidValueException extends RuntimeException {
        public InvalidValueException(Object obj) {
            super("Invalid value: " + obj.toString());
        }
    }

    /* loaded from: classes8.dex */
    public static class InvalidSizeException extends RuntimeException {
        public InvalidSizeException(Object obj) {
            super("Invalid size: " + obj.toString());
        }
    }

    /* loaded from: classes8.dex */
    public static class SizeTooLargeException extends InvalidSizeException {
        public SizeTooLargeException(Object obj) {
            super(obj);
        }
    }

    /* loaded from: classes8.dex */
    public static class PoolSizeViolationException extends RuntimeException {
        public PoolSizeViolationException(int i2, int i3, int i4, int i5) {
            super("Pool hard cap violation? Hard cap = " + i2 + " Used size = " + i3 + " Free size = " + i4 + " Request size = " + i5);
        }
    }
}
