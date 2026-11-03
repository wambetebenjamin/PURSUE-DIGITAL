package com.vk.api.sdk;

import com.bytedance.common.process.cross.a;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VKMethodCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0017B\u000f\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/vk/api/sdk/VKMethodCall;", "", "b", "Lcom/vk/api/sdk/VKMethodCall$Builder;", "(Lcom/vk/api/sdk/VKMethodCall$Builder;)V", a.COL_ARGS, "", "", "getArgs", "()Ljava/util/Map;", "method", "getMethod", "()Ljava/lang/String;", DBDefinition.RETRY_COUNT, "", "getRetryCount", "()I", "skipValidation", "", "getSkipValidation", "()Z", "version", "getVersion", "Builder", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKMethodCall {
    private final Map<String, String> args;
    private final String method;
    private final int retryCount;
    private final boolean skipValidation;
    private final String version;

    /* compiled from: VKMethodCall.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0001H\u0016J\u0018\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0016J\u001c\u0010\u0006\u001a\u00020\u00002\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00002\u0006\u0010$\u001a\u00020\u0010H\u0016J\u0010\u0010\u0017\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\u0016H\u0016J\u0010\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u0005H\u0016R<\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R$\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0003\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR$\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u000f¨\u0006&"}, d2 = {"Lcom/vk/api/sdk/VKMethodCall$Builder;", "", "()V", "<set-?>", "", "", a.COL_ARGS, "getArgs", "()Ljava/util/Map;", "setArgs", "(Ljava/util/Map;)V", "method", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "", DBDefinition.RETRY_COUNT, "getRetryCount", "()I", "setRetryCount", "(I)V", "", "skipValidation", "getSkipValidation", "()Z", "setSkipValidation", "(Z)V", "version", "getVersion", "setVersion", "key", "value", "", "build", "Lcom/vk/api/sdk/VKMethodCall;", "count", "skip", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static class Builder {
        private boolean skipValidation;
        private String method = "";
        private String version = "";
        private Map<String, String> args = new LinkedHashMap();
        private int retryCount = Integer.MAX_VALUE;

        private final void setMethod(String str) {
            this.method = str;
        }

        public final String getMethod() {
            return this.method;
        }

        private final void setVersion(String str) {
            this.version = str;
        }

        public final String getVersion() {
            return this.version;
        }

        private final void setArgs(Map<String, String> map) {
            this.args = map;
        }

        public final Map<String, String> getArgs() {
            return this.args;
        }

        private final void setRetryCount(int i2) {
            this.retryCount = i2;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }

        private final void setSkipValidation(boolean z) {
            this.skipValidation = z;
        }

        public final boolean getSkipValidation() {
            return this.skipValidation;
        }

        public Builder method(String method) {
            Intrinsics.checkParameterIsNotNull(method, "method");
            this.method = method;
            return this;
        }

        public Builder version(String version) {
            Intrinsics.checkParameterIsNotNull(version, "version");
            this.version = version;
            return this;
        }

        public Builder args(Map<String, String> args) {
            Intrinsics.checkParameterIsNotNull(args, "args");
            this.args.putAll(args);
            return this;
        }

        public Builder args(String key, String value) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.args.put(key, value);
            return this;
        }

        public Builder args(String key, Object value) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.args.put(key, value.toString());
            return this;
        }

        public Builder retryCount(int i2) {
            this.retryCount = i2;
            return this;
        }

        public Builder skipValidation(boolean z) {
            this.skipValidation = z;
            return this;
        }

        public VKMethodCall build() {
            return new VKMethodCall(this);
        }
    }

    public final String getMethod() {
        return this.method;
    }

    public final String getVersion() {
        return this.version;
    }

    public final Map<String, String> getArgs() {
        return this.args;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final boolean getSkipValidation() {
        return this.skipValidation;
    }

    protected VKMethodCall(Builder b2) {
        Intrinsics.checkParameterIsNotNull(b2, "b");
        if (StringsKt.isBlank(b2.getMethod())) {
            throw new IllegalArgumentException("method is null or empty");
        }
        if (StringsKt.isBlank(b2.getVersion())) {
            throw new IllegalArgumentException("version is null or empty");
        }
        this.method = b2.getMethod();
        this.version = b2.getVersion();
        this.args = b2.getArgs();
        this.retryCount = b2.getRetryCount();
        this.skipValidation = b2.getSkipValidation();
    }
}
