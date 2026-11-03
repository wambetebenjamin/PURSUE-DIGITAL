package com.bytedance.ttgame.module.apimonitor;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.boot_manager.BootManager;
import com.bytedance.ttgame.library.boot_manager.BootTaskType;
import com.bytedance.ttgame.library.boot_manager_api.BootTaskMethodExclude;
import java.lang.reflect.Method;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes4.dex */
public final class ApiApmHandler implements IApiCallHandler {
    private volatile boolean tasksFinished = false;

    @Override // com.bytedance.ttgame.module.apimonitor.IApiCallHandler
    public void onApiEnter(String str, Class<IModuleApi> cls, Class<IModuleApi> cls2, String str2, String[] strArr, String str3) {
        Method findMethod = findMethod(cls, str2, strArr);
        if (findMethod == null || this.tasksFinished) {
            return;
        }
        if (cls2.isAnnotationPresent(BootTaskType.class) && !findMethod.isAnnotationPresent(BootTaskMethodExclude.class)) {
            BootManager.getInstance().waitTasks((BootTaskType) cls2.getAnnotation(BootTaskType.class));
        }
        this.tasksFinished = true;
    }

    @Override // com.bytedance.ttgame.module.apimonitor.IApiCallHandler
    public void onApiExit(String str, Class<IModuleApi> cls, Class<IModuleApi> cls2, String str2, String[] strArr, String str3) {
        findMethod(cls, str2, strArr);
    }
}
