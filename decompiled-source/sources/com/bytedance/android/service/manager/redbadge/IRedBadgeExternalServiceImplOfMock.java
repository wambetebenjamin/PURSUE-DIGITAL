package com.bytedance.android.service.manager.redbadge;

import android.content.Context;
import android.util.Log;
import com.ss.android.pushmanager.b;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class IRedBadgeExternalServiceImplOfMock implements IRedBadgeExternalService {
    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService
    public void startOnWorkerProcess(b bVar) {
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService
    public JSONObject getRedBadgeRequestBody(Context context, boolean z) {
        Log.w("IRedBadgeExternalService", "cur getRedBadgeRequestBody method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return null;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService
    public List<Long> getRedBadgeShowHistoryList(Context context) {
        Log.w("IRedBadgeExternalService", "cur getRedBadgeShowHistoryList method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return null;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService
    public String getRedBadgeShowHistoryStr(Context context) {
        Log.w("IRedBadgeExternalService", "cur getRedBadgeShowHistoryStr method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return "";
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeExternalService
    public int getBadgeNumberWhenAppLaunch(Context context) {
        Log.w("IRedBadgeExternalService", "cur getBadgeNumberWhenAppLaunch method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return 0;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean applyCount(Context context, int i2) {
        Log.w("IRedBadgeExternalService", "cur applyCount method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean removeCount(Context context) {
        Log.w("IRedBadgeExternalService", "cur removeCount method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public int getCurRedBadgeNumber(Context context) {
        Log.w("IRedBadgeExternalService", "cur getCurRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return 0;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportGetCurRedBadgeNumber() {
        Log.w("IRedBadgeExternalService", "cur isSupportGetCurRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean addRedBadgeNumber(Context context, int i2) {
        Log.w("IRedBadgeExternalService", "cur addRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportAddRedBadgeNumber(int i2) {
        Log.w("IRedBadgeExternalService", "cur isSupportAddRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean reduceRedBadgeNumber(Context context, int i2) {
        Log.w("IRedBadgeExternalService", "cur reduceRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.redbadge.IRedBadgeService
    public boolean isSupportReduceRedBadgeNumber(int i2) {
        Log.w("IRedBadgeExternalService", "cur isSupportReduceRedBadgeNumber method is empty impl in IRedBadgeExternalServiceImplOfMockClass");
        return false;
    }
}
