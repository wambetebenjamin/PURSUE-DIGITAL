package com.bytedance.ttgame.rocketapi.pay;

import com.bytedance.pipo.payment.common.lib.c;
import com.bytedance.ttgame.channel.pay.Constant;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010H\u001a\u00020\u000bH\u0016R&\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\n\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\"\u0010\u0010\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR \u0010\u001e\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\r\"\u0004\b \u0010\u000fR \u0010!\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\r\"\u0004\b#\u0010\u000fR\u001a\u0010$\u001a\u00020\u0018X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010)\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\r\"\u0004\b+\u0010\u000fR\"\u0010,\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b-\u0010\u0013\"\u0004\b.\u0010\u0015R\"\u0010/\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\b0\u0010\u001a\"\u0004\b1\u0010\u001cR \u00102\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\r\"\u0004\b4\u0010\u000fR \u00105\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\r\"\u0004\b7\u0010\u000fR \u00108\u001a\u0004\u0018\u00010\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\r\"\u0004\b:\u0010\u000fR\"\u0010;\u001a\u0004\u0018\u00010\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u0016\u001a\u0004\b<\u0010\u0013\"\u0004\b=\u0010\u0015R\"\u0010>\u001a\n\u0012\u0004\u0012\u00020?\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010\u0007\"\u0004\bA\u0010\tR\u001c\u0010B\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010\r\"\u0004\bD\u0010\u000fR\"\u0010E\u001a\u0004\u0018\u00010\u00188\u0006@\u0006X\u0087\u000e¢\u0006\u0010\n\u0002\u0010\u001d\u001a\u0004\bF\u0010\u001a\"\u0004\bG\u0010\u001c¨\u0006I"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/Goods;", "Ljava/io/Serializable;", "()V", "activityProducts", "", "Lcom/bytedance/ttgame/rocketapi/pay/ActivityProduct;", "getActivityProducts", "()Ljava/util/List;", "setActivityProducts", "(Ljava/util/List;)V", "aid", "", "getAid", "()Ljava/lang/String;", "setAid", "(Ljava/lang/String;)V", "coins", "", "getCoins", "()Ljava/lang/Integer;", "setCoins", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "createTime", "", "getCreateTime", "()Ljava/lang/Long;", "setCreateTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "currencySymbol", "getCurrencySymbol", "setCurrencySymbol", "currencyType", "getCurrencyType", "setCurrencyType", "discountPrice", "getDiscountPrice", "()J", "setDiscountPrice", "(J)V", "extra", "getExtra", "setExtra", "goodsType", "getGoodsType", "setGoodsType", FirebaseAnalytics.Param.PRICE, "getPrice", "setPrice", "priceDesc", "getPriceDesc", "setPriceDesc", "productId", "getProductId", "setProductId", "productName", "getProductName", "setProductName", "status", "getStatus", "setStatus", "subscriptionOfferDetails", "Lcom/bytedance/ttgame/rocketapi/pay/SubscriptionOfferDetails;", "getSubscriptionOfferDetails", "setSubscriptionOfferDetails", "type", "getType", "setType", "updateTime", "getUpdateTime", "setUpdateTime", "toString", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class Goods implements Serializable {
    @SerializedName("activities")
    private List<ActivityProduct> activityProducts;
    @SerializedName("aid")
    private String aid;
    @SerializedName("coins")
    private Integer coins;
    @SerializedName("create_time")
    private Long createTime;
    @SerializedName("currency_symbol")
    private String currencySymbol;
    @SerializedName("currency_type")
    private String currencyType;
    private long discountPrice;
    @SerializedName("extra")
    private String extra;
    @SerializedName(Constant.GOODS_TYPE)
    private Integer goodsType;
    @SerializedName(FirebaseAnalytics.Param.PRICE)
    private Long price;
    @SerializedName("price_desc")
    private String priceDesc;
    @SerializedName(c.am)
    private String productId;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("status")
    private Integer status;
    private List<SubscriptionOfferDetails> subscriptionOfferDetails;
    private String type;
    @SerializedName("update_time")
    private Long updateTime;

    public final String getAid() {
        return this.aid;
    }

    public final void setAid(String str) {
        this.aid = str;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final void setProductId(String str) {
        this.productId = str;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final void setProductName(String str) {
        this.productName = str;
    }

    public final Long getPrice() {
        return this.price;
    }

    public final void setPrice(Long l) {
        this.price = l;
    }

    public final String getPriceDesc() {
        return this.priceDesc;
    }

    public final void setPriceDesc(String str) {
        this.priceDesc = str;
    }

    public final String getCurrencyType() {
        return this.currencyType;
    }

    public final void setCurrencyType(String str) {
        this.currencyType = str;
    }

    public final Integer getGoodsType() {
        return this.goodsType;
    }

    public final void setGoodsType(Integer num) {
        this.goodsType = num;
    }

    public final Integer getStatus() {
        return this.status;
    }

    public final void setStatus(Integer num) {
        this.status = num;
    }

    public final Long getCreateTime() {
        return this.createTime;
    }

    public final void setCreateTime(Long l) {
        this.createTime = l;
    }

    public final Long getUpdateTime() {
        return this.updateTime;
    }

    public final void setUpdateTime(Long l) {
        this.updateTime = l;
    }

    public final String getExtra() {
        return this.extra;
    }

    public final void setExtra(String str) {
        this.extra = str;
    }

    public final Integer getCoins() {
        return this.coins;
    }

    public final void setCoins(Integer num) {
        this.coins = num;
    }

    public final String getCurrencySymbol() {
        return this.currencySymbol;
    }

    public final void setCurrencySymbol(String str) {
        this.currencySymbol = str;
    }

    public final List<ActivityProduct> getActivityProducts() {
        return this.activityProducts;
    }

    public final void setActivityProducts(List<ActivityProduct> list) {
        this.activityProducts = list;
    }

    public final List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return this.subscriptionOfferDetails;
    }

    public final void setSubscriptionOfferDetails(List<SubscriptionOfferDetails> list) {
        this.subscriptionOfferDetails = list;
    }

    public final String getType() {
        return this.type;
    }

    public final void setType(String str) {
        this.type = str;
    }

    public final long getDiscountPrice() {
        return this.discountPrice;
    }

    public final void setDiscountPrice(long j2) {
        this.discountPrice = j2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Goods(aid=").append(this.aid).append(", productId=").append(this.productId).append(", productName=").append(this.productName).append(", price=").append(this.price).append(", priceDesc=").append(this.priceDesc).append(", currencyType=").append(this.currencyType).append(", goodsType=").append(this.goodsType).append(", status=").append(this.status).append(", createTime=").append(this.createTime).append(", updateTime=").append(this.updateTime).append(", extra=").append(this.extra).append(", activityProducts=");
        sb.append(this.activityProducts).append(", subscriptionOfferDetails=").append(this.subscriptionOfferDetails).append(", discountPrice=").append(this.discountPrice).append(", currencySymbol=").append(this.currencySymbol).append(')');
        return sb.toString();
    }
}
