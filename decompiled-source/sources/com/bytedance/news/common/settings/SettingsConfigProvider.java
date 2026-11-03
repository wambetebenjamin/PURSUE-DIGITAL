package com.bytedance.news.common.settings;

import com.bytedance.news.common.service.manager.IService;
/* loaded from: classes2.dex */
public interface SettingsConfigProvider extends IService {
    b getConfig();

    d getLazyConfig();
}
