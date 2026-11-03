package com.bytedance.platform.godzilla.utils;
/* compiled from: Preconditions.java */
/* loaded from: classes2.dex */
public final class g {
    private g() {
    }

    public static void checkArgument(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T> T checkNotNull(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(str);
    }

    public static <T> T checkNotNull(T t, String str, Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new IllegalArgumentException(String.format(str, objArr));
    }

    public static int checkElementIndex(int i2, int i3, String str) {
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException(str);
        }
        return i2;
    }

    public static int checkElementIndex(int i2, int i3, String str, Object... objArr) {
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException(String.format(str, objArr));
        }
        return i2;
    }

    public static void checkTrue(boolean z, String str) {
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void checkTrue(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }
}
