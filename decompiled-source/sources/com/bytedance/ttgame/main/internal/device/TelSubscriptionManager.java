package com.bytedance.ttgame.main.internal.device;

import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TelSubscriptionManager.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/main/internal/device/TelSubscriptionManager;", "", "()V", "KEY_NETWORK_OPERATOR", "", "KEY_NETWORK_OPERATOR_NAME", "TAG", "getNetworkOperator", "context", "Landroid/content/Context;", "main_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class TelSubscriptionManager {
    public static final TelSubscriptionManager INSTANCE = new TelSubscriptionManager();
    private static final String KEY_NETWORK_OPERATOR = "network_operator";
    private static final String KEY_NETWORK_OPERATOR_NAME = "network_operator_name";
    private static final String TAG = "TelSubscriptionManager";

    private TelSubscriptionManager() {
    }

    public final String getNetworkOperator(Context context) {
        String networkOperator;
        String networkOperator2;
        Intrinsics.checkNotNullParameter(context, "context");
        if (Build.VERSION.SDK_INT < 24) {
            Object systemService = context.getSystemService("phone");
            TelephonyManager telephonyManager = systemService instanceof TelephonyManager ? (TelephonyManager) systemService : null;
            JSONObject jSONObject = new JSONObject();
            if (telephonyManager != null) {
                try {
                    networkOperator2 = telephonyManager.getNetworkOperator();
                } catch (JSONException e2) {
                    Log.e(TAG, "getNetworkOperator: ", e2);
                }
            } else {
                networkOperator2 = null;
            }
            jSONObject.put(KEY_NETWORK_OPERATOR, networkOperator2);
            jSONObject.put(KEY_NETWORK_OPERATOR_NAME, telephonyManager != null ? telephonyManager.getNetworkOperatorName() : null);
            String jSONObject2 = jSONObject.toString();
            Intrinsics.checkNotNullExpressionValue(jSONObject2, "jsonObject.toString()");
            return jSONObject2;
        }
        int defaultDataSubscriptionId = SubscriptionManager.getDefaultDataSubscriptionId();
        Object systemService2 = context.getSystemService("phone");
        TelephonyManager telephonyManager2 = systemService2 instanceof TelephonyManager ? (TelephonyManager) systemService2 : null;
        TelephonyManager createForSubscriptionId = telephonyManager2 != null ? telephonyManager2.createForSubscriptionId(defaultDataSubscriptionId) : null;
        JSONObject jSONObject3 = new JSONObject();
        if (createForSubscriptionId != null) {
            try {
                networkOperator = createForSubscriptionId.getNetworkOperator();
            } catch (JSONException e3) {
                Log.e(TAG, "getNetworkOperator: ", e3);
            }
        } else {
            networkOperator = null;
        }
        jSONObject3.put(KEY_NETWORK_OPERATOR, networkOperator);
        jSONObject3.put(KEY_NETWORK_OPERATOR_NAME, createForSubscriptionId != null ? createForSubscriptionId.getNetworkOperatorName() : null);
        String jSONObject4 = jSONObject3.toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject4, "jsonObject.toString()");
        return jSONObject4;
    }
}
