package com.bytedance.push.proxy;

import com.bytedance.push.utils.h;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
/* compiled from: BaseHooker.java */
/* loaded from: classes2.dex */
public abstract class b implements InvocationHandler {

    /* renamed from: b  reason: collision with root package name */
    protected Object f11686b;

    /* renamed from: c  reason: collision with root package name */
    protected boolean f11687c;

    /* renamed from: a  reason: collision with root package name */
    protected String f11685a = c();

    /* renamed from: d  reason: collision with root package name */
    private final Set<c> f11688d = new HashSet();

    protected abstract boolean b();

    protected abstract String c();

    public boolean a(c cVar) {
        if (cVar == null) {
            return false;
        }
        if (!this.f11687c) {
            h.d(this.f11685a, "not hook success,star hook");
            this.f11687c = b();
        } else {
            h.d(this.f11685a, "has hook success,needn't hook");
        }
        if (this.f11687c) {
            this.f11688d.add(cVar);
        }
        return this.f11687c;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Object[] array;
        synchronized (this.f11688d) {
            array = this.f11688d.toArray();
        }
        for (Object obj2 : array) {
            d a2 = ((c) obj2).a(this.f11686b, method, objArr);
            if (a2 != null && a2.f11689a) {
                return a2.f11690b;
            }
        }
        Object obj3 = this.f11686b;
        if (obj3 != null) {
            return method.invoke(obj3, objArr);
        }
        return null;
    }
}
