package com.oasis.android;

import android.app.Application;
import android.content.Context;
/* loaded from: classes9.dex */
public interface ApplicationListener {
    default void attachBaseContext(Context context) {
    }

    default void onApplicationCreate(Application application) {
    }

    default void onLowMemory(Application application) {
    }

    default void onTerminate(Application application) {
    }

    default void onTrimMemory(Application application, int i2) {
    }
}
