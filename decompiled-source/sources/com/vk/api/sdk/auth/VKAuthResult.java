package com.vk.api.sdk.auth;

import com.bytedance.sdk.account.platform.base.i;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: VKAuthResult.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\b\u0007\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR\u0011\u0010\u000e\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/auth/VKAuthResult;", "", i.CT_KEY_ACCESS_TOKEN, "Lcom/vk/api/sdk/auth/VKAccessToken;", "errorCode", "", "(Lcom/vk/api/sdk/auth/VKAccessToken;I)V", "getAccessToken", "()Lcom/vk/api/sdk/auth/VKAccessToken;", "getErrorCode", "()I", "isError", "", "()Z", "isSuccess", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKAuthResult {
    private final VKAccessToken accessToken;
    private final int errorCode;

    public VKAuthResult(VKAccessToken vKAccessToken) {
        this(vKAccessToken, 0, 2, null);
    }

    public VKAuthResult(VKAccessToken vKAccessToken, int i2) {
        this.accessToken = vKAccessToken;
        this.errorCode = i2;
    }

    public /* synthetic */ VKAuthResult(VKAccessToken vKAccessToken, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(vKAccessToken, (i3 & 2) != 0 ? 0 : i2);
    }

    public final VKAccessToken getAccessToken() {
        return this.accessToken;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public final boolean isSuccess() {
        return !isError();
    }

    public final boolean isError() {
        return this.errorCode != 0;
    }
}
