package bitter.jnibridge;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
final class a implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object[] f6a = new Object[0];

    /* renamed from: b  reason: collision with root package name */
    private long f7b;

    /* renamed from: c  reason: collision with root package name */
    private Constructor f8c;

    public a(long j2) {
        this.f7b = j2;
        try {
            Constructor declaredConstructor = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE);
            this.f8c = declaredConstructor;
            declaredConstructor.setAccessible(true);
        } catch (NoClassDefFoundError | NoSuchMethodException unused) {
            this.f8c = null;
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public final Object invoke(Object obj, Method method, Object[] objArr) {
        synchronized (this.f6a) {
            long j2 = this.f7b;
            if (j2 == 0) {
                return null;
            }
            try {
                return JNIBridge.invoke(j2, method.getDeclaringClass(), method, objArr);
            } catch (NoSuchMethodError e2) {
                if (this.f8c == null) {
                    System.err.println("JNIBridge error: Java interface default methods are only supported since Android Oreo");
                    throw e2;
                } else if ((method.getModifiers() & 1024) == 0) {
                    if (objArr == null) {
                        objArr = new Object[0];
                    }
                    Class<?> declaringClass = method.getDeclaringClass();
                    return ((MethodHandles.Lookup) this.f8c.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(obj).invokeWithArguments(objArr);
                } else {
                    throw e2;
                }
            }
        }
    }
}
