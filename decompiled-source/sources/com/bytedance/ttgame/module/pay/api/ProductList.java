package com.bytedance.ttgame.module.pay.api;

import java.util.List;
/* loaded from: classes6.dex */
public class ProductList {
    private int code;
    private String message;
    private List<ProductInfo> productInfoList;
    private List<SubscriptionInfo> subscriptionInfoList;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i2) {
        this.code = i2;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public List<ProductInfo> getProductInfoList() {
        return this.productInfoList;
    }

    public void setProductInfoList(List<ProductInfo> list) {
        this.productInfoList = list;
    }

    public List<SubscriptionInfo> getSubscriptionInfoList() {
        return this.subscriptionInfoList;
    }

    public void setSubscriptionInfoList(List<SubscriptionInfo> list) {
        this.subscriptionInfoList = list;
    }

    public String toString() {
        StringBuilder append = new StringBuilder().append("ProductList{code=").append(this.code).append(", message='").append(this.message).append('\'').append(", productInfoList=");
        List<ProductInfo> list = this.productInfoList;
        StringBuilder append2 = append.append(list == null ? "" : list.toString()).append(", subscriptionInfoList=");
        List<SubscriptionInfo> list2 = this.subscriptionInfoList;
        return append2.append(list2 != null ? list2.toString() : "").append('}').toString();
    }
}
