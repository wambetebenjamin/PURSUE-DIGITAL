package com.bytedance.ttgame.module.compliance.api.realname;

import com.bytedance.ttgame.base.GSDKError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RealVerifyInfo.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/realname/RealVerifyInfo;", "", "()V", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "setGsdkError", "(Lcom/bytedance/ttgame/base/GSDKError;)V", "isVerify", "", "()Z", "setVerify", "(Z)V", "compliance_api_realname_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class RealVerifyInfo {
    public GSDKError gsdkError;
    private boolean isVerify;

    public final GSDKError getGsdkError() {
        GSDKError gSDKError = this.gsdkError;
        if (gSDKError != null) {
            return gSDKError;
        }
        Intrinsics.throwUninitializedPropertyAccessException("gsdkError");
        return null;
    }

    public final void setGsdkError(GSDKError gSDKError) {
        Intrinsics.checkNotNullParameter(gSDKError, "<set-?>");
        this.gsdkError = gSDKError;
    }

    public final boolean isVerify() {
        return this.isVerify;
    }

    public final void setVerify(boolean z) {
        this.isVerify = z;
    }
}
