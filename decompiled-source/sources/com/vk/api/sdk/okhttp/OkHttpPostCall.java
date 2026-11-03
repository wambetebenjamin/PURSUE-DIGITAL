package com.vk.api.sdk.okhttp;

import com.vk.api.sdk.VKHttpPostCall;
import com.vk.api.sdk.internal.HttpMultipartEntry;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: OkHttpPostCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\u0015B\u000f\b\u0012\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0012\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lcom/vk/api/sdk/okhttp/OkHttpPostCall;", "", "b", "Lcom/vk/api/sdk/okhttp/OkHttpPostCall$Builder;", "(Lcom/vk/api/sdk/okhttp/OkHttpPostCall$Builder;)V", "call", "Lcom/vk/api/sdk/VKHttpPostCall;", "(Lcom/vk/api/sdk/VKHttpPostCall;)V", "parts", "", "", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "getParts", "()Ljava/util/Map;", "timeoutMs", "", "getTimeoutMs", "()J", "url", "getUrl", "()Ljava/lang/String;", "Builder", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class OkHttpPostCall {
    private final Map<String, HttpMultipartEntry> parts;
    private final long timeoutMs;
    private final String url;

    public /* synthetic */ OkHttpPostCall(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    /* compiled from: OkHttpPostCall.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\u001a\u0010\u0007\u001a\u00020\u00002\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0019J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\rJ\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0012\u001a\u00020\u0005R<\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/vk/api/sdk/okhttp/OkHttpPostCall$Builder;", "", "()V", "<set-?>", "", "", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "parts", "getParts", "()Ljava/util/Map;", "setParts", "(Ljava/util/Map;)V", "timeoutMs", "", "getTimeoutMs", "()J", "setTimeoutMs", "(J)V", "url", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", "build", "Lcom/vk/api/sdk/okhttp/OkHttpPostCall;", "", "timeout", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Builder {
        private long timeoutMs;
        private String url = "";
        private Map<String, HttpMultipartEntry> parts = new HashMap();

        private final void setUrl(String str) {
            this.url = str;
        }

        public final String getUrl() {
            return this.url;
        }

        private final void setParts(Map<String, HttpMultipartEntry> map) {
            this.parts = map;
        }

        public final Map<String, HttpMultipartEntry> getParts() {
            return this.parts;
        }

        public final long getTimeoutMs() {
            return this.timeoutMs;
        }

        public final void setTimeoutMs(long j2) {
            this.timeoutMs = j2;
        }

        public final Builder url(String url) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            this.url = url;
            return this;
        }

        public final Builder parts(Map<String, ? extends HttpMultipartEntry> parts) {
            Intrinsics.checkParameterIsNotNull(parts, "parts");
            this.parts.clear();
            this.parts.putAll(parts);
            return this;
        }

        public final Builder timeout(long j2) {
            this.timeoutMs = j2;
            return this;
        }

        public final OkHttpPostCall build() {
            return new OkHttpPostCall(this, null);
        }
    }

    public final String getUrl() {
        return this.url;
    }

    public final Map<String, HttpMultipartEntry> getParts() {
        return this.parts;
    }

    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    private OkHttpPostCall(Builder builder) {
        if (StringsKt.isBlank(builder.getUrl())) {
            throw new IllegalArgumentException("Illegal url value: " + builder.getUrl());
        }
        if (builder.getTimeoutMs() <= 0) {
            throw new IllegalArgumentException("Illegal timeout value: " + builder.getTimeoutMs());
        }
        this.url = builder.getUrl();
        this.parts = builder.getParts();
        this.timeoutMs = builder.getTimeoutMs();
    }

    public OkHttpPostCall(VKHttpPostCall call) {
        Intrinsics.checkParameterIsNotNull(call, "call");
        this.url = call.getUrl();
        this.parts = call.getParts();
        this.timeoutMs = call.getTimeoutMs();
    }
}
