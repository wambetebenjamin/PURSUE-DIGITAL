package com.google.common.util.concurrent;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLong value;

    public AtomicDouble(double d2) {
        this.value = new AtomicLong(Double.doubleToRawLongBits(d2));
    }

    public AtomicDouble() {
        this(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);
    }

    public final double get() {
        return Double.longBitsToDouble(this.value.get());
    }

    public final void set(double d2) {
        this.value.set(Double.doubleToRawLongBits(d2));
    }

    public final void lazySet(double d2) {
        this.value.lazySet(Double.doubleToRawLongBits(d2));
    }

    public final double getAndSet(double d2) {
        return Double.longBitsToDouble(this.value.getAndSet(Double.doubleToRawLongBits(d2)));
    }

    public final boolean compareAndSet(double d2, double d3) {
        return this.value.compareAndSet(Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public final boolean weakCompareAndSet(double d2, double d3) {
        return this.value.weakCompareAndSet(Double.doubleToRawLongBits(d2), Double.doubleToRawLongBits(d3));
    }

    public final double getAndAdd(double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.value.get();
            longBitsToDouble = Double.longBitsToDouble(j2);
        } while (!this.value.compareAndSet(j2, Double.doubleToRawLongBits(longBitsToDouble + d2)));
        return longBitsToDouble;
    }

    public final double addAndGet(double d2) {
        long j2;
        double longBitsToDouble;
        do {
            j2 = this.value.get();
            longBitsToDouble = Double.longBitsToDouble(j2) + d2;
        } while (!this.value.compareAndSet(j2, Double.doubleToRawLongBits(longBitsToDouble)));
        return longBitsToDouble;
    }

    public String toString() {
        return Double.toString(get());
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) get();
    }

    @Override // java.lang.Number
    public long longValue() {
        return (long) get();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) get();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return get();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeDouble(get());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.value = new AtomicLong();
        set(objectInputStream.readDouble());
    }
}
