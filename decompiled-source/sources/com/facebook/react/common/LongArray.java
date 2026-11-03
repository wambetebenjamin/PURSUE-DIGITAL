package com.facebook.react.common;
/* loaded from: classes8.dex */
public class LongArray {
    private static final double INNER_ARRAY_GROWTH_FACTOR = 1.8d;
    private long[] mArray;
    private int mLength = 0;

    public static LongArray createWithInitialCapacity(int i2) {
        return new LongArray(i2);
    }

    private LongArray(int i2) {
        this.mArray = new long[i2];
    }

    public void add(long j2) {
        growArrayIfNeeded();
        long[] jArr = this.mArray;
        int i2 = this.mLength;
        this.mLength = i2 + 1;
        jArr[i2] = j2;
    }

    public long get(int i2) {
        if (i2 >= this.mLength) {
            throw new IndexOutOfBoundsException("" + i2 + " >= " + this.mLength);
        }
        return this.mArray[i2];
    }

    public void set(int i2, long j2) {
        if (i2 >= this.mLength) {
            throw new IndexOutOfBoundsException("" + i2 + " >= " + this.mLength);
        }
        this.mArray[i2] = j2;
    }

    public int size() {
        return this.mLength;
    }

    public boolean isEmpty() {
        return this.mLength == 0;
    }

    public void dropTail(int i2) {
        int i3 = this.mLength;
        if (i2 > i3) {
            throw new IndexOutOfBoundsException("Trying to drop " + i2 + " items from array of length " + this.mLength);
        }
        this.mLength = i3 - i2;
    }

    private void growArrayIfNeeded() {
        int i2 = this.mLength;
        if (i2 == this.mArray.length) {
            long[] jArr = new long[Math.max(i2 + 1, (int) (i2 * INNER_ARRAY_GROWTH_FACTOR))];
            System.arraycopy(this.mArray, 0, jArr, 0, this.mLength);
            this.mArray = jArr;
        }
    }
}
