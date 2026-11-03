package com.android.billingclient.api;

import android.text.TextUtils;
import com.ss.android.socialbase.downloader.constants.DBDefinition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class ProductDetails {
    private final String zza;
    private final JSONObject zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final String zzi;
    private final List zzj;
    private final List zzk;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class InstallmentPlanDetails {
        private final int commitmentPaymentsCount;
        private final int subsequentCommitmentPaymentsCount;

        InstallmentPlanDetails(JSONObject jSONObject) throws JSONException {
            this.commitmentPaymentsCount = jSONObject.getInt("commitmentPaymentsCount");
            this.subsequentCommitmentPaymentsCount = jSONObject.optInt("subsequentCommitmentPaymentsCount");
        }

        public int getInstallmentPlanCommitmentPaymentsCount() {
            return this.commitmentPaymentsCount;
        }

        public int getSubsequentInstallmentPlanCommitmentPaymentsCount() {
            return this.subsequentCommitmentPaymentsCount;
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class OneTimePurchaseOfferDetails {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final String zze;
        private final String zzf;
        private final List zzg;
        private final Long zzh;
        private final DiscountDisplayInfo zzi;
        private final ValidTimeWindow zzj;
        private final LimitedQuantityInfo zzk;
        private final String zzl;
        private final PreorderDetails zzm;
        private final RentalDetails zzn;
        private final zzdp zzo;

        /* compiled from: com.android.billingclient:billing@@8.2.1 */
        /* loaded from: classes.dex */
        public static final class DiscountDisplayInfo {
            private final Integer zza;
            private final DiscountAmount zzb;

            /* compiled from: com.android.billingclient:billing@@8.2.1 */
            /* loaded from: classes.dex */
            public static final class DiscountAmount {
                private final String zza;
                private final long zzb;
                private final String zzc;

                DiscountAmount(JSONObject jSONObject) {
                    this.zza = jSONObject.optString("formattedDiscountAmount");
                    this.zzb = jSONObject.optLong("discountAmountMicros");
                    this.zzc = jSONObject.optString("discountAmountCurrencyCode");
                }

                public String getDiscountAmountCurrencyCode() {
                    return this.zzc;
                }

                public long getDiscountAmountMicros() {
                    return this.zzb;
                }

                public String getFormattedDiscountAmount() {
                    return this.zza;
                }
            }

            DiscountDisplayInfo(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.has("percentageDiscount") ? Integer.valueOf(jSONObject.optInt("percentageDiscount")) : null;
                JSONObject optJSONObject = jSONObject.optJSONObject("discountAmount");
                this.zzb = optJSONObject != null ? new DiscountAmount(optJSONObject) : null;
            }

            public DiscountAmount getDiscountAmount() {
                return this.zzb;
            }

            public Integer getPercentageDiscount() {
                return this.zza;
            }
        }

        /* compiled from: com.android.billingclient:billing@@8.2.1 */
        /* loaded from: classes.dex */
        public static final class LimitedQuantityInfo {
            private final int zza;
            private final int zzb;

            LimitedQuantityInfo(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.getInt("maximumQuantity");
                this.zzb = jSONObject.getInt("remainingQuantity");
            }

            public int getMaximumQuantity() {
                return this.zza;
            }

            public int getRemainingQuantity() {
                return this.zzb;
            }
        }

        /* compiled from: com.android.billingclient:billing@@8.2.1 */
        /* loaded from: classes.dex */
        public static final class PreorderDetails {
            private final long preorderPresaleEndTimeMillis;
            private final long preorderReleaseTimeMillis;

            PreorderDetails(JSONObject jSONObject) throws JSONException {
                this.preorderReleaseTimeMillis = jSONObject.getLong("preorderReleaseTimeMillis");
                this.preorderPresaleEndTimeMillis = jSONObject.getLong("preorderPresaleEndTimeMillis");
            }

            public long getPreorderPresaleEndTimeMillis() {
                return this.preorderPresaleEndTimeMillis;
            }

            public long getPreorderReleaseTimeMillis() {
                return this.preorderReleaseTimeMillis;
            }
        }

        /* compiled from: com.android.billingclient:billing@@8.2.1 */
        /* loaded from: classes.dex */
        public static final class RentalDetails {
            private final String rentalExpirationPeriod;
            private final String rentalPeriod;

            RentalDetails(JSONObject jSONObject) throws JSONException {
                this.rentalPeriod = jSONObject.getString("rentalPeriod");
                String optString = jSONObject.optString("rentalExpirationPeriod");
                this.rentalExpirationPeriod = true == optString.isEmpty() ? null : optString;
            }

            public String getRentalExpirationPeriod() {
                return this.rentalExpirationPeriod;
            }

            public String getRentalPeriod() {
                return this.rentalPeriod;
            }
        }

        /* compiled from: com.android.billingclient:billing@@8.2.1 */
        /* loaded from: classes.dex */
        public static final class ValidTimeWindow {
            private final Long zza;
            private final Long zzb;

            ValidTimeWindow(JSONObject jSONObject) throws JSONException {
                this.zza = jSONObject.has("startTimeMillis") ? Long.valueOf(jSONObject.optLong("startTimeMillis")) : null;
                this.zzb = jSONObject.has("endTimeMillis") ? Long.valueOf(jSONObject.optLong("endTimeMillis")) : null;
            }

            public Long getEndTimeMillis() {
                return this.zzb;
            }

            public Long getStartTimeMillis() {
                return this.zza;
            }
        }

        OneTimePurchaseOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            String optString = jSONObject.optString("offerIdToken");
            this.zzd = true == optString.isEmpty() ? null : optString;
            String optString2 = jSONObject.optString("offerId");
            this.zze = true == optString2.isEmpty() ? null : optString2;
            String optString3 = jSONObject.optString("purchaseOptionId");
            this.zzf = true == optString3.isEmpty() ? null : optString3;
            jSONObject.optInt("offerType");
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            this.zzg = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.zzg.add(optJSONArray.getString(i2));
                }
            }
            this.zzh = jSONObject.has("fullPriceMicros") ? Long.valueOf(jSONObject.optLong("fullPriceMicros")) : null;
            JSONObject optJSONObject = jSONObject.optJSONObject("discountDisplayInfo");
            this.zzi = optJSONObject == null ? null : new DiscountDisplayInfo(optJSONObject);
            JSONObject optJSONObject2 = jSONObject.optJSONObject("validTimeWindow");
            this.zzj = optJSONObject2 == null ? null : new ValidTimeWindow(optJSONObject2);
            JSONObject optJSONObject3 = jSONObject.optJSONObject("limitedQuantityInfo");
            this.zzk = optJSONObject3 == null ? null : new LimitedQuantityInfo(optJSONObject3);
            this.zzl = jSONObject.optString("serializedDocid");
            JSONObject optJSONObject4 = jSONObject.optJSONObject("preorderDetails");
            this.zzm = optJSONObject4 == null ? null : new PreorderDetails(optJSONObject4);
            JSONObject optJSONObject5 = jSONObject.optJSONObject("rentalDetails");
            this.zzn = optJSONObject5 == null ? null : new RentalDetails(optJSONObject5);
            JSONObject optJSONObject6 = jSONObject.optJSONObject("autoPayDetails");
            this.zzo = optJSONObject6 != null ? new zzdp(optJSONObject6) : null;
            JSONArray optJSONArray2 = jSONObject.optJSONArray("pricingPhases");
            if (optJSONArray2 == null) {
                return;
            }
            new PricingPhases(optJSONArray2);
        }

        public DiscountDisplayInfo getDiscountDisplayInfo() {
            return this.zzi;
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public Long getFullPriceMicros() {
            return this.zzh;
        }

        public LimitedQuantityInfo getLimitedQuantityInfo() {
            return this.zzk;
        }

        public String getOfferId() {
            return this.zze;
        }

        public List<String> getOfferTags() {
            return this.zzg;
        }

        public String getOfferToken() {
            return this.zzd;
        }

        public PreorderDetails getPreorderDetails() {
            return this.zzm;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public String getPurchaseOptionId() {
            return this.zzf;
        }

        public RentalDetails getRentalDetails() {
            return this.zzn;
        }

        public ValidTimeWindow getValidTimeWindow() {
            return this.zzj;
        }

        public final zzdp zza() {
            return this.zzo;
        }

        final String zzb() {
            return this.zzl;
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class PricingPhase {
        private final String zza;
        private final long zzb;
        private final String zzc;
        private final String zzd;
        private final int zze;
        private final int zzf;

        PricingPhase(JSONObject jSONObject) {
            this.zzd = jSONObject.optString("billingPeriod");
            this.zzc = jSONObject.optString("priceCurrencyCode");
            this.zza = jSONObject.optString("formattedPrice");
            this.zzb = jSONObject.optLong("priceAmountMicros");
            this.zzf = jSONObject.optInt("recurrenceMode");
            this.zze = jSONObject.optInt("billingCycleCount");
        }

        public int getBillingCycleCount() {
            return this.zze;
        }

        public String getBillingPeriod() {
            return this.zzd;
        }

        public String getFormattedPrice() {
            return this.zza;
        }

        public long getPriceAmountMicros() {
            return this.zzb;
        }

        public String getPriceCurrencyCode() {
            return this.zzc;
        }

        public int getRecurrenceMode() {
            return this.zzf;
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static class PricingPhases {
        private final List zza;

        PricingPhases(JSONArray jSONArray) {
            ArrayList arrayList = new ArrayList();
            if (jSONArray != null) {
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        arrayList.add(new PricingPhase(optJSONObject));
                    }
                }
            }
            this.zza = arrayList;
        }

        public List<PricingPhase> getPricingPhaseList() {
            return this.zza;
        }
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface RecurrenceMode {
        public static final int FINITE_RECURRING = 2;
        public static final int INFINITE_RECURRING = 1;
        public static final int NON_RECURRING = 3;
    }

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    /* loaded from: classes.dex */
    public static final class SubscriptionOfferDetails {
        private final String zza;
        private final String zzb;
        private final String zzc;
        private final PricingPhases zzd;
        private final List zze;
        private final InstallmentPlanDetails zzf;

        SubscriptionOfferDetails(JSONObject jSONObject) throws JSONException {
            this.zza = jSONObject.optString("basePlanId");
            String optString = jSONObject.optString("offerId");
            this.zzb = true == optString.isEmpty() ? null : optString;
            this.zzc = jSONObject.getString("offerIdToken");
            this.zzd = new PricingPhases(jSONObject.getJSONArray("pricingPhases"));
            JSONObject optJSONObject = jSONObject.optJSONObject("installmentPlanDetails");
            this.zzf = optJSONObject != null ? new InstallmentPlanDetails(optJSONObject) : null;
            JSONObject optJSONObject2 = jSONObject.optJSONObject("transitionPlanDetails");
            if (optJSONObject2 != null) {
                optJSONObject2.getString("productId");
                optJSONObject2.optString("title");
                optJSONObject2.optString("name");
                optJSONObject2.optString("description");
                optJSONObject2.optString("basePlanId");
                JSONObject optJSONObject3 = optJSONObject2.optJSONObject("pricingPhase");
                if (optJSONObject3 != null) {
                    new PricingPhase(optJSONObject3);
                }
            }
            ArrayList arrayList = new ArrayList();
            JSONArray optJSONArray = jSONObject.optJSONArray("offerTags");
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    arrayList.add(optJSONArray.getString(i2));
                }
            }
            this.zze = arrayList;
        }

        public String getBasePlanId() {
            return this.zza;
        }

        public InstallmentPlanDetails getInstallmentPlanDetails() {
            return this.zzf;
        }

        public String getOfferId() {
            return this.zzb;
        }

        public List<String> getOfferTags() {
            return this.zze;
        }

        public String getOfferToken() {
            return this.zzc;
        }

        public PricingPhases getPricingPhases() {
            return this.zzd;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ProductDetails(String str) throws JSONException {
        this.zza = str;
        JSONObject jSONObject = new JSONObject(str);
        this.zzb = jSONObject;
        String optString = jSONObject.optString("productId");
        this.zzc = optString;
        String optString2 = jSONObject.optString("type");
        this.zzd = optString2;
        if (TextUtils.isEmpty(optString)) {
            throw new IllegalArgumentException("Product id cannot be empty.");
        }
        if (TextUtils.isEmpty(optString2)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.zze = jSONObject.optString("title");
        this.zzf = jSONObject.optString("name");
        this.zzg = jSONObject.optString("description");
        jSONObject.optString("packageDisplayName");
        jSONObject.optString(DBDefinition.ICON_URL);
        this.zzh = jSONObject.optString("skuDetailsToken");
        this.zzi = jSONObject.optString("serializedDocid");
        JSONArray optJSONArray = jSONObject.optJSONArray("subscriptionOfferDetails");
        if (optJSONArray != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arrayList.add(new SubscriptionOfferDetails(optJSONArray.getJSONObject(i2)));
            }
            this.zzj = arrayList;
        } else {
            this.zzj = (optString2.equals("subs") || optString2.equals("play_pass_subs")) ? new ArrayList() : null;
        }
        JSONObject optJSONObject = this.zzb.optJSONObject("oneTimePurchaseOfferDetails");
        JSONArray optJSONArray2 = this.zzb.optJSONArray("oneTimePurchaseOfferDetailsList");
        ArrayList arrayList2 = new ArrayList();
        if (optJSONArray2 != null) {
            for (int i3 = 0; i3 < optJSONArray2.length(); i3++) {
                arrayList2.add(new OneTimePurchaseOfferDetails(optJSONArray2.getJSONObject(i3)));
            }
            this.zzk = arrayList2;
        } else if (optJSONObject == null) {
            this.zzk = null;
        } else {
            arrayList2.add(new OneTimePurchaseOfferDetails(optJSONObject));
            this.zzk = arrayList2;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof ProductDetails) {
            return TextUtils.equals(this.zza, ((ProductDetails) obj).zza);
        }
        return false;
    }

    public String getDescription() {
        return this.zzg;
    }

    public String getName() {
        return this.zzf;
    }

    public OneTimePurchaseOfferDetails getOneTimePurchaseOfferDetails() {
        List list = this.zzk;
        if (list == null || list.isEmpty()) {
            return null;
        }
        return (OneTimePurchaseOfferDetails) list.get(0);
    }

    public List<OneTimePurchaseOfferDetails> getOneTimePurchaseOfferDetailsList() {
        return this.zzk;
    }

    public String getProductId() {
        return this.zzc;
    }

    public String getProductType() {
        return this.zzd;
    }

    public List<SubscriptionOfferDetails> getSubscriptionOfferDetails() {
        return this.zzj;
    }

    public String getTitle() {
        return this.zze;
    }

    public int hashCode() {
        return this.zza.hashCode();
    }

    public String toString() {
        List list = this.zzj;
        String obj = this.zzb.toString();
        String valueOf = String.valueOf(list);
        return "ProductDetails{jsonString='" + this.zza + "', parsedJson=" + obj + ", productId='" + this.zzc + "', productType='" + this.zzd + "', title='" + this.zze + "', productDetailsToken='" + this.zzh + "', subscriptionOfferDetails=" + valueOf + "}";
    }

    public final String zza() {
        return this.zzb.optString("packageName");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzb() {
        return this.zzh;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzc(String str) {
        List<OneTimePurchaseOfferDetails> list;
        if (!TextUtils.isEmpty(str) && (list = this.zzk) != null && !list.isEmpty()) {
            for (OneTimePurchaseOfferDetails oneTimePurchaseOfferDetails : list) {
                if (!TextUtils.isEmpty(oneTimePurchaseOfferDetails.zzb()) && Objects.equals(oneTimePurchaseOfferDetails.getOfferToken(), str)) {
                    return oneTimePurchaseOfferDetails.zzb();
                }
            }
        }
        return this.zzi;
    }
}
