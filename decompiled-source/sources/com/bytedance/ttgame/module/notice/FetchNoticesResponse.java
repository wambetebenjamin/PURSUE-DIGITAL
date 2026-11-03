package com.bytedance.ttgame.module.notice;

import com.bytedance.ttgame.module.notice.api.ResponseData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Request.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/bytedance/ttgame/module/notice/FetchNoticesResponse;", "", "code", "", "message", "", "data", "Lcom/bytedance/ttgame/module/notice/api/ResponseData;", "(ILjava/lang/String;Lcom/bytedance/ttgame/module/notice/api/ResponseData;)V", "getCode", "()I", "getData", "()Lcom/bytedance/ttgame/module/notice/api/ResponseData;", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class FetchNoticesResponse {
    private final int code;
    private final ResponseData data;
    private final String message;

    public static /* synthetic */ FetchNoticesResponse copy$default(FetchNoticesResponse fetchNoticesResponse, int i2, String str, ResponseData responseData, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = fetchNoticesResponse.code;
        }
        if ((i3 & 2) != 0) {
            str = fetchNoticesResponse.message;
        }
        if ((i3 & 4) != 0) {
            responseData = fetchNoticesResponse.data;
        }
        return fetchNoticesResponse.copy(i2, str, responseData);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final ResponseData component3() {
        return this.data;
    }

    public final FetchNoticesResponse copy(int i2, String message, ResponseData data) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        return new FetchNoticesResponse(i2, message, data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof FetchNoticesResponse) {
            FetchNoticesResponse fetchNoticesResponse = (FetchNoticesResponse) obj;
            return this.code == fetchNoticesResponse.code && Intrinsics.areEqual(this.message, fetchNoticesResponse.message) && Intrinsics.areEqual(this.data, fetchNoticesResponse.data);
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + this.data.hashCode();
    }

    public String toString() {
        return "FetchNoticesResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ')';
    }

    public FetchNoticesResponse(int i2, String message, ResponseData data) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        this.code = i2;
        this.message = message;
        this.data = data;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final ResponseData getData() {
        return this.data;
    }
}
