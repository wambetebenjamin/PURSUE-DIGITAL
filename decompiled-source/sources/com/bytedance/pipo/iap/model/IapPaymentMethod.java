package com.bytedance.pipo.iap.model;

import android.text.TextUtils;
import com.bytedance.pipo.payment.common.lib.c;
/* loaded from: classes2.dex */
public enum IapPaymentMethod {
    GOOGLE(c.Y),
    AMAZON("amazon"),
    UNKNOWN("UNKNOWN");
    
    public final String channelName;

    IapPaymentMethod(String str) {
        this.channelName = str;
    }

    public static IapPaymentMethod parse(String str) {
        IapPaymentMethod iapPaymentMethod = GOOGLE;
        if (TextUtils.equals(str, iapPaymentMethod.channelName)) {
            return iapPaymentMethod;
        }
        IapPaymentMethod iapPaymentMethod2 = AMAZON;
        return TextUtils.equals(str, iapPaymentMethod2.channelName) ? iapPaymentMethod2 : UNKNOWN;
    }
}
