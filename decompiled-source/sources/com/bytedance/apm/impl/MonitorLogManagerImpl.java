package com.bytedance.apm.impl;

import com.bytedance.services.apm.api.IMonitorLogManager;
import com.bytedance.services.apm.api.i;
import java.util.List;
import org.json.JSONObject;
/* loaded from: classes.dex */
public class MonitorLogManagerImpl implements IMonitorLogManager {
    @Override // com.bytedance.services.apm.api.IMonitorLogManager
    public void deleteLegacyLogByIds(String str, String str2) {
    }

    @Override // com.bytedance.services.apm.api.IMonitorLogManager
    public void getLegacyLog(long j2, long j3, String str, i iVar) {
    }

    @Override // com.bytedance.services.apm.api.IMonitorLogManager
    public List<JSONObject> getRecentUiActionRecords() {
        return com.bytedance.apm.data.ui.a.getInstance().getRecords();
    }
}
