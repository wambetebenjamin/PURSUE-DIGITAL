package com.bytedance.ttgame.module.share;

import com.meituan.robust.Constants;
/* loaded from: classes7.dex */
public class Proxy__TwitterShareService implements ITwitterShareService {
    private TwitterShareService proxy = new TwitterShareService();

    @Override // com.bytedance.ttgame.module.share.ITwitterShareService
    public void initTwitterShareModule() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("share:impl:twitter", "com.bytedance.ttgame.module.share.ITwitterShareService", "com.bytedance.ttgame.module.share.TwitterShareService", "initTwitterShareModule", new String[0], Constants.VOID);
        this.proxy.initTwitterShareModule();
    }

    public ITwitterShareService getProxy() {
        return this.proxy;
    }
}
