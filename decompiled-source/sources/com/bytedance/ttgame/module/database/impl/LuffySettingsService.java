package com.bytedance.ttgame.module.database.impl;

import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.ICoreInternalService;
import com.bytedance.ttgame.main.internal.IMainInternalService;
import com.bytedance.ttgame.main.internal.cache.ICacheService;
import com.bytedance.ttgame.module.account.api.LoginLogger;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.database.api.ILuffySettingsService;
import com.meituan.robust.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: LuffySettingsService.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/bytedance/ttgame/module/database/impl/LuffySettingsService;", "Lcom/bytedance/ttgame/module/database/api/ILuffySettingsService;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "initState", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getDBDataNeedMigrated", "", "initDBDataNeedMigrated", "", "gsdkDataStorage", "Lorg/json/JSONObject;", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class LuffySettingsService implements ILuffySettingsService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private final String TAG = "LuffySettingsService";
    private AtomicBoolean initState = new AtomicBoolean(false);

    public final String getTAG() {
        return this.TAG;
    }

    @Override // com.bytedance.ttgame.module.database.api.ILuffySettingsService
    public void initDBDataNeedMigrated(JSONObject jSONObject) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.database.api.ILuffySettingsService", "com.bytedance.ttgame.module.database.impl.LuffySettingsService", "initDBDataNeedMigrated", new String[]{"org.json.JSONObject"}, Constants.VOID);
        if (this.initState.compareAndSet(false, true)) {
            LoginLogger.d(this.TAG, "initDBDataNeedMigrated");
            ICoreInternalService iCoreInternalService = (ICoreInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, ICoreInternalService.class, false, (String) null, 6, (Object) null);
            SdkConfig sdkConfig = iCoreInternalService != null ? iCoreInternalService.getSdkConfig() : null;
            boolean z = sdkConfig != null ? sdkConfig.db_data_need_migrated : true;
            if (jSONObject != null) {
                z = jSONObject.optBoolean("db_data_need_migrated", z);
            }
            ICacheService iCacheService = (ICacheService) ModuleManager.getService$default(ModuleManager.INSTANCE, ICacheService.class, false, (String) null, 6, (Object) null);
            if (iCacheService != null) {
                iCacheService.putBoolean("gsdk_cache_repo", "db_data_need_migrated_local", z);
                return;
            }
            return;
        }
        LoginLogger.d(this.TAG, "has inited:" + this.initState.get());
    }

    @Override // com.bytedance.ttgame.module.database.api.ILuffySettingsService
    public boolean getDBDataNeedMigrated() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.database.api.ILuffySettingsService", "com.bytedance.ttgame.module.database.impl.LuffySettingsService", "getDBDataNeedMigrated", new String[0], "boolean");
        IMainInternalService iMainInternalService = (IMainInternalService) ModuleManager.getService$default(ModuleManager.INSTANCE, IMainInternalService.class, false, (String) null, 6, (Object) null);
        SdkConfig sdkConfig = iMainInternalService != null ? iMainInternalService.getSdkConfig() : null;
        ICacheService iCacheService = (ICacheService) ModuleManager.getService$default(ModuleManager.INSTANCE, ICacheService.class, false, (String) null, 6, (Object) null);
        if (iCacheService != null) {
            Boolean bool = iCacheService.getBoolean("gsdk_cache_repo", "db_data_need_migrated_local", sdkConfig != null ? sdkConfig.db_data_need_migrated : true);
            if (bool != null) {
                return bool.booleanValue();
            }
            return true;
        }
        return true;
    }
}
