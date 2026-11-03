package com.bytedance.ttgame.module.account.bridge.model;

import com.appsflyer.AppsFlyerProperties;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMPayModel.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u008d\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000b\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\u0014J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010)\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u000b\u0010*\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0011\u0010+\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u0010-\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010.\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010/\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u00102\u001a\u0004\u0018\u00010\u000bHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u00103\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ\u0010\u00104\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u001aJ°\u0001\u00105\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000b2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u00106J\u0013\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010:\u001a\u00020\u000bHÖ\u0001J\t\u0010;\u001a\u00020\u0003HÖ\u0001R\u0019\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0015\u0010\f\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\t\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0018R\u0015\u0010\u0013\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\u001d\u0010\u001aR\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0018R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b\"\u0010\u001aR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0018R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0015\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\n\n\u0002\u0010!\u001a\u0004\b&\u0010 R\u0015\u0010\r\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u001b\u001a\u0004\b'\u0010\u001a¨\u0006<"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMProductModel;", "", AppsFlyerProperties.APP_ID, "", "productId", "productName", FirebaseAnalytics.Param.PRICE, "", "priceDesc", "currencyType", "status", "", "createTime", "updateTime", "goodsType", "extra", "activities", "", "Lcom/bytedance/ttgame/module/account/bridge/model/GMProductActivity;", "discountPrice", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;)V", "getActivities", "()Ljava/util/List;", "getAppid", "()Ljava/lang/String;", "getCreateTime", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getCurrencyType", "getDiscountPrice", "getExtra", "getGoodsType", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getPrice", "getPriceDesc", "getProductId", "getProductName", "getStatus", "getUpdateTime", "component1", "component10", "component11", "component12", "component13", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Long;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/List;Ljava/lang/Long;)Lcom/bytedance/ttgame/module/account/bridge/model/GMProductModel;", "equals", "", "other", "hashCode", "toString", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductModel {
    private final List<GMProductActivity> activities;
    private final String appid;
    private final Long createTime;
    private final String currencyType;
    private final Long discountPrice;
    private final String extra;
    private final Integer goodsType;
    private final Long price;
    private final String priceDesc;
    private final String productId;
    private final String productName;
    private final Integer status;
    private final Long updateTime;

    public final String component1() {
        return this.appid;
    }

    public final Integer component10() {
        return this.goodsType;
    }

    public final String component11() {
        return this.extra;
    }

    public final List<GMProductActivity> component12() {
        return this.activities;
    }

    public final Long component13() {
        return this.discountPrice;
    }

    public final String component2() {
        return this.productId;
    }

    public final String component3() {
        return this.productName;
    }

    public final Long component4() {
        return this.price;
    }

    public final String component5() {
        return this.priceDesc;
    }

    public final String component6() {
        return this.currencyType;
    }

    public final Integer component7() {
        return this.status;
    }

    public final Long component8() {
        return this.createTime;
    }

    public final Long component9() {
        return this.updateTime;
    }

    public final GMProductModel copy(String str, String str2, String str3, Long l, String str4, String str5, Integer num, Long l2, Long l3, Integer num2, String str6, List<GMProductActivity> list, Long l4) {
        return new GMProductModel(str, str2, str3, l, str4, str5, num, l2, l3, num2, str6, list, l4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMProductModel) {
            GMProductModel gMProductModel = (GMProductModel) obj;
            return Intrinsics.areEqual(this.appid, gMProductModel.appid) && Intrinsics.areEqual(this.productId, gMProductModel.productId) && Intrinsics.areEqual(this.productName, gMProductModel.productName) && Intrinsics.areEqual(this.price, gMProductModel.price) && Intrinsics.areEqual(this.priceDesc, gMProductModel.priceDesc) && Intrinsics.areEqual(this.currencyType, gMProductModel.currencyType) && Intrinsics.areEqual(this.status, gMProductModel.status) && Intrinsics.areEqual(this.createTime, gMProductModel.createTime) && Intrinsics.areEqual(this.updateTime, gMProductModel.updateTime) && Intrinsics.areEqual(this.goodsType, gMProductModel.goodsType) && Intrinsics.areEqual(this.extra, gMProductModel.extra) && Intrinsics.areEqual(this.activities, gMProductModel.activities) && Intrinsics.areEqual(this.discountPrice, gMProductModel.discountPrice);
        }
        return false;
    }

    public int hashCode() {
        String str = this.appid;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.productId;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.productName;
        int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        Long l = this.price;
        int hashCode4 = (hashCode3 + (l == null ? 0 : l.hashCode())) * 31;
        String str4 = this.priceDesc;
        int hashCode5 = (hashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.currencyType;
        int hashCode6 = (hashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Integer num = this.status;
        int hashCode7 = (hashCode6 + (num == null ? 0 : num.hashCode())) * 31;
        Long l2 = this.createTime;
        int hashCode8 = (hashCode7 + (l2 == null ? 0 : l2.hashCode())) * 31;
        Long l3 = this.updateTime;
        int hashCode9 = (hashCode8 + (l3 == null ? 0 : l3.hashCode())) * 31;
        Integer num2 = this.goodsType;
        int hashCode10 = (hashCode9 + (num2 == null ? 0 : num2.hashCode())) * 31;
        String str6 = this.extra;
        int hashCode11 = (hashCode10 + (str6 == null ? 0 : str6.hashCode())) * 31;
        List<GMProductActivity> list = this.activities;
        int hashCode12 = (hashCode11 + (list == null ? 0 : list.hashCode())) * 31;
        Long l4 = this.discountPrice;
        return hashCode12 + (l4 != null ? l4.hashCode() : 0);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GMProductModel(appid=").append(this.appid).append(", productId=").append(this.productId).append(", productName=").append(this.productName).append(", price=").append(this.price).append(", priceDesc=").append(this.priceDesc).append(", currencyType=").append(this.currencyType).append(", status=").append(this.status).append(", createTime=").append(this.createTime).append(", updateTime=").append(this.updateTime).append(", goodsType=").append(this.goodsType).append(", extra=").append(this.extra).append(", activities=");
        sb.append(this.activities).append(", discountPrice=").append(this.discountPrice).append(')');
        return sb.toString();
    }

    public GMProductModel(String str, String str2, String str3, Long l, String str4, String str5, Integer num, Long l2, Long l3, Integer num2, String str6, List<GMProductActivity> list, Long l4) {
        this.appid = str;
        this.productId = str2;
        this.productName = str3;
        this.price = l;
        this.priceDesc = str4;
        this.currencyType = str5;
        this.status = num;
        this.createTime = l2;
        this.updateTime = l3;
        this.goodsType = num2;
        this.extra = str6;
        this.activities = list;
        this.discountPrice = l4;
    }

    public final String getAppid() {
        return this.appid;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final String getProductName() {
        return this.productName;
    }

    public final Long getPrice() {
        return this.price;
    }

    public final String getPriceDesc() {
        return this.priceDesc;
    }

    public final String getCurrencyType() {
        return this.currencyType;
    }

    public final Integer getStatus() {
        return this.status;
    }

    public final Long getCreateTime() {
        return this.createTime;
    }

    public final Long getUpdateTime() {
        return this.updateTime;
    }

    public final Integer getGoodsType() {
        return this.goodsType;
    }

    public final String getExtra() {
        return this.extra;
    }

    public final List<GMProductActivity> getActivities() {
        return this.activities;
    }

    public final Long getDiscountPrice() {
        return this.discountPrice;
    }
}
