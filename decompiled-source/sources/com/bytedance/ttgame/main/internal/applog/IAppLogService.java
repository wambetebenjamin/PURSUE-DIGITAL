package com.bytedance.ttgame.main.internal.applog;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import java.util.Map;
@InternalApi
/* loaded from: classes3.dex */
public interface IAppLogService extends IModuleApi {
    void putCommonParams(Map<String, String> map, boolean z);

    void updateCustomHeadersEmulator(int i2, String str);
}
