package com.bytedance.ttgame.module.appset.api;

import android.content.Context;
import android.os.Bundle;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes4.dex */
public interface IAppSetService extends IModuleApi {
    void init(Context context);

    void putCommonParams(Bundle bundle);
}
