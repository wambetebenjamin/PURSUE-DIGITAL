package com.ss.android.ug.bus.account.model;
/* compiled from: HistoryLoginInfo.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private long f18612a;

    /* renamed from: b  reason: collision with root package name */
    private String f18613b;

    /* renamed from: c  reason: collision with root package name */
    private String f18614c;

    /* renamed from: d  reason: collision with root package name */
    private String f18615d;

    public a(long j2, String str, String str2, String str3) {
        this.f18612a = j2;
        this.f18613b = str;
        this.f18614c = str2;
        this.f18615d = str3;
    }

    public long getUid() {
        return this.f18612a;
    }

    public void setUid(long j2) {
        this.f18612a = j2;
    }

    public String getSecUid() {
        return this.f18613b;
    }

    public void setSecUid(String str) {
        this.f18613b = str;
    }

    public String getAvatarUrl() {
        return this.f18614c;
    }

    public void setAvatarUrl(String str) {
        this.f18614c = str;
    }

    public String getScreenName() {
        return this.f18615d;
    }

    public void setScreenName(String str) {
        this.f18615d = str;
    }

    public String toString() {
        return "HistoryLoginInfo{uid=" + this.f18612a + ", secUid='" + this.f18613b + "', avatarUrl='" + this.f18614c + "', screenName='" + this.f18615d + "'}";
    }
}
