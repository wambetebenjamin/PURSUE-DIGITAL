package com.bytedance.services.apm.api;

import com.bytedance.news.common.service.manager.IService;
/* loaded from: classes3.dex */
public interface IActivityLifeManager extends IService {
    boolean isForeground();

    void register(IActivityLifeObserver iActivityLifeObserver);

    void unregister(IActivityLifeObserver iActivityLifeObserver);
}
