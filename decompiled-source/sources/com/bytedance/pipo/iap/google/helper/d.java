package com.bytedance.pipo.iap.google.helper;

import android.text.TextUtils;
import com.android.billingclient.api.BillingResult;
import com.bytedance.librarian.c;
import com.bytedance.pipo.iap.common.ability.e;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: GoogleIapResultHelper.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: b  reason: collision with root package name */
    private static final String[] f10761b = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split(c.a.SEPARATOR);

    /* compiled from: GoogleIapResultHelper.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface a {

        /* renamed from: f  reason: collision with root package name */
        public static final int f10762f = 0;

        /* renamed from: g  reason: collision with root package name */
        public static final int f10763g = 1;
    }

    public static e a(BillingResult billingResult) {
        e eVar = new e();
        int responseCode = billingResult.getResponseCode();
        eVar.withErrorCode(responseCode);
        eVar.withMessage(TextUtils.isEmpty(billingResult.getDebugMessage()) ? a(responseCode) : billingResult.getDebugMessage());
        return eVar;
    }

    private static String a(int i2) {
        if (i2 == 12) {
            return "12:Network Error";
        }
        if (i2 >= 0) {
            String[] strArr = f10761b;
            return i2 <= strArr.length + (-1) ? strArr[i2] : "Unknown.";
        }
        return "Unknown.";
    }
}
