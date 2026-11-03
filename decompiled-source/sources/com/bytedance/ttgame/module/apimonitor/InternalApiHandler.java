package com.bytedance.ttgame.module.apimonitor;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.library.api_common.exceptions.DontCallThisException;
import java.lang.reflect.Method;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class InternalApiHandler implements IProxyApiCallHandler {
    @Override // com.bytedance.ttgame.module.apimonitor.IProxyApiCallHandler
    public void onProxyApiEnter(String str, Class<IModuleApi> cls, Class<IModuleApi> cls2, String str2, String[] strArr, String str3) {
        Method findMethod;
        InternalApi internalApi = (InternalApi) cls.getAnnotation(InternalApi.class);
        if (internalApi == null && (findMethod = findMethod(cls, str2, strArr)) != null) {
            internalApi = (InternalApi) findMethod.getAnnotation(InternalApi.class);
        }
        if (internalApi != null) {
            throw new DontCallThisException(internalApi.value() + "\n Service: " + cls.getName() + ", method: " + str2);
        }
    }
}
