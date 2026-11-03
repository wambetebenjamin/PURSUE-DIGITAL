package com.bytedance.ttgame.module.database.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes5.dex */
public interface IDatabaseService extends IModuleApi {
    FusionUserInfoDao getFusionUserInfoDao();

    SdkDatabase getSdkDatabase();

    UserInfoDao getUserInfoDao();

    boolean hasCreateFusionUserDao();

    boolean hasCreateUserDao();

    void init();

    RequestCloudDao requestCloudDao();
}
