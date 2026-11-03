package com.bytedance.router.autowire;

import java.lang.reflect.Type;
/* compiled from: ISerializationService.java */
/* loaded from: classes2.dex */
public interface b {
    String object2Json(Object obj);

    <T> T parseObject(String str, Type type);
}
