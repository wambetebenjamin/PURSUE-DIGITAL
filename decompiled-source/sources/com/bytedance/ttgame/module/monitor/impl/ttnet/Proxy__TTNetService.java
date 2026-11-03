package com.bytedance.ttgame.module.monitor.impl.ttnet;

import com.bytedance.ttgame.module.monitor.api.ttnet.ITTNetService;
/* loaded from: classes6.dex */
public class Proxy__TTNetService implements ITTNetService {
    private TTNetService proxy = new TTNetService();

    public ITTNetService getProxy() {
        return this.proxy;
    }
}
