package com.vk.api.sdk.chain;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ChainArgs.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0012\u001a\u00020\rR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/vk/api/sdk/chain/ChainArgs;", "", "()V", "captchaKey", "", "getCaptchaKey", "()Ljava/lang/String;", "setCaptchaKey", "(Ljava/lang/String;)V", "captchaSid", "getCaptchaSid", "setCaptchaSid", "userConfirmed", "", "getUserConfirmed", "()Z", "setUserConfirmed", "(Z)V", "hasCaptcha", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public final class ChainArgs {
    private boolean userConfirmed;
    private String captchaSid = "";
    private String captchaKey = "";

    public final String getCaptchaSid() {
        return this.captchaSid;
    }

    public final void setCaptchaSid(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.captchaSid = str;
    }

    public final String getCaptchaKey() {
        return this.captchaKey;
    }

    public final void setCaptchaKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.captchaKey = str;
    }

    public final boolean getUserConfirmed() {
        return this.userConfirmed;
    }

    public final void setUserConfirmed(boolean z) {
        this.userConfirmed = z;
    }

    public final boolean hasCaptcha() {
        if (this.captchaSid.length() > 0) {
            if (this.captchaKey.length() > 0) {
                return true;
            }
        }
        return false;
    }
}
