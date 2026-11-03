package com.bytedance.ttgame.module.database.api;

import java.util.List;
/* loaded from: classes5.dex */
public interface FusionUserInfoDao {
    @Decryption
    List<FusionUserInfoData> getAllFusionUserInfo();

    @Decryption
    List<FusionUserInfoData> getFusionUserInfo(String str);

    @Encryption
    void insert(FusionUserInfoData fusionUserInfoData);
}
