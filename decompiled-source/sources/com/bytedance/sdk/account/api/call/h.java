package com.bytedance.sdk.account.api.call;
/* compiled from: UserApiResponse.java */
/* loaded from: classes2.dex */
public class h extends b {

    /* renamed from: a  reason: collision with root package name */
    private String f12249a;
    public String authToken;
    public String confirmTip;
    public String errorTip;
    public long mCancelApplyTime;
    public String mCancelAvatarUrl;
    public String mCancelNickName;
    public long mCancelTime;
    public String mCancelToken;
    public com.bytedance.sdk.account.sso.c mConflictUser;
    public String mSharkTicket;
    public String notLoginTicket;
    public com.bytedance.sdk.account.user.c userInfo;
    public String verifyTicket;

    public h(boolean z, int i2) {
        super(z, i2);
    }

    public String getProfileKey() {
        return this.f12249a;
    }

    public void setProfileKey(String str) {
        this.f12249a = str;
    }
}
