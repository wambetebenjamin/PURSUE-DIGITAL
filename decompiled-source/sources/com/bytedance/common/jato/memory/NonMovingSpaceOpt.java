package com.bytedance.common.jato.memory;

import android.os.Build;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class NonMovingSpaceOpt {

    /* renamed from: a  reason: collision with root package name */
    private static WeakReference<b> f4873a;

    /* loaded from: classes.dex */
    public interface a {
        boolean a();
    }

    private static native boolean optimizeNonMovingSpace(int i2, int i3);

    public static native void printHeapStatus();

    public static void a(int i2, int i3) {
        if (Build.VERSION.SDK_INT >= 26 && com.bytedance.common.jato.d.loadLibrary() && i2 >= 0 && i3 >= 0) {
            try {
                optimizeNonMovingSpace(i2 * 1024 * 1024, i3);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(int i2, int i3, a aVar) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        f4873a = new WeakReference<>(new b());
        b.f4874a = i2;
        b.f4875b = i3;
        b.f4876c = aVar;
    }

    /* loaded from: classes.dex */
    private static final class b {

        /* renamed from: a  reason: collision with root package name */
        static int f4874a = -1;

        /* renamed from: b  reason: collision with root package name */
        static int f4875b = -1;

        /* renamed from: c  reason: collision with root package name */
        static a f4876c;

        private b() {
        }

        protected void finalize() {
            a aVar = f4876c;
            if (aVar == null || !aVar.a()) {
                WeakReference unused = NonMovingSpaceOpt.f4873a = new WeakReference(new b());
                return;
            }
            NonMovingSpaceOpt.a(f4874a, f4875b);
            WeakReference unused2 = NonMovingSpaceOpt.f4873a = null;
        }
    }
}
