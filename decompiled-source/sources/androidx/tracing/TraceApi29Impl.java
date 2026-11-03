package androidx.tracing;
/* loaded from: classes.dex */
final class TraceApi29Impl {
    private TraceApi29Impl() {
    }

    public static void beginAsyncSection(String str, int i2) {
        android.os.Trace.beginAsyncSection(str, i2);
    }

    public static void endAsyncSection(String str, int i2) {
        android.os.Trace.endAsyncSection(str, i2);
    }

    public static void setCounter(String str, int i2) {
        android.os.Trace.setCounter(str, i2);
    }
}
