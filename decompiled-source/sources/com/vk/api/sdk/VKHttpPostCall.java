package com.vk.api.sdk;

import android.net.Uri;
import com.bytedance.common.process.cross.a;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import com.vk.api.sdk.internal.HttpMultipartEntry;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
/* compiled from: VKHttpPostCall.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\b\u0016\u0018\u00002\u00020\u0001:\u0001\u0016B\u000f\b\u0014\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0013\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0017"}, d2 = {"Lcom/vk/api/sdk/VKHttpPostCall;", "", "b", "Lcom/vk/api/sdk/VKHttpPostCall$Builder;", "(Lcom/vk/api/sdk/VKHttpPostCall$Builder;)V", "parts", "", "", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "getParts", "()Ljava/util/Map;", DBDefinition.RETRY_COUNT, "", "getRetryCount", "()I", "timeoutMs", "", "getTimeoutMs", "()J", "url", "getUrl", "()Ljava/lang/String;", "Builder", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKHttpPostCall {
    private final Map<String, HttpMultipartEntry> parts;
    private final int retryCount;
    private final long timeoutMs;
    private final String url;

    /* compiled from: VKHttpPostCall.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 H\u0016J \u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0005H\u0016J\u0018\u0010\u001d\u001a\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u0005H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0010\u0010\r\u001a\u00020\u00002\u0006\u0010%\u001a\u00020\fH\u0016J\u0010\u0010&\u001a\u00020\u00002\u0006\u0010&\u001a\u00020\u0012H\u0016J\u0010\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0005H\u0016R<\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR$\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0012@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R$\u0010\u0018\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006'"}, d2 = {"Lcom/vk/api/sdk/VKHttpPostCall$Builder;", "", "()V", "<set-?>", "", "", "Lcom/vk/api/sdk/internal/HttpMultipartEntry;", "parts", "getParts", "()Ljava/util/Map;", "setParts", "(Ljava/util/Map;)V", "", DBDefinition.RETRY_COUNT, "getRetryCount", "()I", "setRetryCount", "(I)V", "", "timeoutMs", "getTimeoutMs", "()J", "setTimeoutMs", "(J)V", "url", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", a.COL_ARGS, "key", "fileUri", "Landroid/net/Uri;", "fileName", "value", "build", "Lcom/vk/api/sdk/VKHttpPostCall;", "count", "timeout", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static class Builder {
        private long timeoutMs;
        private String url = "";
        private Map<String, HttpMultipartEntry> parts = new HashMap();
        private int retryCount = Integer.MAX_VALUE;

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

        private final void setRetryCount(int i2) {
            this.retryCount = i2;
        }

        public final int getRetryCount() {
            return this.retryCount;
        }

        private final void setTimeoutMs(long j2) {
            this.timeoutMs = j2;
        }

        public final long getTimeoutMs() {
            return this.timeoutMs;
        }

        public Builder url(String url) {
            Intrinsics.checkParameterIsNotNull(url, "url");
            this.url = url;
            return this;
        }

        public Builder args(String key, String value) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(value, "value");
            this.parts.put(key, new HttpMultipartEntry.Text(value));
            return this;
        }

        public Builder args(String key, Uri fileUri) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(fileUri, "fileUri");
            this.parts.put(key, new HttpMultipartEntry.File(fileUri));
            return this;
        }

        public Builder args(String key, Uri fileUri, String fileName) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            Intrinsics.checkParameterIsNotNull(fileUri, "fileUri");
            Intrinsics.checkParameterIsNotNull(fileName, "fileName");
            this.parts.put(key, new HttpMultipartEntry.File(fileUri, fileName));
            return this;
        }

        public Builder retryCount(int i2) {
            this.retryCount = i2;
            return this;
        }

        public Builder timeout(long j2) {
            this.timeoutMs = j2;
            return this;
        }

        public VKHttpPostCall build() {
            return new VKHttpPostCall(this);
        }
    }

    public final String getUrl() {
        return this.url;
    }

    public final Map<String, HttpMultipartEntry> getParts() {
        return this.parts;
    }

    public final int getRetryCount() {
        return this.retryCount;
    }

    public final long getTimeoutMs() {
        return this.timeoutMs;
    }

    protected VKHttpPostCall(Builder b2) {
        Intrinsics.checkParameterIsNotNull(b2, "b");
        if (StringsKt.isBlank(b2.getUrl())) {
            throw new IllegalArgumentException("Illegal url value: " + b2.getUrl());
        }
        if (b2.getTimeoutMs() < 0) {
            throw new IllegalArgumentException("Illegal timeout value: " + b2.getTimeoutMs());
        }
        this.url = b2.getUrl();
        this.parts = b2.getParts();
        this.retryCount = b2.getRetryCount();
        this.timeoutMs = b2.getTimeoutMs();
    }
}
