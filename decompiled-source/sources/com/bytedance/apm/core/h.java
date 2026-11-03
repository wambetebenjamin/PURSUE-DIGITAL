package com.bytedance.apm.core;

import android.app.Activity;
import android.os.Bundle;
/* compiled from: ILifecycle.java */
/* loaded from: classes.dex */
public interface h {
    void a(Activity activity);

    void onActivityCreated(Activity activity, Bundle bundle);

    void onBackground(Activity activity);

    void onFront(Activity activity);
}
