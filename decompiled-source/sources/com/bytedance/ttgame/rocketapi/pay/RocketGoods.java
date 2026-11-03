package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.ttgame.base.GSDKError;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\b\u0010\u0019\u001a\u00020\u001aH\u0016R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\rR \u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/RocketGoods;", "", "gsdkError", "Lcom/bytedance/ttgame/base/GSDKError;", "rocketGoods", "Lcom/bytedance/ttgame/rocketapi/pay/Data;", "(Lcom/bytedance/ttgame/base/GSDKError;Lcom/bytedance/ttgame/rocketapi/pay/Data;)V", "getGsdkError", "()Lcom/bytedance/ttgame/base/GSDKError;", "setGsdkError", "(Lcom/bytedance/ttgame/base/GSDKError;)V", "isSuccess", "", "()Z", "getRocketGoods", "()Lcom/bytedance/ttgame/rocketapi/pay/Data;", "setRocketGoods", "(Lcom/bytedance/ttgame/rocketapi/pay/Data;)V", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class RocketGoods {
    @SerializedName("gsdkError")
    private GSDKError gsdkError;
    @SerializedName("data")
    private Data rocketGoods;

    public static /* synthetic */ RocketGoods copy$default(RocketGoods rocketGoods, GSDKError gSDKError, Data data, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            gSDKError = rocketGoods.gsdkError;
        }
        if ((i2 & 2) != 0) {
            data = rocketGoods.rocketGoods;
        }
        return rocketGoods.copy(gSDKError, data);
    }

    public final GSDKError component1() {
        return this.gsdkError;
    }

    public final Data component2() {
        return this.rocketGoods;
    }

    public final RocketGoods copy(GSDKError gsdkError, Data data) {
        Intrinsics.checkNotNullParameter(gsdkError, "gsdkError");
        return new RocketGoods(gsdkError, data);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof RocketGoods) {
            RocketGoods rocketGoods = (RocketGoods) obj;
            return Intrinsics.areEqual(this.gsdkError, rocketGoods.gsdkError) && Intrinsics.areEqual(this.rocketGoods, rocketGoods.rocketGoods);
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.gsdkError.hashCode() * 31;
        Data data = this.rocketGoods;
        return hashCode + (data == null ? 0 : data.hashCode());
    }

    public RocketGoods(GSDKError gsdkError, Data data) {
        Intrinsics.checkNotNullParameter(gsdkError, "gsdkError");
        this.gsdkError = gsdkError;
        this.rocketGoods = data;
    }

    public final GSDKError getGsdkError() {
        return this.gsdkError;
    }

    public final void setGsdkError(GSDKError gSDKError) {
        Intrinsics.checkNotNullParameter(gSDKError, "<set-?>");
        this.gsdkError = gSDKError;
    }

    public final Data getRocketGoods() {
        return this.rocketGoods;
    }

    public final void setRocketGoods(Data data) {
        this.rocketGoods = data;
    }

    public final boolean isSuccess() {
        return this.gsdkError.getCode() == 0;
    }

    public String toString() {
        return "RocketGoods(rocketGoods=" + this.rocketGoods + ", error:" + this.gsdkError + ')';
    }
}
