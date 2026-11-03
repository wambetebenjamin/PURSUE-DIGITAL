package com.bytedance.ttgame.module.boost.api;

import android.content.Context;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes4.dex */
public interface IBoostService extends IModuleApi {
    void disableClassVerify(Context context);

    void enableClassVerify(Context context);

    void shrinkVM(Context context);
}
