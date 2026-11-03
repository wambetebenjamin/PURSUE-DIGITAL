package com.bytedance.android.service.manager.pull;

import android.content.Context;
import android.util.Log;
/* loaded from: classes.dex */
public class PullExternalServiceImplOfMock implements PullExternalService {
    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public void initOnApplication() {
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public void requestAndShowContent(String str) {
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public void start(PullConfiguration pullConfiguration) {
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewApi() {
        Log.w("PullExternalService", "cur isUseNewApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewApi(int i2) {
        Log.w("PullExternalService", "cur isUseNewApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isNeedRequestOldComposeApi(int i2) {
        Log.w("PullExternalService", "cur isNeedRequestOldComposeApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewOnlineRedBadgeApi() {
        Log.w("PullExternalService", "cur isUseNewOnlineRedBadgeApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewOnlineLocalPushApi() {
        Log.w("PullExternalService", "cur isUseNewOnlineLocalPushApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewAllianceRedBadgeApi() {
        Log.w("PullExternalService", "cur isUseNewAllianceRedBadgeApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseNewAllianceLocalPushApi() {
        Log.w("PullExternalService", "cur isUseNewAllianceLocalPushApi method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean enableSdkPull(Context context) {
        Log.w("PullExternalService", "cur enableSdkPull method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.pull.PullExternalService
    public boolean isUseV2Pull() {
        Log.w("PullExternalService", "cur isUseV2Pull method is empty impl in PullExternalServiceImplOfMockClass");
        return false;
    }
}
