package com.bytedance.bdinstall;

import android.text.TextUtils;
import com.ss.android.common.applog.UrlConfig;
/* compiled from: InstallUrl.java */
/* loaded from: classes.dex */
public class ao implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    private final String f3713a;

    /* renamed from: b  reason: collision with root package name */
    private final String f3714b;

    public static ao a(String str) {
        return new ao(str + UrlConfig.PATH_DEVICE_REGISTER, str + UrlConfig.PATH_ACTIVE);
    }

    public ao(String str, String str2) {
        this.f3713a = b(str);
        this.f3714b = b(str2);
    }

    private String b(String str) {
        if (TextUtils.isEmpty(str) || str.startsWith("https")) {
            return str;
        }
        throw new IllegalArgumentException("url protocol should be https");
    }

    public String a() {
        return this.f3713a;
    }

    public String b() {
        return this.f3714b;
    }

    public String toString() {
        return "{r='" + this.f3713a + "', a='" + this.f3714b + "'}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ao) {
            ao aoVar = (ao) obj;
            return TextUtils.equals(this.f3714b, aoVar.f3714b) && TextUtils.equals(this.f3713a, aoVar.f3713a);
        }
        return false;
    }
}
