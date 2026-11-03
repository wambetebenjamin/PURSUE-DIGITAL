package com.bytedance.services.apm.api;

import android.content.Context;
/* compiled from: IWidget.java */
/* loaded from: classes3.dex */
public interface j {
    void destroy();

    String getTag();

    void init(Context context);

    boolean isOnlyMainProcess();

    void notifyParams(l lVar);

    void start();

    void stop();
}
