package com.bytedance.ttgame.module.database.api;

import androidx.room.RoomDatabase;
/* loaded from: classes5.dex */
public abstract class SdkDatabase extends RoomDatabase {
    public abstract FusionUserInfoDao loadFusionUserInfoDao();

    public abstract UserInfoDao loadUserInfoDao();

    public abstract RequestCloudDao requestCloudDao();
}
