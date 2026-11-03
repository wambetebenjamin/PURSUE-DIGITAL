package androidx.core.os;

import android.os.Build;
import android.os.UserHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
public class UserHandleCompat {
    private static Method sGetUserIdMethod;
    private static Constructor<UserHandle> sUserHandleConstructor;

    private UserHandleCompat() {
    }

    public static UserHandle getUserHandleForUid(int i2) {
        if (Build.VERSION.SDK_INT >= 24) {
            return Api24Impl.getUserHandleForUid(i2);
        }
        try {
            return getUserHandleConstructor().newInstance((Integer) getGetUserIdMethod().invoke(null, Integer.valueOf(i2)));
        } catch (IllegalAccessException e2) {
            IllegalAccessError illegalAccessError = new IllegalAccessError();
            illegalAccessError.initCause(e2);
            throw illegalAccessError;
        } catch (InstantiationException e3) {
            InstantiationError instantiationError = new InstantiationError();
            instantiationError.initCause(e3);
            throw instantiationError;
        } catch (NoSuchMethodException e4) {
            NoSuchMethodError noSuchMethodError = new NoSuchMethodError();
            noSuchMethodError.initCause(e4);
            throw noSuchMethodError;
        } catch (InvocationTargetException e5) {
            throw new RuntimeException(e5);
        }
    }

    /* loaded from: classes.dex */
    private static class Api24Impl {
        private Api24Impl() {
        }

        static UserHandle getUserHandleForUid(int i2) {
            return UserHandle.getUserHandleForUid(i2);
        }
    }

    private static Method getGetUserIdMethod() throws NoSuchMethodException {
        if (sGetUserIdMethod == null) {
            Method declaredMethod = UserHandle.class.getDeclaredMethod("getUserId", Integer.TYPE);
            sGetUserIdMethod = declaredMethod;
            declaredMethod.setAccessible(true);
        }
        return sGetUserIdMethod;
    }

    private static Constructor<UserHandle> getUserHandleConstructor() throws NoSuchMethodException {
        if (sUserHandleConstructor == null) {
            Constructor<UserHandle> declaredConstructor = UserHandle.class.getDeclaredConstructor(Integer.TYPE);
            sUserHandleConstructor = declaredConstructor;
            declaredConstructor.setAccessible(true);
        }
        return sUserHandleConstructor;
    }
}
