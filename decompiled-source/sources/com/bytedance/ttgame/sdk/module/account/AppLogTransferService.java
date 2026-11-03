package com.bytedance.ttgame.sdk.module.account;

import com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import gsdk.impl.account.toutiao.da;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/AppLogTransferService;", "Lcom/bytedance/ttgame/module/account/toutiao/account/api/IAppLogTransfer;", "()V", "sendSwitchLoginBegin", "", "sendSwitchLoginCheckResult", "result", "", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class AppLogTransferService implements IAppLogTransfer {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer
    public void sendSwitchLoginBegin() {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer", "com.bytedance.ttgame.sdk.module.account.AppLogTransferService", "sendSwitchLoginBegin", new String[0], Constants.VOID);
        da.K();
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer
    public void sendSwitchLoginCheckResult(boolean z) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.IAppLogTransfer", "com.bytedance.ttgame.sdk.module.account.AppLogTransferService", "sendSwitchLoginCheckResult", new String[]{"boolean"}, Constants.VOID);
        da.d(z);
    }
}
