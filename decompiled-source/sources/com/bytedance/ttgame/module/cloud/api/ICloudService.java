package com.bytedance.ttgame.module.cloud.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.module.database.api.RequestCloudDao;
import com.bytedance.ttgame.sdk.module.account.pojo.ProtocolAddressData;
/* loaded from: classes4.dex */
public interface ICloudService extends IModuleApi {
    public static final String GSDK_REQUEST_CLOUD_LOCAL_VALUE = "gsdk_request_cloud_no_db_local";
    public static final String GSDK_REQUEST_CLOUD_MIGRATE_SUCCESS = "gsdk_request_cloud_migrate_success";
    public static final String GSDK_REQUEST_CLOUD_MIGRATION_TIME = "gsdk_request_cloud_migration_time";
    public static final String GSDK_REQUEST_CLOUD_SETTINGS_VALUE = "gsdk_request_cloud_no_db";
    public static final String GSDK_REQUEST_CLOUD_WRITE_ONLY_LUFFY = "gsdk_request_cloud_write_only_luffy";
    public static final String GSDK_REQUEST_CLOUD_WRITE_ONLY_LUFFY_LOCAL_VALUE = "gsdk_request_cloud_write_only_luffy_local";

    boolean compareAndSetRequestState(int i2, int i3);

    Object fetchValue(String str);

    RequestCloudDao getDBDaoImpl();

    RequestCloudDao getLuffyDaoImpl();

    void init();

    boolean isMigratedSuccess();

    boolean isNoDB();

    boolean isRequestSuccess();

    void loadCloudInfo();

    void loadCloudInfo(ICloudServiceCallback<ProtocolAddressData> iCloudServiceCallback);

    String privatePolicyUrl();

    String userProtocolUrl();
}
