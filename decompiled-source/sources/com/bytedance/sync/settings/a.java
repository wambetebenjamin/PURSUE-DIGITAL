package com.bytedance.sync.settings;

import com.google.gson.annotations.SerializedName;
/* compiled from: Settings.java */
/* loaded from: classes3.dex */
public class a {
    @SerializedName("channel")

    /* renamed from: a  reason: collision with root package name */
    private int f13665a;
    @SerializedName("heartbeat_poll")

    /* renamed from: b  reason: collision with root package name */
    private long f13666b = 60;
    @SerializedName("pull_poll")

    /* renamed from: c  reason: collision with root package name */
    private long f13667c = 60;
    @SerializedName("background_heartbeat_poll")

    /* renamed from: d  reason: collision with root package name */
    private long f13668d = 300;
    @SerializedName("background_pull_poll")

    /* renamed from: e  reason: collision with root package name */
    private long f13669e = 300;
    @SerializedName("report_size_limit")

    /* renamed from: f  reason: collision with root package name */
    private long f13670f = 20480;
    @SerializedName("submit_size_limit")

    /* renamed from: g  reason: collision with root package name */
    private long f13671g = 51200;
    @SerializedName("event_send_delay")

    /* renamed from: h  reason: collision with root package name */
    private long f13672h = 5;
    @SerializedName("db_store_size_limit")

    /* renamed from: i  reason: collision with root package name */
    private long f13673i = 51200;

    public long getChannel() {
        return this.f13665a;
    }

    public void setChannel(int i2) {
        this.f13665a = i2;
    }

    public long getSyncInterval() {
        return this.f13666b;
    }

    public void setHeartbeatPoll(long j2) {
        this.f13666b = j2;
    }

    public long getPollInterval() {
        return this.f13667c;
    }

    public void setPoll(long j2) {
        this.f13667c = j2;
    }

    public long getBackgroundSyncPollInterval() {
        return this.f13668d;
    }

    public void setBackgroundHeartbeatPoll(long j2) {
        this.f13668d = j2;
    }

    public long getBackgroundPollInterval() {
        return this.f13669e;
    }

    public void setBackgroundPoll(long j2) {
        this.f13669e = j2;
    }

    public long getReportSizeLimit() {
        return this.f13670f;
    }

    public void setReportSizeLimit(long j2) {
        this.f13670f = j2;
    }

    public long getSubmitSizeLimit() {
        return this.f13671g;
    }

    public void setSubmitSizeLimit(long j2) {
        this.f13671g = j2;
    }

    public long getEventSendDelay() {
        return this.f13672h;
    }

    public void setEventSendDelay(long j2) {
        this.f13672h = j2;
    }

    public boolean wsFirst() {
        return getChannel() <= 0;
    }

    public void setDbDataMaxSize(long j2) {
        this.f13673i = j2;
    }

    public long getDBDataMaxSize() {
        return this.f13673i;
    }
}
