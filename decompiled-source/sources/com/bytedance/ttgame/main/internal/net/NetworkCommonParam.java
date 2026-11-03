package com.bytedance.ttgame.main.internal.net;

import com.bytedance.ttgame.core.net.TTNetUtil;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
/* loaded from: classes3.dex */
public class NetworkCommonParam implements INetworkCommonParam {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.net.INetworkCommonParam
    public String getIntlInfo() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.INetworkCommonParam", "com.bytedance.ttgame.main.internal.net.NetworkCommonParam", "getIntlInfo", new String[0], "java.lang.String");
        return TTNetUtil.createIntl_info();
    }
}
