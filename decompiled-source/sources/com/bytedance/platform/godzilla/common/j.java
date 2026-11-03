package com.bytedance.platform.godzilla.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
/* compiled from: IReflectHackHelper.java */
/* loaded from: classes2.dex */
public interface j {
    Field getField(Class<?> cls, String str);

    Method getMethod(Class<?> cls, String str, Class... clsArr);
}
