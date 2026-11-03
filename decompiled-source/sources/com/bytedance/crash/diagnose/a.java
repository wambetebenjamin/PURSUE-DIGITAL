package com.bytedance.crash.diagnose;

import android.os.SystemClock;
import com.bytedance.apm.constant.i;
import com.bytedance.crash.diagnose.b;
import org.json.JSONObject;
/* compiled from: InitMonitor.java */
/* loaded from: classes2.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static a f6482a;

    /* renamed from: b  reason: collision with root package name */
    private final JSONObject f6483b = new JSONObject();

    /* renamed from: c  reason: collision with root package name */
    private final long f6484c;

    /* renamed from: d  reason: collision with root package name */
    private long f6485d;

    private a() {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.f6484c = uptimeMillis;
        this.f6485d = uptimeMillis;
    }

    public static void a() {
        if (f6482a == null) {
            f6482a = new a();
        }
    }

    public static void a(String str) {
        if (f6482a == null) {
            return;
        }
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            a aVar = f6482a;
            aVar.f6483b.put(str, uptimeMillis - aVar.f6485d);
            f6482a.f6485d = uptimeMillis;
        } catch (Throwable unused) {
        }
    }

    public static void b() {
        if (f6482a == null) {
            return;
        }
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            a aVar = f6482a;
            aVar.f6483b.put(i.PERF_DISK_TOTAL, uptimeMillis - aVar.f6484c);
        } catch (Throwable unused) {
        }
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.diagnose.a.1
            @Override // java.lang.Runnable
            public void run() {
                a.c();
            }
        });
    }

    public static void c() {
        if (f6482a != null) {
            new b.a("npthStart").a(f6482a.f6483b).a();
            f6482a = null;
        }
    }
}
