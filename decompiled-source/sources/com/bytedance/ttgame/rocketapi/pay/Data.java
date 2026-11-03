package com.bytedance.ttgame.rocketapi.pay;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B%\u0012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0003J-\u0010\u0010\u001a\u00020\u00002\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\b\u0010\u0017\u001a\u00020\u0018H\u0016R&\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR&\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/Data;", "Ljava/io/Serializable;", "goods", "", "Lcom/bytedance/ttgame/rocketapi/pay/Goods;", "accumulAtions", "Lcom/bytedance/ttgame/rocketapi/pay/AccumulAtion;", "(Ljava/util/List;Ljava/util/List;)V", "getAccumulAtions", "()Ljava/util/List;", "setAccumulAtions", "(Ljava/util/List;)V", "getGoods", "setGoods", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class Data implements Serializable {
    @SerializedName("accumulations")
    private List<AccumulAtion> accumulAtions;
    @SerializedName("goods")
    private List<Goods> goods;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Data copy$default(Data data, List list, List list2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = data.goods;
        }
        if ((i2 & 2) != 0) {
            list2 = data.accumulAtions;
        }
        return data.copy(list, list2);
    }

    public final List<Goods> component1() {
        return this.goods;
    }

    public final List<AccumulAtion> component2() {
        return this.accumulAtions;
    }

    public final Data copy(List<Goods> list, List<AccumulAtion> list2) {
        return new Data(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Data) {
            Data data = (Data) obj;
            return Intrinsics.areEqual(this.goods, data.goods) && Intrinsics.areEqual(this.accumulAtions, data.accumulAtions);
        }
        return false;
    }

    public int hashCode() {
        List<Goods> list = this.goods;
        int hashCode = (list == null ? 0 : list.hashCode()) * 31;
        List<AccumulAtion> list2 = this.accumulAtions;
        return hashCode + (list2 != null ? list2.hashCode() : 0);
    }

    public Data(List<Goods> list, List<AccumulAtion> list2) {
        this.goods = list;
        this.accumulAtions = list2;
    }

    public final List<Goods> getGoods() {
        return this.goods;
    }

    public final void setGoods(List<Goods> list) {
        this.goods = list;
    }

    public final List<AccumulAtion> getAccumulAtions() {
        return this.accumulAtions;
    }

    public final void setAccumulAtions(List<AccumulAtion> list) {
        this.accumulAtions = list;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer("Data(goods={");
        List<Goods> list = this.goods;
        if (list != null) {
            for (Goods goods : list) {
                stringBuffer.append(goods).append(",");
            }
        }
        stringBuffer.append("}, accumulAtions={");
        List<AccumulAtion> list2 = this.accumulAtions;
        if (list2 != null) {
            for (AccumulAtion accumulAtion : list2) {
                stringBuffer.append(accumulAtion).append(",");
            }
        }
        stringBuffer.append("})");
        String stringBuffer2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(stringBuffer2, "sb.toString()");
        return stringBuffer2;
    }
}
