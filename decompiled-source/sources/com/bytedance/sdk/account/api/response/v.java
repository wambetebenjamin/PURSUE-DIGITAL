package com.bytedance.sdk.account.api.response;
/* compiled from: EmailRegisterCodeVerifyResponse.java */
/* loaded from: classes2.dex */
public class v extends com.bytedance.sdk.account.api.call.b {

    /* renamed from: a  reason: collision with root package name */
    private String f12297a;

    public v(boolean z, int i2) {
        super(z, i2);
    }

    public String getEmailTicket() {
        return this.f12297a;
    }

    public void setEmailTicket(String str) {
        this.f12297a = str;
    }
}
