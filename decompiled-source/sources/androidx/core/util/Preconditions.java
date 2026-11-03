package androidx.core.util;

import android.text.TextUtils;
import java.util.Locale;
import java.util.Objects;
/* loaded from: classes.dex */
public final class Preconditions {
    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static <T extends CharSequence> T checkStringNotEmpty(T t) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException();
        }
        return t;
    }

    public static <T extends CharSequence> T checkStringNotEmpty(T t, Object obj) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return t;
    }

    public static <T extends CharSequence> T checkStringNotEmpty(T t, String str, Object... objArr) {
        if (TextUtils.isEmpty(t)) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
        return t;
    }

    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static void checkState(boolean z, String str) {
        if (!z) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkState(boolean z) {
        checkState(z, null);
    }

    public static int checkFlagsArgument(int i2, int i3) {
        if ((i2 & i3) == i2) {
            return i2;
        }
        throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(i3) + " are allowed");
    }

    public static int checkArgumentNonnegative(int i2, String str) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(str);
    }

    public static int checkArgumentNonnegative(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException();
    }

    public static int checkArgumentInRange(int i2, int i3, int i4, String str) {
        if (i2 >= i3) {
            if (i2 <= i4) {
                return i2;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i3), Integer.valueOf(i4)));
    }

    public static long checkArgumentInRange(long j2, long j3, long j4, String str) {
        if (j2 >= j3) {
            if (j2 <= j4) {
                return j2;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j3), Long.valueOf(j4)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j3), Long.valueOf(j4)));
    }

    public static float checkArgumentInRange(float f2, float f3, float f4, String str) {
        if (f2 >= f3) {
            if (f2 <= f4) {
                return f2;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f3), Float.valueOf(f4)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f3), Float.valueOf(f4)));
    }

    public static double checkArgumentInRange(double d2, double d3, double d4, String str) {
        if (d2 >= d3) {
            if (d2 <= d4) {
                return d2;
            }
            throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too high)", str, Double.valueOf(d3), Double.valueOf(d4)));
        }
        throw new IllegalArgumentException(String.format(Locale.US, "%s is out of range of [%f, %f] (too low)", str, Double.valueOf(d3), Double.valueOf(d4)));
    }

    public static float checkArgumentFinite(float f2, String str) {
        if (Float.isNaN(f2)) {
            throw new IllegalArgumentException(str + " must not be NaN");
        }
        if (Float.isInfinite(f2)) {
            throw new IllegalArgumentException(str + " must not be infinite");
        }
        return f2;
    }

    private Preconditions() {
    }
}
