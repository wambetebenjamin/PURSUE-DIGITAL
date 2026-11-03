package com.bytedance.router.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.router.g;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* compiled from: Util.java */
/* loaded from: classes2.dex */
public final class c {
    public static String completeUrl(String str, String str2) {
        if (TextUtils.isEmpty(Uri.parse(str2).getScheme())) {
            if (str2.startsWith("//")) {
                return str + ':' + str2;
            }
            return str + "://" + str2;
        }
        return str2;
    }

    public static String getRealRouteUrl(String str) {
        Uri parse = Uri.parse(str);
        if (parse.isHierarchical()) {
            return !TextUtils.isEmpty(parse.getScheme()) ? "//" + parse.getAuthority() + parse.getPath() : str;
        }
        return null;
    }

    public static String getRouteUrl(String str) {
        Uri parse = Uri.parse(str);
        if (parse.isHierarchical()) {
            if (!TextUtils.isEmpty(parse.getScheme())) {
                return parse.getScheme() + "://" + parse.getAuthority() + parse.getPath();
            }
            return "//" + parse.getAuthority() + parse.getPath();
        }
        return null;
    }

    public static boolean isLegalUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Uri.parse(str).isHierarchical();
    }

    public static boolean isLegalUrl(String str, g gVar) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        Uri parse = Uri.parse(str);
        if (parse.isHierarchical()) {
            String scheme = parse.getScheme();
            if (TextUtils.isEmpty(scheme) || scheme.equals(gVar.getScheme())) {
                return true;
            }
            String[] otherSchemes = gVar.getOtherSchemes();
            if (otherSchemes != null && otherSchemes.length > 0) {
                for (String str2 : otherSchemes) {
                    if (scheme.equals(str2)) {
                        return true;
                    }
                }
            }
            return false;
        }
        return false;
    }

    public static Map<String, String> sliceUrlParams(String str) {
        if (TextUtils.isEmpty(str)) {
            b.e("Slice url params but the url is null!!!");
            return Collections.EMPTY_MAP;
        }
        Uri parse = Uri.parse(str);
        if (!parse.isHierarchical()) {
            return Collections.EMPTY_MAP;
        }
        HashMap hashMap = new HashMap();
        for (String str2 : parse.getQueryParameterNames()) {
            if (!TextUtils.isEmpty(str2)) {
                String queryParameter = parse.getQueryParameter(str2);
                if (queryParameter == null) {
                    queryParameter = "";
                }
                hashMap.put(str2, queryParameter);
            }
        }
        return hashMap;
    }

    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        int i2 = -1;
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                i2 = applicationInfo.metaData.getInt("UPDATE_VERSION_CODE", -1);
                if (i2 > 0) {
                    return i2;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            if (packageInfo != null) {
                return packageInfo.versionCode;
            }
        } catch (PackageManager.NameNotFoundException unused2) {
        }
        return i2;
    }
}
