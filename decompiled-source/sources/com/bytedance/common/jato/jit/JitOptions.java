package com.bytedance.common.jato.jit;

import android.os.Build;
import com.bytedance.common.jato.d;
import java.util.ArrayList;
import java.util.Iterator;
/* loaded from: classes.dex */
public class JitOptions {
    public static native void compilerOptionsSupport14();

    public static native long[] getCompileThreshold();

    public static native int getJitTaskCount();

    public static native void setCompileThreshold(int i2, int i3, int i4, int i5, int i6);

    private static native void setCompilerOptions(String[] strArr);

    static {
        d.loadLibrary();
    }

    public static void a(int i2, int i3, int i4, int i5, int i6) {
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT > 32) {
            return;
        }
        setCompileThreshold(i2, i3, i4, i5, i6);
    }

    public static a a() {
        long[] compileThreshold;
        if (Build.VERSION.SDK_INT < 29 || Build.VERSION.SDK_INT > 32 || (compileThreshold = getCompileThreshold()) == null || compileThreshold.length != 5) {
            return null;
        }
        a aVar = new a();
        aVar.f4767a = compileThreshold[0];
        aVar.f4768b = compileThreshold[1];
        aVar.f4769c = compileThreshold[2];
        aVar.f4770d = compileThreshold[3];
        aVar.f4771e = compileThreshold[4];
        return aVar;
    }

    public static void a(boolean z, boolean z2) {
        a(z ? 32 : 0, z2);
    }

    public static void a(int i2, boolean z) {
        ArrayList arrayList = new ArrayList();
        if (i2 >= 0) {
            arrayList.add("--inline-max-code-units=" + i2);
        }
        if (!z) {
            arrayList.add("--no-generate-debug-info");
            arrayList.add("--no-generate-mini-debug-info");
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String[] strArr = new String[arrayList.size()];
        int i3 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            strArr[i3] = (String) it.next();
            i3++;
        }
        setCompilerOptions(strArr);
    }

    public static void a(boolean z) {
        ArrayList arrayList = new ArrayList();
        if (!z) {
            arrayList.add("--no-generate-debug-info");
            arrayList.add("--no-generate-mini-debug-info");
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String[] strArr = new String[arrayList.size()];
        int i2 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            strArr[i2] = (String) it.next();
            i2++;
        }
        setCompilerOptions(strArr);
    }

    public static void a(int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 > 0) {
            arrayList.add("--huge-method-max=" + i2);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String[] strArr = new String[arrayList.size()];
        int i3 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            strArr[i3] = (String) it.next();
            i3++;
        }
        setCompilerOptions(strArr);
    }

    public static void b(int i2) {
        ArrayList arrayList = new ArrayList();
        if (i2 > 0) {
            arrayList.add("--large-method-max=" + i2);
        }
        if (arrayList.isEmpty()) {
            return;
        }
        String[] strArr = new String[arrayList.size()];
        int i3 = 0;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            strArr[i3] = (String) it.next();
            i3++;
        }
        setCompilerOptions(strArr);
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public long f4767a;

        /* renamed from: b  reason: collision with root package name */
        public long f4768b;

        /* renamed from: c  reason: collision with root package name */
        public long f4769c;

        /* renamed from: d  reason: collision with root package name */
        public long f4770d;

        /* renamed from: e  reason: collision with root package name */
        public long f4771e;

        public String toString() {
            return this.f4767a + "," + this.f4768b + "," + this.f4769c + "," + this.f4770d + "," + this.f4771e;
        }
    }
}
