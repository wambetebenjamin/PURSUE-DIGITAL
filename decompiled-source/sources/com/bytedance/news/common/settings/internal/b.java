package com.bytedance.news.common.settings.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.concurrent.Executor;
/* compiled from: IConfig.java */
/* loaded from: classes2.dex */
public interface b {
    com.bytedance.news.common.settings.api.e getAbReportService();

    Context getContext();

    com.bytedance.news.common.settings.api.a getDebugTeller();

    Executor getExecutor();

    int getMaxAppSettingSpCount();

    com.bytedance.news.common.settings.api.c getRequestService();

    long getRetryInterval();

    com.bytedance.news.common.settings.api.g getSettingsLogService();

    SharedPreferences getSharedPreferences(Context context, String str, int i2, boolean z);

    com.bytedance.news.common.settings.api.j getStorageFactory();

    String getUID();

    long getUpdateInterval();

    String getUpdateVersionCode();

    boolean isMainProcess();

    boolean isReportSettingsStack();

    boolean useOneSpForAppSettings();

    boolean useReflect();
}
