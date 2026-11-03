package com.bytedance.ttgame.module.compliance.api.antiaddiction;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IAntiAddictionStatusListener.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/antiaddiction/AntiAddictionStatus;", "", "code", "", "message", "", "loginMessage", "ruleMessage", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCode", "()I", "getLoginMessage", "()Ljava/lang/String;", "getMessage", "getRuleMessage", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "compliance_api_antiaddiction_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AntiAddictionStatus {
    private final int code;
    private final String loginMessage;
    private final String message;
    private final String ruleMessage;

    public static /* synthetic */ AntiAddictionStatus copy$default(AntiAddictionStatus antiAddictionStatus, int i2, String str, String str2, String str3, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i2 = antiAddictionStatus.code;
        }
        if ((i3 & 2) != 0) {
            str = antiAddictionStatus.message;
        }
        if ((i3 & 4) != 0) {
            str2 = antiAddictionStatus.loginMessage;
        }
        if ((i3 & 8) != 0) {
            str3 = antiAddictionStatus.ruleMessage;
        }
        return antiAddictionStatus.copy(i2, str, str2, str3);
    }

    public final int component1() {
        return this.code;
    }

    public final String component2() {
        return this.message;
    }

    public final String component3() {
        return this.loginMessage;
    }

    public final String component4() {
        return this.ruleMessage;
    }

    public final AntiAddictionStatus copy(int i2, String message, String loginMessage, String ruleMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(loginMessage, "loginMessage");
        Intrinsics.checkNotNullParameter(ruleMessage, "ruleMessage");
        return new AntiAddictionStatus(i2, message, loginMessage, ruleMessage);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AntiAddictionStatus) {
            AntiAddictionStatus antiAddictionStatus = (AntiAddictionStatus) obj;
            return this.code == antiAddictionStatus.code && Intrinsics.areEqual(this.message, antiAddictionStatus.message) && Intrinsics.areEqual(this.loginMessage, antiAddictionStatus.loginMessage) && Intrinsics.areEqual(this.ruleMessage, antiAddictionStatus.ruleMessage);
        }
        return false;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.code) * 31) + this.message.hashCode()) * 31) + this.loginMessage.hashCode()) * 31) + this.ruleMessage.hashCode();
    }

    public String toString() {
        return "AntiAddictionStatus(code=" + this.code + ", message=" + this.message + ", loginMessage=" + this.loginMessage + ", ruleMessage=" + this.ruleMessage + ')';
    }

    public AntiAddictionStatus(int i2, String message, String loginMessage, String ruleMessage) {
        Intrinsics.checkNotNullParameter(message, "message");
        Intrinsics.checkNotNullParameter(loginMessage, "loginMessage");
        Intrinsics.checkNotNullParameter(ruleMessage, "ruleMessage");
        this.code = i2;
        this.message = message;
        this.loginMessage = loginMessage;
        this.ruleMessage = ruleMessage;
    }

    public final int getCode() {
        return this.code;
    }

    public final String getMessage() {
        return this.message;
    }

    public final String getLoginMessage() {
        return this.loginMessage;
    }

    public final String getRuleMessage() {
        return this.ruleMessage;
    }
}
