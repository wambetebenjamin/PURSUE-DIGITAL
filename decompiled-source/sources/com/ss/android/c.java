package com.ss.android;

import com.bytedance.common.utility.o;
/* compiled from: CountryCommomParams.java */
/* loaded from: classes9.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static String f18017a;

    /* renamed from: b  reason: collision with root package name */
    private static String f18018b;

    /* renamed from: c  reason: collision with root package name */
    private static String f18019c;

    public static void setGoogleAID(String str) {
        if (o.isEmpty(str) || str.equals(f18017a)) {
            return;
        }
        f18017a = str;
    }

    public static void setAppLanguage(String str) {
        if (o.isEmpty(str) || str.equals(f18018b)) {
            return;
        }
        f18018b = str;
    }

    public static void setAppRegion(String str) {
        if (o.isEmpty(str) || str.equals(f18019c)) {
            return;
        }
        f18019c = str;
    }

    public static String getAppLanguage() {
        return f18018b;
    }

    public static String getAppRegion() {
        return f18019c;
    }
}
