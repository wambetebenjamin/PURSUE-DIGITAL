package com.bytedance.ttgame.core.net;

import android.content.Context;
import com.bytedance.ttgame.main.internal.INetService;
import com.bytedance.ttgame.main.internal.net.INetworkClient;
import com.meituan.robust.Constants;
import java.util.Map;
/* loaded from: classes3.dex */
public class Proxy__NetService implements INetService {
    private NetService proxy = new NetService();

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String addCommonParams(String str, boolean z, Map map) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "addCommonParams", new String[]{"java.lang.String", "boolean", "java.util.Map"}, "java.lang.String");
        return this.proxy.addCommonParams(str, z, map);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public Map addCommonParamsWithlevel(String str, boolean z, int i2, Map map) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "addCommonParamsWithlevel", new String[]{"java.lang.String", "boolean", Constants.INT, "java.util.Map"}, "java.util.Map");
        return this.proxy.addCommonParamsWithlevel(str, z, i2, map);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String createNewUUID() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "createNewUUID", new String[0], "java.lang.String");
        return this.proxy.createNewUUID();
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public String getClientUUID() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "getClientUUID", new String[0], "java.lang.String");
        return this.proxy.getClientUUID();
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public int getNetEffectiveConnectionType() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "getNetEffectiveConnectionType", new String[0], Constants.INT);
        return this.proxy.getNetEffectiveConnectionType();
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public boolean isUsingProxy(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "isUsingProxy", new String[]{"android.content.Context"}, "boolean");
        return this.proxy.isUsingProxy(context);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public boolean isVpnOn(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "isVpnOn", new String[]{"android.content.Context"}, "boolean");
        return this.proxy.isVpnOn(context);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public INetworkClient newTTLogNetworkClient(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "newTTLogNetworkClient", new String[]{"android.content.Context"}, "com.bytedance.ttgame.main.internal.net.INetworkClient");
        return this.proxy.newTTLogNetworkClient(context);
    }

    @Override // com.bytedance.ttgame.main.internal.INetService
    public void setClientUUID(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.INetService", "com.bytedance.ttgame.core.net.NetService", "setClientUUID", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setClientUUID(str);
    }

    public INetService getProxy() {
        return this.proxy;
    }
}
