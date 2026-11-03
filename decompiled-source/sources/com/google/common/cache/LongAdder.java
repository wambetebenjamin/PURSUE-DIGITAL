package com.google.common.cache;

import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
final class LongAdder extends Striped64 implements Serializable, LongAddable {
    private static final long serialVersionUID = 7249069246863182397L;

    @Override // com.google.common.cache.Striped64
    final long fn(long j2, long j3) {
        return j2 + j3;
    }

    @Override // com.google.common.cache.LongAddable
    public void add(long j2) {
        int length;
        Striped64.Cell cell;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr == null) {
            long j3 = this.base;
            if (casBase(j3, j3 + j2)) {
                return;
            }
        }
        int[] iArr = threadHashCode.get();
        boolean z = true;
        if (iArr != null && cellArr != null && (length = cellArr.length) >= 1 && (cell = cellArr[(length - 1) & iArr[0]]) != null) {
            long j4 = cell.value;
            z = cell.cas(j4, j4 + j2);
            if (z) {
                return;
            }
        }
        retryUpdate(j2, iArr, z);
    }

    @Override // com.google.common.cache.LongAddable
    public void increment() {
        add(1L);
    }

    public void decrement() {
        add(-1L);
    }

    @Override // com.google.common.cache.LongAddable
    public long sum() {
        long j2 = this.base;
        Striped64.Cell[] cellArr = this.cells;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.value;
                }
            }
        }
        return j2;
    }

    public void reset() {
        internalReset(0L);
    }

    public long sumThenReset() {
        long j2 = this.base;
        Striped64.Cell[] cellArr = this.cells;
        this.base = 0L;
        if (cellArr != null) {
            for (Striped64.Cell cell : cellArr) {
                if (cell != null) {
                    j2 += cell.value;
                    cell.value = 0L;
                }
            }
        }
        return j2;
    }

    public String toString() {
        return Long.toString(sum());
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) sum();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return sum();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = objectInputStream.readLong();
    }
}
