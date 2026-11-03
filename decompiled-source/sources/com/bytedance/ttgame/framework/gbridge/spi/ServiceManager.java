package com.bytedance.ttgame.framework.gbridge.spi;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
/* loaded from: classes3.dex */
public class ServiceManager {
    private static final ServiceManager instance = new ServiceManager();

    private ServiceManager() {
    }

    public static ServiceManager get() {
        return instance;
    }

    public <T extends IModuleApi> T getService(Class<T> cls) {
        return (T) ModuleManager.INSTANCE.getPublicService(cls);
    }

    public <T extends IModuleApi> T getService(String str) {
        try {
            return (T) get().getService((Class<IModuleApi>) Class.forName(str));
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
