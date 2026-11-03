package com.bytedance.pipo.iap.google.model;

import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.ProductDetails;
import com.bytedance.pipo.iap.common.ability.model.OrderData;
/* compiled from: GoogleProductDetails.java */
/* loaded from: classes2.dex */
public class c implements com.bytedance.pipo.iap.google.interfaze.a {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.pipo.iap.google.interfaze.a f10782a;

    public c(ProductDetails productDetails) {
        this.f10782a = new com.bytedance.pipo.iap.google.interfaze.impl.a(productDetails);
    }

    @Override // com.bytedance.pipo.iap.google.interfaze.a
    public void a(BillingFlowParams.Builder builder, OrderData orderData) {
        this.f10782a.a(builder, orderData);
    }

    @Override // com.bytedance.pipo.iap.google.interfaze.a
    public b a() {
        return this.f10782a.a();
    }

    @Override // com.bytedance.pipo.iap.google.interfaze.a
    public String b() {
        return this.f10782a.b();
    }

    @Override // com.bytedance.pipo.iap.google.interfaze.a
    public boolean c() {
        return this.f10782a.c();
    }
}
