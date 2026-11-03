package com.bytedance.tracing.internal;

import com.bytedance.apm.constant.p;
import com.bytedance.apm.util.l;
import com.bytedance.services.slardar.config.IConfigManager;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import org.json.JSONObject;
/* compiled from: TraceSettings.java */
/* loaded from: classes3.dex */
public class d implements com.bytedance.services.slardar.config.a {

    /* renamed from: e  reason: collision with root package name */
    private static volatile d f14058e;

    /* renamed from: a  reason: collision with root package name */
    private boolean f14059a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f14060b = true;

    /* renamed from: c  reason: collision with root package name */
    private JSONObject f14061c;

    /* renamed from: d  reason: collision with root package name */
    private JSONObject f14062d;

    @Override // com.bytedance.services.slardar.config.a
    public void onReady() {
    }

    private d() {
    }

    public static d getInstance() {
        if (f14058e == null) {
            synchronized (d.class) {
                if (f14058e == null) {
                    f14058e = new d();
                    ((IConfigManager) com.bytedance.news.common.service.manager.e.getService(IConfigManager.class)).registerConfigListener(f14058e);
                }
            }
        }
        return f14058e;
    }

    @Override // com.bytedance.services.slardar.config.a
    public void onRefresh(JSONObject jSONObject, boolean z) {
        this.f14060b = l.optBoolean(jSONObject, "tracing", p.SETTING_TRACING_ENABLE, true);
        if (this.f14059a) {
            return;
        }
        this.f14061c = l.optJSONObject(jSONObject, "tracing", p.SETTING_TRACING_ALLOW_LIST);
        this.f14062d = l.optJSONObject(jSONObject, "tracing", p.SETTING_TRACING_ERROR_LIST);
        this.f14059a = true;
    }

    public int isErrorOrSampleHit(boolean z, String str) {
        JSONObject jSONObject;
        int i2 = 0;
        if (this.f14060b) {
            if (z && ((jSONObject = this.f14062d) == null || jSONObject.optDouble(str, -1.0d) > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)) {
                i2 = 16;
            }
            JSONObject jSONObject2 = this.f14061c;
            return (jSONObject2 == null || jSONObject2.optDouble(str, -1.0d) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) ? i2 : i2 | 1;
        }
        return 0;
    }

    public double getSampleRate(String str) {
        JSONObject jSONObject = this.f14061c;
        if (jSONObject == null) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        double optDouble = jSONObject.optDouble(str);
        return Double.isNaN(optDouble) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : optDouble;
    }
}
