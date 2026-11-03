package com.bytedance.apm.battery.hook;

import android.os.IBinder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/* compiled from: BinderProxyHookHandler.java */
/* loaded from: classes.dex */
public class c implements InvocationHandler {

    /* renamed from: f  reason: collision with root package name */
    private static final String f626f = "BinderProxyHookHandler";

    /* renamed from: a  reason: collision with root package name */
    IBinder f627a;

    /* renamed from: b  reason: collision with root package name */
    IBinder f628b;

    /* renamed from: c  reason: collision with root package name */
    d f629c;

    /* renamed from: d  reason: collision with root package name */
    Class<?> f630d;

    /* renamed from: e  reason: collision with root package name */
    Class<?> f631e;

    public c(IBinder iBinder, d dVar) {
        this.f627a = iBinder;
        this.f629c = dVar;
        try {
            String interfaceName = dVar.getInterfaceName();
            this.f630d = Class.forName(interfaceName + "$Stub");
            this.f631e = Class.forName(interfaceName);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
    }

    public c setHook(IBinder iBinder) {
        this.f628b = iBinder;
        return this;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        return "queryLocalInterface".equals(method.getName()) ? Proxy.newProxyInstance(obj.getClass().getClassLoader(), new Class[]{this.f631e}, new a(this.f627a, this.f630d, this.f629c, this.f628b)) : method.invoke(this.f627a, objArr);
    }
}
