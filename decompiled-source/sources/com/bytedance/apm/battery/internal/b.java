package com.bytedance.apm.battery.internal;

import com.bytedance.apm.data.type.f;
import com.bytedance.apm.logging.e;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONObject;
/* compiled from: BatteryStatsRet.java */
/* loaded from: classes.dex */
public class b {
    private long A;
    private long B;
    private long C;
    private boolean K;
    private String L;
    private long M;
    private long P;

    /* renamed from: a  reason: collision with root package name */
    private long f639a;

    /* renamed from: b  reason: collision with root package name */
    private long f640b;
    private String n;
    private String o;
    private long p;
    private long q;
    private int r;
    private int s;
    private int t;
    private int u;
    private long v;
    private int w;
    private int x;
    private int y;
    private int z;

    /* renamed from: c  reason: collision with root package name */
    private long f641c = 0;

    /* renamed from: d  reason: collision with root package name */
    private long f642d = 0;

    /* renamed from: e  reason: collision with root package name */
    private long f643e = 0;

    /* renamed from: f  reason: collision with root package name */
    private long f644f = 0;

    /* renamed from: g  reason: collision with root package name */
    private long f645g = 0;
    private long D = 0;
    private long E = 0;
    private long F = 0;
    private long G = 0;

    /* renamed from: h  reason: collision with root package name */
    private long f646h = 0;

    /* renamed from: i  reason: collision with root package name */
    private long f647i = 0;

    /* renamed from: j  reason: collision with root package name */
    private long f648j = 0;
    private long k = 0;
    private long l = 0;
    private long H = 0;
    private long I = 0;

    /* renamed from: J  reason: collision with root package name */
    private long f638J = 0;
    private boolean m = true;
    private long N = 0;
    private long O = 0;
    private long Q = 0;
    private long R = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.f640b += j2;
    }

    public void addBackCpuMs(long j2) {
        this.f646h += j2;
    }

    public void addBackWakeLockMs(long j2) {
        this.f648j += j2;
    }

    public void addBackTrafficBytes(long j2) {
        this.l += j2;
    }

    public void c(long j2) {
        this.H += j2;
    }

    public void d(long j2) {
        this.I += j2;
    }

    public void e(long j2) {
        this.f638J += j2;
    }

    public void addBackLocMs(long j2) {
        this.f647i += j2;
    }

    public void addBackWakeUpAlarmCount(long j2) {
        this.k += j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(long j2) {
        this.f639a += j2;
    }

    public void addFrontCpuMs(long j2) {
        this.f641c += j2;
    }

    public void addFrontWakeLockMs(long j2) {
        this.f643e += j2;
    }

    public void addFrontTrafficBytes(long j2) {
        this.f645g += j2;
    }

    public void f(long j2) {
        this.D += j2;
    }

    public void g(long j2) {
        this.E += j2;
    }

    public void h(long j2) {
        this.F += j2;
    }

    public void addFrontLocMs(long j2) {
        this.f642d += j2;
    }

    public void addFrontWakeUpAlarmCount(long j2) {
        this.f644f += j2;
    }

    public void i(long j2) {
        this.G += j2;
    }

    public boolean isMainProcess() {
        return this.m;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(boolean z) {
        this.m = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(boolean z) {
        this.K = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.n = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b(String str) {
        this.o = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c(String str) {
        this.L = str;
    }

    void a() {
        this.f639a = 0L;
        this.f640b = 0L;
        this.f641c = 0L;
        this.f642d = 0L;
        this.f643e = 0L;
        this.f644f = 0L;
        this.f645g = 0L;
        this.D = 0L;
        this.E = 0L;
        this.F = 0L;
        this.G = 0L;
        this.f646h = 0L;
        this.f647i = 0L;
        this.f648j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.H = 0L;
        this.I = 0L;
        this.f638J = 0L;
        this.m = true;
        this.K = false;
        this.n = "";
        this.o = "";
        this.L = "";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        this.f644f = this.r;
        this.f641c = this.u;
        this.f642d = this.s;
        this.f643e = this.t;
        this.f639a = this.p;
        this.f645g = this.v;
        this.D = this.M;
        this.E = this.N;
        this.F = this.O;
        this.k = this.w;
        this.f646h = this.z;
        this.f647i = this.x;
        this.f648j = this.y;
        this.f640b = this.q;
        this.l = this.A;
        this.H = this.P;
        this.I = this.Q;
        this.f638J = this.R;
        this.m = false;
        this.n = "all_process";
        this.K = false;
        this.L = "";
        try {
            report(false);
        } catch (Exception unused) {
        }
    }

    boolean c() {
        return this.f639a > 10000;
    }

    boolean d() {
        return this.f640b > 5000;
    }

    public boolean report(boolean z) {
        boolean b2 = b(z);
        if (!b2 && com.bytedance.apm.c.isDebugMode()) {
            e.i(com.bytedance.apm.logging.b.TAG_BATTERY, "stats report failed, processName: " + this.n);
        }
        a();
        return b2;
    }

    public void e() {
        long j2 = this.B;
        if (j2 > this.p) {
            this.p = j2;
        }
        this.B = 0L;
        long j3 = this.C;
        if (j3 > this.q) {
            this.q = j3;
        }
        this.C = 0L;
    }

    private boolean b(boolean z) {
        JSONObject computeAndReturnJSONObject = computeAndReturnJSONObject(z);
        if (computeAndReturnJSONObject == null) {
            return false;
        }
        if (computeAndReturnJSONObject.length() == 0) {
            return true;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_main_process", this.m);
        jSONObject.put("process_name", this.n);
        jSONObject.put("scene", this.L);
        jSONObject.put(com.bytedance.apm.constant.c.f949b, this.K ? 1 : 0);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("sid", this.o);
        com.bytedance.apm.data.pipeline.a.getInstance().handle(new f("battery", "", computeAndReturnJSONObject, jSONObject, jSONObject2));
        if (com.bytedance.apm.c.isDebugMode()) {
            e.i(com.bytedance.apm.logging.b.TAG_BATTERY, "stats report, processName: " + this.n + " Json:" + computeAndReturnJSONObject.toString());
        }
        return true;
    }

    public JSONObject computeAndReturnJSONObject(boolean z) {
        long j2;
        long j3;
        JSONObject jSONObject = new JSONObject();
        if (c()) {
            jSONObject.put("front_alarm", this.f644f);
            jSONObject.put("front_loc_p_time", (((float) this.f642d) * 1.0f) / 1000.0f);
            jSONObject.put("front_power_p_time", (((float) this.f643e) * 1.0f) / 1000.0f);
            if (this.f641c < 0) {
                if (com.bytedance.apm.c.isDebugMode()) {
                    e.w(com.bytedance.apm.logging.b.TAG_BATTERY, " report data invalid, frontCpuMs < 0 : " + this.f641c);
                }
                return null;
            }
            jSONObject.put("front_cpu_active_time_p_time", (((float) j3) * 1.0f) / 1000.0f);
            double d2 = (this.f644f * 0.002083333383779973d) + (this.f641c * 6.944444612599909E-5d) + (this.f642d * 7.499999810534064E-6d) + (this.f643e * 6.944444521650439E-6d);
            if (this.m || !z) {
                jSONObject.put("front_traffic_all_iface", (((float) this.E) * 1.0f) / 1024.0f);
                jSONObject.put("front_traffic_packets_all_iface", this.F);
                jSONObject.put("front_traffic_p_capacity", (((float) this.f645g) * 1.0f) / 1024.0f);
                jSONObject.put("front_traffic_packets", this.D);
                d2 += this.f645g * 2.6666666030905617E-7d;
            }
            if (d2 < FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                if (com.bytedance.apm.c.isDebugMode()) {
                    e.w(com.bytedance.apm.logging.b.TAG_BATTERY, " report data invalid, frontScore < 0 : " + d2);
                }
                return null;
            }
            jSONObject.put("front_score", d2);
            jSONObject.put("front_p_time", (((float) this.f639a) * 1.0f) / 1000.0f);
            float f2 = 60000.0f / ((float) this.f639a);
            jSONObject.put("front_alarm_per_min", ((float) this.f644f) * f2);
            jSONObject.put("front_loc_per_min_p_time", (((float) this.f642d) / 1000.0f) * f2);
            jSONObject.put("front_power_per_min_p_time", (((float) this.f643e) / 1000.0f) * f2);
            jSONObject.put("front_cpu_active_time_per_min_p_time", (((float) this.f641c) / 1000.0f) * f2);
            if (this.m || !z) {
                jSONObject.put("front_traffic_per_min_p_capacity", (((float) this.f645g) / 1024.0f) * f2);
                jSONObject.put("front_traffic_packets_per_min", ((float) this.D) * f2);
                jSONObject.put("front_traffic_all_iface_per_min", ((((float) this.E) * 1.0f) / 1024.0f) * f2);
                jSONObject.put("front_traffic_packets_all_iface_per_min", ((float) this.F) * f2);
            }
            jSONObject.put("front_score_per_min", d2 * f2);
            if (this.m) {
                jSONObject.put("front_battery_consume", this.G);
                jSONObject.put("front_battery_consume_per_min", ((float) this.G) * f2);
            }
            if (z) {
                this.r = (int) (this.r + this.f644f);
                this.u = (int) (this.u + this.f641c);
                this.s = (int) (this.s + this.f642d);
                this.t = (int) (this.t + this.f643e);
                this.B += this.f639a;
                this.v += this.f645g;
                this.M += this.D;
                this.N += this.E;
                this.O += this.F;
            }
        }
        if (d()) {
            jSONObject.put("back_alarm", this.k);
            jSONObject.put("back_loc_p_time", (((float) this.f647i) * 1.0f) / 1000.0f);
            jSONObject.put("back_power_p_time", (((float) this.f648j) * 1.0f) / 1000.0f);
            if (this.f646h < 0) {
                if (com.bytedance.apm.c.isDebugMode()) {
                    e.w(com.bytedance.apm.logging.b.TAG_BATTERY, " report data invalid, mBackCpuMs < 0 : " + this.f646h);
                }
                return null;
            }
            jSONObject.put("back_cpu_active_time_p_time", (((float) j2) * 1.0f) / 1000.0f);
            double d3 = (this.k * 0.002083333383779973d) + (this.f646h * 6.944444612599909E-5d) + (this.f647i * 7.499999810534064E-6d) + (this.f648j * 6.944444521650439E-6d);
            if (this.m || !z) {
                jSONObject.put("back_traffic_all_iface", (((float) this.I) * 1.0f) / 1024.0f);
                jSONObject.put("back_traffic_packets_all_iface", this.f638J);
                jSONObject.put("back_traffic_p_capacity", (((float) this.l) * 1.0f) / 1024.0f);
                jSONObject.put("back_traffic_packets", this.H);
                d3 += this.l * 2.6666666030905617E-7d;
            }
            jSONObject.put("back_score", d3);
            jSONObject.put("back_p_time", (((float) this.f640b) * 1.0f) / 1000.0f);
            float f3 = 60000.0f / ((float) this.f640b);
            jSONObject.put("back_alarm_per_min", ((float) this.k) * f3);
            jSONObject.put("back_loc_per_min_p_time", (((float) this.f647i) / 1000.0f) * f3);
            jSONObject.put("back_power_per_min_p_time", (((float) this.f648j) / 1000.0f) * f3);
            jSONObject.put("back_cpu_active_time_per_min_p_time", (((float) this.f646h) / 1000.0f) * f3);
            if (this.m || !z) {
                jSONObject.put("back_traffic_all_iface_per_min", ((((float) this.I) * 1.0f) / 1024.0f) * f3);
                jSONObject.put("back_traffic_packets_all_iface_per_min", ((float) this.f638J) * f3);
                jSONObject.put("back_traffic_per_min_p_capacity", (((float) this.l) / 1024.0f) * f3);
                jSONObject.put("back_traffic_packets_per_min", ((float) this.H) * f3);
            }
            jSONObject.put("back_score_per_min", d3 * f3);
            if (z) {
                this.w = (int) (this.w + this.k);
                this.z = (int) (this.z + this.f646h);
                this.x = (int) (this.x + this.f647i);
                this.y = (int) (this.y + this.f648j);
                this.C += this.f640b;
                this.Q += this.I;
                this.R += this.f638J;
                this.A += this.l;
                this.P += this.H;
            }
        }
        return jSONObject;
    }
}
