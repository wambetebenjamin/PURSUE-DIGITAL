package androidx.tracing;

import android.os.Build;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public final class Trace {
    static final String TAG = "Trace";
    private static Method sAsyncTraceBeginMethod;
    private static Method sAsyncTraceEndMethod;
    private static Method sIsTagEnabledMethod;
    private static Method sTraceCounterMethod;
    private static long sTraceTagApp;

    public static boolean isEnabled() {
        try {
            if (sIsTagEnabledMethod == null) {
                return android.os.Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        return isEnabledFallback();
    }

    public static void beginSection(String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceApi18Impl.beginSection(str);
        }
    }

    public static void endSection() {
        if (Build.VERSION.SDK_INT >= 18) {
            TraceApi18Impl.endSection();
        }
    }

    public static void beginAsyncSection(String str, int i2) {
        try {
            if (sAsyncTraceBeginMethod == null) {
                TraceApi29Impl.beginAsyncSection(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        beginAsyncSectionFallback(str, i2);
    }

    public static void endAsyncSection(String str, int i2) {
        try {
            if (sAsyncTraceEndMethod == null) {
                TraceApi29Impl.endAsyncSection(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        endAsyncSectionFallback(str, i2);
    }

    public static void setCounter(String str, int i2) {
        try {
            if (sTraceCounterMethod == null) {
                TraceApi29Impl.setCounter(str, i2);
                return;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        setCounterFallback(str, i2);
    }

    private static boolean isEnabledFallback() {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sIsTagEnabledMethod == null) {
                    sTraceTagApp = android.os.Trace.class.getField("TRACE_TAG_APP").getLong(null);
                    sIsTagEnabledMethod = android.os.Trace.class.getMethod("isTagEnabled", Long.TYPE);
                }
                return ((Boolean) sIsTagEnabledMethod.invoke(null, Long.valueOf(sTraceTagApp))).booleanValue();
            } catch (Exception e2) {
                handleException("isTagEnabled", e2);
            }
        }
        return false;
    }

    private static void beginAsyncSectionFallback(String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sAsyncTraceBeginMethod == null) {
                    sAsyncTraceBeginMethod = android.os.Trace.class.getMethod("asyncTraceBegin", Long.TYPE, String.class, Integer.TYPE);
                }
                sAsyncTraceBeginMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                handleException("asyncTraceBegin", e2);
            }
        }
    }

    private static void endAsyncSectionFallback(String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sAsyncTraceEndMethod == null) {
                    sAsyncTraceEndMethod = android.os.Trace.class.getMethod("asyncTraceEnd", Long.TYPE, String.class, Integer.TYPE);
                }
                sAsyncTraceEndMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                handleException("asyncTraceEnd", e2);
            }
        }
    }

    private static void setCounterFallback(String str, int i2) {
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                if (sTraceCounterMethod == null) {
                    sTraceCounterMethod = android.os.Trace.class.getMethod("traceCounter", Long.TYPE, String.class, Integer.TYPE);
                }
                sTraceCounterMethod.invoke(null, Long.valueOf(sTraceTagApp), str, Integer.valueOf(i2));
            } catch (Exception e2) {
                handleException("traceCounter", e2);
            }
        }
    }

    private static void handleException(String str, Exception exc) {
        if (exc instanceof InvocationTargetException) {
            Throwable cause = exc.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
        Log.v(TAG, "Unable to call " + str + " via reflection", exc);
    }

    private Trace() {
    }
}
