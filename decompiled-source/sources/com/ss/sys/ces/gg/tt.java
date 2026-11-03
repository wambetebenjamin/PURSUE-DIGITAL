package com.ss.sys.ces.gg;

import android.util.Log;
import com.bytedance.frameworks.baselib.network.http.e;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tt.g.p.f.n.am;
import tt.g.p.f.n.h;
/* loaded from: classes9.dex */
public class tt {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public class a implements e.a {
        a() {
        }

        @Override // com.bytedance.frameworks.baselib.network.http.e.a
        public Map<String, String> onCallToAddSecurityFactor(String str, Map<String, List<String>> map) {
            return tt.calcHttpRequestChecksum(str, map);
        }
    }

    private static String a(String str) {
        int indexOf = str.indexOf("?");
        int indexOf2 = str.indexOf("#");
        if (indexOf == -1) {
            return null;
        }
        if (indexOf2 == -1) {
            return str.substring(indexOf + 1);
        }
        if (indexOf2 < indexOf) {
            return null;
        }
        return str.substring(indexOf + 1, indexOf2);
    }

    private static String b(String str) {
        String[] split;
        for (String str2 : str.replace(" ", "").split(",")) {
            int indexOf = str2.indexOf("sessionid=");
            if (indexOf != -1) {
                return str2.substring(indexOf + 10);
            }
        }
        return null;
    }

    private static boolean c(String str) {
        return false;
    }

    public static Map<String, String> calcHttpRequestChecksum(String str, Map<String, List<String>> map) {
        String str2;
        HashMap hashMap = new HashMap();
        try {
            if (!str.toLowerCase().contains("http") && !str.toLowerCase().contains("https")) {
                throw new NullPointerException("nein http/https");
            }
            if (str.toLowerCase().contains("x-khronos") && str.toLowerCase().contains("x-gorgon")) {
                throw new NullPointerException("it was");
            }
        } catch (Throwable th) {
            if (!th.getMessage().contains("filter_1")) {
                Log.e("CZL_ALGO", "[-] ****** Gorgon Fetch Error !!!");
                th.printStackTrace();
            }
            hashMap.clear();
        }
        if (c(str)) {
            throw new NullPointerException("filter_1");
        }
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        String a2 = a(str);
        String str3 = null;
        String a3 = (a2 == null || a2.length() <= 0) ? null : h.a(a2);
        String str4 = null;
        String str5 = null;
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getKey().toUpperCase().contains("X-SS-STUB")) {
                str3 = entry.getValue().get(0);
            }
            if (entry.getKey().toUpperCase().contains("COOKIE") && (str2 = entry.getValue().get(0)) != null && str2.length() > 0) {
                str4 = h.a(str2);
                String b2 = b(str2);
                if (b2 != null && b2.length() > 0) {
                    str5 = h.a(b2);
                }
            }
        }
        hashMap.put("X-Khronos", "" + currentTimeMillis);
        hashMap.put("X-Gorgon", am.a(com.ss.sys.ces.a.leviathan(currentTimeMillis, am.a(((a3 == null || a3.length() == 0) ? "00000000000000000000000000000000" : "00000000000000000000000000000000") + ((str3 == null || str3.length() == 0) ? "00000000000000000000000000000000" : "00000000000000000000000000000000") + ((str4 == null || str4.length() == 0) ? "00000000000000000000000000000000" : "00000000000000000000000000000000") + ((str5 == null || str5.length() == 0) ? "00000000000000000000000000000000" : "00000000000000000000000000000000")))));
        return hashMap;
    }

    public static void init_gorgon() {
        e.a((e.a) new a());
    }
}
