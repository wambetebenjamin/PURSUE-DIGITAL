package com.bytedance.applog.monitor.v3.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.log.h;
import com.bytedance.applog.log.o;
import com.bytedance.applog.monitor.v3.d;
import com.bytedance.applog.monitor.v3.e;
import com.bytedance.applog.monitor.v3.i;
import com.bytedance.applog.monitor.v3.j;
import com.bytedance.applog.monitor.v3.l;
import com.bytedance.applog.priority.original.m;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.geckox.b;
import com.ss.android.common.applog.AppLog;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: SentryMonitor.java */
/* loaded from: classes.dex */
public class b implements Handler.Callback, d<a> {

    /* renamed from: b  reason: collision with root package name */
    private static final List<String> f2985b = Collections.singletonList("SentryMonitor");

    /* renamed from: e  reason: collision with root package name */
    private static final int f2986e = 4;

    /* renamed from: f  reason: collision with root package name */
    private static final int f2987f = 8;

    /* renamed from: g  reason: collision with root package name */
    private static final int f2988g = 12;

    /* renamed from: h  reason: collision with root package name */
    private static final long f2989h = 500;

    /* renamed from: d  reason: collision with root package name */
    private Handler f2992d;

    /* renamed from: i  reason: collision with root package name */
    private SharedPreferences f2993i;

    /* renamed from: j  reason: collision with root package name */
    private final e f2994j;

    /* renamed from: a  reason: collision with root package name */
    public final Map<String, a> f2990a = Collections.synchronizedMap(new LinkedHashMap());

    /* renamed from: c  reason: collision with root package name */
    private boolean f2991c = true;
    private boolean k = true;
    private final AtomicBoolean l = new AtomicBoolean(false);

    public b(e eVar) {
        this.f2994j = eVar;
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 4) {
            c();
            return false;
        } else if (i2 == 8) {
            a((a) message.obj);
            return false;
        } else if (i2 != 12) {
            return false;
        } else {
            d();
            return false;
        }
    }

    /* compiled from: SentryMonitor.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private l f2995a;

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.applog.monitor.v3.a f2996b;

        /* renamed from: c  reason: collision with root package name */
        private boolean f2997c = false;

        public a(com.bytedance.applog.monitor.v3.a aVar, l lVar) {
            this.f2996b = aVar;
            this.f2995a = lVar;
        }

        public JSONObject a() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(m.f3283d, this.f2995a.f3017b);
                if (this.f2995a.f3019d != null) {
                    jSONObject.put("event_type", this.f2995a.f3019d.a());
                }
                if (!TextUtils.isEmpty(this.f2995a.f3016a)) {
                    jSONObject.put("event", this.f2995a.f3016a);
                }
                if (this.f2995a.f3018c > 0) {
                    jSONObject.put("local_time_ms", this.f2995a.f3018c);
                }
                if (!TextUtils.isEmpty(this.f2995a.f3020e)) {
                    jSONObject.put("event_priority", this.f2995a.f3020e);
                }
                if (this.f2995a.f3021f >= 0) {
                    jSONObject.put("global_priority", this.f2995a.f3021f);
                }
            } catch (Throwable unused) {
            }
            return jSONObject;
        }

        public long b() {
            l lVar = this.f2995a;
            return (lVar != null ? lVar.f3016a.length() : 0) + 133;
        }

        public String c() {
            return "v3|" + (TextUtils.isEmpty(this.f2995a.f3020e) ? "" : this.f2995a.f3020e) + "|" + this.f2995a.f3021f + "|" + this.f2996b.name() + "|" + this.f2995a.f3016a + "|" + this.f2995a.f3018c + "|" + this.f2995a.f3019d.name();
        }

        public static a a(String str, String str2) {
            if (TextUtils.isEmpty(str2)) {
                return null;
            }
            List<String> a2 = i.a(str2, '|');
            if (a2.isEmpty()) {
                return null;
            }
            if (a2.get(0).equals(b.a.f8888c)) {
                int i2 = -1;
                try {
                    i2 = Integer.parseInt(a2.get(2));
                } catch (Throwable unused) {
                }
                return new a(com.bytedance.applog.monitor.v3.a.valueOf(a2.get(3)), new l(TextUtils.isEmpty(a2.get(1)) ? null : a2.get(1), i2, a2.get(4), str, Long.parseLong(a2.get(5)), com.bytedance.applog.monitor.v3.m.a(a2.get(6))));
            } else if (a2.size() == 4) {
                return new a(com.bytedance.applog.monitor.v3.a.valueOf(a2.get(0)), new l(a2.get(1), str, Long.parseLong(a2.get(2)), com.bytedance.applog.monitor.v3.m.V3_EVENT));
            } else {
                if (a2.size() == 5) {
                    return new a(com.bytedance.applog.monitor.v3.a.valueOf(a2.get(0)), new l(a2.get(1), str, Long.parseLong(a2.get(2)), com.bytedance.applog.monitor.v3.m.a(a2.get(3))));
                }
                return null;
            }
        }
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a() {
        o.a().c(8, f2985b, "init with disabled", new Object[0]);
        this.f2991c = false;
        this.f2990a.clear();
        this.l.set(true);
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public Pair<Map<String, a>, Long> a(long j2) {
        if (this.f2990a.isEmpty()) {
            return new Pair<>(null, Long.valueOf(j2));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        synchronized (this.f2990a) {
            for (Map.Entry<String, a> entry : this.f2990a.entrySet()) {
                a value = entry.getValue();
                linkedHashMap.put(entry.getKey(), value);
                j2 -= value.b();
                if (j2 < 0) {
                    break;
                }
            }
        }
        return new Pair<>(linkedHashMap, Long.valueOf(j2));
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, a> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        SharedPreferences sharedPreferences = this.f2993i;
        SharedPreferences.Editor edit = sharedPreferences != null ? sharedPreferences.edit() : null;
        synchronized (this.f2990a) {
            for (String str : map.keySet()) {
                this.f2990a.remove(str);
                if (edit != null) {
                    edit.remove(str);
                }
            }
        }
        if (edit != null) {
            edit.apply();
        }
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, a> map, JSONArray jSONArray) {
        if (map.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap();
        for (a aVar : map.values()) {
            JSONArray jSONArray2 = (JSONArray) hashMap.get(aVar.f2996b);
            if (jSONArray2 == null) {
                jSONArray2 = new JSONArray();
                hashMap.put(aVar.f2996b, jSONArray2);
            }
            jSONArray2.put(aVar.a());
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", "sdk_stage");
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry entry : hashMap.entrySet()) {
                jSONObject2.put(((com.bytedance.applog.monitor.v3.a) entry.getKey()).toString(), entry.getValue());
            }
            jSONObject.put("params", jSONObject2);
            jSONObject.put("local_time_ms", System.currentTimeMillis());
            jSONObject.put(AppLog.KEY_EVENT_ID, i.b());
            jSONObject.put("datetime", i.d());
        } catch (Throwable unused) {
        }
        jSONArray.put(jSONObject);
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Context context, HandlerThread handlerThread, String str) {
        h a2 = o.a();
        List<String> list = f2985b;
        a2.c(8, list, "init with prefix sp: {}.", str);
        if (!this.l.compareAndSet(false, true)) {
            o.a().e(8, list, "init failed for already initialized with sp prefix:{}.", str);
        } else if (!this.f2994j.h()) {
            o.a().c(8, list, "init but disabled!!!", new Object[0]);
            this.k = false;
            this.f2990a.clear();
            this.f2991c = false;
        } else {
            this.k = j.a(this.f2994j.i(), 10000);
            o.a().c(8, list, "sentry acceptAllEvents = {}", Boolean.valueOf(this.k));
            this.f2993i = context.getSharedPreferences(str + "@applog_monitor_sentry", 0);
            if (!b()) {
                e();
            }
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.f2992d = handler;
            handler.removeMessages(4);
            this.f2992d.sendEmptyMessageDelayed(4, 500L);
            this.f2992d.sendEmptyMessageDelayed(12, Math.max(10000L, this.f2994j.f() - 10000) + new SecureRandom().nextInt(WsConstants.APP_STATE_CHANGE_SERVICE));
        }
    }

    public boolean b() {
        return this.k || this.f2994j.j();
    }

    public void a(String str, int i2, com.bytedance.applog.monitor.v3.a aVar, String str2, String str3, long j2, com.bytedance.applog.monitor.v3.m mVar) {
        if (!this.f2991c || !this.f2994j.h()) {
            o.a().c(8, f2985b, "stage nothing for disabled!!!", new Object[0]);
            return;
        }
        a aVar2 = new a(aVar, new l(str, i2, str2, str3, j2, mVar));
        if (this.f2992d != null && Looper.myLooper() == Looper.getMainLooper()) {
            Handler handler = this.f2992d;
            handler.sendMessage(handler.obtainMessage(8, aVar2));
            return;
        }
        a(aVar2);
    }

    private void a(a aVar) {
        o.a().c(8, f2985b, "start handle stage ({})", aVar);
        synchronized (this.f2990a) {
            if (b(aVar.f2995a.f3016a)) {
                a aVar2 = this.f2990a.get(aVar.f2995a.f3017b);
                if (aVar2 == null) {
                    this.f2990a.put(aVar.f2995a.f3017b, aVar);
                } else {
                    aVar2.f2997c = false;
                    aVar2.f2995a.a(aVar.f2995a);
                    if (aVar.f2996b.ordinal() > aVar2.f2996b.ordinal()) {
                        aVar2.f2996b = aVar.f2996b;
                    }
                }
                Handler handler = this.f2992d;
                if (handler != null) {
                    handler.removeMessages(4);
                    this.f2992d.sendEmptyMessageDelayed(4, 500L);
                }
            }
        }
    }

    private void c() {
        o.a().c(8, f2985b, "start flush cache map (size={}) to sp...", Integer.valueOf(this.f2990a.size()));
        SharedPreferences.Editor edit = this.f2993i.edit();
        synchronized (this.f2990a) {
            for (Map.Entry<String, a> entry : this.f2990a.entrySet()) {
                a value = entry.getValue();
                if (!value.f2997c && b(value.f2995a.f3016a)) {
                    edit.putString(value.f2995a.f3017b, value.c());
                    value.f2997c = true;
                }
            }
        }
        edit.putLong(e.f2918d, System.currentTimeMillis());
        edit.apply();
    }

    private void d() {
        a aVar;
        a a2;
        if (this.f2993i == null) {
            return;
        }
        o.a().c(8, f2985b, "start load cache map from sp...", new Object[0]);
        long j2 = this.f2993i.getLong(e.f2918d, 0L);
        if (j2 > 0 && System.currentTimeMillis() - j2 > TimeUnit.DAYS.toMillis(this.f2994j.g())) {
            this.f2993i.edit().clear().apply();
            return;
        }
        Map<String, ?> all = this.f2993i.getAll();
        if (all.isEmpty()) {
            return;
        }
        HashMap hashMap = new HashMap(all.size() * 2);
        for (Map.Entry<String, ?> entry : all.entrySet()) {
            String key = entry.getKey();
            if (!TextUtils.isEmpty(key) && !e.f2918d.equals(key) && (a2 = a.a(key, (String) entry.getValue())) != null) {
                a2.f2997c = true;
                hashMap.put(key, a2);
            }
        }
        synchronized (this.f2990a) {
            for (Map.Entry entry2 : hashMap.entrySet()) {
                String str = (String) entry2.getKey();
                a aVar2 = (a) entry2.getValue();
                if (this.f2990a.containsKey(entry2.getKey()) && (aVar = this.f2990a.get(entry2.getKey())) != null) {
                    aVar2.f2997c = false;
                    aVar2.f2995a.a(aVar.f2995a);
                }
                this.f2990a.put(str, aVar2);
            }
        }
    }

    public boolean a(String str) {
        Integer num;
        if (b() || !this.l.get()) {
            return true;
        }
        if (b(str) && (num = this.f2994j.k().get(str)) != null && num.intValue() > 0) {
            return j.a(num.intValue(), 10000);
        }
        return false;
    }

    public boolean b(String str) {
        if (b()) {
            return true;
        }
        if (this.f2994j.k() == null) {
            return false;
        }
        return this.f2994j.k().containsKey(str);
    }

    private void e() {
        if (this.f2990a.isEmpty()) {
            return;
        }
        o.a().c(8, f2985b, "start reset cache map...", new Object[0]);
        if (!b() && (this.f2994j.k() == null || this.f2994j.k().isEmpty())) {
            this.f2990a.clear();
            return;
        }
        synchronized (this.f2990a) {
            for (Map.Entry<String, a> entry : this.f2990a.entrySet()) {
                if (!b(entry.getValue().f2995a.f3016a)) {
                    this.f2990a.remove(entry.getKey());
                }
            }
        }
    }
}
