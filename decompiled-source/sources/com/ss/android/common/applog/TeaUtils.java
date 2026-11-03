package com.ss.android.common.applog;

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class TeaUtils {
    public static void ensureNonNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str + "can not be null");
        }
    }

    public static long now() {
        return System.currentTimeMillis();
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }

    public static long optLong(JSONObject jSONObject, String str) {
        if (jSONObject == null) {
            return 0L;
        }
        try {
            return Long.valueOf(jSONObject.optString(str)).longValue();
        } catch (NumberFormatException unused) {
            return 0L;
        }
    }

    public static SimpleDateFormat getCompatFormat(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str, Locale.US);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("Z"));
        return simpleDateFormat;
    }
}
