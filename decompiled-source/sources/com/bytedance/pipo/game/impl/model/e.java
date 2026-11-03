package com.bytedance.pipo.game.impl.model;

import android.text.TextUtils;
import com.android.billingclient.api.BillingResult;
import com.bytedance.librarian.c;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: PayResult.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: c  reason: collision with root package name */
    private static final String[] f10360c = "0:OK/1:User Canceled/2:Unknown/3:Billing Unavailable/4:Item unavailable/5:Developer Error/6:Error/7:Item Already Owned/8:Item not owned".split(c.a.SEPARATOR);

    /* renamed from: a  reason: collision with root package name */
    public int f10361a;

    /* renamed from: b  reason: collision with root package name */
    public String f10362b;

    /* compiled from: PayResult.java */
    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes2.dex */
    public @interface a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f10363a = Integer.MIN_VALUE;

        /* renamed from: b  reason: collision with root package name */
        public static final int f10364b = -4;

        /* renamed from: c  reason: collision with root package name */
        public static final int f10365c = -3;

        /* renamed from: d  reason: collision with root package name */
        public static final int f10366d = -2;

        /* renamed from: e  reason: collision with root package name */
        public static final int f10367e = -1;

        /* renamed from: f  reason: collision with root package name */
        public static final int f10368f = 0;

        /* renamed from: g  reason: collision with root package name */
        public static final int f10369g = 1;

        /* renamed from: h  reason: collision with root package name */
        public static final int f10370h = 2;

        /* renamed from: i  reason: collision with root package name */
        public static final int f10371i = 3;

        /* renamed from: j  reason: collision with root package name */
        public static final int f10372j = 4;
        public static final int k = 5;
        public static final int l = 6;
        public static final int m = 7;
        public static final int n = 8;
        public static final int o = 12;
    }

    public e(BillingResult billingResult) {
        b(billingResult.getResponseCode());
        this.f10362b = TextUtils.isEmpty(billingResult.getDebugMessage()) ? c(this.f10361a) : billingResult.getDebugMessage();
    }

    public e(int i2) {
        b(i2);
    }

    private void b(int i2) {
        if (i2 == Integer.MIN_VALUE) {
            this.f10361a = -4;
        } else if (i2 != 12) {
            switch (i2) {
                case -3:
                    this.f10361a = -3;
                    return;
                case -2:
                    this.f10361a = -2;
                    return;
                case -1:
                    this.f10361a = -1;
                    return;
                case 0:
                    this.f10361a = 0;
                    return;
                case 1:
                    this.f10361a = 1;
                    return;
                case 2:
                    this.f10361a = 2;
                    return;
                case 3:
                    this.f10361a = 3;
                    return;
                case 4:
                    this.f10361a = 4;
                    return;
                case 5:
                    this.f10361a = 5;
                    return;
                case 6:
                    this.f10361a = 6;
                    return;
                case 7:
                    this.f10361a = 7;
                    return;
                case 8:
                    this.f10361a = 8;
                    return;
                default:
                    this.f10361a = Integer.MIN_VALUE;
                    return;
            }
        } else {
            this.f10361a = 12;
        }
    }

    public e(int i2, String str) {
        this.f10361a = i2;
        this.f10362b = str;
    }

    private String c(int i2) {
        if (i2 == 12) {
            return "12:Network Error";
        }
        if (i2 >= 0) {
            String[] strArr = f10360c;
            return i2 <= strArr.length + (-1) ? strArr[i2] : "Unknown.";
        }
        return "Unknown.";
    }

    public int a() {
        return this.f10361a;
    }

    public void a(int i2) {
        this.f10361a = i2;
    }

    public void a(String str) {
        this.f10362b = str;
    }

    public String b() {
        return this.f10362b;
    }

    public String toString() {
        return "PayResult{code=" + this.f10361a + ", message='" + this.f10362b + "'}";
    }
}
