package com.bytedance.frameworks.baselib.network.http;

import com.bytedance.frameworks.baselib.network.http.b;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: RotationHostRetryHandler.java */
/* loaded from: classes2.dex */
public class l {

    /* renamed from: a  reason: collision with root package name */
    private final List<b.c> f7940a;

    /* renamed from: b  reason: collision with root package name */
    private int f7941b;

    /* renamed from: c  reason: collision with root package name */
    private final List<a> f7942c;

    /* compiled from: RotationHostRetryHandler.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f7943a;

        /* renamed from: b  reason: collision with root package name */
        public String f7944b;

        /* renamed from: c  reason: collision with root package name */
        public int f7945c;

        /* renamed from: d  reason: collision with root package name */
        public long f7946d;

        /* renamed from: e  reason: collision with root package name */
        public long f7947e;

        /* renamed from: f  reason: collision with root package name */
        public boolean f7948f;

        public a(String str, String str2, int i2, long j2, long j3, boolean z) {
            this.f7943a = str;
            this.f7944b = str2;
            this.f7945c = i2;
            this.f7946d = j2;
            this.f7947e = j3;
            this.f7948f = z;
        }
    }

    public l(List<b.c> list) {
        ArrayList arrayList = new ArrayList();
        this.f7940a = arrayList;
        this.f7941b = 0;
        this.f7942c = new CopyOnWriteArrayList();
        if (list == null || list.isEmpty()) {
            return;
        }
        arrayList.addAll(list);
    }

    public boolean a() {
        return this.f7940a.isEmpty() || this.f7941b >= this.f7940a.size();
    }

    public b.c b() {
        if (this.f7941b >= this.f7940a.size()) {
            return null;
        }
        List<b.c> list = this.f7940a;
        int i2 = this.f7941b;
        this.f7941b = i2 + 1;
        return list.get(i2);
    }

    public void a(String str, String str2, int i2, long j2, g gVar, com.bytedance.frameworks.baselib.network.http.a aVar) {
        this.f7942c.add(new a(str, str2, i2, j2, System.currentTimeMillis() - (aVar.aq > 0 ? aVar.aq : aVar.l), gVar != null && gVar.a()));
        if (aVar.Y != null) {
            aVar.Y.setRotationHostRetryInfo(c());
        }
    }

    public JSONObject c() {
        if (this.f7942c.isEmpty()) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (a aVar : this.f7942c) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("callback_duration", aVar.f7946d);
                jSONObject2.put("request_duration", aVar.f7947e);
                jSONObject2.put("code", aVar.f7945c);
                jSONObject.put(aVar.f7943a + aVar.f7944b, jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public l d() {
        ArrayList arrayList = new ArrayList();
        for (b.c cVar : this.f7940a) {
            arrayList.add(cVar.clone());
        }
        l lVar = new l(arrayList);
        lVar.f7941b = this.f7941b;
        for (a aVar : this.f7942c) {
            lVar.f7942c.add(new a(aVar.f7943a, aVar.f7944b, aVar.f7945c, aVar.f7946d, aVar.f7947e, aVar.f7948f));
        }
        return lVar;
    }
}
