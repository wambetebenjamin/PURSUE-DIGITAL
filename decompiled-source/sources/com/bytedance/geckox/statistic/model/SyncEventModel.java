package com.bytedance.geckox.statistic.model;

import android.os.Build;
import com.bytedance.geckox.b;
import com.bytedance.geckox.f;
import com.google.gson.annotations.SerializedName;
/* loaded from: classes2.dex */
public class SyncEventModel {
    @SerializedName("aid")
    private long aid;
    @SerializedName("app_version")
    private String appVersion;
    @SerializedName("device_id")
    private String deviceId;
    @SerializedName("region")
    private String region;
    @SerializedName("sync_stats_type")
    private int syncStatsType;
    @SerializedName(b.g.f8909b)
    private int syncTaskId;
    @SerializedName("sync_task_type")
    private int syncTaskType;
    @SerializedName("params_for_special")
    private String params = "gecko";
    @SerializedName("os")
    private int os = 0;
    @SerializedName("sdk_version")
    private String sdkVersion = "2.3.1-rc.1-16kb";
    @SerializedName("device_model")
    private String deviceModel = Build.MODEL;

    public SyncEventModel(f fVar) {
        this.aid = fVar.h();
        this.appVersion = fVar.k();
        this.region = fVar.j();
        this.deviceId = fVar.m();
    }

    public void setSyncStatsType(int i2) {
        this.syncStatsType = i2;
    }

    public void setSyncTaskId(int i2) {
        this.syncTaskId = i2;
    }

    public void setSyncTaskType(int i2) {
        this.syncTaskType = i2;
    }
}
