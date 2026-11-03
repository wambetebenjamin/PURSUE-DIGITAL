package com.bytedance.ttgame.channel.minors;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: MinorsLimit.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u0018\u001a\u00020\u0015J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u001a"}, d2 = {"Lcom/bytedance/ttgame/channel/minors/MinorsLimitResponse;", "", "code", "", "message", "", "data", "encryptMessage", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getData", "()Ljava/lang/String;", "getEncryptMessage", "getMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "isSuccess", "toString", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MinorsLimitResponse {
    @SerializedName("code")
    private final int code;
    @SerializedName("data")
    private final String data;
    @SerializedName("encrypt_ret_msg")
    private final String encryptMessage;
    @SerializedName("message")
    private final String message;

    public static /* synthetic */ MinorsLimitResponse copy$default(MinorsLimitResponse minorsLimitResponse, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = minorsLimitResponse.code;
        }
        if ((i3 & 2) != 0) {
            str = minorsLimitResponse.message;
        }
        if ((i3 & 4) != 0) {
            str2 = minorsLimitResponse.data;
        }
        if ((i3 & 8) != 0) {
            str3 = minorsLimitResponse.encryptMessage;
        }
        return minorsLimitResponse.copy(i2, str, str2, str3);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final String component3() {
        return this.data;
    }

    public final String component4() {
        return this.encryptMessage;
    }

    public final MinorsLimitResponse copy(int i2, String message, String data, String encryptMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(encryptMessage, "encryptMessage");
        return new MinorsLimitResponse(i2, message, data, encryptMessage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MinorsLimitResponse) {
            MinorsLimitResponse minorsLimitResponse = (MinorsLimitResponse) obj;
            return this.code == minorsLimitResponse.code && Intrinsics.areEqual(this.message, minorsLimitResponse.message) && Intrinsics.areEqual(this.data, minorsLimitResponse.data) && Intrinsics.areEqual(this.encryptMessage, minorsLimitResponse.encryptMessage);
        }
        return false;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + this.data.hashCode()) * 31) + this.encryptMessage.hashCode();
    }

    public String toString() {
        return "MinorsLimitResponse(code=" + this.code + ", message=" + this.message + ", data=" + this.data + ", encryptMessage=" + this.encryptMessage + ')';
    }

    public MinorsLimitResponse(int i2, String message, String data, String encryptMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(data, "data");
        Intrinsics.checkNotNullParameter(encryptMessage, "encryptMessage");
        this.code = i2;
        this.message = message;
        this.data = data;
        this.encryptMessage = encryptMessage;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getData() {
        return this.data;
    }

    public final String getEncryptMessage() {
        return this.encryptMessage;
    }

    public final boolean isSuccess() {
        return this.code == 0 && (Intrinsics.areEqual(this.message, "success") || Intrinsics.areEqual("Success", this.message));
    }
}
