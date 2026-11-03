package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.pipo.payment.common.lib.c;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B_\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\rJ\u000b\u0010%\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010(\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010)\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u0010\u0010*\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010,\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003Jz\u0010.\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0001¢\u0006\u0002\u0010/J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00103\u001a\u00020\u0007HÖ\u0001J\b\u00104\u001a\u00020\u0003H\u0016R\"\u0010\t\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\n\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R \u0010\u000b\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R \u0010\f\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\"\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b\u001b\u0010\u000f\"\u0004\b\u001c\u0010\u0011R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0014\"\u0004\b\u001e\u0010\u0016R \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0014\"\u0004\b \u0010\u0016R \u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0014\"\u0004\b\"\u0010\u0016R\"\u0010\u0006\u001a\u0004\u0018\u00010\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0012\u001a\u0004\b#\u0010\u000f\"\u0004\b$\u0010\u0011¨\u00065"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/GoodsBean;", "", "productId", "", "priceDesc", "productName", "productType", "", FirebaseAnalytics.Param.PRICE, "coins", "currency", "currencySymbol", "extra", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getCoins", "()Ljava/lang/Integer;", "setCoins", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getCurrency", "()Ljava/lang/String;", "setCurrency", "(Ljava/lang/String;)V", "getCurrencySymbol", "setCurrencySymbol", "getExtra", "setExtra", "getPrice", "setPrice", "getPriceDesc", "setPriceDesc", "getProductId", "setProductId", "getProductName", "setProductName", "getProductType", "setProductType", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lcom/bytedance/ttgame/rocketapi/pay/GoodsBean;", "equals", "", "other", "hashCode", "toString", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class GoodsBean {
    @SerializedName("coins")
    private Integer coins;
    @SerializedName("currency")
    private String currency;
    @SerializedName("currency_symbol")
    private String currencySymbol;
    @SerializedName("extra")
    private String extra;
    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private Integer price;
    @SerializedName("price_desc")
    private String priceDesc;
    @SerializedName(c.am)
    private String productId;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("product_type")
    private Integer productType;

    public final String component1() {
        return this.productId;
    }

    public final String component2() {
        return this.priceDesc;
    }

    public final String component3() {
        return this.productName;
    }

    public final Integer component4() {
        return this.productType;
    }

    public final Integer component5() {
        return this.price;
    }

    public final Integer component6() {
        return this.coins;
    }

    public final String component7() {
        return this.currency;
    }

    public final String component8() {
        return this.currencySymbol;
    }

    public final String component9() {
        return this.extra;
    }

    public final GoodsBean copy(String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6) {
        return new GoodsBean(str, str2, str3, num, num2, num3, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GoodsBean) {
            GoodsBean goodsBean = (GoodsBean) obj;
            return Intrinsics.areEqual(this.productId, goodsBean.productId) && Intrinsics.areEqual(this.priceDesc, goodsBean.priceDesc) && Intrinsics.areEqual(this.productName, goodsBean.productName) && Intrinsics.areEqual(this.productType, goodsBean.productType) && Intrinsics.areEqual(this.price, goodsBean.price) && Intrinsics.areEqual(this.coins, goodsBean.coins) && Intrinsics.areEqual(this.currency, goodsBean.currency) && Intrinsics.areEqual(this.currencySymbol, goodsBean.currencySymbol) && Intrinsics.areEqual(this.extra, goodsBean.extra);
        }
        return false;
    }

    public int hashCode() {
        String str = this.productId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.priceDesc;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.productName;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Integer num = this.productType;
        int hashCode4 = (hashCode3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this.price;
        int hashCode5 = (hashCode4 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.coins;
        int hashCode6 = (hashCode5 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str4 = this.currency;
        int hashCode7 = (hashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.currencySymbol;
        int hashCode8 = (hashCode7 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.extra;
        return hashCode8 + (str6 != null ? str6.hashCode() : 0);
    }

    public GoodsBean(String str, String str2, String str3, Integer num, Integer num2, Integer num3, String str4, String str5, String str6) {
        this.productId = str;
        this.priceDesc = str2;
        this.productName = str3;
        this.productType = num;
        this.price = num2;
        this.coins = num3;
        this.currency = str4;
        this.currencySymbol = str5;
        this.extra = str6;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final void setProductId(String str) {
        this.productId = str;
    }

    public final String getPriceDesc() {
        return this.priceDesc;
    }

    public final void setPriceDesc(String str) {
        this.priceDesc = str;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final Integer getProductType() {
        return this.productType;
    }

    public final void setProductType(Integer num) {
        this.productType = num;
    }

    public final Integer getPrice() {
        return this.price;
    }

    public final void setPrice(Integer num) {
        this.price = num;
    }

    public final Integer getCoins() {
        return this.coins;
    }

    public final void setCoins(Integer num) {
        this.coins = num;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final void setCurrency(String str) {
        this.currency = str;
    }

    public final String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public final void setCurrencySymbol(String str) {
        this.currencySymbol = str;
    }

    public final String getExtra() {
        return this.extra;
    }

    public final void setExtra(String str) {
        this.extra = str;
    }

    public String toString() {
        return "GoodsBean(productId=" + this.productId + ", productName=" + this.productName + ", productType=" + this.productType + ", price=" + this.price + ", priceDesc=" + this.priceDesc + ", coins=" + this.coins + ", currency=" + this.currency + ", extra=" + this.extra + ") currencySymbol=" + this.currencySymbol + ')';
    }
}
