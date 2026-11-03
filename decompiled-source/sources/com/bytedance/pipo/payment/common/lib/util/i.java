package com.bytedance.pipo.payment.common.lib.util;

import android.content.Context;
import android.text.TextUtils;
import com.facebook.internal.security.CertificateUtil;
import java.util.HashMap;
import java.util.Map;
/* compiled from: Utils.java */
/* loaded from: classes2.dex */
public class i {
    public static String a(Context context) {
        return context.getPackageName();
    }

    public static String b(Map<String, Long> map) {
        StringBuilder sb = new StringBuilder();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, Long> entry : map.entrySet()) {
                sb.append(entry.getKey());
                sb.append(CertificateUtil.DELIMITER);
                sb.append(entry.getValue());
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public static Map<String, Long> c(String str) {
        HashMap hashMap = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            for (String str2 : str.split(",")) {
                String[] split = str2.split(CertificateUtil.DELIMITER);
                if (split.length == 2) {
                    hashMap.put(split[0], Long.valueOf(split[1]));
                }
            }
        }
        return hashMap;
    }
}
