package com.bytedance.sdk.account.api.response;

import java.util.List;
/* compiled from: GetAvailableWaysResponse.java */
/* loaded from: classes2.dex */
public class z extends com.bytedance.sdk.account.api.call.b {

    /* renamed from: a  reason: collision with root package name */
    private String f12299a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f12300b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12301c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f12302d;

    /* renamed from: e  reason: collision with root package name */
    private boolean f12303e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f12304f;

    /* renamed from: g  reason: collision with root package name */
    private String f12305g;

    /* renamed from: h  reason: collision with root package name */
    private List<String> f12306h;

    /* renamed from: i  reason: collision with root package name */
    private String f12307i;

    public z(boolean z, int i2) {
        super(z, i2);
    }

    public String getEmail() {
        return this.f12299a;
    }

    public void setEmail(String str) {
        this.f12299a = str;
    }

    public boolean isHasEmail() {
        return this.f12300b;
    }

    public void setHasEmail(boolean z) {
        this.f12300b = z;
    }

    public boolean isHasMobile() {
        return this.f12301c;
    }

    public void setHasMobile(boolean z) {
        this.f12301c = z;
    }

    public boolean isHasOauth() {
        return this.f12302d;
    }

    public void setHasOauth(boolean z) {
        this.f12302d = z;
    }

    public boolean isHasPwd() {
        return this.f12303e;
    }

    public void setHasPwd(boolean z) {
        this.f12303e = z;
    }

    public boolean isMostDevice() {
        return this.f12304f;
    }

    public void setMostDevice(boolean z) {
        this.f12304f = z;
    }

    public String getMobile() {
        return this.f12305g;
    }

    public void setMobile(String str) {
        this.f12305g = str;
    }

    public List<String> getOauthPlatforms() {
        return this.f12306h;
    }

    public void setOauthPlatforms(List<String> list) {
        this.f12306h = list;
    }

    public String getToken() {
        return this.f12307i;
    }

    public void setToken(String str) {
        this.f12307i = str;
    }

    @Override // com.bytedance.sdk.account.api.call.b
    public String toString() {
        return "GetAvailableWaysResponse{email='" + this.f12299a + "', hasEmail=" + this.f12300b + ", hasMobile=" + this.f12301c + ", hasOauth=" + this.f12302d + ", hasPwd=" + this.f12303e + ", isMostDevice=" + this.f12304f + ", mobile='" + this.f12305g + "', oauthPlatforms=" + this.f12306h + ", token='" + this.f12307i + "'}";
    }
}
