package com.bytedance.ttgame.core.lifecycle;

import android.app.Application;
import android.content.Context;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.main.internal.lifecycle.IActivityLifecycleCallback;
import com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService;
import com.meituan.robust.Constants;
import java.lang.ref.WeakReference;
/* loaded from: classes3.dex */
public class Proxy__LifecycleService implements ILifecycleService {
    private LifecycleService proxy = new LifecycleService();

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void afterSdkInitialized() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "afterSdkInitialized", new String[0], Constants.VOID);
        this.proxy.afterSdkInitialized();
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public WeakReference getMainActivity() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "getMainActivity", new String[0], "java.lang.ref.WeakReference");
        return this.proxy.getMainActivity();
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public WeakReference getTopActivity() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "getTopActivity", new String[0], "java.lang.ref.WeakReference");
        return this.proxy.getTopActivity();
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void init(Context context, SdkConfig sdkConfig, boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "init", new String[]{"android.content.Context", "com.bytedance.ttgame.core.SdkConfig", "boolean"}, Constants.VOID);
        this.proxy.init(context, sdkConfig, z);
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void registerActivityLifecycleCallbacks(IActivityLifecycleCallback iActivityLifecycleCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "registerActivityLifecycleCallbacks", new String[]{"com.bytedance.ttgame.main.internal.lifecycle.IActivityLifecycleCallback"}, Constants.VOID);
        this.proxy.registerActivityLifecycleCallbacks(iActivityLifecycleCallback);
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "unregisterActivityLifecycleCallbacks", new String[]{"android.app.Application$ActivityLifecycleCallbacks"}, Constants.VOID);
        this.proxy.unregisterActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    public ILifecycleService getProxy() {
        return this.proxy;
    }
}
