package com.bytedance.common.jato.boost.hardware;

import android.content.Context;
import android.os.Build;
/* compiled from: BoostFramework.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f4593a = "qcom";

    /* renamed from: b  reason: collision with root package name */
    private static final String f4594b = "qsc";

    /* renamed from: c  reason: collision with root package name */
    private static final String f4595c = "msm";

    /* renamed from: d  reason: collision with root package name */
    private static final String f4596d = "sdm";

    /* renamed from: e  reason: collision with root package name */
    private static final String f4597e = "mt";

    /* renamed from: f  reason: collision with root package name */
    private static final String f4598f = "hi";

    /* renamed from: g  reason: collision with root package name */
    private static final String f4599g = "kirin";

    /* renamed from: h  reason: collision with root package name */
    private static a f4600h;

    /* renamed from: i  reason: collision with root package name */
    private com.bytedance.common.jato.c f4601i;

    /* renamed from: j  reason: collision with root package name */
    private c f4602j;

    private a(Context context, com.bytedance.common.jato.c cVar) {
        this.f4601i = cVar;
        c c2 = c();
        this.f4602j = c2;
        c2.a(this, context);
    }

    public static synchronized a a(Context context, com.bytedance.common.jato.c cVar) {
        a aVar;
        synchronized (a.class) {
            if (f4600h == null) {
                synchronized (a.class) {
                    f4600h = new a(context, cVar);
                }
            }
            aVar = f4600h;
        }
        return aVar;
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            aVar = f4600h;
        }
        return aVar;
    }

    public synchronized boolean a(long j2) {
        c cVar = this.f4602j;
        if (cVar != null) {
            return cVar.a(j2);
        }
        return false;
    }

    public synchronized boolean b(long j2) {
        c cVar = this.f4602j;
        if (cVar != null) {
            return cVar.c(j2);
        }
        return false;
    }

    public synchronized boolean c(long j2) {
        c cVar = this.f4602j;
        if (cVar != null) {
            return cVar.b(j2);
        }
        return false;
    }

    public synchronized void b() {
        c cVar = this.f4602j;
        if (cVar != null) {
            cVar.a();
        }
    }

    private c c() {
        String str = Build.HARDWARE;
        a("cpuboost hardware: " + Build.HARDWARE);
        if (str.startsWith(f4593a) || str.startsWith(f4595c) || str.startsWith(f4594b) || str.startsWith(f4596d)) {
            a("cpuboost qcm boost");
            return new e();
        } else if (str.startsWith(f4597e)) {
            if (Build.VERSION.SDK_INT <= 28) {
                a("cpuboost mtk boost");
                return new d();
            }
            a("cpuboost unsupport for MTK Android9: " + Build.HARDWARE + Build.VERSION.SDK_INT);
            return new f();
        } else if (str.startsWith(f4599g) || str.startsWith(f4598f)) {
            a("cpuboost hisilicon boost");
            return new b();
        } else {
            a("cpuboost not found boost for: " + Build.HARDWARE);
            return new f();
        }
    }

    protected void a(String str) {
        com.bytedance.common.jato.c cVar = this.f4601i;
        if (cVar != null) {
            cVar.onDebugInfo(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void b(String str) {
        com.bytedance.common.jato.c cVar = this.f4601i;
        if (cVar != null) {
            cVar.onDebugInfo(str);
        }
    }
}
