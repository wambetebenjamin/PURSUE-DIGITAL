package com.twitter.sdk.android.core.internal;

import android.net.Uri;
import android.os.Build;
import java.text.Normalizer;
/* loaded from: classes10.dex */
public class TwitterApi {
    public static final String BASE_HOST = "api.twitter.com";
    public static final String BASE_HOST_URL = "https://api.twitter.com";
    private final String baseHostUrl;

    public TwitterApi() {
        this("https://api.twitter.com");
    }

    public TwitterApi(String str) {
        this.baseHostUrl = str;
    }

    public String getBaseHostUrl() {
        return this.baseHostUrl;
    }

    public Uri.Builder buildUponBaseHostUrl(String... strArr) {
        Uri.Builder buildUpon = Uri.parse(getBaseHostUrl()).buildUpon();
        if (strArr != null) {
            for (String str : strArr) {
                buildUpon.appendPath(str);
            }
        }
        return buildUpon;
    }

    public static String buildUserAgent(String str, String str2) {
        return normalizeString(str + '/' + str2 + ' ' + Build.MODEL + '/' + Build.VERSION.RELEASE + " (" + Build.MANUFACTURER + ';' + Build.MODEL + ';' + Build.BRAND + ';' + Build.PRODUCT + ')');
    }

    static String normalizeString(String str) {
        return stripNonAscii(Normalizer.normalize(str, Normalizer.Form.NFD));
    }

    static String stripNonAscii(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt > 31 && charAt < 127) {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }
}
