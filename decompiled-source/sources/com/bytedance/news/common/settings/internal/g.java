package com.bytedance.news.common.settings.internal;

import com.bytedance.news.common.settings.api.annotation.ILocalSettings;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: LocalSettingsCache.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ILocalSettings>, ILocalSettings> f9811a = new ConcurrentHashMap<>();

    public <T extends ILocalSettings> T obtain(Class<T> cls, com.bytedance.news.common.settings.b bVar, String str) {
        Object obj = (T) this.f9811a.get(cls);
        if (obj == null) {
            com.bytedance.news.common.settings.api.annotation.j jVar = (com.bytedance.news.common.settings.api.annotation.j) cls.getAnnotation(com.bytedance.news.common.settings.api.annotation.j.class);
            String str2 = jVar == null ? "" : jVar.settingsId();
            if (!str.equals(str2)) {
                throw new IllegalArgumentException("Settings声明id与Manager不匹配：" + str2 + " - ");
            }
            synchronized (this) {
                Object obj2 = (ILocalSettings) this.f9811a.get(cls);
                if (obj2 == null) {
                    ILocalSettings a2 = a(cls, bVar);
                    if (a2 != null) {
                        this.f9811a.put(cls, a2);
                    }
                    obj = (T) a2;
                } else {
                    obj = obj2;
                }
            }
        }
        return (T) obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends ILocalSettings> T a(Class<T> cls, com.bytedance.news.common.settings.b bVar) {
        InvocationTargetException invocationTargetException;
        Constructor<?> constructor;
        com.bytedance.news.common.settings.api.annotation.j jVar;
        com.bytedance.news.common.settings.api.j storageFactory = bVar.getStorageFactory();
        T t = (T) j.create(cls, storageFactory);
        if (t != null) {
            return t;
        }
        try {
            constructor = Class.forName(cls.getName() + "$$Impl", true, cls.getClassLoader()).getConstructor(com.bytedance.news.common.settings.api.i.class);
            jVar = (com.bytedance.news.common.settings.api.annotation.j) cls.getAnnotation(com.bytedance.news.common.settings.api.annotation.j.class);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            invocationTargetException = e2;
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
            invocationTargetException = e3;
        } catch (InstantiationException e4) {
            e4.printStackTrace();
            invocationTargetException = e4;
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
            invocationTargetException = e5;
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
            invocationTargetException = e6;
        }
        if (jVar != null) {
            return (T) constructor.newInstance(storageFactory.create(jVar.storageKey()));
        }
        invocationTargetException = null;
        throw new IllegalStateException("获取settings实例失败，检查warning日志确认问题", invocationTargetException);
    }
}
