package com.twitter.sdk.android.core;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes10.dex */
public abstract class AuthToken {
    @SerializedName("created_at")
    protected final long createdAt;

    public abstract boolean isExpired();

    public AuthToken() {
        this(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AuthToken(long j2) {
        this.createdAt = j2;
    }
}
