package com.bytedance.android.service.manager.statistics;

import com.bytedance.android.push.service.manager.annotation.a;
@a
/* loaded from: classes.dex */
public interface IPushStatisticsExternalService {
    boolean isEnablePushStatistics();

    void onProcessStart();

    void onPushStart();
}
