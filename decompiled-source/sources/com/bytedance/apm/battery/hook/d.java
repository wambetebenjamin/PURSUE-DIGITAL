package com.bytedance.apm.battery.hook;

import java.lang.reflect.Method;
/* compiled from: IHookService.java */
/* loaded from: classes.dex */
public interface d {
    String getInterfaceName();

    void invoke(Object obj, Method method, Object[] objArr);
}
