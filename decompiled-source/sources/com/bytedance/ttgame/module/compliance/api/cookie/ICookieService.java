package com.bytedance.ttgame.module.compliance.api.cookie;

import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
/* loaded from: classes4.dex */
public interface ICookieService extends IModuleApi {
    void modifyCookieSwitchStatus(boolean z, ICallback<CookieResult> iCallback);

    void queryCookieSwitchStatus(ICallback<CookieResult> iCallback);
}
