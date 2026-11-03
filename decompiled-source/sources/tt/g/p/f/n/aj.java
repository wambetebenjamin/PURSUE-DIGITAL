package tt.g.p.f.n;

import android.os.Build;
import com.bytedance.JProtect;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@JProtect
/* loaded from: classes10.dex */
public class aj {

    /* renamed from: a  reason: collision with root package name */
    private static boolean f21153a = false;

    public static synchronized boolean a() {
        Class<?> cls;
        String str;
        synchronized (aj.class) {
            boolean z = f21153a;
            if (z) {
                return z;
            }
            try {
                int i2 = Build.VERSION.SDK_INT;
                boolean z2 = false;
                if (i2 > 28) {
                    if (b() && c()) {
                        z2 = true;
                    }
                    f21153a = z2;
                } else {
                    if (i2 >= 26 || (i2 == 25 && Build.VERSION.PREVIEW_SDK_INT > 0)) {
                        cls = Class.forName("android.app.ActivityManager");
                        str = "IActivityManagerSingleton";
                    } else {
                        cls = Class.forName("android.app.ActivityManagerNative");
                        str = "gDefault";
                    }
                    Object a2 = be.a(cls, null, str);
                    Field a3 = be.a(Class.forName("android.util.Singleton"), "mInstance");
                    a3.set(a2, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Class.forName("android.app.IActivityManager")}, new an(a3.get(a2))));
                    f21153a = true;
                }
            } catch (Throwable unused) {
            }
            return f21153a;
        }
    }

    protected static boolean b() {
        try {
            Field declaredField = Class.forName("android.app.ActivityTaskManager").getDeclaredField("IActivityTaskManagerSingleton");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                Class<?> cls = Class.forName("android.util.Singleton");
                Field declaredField2 = cls.getDeclaredField("mInstance");
                declaredField2.setAccessible(true);
                Method declaredMethod = cls.getDeclaredMethod("get", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(obj, new Object[0]);
                if (invoke != null) {
                    Class<?> cls2 = Class.forName("android.app.IActivityTaskManager");
                    declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls2}, new an(invoke)));
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    protected static boolean c() {
        try {
            Field declaredField = Class.forName("android.app.ActivityManager").getDeclaredField("IActivityManagerSingleton");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                Class<?> cls = Class.forName("android.util.Singleton");
                Field declaredField2 = cls.getDeclaredField("mInstance");
                declaredField2.setAccessible(true);
                Method declaredMethod = cls.getDeclaredMethod("get", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(obj, new Object[0]);
                if (invoke != null) {
                    Class<?> cls2 = Class.forName("android.app.IActivityManager");
                    declaredField2.set(obj, Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls2}, new an(invoke)));
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
