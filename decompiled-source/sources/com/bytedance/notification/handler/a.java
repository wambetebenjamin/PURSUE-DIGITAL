package com.bytedance.notification.handler;

import android.util.Log;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/* compiled from: NotificationManagerHandler.java */
/* loaded from: classes2.dex */
public class a implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object f9877a;

    public a(Object obj) {
        this.f9877a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Log.d("xuefanggang", "invoke: " + method.getName());
        return method.invoke(this.f9877a, objArr);
    }
}
