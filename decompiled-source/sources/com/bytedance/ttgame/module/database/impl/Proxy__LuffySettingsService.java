package com.bytedance.ttgame.module.database.impl;

import com.bytedance.ttgame.module.database.api.ILuffySettingsService;
import com.meituan.robust.Constants;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public class Proxy__LuffySettingsService implements ILuffySettingsService {
    private LuffySettingsService proxy = new LuffySettingsService();

    @Override // com.bytedance.ttgame.module.database.api.ILuffySettingsService
    public boolean getDBDataNeedMigrated() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.database.api.ILuffySettingsService", "com.bytedance.ttgame.module.database.impl.LuffySettingsService", "getDBDataNeedMigrated", new String[0], "boolean");
        return this.proxy.getDBDataNeedMigrated();
    }

    @Override // com.bytedance.ttgame.module.database.api.ILuffySettingsService
    public void initDBDataNeedMigrated(JSONObject jSONObject) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.database.api.ILuffySettingsService", "com.bytedance.ttgame.module.database.impl.LuffySettingsService", "initDBDataNeedMigrated", new String[]{"org.json.JSONObject"}, Constants.VOID);
        this.proxy.initDBDataNeedMigrated(jSONObject);
    }

    public ILuffySettingsService getProxy() {
        return this.proxy;
    }
}
