package com.bytedance.ttgame.main.internal.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
/* compiled from: ILifecycleService.kt */
@InternalApi
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\u0010\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\"\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH&J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0013H&¨\u0006\u0014"}, d2 = {"Lcom/bytedance/ttgame/main/internal/lifecycle/ILifecycleService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "afterSdkInitialized", "", "getMainActivity", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "getTopActivity", "init", "application", "Landroid/content/Context;", "config", "Lcom/bytedance/ttgame/core/SdkConfig;", "ignoreMainActivity", "", "registerActivityLifecycleCallbacks", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/main/internal/lifecycle/IActivityLifecycleCallback;", "unregisterActivityLifecycleCallbacks", "Landroid/app/Application$ActivityLifecycleCallbacks;", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface ILifecycleService extends IModuleApi {
    void afterSdkInitialized();

    WeakReference<Activity> getMainActivity();

    WeakReference<Activity> getTopActivity();

    void init(Context context, SdkConfig sdkConfig, boolean z);

    void registerActivityLifecycleCallbacks(IActivityLifecycleCallback iActivityLifecycleCallback);

    void unregisterActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks);

    /* compiled from: ILifecycleService.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static /* synthetic */ void init$default(ILifecycleService iLifecycleService, Context context, SdkConfig sdkConfig, boolean z, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: init");
            }
            if ((i2 & 4) != 0) {
                z = false;
            }
            iLifecycleService.init(context, sdkConfig, z);
        }
    }
}
