package com.android.billingclient.api;

import android.text.TextUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: com.android.billingclient:billing@@8.2.1 */
/* loaded from: classes.dex */
public final class UnfetchedProduct {
    private final String jsonString;
    private final String productId;
    private final String productType;
    private final String serializedDocid;
    private final int statusCode;

    /* compiled from: com.android.billingclient:billing@@8.2.1 */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes.dex */
    public @interface StatusCode {
        public static final int INVALID_PRODUCT_ID_FORMAT = 2;
        public static final int NO_ELIGIBLE_OFFER = 4;
        public static final int PRODUCT_NOT_FOUND = 3;
        public static final int UNKNOWN = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public UnfetchedProduct(String str) throws JSONException {
        this.jsonString = str;
        JSONObject jSONObject = new JSONObject(str);
        this.productId = jSONObject.optString("productId");
        String optString = jSONObject.optString("type");
        this.productType = optString;
        this.statusCode = jSONObject.has("statusCode") ? jSONObject.optInt("statusCode") : 0;
        if (TextUtils.isEmpty(optString)) {
            throw new IllegalArgumentException("Product type cannot be empty.");
        }
        this.serializedDocid = jSONObject.optString("serializedDocid");
    }

    public static UnfetchedProduct fromJson(String str) throws JSONException {
        return new UnfetchedProduct(str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof UnfetchedProduct) {
            return TextUtils.equals(this.jsonString, ((UnfetchedProduct) obj).jsonString);
        }
        return false;
    }

    public String getProductId() {
        return this.productId;
    }

    public String getProductType() {
        return this.productType;
    }

    public String getSerializedDocid() {
        return this.serializedDocid;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public int hashCode() {
        return this.jsonString.hashCode();
    }

    public String toString() {
        return "UnfetchedProduct{productId='" + this.productId + "', productType='" + this.productType + "', statusCode=" + this.statusCode + "}";
    }
}
