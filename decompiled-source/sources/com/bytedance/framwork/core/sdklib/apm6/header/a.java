package com.bytedance.framwork.core.sdklib.apm6.header;

import android.os.Process;
import com.bytedance.framwork.core.sdklib.apm6.l;
import com.bytedance.framwork.core.sdklib.apm6.safety.d;
import com.bytedance.framwork.core.sdklib.util.c;
import com.facebook.internal.security.CertificateUtil;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* compiled from: HeaderManager.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f8671a;

    /* renamed from: b  reason: collision with root package name */
    private ConcurrentHashMap<Long, ConcurrentHashMap<Long, JSONObject>> f8672b = new ConcurrentHashMap<>();

    /* renamed from: c  reason: collision with root package name */
    private volatile boolean f8673c = false;

    public static a a() {
        if (f8671a == null) {
            synchronized (a.class) {
                if (f8671a == null) {
                    f8671a = new a();
                }
            }
        }
        return f8671a;
    }

    public long a(long j2, JSONObject jSONObject) {
        long c2 = c();
        if (!this.f8672b.containsKey(Long.valueOf(j2))) {
            this.f8672b.put(Long.valueOf(j2), new ConcurrentHashMap<>());
        }
        this.f8672b.get(Long.valueOf(j2)).put(Long.valueOf(c2), jSONObject);
        String b2 = b(j2, c2);
        if (l.e()) {
            d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "header init:" + j2 + CertificateUtil.DELIMITER + b2 + " " + jSONObject);
        }
        b.a().a(b2, c.a(jSONObject));
        b();
        return c2;
    }

    private synchronized void b() {
        if (this.f8673c) {
            return;
        }
        this.f8673c = true;
        b.a().b();
    }

    public JSONObject a(long j2, long j3) {
        ConcurrentHashMap<Long, JSONObject> concurrentHashMap = this.f8672b.get(Long.valueOf(j2));
        if (concurrentHashMap != null && concurrentHashMap.containsKey(Long.valueOf(j3))) {
            return concurrentHashMap.get(Long.valueOf(j3));
        }
        JSONObject a2 = b.a().a(b(j2, j3));
        if (l.e()) {
            d.b(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "header init:" + j2 + CertificateUtil.DELIMITER + j3 + " " + b(j2, j3));
        }
        if (!this.f8672b.containsKey(Long.valueOf(j2))) {
            this.f8672b.put(Long.valueOf(j2), new ConcurrentHashMap<>());
        }
        if (a2 != null) {
            this.f8672b.get(Long.valueOf(j2)).put(Long.valueOf(j3), a2);
        } else {
            a2 = l.a(j2);
        }
        if (a2 == null) {
            d.e(com.bytedance.framwork.core.sdklib.apm6.a.f8583a, "header==null " + j2);
        }
        return a2;
    }

    private static long c() {
        return (System.currentTimeMillis() << 16) | Process.myPid();
    }

    private String b(long j2, long j3) {
        return j2 + "_" + j3;
    }
}
