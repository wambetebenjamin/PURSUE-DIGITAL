package com.ss.android.message;

import android.content.Context;
import android.content.Intent;
/* compiled from: IPushLifeCycleListener.java */
/* loaded from: classes9.dex */
public interface c {
    void onNotifyDestroy();

    void onNotifyServiceCreate(Context context);

    void onNotifyServiceStart(Intent intent);
}
