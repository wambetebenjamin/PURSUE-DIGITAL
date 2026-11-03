package com.bytedance.bdinstall.event;

import com.bytedance.sdk.account.platform.base.g;
import com.bytedance.ttgame.sdk.module.core.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: ParamsMonitor.java */
/* loaded from: classes.dex */
public class b extends com.bytedance.bdinstall.callback.event.c {

    /* renamed from: a  reason: collision with root package name */
    private int f3812a = 0;

    /* renamed from: b  reason: collision with root package name */
    private int f3813b = 0;

    /* renamed from: c  reason: collision with root package name */
    private int f3814c = 0;

    /* renamed from: d  reason: collision with root package name */
    private long f3815d = 0;

    @Override // com.bytedance.bdinstall.callback.event.c
    protected void b() {
    }

    public int c() {
        return this.f3812a;
    }

    public void a(int i2) {
        this.f3812a = i2;
    }

    public int d() {
        return this.f3813b;
    }

    public void b(int i2) {
        this.f3813b = i2;
    }

    public int e() {
        return this.f3814c;
    }

    public void c(int i2) {
        this.f3814c = i2;
    }

    public void a(long j2) {
        this.f3815d = j2;
    }

    public long f() {
        return this.f3815d;
    }

    @Override // com.bytedance.bdinstall.callback.event.c
    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("params_for_special", g.SPECIAL);
            jSONObject.put("type", c());
            jSONObject.put("state", d());
            jSONObject.put(Constants.FLAG, e());
            jSONObject.put("duration", f());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
