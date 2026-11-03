package com.bytedance.applog.monitor.v3.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.log.o;
import com.bytedance.applog.monitor.v3.d;
import com.bytedance.applog.monitor.v3.e;
import com.bytedance.applog.monitor.v3.i;
import com.bytedance.common.wschannel.WsConstants;
import com.ss.android.common.applog.AppLog;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: StatsMonitor.java */
/* loaded from: classes.dex */
public class c implements Handler.Callback, d<Integer> {

    /* renamed from: a  reason: collision with root package name */
    private static final List<String> f2998a = Collections.singletonList("StatsMonitor");

    /* renamed from: f  reason: collision with root package name */
    private static final int f2999f = 8;

    /* renamed from: g  reason: collision with root package name */
    private static final int f3000g = 16;

    /* renamed from: h  reason: collision with root package name */
    private static final int f3001h = 24;

    /* renamed from: i  reason: collision with root package name */
    private static final long f3002i = 500;

    /* renamed from: c  reason: collision with root package name */
    private Handler f3004c;

    /* renamed from: e  reason: collision with root package name */
    private SharedPreferences f3006e;

    /* renamed from: j  reason: collision with root package name */
    private final e f3007j;

    /* renamed from: b  reason: collision with root package name */
    private boolean f3003b = true;

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, Integer> f3005d = new ConcurrentHashMap();

    public c(e eVar) {
        this.f3007j = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StatsMonitor.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f3008a;

        /* renamed from: b  reason: collision with root package name */
        private final int f3009b;

        public a(String str, int i2) {
            this.f3008a = str;
            this.f3009b = i2;
        }
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Context context, HandlerThread handlerThread, String str) {
        this.f3006e = context.getSharedPreferences(str + "@applog_monitor_stats", 0);
        Handler handler = new Handler(handlerThread.getLooper(), this);
        this.f3004c = handler;
        handler.removeMessages(8);
        this.f3004c.sendEmptyMessageDelayed(8, 500L);
        this.f3004c.sendEmptyMessageDelayed(24, Math.max(10000L, this.f3007j.f() - 10000) + new SecureRandom().nextInt(WsConstants.APP_STATE_CHANGE_SERVICE));
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a() {
        this.f3003b = false;
        this.f3005d.clear();
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public Pair<Map<String, Integer>, Long> a(long j2) {
        if (this.f3005d.isEmpty()) {
            return new Pair<>(null, Long.valueOf(j2));
        }
        return new Pair<>(new HashMap(this.f3005d), Long.valueOf(j2 - (this.f3005d.size() * 30)));
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, Integer> map) {
        if (map == null || map.isEmpty()) {
            return;
        }
        synchronized (this.f3005d) {
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                String key = entry.getKey();
                int intValue = entry.getValue() != null ? entry.getValue().intValue() : 0;
                Integer num = this.f3005d.get(key);
                if (num != null) {
                    int max = Math.max(0, num.intValue() - intValue);
                    if (max == 0) {
                        this.f3005d.remove(key);
                    } else {
                        this.f3005d.put(key, Integer.valueOf(max));
                    }
                }
            }
        }
        this.f3004c.removeMessages(8);
        this.f3004c.sendEmptyMessageDelayed(8, 500L);
    }

    @Override // com.bytedance.applog.monitor.v3.d
    public void a(Map<String, Integer> map, JSONArray jSONArray) {
        if (map.isEmpty()) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("event", "sdk_stats");
            JSONObject jSONObject2 = new JSONObject();
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                Integer value = entry.getValue();
                if (value != null && value.intValue() > 0) {
                    jSONObject2.put(entry.getKey(), value);
                }
            }
            jSONObject.put("params", jSONObject2);
            jSONObject.put("local_time_ms", System.currentTimeMillis());
            jSONObject.put(AppLog.KEY_EVENT_ID, i.b());
            jSONObject.put("datetime", i.d());
        } catch (Throwable th) {
            o.a().a(8, f2998a, "appendMapToJSONArray failed", th, new Object[0]);
        }
        jSONArray.put(jSONObject);
    }

    public void a(String str, int i2) {
        if (this.f3003b) {
            a aVar = new a(str, i2);
            Handler handler = this.f3004c;
            if (handler != null) {
                handler.sendMessageDelayed(handler.obtainMessage(16, aVar), 16L);
            } else {
                a(aVar);
            }
        }
    }

    public Map<String, Integer> b() {
        return new HashMap(this.f3005d);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i2 = message.what;
        if (i2 == 8) {
            d();
            return false;
        } else if (i2 == 16) {
            a((a) message.obj);
            return false;
        } else if (i2 != 24) {
            return false;
        } else {
            c();
            return false;
        }
    }

    private void a(a aVar) {
        synchronized (this.f3005d) {
            Integer num = this.f3005d.get(aVar.f3008a);
            this.f3005d.put(aVar.f3008a, Integer.valueOf((num != null ? num.intValue() : 0) + aVar.f3009b));
        }
        Handler handler = this.f3004c;
        if (handler != null) {
            handler.removeMessages(8);
            this.f3004c.sendEmptyMessageDelayed(8, 500L);
        }
    }

    private void c() {
        SharedPreferences sharedPreferences = this.f3006e;
        if (sharedPreferences == null) {
            return;
        }
        long j2 = sharedPreferences.getLong(e.f2918d, 0L);
        if (j2 > 0 && System.currentTimeMillis() - j2 > TimeUnit.DAYS.toMillis(this.f3007j.g())) {
            this.f3006e.edit().clear().apply();
            return;
        }
        Map<String, ?> all = this.f3006e.getAll();
        if (all.isEmpty()) {
            return;
        }
        synchronized (this.f3005d) {
            for (Map.Entry<String, ?> entry : all.entrySet()) {
                String key = entry.getKey();
                if (!TextUtils.isEmpty(key) && !e.f2918d.equals(entry.getKey())) {
                    int intValue = entry.getValue() != null ? ((Integer) entry.getValue()).intValue() : 0;
                    Integer num = this.f3005d.get(key);
                    if (num == null) {
                        this.f3005d.put(key, Integer.valueOf(intValue));
                    } else {
                        this.f3005d.put(key, Integer.valueOf(intValue + num.intValue()));
                    }
                }
            }
        }
        Handler handler = this.f3004c;
        if (handler != null) {
            handler.removeMessages(8);
            this.f3004c.sendEmptyMessageDelayed(8, 500L);
        }
    }

    private void d() {
        SharedPreferences.Editor edit = this.f3006e.edit();
        edit.clear();
        synchronized (this.f3005d) {
            for (Map.Entry<String, Integer> entry : this.f3005d.entrySet()) {
                edit.putInt(entry.getKey(), entry.getValue() != null ? entry.getValue().intValue() : 0);
            }
        }
        edit.putLong(e.f2918d, System.currentTimeMillis());
        edit.apply();
    }
}
