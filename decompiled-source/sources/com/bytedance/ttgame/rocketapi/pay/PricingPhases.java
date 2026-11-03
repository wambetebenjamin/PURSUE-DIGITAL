package com.bytedance.ttgame.rocketapi.pay;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: RocketGoods.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087\b\u0018\u00002\u00020\u0001B=\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0016\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0019\u001a\u00020\tHÆ\u0003J\t\u0010\u001a\u001a\u00020\tHÆ\u0003JK\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001f\u001a\u00020\tHÖ\u0001J\t\u0010 \u001a\u00020\u0005HÖ\u0001R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\r¨\u0006!"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/pay/PricingPhases;", "", "priceAmountMicros", "", "priceCurrencyCode", "", "formattedPrice", "billingPeriod", "recurrenceMode", "", "billingCycleCount", "(JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V", "getBillingCycleCount", "()I", "getBillingPeriod", "()Ljava/lang/String;", "getFormattedPrice", "getPriceAmountMicros", "()J", "getPriceCurrencyCode", "getRecurrenceMode", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "pay_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class PricingPhases {
    private final int billingCycleCount;
    private final String billingPeriod;
    private final String formattedPrice;
    private final long priceAmountMicros;
    private final String priceCurrencyCode;
    private final int recurrenceMode;

    public final long component1() {
        return this.priceAmountMicros;
    }

    public final String component2() {
        return this.priceCurrencyCode;
    }

    public final String component3() {
        return this.formattedPrice;
    }

    public final String component4() {
        return this.billingPeriod;
    }

    public final int component5() {
        return this.recurrenceMode;
    }

    public final int component6() {
        return this.billingCycleCount;
    }

    public final PricingPhases copy(long j2, String str, String str2, String str3, int i2, int i3) {
        return new PricingPhases(j2, str, str2, str3, i2, i3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof PricingPhases) {
            PricingPhases pricingPhases = (PricingPhases) obj;
            return this.priceAmountMicros == pricingPhases.priceAmountMicros && Intrinsics.areEqual(this.priceCurrencyCode, pricingPhases.priceCurrencyCode) && Intrinsics.areEqual(this.formattedPrice, pricingPhases.formattedPrice) && Intrinsics.areEqual(this.billingPeriod, pricingPhases.billingPeriod) && this.recurrenceMode == pricingPhases.recurrenceMode && this.billingCycleCount == pricingPhases.billingCycleCount;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = Long.hashCode(this.priceAmountMicros) * 31;
        String str = this.priceCurrencyCode;
        int hashCode2 = (hashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.formattedPrice;
        int hashCode3 = (hashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.billingPeriod;
        return ((((hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31) + Integer.hashCode(this.recurrenceMode)) * 31) + Integer.hashCode(this.billingCycleCount);
    }

    public String toString() {
        return "PricingPhases(priceAmountMicros=" + this.priceAmountMicros + ", priceCurrencyCode=" + this.priceCurrencyCode + ", formattedPrice=" + this.formattedPrice + ", billingPeriod=" + this.billingPeriod + ", recurrenceMode=" + this.recurrenceMode + ", billingCycleCount=" + this.billingCycleCount + ')';
    }

    public PricingPhases(long j2, String str, String str2, String str3, int i2, int i3) {
        this.priceAmountMicros = j2;
        this.priceCurrencyCode = str;
        this.formattedPrice = str2;
        this.billingPeriod = str3;
        this.recurrenceMode = i2;
        this.billingCycleCount = i3;
    }

    public /* synthetic */ PricingPhases(long j2, String str, String str2, String str3, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? 0L : j2, str, str2, str3, i2, i3);
    }

    public final long getPriceAmountMicros() {
        return this.priceAmountMicros;
    }

    public final String getPriceCurrencyCode() {
        return this.priceCurrencyCode;
    }

    public final String getFormattedPrice() {
        return this.formattedPrice;
    }

    public final String getBillingPeriod() {
        return this.billingPeriod;
    }

    public final int getRecurrenceMode() {
        return this.recurrenceMode;
    }

    public final int getBillingCycleCount() {
        return this.billingCycleCount;
    }
}
