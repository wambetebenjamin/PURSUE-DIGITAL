package com.bytedance.ttgame.module.cloud;

import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.module.database.api.RequestCloudData;
/* loaded from: classes4.dex */
interface ICloudRepository {
    void requestCloudInfo(ICallback<RequestCloudData> iCallback);
}
