package com.bytedance.common.jato.memory;

import android.os.Build;
import java.lang.ref.WeakReference;
/* loaded from: classes.dex */
public class TlabOpt {
    private static WeakReference<b> sGcWatcher;

    /* loaded from: classes.dex */
    public interface a {
        boolean a();
    }

    private static native void nativeDisableExpandTlab();

    private static native void nativeExpandTlab(String[] strArr, int[] iArr, int i2);

    public static void a(String[] strArr, int[] iArr, int i2) {
        if (Build.VERSION.SDK_INT < 29) {
            return;
        }
        if (!(i2 == 0 && (strArr == null || iArr == null || strArr.length == 0 || iArr.length == 0)) && com.bytedance.common.jato.d.loadLibrary()) {
            try {
                nativeExpandTlab(strArr, iArr, i2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(a aVar) {
        if (Build.VERSION.SDK_INT <= 29) {
            return;
        }
        sGcWatcher = new WeakReference<>(new b(aVar));
    }

    public static void a() {
        if (Build.VERSION.SDK_INT >= 26 && com.bytedance.common.jato.d.loadLibrary()) {
            try {
                nativeDisableExpandTlab();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* loaded from: classes.dex */
    private static final class b {

        /* renamed from: a  reason: collision with root package name */
        private a f4882a;

        public b(a aVar) {
            this.f4882a = aVar;
        }

        protected void finalize() {
            a aVar = this.f4882a;
            if (aVar == null || !aVar.a()) {
                WeakReference unused = TlabOpt.sGcWatcher = new WeakReference(new b(this.f4882a));
                return;
            }
            TlabOpt.a();
            WeakReference unused2 = TlabOpt.sGcWatcher = null;
        }
    }
}
