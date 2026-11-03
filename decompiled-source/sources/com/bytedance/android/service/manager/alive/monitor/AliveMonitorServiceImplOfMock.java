package com.bytedance.android.service.manager.alive.monitor;

import android.content.Context;
import android.util.Log;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class AliveMonitorServiceImplOfMock implements AliveMonitorService {
    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public void monitorAssociationStart(Context context) {
    }

    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public void monitorAssociationStart(Context context, IMonitorCallback iMonitorCallback) {
    }

    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public void onUserActive() {
    }

    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public boolean hasHookActivityManagerSuccess(Context context) {
        Log.w("AliveMonitorService", "cur hasHookActivityManagerSuccess method is empty impl in AliveMonitorServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public boolean hasHookActivityTaskManagerSuccess(Context context) {
        Log.w("AliveMonitorService", "cur hasHookActivityTaskManagerSuccess method is empty impl in AliveMonitorServiceImplOfMockClass");
        return false;
    }

    @Override // com.bytedance.android.service.manager.alive.monitor.AliveMonitorService
    public JSONObject getProcessStartInfoObject(Context context) {
        Log.w("AliveMonitorService", "cur getProcessStartInfoObject method is empty impl in AliveMonitorServiceImplOfMockClass");
        return null;
    }
}
