package com.bytedance.ttgame.module.pay.api;
/* loaded from: classes6.dex */
public class ProductInfo {
    private int count;
    private String currency;
    private String iapId;
    private String price;
    private long priceAmountMicros;
    private String productId;

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String str) {
        this.productId = str;
    }

    public String getIapId() {
        return this.iapId;
    }

    public void setIapId(String str) {
        this.iapId = str;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int i2) {
        this.count = i2;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public long getPriceAmountMicros() {
        return this.priceAmountMicros;
    }

    public void setPriceAmountMicros(long j2) {
        this.priceAmountMicros = j2;
    }

    public String toString() {
        return "{, iapId='" + this.iapId + "', price='" + this.price + "', count='" + this.count + "', currency='" + this.currency + "', priceAmountMicros='" + this.priceAmountMicros + "'}";
    }
}
