package com.bytedance.sdk.account.api.response;
/* compiled from: CheckMobileUnusableResponse.java */
/* loaded from: classes2.dex */
public class n extends com.bytedance.sdk.account.api.call.b {

    /* renamed from: a  reason: collision with root package name */
    private boolean f12291a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f12292b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f12293c;

    /* renamed from: d  reason: collision with root package name */
    private String f12294d;

    public n(boolean z, int i2) {
        super(z, i2);
    }

    public boolean isUnusable() {
        return this.f12291a;
    }

    public void setUnusable(boolean z) {
        this.f12291a = z;
    }

    public boolean isVerified() {
        return this.f12292b;
    }

    public void setVerified(boolean z) {
        this.f12292b = z;
    }

    public boolean isMnoSupport() {
        return this.f12293c;
    }

    public void setMnoSupport(boolean z) {
        this.f12293c = z;
    }

    public String getTicket() {
        return this.f12294d;
    }

    public void setTicket(String str) {
        this.f12294d = str;
    }
}
