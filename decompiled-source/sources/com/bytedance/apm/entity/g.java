package com.bytedance.apm.entity;

import android.text.TextUtils;
/* compiled from: LocalVersionInfo.java */
/* loaded from: classes.dex */
public class g {
    public String appVersion;
    public long id;
    public String manifestVersionCode;
    public String updateVersionCode;
    public String versionCode;
    public String versionName;

    public g(long j2, String str, String str2, String str3, String str4, String str5) {
        this.id = j2;
        this.versionCode = str;
        this.versionName = str2;
        this.manifestVersionCode = str3;
        this.updateVersionCode = str4;
        this.appVersion = str5;
    }

    public g(String str, String str2, String str3, String str4, String str5) {
        this.versionCode = str;
        this.versionName = str2;
        this.manifestVersionCode = str3;
        this.updateVersionCode = str4;
        this.appVersion = str5;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            g gVar = (g) obj;
            return TextUtils.equals(this.versionCode, gVar.versionCode) && TextUtils.equals(this.versionName, gVar.versionName) && TextUtils.equals(this.manifestVersionCode, gVar.manifestVersionCode) && TextUtils.equals(this.updateVersionCode, gVar.updateVersionCode) && TextUtils.equals(this.appVersion, gVar.appVersion);
        }
        return false;
    }

    public int hashCode() {
        return a(this.versionCode) + a(this.versionName) + a(this.manifestVersionCode) + a(this.updateVersionCode) + a(this.appVersion);
    }

    private static int a(Object obj) {
        if (obj != null) {
            return obj.hashCode();
        }
        return 0;
    }
}
