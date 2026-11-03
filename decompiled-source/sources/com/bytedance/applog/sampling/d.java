package com.bytedance.applog.sampling;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: EventSampling.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3358a = "event_sampling";

    /* renamed from: b  reason: collision with root package name */
    public static final String f3359b = "capability";

    /* renamed from: c  reason: collision with root package name */
    public static final String f3360c = "version";

    /* renamed from: d  reason: collision with root package name */
    public static final String f3361d = "config_ids";

    /* renamed from: e  reason: collision with root package name */
    public static final String f3362e = "log_data";

    /* renamed from: f  reason: collision with root package name */
    public static final int f3363f = 1;

    /* renamed from: g  reason: collision with root package name */
    public static final int f3364g = 2;

    /* renamed from: h  reason: collision with root package name */
    public static final int f3365h = 3;

    /* renamed from: i  reason: collision with root package name */
    public static final int f3366i = 4;

    /* renamed from: j  reason: collision with root package name */
    public static final int f3367j = 6;
    public static final int k = 1;
    public static final int l = 2;
    private int m;
    private int n;
    private JSONArray o;
    private JSONArray p;
    private final List<a> q = new ArrayList();

    public int a() {
        return this.m;
    }

    public void a(int i2) {
        this.m = i2;
    }

    public void b(int i2) {
        this.n = i2;
    }

    public int b() {
        return this.n;
    }

    public void a(JSONArray jSONArray) {
        this.p = jSONArray;
    }

    public JSONArray c() {
        return this.p;
    }

    public void b(JSONArray jSONArray) {
        this.o = jSONArray;
    }

    public JSONArray d() {
        return this.o;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031  */
    /* JADX WARN: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(int r2, int r3, java.util.Set<java.lang.String> r4, java.util.Map<java.lang.String, java.util.List<java.util.Set<java.lang.String>>> r5, java.util.Map<java.lang.String, java.util.List<java.util.Map<java.lang.String, java.lang.Object>>> r6) {
        /*
            r1 = this;
            r0 = 1
            if (r3 == r0) goto L29
            r0 = 2
            if (r3 == r0) goto L23
            r0 = 3
            if (r3 == r0) goto L1d
            r0 = 4
            if (r3 == r0) goto L17
            r0 = 6
            if (r3 == r0) goto L11
            r2 = 0
            goto L2f
        L11:
            com.bytedance.applog.sampling.j r3 = new com.bytedance.applog.sampling.j
            r3.<init>(r2, r4, r5, r6)
            goto L2e
        L17:
            com.bytedance.applog.sampling.i r3 = new com.bytedance.applog.sampling.i
            r3.<init>(r2, r4, r5, r6)
            goto L2e
        L1d:
            com.bytedance.applog.sampling.g r3 = new com.bytedance.applog.sampling.g
            r3.<init>(r2, r4, r5, r6)
            goto L2e
        L23:
            com.bytedance.applog.sampling.f r3 = new com.bytedance.applog.sampling.f
            r3.<init>(r2, r4, r5, r6)
            goto L2e
        L29:
            com.bytedance.applog.sampling.h r3 = new com.bytedance.applog.sampling.h
            r3.<init>(r2, r4, r5, r6)
        L2e:
            r2 = r3
        L2f:
            if (r2 == 0) goto L36
            java.util.List<com.bytedance.applog.sampling.a> r3 = r1.q
            r3.add(r2)
        L36:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.sampling.d.a(int, int, java.util.Set, java.util.Map, java.util.Map):void");
    }

    public boolean a(b bVar, String str) {
        return a(bVar, str, "");
    }

    public boolean a(b bVar, String str, String str2) {
        for (a aVar : this.q) {
            if (aVar.a(str, str2)) {
                return aVar.a(bVar);
            }
        }
        return false;
    }

    public boolean a(b bVar, String str, JSONObject jSONObject) {
        for (a aVar : this.q) {
            if (aVar.a(str, jSONObject)) {
                return aVar.a(bVar);
            }
        }
        return false;
    }

    public boolean b(b bVar, String str, JSONObject jSONObject) {
        for (a aVar : this.q) {
            if (aVar.a(str, jSONObject)) {
                if (aVar instanceof h) {
                    return false;
                }
                return aVar.a(bVar);
            }
        }
        return false;
    }

    public boolean e() {
        return !this.q.isEmpty();
    }
}
