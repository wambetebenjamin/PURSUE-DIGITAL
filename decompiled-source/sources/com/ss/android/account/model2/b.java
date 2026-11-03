package com.ss.android.account.model2;
/* compiled from: LoginInfoPlatformEntity.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private String f17918a;

    /* renamed from: b  reason: collision with root package name */
    private String f17919b;

    /* renamed from: c  reason: collision with root package name */
    private String f17920c;

    public b(String str, String str2, String str3) {
        this.f17918a = str;
        this.f17919b = str2;
        this.f17920c = str3;
    }

    public String getPlatform() {
        return this.f17918a;
    }

    public void setPlatform(String str) {
        this.f17918a = str;
    }

    public String getPlatformScreenName() {
        return this.f17919b;
    }

    public String getProfileImageUrl() {
        return this.f17920c;
    }

    public String toString() {
        return "LoginInfoPlatformEntity{platform='" + this.f17918a + "', platformScreenName='" + this.f17919b + "', profileImageUrl='" + this.f17920c + "'}";
    }
}
