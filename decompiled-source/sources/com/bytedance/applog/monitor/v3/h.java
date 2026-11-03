package com.bytedance.applog.monitor.v3;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.applog.log.o;
import com.bytedance.applog.monitor.v3.impl.b;
import com.bytedance.bdinstall.ai;
import com.bytedance.common.wschannel.WsConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: MonitorV3Helper.java */
/* loaded from: classes.dex */
public class h implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2960a = 1;

    /* renamed from: b  reason: collision with root package name */
    public static final int f2961b = 2;

    /* renamed from: c  reason: collision with root package name */
    private static final List<String> f2962c = Collections.singletonList("MonitorV3Helper");
    private static final String k = UUID.randomUUID().toString();
    private static int n = 3590;
    private static final int o = 1;

    /* renamed from: d  reason: collision with root package name */
    private final e f2963d;

    /* renamed from: e  reason: collision with root package name */
    private final com.bytedance.applog.monitor.v3.impl.a f2964e;

    /* renamed from: f  reason: collision with root package name */
    private final com.bytedance.applog.monitor.v3.impl.b f2965f;

    /* renamed from: g  reason: collision with root package name */
    private final com.bytedance.applog.monitor.v3.impl.c f2966g;

    /* renamed from: h  reason: collision with root package name */
    private final JSONObject f2967h;

    /* renamed from: i  reason: collision with root package name */
    private Handler f2968i;

    /* renamed from: j  reason: collision with root package name */
    private g f2969j;
    private final AtomicBoolean l;
    private final JSONObject m;

    public static void a(int i2) {
        if (i2 > 0) {
            n = i2;
        }
    }

    public h() {
        e eVar = new e();
        this.f2963d = eVar;
        this.f2964e = new com.bytedance.applog.monitor.v3.impl.a(eVar);
        this.f2965f = new com.bytedance.applog.monitor.v3.impl.b(eVar);
        this.f2966g = new com.bytedance.applog.monitor.v3.impl.c(eVar);
        this.f2967h = new JSONObject();
        this.l = new AtomicBoolean(false);
        this.m = new JSONObject();
        c("cold_start_id", k);
    }

    public e a() {
        return this.f2963d;
    }

    public void a(String str, String str2) {
        if (c()) {
            o.a().c(8, f2962c, "setHeader {} = {}", str, str2);
            try {
                this.f2967h.put(str, str2);
            } catch (Throwable unused) {
            }
        }
    }

    public void a(String str, boolean z) {
        if (c()) {
            o.a().c(8, f2962c, "setFeature boolean {} = {}", str, Boolean.valueOf(z));
            synchronized (this.m) {
                try {
                    this.m.put(str, z ? 1 : 0);
                }
            }
        }
    }

    public void b(String str, String str2) {
        if (c()) {
            o.a().c(8, f2962c, "setFeature string {} = {}", str, str2);
            synchronized (this.m) {
                try {
                    this.m.put(str, str2);
                }
            }
        }
    }

    public void a(String str, JSONObject jSONObject) {
        if (c()) {
            o.a().c(8, f2962c, "setFeature json {} = {}", str, jSONObject);
            synchronized (this.m) {
                try {
                    this.m.put(str, jSONObject);
                }
            }
        }
    }

    public void a(String str, long j2) {
        if (c()) {
            o.a().c(8, f2962c, "setFeature int {} = {}", str, Long.valueOf(j2));
            synchronized (this.m) {
                try {
                    this.m.put(str, j2);
                }
            }
        }
    }

    public void a(n nVar) {
        a(nVar, 1);
    }

    public void a(String str) {
        a(str, 1);
    }

    public void a(n nVar, int i2) {
        if (c()) {
            o.a().c(8, f2962c, "increaseStats {} +{}", nVar, Integer.valueOf(i2));
            this.f2966g.a(nVar.name(), i2);
        }
    }

    public void a(String str, int i2) {
        if (c()) {
            o.a().c(8, f2962c, "increaseStats {} +{}", str, Integer.valueOf(i2));
            this.f2966g.a(str, i2);
        }
    }

    public boolean b(String str) {
        if (d()) {
            return this.f2965f.a(str);
        }
        return false;
    }

    public boolean c(String str) {
        if (d()) {
            return this.f2965f.b(str);
        }
        return false;
    }

    public void a(a aVar, String str, String str2, long j2, m mVar) {
        a(null, -1, aVar, str, str2, j2, mVar);
    }

    public void a(String str, int i2, a aVar, String str2, String str3, long j2, m mVar) {
        if (d()) {
            o.a().c(8, f2962c, "stageEvent {} -> event: {}, monitor_id: {}, ts: {}, type: {}, priority: {}-{}", aVar, str2, str3, Long.valueOf(j2), mVar, str, Integer.valueOf(i2));
            this.f2965f.a(str, i2, aVar, str2, str3, j2, mVar);
        }
    }

    public void a(String str, Throwable th) {
        a(str, th, true);
    }

    public void a(String str, Throwable th, boolean z) {
        if (g()) {
            o.a().c(8, f2962c, "sendError {} -> {}", str, th);
            this.f2964e.a(str, th);
            if (z) {
                com.bytedance.applog.monitor.exception.b.a().a(str, th);
            }
        }
    }

    public void a(g gVar) {
        o.a().c(8, f2962c, "init monitor v3 helper with config: {}", gVar);
        if (this.l.compareAndSet(false, true)) {
            this.f2969j = gVar;
            f b2 = gVar.b();
            if (!this.f2963d.a() || gVar.a() == null || b2 == null) {
                this.f2964e.a();
                this.f2965f.a();
                this.f2966g.a();
                return;
            }
            c("aid", String.valueOf(n));
            c(com.bytedance.framwork.core.sdkmonitor.h.HOST_APP_ID, b2.a());
            c("channel", b2.b());
            c("cdid", b2.k());
            c("sdk_version_v2", b2.j());
            c("os", com.bytedance.bdturing.utils.b.f4370d);
            c("os_version", Build.VERSION.RELEASE);
            c("app_name", b2.d());
            c("app_version", b2.c());
            c("update_version_code", String.valueOf(b2.e()));
            if (b2.f()) {
                a("log_enable", 1L);
            }
            if (b2.l()) {
                a("devtools_enable", 1L);
            }
            if (b2.g()) {
                a("tourist_mode", 1L);
            }
            if (b2.h()) {
                a("frequency_control_enable", 1L);
            }
            if (b2.i()) {
                a("log_event_filter_enable", 1L);
            }
            if (e()) {
                a("debug_mode", 1L);
            }
            a("report_interval", gVar.c());
            String a2 = b2.a();
            this.f2963d.a(gVar.a(), a2);
            HandlerThread handlerThread = new HandlerThread(b2.a() + "@applog_monitor_v3");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper(), this);
            this.f2968i = handler;
            handler.sendEmptyMessageDelayed(1, this.f2963d.f());
            this.f2964e.a(gVar.a(), handlerThread, a2);
            this.f2965f.a(gVar.a(), handlerThread, a2);
            this.f2966g.a(gVar.a(), handlerThread, a2);
        }
    }

    public void b() {
        if (this.f2968i == null) {
            return;
        }
        o.a().c(8, f2962c, "monitor flush...", new Object[0]);
        this.f2968i.removeMessages(1);
        this.f2968i.sendEmptyMessageDelayed(1, 500L);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what != 1) {
            return false;
        }
        i();
        return false;
    }

    public void c(String str, String str2) {
        if (c()) {
            o.a().c(8, f2962c, "setStringHeader {}: {}", str, str2);
            try {
                this.f2967h.put(str, str2);
            } catch (Throwable unused) {
            }
        }
    }

    public boolean c() {
        return this.f2963d.a();
    }

    public boolean d() {
        return c() && this.f2963d.h();
    }

    public boolean e() {
        return this.f2963d.j();
    }

    public boolean f() {
        return d() && this.f2965f.b();
    }

    public boolean g() {
        return c() && this.f2963d.d();
    }

    private void i() {
        g gVar;
        if (!c() || (gVar = this.f2969j) == null || gVar.d() == null || this.f2969j.f() == null || TextUtils.isEmpty(this.f2969j.e())) {
            return;
        }
        o.a().c(8, f2962c, "start doReport...", new Object[0]);
        final long b2 = this.f2963d.b();
        final int c2 = this.f2963d.c();
        final ai d2 = this.f2969j.d();
        final String e2 = this.f2969j.e();
        this.f2969j.f().submit(new Runnable() { // from class: com.bytedance.applog.monitor.v3.h.1
            @Override // java.lang.Runnable
            public void run() {
                JSONObject jSONObject;
                JSONArray jSONArray;
                Pair<Map<String, b.a>, Long> a2;
                Pair<Map<String, Integer>, Long> a3;
                Pair<Map<String, JSONObject>, Long> a4;
                JSONObject a5;
                int i2 = 0;
                for (int i3 = 0; i3 < c2; i3++) {
                    if (i2 >= 2) {
                        o.a().c(8, h.f2962c, "report failed and break with try 2 times: {}.", new Object[0]);
                        h.this.a("monitor_report_failed_2_tries");
                        return;
                    }
                    try {
                        Thread.sleep(WsConstants.EXIT_DELAY_TIME);
                    } catch (InterruptedException unused) {
                    }
                    long j2 = b2;
                    try {
                        jSONObject = new JSONObject();
                        c g2 = h.this.f2969j.g();
                        if (g2 != null && (a5 = g2.a()) != null && a5.length() > 0) {
                            Iterator<String> keys = a5.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                h.this.f2967h.put(next, a5.opt(next));
                            }
                        }
                        h.this.f2967h.put("feature_options", h.this.m);
                        jSONObject.put("header", h.this.f2967h);
                        jSONArray = new JSONArray();
                        a2 = h.this.f2965f.a(j2);
                        if (a2.first != null) {
                            h.this.f2965f.a((Map) a2.first, jSONArray);
                            j2 = ((Long) a2.second).longValue();
                        }
                        a3 = h.this.f2966g.a(j2);
                        if (a3.first != null) {
                            h.this.f2966g.a((Map) a3.first, jSONArray);
                            j2 = ((Long) a3.second).longValue();
                        }
                        a4 = h.this.f2964e.a(j2);
                        if (a4.first != null) {
                            h.this.f2964e.a((Map) a4.first, jSONArray);
                            j2 = ((Long) a4.second).longValue();
                        }
                    } catch (Throwable th) {
                        o.a().a(8, h.f2962c, "report to server failed!", th, new Object[0]);
                    }
                    if (jSONArray.length() == 0) {
                        o.a().c(8, h.f2962c, "report break for no any event!", new Object[0]);
                        return;
                    }
                    jSONObject.put("event_v3", jSONArray);
                    byte[] a6 = com.bytedance.applog.compress.c.a(jSONObject.toString().getBytes("UTF-8"));
                    HashMap hashMap = new HashMap();
                    hashMap.put("log-encode-type", "gzip");
                    hashMap.put("Content-Encoding", "gzip");
                    hashMap.put("Content-Type", "application/octet-stream;tt-data=b");
                    String post = d2.post(e2, a6, hashMap);
                    o.a().c(8, h.f2962c, "report with left body size left: {} and response: {}.", Long.valueOf(j2), post);
                    if (!TextUtils.isEmpty(post) && "success".equals(new JSONObject(post).getString("message"))) {
                        o.a().b(8, h.f2962c, "report to server success!", new Object[0]);
                        h.this.f2965f.a((Map) a2.first);
                        h.this.f2966g.a((Map) a3.first);
                        h.this.f2964e.a((Map) a4.first);
                        if (j2 > 0) {
                            return;
                        }
                    }
                    i2++;
                }
            }
        });
        this.f2968i.removeMessages(1);
        this.f2968i.sendEmptyMessageDelayed(1, this.f2963d.e());
    }
}
