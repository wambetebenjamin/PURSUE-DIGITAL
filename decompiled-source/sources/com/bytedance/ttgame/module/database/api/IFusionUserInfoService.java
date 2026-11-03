package com.bytedance.ttgame.module.database.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: IFusionUserInfoService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \r2\u00020\u0001:\u0001\rJ\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\u000b\u001a\u00020\u0006H&J\b\u0010\f\u001a\u00020\u0006H&¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/IFusionUserInfoService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getDBDaoImpl", "Lcom/bytedance/ttgame/module/database/api/FusionUserInfoDao;", "getLuffyDaoImpl", "getMigrateDefaultValue", "", "initDataStorageSettings", "", "gsdkDataStorage", "Lorg/json/JSONObject;", "isMigratedSuccess", "isNoDB", "Companion", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface IFusionUserInfoService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String GSDK_FUSION_USER_MIGRATE_SUCCESS = "gsdk_fusion_user_migrate_success";
    public static final String GSDK_FUSION_USER_MIGRATION_TIME = "gsdk_fusion_user_migration_time";
    public static final String GSDK_FUSION_USER_NO_DB_LOCAL = "gsdk_fusion_user_no_db_local";
    public static final String GSDK_FUSION_USER_NO_DB_SETTINGS = "gsdk_fusion_user_no_db";
    public static final String GSDK_FUSION_WRITE_ONLY_LUFFY_LOCAL = "gsdk_fusion_user_write_only_luffy_local";
    public static final String GSDK_FUSION_WRITE_ONLY_SETTINGS = "gsdk_fusion_user_write_only_luffy";

    FusionUserInfoDao getDBDaoImpl();

    FusionUserInfoDao getLuffyDaoImpl();

    boolean getMigrateDefaultValue();

    void initDataStorageSettings(JSONObject jSONObject);

    boolean isMigratedSuccess();

    boolean isNoDB();

    /* compiled from: IFusionUserInfoService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/IFusionUserInfoService$Companion;", "", "()V", "GSDK_FUSION_USER_MIGRATE_SUCCESS", "", "GSDK_FUSION_USER_MIGRATION_TIME", "GSDK_FUSION_USER_NO_DB_LOCAL", "GSDK_FUSION_USER_NO_DB_SETTINGS", "GSDK_FUSION_WRITE_ONLY_LUFFY_LOCAL", "GSDK_FUSION_WRITE_ONLY_SETTINGS", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String GSDK_FUSION_USER_MIGRATE_SUCCESS = "gsdk_fusion_user_migrate_success";
        public static final String GSDK_FUSION_USER_MIGRATION_TIME = "gsdk_fusion_user_migration_time";
        public static final String GSDK_FUSION_USER_NO_DB_LOCAL = "gsdk_fusion_user_no_db_local";
        public static final String GSDK_FUSION_USER_NO_DB_SETTINGS = "gsdk_fusion_user_no_db";
        public static final String GSDK_FUSION_WRITE_ONLY_LUFFY_LOCAL = "gsdk_fusion_user_write_only_luffy_local";
        public static final String GSDK_FUSION_WRITE_ONLY_SETTINGS = "gsdk_fusion_user_write_only_luffy";

        private Companion() {
        }
    }
}
