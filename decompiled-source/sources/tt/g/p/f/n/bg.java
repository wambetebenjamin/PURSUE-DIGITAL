package tt.g.p.f.n;

import android.content.ServiceConnection;
import com.bytedance.JProtect;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@JProtect
/* loaded from: classes10.dex */
public class bg implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object f21195a;

    public bg(Object obj) {
        this.f21195a = obj;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x0048 -> B:16:0x0049). Please submit an issue!!! */
    public static Object a(Object obj) {
        Object obj2;
        Method a2;
        try {
            Object obj3 = be.a(obj.getClass(), "mDispatcher").get(obj);
            Object invoke = bi.a(obj3.getClass(), "get", new Class[0]).invoke(obj3, new Object[0]);
            try {
                a2 = bi.a(invoke.getClass(), "getServiceConnection", new Class[0]);
            } catch (Throwable unused) {
            }
            if (a2 != null) {
                obj2 = a2.invoke(invoke, new Object[0]);
            } else {
                Field a3 = be.a(invoke.getClass(), "mConnection");
                if (a3 != null) {
                    obj2 = a3.get(invoke);
                }
                obj2 = null;
            }
            b(obj2);
            return obj2;
        } catch (Throwable unused2) {
            return null;
        }
    }

    public static Object a(Object obj, Object obj2) {
        try {
            Object obj3 = be.a(obj.getClass(), "mDispatcher").get(obj);
            Object invoke = bi.a(obj3.getClass(), "get", new Class[0]).invoke(obj3, new Object[0]);
            be.a(invoke.getClass(), "mConnection").set(invoke, obj2);
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    public static void b(Object obj) {
        for (int i2 = 0; i2 < obj.getClass().getInterfaces().length; i2++) {
            try {
            } catch (Throwable unused) {
                return;
            }
        }
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        name.hashCode();
        if (name.equals("asBinder")) {
            try {
                Object a2 = a(this.f21195a);
                if (a2 != null) {
                    a(this.f21195a, Proxy.newProxyInstance(ServiceConnection.class.getClassLoader(), new Class[]{ServiceConnection.class}, new bc(a2)));
                }
            } catch (Throwable unused) {
            }
        }
        return method.invoke(this.f21195a, objArr);
    }
}
