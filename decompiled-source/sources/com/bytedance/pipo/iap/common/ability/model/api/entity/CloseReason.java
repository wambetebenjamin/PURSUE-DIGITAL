package com.bytedance.pipo.iap.common.ability.model.api.entity;

import com.bytedance.pipo.iap.common.ability.e;
import com.bytedance.pipo.payment.common.lib.c;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: CloseTradeRequest.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007R \u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u0013"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;", "", "code", "", "detailCode", c.de, "actor", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getActor", "()Ljava/lang/String;", "setActor", "(Ljava/lang/String;)V", "getCode", "setCode", "getDetailCode", "setDetailCode", "getReason", "setReason", "Companion", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class CloseReason {
    public static final Companion Companion = new Companion(null);
    @SerializedName("actor")
    private String actor;
    @SerializedName("code")
    private String code;
    @SerializedName("detail_code")
    private String detailCode;
    @SerializedName(c.de)
    private String reason;

    public CloseReason() {
        this(null, null, null, null, 15, null);
    }

    @JvmStatic
    public static final CloseReason fromIapResult(e eVar, String str) {
        return Companion.fromIapResult(eVar, str);
    }

    public CloseReason(String str, String str2, String str3, String str4) {
        this.code = str;
        this.detailCode = str2;
        this.reason = str3;
        this.actor = str4;
    }

    public /* synthetic */ CloseReason(String str, String str2, String str3, String str4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? null : str2, (i2 & 4) != 0 ? null : str3, (i2 & 8) != 0 ? null : str4);
    }

    public final String getCode() {
        return this.code;
    }

    public final void setCode(String str) {
        this.code = str;
    }

    public final String getDetailCode() {
        return this.detailCode;
    }

    public final void setDetailCode(String str) {
        this.detailCode = str;
    }

    public final String getReason() {
        return this.reason;
    }

    public final void setReason(String str) {
        this.reason = str;
    }

    public final String getActor() {
        return this.actor;
    }

    public final void setActor(String str) {
        this.actor = str;
    }

    /* compiled from: CloseTradeRequest.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason$Companion;", "", "()V", "fromIapResult", "Lcom/bytedance/pipo/iap/common/ability/model/api/entity/CloseReason;", "iapResult", "Lcom/bytedance/pipo/iap/common/ability/IapResult;", "actor", "", "iap_common_ability_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final CloseReason fromIapResult(e iapResult, String actor) {
            Intrinsics.checkNotNullParameter(iapResult, "iapResult");
            Intrinsics.checkNotNullParameter(actor, "actor");
            return new CloseReason(String.valueOf(iapResult.getCode()), iapResult.getDetailCode(), iapResult.getMessage(), actor);
        }
    }
}
