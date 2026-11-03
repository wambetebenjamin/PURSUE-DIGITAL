package com.vk.api.sdk;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: OauthHttpUrlPostCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/vk/api/sdk/OauthHttpUrlPostCall;", "", "url", "", "timeoutMs", "", "retryCountOnBackendError", "", "(Ljava/lang/String;JI)V", "getRetryCountOnBackendError", "()I", "getTimeoutMs", "()J", "getUrl", "()Ljava/lang/String;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class OauthHttpUrlPostCall {
    private final int retryCountOnBackendError;
    private final long timeoutMs;
    private final String url;

    public OauthHttpUrlPostCall(String url, long j2, int i2) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.url = url;
        this.timeoutMs = j2;
        this.retryCountOnBackendError = i2;
    }

    public /* synthetic */ OauthHttpUrlPostCall(String str, long j2, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? 0L : j2, (i3 & 4) != 0 ? 0 : i2);
    }

    public final int getRetryCountOnBackendError() {
        return this.retryCountOnBackendError;
    }

    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    public final String getUrl() {
        return this.url;
    }
}
