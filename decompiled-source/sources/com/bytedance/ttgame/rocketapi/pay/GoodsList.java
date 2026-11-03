package com.bytedance.ttgame.rocketapi.pay;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\n\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0011H\u0016R$\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0005¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/GoodsList;", "", "productList", "", "Lcom/bytedance/ttgame/rocketapi/pay/GoodsBean;", "(Ljava/util/List;)V", "getProductList", "()Ljava/util/List;", "setProductList", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class GoodsList {
    @SerializedName("product_list")
    private List<GoodsBean> productList;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ GoodsList copy$default(GoodsList goodsList, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            list = goodsList.productList;
        }
        return goodsList.copy(list);
    }

    public final List<GoodsBean> component1() {
        return this.productList;
    }

    public final GoodsList copy(List<GoodsBean> productList) {
        Intrinsics.checkNotNullParameter(productList, "productList");
        return new GoodsList(productList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof GoodsList) && Intrinsics.areEqual(this.productList, ((GoodsList) obj).productList);
    }

    public int hashCode() {
        return this.productList.hashCode();
    }

    public GoodsList(List<GoodsBean> productList) {
        Intrinsics.checkNotNullParameter(productList, "productList");
        this.productList = productList;
    }

    public final List<GoodsBean> getProductList() {
        return this.productList;
    }

    public final void setProductList(List<GoodsBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.productList = list;
    }

    public String toString() {
        return "GoodsList(productList=" + this.productList + ')';
    }
}
