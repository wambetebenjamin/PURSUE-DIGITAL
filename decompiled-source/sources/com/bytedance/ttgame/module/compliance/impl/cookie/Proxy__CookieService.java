package com.bytedance.ttgame.module.compliance.impl.cookie;

import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.module.compliance.api.cookie.ICookieService;
import com.meituan.robust.Constants;
/* loaded from: classes5.dex */
public class Proxy__CookieService implements ICookieService {
    private CookieService proxy = new CookieService();

    @Override // com.bytedance.ttgame.module.compliance.api.cookie.ICookieService
    public void modifyCookieSwitchStatus(boolean z, ICallback iCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("compliance:impl:cookie", "com.bytedance.ttgame.module.compliance.api.cookie.ICookieService", "com.bytedance.ttgame.module.compliance.impl.cookie.CookieService", "modifyCookieSwitchStatus", new String[]{"boolean", "com.bytedance.ttgame.framework.module.callback.ICallback"}, Constants.VOID);
        this.proxy.modifyCookieSwitchStatus(z, iCallback);
    }

    @Override // com.bytedance.ttgame.module.compliance.api.cookie.ICookieService
    public void queryCookieSwitchStatus(ICallback iCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("compliance:impl:cookie", "com.bytedance.ttgame.module.compliance.api.cookie.ICookieService", "com.bytedance.ttgame.module.compliance.impl.cookie.CookieService", "queryCookieSwitchStatus", new String[]{"com.bytedance.ttgame.framework.module.callback.ICallback"}, Constants.VOID);
        this.proxy.queryCookieSwitchStatus(iCallback);
    }

    public ICookieService getProxy() {
        return this.proxy;
    }
}
