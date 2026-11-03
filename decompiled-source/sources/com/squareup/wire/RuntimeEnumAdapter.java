package com.squareup.wire;

import com.squareup.wire.WireEnum;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes9.dex */
final class RuntimeEnumAdapter<E extends WireEnum> extends EnumAdapter<E> {
    private Method fromValueMethod;
    private final Class<E> type;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RuntimeEnumAdapter(Class<E> cls) {
        super(cls);
        this.type = cls;
    }

    private Method getFromValueMethod() {
        Method method = this.fromValueMethod;
        if (method != null) {
            return method;
        }
        try {
            Method method2 = this.type.getMethod("fromValue", Integer.TYPE);
            this.fromValueMethod = method2;
            return method2;
        } catch (NoSuchMethodException e2) {
            throw new AssertionError(e2);
        }
    }

    @Override // com.squareup.wire.EnumAdapter
    protected E fromValue(int i2) {
        try {
            return (E) getFromValueMethod().invoke(null, Integer.valueOf(i2));
        } catch (IllegalAccessException | InvocationTargetException e2) {
            throw new AssertionError(e2);
        }
    }

    public boolean equals(Object obj) {
        return (obj instanceof RuntimeEnumAdapter) && ((RuntimeEnumAdapter) obj).type == this.type;
    }

    public int hashCode() {
        return this.type.hashCode();
    }
}
