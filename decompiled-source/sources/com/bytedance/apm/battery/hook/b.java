package com.bytedance.apm.battery.hook;

import android.os.IBinder;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
/* compiled from: BinderHookHelper.java */
/* loaded from: classes.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, d> f625a = new HashMap();

    public void hookService() {
        if (this.f625a.size() == 0) {
            return;
        }
        Class<?> cls = Class.forName("android.os.ServiceManager");
        Method declaredMethod = cls.getDeclaredMethod("getService", String.class);
        Field declaredField = cls.getDeclaredField("sCache");
        declaredField.setAccessible(true);
        Map map = (Map) declaredField.get(null);
        for (Map.Entry<String, d> entry : this.f625a.entrySet()) {
            String key = entry.getKey();
            c cVar = new c((IBinder) declaredMethod.invoke(null, key), entry.getValue());
            IBinder iBinder = (IBinder) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{IBinder.class}, cVar);
            cVar.setHook(iBinder);
            map.put(key, iBinder);
        }
    }

    public void addHook(String str, d dVar) {
        this.f625a.put(str, dVar);
    }
}
