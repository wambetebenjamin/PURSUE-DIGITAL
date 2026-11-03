package com.bytedance.ttgame.module.share.api;

import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
import com.bytedance.ttgame.sdk.module.bridge.IResultCallback;
import java.util.HashMap;
@InternalApi
/* loaded from: classes7.dex */
public interface ISystemShareService extends IModuleApi {
    void initSystemShareModule();

    void onBridgeCalled(String str, HashMap hashMap, IResultCallback iResultCallback);
}
