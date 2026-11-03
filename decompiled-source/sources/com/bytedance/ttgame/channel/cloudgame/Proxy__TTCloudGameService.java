package com.bytedance.ttgame.channel.cloudgame;

import android.app.Activity;
import com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.meituan.robust.Constants;
/* loaded from: classes3.dex */
public class Proxy__TTCloudGameService implements ITTCloudGameService {
    private TTCloudGameService proxy = new TTCloudGameService();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public void cloudGameLogin(Activity activity, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "cloudGameLogin", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.cloudGameLogin(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public void cloudGameLoginV2(Activity activity, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "cloudGameLoginV2", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        this.proxy.cloudGameLoginV2(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService
    public boolean isCloudRuntime() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTCloudGameService", "com.bytedance.ttgame.channel.cloudgame.TTCloudGameService", "isCloudRuntime", new String[0], "boolean");
        return this.proxy.isCloudRuntime();
    }

    public ITTCloudGameService getProxy() {
        return this.proxy;
    }
}
