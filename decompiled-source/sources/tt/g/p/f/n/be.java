package tt.g.p.f.n;

import com.bytedance.JProtect;
import java.lang.reflect.Field;
@JProtect
/* loaded from: classes10.dex */
public class be {
    public static Object a(Class cls, Object obj, String str) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static Field a(Class cls, String str) {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField;
    }
}
