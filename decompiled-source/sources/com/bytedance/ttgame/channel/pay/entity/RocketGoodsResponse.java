package com.bytedance.ttgame.channel.pay.entity;

import com.bytedance.ttgame.rocketapi.pay.Data;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: RocketGoodsResponse.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u0005H\u0016R \u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/channel/pay/entity/RocketGoodsResponse;", "Lcom/bytedance/ttgame/channel/pay/entity/BaseResponse;", "code", "", "msg", "", "rocketGoods", "Lcom/bytedance/ttgame/rocketapi/pay/Data;", "(Ljava/lang/Integer;Ljava/lang/String;Lcom/bytedance/ttgame/rocketapi/pay/Data;)V", "getRocketGoods", "()Lcom/bytedance/ttgame/rocketapi/pay/Data;", "setRocketGoods", "(Lcom/bytedance/ttgame/rocketapi/pay/Data;)V", "toString", "pay_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RocketGoodsResponse extends BaseResponse {
    @SerializedName("data")
    private Data rocketGoods;

    public /* synthetic */ RocketGoodsResponse(Integer num, String str, Data data, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(num, str, (i2 & 4) != 0 ? null : data);
    }

    public RocketGoodsResponse(Integer num, String str, Data data) {
        super(num, str);
        this.rocketGoods = data;
    }

    public final Data getRocketGoods() {
        return this.rocketGoods;
    }

    public final void setRocketGoods(Data data) {
        this.rocketGoods = data;
    }

    public String toString() {
        return "RocketGoods(code=" + getCode() + ", msg=" + getMsg() + ", rocketGoods=" + this.rocketGoods + ')';
    }
}
