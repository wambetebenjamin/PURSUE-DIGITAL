package com.bytedance.ttnet.config;

import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.o;
import com.bytedance.librarian.c;
import com.meituan.robust.Constants;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/* compiled from: ShareCookieHostsSync.java */
/* loaded from: classes8.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15086a = "ShareCookieHostsSync";

    /* renamed from: b  reason: collision with root package name */
    private static volatile e f15087b;

    public static e a() {
        if (f15087b == null) {
            synchronized (e.class) {
                if (f15087b == null) {
                    f15087b = new e();
                }
            }
        }
        return f15087b;
    }

    public void a(final String str, final String str2) {
        if (str2.equals(str)) {
            return;
        }
        new com.bytedance.common.utility.concurrent.e("Sync-Cookie") { // from class: com.bytedance.ttnet.config.e.1
            @Override // com.bytedance.common.utility.concurrent.e, java.lang.Runnable
            public void run() {
                e.this.b(str, str2);
            }
        }.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str, String str2) {
        CookieHandler cookieHandler = CookieHandler.getDefault();
        if (cookieHandler == null) {
            Logger.e(f15086a, "Default CookieHandler is null.");
            return;
        }
        List<String> a2 = a(str);
        List<String> a3 = a(str2);
        ArrayList<String> arrayList = new ArrayList();
        ArrayList<String> arrayList2 = new ArrayList();
        for (String str3 : a3) {
            if (com.bytedance.ttnet.utils.f.a(str3, a2)) {
                if (Logger.debug()) {
                    Logger.d(f15086a, "kept hosts add: " + str3);
                }
                arrayList.add(str3);
            } else {
                if (Logger.debug()) {
                    Logger.d(f15086a, "added hosts add: " + str3);
                }
                arrayList2.add(str3);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        if (arrayList.isEmpty()) {
            arrayList = arrayList2;
        }
        Map<String, List<String>> hashMap = new HashMap<>();
        for (String str4 : arrayList) {
            try {
                hashMap = cookieHandler.get(new URI("https://" + str4 + c.a.SEPARATOR), null);
            } catch (Throwable th) {
                th.printStackTrace();
            }
            if (hashMap.size() > 0) {
                if (!Logger.debug()) {
                    break;
                }
                Logger.d(f15086a, "Sync cookie from host: " + str4 + " sync cookies: " + hashMap.toString());
                break;
            }
            continue;
        }
        if (hashMap == null || hashMap.isEmpty()) {
            return;
        }
        List<String> list = hashMap.get("Cookie");
        if ((list == null || list.isEmpty()) && ((list = hashMap.get(com.bytedance.frameworks.baselib.network.http.a.f7602g)) == null || list.isEmpty())) {
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        List<String> a4 = a(list);
        for (String str5 : arrayList2) {
            ArrayList arrayList3 = new ArrayList();
            try {
                URI uri = new URI("https://" + str5 + c.a.SEPARATOR);
                for (String str6 : a4) {
                    if (!o.isEmpty(str6)) {
                        arrayList3.add(str6 + "; Domain=" + str5);
                    }
                }
                linkedHashMap.put("Set-Cookie", arrayList3);
                cookieHandler.put(uri, linkedHashMap);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
    }

    private List<String> a(List<String> list) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            for (String str2 : str.split(Constants.PACKNAME_END)) {
                if (!o.isEmpty(str2)) {
                    arrayList.add(str2.trim());
                }
            }
        }
        return arrayList;
    }

    private List<String> a(String str) {
        String[] split;
        ArrayList arrayList = new ArrayList();
        if (o.isEmpty(str)) {
            return arrayList;
        }
        for (String str2 : str.split(",")) {
            if (!o.isEmpty(str2)) {
                arrayList.add(str2.trim());
            }
        }
        return arrayList;
    }
}
