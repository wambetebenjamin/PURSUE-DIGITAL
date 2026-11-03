package com.bytedance.sdk.account.information.method.oauth_profile;

import org.json.JSONObject;
/* compiled from: OauthProfileResponse.java */
/* loaded from: classes2.dex */
public class c extends com.bytedance.sdk.account.api.call.b {
    public String avatarUrl;
    public JSONObject extraInfo;
    public String name;

    public c(boolean z, int i2) {
        super(z, i2);
    }

    public String getName() {
        return this.name;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }
}
