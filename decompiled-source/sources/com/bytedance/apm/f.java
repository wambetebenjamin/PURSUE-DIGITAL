package com.bytedance.apm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.Fragment;
import com.bytedance.apm.constant.p;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.thread.b;
import com.bytedance.services.apm.api.IActivityLifeManager;
import com.bytedance.services.apm.api.IActivityLifeObserver;
import com.bytedance.services.slardar.config.IConfigManager;
import org.json.JSONObject;
/* compiled from: BaseApmWidget.java */
/* loaded from: classes.dex */
public abstract class f implements b.InterfaceC0021b, IActivityLifeObserver, com.bytedance.services.apm.api.j, com.bytedance.services.slardar.config.a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f1058a;

    /* renamed from: b  reason: collision with root package name */
    private JSONObject f1059b;

    /* renamed from: c  reason: collision with root package name */
    private volatile JSONObject f1060c;

    /* renamed from: d  reason: collision with root package name */
    private volatile JSONObject f1061d;

    /* renamed from: e  reason: collision with root package name */
    private volatile JSONObject f1062e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1063f;

    @Override // com.bytedance.services.apm.api.j
    public String getTag() {
        return r.STATS_BASE_MODULE;
    }

    @Override // com.bytedance.services.apm.api.j
    public void init(Context context) {
    }

    @Override // com.bytedance.services.apm.api.j
    public abstract boolean isOnlyMainProcess();

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onActivityPause(Activity activity) {
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onActivityResume(Activity activity) {
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onActivityStarted(Activity activity) {
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onChange(Activity activity, Fragment fragment) {
    }

    @Override // com.bytedance.apm.thread.b.InterfaceC0021b
    public void onTimeEvent(long j2) {
    }

    @Override // com.bytedance.services.apm.api.j
    public void start() {
    }

    @Override // com.bytedance.services.apm.api.j
    public void stop() {
    }

    @Override // com.bytedance.services.apm.api.j
    public void destroy() {
        h();
    }

    private void h() {
        e();
        d();
        f();
    }

    protected final boolean a() {
        IConfigManager iConfigManager = (IConfigManager) com.bytedance.news.common.service.manager.e.getService(IConfigManager.class);
        if (iConfigManager != null) {
            iConfigManager.registerConfigListener(this);
            return true;
        }
        return false;
    }

    protected final boolean b() {
        IActivityLifeManager iActivityLifeManager = (IActivityLifeManager) com.bytedance.news.common.service.manager.e.getService(IActivityLifeManager.class);
        if (iActivityLifeManager != null) {
            iActivityLifeManager.register(this);
            return true;
        }
        return false;
    }

    protected final void c() {
        com.bytedance.apm.thread.b.getInstance().addTimeTask(this);
    }

    protected final boolean d() {
        IConfigManager iConfigManager = (IConfigManager) com.bytedance.news.common.service.manager.e.getService(IConfigManager.class);
        if (iConfigManager != null) {
            iConfigManager.unregisterConfigListener(this);
            return true;
        }
        return false;
    }

    protected final boolean e() {
        IActivityLifeManager iActivityLifeManager = (IActivityLifeManager) com.bytedance.news.common.service.manager.e.getService(IActivityLifeManager.class);
        if (iActivityLifeManager != null) {
            iActivityLifeManager.unregister(this);
            return true;
        }
        return false;
    }

    protected final void f() {
        com.bytedance.apm.thread.b.getInstance().removeTimeTask(this);
    }

    protected boolean g() {
        return this.f1063f;
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onFront(Activity activity) {
        this.f1063f = true;
    }

    @Override // com.bytedance.services.apm.api.IActivityLifeObserver
    public void onBackground(Activity activity) {
        this.f1063f = false;
    }

    @Override // com.bytedance.services.slardar.config.a
    public void onRefresh(JSONObject jSONObject, boolean z) {
        this.f1059b = jSONObject;
        JSONObject optJSONObject = jSONObject.optJSONObject("custom_event_settings");
        if (optJSONObject != null) {
            this.f1060c = optJSONObject.optJSONObject("allow_log_type");
            this.f1061d = optJSONObject.optJSONObject(p.BASE_KEY_ALLOW_METRIC_TYPE);
            this.f1062e = optJSONObject.optJSONObject(p.BASE_KEY_ALLOW_SERVICE_NAME);
        }
    }

    @Override // com.bytedance.services.slardar.config.a
    public void onReady() {
        this.f1058a = true;
    }

    public boolean isConfigReady() {
        return this.f1058a;
    }

    public boolean getLogTypeSwitch(String str) {
        return (this.f1060c == null || TextUtils.isEmpty(str) || this.f1060c.optInt(str) != 1) ? false : true;
    }

    public boolean getMetricTypeSwitch(String str) {
        return (this.f1061d == null || TextUtils.isEmpty(str) || this.f1061d.optInt(str) != 1) ? false : true;
    }

    public boolean getServiceSwitch(String str) {
        return (this.f1062e == null || TextUtils.isEmpty(str) || this.f1062e.optInt(str) != 1) ? false : true;
    }

    public boolean getConfigBool(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str) || (jSONObject = this.f1059b) == null) {
            return false;
        }
        return jSONObject.optBoolean(str);
    }

    public JSONObject getConfigJSON(String str) {
        JSONObject jSONObject;
        if (TextUtils.isEmpty(str) || (jSONObject = this.f1059b) == null) {
            return null;
        }
        return jSONObject.optJSONObject(str);
    }

    public int getConfigInt(String str, int i2) {
        JSONObject jSONObject;
        return (TextUtils.isEmpty(str) || (jSONObject = this.f1059b) == null) ? i2 : jSONObject.optInt(str, i2);
    }
}
