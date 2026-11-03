package com.bytedance.apm6.service.lifecycle;

import android.app.Activity;
/* compiled from: IActivityLifecycleObserver.java */
/* loaded from: classes.dex */
public interface c {
    void onActivityCreated(Activity activity);

    void onActivityPause(Activity activity);

    void onActivityResume(Activity activity);

    void onActivityStarted(Activity activity);

    void onBackground(Activity activity);

    void onFront(Activity activity);
}
