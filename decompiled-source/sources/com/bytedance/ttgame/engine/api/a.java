package com.bytedance.ttgame.engine.api;

import com.bytedance.react.framework.core.BRNManager;
import com.bytedance.ttgame.engine.network.b;
import java.util.HashMap;
import java.util.Map;
/* compiled from: IRnBaseApi.java */
/* loaded from: classes3.dex */
public class a {
    public static <T> b a(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, Object> map2, Class<T> cls) {
        return BRNManager.newInstance().requestNetwork(z, z2, str, map, null, map2, cls, null, null, null, false, false);
    }

    public static <T> b b(boolean z, boolean z2, String str, Map<String, String> map, Map<String, Object> map2, Class<T> cls) {
        return BRNManager.newInstance().requestNetwork(z, z2, str, null, map, map2, cls, null, null, null, false, false);
    }

    public static <T> b a(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3) {
        return BRNManager.newInstance().requestNetwork(z, z2, str, map, map2, map3, cls, obj, map4, str2, true, z3);
    }

    public static <T> b a(boolean z, boolean z2, String str, Map<String, Object> map, Map<String, String> map2, Map<String, Object> map3, Class<T> cls, Object obj, Map<String, String> map4, String str2, boolean z3, HashMap hashMap) {
        return BRNManager.newInstance().requestNetworkWithTimeout(z, z2, str, map, map2, map3, cls, obj, map4, str2, true, z3, hashMap);
    }

    public static <T> b a(Map<String, Object> map, String str, String str2, Class<T> cls) {
        return a(true, false, BRNManager.newInstance().getGumihoHost() + "gumiho/api/v3/" + str + "/all_bundle/report", null, null, map, cls, null, null, str2, true);
    }
}
