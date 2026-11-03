package com.bytedance.ttgame.library.module_manager.internal;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ASMMapServiceLoader.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bJ\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b2\u0006\u0010\n\u001a\u00020\u0005R \u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/GSDKServiceMap;", "", "()V", "serviceMap", "", "", "", "getAllServiceNames", "", "getServicesByName", "serviceName", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class GSDKServiceMap {
    public static final GSDKServiceMap INSTANCE = new GSDKServiceMap();
    private static final Map<String, String[]> serviceMap;

    private GSDKServiceMap() {
    }

    static {
        Map<String, String[]> emptyMap;
        Object obj;
        try {
            obj = Class.forName("com.bytedance.ttgame.library.module_manager.servicemap.__GSDKServiceMap__").getDeclaredField("serviceMap").get(null);
        } catch (Exception e2) {
            Logger.INSTANCE.logWarning("查找 ASM Service Map 注册表失败，将无法通过 ASM Map 加速装载 service！错误: " + e2.getClass().getCanonicalName() + ':' + e2.getMessage());
            emptyMap = MapsKt.emptyMap();
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Array<kotlin.String>>");
        }
        emptyMap = (Map) obj;
        serviceMap = emptyMap;
    }

    public final List<String> getAllServiceNames() {
        return CollectionsKt.toList(serviceMap.keySet());
    }

    public final List<String> getServicesByName(String serviceName) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        String[] strArr = serviceMap.get(serviceName);
        if (strArr != null) {
            return ArraysKt.toList(strArr);
        }
        return null;
    }
}
