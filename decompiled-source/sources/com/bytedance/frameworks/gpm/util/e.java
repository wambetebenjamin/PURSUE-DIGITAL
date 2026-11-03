package com.bytedance.frameworks.gpm.util;

import android.os.Build;
/* compiled from: GPUUtil.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f8548a = false;

    /* renamed from: b  reason: collision with root package name */
    private static String f8549b = "unknown";

    /* renamed from: c  reason: collision with root package name */
    private static String f8550c = "unknown";

    /* renamed from: d  reason: collision with root package name */
    private static String f8551d = "unknown";

    /* JADX WARN: Can't wrap try/catch for region: R(4:(3:112|113|(6:115|(5:66|67|(3:71|72|(2:74|(2:76|(2:78|(2:80|(2:95|96)(2:84|(2:93|94)(2:88|(1:90)(2:91|92))))(2:97|98))(2:99|100))(2:101|102)))|69|70)(1:7)|8|9|10|(6:12|(1:16)|(1:20)|(1:27)|24|25)(1:29)))|9|10|(0)(0)) */
    /* JADX WARN: Code restructure failed: missing block: B:128:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:?, code lost:
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0110, code lost:
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0137, code lost:
        android.opengl.EGL14.eglMakeCurrent(r1, android.opengl.EGL14.EGL_NO_SURFACE, android.opengl.EGL14.EGL_NO_SURFACE, android.opengl.EGL14.EGL_NO_CONTEXT);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0140, code lost:
        if (r2 != null) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x0146, code lost:
        android.opengl.EGL14.eglDestroySurface(r1, r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x0149, code lost:
        if (r3 != null) goto L40;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x014f, code lost:
        android.opengl.EGL14.eglDestroyContext(r1, r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0152, code lost:
        if (r1 == null) goto L47;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x001f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:125:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:128:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ee  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0137  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a() {
        /*
            Method dump skipped, instructions count: 388
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.frameworks.gpm.util.e.a():void");
    }

    public static String getGPUVendor() {
        if (Build.VERSION.SDK_INT <= 19) {
            return f8549b;
        }
        if (!f8548a) {
            a();
        }
        return f8549b;
    }

    public static String b() {
        if (Build.VERSION.SDK_INT <= 19) {
            return f8551d;
        }
        if (!f8548a) {
            a();
        }
        return f8551d;
    }

    public static String getGPUModel() {
        if (Build.VERSION.SDK_INT <= 19) {
            return f8550c;
        }
        if (!f8548a) {
            a();
        }
        return f8550c;
    }
}
