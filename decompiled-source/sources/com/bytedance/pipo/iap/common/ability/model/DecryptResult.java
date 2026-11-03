package com.bytedance.pipo.iap.common.ability.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: PIPOContextDecrypter.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/DecryptResult;", "", "decryptedPipoContext", "", "errMsg", "(Ljava/lang/String;Ljava/lang/String;)V", "getDecryptedPipoContext", "()Ljava/lang/String;", "getErrMsg", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class DecryptResult {
    private final String decryptedPipoContext;
    private final String errMsg;

    public static /* synthetic */ DecryptResult copy$default(DecryptResult decryptResult, String str, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = decryptResult.decryptedPipoContext;
        }
        if ((i2 & 2) != 0) {
            str2 = decryptResult.errMsg;
        }
        return decryptResult.copy(str, str2);
    }

    public final String component1() {
        return this.decryptedPipoContext;
    }

    public final String component2() {
        return this.errMsg;
    }

    public final DecryptResult copy(String decryptedPipoContext, String errMsg) {
        Intrinsics.checkNotNullParameter(decryptedPipoContext, "decryptedPipoContext");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        return new DecryptResult(decryptedPipoContext, errMsg);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof DecryptResult) {
            DecryptResult decryptResult = (DecryptResult) obj;
            return Intrinsics.areEqual(this.decryptedPipoContext, decryptResult.decryptedPipoContext) && Intrinsics.areEqual(this.errMsg, decryptResult.errMsg);
        }
        return false;
    }

    public int hashCode() {
        return (this.decryptedPipoContext.hashCode() * 31) + this.errMsg.hashCode();
    }

    public String toString() {
        return "DecryptResult(decryptedPipoContext=" + this.decryptedPipoContext + ", errMsg=" + this.errMsg + ')';
    }

    public DecryptResult(String decryptedPipoContext, String errMsg) {
        Intrinsics.checkNotNullParameter(decryptedPipoContext, "decryptedPipoContext");
        Intrinsics.checkNotNullParameter(errMsg, "errMsg");
        this.decryptedPipoContext = decryptedPipoContext;
        this.errMsg = errMsg;
    }

    public final String getDecryptedPipoContext() {
        return this.decryptedPipoContext;
    }

    public final String getErrMsg() {
        return this.errMsg;
    }
}
