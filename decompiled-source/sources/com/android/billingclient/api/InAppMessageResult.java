package com.android.billingclient.api;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class InAppMessageResult {
    private final int zza;
    private final String zzb;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface InAppMessageResponseCode {
        public static final int NO_ACTION_NEEDED = 0;
        public static final int SUBSCRIPTION_STATUS_UPDATED = 1;
    }

    public InAppMessageResult(int i2, String str) {
        this.zza = i2;
        this.zzb = str;
    }

    public String getPurchaseToken() {
        return this.zzb;
    }

    public int getResponseCode() {
        return this.zza;
    }
}
