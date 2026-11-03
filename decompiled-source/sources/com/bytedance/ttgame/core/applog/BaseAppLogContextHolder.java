package com.bytedance.ttgame.core.applog;
/* loaded from: classes3.dex */
public class BaseAppLogContextHolder {
    private static BaseAppLogContextHolder instance;
    private ITeaAgent iTeaAgent;

    private BaseAppLogContextHolder() {
    }

    public static BaseAppLogContextHolder getInstance() {
        if (instance == null) {
            synchronized (BaseAppLogContextHolder.class) {
                if (instance == null) {
                    instance = new BaseAppLogContextHolder();
                }
            }
        }
        return instance;
    }

    public void init(ITeaAgent iTeaAgent) {
        this.iTeaAgent = iTeaAgent;
    }

    public ITeaAgent fetchTeaAgent() {
        return this.iTeaAgent;
    }
}
