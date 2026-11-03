package com.bytedance.crash.dumper;

import android.os.Build;
import com.bytedance.common.wschannel.WsConstants;
import com.facebook.react.views.textinput.ReactEditTextInputConnectionWrapper;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
/* compiled from: ProcessState.java */
/* loaded from: classes2.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private static final int f6573a = 10;

    /* renamed from: b  reason: collision with root package name */
    private static o f6574b = new o();

    /* renamed from: f  reason: collision with root package name */
    private Class f6578f;

    /* renamed from: c  reason: collision with root package name */
    private a[] f6575c = new a[10];

    /* renamed from: d  reason: collision with root package name */
    private a[] f6576d = new a[10];

    /* renamed from: e  reason: collision with root package name */
    private int f6577e = 0;

    /* renamed from: g  reason: collision with root package name */
    private Object f6579g = null;

    /* renamed from: h  reason: collision with root package name */
    private Object f6580h = null;

    /* renamed from: i  reason: collision with root package name */
    private Field f6581i = null;

    /* renamed from: j  reason: collision with root package name */
    private volatile boolean f6582j = false;
    private Runnable k = new Runnable() { // from class: com.bytedance.crash.dumper.o.1
        @Override // java.lang.Runnable
        public void run() {
            o.this.c();
        }
    };

    public static o a() {
        return f6574b;
    }

    public void b() {
        for (int i2 = 0; i2 < 10; i2++) {
            this.f6575c[i2] = new a();
        }
        com.bytedance.crash.runtime.b.a(this.k, WsConstants.EXIT_DELAY_TIME);
        this.f6582j = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void c() {
        this.f6575c[this.f6577e].a();
        this.f6575c[this.f6577e].f6585a = System.currentTimeMillis();
        this.f6575c[this.f6577e].f6586b = e();
        this.f6577e = (this.f6577e + 1) % 10;
        com.bytedance.crash.runtime.b.a(this.k, WsConstants.EXIT_DELAY_TIME);
    }

    private synchronized void d() {
        synchronized (this.f6575c) {
            for (int i2 = 0; i2 < 10; i2++) {
                this.f6576d[i2] = this.f6575c[i2];
            }
        }
    }

    private int e() {
        try {
            if (this.f6579g == null) {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                this.f6578f = cls;
                Method a2 = com.bytedance.crash.util.f.a(cls, "currentActivityThread", null);
                a2.setAccessible(true);
                this.f6579g = a2.invoke(this.f6578f, new Object[0]);
            }
            if (Build.VERSION.SDK_INT >= 29) {
                if (this.f6581i == null) {
                    this.f6581i = com.bytedance.crash.util.f.b(this.f6578f, "mLastProcessState");
                }
                this.f6581i.setAccessible(true);
                return ((Integer) this.f6581i.get(this.f6579g)).intValue();
            }
            if (this.f6580h == null) {
                Method a3 = com.bytedance.crash.util.f.a(this.f6578f, "getApplicationThread", null);
                a3.setAccessible(true);
                Object invoke = a3.invoke(this.f6579g, new Object[0]);
                this.f6580h = invoke;
                this.f6581i = com.bytedance.crash.util.f.b(invoke.getClass(), "mLastProcessState");
            }
            Field field = this.f6581i;
            if (field != null) {
                field.setAccessible(true);
                return ((Integer) this.f6581i.get(this.f6580h)).intValue();
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    public void a(File file) {
        if (!this.f6582j) {
            return;
        }
        try {
            com.bytedance.crash.runtime.b.c(this.k);
            c();
            d();
            StringBuilder sb = new StringBuilder();
            Arrays.sort(this.f6576d, new Comparator<a>() { // from class: com.bytedance.crash.dumper.o.2
                @Override // java.util.Comparator
                /* renamed from: a */
                public int compare(a aVar, a aVar2) {
                    if (aVar.f6585a > aVar2.f6585a) {
                        return -1;
                    }
                    return aVar.f6585a == aVar2.f6585a ? 0 : 1;
                }
            });
            int i2 = 0;
            while (true) {
                a[] aVarArr = this.f6576d;
                if (i2 < aVarArr.length) {
                    sb.append(aVarArr[i2].f6585a);
                    sb.append(",");
                    sb.append(this.f6576d[i2].f6586b);
                    sb.append(ReactEditTextInputConnectionWrapper.NEWLINE_RAW_VALUE);
                    i2++;
                } else {
                    com.bytedance.crash.util.m.writeFile(new File(file, "process_state.txt"), sb.toString(), false);
                    return;
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00b9 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00ba A[Catch: all -> 0x00f8, TryCatch #6 {all -> 0x00f8, blocks: (B:3:0x0006, B:6:0x001c, B:40:0x00af, B:41:0x00b3, B:44:0x00ba, B:62:0x00e6, B:51:0x00d1, B:56:0x00db, B:37:0x00a9, B:38:0x00ac, B:26:0x0093), top: B:73:0x0006 }] */
    /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.CharSequence, java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r12v0 */
    /* JADX WARN: Type inference failed for: r12v4 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r21, org.json.JSONObject r22, org.json.JSONObject r23) {
        /*
            Method dump skipped, instructions count: 249
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.dumper.o.a(java.io.File, org.json.JSONObject, org.json.JSONObject):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: ProcessState.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public long f6585a;

        /* renamed from: b  reason: collision with root package name */
        public int f6586b;

        a() {
        }

        public void a() {
            this.f6585a = 0L;
            this.f6586b = 0;
        }
    }
}
