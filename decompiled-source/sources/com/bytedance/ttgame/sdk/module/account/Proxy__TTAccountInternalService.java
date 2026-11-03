package com.bytedance.ttgame.sdk.module.account;

import android.content.Context;
import com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo;
import com.meituan.robust.Constants;
/* loaded from: classes2.dex */
public class Proxy__TTAccountInternalService implements ITTAccountInternalApi {
    private TTAccountInternalService proxy = new TTAccountInternalService();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public TTUserInfo getTTUserInfo() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "getTTUserInfo", new String[0], "com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo");
        return this.proxy.getTTUserInfo();
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void initAuthService(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "initAuthService", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.initAuthService(context);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void initSpManager(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "initSpManager", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.initSpManager(context);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void setTTUserInfo(TTUserInfo tTUserInfo) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "setTTUserInfo", new String[]{"com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo"}, Constants.VOID);
        this.proxy.setTTUserInfo(tTUserInfo);
    }

    public ITTAccountInternalApi getProxy() {
        return this.proxy;
    }
}
