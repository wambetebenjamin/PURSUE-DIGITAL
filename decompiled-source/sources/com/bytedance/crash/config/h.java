package com.bytedance.crash.config;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.crash.BuildConfig;
/* compiled from: NpthBuildConfig.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    public static String f6371a;

    static {
        String a2 = a(BuildConfig.VERSION_NAME, "overseaNoasanInnerLite".toLowerCase());
        if (!TextUtils.isEmpty(a2)) {
            f6371a = a2;
        } else {
            f6371a = "4.2.1.oversea.lite-rc.38-g.oversea";
        }
    }

    public static String a(String str, String str2) {
        try {
            String[] split = str.split("-");
            String str3 = split.length == 2 ? str.split("-")[1] : "";
            String a2 = a(split[0]);
            StringBuilder sb = new StringBuilder();
            sb.append(a2);
            if (str2.contains("oversea")) {
                sb.append(".oversea");
            }
            if (str2.contains("lite")) {
                sb.append(".lite");
            }
            if (!TextUtils.isEmpty(str3)) {
                sb.append("-");
                sb.append(str3);
            }
            return sb.toString();
        } catch (Throwable th) {
            Log.e("NpthBuildConfig", th.toString(), th);
            return null;
        }
    }

    public static String a(String str) {
        if (str == null) {
            return null;
        }
        int i2 = 1;
        if (Character.isDigit(str.charAt(str.length() - 1))) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char charAt = str.charAt(0);
        while (i2 < str.length()) {
            char charAt2 = str.charAt(i2);
            if (!Character.isDigit(str.charAt(i2)) && charAt2 != '.') {
                break;
            }
            sb.append(charAt);
            i2++;
            charAt = charAt2;
        }
        return sb.toString();
    }
}
