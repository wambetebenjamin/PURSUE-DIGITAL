package com.vk.api.sdk.okhttp;

import android.content.Context;
import com.bytedance.bdturing.setting.g;
import com.bytedance.sdk.account.platform.base.i;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.VKOkHttpProvider;
import com.vk.api.sdk.internal.Validation;
import com.vk.api.sdk.utils.log.Logger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: OkHttpExecutorConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010'\u001a\u00020\u0006H\u0016R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u001c8F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020 8F¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0011\u0010#\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b$\u0010\u0014R\u0013\u0010%\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b&\u0010\b¨\u0006("}, d2 = {"Lcom/vk/api/sdk/okhttp/OkHttpExecutorConfig;", "", "apiConfig", "Lcom/vk/api/sdk/VKApiConfig;", "(Lcom/vk/api/sdk/VKApiConfig;)V", i.CT_KEY_ACCESS_TOKEN, "", "getAccessToken", "()Ljava/lang/String;", RemoteConfigConstants.RequestFieldKey.APP_ID, "", "getAppId", "()I", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "defaultTimeoutMs", "", "getDefaultTimeoutMs", "()J", g.HOST_KEY, "getHost", "logFilterCredentials", "", "getLogFilterCredentials", "()Z", "logger", "Lcom/vk/api/sdk/utils/log/Logger;", "getLogger", "()Lcom/vk/api/sdk/utils/log/Logger;", "okHttpProvider", "Lcom/vk/api/sdk/VKOkHttpProvider;", "getOkHttpProvider", "()Lcom/vk/api/sdk/VKOkHttpProvider;", "postRequestsTimeout", "getPostRequestsTimeout", "secret", "getSecret", "toString", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class OkHttpExecutorConfig {
    private final VKApiConfig apiConfig;

    public OkHttpExecutorConfig(VKApiConfig apiConfig) {
        Intrinsics.checkParameterIsNotNull(apiConfig, "apiConfig");
        this.apiConfig = apiConfig;
        Validation.INSTANCE.assertContextValid(getContext());
        Validation.INSTANCE.assertHttpHostValid(getHost());
        Validation.INSTANCE.assertAccessTokenValid(getAccessToken());
    }

    public final Context getContext() {
        return this.apiConfig.getContext();
    }

    public final int getAppId() {
        return this.apiConfig.getAppId();
    }

    public final String getHost() {
        return this.apiConfig.getHttpApiHost().getValue();
    }

    public final String getAccessToken() {
        return this.apiConfig.getAccessToken().getValue();
    }

    public final String getSecret() {
        return this.apiConfig.getSecret().getValue();
    }

    public final VKOkHttpProvider getOkHttpProvider() {
        return this.apiConfig.getOkHttpProvider();
    }

    public final boolean getLogFilterCredentials() {
        return this.apiConfig.getLogFilterCredentials();
    }

    public final long getDefaultTimeoutMs() {
        return this.apiConfig.getDefaultTimeoutMs();
    }

    public final long getPostRequestsTimeout() {
        return this.apiConfig.getPostRequestsTimeout();
    }

    public final Logger getLogger() {
        return this.apiConfig.getLogger();
    }

    public String toString() {
        return "OkHttpExecutorConfig(host='" + getHost() + "', accessToken='" + getAccessToken() + "', secret='" + getSecret() + "', logFilterCredentials=" + getLogFilterCredentials() + ')';
    }
}
