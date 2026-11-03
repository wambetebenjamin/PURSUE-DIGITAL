package com.bytedance.ttgame.main.internal.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import kotlin.Metadata;
/* compiled from: IActivityLifecycleCallback.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\u001a\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\t\u001a\u00020\u0003H&¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/main/internal/lifecycle/IActivityLifecycleCallback;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "onLaunchActivityCreated", "", "activity", "Landroid/app/Activity;", "savedInstanceState", "Landroid/os/Bundle;", "onMainActivityCreated", "onMainActivityCreatedAfterSdkInitialized", "core_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IActivityLifecycleCallback extends Application.ActivityLifecycleCallbacks {
    void onLaunchActivityCreated(Activity activity, Bundle bundle);

    void onMainActivityCreated(Activity activity, Bundle bundle);

    void onMainActivityCreatedAfterSdkInitialized();
}
