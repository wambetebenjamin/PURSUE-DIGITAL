package com.bytedance.sdk.open.tiktok.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes3.dex */
public class SignatureUtils {
    public static boolean validateSign(Context context, String str, String str2) {
        List<String> md5Signs;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && context != null && (md5Signs = getMd5Signs(context, str)) != null && md5Signs.size() > 0) {
            for (String str3 : md5Signs) {
                if (str2.equalsIgnoreCase(str3)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<String> getMd5Signs(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            ArrayList arrayList = new ArrayList();
            Signature[] signatureArr = packageInfo.signatures;
            if (signatureArr != null) {
                for (Signature signature : signatureArr) {
                    arrayList.add(Md5Utils.hexDigest(signature.toByteArray()));
                }
                return arrayList;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return null;
    }

    public static String packageSignature(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 != 0) {
                sb.append(",");
            }
            sb.append(list.get(i2));
        }
        return sb.toString();
    }
}
