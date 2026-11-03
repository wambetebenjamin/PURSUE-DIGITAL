package com.bytedance.applog.monitor.v3.impl;

import android.content.Context;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.monitor.v3.d;
import com.bytedance.applog.monitor.v3.e;
import com.bytedance.applog.monitor.v3.i;
import com.bytedance.applog.monitor.v3.j;
import com.ss.android.common.applog.AppLog;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: ErrorMonitor.java */
/* loaded from: classes.dex */
public class a implements d<JSONObject> {

    /* renamed from: a  reason: collision with root package name */
    private final List<C0039a> f2978a = new CopyOnWriteArrayList();

    /* renamed from: b  reason: collision with root package name */
    private int f2979b = 10000;

    /* renamed from: c  reason: collision with root package name */
    private final int f2980c = 100;

    /* renamed from: d  reason: collision with root package name */
    private e f2981d;

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Context context, HandlerThread handlerThread, String str) {
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, JSONObject> map) {
    }

    public a(e eVar) {
        this.f2981d = eVar;
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a() {
        this.f2979b = 0;
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public Pair<Map<String, JSONObject>, Long> a(long j2) {
        if (this.f2979b <= 0) {
            return new Pair<>(null, Long.valueOf(j2));
        }
        HashMap hashMap = new HashMap();
        int i2 = 0;
        for (C0039a c0039a : this.f2978a) {
            int i3 = i2 + 1;
            hashMap.put(String.valueOf(i2), c0039a.a());
            this.f2978a.remove(c0039a);
            j2 -= c0039a.b();
            if (j2 <= 0) {
                break;
            }
            i2 = i3;
        }
        return new Pair<>(hashMap, Long.valueOf(j2));
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, JSONObject> map, JSONArray jSONArray) {
        for (JSONObject jSONObject : map.values()) {
            jSONArray.put(jSONObject);
        }
    }

    /* compiled from: ErrorMonitor.java */
    /* renamed from: com.bytedance.applog.monitor.v3.impl.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    private static class C0039a {

        /* renamed from: a  reason: collision with root package name */
        private String f2982a;

        /* renamed from: b  reason: collision with root package name */
        private String f2983b;

        /* renamed from: c  reason: collision with root package name */
        private String f2984c;

        public C0039a(String str, String str2, String str3) {
            this.f2982a = str;
            this.f2983b = str2;
            this.f2984c = str3;
        }

        public C0039a(String str) {
            this.f2982a = str;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("event", "sdk_fatal");
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("message", this.f2982a);
                if (!TextUtils.isEmpty(this.f2983b)) {
                    jSONObject2.put("class", this.f2983b);
                    jSONObject2.put("stack", this.f2984c);
                }
                jSONObject.put("params", jSONObject2);
                jSONObject.put("local_time_ms", System.currentTimeMillis());
                jSONObject.put(AppLog.KEY_EVENT_ID, i.b());
                jSONObject.put("datetime", i.d());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return jSONObject;
        }

        public long b() {
            int length;
            if (TextUtils.isEmpty(this.f2983b)) {
                length = this.f2982a.length() + 43;
            } else {
                length = this.f2982a.length() + 65 + this.f2983b.length() + this.f2984c.length();
            }
            return length;
        }
    }

    public void a(String str, Throwable th) {
        if (j.a(this.f2979b, 10000)) {
            if (th != null) {
                this.f2978a.add(new C0039a(str, th.getClass().getCanonicalName(), a(th)));
            } else {
                this.f2978a.add(new C0039a(str));
            }
            if (this.f2978a.size() > 100) {
                this.f2978a.remove(0);
            }
        }
    }

    private String a(Throwable th) {
        StringBuilder sb = new StringBuilder();
        while (th != null) {
            a(sb, th);
            th = th.getCause();
        }
        String replaceAll = sb.toString().replaceAll("[\\s|\\n]", "");
        return replaceAll.length() <= 200 ? replaceAll : replaceAll.substring(0, 200);
    }

    private void a(StringBuilder sb, Throwable th) {
        sb.append(th.toString());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\n\tat ").append(stackTraceElement);
        }
    }
}
