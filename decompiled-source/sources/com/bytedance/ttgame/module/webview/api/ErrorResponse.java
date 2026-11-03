package com.bytedance.ttgame.module.webview.api;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: WebViewData.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/module/webview/api/ErrorResponse;", "", "code", "", "message", "", "(ILjava/lang/String;)V", "getCode", "()I", "getMessage", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "webview_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class ErrorResponse {
    private final int code;
    private final String message;

    public static /* synthetic */ ErrorResponse copy$default(ErrorResponse errorResponse, int i2, String str, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = errorResponse.code;
        }
        if ((i3 & 2) != 0) {
            str = errorResponse.message;
        }
        return errorResponse.copy(i2, str);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final ErrorResponse copy(int i2, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new ErrorResponse(i2, message);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ErrorResponse) {
            ErrorResponse errorResponse = (ErrorResponse) obj;
            return this.code == errorResponse.code && Intrinsics.areEqual(this.message, errorResponse.message);
        }
        return false;
    }

    public int hashCode() {
        return (Integer.hashCode(this.code) * 31) + this.message.hashCode();
    }

    public String toString() {
        return "ErrorResponse(code=" + this.code + ", message=" + this.message + ')';
    }

    public ErrorResponse(int i2, String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.code = i2;
        this.message = message;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }
}
