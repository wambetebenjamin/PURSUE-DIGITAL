package com.twitter.sdk.android.core.internal.oauth;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
class GuestTokenResponse {
    @SerializedName("guest_token")
    public final String guestToken;

    GuestTokenResponse(String str) {
        this.guestToken = str;
    }
}
