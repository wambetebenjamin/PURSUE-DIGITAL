package com.bytedance.crash.dumper;

import android.text.TextUtils;
import com.bytedance.crash.util.u;
import com.bytedance.crash.util.x;
import java.io.File;
import java.io.Serializable;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class LocaleInfo implements Serializable {
    private static final String FILE_NAME = "locale.inf";
    private static final String KEY_LOC_NETWORK_ACCESS = "access";
    private static final String KEY_LOC_TIMEZONE = "timezone";
    private static final String UNKNOWN = "unknown";
    private static final long serialVersionUID = 1;
    public final String mNetworkAccessType = getNetworkAccessType();
    public final int mTimeZone = getTimeZone();

    private LocaleInfo() {
    }

    public static void putTo(JSONObject jSONObject) {
        putTo(jSONObject, null);
    }

    public static void putTo(JSONObject jSONObject, File file) {
        LocaleInfo localeInfo;
        if (file != null) {
            localeInfo = load(file);
        } else {
            localeInfo = new LocaleInfo();
        }
        if (localeInfo != null) {
            try {
                jSONObject.put("access", localeInfo.mNetworkAccessType);
                jSONObject.put("timezone", localeInfo.mTimeZone);
            } catch (JSONException unused) {
            }
        }
    }

    public static void dump(File file) {
        u.a(new File(file, FILE_NAME), new LocaleInfo());
    }

    public static LocaleInfo load(File file) {
        return (LocaleInfo) u.a(new File(file, FILE_NAME));
    }

    public static String getNetworkAccessType() {
        String a2 = x.a();
        return TextUtils.isEmpty(a2) ? "unknown" : a2;
    }

    public static int getTimeZone() {
        int rawOffset = TimeZone.getDefault().getRawOffset() / 3600000;
        if (rawOffset < -12) {
            return -12;
        }
        if (rawOffset > 12) {
            return 12;
        }
        return rawOffset;
    }
}
