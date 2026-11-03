package com.bytedance.ttgame.module.account.bridge.model;

import com.google.firebase.analytics.FirebaseAnalytics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GMProductInfo.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B?\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\nJ\u000b\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJT\u0010\u001b\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u001cJ\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010 \u001a\u00020!HÖ\u0001J\t\u0010\"\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\t\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\f¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/module/account/bridge/model/GMProductInfo;", "", "productId", "", FirebaseAnalytics.Param.PRICE, "currency", "priceAmount", "", "priceDesc", "discountPrice", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/Long;)V", "getCurrency", "()Ljava/lang/String;", "getDiscountPrice", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getPrice", "getPriceAmount", "()J", "getPriceDesc", "getProductId", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Ljava/lang/Long;)Lcom/bytedance/ttgame/module/account/bridge/model/GMProductInfo;", "equals", "", "other", "hashCode", "", "toString", "pay_bridge_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GMProductInfo {
    private final String currency;
    private final Long discountPrice;
    private final String price;
    private final long priceAmount;
    private final String priceDesc;
    private final String productId;

    public static /* synthetic */ GMProductInfo copy$default(GMProductInfo gMProductInfo, String str, String str2, String str3, long j2, String str4, Long l, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = gMProductInfo.productId;
        }
        if ((i2 & 2) != 0) {
            str2 = gMProductInfo.price;
        }
        String str5 = str2;
        if ((i2 & 4) != 0) {
            str3 = gMProductInfo.currency;
        }
        String str6 = str3;
        if ((i2 & 8) != 0) {
            j2 = gMProductInfo.priceAmount;
        }
        long j3 = j2;
        if ((i2 & 16) != 0) {
            str4 = gMProductInfo.priceDesc;
        }
        String str7 = str4;
        if ((i2 & 32) != 0) {
            l = gMProductInfo.discountPrice;
        }
        return gMProductInfo.copy(str, str5, str6, j3, str7, l);
    }

    public final String component1() {
        return this.productId;
    }

    public final String component2() {
        return this.price;
    }

    public final String component3() {
        return this.currency;
    }

    public final long component4() {
        return this.priceAmount;
    }

    public final String component5() {
        return this.priceDesc;
    }

    public final Long component6() {
        return this.discountPrice;
    }

    public final GMProductInfo copy(String str, String str2, String str3, long j2, String str4, Long l) {
        return new GMProductInfo(str, str2, str3, j2, str4, l);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof GMProductInfo) {
            GMProductInfo gMProductInfo = (GMProductInfo) obj;
            return Intrinsics.areEqual(this.productId, gMProductInfo.productId) && Intrinsics.areEqual(this.price, gMProductInfo.price) && Intrinsics.areEqual(this.currency, gMProductInfo.currency) && this.priceAmount == gMProductInfo.priceAmount && Intrinsics.areEqual(this.priceDesc, gMProductInfo.priceDesc) && Intrinsics.areEqual(this.discountPrice, gMProductInfo.discountPrice);
        }
        return false;
    }

    public int hashCode() {
        String str = this.productId;
        int hashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.price;
        int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.currency;
        int hashCode3 = (((hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31) + Long.hashCode(this.priceAmount)) * 31;
        String str4 = this.priceDesc;
        int hashCode4 = (hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Long l = this.discountPrice;
        return hashCode4 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "GMProductInfo(productId=" + this.productId + ", price=" + this.price + ", currency=" + this.currency + ", priceAmount=" + this.priceAmount + ", priceDesc=" + this.priceDesc + ", discountPrice=" + this.discountPrice + ')';
    }

    public GMProductInfo(String str, String str2, String str3, long j2, String str4, Long l) {
        this.productId = str;
        this.price = str2;
        this.currency = str3;
        this.priceAmount = j2;
        this.priceDesc = str4;
        this.discountPrice = l;
    }

    public final String getProductId() {
        return this.productId;
    }

    public final String getPrice() {
        return this.price;
    }

    public final String getCurrency() {
        return this.currency;
    }

    public final long getPriceAmount() {
        return this.priceAmount;
    }

    public final String getPriceDesc() {
        return this.priceDesc;
    }

    public final Long getDiscountPrice() {
        return this.discountPrice;
    }
}
