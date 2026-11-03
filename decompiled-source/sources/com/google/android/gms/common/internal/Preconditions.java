package com.google.android.gms.common.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.bytedance.librarian.c;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class Preconditions {
    private Preconditions() {
        throw new AssertionError("Uninstantiable");
    }

    public static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static double checkArgumentInRange(double d2, double d3, double d4, String str) {
        if (d2 >= d3) {
            if (d2 <= d4) {
                return d2;
            }
            throw new IllegalArgumentException(zza("%s is out of range of [%f, %f] (too high)", str, Double.valueOf(d3), Double.valueOf(d4)));
        }
        throw new IllegalArgumentException(zza("%s is out of range of [%f, %f] (too low)", str, Double.valueOf(d3), Double.valueOf(d4)));
    }

    public static int checkArgumentNonnegative(int i2) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException("Given value is negative");
    }

    public static void checkHandlerThread(Handler handler) {
        Looper myLooper = Looper.myLooper();
        if (myLooper != handler.getLooper()) {
            String name = myLooper != null ? myLooper.getThread().getName() : "null current looper";
            String name2 = handler.getLooper().getThread().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name2).length() + 35 + String.valueOf(name).length() + 1);
            sb.append("Must be called on ");
            sb.append(name2);
            sb.append(" thread, but got ");
            sb.append(name);
            sb.append(c.a.DOT);
            throw new IllegalStateException(sb.toString());
        }
    }

    public static void checkMainThread() {
        checkMainThread("Must be called on the main application thread");
    }

    public static String checkNotEmpty(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Given String is empty or null");
        }
        return str;
    }

    public static void checkNotGoogleApiHandlerThread() {
        checkNotGoogleApiHandlerThread("Must not be called on GoogleApiHandler thread.");
    }

    public static void checkNotMainThread() {
        checkNotMainThread("Must not be called on the main application thread");
    }

    public static <T> T checkNotNull(T t) {
        java.util.Objects.requireNonNull(t, "null reference");
        return t;
    }

    public static int checkNotZero(int i2) {
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException("Given Integer is zero");
    }

    public static void checkState(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    static String zza(String str, Object... objArr) {
        int indexOf;
        StringBuilder sb = new StringBuilder(str.length() + 48);
        int i2 = 0;
        int i3 = 0;
        while (i2 < 3 && (indexOf = str.indexOf("%s", i3)) != -1) {
            sb.append(str.substring(i3, indexOf));
            sb.append(objArr[i2]);
            i3 = indexOf + 2;
            i2++;
        }
        sb.append(str.substring(i3));
        if (i2 < 3) {
            sb.append(" [");
            sb.append(objArr[i2]);
            for (int i4 = i2 + 1; i4 < 3; i4++) {
                sb.append(", ");
                sb.append(objArr[i4]);
            }
            sb.append("]");
        }
        return sb.toString();
    }

    public static void checkArgument(boolean z, Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static int checkArgumentNonnegative(int i2, String str) {
        if (i2 >= 0) {
            return i2;
        }
        throw new IllegalArgumentException(str);
    }

    public static void checkMainThread(String str) {
        if (!com.google.android.gms.common.util.zze.zza()) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkNotGoogleApiHandlerThread(String str) {
        Looper myLooper = Looper.myLooper();
        if (myLooper != null && java.util.Objects.equals(myLooper.getThread().getName(), "GoogleApiHandler")) {
            throw new IllegalStateException(str);
        }
    }

    public static void checkNotMainThread(String str) {
        if (com.google.android.gms.common.util.zze.zza()) {
            throw new IllegalStateException(str);
        }
    }

    public static <T> T checkNotNull(T t, Object obj) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(String.valueOf(obj));
    }

    public static int checkNotZero(int i2, Object obj) {
        if (i2 != 0) {
            return i2;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    public static void checkState(boolean z, Object obj) {
        if (!z) {
            throw new IllegalStateException(String.valueOf(obj));
        }
    }

    public static void checkArgument(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalArgumentException(String.format(str, objArr));
        }
    }

    public static float checkArgumentInRange(float f2, float f3, float f4, String str) {
        if (f2 >= f3) {
            if (f2 <= f4) {
                return f2;
            }
            throw new IllegalArgumentException(zza("%s is out of range of [%f, %f] (too high)", str, Float.valueOf(f3), Float.valueOf(f4)));
        }
        throw new IllegalArgumentException(zza("%s is out of range of [%f, %f] (too low)", str, Float.valueOf(f3), Float.valueOf(f4)));
    }

    public static long checkArgumentNonnegative(long j2) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException("Given value is negative");
    }

    public static String checkNotEmpty(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
        return str;
    }

    public static long checkNotZero(long j2) {
        if (j2 != 0) {
            return j2;
        }
        throw new IllegalArgumentException("Given Long is zero");
    }

    public static void checkState(boolean z, String str, Object... objArr) {
        if (!z) {
            throw new IllegalStateException(String.format(str, objArr));
        }
    }

    public static long checkArgumentNonnegative(long j2, String str) {
        if (j2 >= 0) {
            return j2;
        }
        throw new IllegalArgumentException(str);
    }

    public static long checkNotZero(long j2, Object obj) {
        if (j2 != 0) {
            return j2;
        }
        throw new IllegalArgumentException(String.valueOf(obj));
    }

    public static int checkArgumentInRange(int i2, int i3, int i4, String str) {
        if (i2 >= i3) {
            if (i2 <= i4) {
                return i2;
            }
            throw new IllegalArgumentException(zza("%s is out of range of [%d, %d] (too high)", str, Integer.valueOf(i3), Integer.valueOf(i4)));
        }
        throw new IllegalArgumentException(zza("%s is out of range of [%d, %d] (too low)", str, Integer.valueOf(i3), Integer.valueOf(i4)));
    }

    public static void checkHandlerThread(Handler handler, String str) {
        if (Looper.myLooper() != handler.getLooper()) {
            throw new IllegalStateException(str);
        }
    }

    public static long checkArgumentInRange(long j2, long j3, long j4, String str) {
        if (j2 >= j3) {
            if (j2 <= j4) {
                return j2;
            }
            throw new IllegalArgumentException(zza("%s is out of range of [%d, %d] (too high)", str, Long.valueOf(j3), Long.valueOf(j4)));
        }
        throw new IllegalArgumentException(zza("%s is out of range of [%d, %d] (too low)", str, Long.valueOf(j3), Long.valueOf(j4)));
    }
}
