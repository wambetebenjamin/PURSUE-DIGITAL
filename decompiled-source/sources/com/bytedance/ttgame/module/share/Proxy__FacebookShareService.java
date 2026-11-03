package com.bytedance.ttgame.module.share;

import android.content.Intent;
import com.meituan.robust.Constants;
/* loaded from: classes7.dex */
public class Proxy__FacebookShareService implements IFacebookShareService {
    private FacebookShareService proxy = new FacebookShareService();

    @Override // com.bytedance.ttgame.module.share.IFacebookShareService
    public void handleShareResult(int i2, int i3, Intent intent) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("share:impl:facebook", "com.bytedance.ttgame.module.share.IFacebookShareService", "com.bytedance.ttgame.module.share.FacebookShareService", "handleShareResult", new String[]{Constants.INT, Constants.INT, "android.content.Intent"}, Constants.VOID);
        this.proxy.handleShareResult(i2, i3, intent);
    }

    @Override // com.bytedance.ttgame.module.share.IFacebookShareService
    public void initFacebookShareModule() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("share:impl:facebook", "com.bytedance.ttgame.module.share.IFacebookShareService", "com.bytedance.ttgame.module.share.FacebookShareService", "initFacebookShareModule", new String[0], Constants.VOID);
        this.proxy.initFacebookShareModule();
    }

    public IFacebookShareService getProxy() {
        return this.proxy;
    }
}
