package com.bytedance.ttgame.rocketapi.pay;
/* loaded from: classes7.dex */
public class RocketProductInfo {
    private String currency;
    private long discountPrice;
    private String price;
    private long priceAmountMicros;
    private String productId;

    public RocketProductInfo() {
    }

    public RocketProductInfo(String str, String str2, String str3, long j2) {
        this.productId = str;
        this.price = str2;
        this.currency = str3;
        this.priceAmountMicros = j2;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getPrice() {
        return this.price;
    }

    public String getCurrency() {
        return this.currency;
    }

    public long getPriceAmountMicros() {
        return this.priceAmountMicros;
    }

    public long getDiscountPrice() {
        return this.discountPrice;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setPriceAmountMicros(long j2) {
        this.priceAmountMicros = j2;
    }

    public void setDiscountPrice(long j2) {
        this.discountPrice = j2;
    }

    public String toString() {
        return "RocketProductInfo{productId='" + this.productId + "', price='" + this.price + "', currency='" + this.currency + "', priceAmountMicros=" + this.priceAmountMicros + ", discountPrice=" + this.discountPrice + '}';
    }
}
