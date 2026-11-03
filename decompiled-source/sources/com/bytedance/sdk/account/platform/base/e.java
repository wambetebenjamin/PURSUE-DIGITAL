package com.bytedance.sdk.account.platform.base;

import android.content.Context;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: AuthorizeFramework.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static Context f12794a;

    /* renamed from: b  reason: collision with root package name */
    private static Map<Class, com.bytedance.sdk.account.platform.api.d> f12795b = new ConcurrentHashMap();

    public static <T extends com.bytedance.sdk.account.platform.api.d> void registerService(Class<T> cls, com.bytedance.sdk.account.platform.api.d dVar) {
        if (dVar != null) {
            f12795b.put(cls, dVar);
        }
    }

    public static <T extends com.bytedance.sdk.account.platform.api.d, W extends c<T>> T createService(Context context, W w) {
        return (T) w.createService(context);
    }

    public static <T extends com.bytedance.sdk.account.platform.api.d> T getService(Class<T> cls) {
        return (T) f12795b.get(cls);
    }

    public static <T extends f> void init(Context context, T... tArr) {
        f12794a = context;
        if (tArr != null) {
            for (T t : tArr) {
                t.init(context);
            }
        }
    }
}
