package com.android.billingclient.api;

import com.google.firebase.remoteconfig.RemoteConfigConstants;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class BillingConfig {
    private final String countryCode;

    /* JADX INFO: Access modifiers changed from: package-private */
    public BillingConfig(String str) throws JSONException {
        this.countryCode = new JSONObject(str).optString(RemoteConfigConstants.RequestFieldKey.COUNTRY_CODE);
    }

    private BillingConfig(String str, String str2) {
        this.countryCode = str2;
    }

    static BillingConfig forCountryCode(String str) {
        return new BillingConfig(null, str);
    }

    public String getCountryCode() {
        return this.countryCode;
    }
}
