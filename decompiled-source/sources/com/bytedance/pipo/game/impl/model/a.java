package com.bytedance.pipo.game.impl.model;

import com.android.billingclient.api.ProductDetails;
import com.bytedance.pipo.game.api.p;
import com.bytedance.pipo.game.api.s;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ModelMapper.java */
/* loaded from: classes2.dex */
public class a {
    public static List<p> a(List<b> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(a(list.get(i2)));
        }
        return arrayList;
    }

    public static List<s> b(List<b> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(b(list.get(i2)));
        }
        return arrayList;
    }

    private static p a(b bVar) {
        if (bVar == null) {
            return null;
        }
        p pVar = new p();
        pVar.a(bVar.a()).b(bVar.b()).c(bVar.e()).a(bVar.c()).d(bVar.d()).f(bVar.f()).g(bVar.g());
        return pVar;
    }

    private static s b(b bVar) {
        if (bVar == null) {
            return null;
        }
        s sVar = new s();
        sVar.a(bVar.a());
        sVar.c(bVar.f());
        sVar.e(bVar.g());
        List<ProductDetails.SubscriptionOfferDetails> h2 = bVar.h();
        ArrayList arrayList = new ArrayList();
        for (ProductDetails.SubscriptionOfferDetails subscriptionOfferDetails : h2) {
            s.b bVar2 = new s.b();
            bVar2.a(subscriptionOfferDetails.getOfferToken());
            bVar2.a(subscriptionOfferDetails.getOfferTags());
            bVar2.c(subscriptionOfferDetails.getOfferId());
            bVar2.b(subscriptionOfferDetails.getBasePlanId());
            List<ProductDetails.PricingPhase> pricingPhaseList = subscriptionOfferDetails.getPricingPhases().getPricingPhaseList();
            ArrayList arrayList2 = new ArrayList();
            for (ProductDetails.PricingPhase pricingPhase : pricingPhaseList) {
                s.a aVar = new s.a();
                aVar.a(pricingPhase.getPriceCurrencyCode());
                aVar.b(pricingPhase.getFormattedPrice());
                aVar.b(pricingPhase.getBillingCycleCount());
                aVar.c(pricingPhase.getBillingPeriod());
                aVar.a(pricingPhase.getRecurrenceMode());
                aVar.a(pricingPhase.getPriceAmountMicros());
                arrayList2.add(aVar);
            }
            bVar2.b(arrayList2);
            arrayList.add(bVar2);
        }
        sVar.a(arrayList);
        return sVar;
    }
}
