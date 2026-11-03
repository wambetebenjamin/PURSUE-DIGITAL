package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.lang.reflect.Field;
/* compiled from: com.google.android.gms:play-services-basement@@18.9.0 */
/* loaded from: classes4.dex */
public final class ObjectWrapper<T> extends IObjectWrapper.Stub {
    private final Object zza;

    private ObjectWrapper(Object obj) {
        this.zza = obj;
    }

    public static <T> T unwrap(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper instanceof ObjectWrapper) {
            return (T) ((ObjectWrapper) iObjectWrapper).zza;
        }
        IBinder asBinder = iObjectWrapper.asBinder();
        Field[] declaredFields = asBinder.getClass().getDeclaredFields();
        Field field = null;
        int i2 = 0;
        for (Field field2 : declaredFields) {
            if (!field2.isSynthetic()) {
                i2++;
                field = field2;
            }
        }
        if (i2 == 1) {
            Preconditions.checkNotNull(field);
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    return (T) field.get(asBinder);
                } catch (IllegalAccessException e2) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", e2);
                } catch (NullPointerException e3) {
                    throw new IllegalArgumentException("Binder object is null.", e3);
                }
            }
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        int length = declaredFields.length;
        StringBuilder sb = new StringBuilder(String.valueOf(length).length() + 53);
        sb.append("Unexpected number of IObjectWrapper declared fields: ");
        sb.append(length);
        throw new IllegalArgumentException(sb.toString());
    }

    public static <T> IObjectWrapper wrap(T t) {
        return new ObjectWrapper(t);
    }
}
