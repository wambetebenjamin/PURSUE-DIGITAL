package tt.g.p.f.n;

import android.os.IBinder;
import com.bytedance.JProtect;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@JProtect
/* loaded from: classes10.dex */
public class bc implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    private Object f21174a;

    public bc(Object obj) {
        this.f21174a = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        String name = method.getName();
        name.hashCode();
        if (name.equals("onServiceConnected")) {
            try {
                IBinder iBinder = (IBinder) objArr[1];
                String interfaceDescriptor = iBinder.getInterfaceDescriptor();
                if (interfaceDescriptor != null && interfaceDescriptor.equalsIgnoreCase(bf.f21188d)) {
                    objArr[1] = (IBinder) Proxy.newProxyInstance(iBinder.getClass().getClassLoader(), new Class[]{IBinder.class}, new f(iBinder));
                }
            } catch (Throwable unused) {
            }
        }
        return method.invoke(this.f21174a, objArr);
    }
}
