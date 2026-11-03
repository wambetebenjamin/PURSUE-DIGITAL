package com.ttnet.org.chromium.net;

import com.bytedance.apm.perf.traffic.a;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import java.net.CookieHandler;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@JNINamespace(a.KEY_TRAFFIC_STATUS_NET)
/* loaded from: classes9.dex */
public class TTAndroidGetCookieUtil {
    public static String getCookieHeader(String str) {
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        try {
            CookieHandler cookieHandler = CookieHandler.getDefault();
            Map<String, List<String>> map = cookieHandler != null ? cookieHandler.get(new URI(str), hashMap) : null;
            if (map != null && map.size() > 0) {
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if ("Cookie".equalsIgnoreCase(entry.getKey()) && !entry.getValue().isEmpty()) {
                        int i2 = 0;
                        for (String str2 : entry.getValue()) {
                            if (i2 > 0) {
                                sb.append("; ");
                            }
                            sb.append(str2);
                            i2++;
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return sb.toString();
        }
    }
}
