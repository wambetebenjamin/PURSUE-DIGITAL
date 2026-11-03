package com.bytedance.ttgame.sdk.module.account;

import com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer;
import com.meituan.robust.Constants;
/* loaded from: classes2.dex */
public class Proxy__AppLogTransferService implements IAppLogTransfer {
    private AppLogTransferService proxy = new AppLogTransferService();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer
    public void sendSwitchLoginBegin() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer", "com.bytedance.ttgame.sdk.module.account.AppLogTransferService", "sendSwitchLoginBegin", new String[0], Constants.VOID);
        this.proxy.sendSwitchLoginBegin();
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer
    public void sendSwitchLoginCheckResult(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer", "com.bytedance.ttgame.sdk.module.account.AppLogTransferService", "sendSwitchLoginCheckResult", new String[]{"boolean"}, Constants.VOID);
        this.proxy.sendSwitchLoginCheckResult(z);
    }

    public IAppLogTransfer getProxy() {
        return this.proxy;
    }
}
