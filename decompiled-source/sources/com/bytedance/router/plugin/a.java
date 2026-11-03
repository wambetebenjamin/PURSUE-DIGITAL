package com.bytedance.router.plugin;

import android.net.Uri;
import android.text.TextUtils;
import java.util.regex.Pattern;
/* compiled from: Plugin.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private String f12222a;

    /* renamed from: b  reason: collision with root package name */
    private String f12223b;

    /* renamed from: c  reason: collision with root package name */
    private String f12224c;

    public a(String str, String str2, String str3) {
        this.f12222a = str;
        this.f12223b = str2;
        this.f12224c = str3;
    }

    public String getName() {
        return this.f12222a;
    }

    public String getPackageName() {
        return this.f12223b;
    }

    public String getSchemaRegExp() {
        return this.f12224c;
    }

    public boolean containsUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String schemeSpecificPart = Uri.parse(str).getSchemeSpecificPart();
        if (TextUtils.isEmpty(schemeSpecificPart)) {
            return false;
        }
        return Pattern.matches(this.f12224c, schemeSpecificPart);
    }
}
