package com.bytedance.ttgame.main.internal.net;
/* loaded from: classes3.dex */
public class Proxy__NetworkCommonParam implements INetworkCommonParam {
    private NetworkCommonParam proxy = new NetworkCommonParam();

    @Override // com.bytedance.ttgame.main.internal.net.INetworkCommonParam
    public String getIntlInfo() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.INetworkCommonParam", "com.bytedance.ttgame.main.internal.net.NetworkCommonParam", "getIntlInfo", new String[0], "java.lang.String");
        return this.proxy.getIntlInfo();
    }

    public INetworkCommonParam getProxy() {
        return this.proxy;
    }
}
