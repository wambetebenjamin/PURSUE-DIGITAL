package com.bytedance.pipo.iap.google.model;

import com.android.billingclient.api.BillingConfig;
import com.bytedance.pipo.iap.model.AbstractChannelConfig;
/* compiled from: GoogleConfig.java */
/* loaded from: classes2.dex */
public class d extends AbstractChannelConfig {

    /* renamed from: a  reason: collision with root package name */
    private BillingConfig f10783a;

    public d(BillingConfig billingConfig) {
        this.f10783a = billingConfig;
        this.countryCode = billingConfig.getCountryCode();
    }
}
