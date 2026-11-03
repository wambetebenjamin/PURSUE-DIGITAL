package com.bytedance.common.jato.fastnative;

import com.bytedance.common.jato.d;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class FastNative {

    /* renamed from: a  reason: collision with root package name */
    static ExecutorService f4689a;

    private static native Method[] getMethodsNative(String[] strArr, String[] strArr2, String[] strArr3);

    private static native Method[] nativeModifyFastNativeInner(Method[] methodArr, boolean z, boolean z2);

    static {
        d.loadLibrary();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(String[] strArr, String[] strArr2, String[] strArr3, boolean z) {
        Method[] methodsNative = getMethodsNative(strArr, strArr2, strArr3);
        int i2 = 0;
        while (methodsNative != null && methodsNative.length > 0 && i2 != 3) {
            i2++;
            methodsNative = nativeModifyFastNativeInner(methodsNative, z, true);
            try {
                Thread.sleep(10000L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(final String[] strArr, final String[] strArr2, final String[] strArr3, final boolean z) {
        if (f4689a == null) {
            f4689a = Executors.newSingleThreadExecutor();
        }
        f4689a.execute(new Runnable() { // from class: com.bytedance.common.jato.fastnative.FastNative.1
            @Override // java.lang.Runnable
            public void run() {
                FastNative.d(strArr, strArr2, strArr3, z);
            }
        });
    }

    public static void b(String[] strArr, String[] strArr2, String[] strArr3, boolean z) {
        nativeModifyFastNativeInner(getMethodsNative(strArr, strArr2, strArr3), z, false);
    }

    /* loaded from: classes.dex */
    public static class Stub {
        public void stubMethod() {
            System.out.println("stubMethod");
        }
    }
}
