package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import com.bytedance.platform.godzilla.common.c;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes.dex */
public final class AFb1aSDK {
    public static Map<String, String> values(Map<String, String> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            try {
                hashMap.put(URLEncoder.encode(entry.getKey(), c.CHARSET_UTF_8), URLEncoder.encode(entry.getValue(), c.CHARSET_UTF_8));
            } catch (UnsupportedEncodingException e2) {
                AFLogger.afErrorLogForExcManagerOnly("failed to encode map", e2);
            }
        }
        return hashMap;
    }
}
