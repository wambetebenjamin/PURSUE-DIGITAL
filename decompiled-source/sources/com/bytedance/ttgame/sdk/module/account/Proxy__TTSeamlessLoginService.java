package com.bytedance.ttgame.sdk.module.account;

import android.app.Activity;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo;
import com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService;
import com.meituan.robust.Constants;
/* loaded from: classes2.dex */
public class Proxy__TTSeamlessLoginService implements ITTSeamlessLoginService {
    private TTSeamlessLoginService proxy = new TTSeamlessLoginService();

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void getSeamlessLoginAccounts(Activity activity, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "getSeamlessLoginAccounts", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.getSeamlessLoginAccounts(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public boolean isSeamlessLoginEnabled() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "isSeamlessLoginEnabled", new String[0], "boolean");
        return this.proxy.isSeamlessLoginEnabled();
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void linkSeamlessAccount(Activity activity, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "linkSeamlessAccount", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.linkSeamlessAccount(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void seamlessLogin(SeamlessLoginInfo seamlessLoginInfo, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "seamlessLogin", new String[]{"com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.seamlessLogin(seamlessLoginInfo, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void unlinkSeamlessAccount(Activity activity, SeamlessUnlinkInfo seamlessUnlinkInfo, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "unlinkSeamlessAccount", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.unlinkSeamlessAccount(activity, seamlessUnlinkInfo, iAccountCallback);
    }

    public ITTSeamlessLoginService getProxy() {
        return this.proxy;
    }
}
