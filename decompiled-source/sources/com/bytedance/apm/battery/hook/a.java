package com.bytedance.apm.battery.hook;

import android.os.IBinder;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/* compiled from: BinderHookHandler.java */
/* loaded from: classes.dex */
public class a implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f621a = "BinderHookHandler";

    /* renamed from: b  reason: collision with root package name */
    private Object f622b;

    /* renamed from: c  reason: collision with root package name */
    private d f623c;

    /* renamed from: d  reason: collision with root package name */
    private IBinder f624d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(IBinder iBinder, Class<?> cls, d dVar, IBinder iBinder2) {
        this.f624d = null;
        try {
            this.f622b = cls.getDeclaredMethod("asInterface", IBinder.class).invoke(null, iBinder);
            this.f623c = dVar;
            this.f624d = iBinder2;
        } catch (Exception unused) {
        }
    }

    public void setHooked(IBinder iBinder) {
        this.f624d = iBinder;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (this.f624d != null && method.getName().equals("asBinder")) {
            return this.f624d;
        }
        this.f623c.invoke(obj, method, objArr);
        return method.invoke(this.f622b, objArr);
    }
}
