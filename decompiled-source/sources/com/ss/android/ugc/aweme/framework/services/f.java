package com.ss.android.ugc.aweme.framework.services;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
/* compiled from: DynamicProxy.java */
/* loaded from: classes9.dex */
public class f {
    public static <T> T a(Class<?> cls) {
        if (cls == null || !cls.isInterface()) {
            return null;
        }
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, new InvocationHandler() { // from class: com.ss.android.ugc.aweme.framework.services.f.1
            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, Method method, Object[] objArr) {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, objArr);
                }
                Class<?> returnType = method.getReturnType();
                if (returnType.isPrimitive()) {
                    return d.a((Class<Object>) returnType);
                }
                return method.getDefaultValue();
            }
        });
    }
}
