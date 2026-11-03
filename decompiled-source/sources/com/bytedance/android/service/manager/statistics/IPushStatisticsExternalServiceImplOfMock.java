package com.bytedance.android.service.manager.statistics;

import android.util.Log;
/* loaded from: classes.dex */
public class IPushStatisticsExternalServiceImplOfMock implements IPushStatisticsExternalService {
    @Override // com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService
    public void onProcessStart() {
    }

    @Override // com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService
    public void onPushStart() {
    }

    @Override // com.bytedance.android.service.manager.statistics.IPushStatisticsExternalService
    public boolean isEnablePushStatistics() {
        Log.w("IPushStatisticsExternalService", "cur isEnablePushStatistics method is empty impl in IPushStatisticsExternalServiceImplOfMockClass");
        return false;
    }
}
