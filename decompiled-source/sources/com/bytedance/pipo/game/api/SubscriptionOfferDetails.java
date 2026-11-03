package com.bytedance.pipo.game.api;

import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;
/* compiled from: Products.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/bytedance/pipo/game/api/SubscriptionOfferDetails;", "Ljava/io/Serializable;", "()V", "basePlaneId", "", "getBasePlaneId", "()Ljava/lang/String;", "setBasePlaneId", "(Ljava/lang/String;)V", "offerId", "getOfferId", "setOfferId", "offerIdToken", "getOfferIdToken", "setOfferIdToken", "pricingPhases", "", "Lcom/bytedance/pipo/game/api/PricingPhases;", "getPricingPhases", "()Ljava/util/List;", "setPricingPhases", "(Ljava/util/List;)V", "library_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class SubscriptionOfferDetails implements Serializable {
    private String basePlaneId;
    private String offerId;
    private String offerIdToken;
    private List<PricingPhases> pricingPhases;

    public final String getOfferIdToken() {
        return this.offerIdToken;
    }

    public final void setOfferIdToken(String str) {
        this.offerIdToken = str;
    }

    public final String getBasePlaneId() {
        return this.basePlaneId;
    }

    public final void setBasePlaneId(String str) {
        this.basePlaneId = str;
    }

    public final String getOfferId() {
        return this.offerId;
    }

    public final void setOfferId(String str) {
        this.offerId = str;
    }

    public final List<PricingPhases> getPricingPhases() {
        return this.pricingPhases;
    }

    public final void setPricingPhases(List<PricingPhases> list) {
        this.pricingPhases = list;
    }
}
