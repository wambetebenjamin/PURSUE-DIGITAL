package com.bytedance.ttgame.module.cdkey.api;

import com.bytedance.ttgame.channel.account.ErrorConstants;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: Models.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0006\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/bytedance/ttgame/module/cdkey/api/CDkeyExchangeResult;", "", "code", "", "message", "", "extraCode", "(ILjava/lang/String;I)V", "getCode", "()I", "getExtraCode", "getMessage", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "main_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CDkeyExchangeResult {
    @SerializedName("code")
    private final int code;
    @SerializedName(ErrorConstants.EXTRA_ERROR_CODE)
    private final int extraCode;
    @SerializedName("message")
    private final String message;

    public static /* synthetic */ CDkeyExchangeResult copy$default(CDkeyExchangeResult cDkeyExchangeResult, int i2, String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            i2 = cDkeyExchangeResult.code;
        }
        if ((i4 & 2) != 0) {
            str = cDkeyExchangeResult.message;
        }
        if ((i4 & 4) != 0) {
            i3 = cDkeyExchangeResult.extraCode;
        }
        return cDkeyExchangeResult.copy(i2, str, i3);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final int component3() {
        return this.extraCode;
    }

    public final CDkeyExchangeResult copy(int i2, String message, int i3) {
        Intrinsics.checkNotNullParameter(message, "message");
        return new CDkeyExchangeResult(i2, message, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof CDkeyExchangeResult) {
            CDkeyExchangeResult cDkeyExchangeResult = (CDkeyExchangeResult) obj;
            return this.code == cDkeyExchangeResult.code && Intrinsics.areEqual(this.message, cDkeyExchangeResult.message) && this.extraCode == cDkeyExchangeResult.extraCode;
        }
        return false;
    }

    public int hashCode() {
        return (((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + Integer.hashCode(this.extraCode);
    }

    public String toString() {
        return "CDkeyExchangeResult(code=" + this.code + ", message=" + this.message + ", extraCode=" + this.extraCode + ')';
    }

    public CDkeyExchangeResult(int i2, String message, int i3) {
        Intrinsics.checkNotNullParameter(message, "message");
        this.code = i2;
        this.message = message;
        this.extraCode = i3;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final int getExtraCode() {
        return this.extraCode;
    }
}
