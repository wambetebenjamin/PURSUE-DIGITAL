package com.bytedance.apm6.consumer.slardar.send;

import android.content.SharedPreferences;
import android.text.TextUtils;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: DropDataMonitor.java */
/* loaded from: classes.dex */
class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1848a = "drop_data_count";

    /* renamed from: b  reason: collision with root package name */
    private static final String f1849b = "drop_data_bytes";

    /* renamed from: c  reason: collision with root package name */
    private static final String f1850c = "x-tt-logid";

    /* renamed from: d  reason: collision with root package name */
    private static final String f1851d = "timestamp";

    /* renamed from: e  reason: collision with root package name */
    private static final String f1852e = "drop_timestamp";

    /* renamed from: f  reason: collision with root package name */
    private static final String f1853f = "drop_reason";

    /* renamed from: g  reason: collision with root package name */
    private static final String f1854g = "server_drop_data";

    /* renamed from: h  reason: collision with root package name */
    private static final String f1855h = "log_type";

    /* renamed from: i  reason: collision with root package name */
    private static final String f1856i = "drop_message";

    /* renamed from: j  reason: collision with root package name */
    private static final String f1857j = "drop_data_items";
    private static final int k = 10;
    private SharedPreferences l;
    private JSONArray m = new JSONArray();
    private volatile boolean n;
    private String o;
    private long p;

    b() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static b a() {
        return a.f1858a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.o = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.p = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2, long j3, long j4, EnumC0028b enumC0028b) {
        c();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("log_type", f1854g);
            jSONObject.put("timestamp", j4);
            jSONObject.put(f1848a, j2);
            jSONObject.put(f1849b, j3);
            if (enumC0028b == EnumC0028b.SERVER_DROP) {
                jSONObject.put("x-tt-logid", this.o);
                jSONObject.put(f1852e, this.p);
            }
            if (enumC0028b != null) {
                jSONObject.put(f1853f, enumC0028b);
            }
            this.m.put(jSONObject);
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "monitorDropLog:" + this.m.toString());
            }
            this.l.edit().putString(f1857j, this.m.toString()).commit();
        } catch (Exception e2) {
            com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.consumer.slardar.b.TAG, "monitorDropLog:", e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONArray b() {
        c();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < this.m.length(); i2++) {
            try {
                if (i2 < 10) {
                    jSONArray.put(this.m.get(i2));
                } else {
                    jSONArray2.put(this.m.get(i2));
                }
            } catch (Exception unused) {
            }
        }
        this.m = jSONArray2;
        this.l.edit().putString(f1857j, this.m.toString()).commit();
        return jSONArray;
    }

    private synchronized void c() {
        if (this.n) {
            return;
        }
        this.n = true;
        SharedPreferences sharedPreferences = com.bytedance.apm6.foundation.context.a.getContext().getSharedPreferences(com.bytedance.apm6.foundation.context.a.getCurrentProcessName() + "_" + f1856i, 0);
        this.l = sharedPreferences;
        String string = sharedPreferences.getString(f1857j, "");
        if (!TextUtils.isEmpty(string)) {
            try {
                this.m = new JSONArray(string);
            } catch (Exception unused) {
            }
        }
    }

    /* compiled from: DropDataMonitor.java */
    /* loaded from: classes.dex */
    private static final class a {

        /* renamed from: a  reason: collision with root package name */
        private static final b f1858a = new b();

        private a() {
        }
    }

    /* compiled from: DropDataMonitor.java */
    /* renamed from: com.bytedance.apm6.consumer.slardar.send.b$b  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    enum EnumC0028b {
        SERVER_DROP("server drop message"),
        FULL_DISK("disk is full");
        

        /* renamed from: a  reason: collision with root package name */
        private String f1860a;

        EnumC0028b(String str) {
            this.f1860a = str;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.f1860a;
        }
    }
}
