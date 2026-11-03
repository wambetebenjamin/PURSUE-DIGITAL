package com.bytedance.ttgame.library.module_manager.internal;

import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ASMMAPWrappersLoader.kt */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0004\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0005R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/GSDKWrappersMap;", "", "()V", "wrappersMap", "", "", "getAllWrappers", "getWrappersByName", "serviceName", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
final class GSDKWrappersMap {
    public static final GSDKWrappersMap INSTANCE = new GSDKWrappersMap();
    private static final Map<String, String> wrappersMap;

    private GSDKWrappersMap() {
    }

    static {
        Map<String, String> emptyMap;
        Object obj;
        try {
            obj = Class.forName("com.bytedance.ttgame.library.module_manager.wrappersmap.__GSDKWrappersMap__").getDeclaredField("wrappersMap").get(null);
        } catch (Exception e2) {
            Logger.INSTANCE.logWarning("查找 ASM Wrappers Map 注册表失败，将无法通过 ASM Map 加速装载 wrappers！错误: " + e2.getClass().getCanonicalName() + ':' + e2.getMessage());
            emptyMap = MapsKt.emptyMap();
        }
        if (obj == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.String>");
        }
        emptyMap = (Map) obj;
        wrappersMap = emptyMap;
    }

    public final Map<String, String> getAllWrappers() {
        return wrappersMap;
    }

    public final String getWrappersByName(String serviceName) {
        Intrinsics.checkNotNullParameter(serviceName, "serviceName");
        return wrappersMap.get(serviceName);
    }
}
