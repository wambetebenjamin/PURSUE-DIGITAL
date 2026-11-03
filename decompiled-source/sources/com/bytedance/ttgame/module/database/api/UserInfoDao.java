package com.bytedance.ttgame.module.database.api;

import androidx.lifecycle.LiveData;
import java.util.List;
/* loaded from: classes5.dex */
public interface UserInfoDao {
    int deleteAccount(UserInfoData userInfoData);

    @Decryption
    LiveData<List<UserInfoData>> getHistoryAccount();

    @Decryption
    List<UserInfoData> getHistoryAccountByThread();

    @Encryption
    void insertUserInfo(UserInfoData userInfoData);
}
