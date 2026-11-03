package com.bytedance.sdk.account.api.response;
/* compiled from: CheckQRCodeStatusResponse.java */
/* loaded from: classes2.dex */
public class p extends com.bytedance.sdk.account.api.call.h {

    /* renamed from: a  reason: collision with root package name */
    public int f12295a;
    public String appName;
    public String extra;
    public String qrCode;
    public String qrCodeUrl;
    public String redirectUrl;
    public String status;
    public String token;
    public String webName;

    public p(boolean z, int i2) {
        super(z, i2);
    }

    public com.bytedance.sdk.account.user.c getUserInfo() {
        return this.userInfo;
    }
}
