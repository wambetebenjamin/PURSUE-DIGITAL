package com.google.android.datatransport;
/* loaded from: classes2.dex */
public abstract class Event<T> {
    public abstract Integer getCode();

    public abstract T getPayload();

    public abstract Priority getPriority();

    public abstract ProductData getProductData();

    public static <T> Event<T> ofData(int i2, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.DEFAULT, productData);
    }

    public static <T> Event<T> ofData(int i2, T t) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.DEFAULT, null);
    }

    public static <T> Event<T> ofData(T t, ProductData productData) {
        return new AutoValue_Event(null, t, Priority.DEFAULT, productData);
    }

    public static <T> Event<T> ofData(T t) {
        return new AutoValue_Event(null, t, Priority.DEFAULT, null);
    }

    public static <T> Event<T> ofTelemetry(int i2, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.VERY_LOW, productData);
    }

    public static <T> Event<T> ofTelemetry(int i2, T t) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.VERY_LOW, null);
    }

    public static <T> Event<T> ofTelemetry(T t, ProductData productData) {
        return new AutoValue_Event(null, t, Priority.VERY_LOW, productData);
    }

    public static <T> Event<T> ofTelemetry(T t) {
        return new AutoValue_Event(null, t, Priority.VERY_LOW, null);
    }

    public static <T> Event<T> ofUrgent(int i2, T t, ProductData productData) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.HIGHEST, productData);
    }

    public static <T> Event<T> ofUrgent(int i2, T t) {
        return new AutoValue_Event(Integer.valueOf(i2), t, Priority.HIGHEST, null);
    }

    public static <T> Event<T> ofUrgent(T t, ProductData productData) {
        return new AutoValue_Event(null, t, Priority.HIGHEST, productData);
    }

    public static <T> Event<T> ofUrgent(T t) {
        return new AutoValue_Event(null, t, Priority.HIGHEST, null);
    }
}
