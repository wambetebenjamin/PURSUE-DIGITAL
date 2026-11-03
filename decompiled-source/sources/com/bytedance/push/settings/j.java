package com.bytedance.push.settings;

import android.content.Context;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LocalSettingsCache.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ILocalSettings>, ILocalSettings> f11789a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final n f11790b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(n nVar) {
        this.f11790b = nVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Object, com.bytedance.push.settings.ILocalSettings] */
    public <T extends ILocalSettings> T a(Context context, Class<T> cls) {
        T t = (T) this.f11789a.get(cls);
        if (t == null) {
            synchronized (this) {
                t = (T) this.f11789a.get(cls);
                if (t == null) {
                    ?? b2 = b(context, cls);
                    this.f11789a.put(cls, b2);
                    t = b2;
                }
            }
        }
        return t;
    }

    private <T extends ILocalSettings> T b(Context context, Class<T> cls) {
        n nVar = this.f11790b;
        T t = (T) m.create(cls, nVar);
        if (t != null) {
            return t;
        }
        try {
            Constructor<?> constructor = Class.forName(cls.getName() + "$$SettingImpl", true, cls.getClassLoader()).getConstructor(Context.class, com.bytedance.push.settings.storage.i.class);
            com.bytedance.push.settings.annotation.f fVar = (com.bytedance.push.settings.annotation.f) cls.getAnnotation(com.bytedance.push.settings.annotation.f.class);
            if (fVar != null) {
                String storageKey = fVar.storageKey();
                boolean supportMultiProcess = fVar.supportMultiProcess();
                com.bytedance.push.settings.logger.b.a().a("createLocalSettingsInstance for " + storageKey);
                return (T) constructor.newInstance(context, nVar.create(context, supportMultiProcess, storageKey, null));
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (InstantiationException e4) {
            e4.printStackTrace();
        } catch (NoSuchMethodException e5) {
            e5.printStackTrace();
        } catch (InvocationTargetException e6) {
            e6.printStackTrace();
        }
        throw new IllegalStateException("get settings instance error，please check warning log");
    }
}
