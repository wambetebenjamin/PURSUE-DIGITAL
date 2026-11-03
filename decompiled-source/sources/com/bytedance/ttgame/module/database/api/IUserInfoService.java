package com.bytedance.ttgame.module.database.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import kotlin.Metadata;
import org.json.JSONObject;
/* compiled from: IUserInfoService.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fJ\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H&J\n\u0010\u0004\u001a\u0004\u0018\u00010\u0003H&J\b\u0010\u0005\u001a\u00020\u0006H&J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH&J\b\u0010\r\u001a\u00020\u0006H&J\b\u0010\u000e\u001a\u00020\u0006H&¨\u0006\u0010"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/IUserInfoService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "getDBDaoImpl", "Lcom/bytedance/ttgame/module/database/api/UserInfoDao;", "getLuffyDaoImpl", "getMigrateDefaultValue", "", "initDataStorageSettings", "", "app", "Landroid/content/Context;", "gsdkDataStorage", "Lorg/json/JSONObject;", "isMigratedSuccess", "isNoDB", "Companion", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface IUserInfoService extends IModuleApi {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final String GSDK_ONLY_LUFFY_TO_DB = "gsdk_only_luffy_db";
    public static final String GSDK_USER_INFO_MIGRATE_SUCCESS = "gsdk_user_info_migrate_success";
    public static final String GSDK_USER_INFO_MIGRATION_TIME = "gsdk_user_info_migration_time";
    public static final String GSDK_USER_INFO_NO_DB_LOCAL = "gsdk_user_info_no_db_local";
    public static final String GSDK_USER_INFO_NO_DB_SETTINGS = "gsdk_user_info_no_db";
    public static final String GSDK_USER_INFO_WRITE_ONLY_LUFFY = "gsdk_user_info_write_only_luffy";
    public static final String GSDK_USER_INFO_WRITE_ONLY_LUFFY_LOCAL = "gsdk_user_info_write_only_luffy_local";

    UserInfoDao getDBDaoImpl();

    UserInfoDao getLuffyDaoImpl();

    boolean getMigrateDefaultValue();

    void initDataStorageSettings(Context context, JSONObject jSONObject);

    boolean isMigratedSuccess();

    boolean isNoDB();

    /* compiled from: IUserInfoService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/module/database/api/IUserInfoService$Companion;", "", "()V", "GSDK_ONLY_LUFFY_TO_DB", "", "GSDK_USER_INFO_MIGRATE_SUCCESS", "GSDK_USER_INFO_MIGRATION_TIME", "GSDK_USER_INFO_NO_DB_LOCAL", "GSDK_USER_INFO_NO_DB_SETTINGS", "GSDK_USER_INFO_WRITE_ONLY_LUFFY", "GSDK_USER_INFO_WRITE_ONLY_LUFFY_LOCAL", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String GSDK_ONLY_LUFFY_TO_DB = "gsdk_only_luffy_db";
        public static final String GSDK_USER_INFO_MIGRATE_SUCCESS = "gsdk_user_info_migrate_success";
        public static final String GSDK_USER_INFO_MIGRATION_TIME = "gsdk_user_info_migration_time";
        public static final String GSDK_USER_INFO_NO_DB_LOCAL = "gsdk_user_info_no_db_local";
        public static final String GSDK_USER_INFO_NO_DB_SETTINGS = "gsdk_user_info_no_db";
        public static final String GSDK_USER_INFO_WRITE_ONLY_LUFFY = "gsdk_user_info_write_only_luffy";
        public static final String GSDK_USER_INFO_WRITE_ONLY_LUFFY_LOCAL = "gsdk_user_info_write_only_luffy_local";

        private Companion() {
        }
    }
}
