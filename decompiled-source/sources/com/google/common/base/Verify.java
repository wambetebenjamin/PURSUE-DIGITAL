package com.google.common.base;

import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public final class Verify {
    public static void verify(boolean z) {
        if (!z) {
            throw new VerifyException();
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object... objArr) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, objArr));
        }
    }

    public static void verify(boolean z, String str, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, int i2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, String str, long j2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj));
        }
    }

    public static void verify(boolean z, String str, char c2, char c3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Character.valueOf(c3)));
        }
    }

    public static void verify(boolean z, String str, int i2, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i2), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, long j2, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j2), Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, char c2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Character.valueOf(c2)));
        }
    }

    public static void verify(boolean z, String str, char c2, int i2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, String str, int i2, int i3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i2), Integer.valueOf(i3)));
        }
    }

    public static void verify(boolean z, String str, long j2, int i2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j2), Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, int i2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Integer.valueOf(i2)));
        }
    }

    public static void verify(boolean z, String str, char c2, long j2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, String str, int i2, long j2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i2), Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, String str, long j2, long j3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j2), Long.valueOf(j3)));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, long j2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, Long.valueOf(j2)));
        }
    }

    public static void verify(boolean z, String str, char c2, @CheckForNull Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Character.valueOf(c2), obj));
        }
    }

    public static void verify(boolean z, String str, int i2, @CheckForNull Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Integer.valueOf(i2), obj));
        }
    }

    public static void verify(boolean z, String str, long j2, @CheckForNull Object obj) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, Long.valueOf(j2), obj));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2, obj3));
        }
    }

    public static void verify(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3, @CheckForNull Object obj4) {
        if (!z) {
            throw new VerifyException(Strings.lenientFormat(str, obj, obj2, obj3, obj4));
        }
    }

    public static <T> T verifyNotNull(@CheckForNull T t) {
        return (T) verifyNotNull(t, "expected a non-null reference", new Object[0]);
    }

    public static <T> T verifyNotNull(@CheckForNull T t, String str, @CheckForNull Object... objArr) {
        if (t != null) {
            return t;
        }
        throw new VerifyException(Strings.lenientFormat(str, objArr));
    }

    private Verify() {
    }
}
