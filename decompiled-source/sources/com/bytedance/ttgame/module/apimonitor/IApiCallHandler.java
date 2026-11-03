package com.bytedance.ttgame.module.apimonitor;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes4.dex */
interface IApiCallHandler extends Handler {
    default void onApiEnter(String str, Class<IModuleApi> cls, Class<IModuleApi> cls2, String str2, String[] strArr, String str3) {
    }

    default void onApiExit(String str, Class<IModuleApi> cls, Class<IModuleApi> cls2, String str2, String[] strArr, String str3) {
    }
}
