package com.bytedance.sdk.account.save.entity;

import android.text.TextUtils;
import com.bytedance.sdk.account.save.c;
import java.util.Iterator;
import org.json.JSONObject;
/* compiled from: ExtHelper.java */
/* loaded from: classes2.dex */
public class b {
    public static String encode(a aVar) {
        if (aVar != null) {
            try {
                Integer countryCode = aVar.getCountryCode();
                JSONObject jSONObject = new JSONObject();
                if (countryCode != null) {
                    jSONObject.put(c.b.COUNTRY_CODE, countryCode);
                }
                return jSONObject.toString();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static a decode(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            Iterator<String> keys = jSONObject.keys();
            a aVar = new a();
            if (keys.hasNext()) {
                String next = keys.next();
                if (!TextUtils.isEmpty(next)) {
                    char c2 = 65535;
                    if (next.hashCode() == 1481071862 && next.equals(c.b.COUNTRY_CODE)) {
                        c2 = 0;
                    }
                    aVar.setCountryCode(Integer.valueOf(jSONObject.optInt(next)));
                }
            }
            return aVar;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
