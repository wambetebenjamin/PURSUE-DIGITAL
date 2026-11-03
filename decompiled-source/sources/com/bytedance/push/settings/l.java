package com.bytedance.push.settings;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SettingsCache.java */
/* loaded from: classes2.dex */
public final class l {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ISettings>, ISettings> f11791a = new ConcurrentHashMap<>();

    /* renamed from: b  reason: collision with root package name */
    private final ConcurrentHashMap<Class<? extends ISettings>, Object> f11792b = new ConcurrentHashMap<>();

    public synchronized void updateSettingsData(Context context, JSONObject jSONObject) {
        for (ISettings iSettings : this.f11791a.values()) {
            iSettings.updateSettings(context, jSONObject);
        }
    }

    public <T extends ISettings> T obtain(Context context, Class<T> cls) {
        Object obj;
        ISettings iSettings;
        T t = (T) this.f11791a.get(cls);
        if (t == null) {
            synchronized (this.f11792b) {
                obj = this.f11792b.containsKey(cls) ? this.f11792b.get(cls) : null;
                if (obj == null) {
                    obj = new Object();
                    this.f11792b.put(cls, obj);
                }
            }
            synchronized (obj) {
                ISettings iSettings2 = this.f11791a.get(cls);
                if (iSettings2 == null) {
                    iSettings = a(context, cls);
                    if (iSettings != null) {
                        this.f11791a.put(cls, iSettings);
                    }
                } else {
                    iSettings = iSettings2;
                }
            }
            return (T) iSettings;
        } else if (this.f11792b.containsKey(cls)) {
            this.f11792b.remove(cls);
            return t;
        } else {
            return t;
        }
    }

    private static <T extends ISettings> T a(Context context, Class<T> cls) {
        n storageFactory = SettingsManager.getStorageFactory();
        T t = (T) m.create(cls, storageFactory);
        ClassNotFoundException classNotFoundException = null;
        if (t != null) {
            t.updateSettings(context, null);
            return t;
        }
        try {
            com.bytedance.push.settings.annotation.f fVar = (com.bytedance.push.settings.annotation.f) cls.getAnnotation(com.bytedance.push.settings.annotation.f.class);
            if (fVar != null) {
                String storageKey = fVar.storageKey();
                boolean supportMultiProcess = fVar.supportMultiProcess();
                com.bytedance.push.settings.logger.b.a().a("createSettingsInstance for " + storageKey);
                return (T) a(context, cls, storageFactory.create(context, supportMultiProcess, storageKey, null));
            }
        } catch (ClassNotFoundException e2) {
            ClassNotFoundException classNotFoundException2 = e2;
            classNotFoundException2.printStackTrace();
            classNotFoundException = classNotFoundException2;
        } catch (IllegalAccessException e3) {
            IllegalAccessException illegalAccessException = e3;
            illegalAccessException.printStackTrace();
            classNotFoundException = illegalAccessException;
        } catch (InstantiationException e4) {
            InstantiationException instantiationException = e4;
            instantiationException.printStackTrace();
            classNotFoundException = instantiationException;
        } catch (NoSuchMethodException e5) {
            NoSuchMethodException noSuchMethodException = e5;
            noSuchMethodException.printStackTrace();
            classNotFoundException = noSuchMethodException;
        } catch (InvocationTargetException e6) {
            InvocationTargetException invocationTargetException = e6;
            invocationTargetException.printStackTrace();
            classNotFoundException = invocationTargetException;
        }
        if (classNotFoundException != null) {
            throw new IllegalStateException("get settings instance error，please check warning log", classNotFoundException);
        }
        throw new IllegalStateException("get settings instance error，please check warning log");
    }

    private static <T extends ISettings> ISettings a(Context context, Class<T> cls, com.bytedance.push.settings.storage.i iVar) {
        ISettings iSettings = (ISettings) Class.forName(cls.getName() + "$$SettingImpl", true, cls.getClassLoader()).getConstructor(com.bytedance.push.settings.storage.i.class).newInstance(iVar);
        iSettings.updateSettings(context, null);
        return iSettings;
    }
}
