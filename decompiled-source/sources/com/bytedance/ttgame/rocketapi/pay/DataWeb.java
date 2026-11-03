package com.bytedance.ttgame.rocketapi.pay;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0015\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u001b\u0010\n\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\b\u0010\u0011\u001a\u00020\u0012H\u0016R&\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0013"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/DataWeb;", "Ljava/io/Serializable;", "goods", "", "Lcom/bytedance/ttgame/rocketapi/pay/Goods;", "(Ljava/util/List;)V", "getGoods", "()Ljava/util/List;", "setGoods", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class DataWeb implements Serializable {
    @SerializedName("normal")
    private List<Goods> goods;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DataWeb copy$default(DataWeb dataWeb, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = dataWeb.goods;
        }
        return dataWeb.copy(list);
    }

    public final List<Goods> component1() {
        return this.goods;
    }

    public final DataWeb copy(List<Goods> list) {
        return new DataWeb(list);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof DataWeb) && Intrinsics.areEqual(this.goods, ((DataWeb) obj).goods);
    }

    public int hashCode() {
        List<Goods> list = this.goods;
        if (list == null) {
            return 0;
        }
        return list.hashCode();
    }

    public DataWeb(List<Goods> list) {
        this.goods = list;
    }

    public final List<Goods> getGoods() {
        return this.goods;
    }

    public final void setGoods(List<Goods> list) {
        this.goods = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Data(goods={");
        List<Goods> list = this.goods;
        if (list != null) {
            for (Goods goods : list) {
                stringBuffer.append(goods).append(",");
            }
        }
        stringBuffer.append("})");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }
}
