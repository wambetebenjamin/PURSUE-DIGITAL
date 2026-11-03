package com.bytedance.bdinstall.event;

import com.bytedance.sdk.account.platform.base.g;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ActivateMonitor.java */
/* loaded from: classes.dex */
public class a extends com.bytedance.bdinstall.callback.event.c {

    /* renamed from: a  reason: collision with root package name */
    private long f3805a = 0;

    /* renamed from: b  reason: collision with root package name */
    private long f3806b = 0;

    /* renamed from: c  reason: collision with root package name */
    private long f3807c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3808d = false;

    /* renamed from: e  reason: collision with root package name */
    private int f3809e = 0;

    /* renamed from: f  reason: collision with root package name */
    private int f3810f = 0;

    /* renamed from: g  reason: collision with root package name */
    private boolean f3811g = false;

    public boolean c() {
        return this.f3811g;
    }

    public void a(boolean z) {
        this.f3811g = z;
    }

    public void a(int i2) {
        this.f3810f = i2;
    }

    public int d() {
        return this.f3810f;
    }

    public void b(int i2) {
        this.f3809e = i2;
    }

    public int e() {
        return this.f3809e;
    }

    public boolean f() {
        return this.f3808d;
    }

    public void b(boolean z) {
        this.f3808d = z;
    }

    public void g() {
        if (this.f3805a > 0) {
            return;
        }
        this.f3805a = System.currentTimeMillis();
    }

    public void h() {
        if (this.f3806b > 0) {
            return;
        }
        this.f3806b = System.currentTimeMillis();
    }

    public long i() {
        return this.f3806b - this.f3805a;
    }

    public long j() {
        return this.f3807c;
    }

    public void a(long j2) {
        this.f3807c = j2;
    }

    @Override // com.bytedance.bdinstall.callback.event.c
    protected void b() {
        this.f3805a = 0L;
        this.f3806b = 0L;
        this.f3807c = 0L;
        this.f3808d = false;
        this.f3811g = false;
    }

    @Override // com.bytedance.bdinstall.callback.event.c
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("params_for_special", g.SPECIAL);
            jSONObject.put("duration", i());
            jSONObject.put("retry_count", j());
            jSONObject.put("is_first", f());
            jSONObject.put("is_new_user_mode", e());
            jSONObject.put("scene", d());
            jSONObject.put("result", c());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
