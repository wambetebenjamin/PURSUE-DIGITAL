package com.bytedance.ttgame.core.net;

import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.main.internal.net.IRetrofit;
import com.bytedance.ttgame.main.internal.net.IRetrofitService;
import com.meituan.robust.Constants;
/* loaded from: classes3.dex */
public class Proxy__RetrofitService implements IRetrofitService {
    private RetrofitService proxy = new RetrofitService();

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createAccountBsdkRetrofit() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createAccountBsdkRetrofit", new String[0], "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return this.proxy.createAccountBsdkRetrofit();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createAccountGsdkRetrofit() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createAccountGsdkRetrofit", new String[0], "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return this.proxy.createAccountGsdkRetrofit();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createNewRetrofit(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createNewRetrofit", new String[]{"java.lang.String"}, "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return this.proxy.createNewRetrofit(str);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public String getAccountBsdkUrl() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "getAccountBsdkUrl", new String[0], "java.lang.String");
        return this.proxy.getAccountBsdkUrl();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public String getAccountGsdkUrl() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "getAccountGsdkUrl", new String[0], "java.lang.String");
        return this.proxy.getAccountGsdkUrl();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public String getHostToPassport() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "getHostToPassport", new String[0], "java.lang.String");
        return this.proxy.getHostToPassport();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public void initHost(SdkConfig sdkConfig) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "initHost", new String[]{"com.bytedance.ttgame.core.SdkConfig"}, Constants.VOID);
        this.proxy.initHost(sdkConfig);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public void mergeAccountHost(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "mergeAccountHost", new String[]{"boolean"}, Constants.VOID);
        this.proxy.mergeAccountHost(z);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public boolean needMergeAccountHost() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "needMergeAccountHost", new String[0], "boolean");
        return this.proxy.needMergeAccountHost();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public boolean useAccountPrivatizationHost() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "useAccountPrivatizationHost", new String[0], "boolean");
        return this.proxy.useAccountPrivatizationHost();
    }

    public IRetrofitService getProxy() {
        return this.proxy;
    }
}
