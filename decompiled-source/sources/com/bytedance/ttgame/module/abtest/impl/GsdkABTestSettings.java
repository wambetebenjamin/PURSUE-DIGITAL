package com.bytedance.ttgame.module.abtest.impl;

import com.bytedance.news.common.settings.api.annotation.ISettings;
import com.bytedance.news.common.settings.api.annotation.j;
@j(storageKey = "Gsdk_ab")
/* loaded from: classes2.dex */
public interface GsdkABTestSettings extends ISettings {
    String getValue(String str);

    boolean register(String str, String str2, String str3, String str4, boolean z);
}
