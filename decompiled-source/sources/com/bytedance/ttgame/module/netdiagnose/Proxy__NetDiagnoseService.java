package com.bytedance.ttgame.module.netdiagnose;

import android.content.Context;
import com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService;
import com.bytedance.ttgame.module.netdiagnose.api.data.IpAddressResult;
/* loaded from: classes6.dex */
public class Proxy__NetDiagnoseService implements INetDiagnoseService {
    private NetDiagnoseService proxy = new NetDiagnoseService();

    @Override // com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService
    public IpAddressResult getIPAddress(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("main:impl:DEFAULT", "com.bytedance.ttgame.module.netdiagnose.api.INetDiagnoseService", "com.bytedance.ttgame.module.netdiagnose.NetDiagnoseService", "getIPAddress", new String[]{"android.content.Context"}, "com.bytedance.ttgame.module.netdiagnose.api.data.IpAddressResult");
        return this.proxy.getIPAddress(context);
    }

    public INetDiagnoseService getProxy() {
        return this.proxy;
    }
}
