package tt.g.p.f.n;

import com.bytedance.JProtect;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@JProtect
/* loaded from: classes10.dex */
public class bi {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<Class<?>, Class<?>> f21196a;

    static {
        HashMap hashMap = new HashMap();
        f21196a = hashMap;
        Class cls = Boolean.TYPE;
        hashMap.put(Boolean.class, cls);
        hashMap.put(Byte.class, Byte.TYPE);
        hashMap.put(Character.class, Character.TYPE);
        hashMap.put(Short.class, Short.TYPE);
        Class cls2 = Integer.TYPE;
        hashMap.put(Integer.class, cls2);
        Class cls3 = Float.TYPE;
        hashMap.put(Float.class, cls3);
        Class cls4 = Long.TYPE;
        hashMap.put(Long.class, cls4);
        hashMap.put(Double.class, Double.TYPE);
        hashMap.put(cls, cls);
        Class cls5 = Byte.TYPE;
        hashMap.put(cls5, cls5);
        Class cls6 = Character.TYPE;
        hashMap.put(cls6, cls6);
        Class cls7 = Short.TYPE;
        hashMap.put(cls7, cls7);
        hashMap.put(cls2, cls2);
        hashMap.put(cls3, cls3);
        hashMap.put(cls4, cls4);
        Class cls8 = Double.TYPE;
        hashMap.put(cls8, cls8);
    }

    public static Method a(Class<?> cls, String str, Class... clsArr) {
        Method a2 = a(cls.getDeclaredMethods(), str, clsArr);
        if (a2 != null) {
            a2.setAccessible(true);
            return a2;
        } else if (cls.getSuperclass() != null) {
            return a(cls.getSuperclass(), str, clsArr);
        } else {
            throw new NoSuchMethodException();
        }
    }

    private static Method a(Method[] methodArr, String str, Class<?>[] clsArr) {
        Objects.requireNonNull(str, "Method name must not be null.");
        for (Method method : methodArr) {
            if (method.getName().equals(str) && a(method.getParameterTypes(), clsArr)) {
                return method;
            }
        }
        return null;
    }

    private static boolean a(Class<?>[] clsArr, Class<?>[] clsArr2) {
        if (clsArr == null) {
            return clsArr2 == null || clsArr2.length == 0;
        } else if (clsArr2 == null) {
            return clsArr.length == 0;
        } else if (clsArr.length != clsArr2.length) {
            return false;
        } else {
            for (int i2 = 0; i2 < clsArr.length; i2++) {
                if (!clsArr[i2].isAssignableFrom(clsArr2[i2])) {
                    Map<Class<?>, Class<?>> map = f21196a;
                    if (!map.containsKey(clsArr[i2]) || !map.get(clsArr[i2]).equals(map.get(clsArr2[i2]))) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
