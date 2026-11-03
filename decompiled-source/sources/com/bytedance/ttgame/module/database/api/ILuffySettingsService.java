package com.bytedance.ttgame.module.database.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: ILuffySettingsService.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000 \b2\u00020\u0001:\u0001\bJ\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/ILuffySettingsService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getDBDataNeedMigrated", "", "initDBDataNeedMigrated", "", "gsdkDataStorage", "Lorg/json/JSONObject;", "Companion", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface ILuffySettingsService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String GSDK_DB_DATA_NEED_MIGRATED = "db_data_need_migrated";
    public static final String GSDK_DB_DATA_NEED_MIGRATED_LOCAL = "db_data_need_migrated_local";

    boolean getDBDataNeedMigrated();

    void initDBDataNeedMigrated(JSONObject jSONObject);

    /* compiled from: ILuffySettingsService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/ILuffySettingsService$Companion;", "", "()V", "GSDK_DB_DATA_NEED_MIGRATED", "", "GSDK_DB_DATA_NEED_MIGRATED_LOCAL", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String GSDK_DB_DATA_NEED_MIGRATED = "db_data_need_migrated";
        public static final String GSDK_DB_DATA_NEED_MIGRATED_LOCAL = "db_data_need_migrated_local";

        private Companion() {
        }
    }
}
