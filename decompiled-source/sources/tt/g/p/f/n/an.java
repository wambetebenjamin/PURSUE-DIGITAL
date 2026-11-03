package tt.g.p.f.n;

import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@JProtect
/* loaded from: classes10.dex */
public class an implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object f21156a;

    public an(Object obj) {
        this.f21156a = obj;
    }

    public static Object a(Intent intent, Object obj) {
        try {
            String action = intent.getAction();
            if (!TextUtils.isEmpty(action) && action.contains(bf.f21187c)) {
                ClassLoader classLoader = obj.getClass().getClassLoader();
                return Proxy.newProxyInstance(classLoader, new Class[]{classLoader.loadClass("android.app.IServiceConnection")}, new bg(obj));
            }
        } catch (Throwable unused) {
        }
        return obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        Intent intent;
        Intent intent2 = null;
        if ("startActivity".equals(method.getName()) || "startActivityForResult".equals(method.getName())) {
            int i2 = 0;
            while (true) {
                if (i2 >= objArr.length) {
                    intent = null;
                    break;
                }
                Object obj2 = objArr[i2];
                if (obj2 instanceof Intent) {
                    intent = (Intent) obj2;
                    break;
                }
                i2++;
            }
            c.a(intent, bl.a());
        }
        if ("bindService".equals(method.getName()) || "bindIsolatedService".equals(method.getName())) {
            int i3 = 4;
            for (int i4 = 0; i4 < objArr.length; i4++) {
                Object obj3 = objArr[i4];
                if (obj3 != null) {
                    if (obj3 instanceof Intent) {
                        intent2 = (Intent) obj3;
                    }
                    if (obj3.getClass().getName().contains("ServiceConnection")) {
                        i3 = i4;
                    }
                }
            }
            Object a2 = a(intent2, objArr[i3]);
            if (a2 != null) {
                objArr[i3] = a2;
            }
        }
        return method.invoke(this.f21156a, objArr);
    }
}
