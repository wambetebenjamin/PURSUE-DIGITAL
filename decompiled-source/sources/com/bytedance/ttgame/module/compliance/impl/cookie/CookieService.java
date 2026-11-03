package com.bytedance.ttgame.module.compliance.impl.cookie;

import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.compliance.api.cookie.CookieResult;
import com.bytedance.ttgame.module.compliance.api.cookie.ICookieService;
import com.meituan.robust.Constants;
import gsdk.impl.compliance.cookie.a;
import gsdk.impl.compliance.cookie.f;
/* compiled from: CookieService.kt */
/* loaded from: classes5.dex */
public final class CookieService implements ICookieService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.compliance.api.cookie.ICookieService
    public void queryCookieSwitchStatus(ICallback<CookieResult> iCallback) {
        this.moduleApiMonitor.onApiEnter("compliance:impl:cookie", "com.bytedance.ttgame.module.compliance.api.cookie.ICookieService", "com.bytedance.ttgame.module.compliance.impl.cookie.CookieService", "queryCookieSwitchStatus", new String[]{"com.bytedance.ttgame.framework.module.callback.ICallback"}, Constants.VOID);
        f.c();
        a.f19917a.a(iCallback);
    }

    @Override // com.bytedance.ttgame.module.compliance.api.cookie.ICookieService
    public void modifyCookieSwitchStatus(boolean z, ICallback<CookieResult> iCallback) {
        this.moduleApiMonitor.onApiEnter("compliance:impl:cookie", "com.bytedance.ttgame.module.compliance.api.cookie.ICookieService", "com.bytedance.ttgame.module.compliance.impl.cookie.CookieService", "modifyCookieSwitchStatus", new String[]{"boolean", "com.bytedance.ttgame.framework.module.callback.ICallback"}, Constants.VOID);
        f.a();
        a.f19917a.a(z, iCallback);
    }
}
