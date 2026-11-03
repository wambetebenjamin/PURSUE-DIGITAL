package com.bytedance.ttgame.sdk.module.account.login;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/login/EmailCheckCodeResult;", "", "ticket", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "(Ljava/lang/String;Lcom/bytedance/ttgame/base/GSDKError;)V", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "getTicket", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EmailCheckCodeResult {
    private final GSDKError gsdkError;
    private final String ticket;

    public static /* synthetic */ EmailCheckCodeResult copy$default(EmailCheckCodeResult emailCheckCodeResult, String str, GSDKError gSDKError, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = emailCheckCodeResult.ticket;
        }
        if ((i2 & 2) != 0) {
            gSDKError = emailCheckCodeResult.gsdkError;
        }
        return emailCheckCodeResult.copy(str, gSDKError);
    }

    public final String component1() {
        return this.ticket;
    }

    public final GSDKError component2() {
        return this.gsdkError;
    }

    public final EmailCheckCodeResult copy(String str, GSDKError gSDKError) {
        return new EmailCheckCodeResult(str, gSDKError);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof EmailCheckCodeResult) {
            EmailCheckCodeResult emailCheckCodeResult = (EmailCheckCodeResult) obj;
            return Intrinsics.areEqual(this.ticket, emailCheckCodeResult.ticket) && Intrinsics.areEqual(this.gsdkError, emailCheckCodeResult.gsdkError);
        }
        return false;
    }

    public int hashCode() {
        String str = this.ticket;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        GSDKError gSDKError = this.gsdkError;
        return hashCode + (gSDKError != null ? gSDKError.hashCode() : 0);
    }

    public String toString() {
        return "EmailCheckCodeResult(ticket=" + this.ticket + ", gsdkError=" + this.gsdkError + ')';
    }

    public EmailCheckCodeResult(String str, GSDKError gSDKError) {
        this.ticket = str;
        this.gsdkError = gSDKError;
    }

    public /* synthetic */ EmailCheckCodeResult(String str, GSDKError gSDKError, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, gSDKError);
    }

    public final GSDKError getGsdkError() {
        return this.gsdkError;
    }

    public final String getTicket() {
        return this.ticket;
    }
}
