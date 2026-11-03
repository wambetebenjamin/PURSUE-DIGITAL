package com.bytedance.ttgame.module.compliance.api.privacy;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.library.api_common.annotations.InternalApi;
/* loaded from: classes4.dex */
public interface IPrivacyService extends IModuleApi {
    boolean isAgreedPrivacyProtection();

    @InternalApi
    boolean isNeedPrivateVerify();

    @InternalApi
    boolean isPrivacyProtectionProcess(Context context);

    void privacyProtection(Activity activity, ICallback<Boolean> iCallback);

    void privacyProtection(Activity activity, String str, ICallback<Boolean> iCallback);

    @InternalApi
    void setOpenPrivacyPanel(boolean z);
}
