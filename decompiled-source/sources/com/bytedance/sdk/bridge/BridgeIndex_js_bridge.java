package com.bytedance.sdk.bridge;

import com.bytedance.sdk.bridge.annotation.BridgePrivilege;
import com.bytedance.sdk.bridge.annotation.BridgeSyncType;
import gsdk.impl.webview.DEFAULT.a;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BridgeIndex_js_bridge implements n {
    private static Map<Class<?>, p> sSubscriberInfoMap = new HashMap();
    private static Map<String, Class<?>> sClassNameMap = new HashMap();

    public void putSubscriberInfoMap(Class<?> cls) {
        if (cls.equals(com.bytedance.sdk.bridge.js.a.class)) {
            try {
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod("close", new Class[0]), "close", "public", BridgeSyncType.ASYNC, new h[0]);
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod(a.e.o, com.bytedance.sdk.bridge.model.d.class), a.e.o, "public", BridgeSyncType.ASYNC, new h[]{new h(1)});
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod(a.e.p, com.bytedance.sdk.bridge.model.d.class), a.e.p, "public", BridgeSyncType.ASYNC, new h[]{new h(1)});
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod(a.e.f20850j, new Class[0]), a.e.f20850j, "public", BridgeSyncType.ASYNC, new h[0]);
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod(a.e.k, new Class[0]), a.e.k, "public", BridgeSyncType.ASYNC, new h[0]);
                putSubscriberInfo(com.bytedance.sdk.bridge.js.a.class, com.bytedance.sdk.bridge.js.a.class.getDeclaredMethod(a.e.f20849i, String.class, JSONObject.class), a.e.f20849i, BridgePrivilege.PROTECTED, BridgeSyncType.ASYNC, new h[]{new h(0, String.class, "title", "", false), new h(0, JSONObject.class, "__all_params__", null, false)});
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                sSubscriberInfoMap.remove(com.bytedance.sdk.bridge.js.a.class);
            }
        }
    }

    private static void putSubscriberInfo(Class<?> cls, Method method, String str, String str2, String str3, h[] hVarArr) {
        p pVar;
        method.setAccessible(true);
        if (sSubscriberInfoMap.containsKey(cls)) {
            pVar = sSubscriberInfoMap.get(cls);
        } else {
            p pVar2 = new p();
            sSubscriberInfoMap.put(cls, pVar2);
            pVar = pVar2;
        }
        pVar.putMethodInfo(str, new g(method, str, str2, str3, hVarArr));
    }

    @Override // com.bytedance.sdk.bridge.n
    public void getSubscriberInfoMap(Map<Class<?>, p> map, String str) {
        if (sClassNameMap.isEmpty()) {
            getSubscriberClassMap();
        }
        if (sClassNameMap.containsKey(str)) {
            putSubscriberInfoMap(sClassNameMap.get(str));
        }
        map.putAll(sSubscriberInfoMap);
    }

    private void getSubscriberClassMap() {
        try {
            sClassNameMap.put("close", com.bytedance.sdk.bridge.js.a.class);
            sClassNameMap.put(a.e.o, com.bytedance.sdk.bridge.js.a.class);
            sClassNameMap.put(a.e.p, com.bytedance.sdk.bridge.js.a.class);
            sClassNameMap.put(a.e.f20850j, com.bytedance.sdk.bridge.js.a.class);
            sClassNameMap.put(a.e.k, com.bytedance.sdk.bridge.js.a.class);
            sClassNameMap.put(a.e.f20849i, com.bytedance.sdk.bridge.js.a.class);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.bytedance.sdk.bridge.n
    public void getSubscriberClassMap(Map<String, Class<?>> map) {
        getSubscriberClassMap();
        map.putAll(sClassNameMap);
    }
}
