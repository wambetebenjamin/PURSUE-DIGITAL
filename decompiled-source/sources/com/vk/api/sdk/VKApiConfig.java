package com.vk.api.sdk;

import android.content.Context;
import com.bytedance.sdk.account.platform.base.i;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.vk.api.sdk.VKOkHttpProvider;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.utils.log.DefaultApiLogger;
import com.vk.api.sdk.utils.log.Logger;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: VKApiConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b2\b\u0086\b\u0018\u0000 G2\u00020\u0001:\u0001GB³\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0012\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0016\u0012\b\b\u0002\u0010\u0017\u001a\u00020\u0005\u0012\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\b\b\u0002\u0010\u0019\u001a\u00020\n¢\u0006\u0002\u0010\u001aJ\t\u00103\u001a\u00020\u0003HÆ\u0003J\u000f\u00104\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\u0011\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tHÆ\u0003J\t\u00106\u001a\u00020\u0016HÆ\u0003J\t\u00107\u001a\u00020\u0005HÆ\u0003J\u000f\u00108\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u00109\u001a\u00020\nHÆ\u0003J\t\u0010:\u001a\u00020\u0005HÆ\u0003J\u000b\u0010;\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000f\u0010<\u001a\b\u0012\u0004\u0012\u00020\n0\tHÆ\u0003J\t\u0010=\u001a\u00020\nHÆ\u0003J\t\u0010>\u001a\u00020\rHÆ\u0003J\t\u0010?\u001a\u00020\u000fHÆ\u0003J\t\u0010@\u001a\u00020\u000fHÆ\u0003J\t\u0010A\u001a\u00020\u0012HÆ\u0003J»\u0001\u0010B\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t2\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00052\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u0019\u001a\u00020\nHÆ\u0001J\u0013\u0010C\u001a\u00020\u00162\b\u0010D\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010E\u001a\u00020\u0005HÖ\u0001J\t\u0010F\u001a\u00020\nHÖ\u0001R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0017\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001cR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001cR\u0011\u0010\u0019\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b,\u0010-R\u0011\u0010\u0010\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b.\u0010#R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001cR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u0011\u0010\u000b\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b2\u0010'¨\u0006H"}, d2 = {"Lcom/vk/api/sdk/VKApiConfig;", "", "context", "Landroid/content/Context;", RemoteConfigConstants.RequestFieldKey.APP_ID, "", "validationHandler", "Lcom/vk/api/sdk/VKApiValidationHandler;", "deviceId", "Lkotlin/Lazy;", "", "version", "okHttpProvider", "Lcom/vk/api/sdk/VKOkHttpProvider;", "defaultTimeoutMs", "", "postRequestsTimeout", "logger", "Lcom/vk/api/sdk/utils/log/Logger;", i.CT_KEY_ACCESS_TOKEN, "secret", "logFilterCredentials", "", "callsPerSecondLimit", "httpApiHost", VKApiCodes.PARAM_LANG, "(Landroid/content/Context;ILcom/vk/api/sdk/VKApiValidationHandler;Lkotlin/Lazy;Ljava/lang/String;Lcom/vk/api/sdk/VKOkHttpProvider;JJLcom/vk/api/sdk/utils/log/Logger;Lkotlin/Lazy;Lkotlin/Lazy;ZILkotlin/Lazy;Ljava/lang/String;)V", "getAccessToken", "()Lkotlin/Lazy;", "getAppId", "()I", "getCallsPerSecondLimit", "getContext", "()Landroid/content/Context;", "getDefaultTimeoutMs", "()J", "getDeviceId", "getHttpApiHost", "getLang", "()Ljava/lang/String;", "getLogFilterCredentials", "()Z", "getLogger", "()Lcom/vk/api/sdk/utils/log/Logger;", "getOkHttpProvider", "()Lcom/vk/api/sdk/VKOkHttpProvider;", "getPostRequestsTimeout", "getSecret", "getValidationHandler", "()Lcom/vk/api/sdk/VKApiValidationHandler;", "getVersion", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class VKApiConfig {
    public static final Companion Companion = new Companion(null);
    public static final String DEFAULT_API_DOMAIN = "api.vk.com";
    public static final String DEFAULT_API_VERSION = "5.90";
    public static final String DEFAULT_DOMAIN = "vk.com";
    private final Lazy<String> accessToken;
    private final int appId;
    private final int callsPerSecondLimit;
    private final Context context;
    private final long defaultTimeoutMs;
    private final Lazy<String> deviceId;
    private final Lazy<String> httpApiHost;
    private final String lang;
    private final boolean logFilterCredentials;
    private final Logger logger;
    private final VKOkHttpProvider okHttpProvider;
    private final long postRequestsTimeout;
    private final Lazy<String> secret;
    private final VKApiValidationHandler validationHandler;
    private final String version;

    public final Context component1() {
        return this.context;
    }

    public final Lazy<String> component10() {
        return this.accessToken;
    }

    public final Lazy<String> component11() {
        return this.secret;
    }

    public final boolean component12() {
        return this.logFilterCredentials;
    }

    public final int component13() {
        return this.callsPerSecondLimit;
    }

    public final Lazy<String> component14() {
        return this.httpApiHost;
    }

    public final String component15() {
        return this.lang;
    }

    public final int component2() {
        return this.appId;
    }

    public final VKApiValidationHandler component3() {
        return this.validationHandler;
    }

    public final Lazy<String> component4() {
        return this.deviceId;
    }

    public final String component5() {
        return this.version;
    }

    public final VKOkHttpProvider component6() {
        return this.okHttpProvider;
    }

    public final long component7() {
        return this.defaultTimeoutMs;
    }

    public final long component8() {
        return this.postRequestsTimeout;
    }

    public final Logger component9() {
        return this.logger;
    }

    public final VKApiConfig copy(Context context, int i2, VKApiValidationHandler vKApiValidationHandler, Lazy<String> deviceId, String version, VKOkHttpProvider okHttpProvider, long j2, long j3, Logger logger, Lazy<String> accessToken, Lazy<String> secret, boolean z, int i3, Lazy<String> httpApiHost, String lang) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceId, "deviceId");
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(okHttpProvider, "okHttpProvider");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        Intrinsics.checkParameterIsNotNull(httpApiHost, "httpApiHost");
        Intrinsics.checkParameterIsNotNull(lang, "lang");
        return new VKApiConfig(context, i2, vKApiValidationHandler, deviceId, version, okHttpProvider, j2, j3, logger, accessToken, secret, z, i3, httpApiHost, lang);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj instanceof VKApiConfig) {
                VKApiConfig vKApiConfig = (VKApiConfig) obj;
                if (Intrinsics.areEqual(this.context, vKApiConfig.context)) {
                    if ((this.appId == vKApiConfig.appId) && Intrinsics.areEqual(this.validationHandler, vKApiConfig.validationHandler) && Intrinsics.areEqual(this.deviceId, vKApiConfig.deviceId) && Intrinsics.areEqual(this.version, vKApiConfig.version) && Intrinsics.areEqual(this.okHttpProvider, vKApiConfig.okHttpProvider)) {
                        if (this.defaultTimeoutMs == vKApiConfig.defaultTimeoutMs) {
                            if ((this.postRequestsTimeout == vKApiConfig.postRequestsTimeout) && Intrinsics.areEqual(this.logger, vKApiConfig.logger) && Intrinsics.areEqual(this.accessToken, vKApiConfig.accessToken) && Intrinsics.areEqual(this.secret, vKApiConfig.secret)) {
                                if (this.logFilterCredentials == vKApiConfig.logFilterCredentials) {
                                    if (!(this.callsPerSecondLimit == vKApiConfig.callsPerSecondLimit) || !Intrinsics.areEqual(this.httpApiHost, vKApiConfig.httpApiHost) || !Intrinsics.areEqual(this.lang, vKApiConfig.lang)) {
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        Context context = this.context;
        int hashCode = (((context != null ? context.hashCode() : 0) * 31) + this.appId) * 31;
        VKApiValidationHandler vKApiValidationHandler = this.validationHandler;
        int hashCode2 = (hashCode + (vKApiValidationHandler != null ? vKApiValidationHandler.hashCode() : 0)) * 31;
        Lazy<String> lazy = this.deviceId;
        int hashCode3 = (hashCode2 + (lazy != null ? lazy.hashCode() : 0)) * 31;
        String str = this.version;
        int hashCode4 = (hashCode3 + (str != null ? str.hashCode() : 0)) * 31;
        VKOkHttpProvider vKOkHttpProvider = this.okHttpProvider;
        int hashCode5 = vKOkHttpProvider != null ? vKOkHttpProvider.hashCode() : 0;
        long j2 = this.defaultTimeoutMs;
        long j3 = this.postRequestsTimeout;
        int i2 = (((((hashCode4 + hashCode5) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        Logger logger = this.logger;
        int hashCode6 = (i2 + (logger != null ? logger.hashCode() : 0)) * 31;
        Lazy<String> lazy2 = this.accessToken;
        int hashCode7 = (hashCode6 + (lazy2 != null ? lazy2.hashCode() : 0)) * 31;
        Lazy<String> lazy3 = this.secret;
        int hashCode8 = (hashCode7 + (lazy3 != null ? lazy3.hashCode() : 0)) * 31;
        boolean z = this.logFilterCredentials;
        int i3 = z;
        if (z != 0) {
            i3 = 1;
        }
        int i4 = (((hashCode8 + i3) * 31) + this.callsPerSecondLimit) * 31;
        Lazy<String> lazy4 = this.httpApiHost;
        int hashCode9 = (i4 + (lazy4 != null ? lazy4.hashCode() : 0)) * 31;
        String str2 = this.lang;
        return hashCode9 + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "VKApiConfig(context=" + this.context + ", appId=" + this.appId + ", validationHandler=" + this.validationHandler + ", deviceId=" + this.deviceId + ", version=" + this.version + ", okHttpProvider=" + this.okHttpProvider + ", defaultTimeoutMs=" + this.defaultTimeoutMs + ", postRequestsTimeout=" + this.postRequestsTimeout + ", logger=" + this.logger + ", accessToken=" + this.accessToken + ", secret=" + this.secret + ", logFilterCredentials=" + this.logFilterCredentials + ", callsPerSecondLimit=" + this.callsPerSecondLimit + ", httpApiHost=" + this.httpApiHost + ", lang=" + this.lang + ")";
    }

    public VKApiConfig(Context context, int i2, VKApiValidationHandler vKApiValidationHandler, Lazy<String> deviceId, String version, VKOkHttpProvider okHttpProvider, long j2, long j3, Logger logger, Lazy<String> accessToken, Lazy<String> secret, boolean z, int i3, Lazy<String> httpApiHost, String lang) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(deviceId, "deviceId");
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(okHttpProvider, "okHttpProvider");
        Intrinsics.checkParameterIsNotNull(logger, "logger");
        Intrinsics.checkParameterIsNotNull(accessToken, "accessToken");
        Intrinsics.checkParameterIsNotNull(secret, "secret");
        Intrinsics.checkParameterIsNotNull(httpApiHost, "httpApiHost");
        Intrinsics.checkParameterIsNotNull(lang, "lang");
        this.context = context;
        this.appId = i2;
        this.validationHandler = vKApiValidationHandler;
        this.deviceId = deviceId;
        this.version = version;
        this.okHttpProvider = okHttpProvider;
        this.defaultTimeoutMs = j2;
        this.postRequestsTimeout = j3;
        this.logger = logger;
        this.accessToken = accessToken;
        this.secret = secret;
        this.logFilterCredentials = z;
        this.callsPerSecondLimit = i3;
        this.httpApiHost = httpApiHost;
        this.lang = lang;
    }

    public final Context getContext() {
        return this.context;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final VKApiValidationHandler getValidationHandler() {
        return this.validationHandler;
    }

    public /* synthetic */ VKApiConfig(Context context, int i2, VKApiValidationHandler vKApiValidationHandler, Lazy lazy, String str, VKOkHttpProvider vKOkHttpProvider, long j2, long j3, Logger logger, Lazy lazy2, Lazy lazy3, boolean z, int i3, Lazy lazy4, String str2, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i4 & 2) != 0 ? 0 : i2, vKApiValidationHandler, (i4 & 8) != 0 ? LazyKt.lazy(new Function0<String>() { // from class: com.vk.api.sdk.VKApiConfig.1
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "";
            }
        }) : lazy, (i4 & 16) != 0 ? DEFAULT_API_VERSION : str, (i4 & 32) != 0 ? new VKOkHttpProvider.DefaultProvider() : vKOkHttpProvider, (i4 & 64) != 0 ? TimeUnit.SECONDS.toMillis(10L) : j2, (i4 & 128) != 0 ? TimeUnit.MINUTES.toMillis(5L) : j3, (i4 & 256) != 0 ? new DefaultApiLogger(LazyKt.lazy(new Function0<Logger.LogLevel>() { // from class: com.vk.api.sdk.VKApiConfig.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Logger.LogLevel invoke() {
                return Logger.LogLevel.NONE;
            }
        }), "VKSdkApi") : logger, (i4 & 512) != 0 ? LazyKt.lazy(new Function0<String>() { // from class: com.vk.api.sdk.VKApiConfig.3
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "";
            }
        }) : lazy2, (i4 & 1024) != 0 ? LazyKt.lazy(new Function0() { // from class: com.vk.api.sdk.VKApiConfig.4
            @Override // kotlin.jvm.functions.Function0
            public final Void invoke() {
                return null;
            }
        }) : lazy3, (i4 & 2048) != 0 ? true : z, (i4 & 4096) != 0 ? 3 : i3, (i4 & 8192) != 0 ? LazyKt.lazy(new Function0<String>() { // from class: com.vk.api.sdk.VKApiConfig.5
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return VKApiConfig.DEFAULT_API_DOMAIN;
            }
        }) : lazy4, (i4 & 16384) != 0 ? "en" : str2);
    }

    public final Lazy<String> getDeviceId() {
        return this.deviceId;
    }

    public final String getVersion() {
        return this.version;
    }

    public final VKOkHttpProvider getOkHttpProvider() {
        return this.okHttpProvider;
    }

    public final long getDefaultTimeoutMs() {
        return this.defaultTimeoutMs;
    }

    public final long getPostRequestsTimeout() {
        return this.postRequestsTimeout;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final Lazy<String> getAccessToken() {
        return this.accessToken;
    }

    public final Lazy<String> getSecret() {
        return this.secret;
    }

    public final boolean getLogFilterCredentials() {
        return this.logFilterCredentials;
    }

    public final int getCallsPerSecondLimit() {
        return this.callsPerSecondLimit;
    }

    public final Lazy<String> getHttpApiHost() {
        return this.httpApiHost;
    }

    public final String getLang() {
        return this.lang;
    }

    /* compiled from: VKApiConfig.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/vk/api/sdk/VKApiConfig$Companion;", "", "()V", "DEFAULT_API_DOMAIN", "", "DEFAULT_API_VERSION", "DEFAULT_DOMAIN", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
