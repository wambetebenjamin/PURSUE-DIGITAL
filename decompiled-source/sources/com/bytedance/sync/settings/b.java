package com.bytedance.sync.settings;

import com.facebook.react.bridge.BaseJavaModule;
import com.google.gson.annotations.SerializedName;
/* compiled from: SettingsV2.java */
/* loaded from: classes3.dex */
public class b {
    @SerializedName("version")

    /* renamed from: a  reason: collision with root package name */
    private int f13674a;
    @SerializedName("channel")

    /* renamed from: b  reason: collision with root package name */
    private int f13675b;
    @SerializedName(BaseJavaModule.METHOD_TYPE_SYNC)

    /* renamed from: c  reason: collision with root package name */
    private int f13676c = 60;
    @SerializedName("poll")

    /* renamed from: d  reason: collision with root package name */
    private int f13677d = 300;
    @SerializedName("background_sync")

    /* renamed from: e  reason: collision with root package name */
    private int f13678e = 300;
    @SerializedName("background_poll")

    /* renamed from: f  reason: collision with root package name */
    private int f13679f = 600;
    @SerializedName("report_size_limit")

    /* renamed from: g  reason: collision with root package name */
    private int f13680g = 6144;
    @SerializedName("submit_size_limit")

    /* renamed from: h  reason: collision with root package name */
    private int f13681h = 51200;
    @SerializedName("db_store_size_limit")

    /* renamed from: i  reason: collision with root package name */
    private int f13682i = 51200;
    @SerializedName("event_send_delay")

    /* renamed from: j  reason: collision with root package name */
    private long f13683j = 5;
    @SerializedName("android_ws_poll_interval")
    private long k = -1;

    public boolean wsFirst() {
        return ((long) getChannel()) <= 0;
    }

    public int getChannel() {
        return this.f13675b;
    }

    public void setChannel(int i2) {
        this.f13675b = i2;
    }

    public int getSync() {
        return this.f13676c;
    }

    public void setSync(int i2) {
        this.f13676c = i2;
    }

    public int getPoll() {
        return this.f13677d;
    }

    public void setPoll(int i2) {
        this.f13677d = i2;
    }

    public int getBackgroundSync() {
        return this.f13678e;
    }

    public void setBackgroundSync(int i2) {
        this.f13678e = i2;
    }

    public int getBackgroundPoll() {
        return this.f13679f;
    }

    public void setBackgroundPoll(int i2) {
        this.f13679f = i2;
    }

    public int getReportSizeLimit() {
        return this.f13680g;
    }

    public void setReportSizeLimit(int i2) {
        this.f13680g = i2;
    }

    public int getSubmitSizeLimit() {
        return this.f13681h;
    }

    public void setSubmitSizeLimit(int i2) {
        this.f13681h = i2;
    }

    public int getDbStoreSizeLimit() {
        return this.f13682i;
    }

    public void setDbStoreSizeLimit(int i2) {
        this.f13682i = i2;
    }

    public long getEventSendDelay() {
        return this.f13683j;
    }

    public void setEventSendDelay(long j2) {
        this.f13683j = j2;
    }

    public long getPollIntervalWhenWsConnected() {
        long j2 = this.k;
        return j2 > 0 ? j2 : getPoll();
    }

    public void setPollIntervalWhenWsConnected(long j2) {
        this.k = j2;
    }

    public boolean enablePollWhenWsConnected() {
        return this.k != 0;
    }
}
