package com.bytedance.crash.runtime;

import android.text.TextUtils;
import com.bytedance.crash.util.y;
import org.json.JSONArray;
/* compiled from: AllDefaultUrls.java */
/* loaded from: classes2.dex */
public class a {
    private static String l = "https://mon-va.tiktokv.com";

    public static String a() {
        return l;
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        y.i("setDomainName:" + str);
        l = "https://" + str;
    }

    public static void a(String str, JSONArray jSONArray) {
        if (TextUtils.isEmpty(str) || jSONArray == null || jSONArray.length() == 0) {
            y.i("setDomainName: invalid param");
        } else if (str.equals(l)) {
            y.i("setDomainName: same domain");
        } else {
            boolean z = false;
            int i2 = 0;
            while (true) {
                if (i2 >= jSONArray.length()) {
                    break;
                } else if (str.equals(jSONArray.optString(i2))) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z) {
                y.i("setDomainName: check valid fail");
                return;
            }
            y.i("setDomainName:" + str);
            l = str;
        }
    }
}
