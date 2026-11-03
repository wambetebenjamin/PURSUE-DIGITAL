package com.google.common.base;

import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void checkArgument(boolean z, String str, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, int i2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, char c2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    public static void checkArgument(boolean z, String str, int i2, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i2, int i3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void checkArgument(boolean z, String str, int i2, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, int i2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Integer.valueOf(i2), obj));
        }
    }

    public static void checkArgument(boolean z, String str, long j2, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j2, int i2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, String str, long j2, long j3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void checkArgument(boolean z, String str, long j2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, Long.valueOf(j2), obj));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, char c2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, int i2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Integer.valueOf(i2)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, long j2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, Long.valueOf(j2)));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void checkArgument(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (!z) {
            throw new IllegalArgumentException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void checkState(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkState(boolean z, @CheckForNull String str, @CheckForNull Object... objArr) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void checkState(boolean z, String str, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, String str, int i2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i2)));
        }
    }

    public static void checkState(boolean z, String str, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj));
        }
    }

    public static void checkState(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void checkState(boolean z, String str, char c2, int i2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    public static void checkState(boolean z, String str, char c2, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, String str, char c2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    public static void checkState(boolean z, String str, int i2, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, String str, int i2, int i3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void checkState(boolean z, String str, int i2, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, String str, int i2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Integer.valueOf(i2), obj));
        }
    }

    public static void checkState(boolean z, String str, long j2, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, String str, long j2, int i2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    public static void checkState(boolean z, String str, long j2, long j3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void checkState(boolean z, String str, long j2, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, Long.valueOf(j2), obj));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, char c2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, int i2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Integer.valueOf(i2)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, long j2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, Long.valueOf(j2)));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void checkState(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (!z) {
            throw new IllegalStateException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    public static <T> T checkNotNull(@CheckForNull T t) {
        java.util.Objects.requireNonNull(t);
        return t;
    }

    public static <T> T checkNotNull(@CheckForNull T t, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, objArr));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c2, char c3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c2, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c2, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, char c2, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int i2, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i2), Character.valueOf(c2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int i2, int i3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i2), Integer.valueOf(i3)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int i2, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i2), Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, int i2, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Integer.valueOf(i2), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j2, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j2), Character.valueOf(c2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j2, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j2), Integer.valueOf(i2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j2, long j3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j2), Long.valueOf(j3)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, long j2, @CheckForNull Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, Long.valueOf(j2), obj));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, char c2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, int i2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Integer.valueOf(i2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, long j2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, Long.valueOf(j2)));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3));
    }

    public static <T> T checkNotNull(@CheckForNull T t, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
    }

    public static int checkElementIndex(int i2, int i3) {
        return checkElementIndex(i2, i3, "index");
    }

    public static int checkElementIndex(int i2, int i3, String str) {
        if (i2 < 0 || i2 >= i3) {
            throw new IndexOutOfBoundsException(badElementIndex(i2, i3, str));
        }
        return i2;
    }

    private static String badElementIndex(int i2, int i3, String str) {
        if (i2 < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return Strings.lenientFormat("%s (%s) must be less than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException(new StringBuilder(26).append("negative size: ").append(i3).toString());
    }

    public static int checkPositionIndex(int i2, int i3) {
        return checkPositionIndex(i2, i3, "index");
    }

    public static int checkPositionIndex(int i2, int i3, String str) {
        if (i2 < 0 || i2 > i3) {
            throw new IndexOutOfBoundsException(badPositionIndex(i2, i3, str));
        }
        return i2;
    }

    private static String badPositionIndex(int i2, int i3, String str) {
        if (i2 < 0) {
            return Strings.lenientFormat("%s (%s) must not be negative", str, Integer.valueOf(i2));
        }
        if (i3 >= 0) {
            return Strings.lenientFormat("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i2), Integer.valueOf(i3));
        }
        throw new IllegalArgumentException(new StringBuilder(26).append("negative size: ").append(i3).toString());
    }

    public static void checkPositionIndexes(int i2, int i3, int i4) {
        if (i2 < 0 || i3 < i2 || i3 > i4) {
            throw new IndexOutOfBoundsException(badPositionIndexes(i2, i3, i4));
        }
    }

    private static String badPositionIndexes(int i2, int i3, int i4) {
        if (i2 < 0 || i2 > i4) {
            return badPositionIndex(i2, i4, "start index");
        }
        return (i3 < 0 || i3 > i4) ? badPositionIndex(i3, i4, "end index") : Strings.lenientFormat("end index (%s) must not be less than start index (%s)", Integer.valueOf(i3), Integer.valueOf(i2));
    }
}
