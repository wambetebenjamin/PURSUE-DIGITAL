package com.bytedance.ttgame.module.share;

import android.content.Intent;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
@InternalApi
/* loaded from: classes7.dex */
public interface IFacebookShareService extends IModuleApi {
    void handleShareResult(int i2, int i3, Intent intent);

    void initFacebookShareModule();
}
