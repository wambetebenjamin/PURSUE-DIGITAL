package com.bytedance.ttgame.core.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.main.internal.lifecycle.IActivityLifecycleCallback;
import com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LifecycleService.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\u0010\u0010\n\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J \u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0016H\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/bytedance/ttgame/core/lifecycle/LifecycleService;", "Lcom/bytedance/ttgame/main/internal/lifecycle/ILifecycleService;", "()V", "lifecycleCallback", "Lcom/bytedance/ttgame/core/lifecycle/GeneralLifecycleCallback;", "afterSdkInitialized", "", "getMainActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "getTopActivity", "init", "application", "Landroid/content/Context;", "config", "Lcom/bytedance/ttgame/core/SdkConfig;", "ignoreMainActivity", "", "registerActivityLifecycleCallbacks", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/main/internal/lifecycle/IActivityLifecycleCallback;", "unregisterActivityLifecycleCallbacks", "Landroid/app/Application$ActivityLifecycleCallbacks;", "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LifecycleService implements ILifecycleService {
    private GeneralLifecycleCallback lifecycleCallback;
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void init(Context application, SdkConfig config, boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "init", new String[]{"android.content.Context", "com.bytedance.ttgame.core.SdkConfig", "boolean"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(application, "application");
        Intrinsics.checkNotNullParameter(config, "config");
        GeneralLifecycleCallback generalLifecycleCallback = new GeneralLifecycleCallback(config, application);
        this.lifecycleCallback = generalLifecycleCallback;
        if (z) {
            generalLifecycleCallback.ignoreMainActivityFlag();
        }
        ((Application) application).registerActivityLifecycleCallbacks(this.lifecycleCallback);
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void afterSdkInitialized() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "afterSdkInitialized", new String[0], Constants.VOID);
        GeneralLifecycleCallback generalLifecycleCallback = this.lifecycleCallback;
        if (generalLifecycleCallback != null) {
            generalLifecycleCallback.changFlagAfterSdkInitialized();
        }
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void registerActivityLifecycleCallbacks(IActivityLifecycleCallback callback) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "registerActivityLifecycleCallbacks", new String[]{"com.bytedance.ttgame.main.internal.lifecycle.IActivityLifecycleCallback"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(callback, "callback");
        GeneralLifecycleCallback generalLifecycleCallback = this.lifecycleCallback;
        if (generalLifecycleCallback != null) {
            generalLifecycleCallback.registerActivityLifecycleCallbacks(callback);
        }
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks callback) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "unregisterActivityLifecycleCallbacks", new String[]{"android.app.Application$ActivityLifecycleCallbacks"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(callback, "callback");
        GeneralLifecycleCallback generalLifecycleCallback = this.lifecycleCallback;
        if (generalLifecycleCallback != null) {
            generalLifecycleCallback.unregisterActivityLifecycleCallbacks(callback);
        }
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public WeakReference<Activity> getMainActivity() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "getMainActivity", new String[0], "java.lang.ref.WeakReference");
        GeneralLifecycleCallback generalLifecycleCallback = this.lifecycleCallback;
        if (generalLifecycleCallback != null) {
            return generalLifecycleCallback.getMainActivity();
        }
        return null;
    }

    @Override // com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService
    public WeakReference<Activity> getTopActivity() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.lifecycle.ILifecycleService", "com.bytedance.ttgame.core.lifecycle.LifecycleService", "getTopActivity", new String[0], "java.lang.ref.WeakReference");
        GeneralLifecycleCallback generalLifecycleCallback = this.lifecycleCallback;
        if (generalLifecycleCallback != null) {
            return generalLifecycleCallback.getTopActivity();
        }
        return null;
    }
}
