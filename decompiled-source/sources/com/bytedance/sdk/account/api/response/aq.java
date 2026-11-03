package com.bytedance.sdk.account.api.response;
/* compiled from: VerifyAccountPasswordResponse.java */
/* loaded from: classes2.dex */
public class aq extends com.bytedance.sdk.account.api.call.b {

    /* renamed from: a  reason: collision with root package name */
    private String f12272a;

    /* renamed from: b  reason: collision with root package name */
    private String f12273b;

    /* renamed from: c  reason: collision with root package name */
    private String f12274c;

    public aq(boolean z, int i2) {
        super(z, i2);
    }

    public String getTicket() {
        return this.f12272a;
    }

    public void setTicket(String str) {
        this.f12272a = str;
    }

    public String getCaptcha() {
        return this.f12273b;
    }

    public void setCaptcha(String str) {
        this.f12273b = str;
    }

    public String getVerifyTicket() {
        return this.f12274c;
    }

    public void setVerifyTicket(String str) {
        this.f12274c = str;
    }
}
