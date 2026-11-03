package com.bytedance.bdinstall.event;

import com.bytedance.sdk.account.platform.base.g;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: RegisterMonitor.java */
/* loaded from: classes.dex */
public class c extends com.bytedance.bdinstall.callback.event.c {

    /* renamed from: a  reason: collision with root package name */
    private long f3816a = 0;

    /* renamed from: b  reason: collision with root package name */
    private long f3817b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f3818c = 0;

    /* renamed from: d  reason: collision with root package name */
    private boolean f3819d = false;

    /* renamed from: e  reason: collision with root package name */
    private boolean f3820e = false;

    /* renamed from: f  reason: collision with root package name */
    private int f3821f = 0;

    /* renamed from: g  reason: collision with root package name */
    private int f3822g = 0;

    /* renamed from: h  reason: collision with root package name */
    private boolean f3823h = false;

    public boolean c() {
        return this.f3823h;
    }

    public void a(boolean z) {
        this.f3823h = z;
    }

    public void a(int i2) {
        this.f3822g = i2;
    }

    public int d() {
        return this.f3822g;
    }

    public void b(int i2) {
        this.f3821f = i2;
    }

    public int e() {
        return this.f3821f;
    }

    public boolean f() {
        return this.f3820e;
    }

    public void b(boolean z) {
        this.f3820e = z;
    }

    public boolean g() {
        return this.f3819d;
    }

    public void c(boolean z) {
        this.f3819d = z;
    }

    public long h() {
        return this.f3818c;
    }

    public long i() {
        return this.f3817b - this.f3816a;
    }

    public void c(int i2) {
        this.f3818c = i2;
    }

    public void j() {
        if (this.f3816a > 0) {
            return;
        }
        this.f3816a = System.currentTimeMillis();
    }

    public void k() {
        if (this.f3817b > 0) {
            return;
        }
        this.f3817b = System.currentTimeMillis();
    }

    @Override // com.bytedance.bdinstall.callback.event.c
    protected void b() {
        this.f3816a = 0L;
        this.f3817b = 0L;
        this.f3818c = 0;
        this.f3819d = false;
        this.f3820e = false;
        this.f3823h = false;
    }

    @Override // com.bytedance.bdinstall.callback.event.c
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("params_for_special", g.SPECIAL);
            jSONObject.put("duration", i());
            jSONObject.put("retry_count", h());
            jSONObject.put("is_first", f());
            jSONObject.put("did_change", g());
            jSONObject.put("is_new_user_mode", e());
            jSONObject.put("scene", d());
            jSONObject.put("result", c());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
