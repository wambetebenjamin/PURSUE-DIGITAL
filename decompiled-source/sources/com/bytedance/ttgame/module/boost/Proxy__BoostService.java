package com.bytedance.ttgame.module.boost;

import android.content.Context;
import com.bytedance.ttgame.module.boost.api.IBoostService;
import com.meituan.robust.Constants;
/* loaded from: classes4.dex */
public class Proxy__BoostService implements IBoostService {
    private BoostService proxy = new BoostService();

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void disableClassVerify(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "disableClassVerify", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.disableClassVerify(context);
    }

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void enableClassVerify(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "enableClassVerify", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.enableClassVerify(context);
    }

    @Override // com.bytedance.ttgame.module.boost.api.IBoostService
    public void shrinkVM(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("quality:impl:boost", "com.bytedance.ttgame.module.boost.api.IBoostService", "com.bytedance.ttgame.module.boost.BoostService", "shrinkVM", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.shrinkVM(context);
    }

    public IBoostService getProxy() {
        return this.proxy;
    }
}
