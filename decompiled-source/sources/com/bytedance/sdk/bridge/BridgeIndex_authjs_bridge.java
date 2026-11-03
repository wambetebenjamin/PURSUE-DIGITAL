package com.bytedance.sdk.bridge;

import com.bytedance.sdk.bridge.annotation.BridgeSyncType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class BridgeIndex_authjs_bridge implements n {
    private static Map<Class<?>, p> sSubscriberInfoMap = new HashMap();
    private static Map<String, Class<?>> sClassNameMap = new HashMap();

    public void putSubscriberInfoMap(Class<?> cls) {
        if (cls.equals(com.bytedance.sdk.bridge.js.auth.c.class)) {
            try {
                putSubscriberInfo(com.bytedance.sdk.bridge.js.auth.c.class, com.bytedance.sdk.bridge.js.auth.c.class.getDeclaredMethod("config", com.bytedance.sdk.bridge.model.d.class, JSONObject.class), "config", "public", BridgeSyncType.ASYNC, new h[]{new h(1), new h(0, JSONObject.class, "__all_params__", null, false)});
                putSubscriberInfo(com.bytedance.sdk.bridge.js.auth.c.class, com.bytedance.sdk.bridge.js.auth.c.class.getDeclaredMethod("appConfig", com.bytedance.sdk.bridge.model.d.class, JSONObject.class), "app.config", "public", BridgeSyncType.ASYNC, new h[]{new h(1), new h(0, JSONObject.class, "__all_params__", null, false)});
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
                sSubscriberInfoMap.remove(com.bytedance.sdk.bridge.js.auth.c.class);
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
            sClassNameMap.put("config", com.bytedance.sdk.bridge.js.auth.c.class);
            sClassNameMap.put("app.config", com.bytedance.sdk.bridge.js.auth.c.class);
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
