package com.bytedance.applog.monitor.exception;

import com.bytedance.applog.log.o;
import com.bytedance.applog.monitor.exception.f;
import com.bytedance.applog.monitor.v3.m;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
/* compiled from: AppLogExceptionManager.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final b f2833a = new b();

    /* renamed from: b  reason: collision with root package name */
    private boolean f2834b = false;

    /* renamed from: c  reason: collision with root package name */
    private a f2835c;

    /* renamed from: d  reason: collision with root package name */
    private f f2836d;

    public static b a() {
        return f2833a;
    }

    public void a(a aVar) {
        this.f2835c = aVar;
    }

    public void a(m mVar, JSONArray jSONArray, long j2) {
        if (b()) {
            HashMap hashMap = new HashMap();
            hashMap.put(com.bytedance.applog.forward.b.f2687d, jSONArray);
            a(d.DataPackaging, e.OutOfDataBoundary, c.Fatal, mVar + " out of data boundary, pack limit length: " + j2, hashMap);
        }
    }

    public void a(m mVar, String str, String str2, long j2) {
        if (b()) {
            HashMap hashMap = new HashMap();
            hashMap.put("tag", str);
            hashMap.put("params", str2);
            a(d.DataCollection, e.OutOfDataBoundary, c.Fatal, mVar + " out of data boundary, event limit length: " + j2, hashMap);
        }
    }

    public void a(d dVar) {
        if (b()) {
            a(dVar, e.InterruptionTouristMode, c.Notice, "report interrupted due to tourist mode.", null);
        }
    }

    public void b(d dVar) {
        if (b()) {
            a(dVar, e.InterruptionInvalidDid, c.Notice, "report interrupted due to invalid did.", null);
        }
    }

    public void a(d dVar, e eVar, c cVar, String str, Map<String, ?> map) {
        a aVar;
        if (b() && (aVar = this.f2835c) != null) {
            try {
                aVar.a(dVar, eVar, cVar, str, map);
            } catch (Throwable th) {
                o.a().a("AppLogExceptionHandler onException failed, ", th, new Object[0]);
            }
        }
    }

    public void a(String str) {
        if (b()) {
            a(d.DataCollection, e.CacheOverflow, c.Fatal, str, null);
        }
    }

    public void a(String str, Throwable th) {
        if (b()) {
            HashMap hashMap = new HashMap();
            hashMap.put("throwable", th);
            a(d.Common, e.CommonError, c.Fatal, str, hashMap);
        }
    }

    public void a(e eVar, String str) {
        if (b()) {
            a(d.DataCollection, eVar, c.Notice, str, null);
        }
    }

    public void a(long j2, long j3) {
        if (j2 > 0 && j3 > 0) {
            this.f2836d = new f(j2, j3, new f.b() { // from class: com.bytedance.applog.monitor.exception.b.1
                @Override // com.bytedance.applog.monitor.exception.f.b
                public void a(Map<String, Integer> map) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(com.bytedance.applog.forward.b.f2687d, map);
                    b.this.a(d.DataCollection, e.HighFrequency, c.Fatal, "high frequency events detected", hashMap);
                }
            });
        } else {
            this.f2836d = null;
        }
    }

    public void a(m mVar, String str, long j2) {
        f fVar;
        if (b() && (fVar = this.f2836d) != null) {
            try {
                fVar.a(mVar, str, j2);
            } catch (Throwable unused) {
            }
        }
    }

    public void a(boolean z) {
        this.f2834b = z;
    }

    public boolean b() {
        return this.f2834b;
    }
}
