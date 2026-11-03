package com.bytedance.sdk.account.mobile.query;

import java.util.Map;
/* compiled from: EmailCheckRegisterQueryObj.java */
/* loaded from: classes2.dex */
public class e extends m {
    public int is_registered;
    public String mEmail;
    public Map mExtendInfo;
    public String mVersion;

    public e(String str, Map map, String str2) {
        this.mEmail = str;
        this.mExtendInfo = map;
        this.mVersion = str2;
    }

    public boolean isEmailRegister() {
        return this.is_registered == 1;
    }
}
