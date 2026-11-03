package com.bytedance.ttnet.cronet;

import com.bytedance.common.utility.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/* compiled from: CronetDataStorageAccess.java */
/* loaded from: classes8.dex */
public class b extends Observable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15233a = "store_idc";

    /* renamed from: b  reason: collision with root package name */
    public static final String f15234b = "store_region";

    /* renamed from: c  reason: collision with root package name */
    public static final String f15235c = "region_source";

    /* renamed from: d  reason: collision with root package name */
    private static final String f15236d = "CronetDataStorageAccess";

    /* renamed from: e  reason: collision with root package name */
    private static volatile b f15237e = null;

    /* renamed from: f  reason: collision with root package name */
    private static volatile String f15238f = "";

    /* renamed from: g  reason: collision with root package name */
    private static volatile String f15239g = "";

    /* renamed from: h  reason: collision with root package name */
    private static volatile String f15240h = "";

    /* renamed from: i  reason: collision with root package name */
    public static final String f15241i = "region_legacy_user";

    /* renamed from: j  reason: collision with root package name */
    public static final String f15242j = "sec_uid";
    public static final String k = "logid";
    private static volatile String l = "";
    private static volatile String m = "";

    public static b a() {
        if (f15237e == null) {
            synchronized (b.class) {
                if (f15237e == null) {
                    f15237e = new b();
                }
            }
        }
        return f15237e;
    }

    public void a(String str, String str2, String str3, String str4, String str5, String str6) {
        if (Logger.debug()) {
            Logger.d(f15236d, "onStoreIdcChanged idc: " + str + " region: " + str2 + " source: " + str3 + " sec_uid:" + str5 + " logid:" + str6);
        }
        f15238f = str;
        f15239g = str2;
        f15240h = str3;
        l = str4;
        m = str5;
        HashMap hashMap = new HashMap();
        hashMap.put(f15233a, str);
        hashMap.put(f15234b, str2);
        hashMap.put(f15235c, str3);
        hashMap.put(f15241i, str4);
        hashMap.put("sec_uid", str5);
        hashMap.put("logid", str6);
        a(hashMap);
    }

    private void a(Map<String, Object> map) {
        setChanged();
        notifyObservers(map);
    }

    public static String b() {
        return f15238f;
    }

    public static String c() {
        return f15239g;
    }

    public static String d() {
        return f15240h;
    }

    public static String e() {
        return l;
    }

    public static String f() {
        return m;
    }
}
