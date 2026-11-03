package com.ttnet.org.chromium.base;
@FunctionalInterface
/* loaded from: classes9.dex */
public interface Callback<T> {
    void onResult(T t);

    default Runnable bind(final T t) {
        return new Runnable() { // from class: com.ttnet.org.chromium.base.Callback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Callback.this.onResult(t);
            }
        };
    }

    /* loaded from: classes9.dex */
    public static abstract class Helper {
        static void onObjectResultFromNative(Callback callback, Object obj) {
            callback.onResult(obj);
        }

        static void onBooleanResultFromNative(Callback callback, boolean z) {
            callback.onResult(Boolean.valueOf(z));
        }

        static void onIntResultFromNative(Callback callback, int i2) {
            callback.onResult(Integer.valueOf(i2));
        }

        static void onLongResultFromNative(Callback callback, long j2) {
            callback.onResult(Long.valueOf(j2));
        }

        static void onTimeResultFromNative(Callback callback, long j2) {
            callback.onResult(Long.valueOf(j2));
        }

        static void runRunnable(Runnable runnable) {
            runnable.run();
        }
    }
}
