package com.bytedance.ttgame.core.net;

import com.bytedance.ttgame.core.SdkConfig;
import com.bytedance.ttgame.framework.module.util.FlavorUtilKt;
import com.bytedance.ttgame.main.internal.net.IRetrofit;
import com.bytedance.ttgame.main.internal.net.IRetrofitService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.sdk.module.core.internal.ChannelConstants;
import com.meituan.robust.Constants;
import gsdk.impl.core.DEFAULT.a;
/* loaded from: classes3.dex */
public class RetrofitService implements IRetrofitService {
    private SdkConfig sdkConfig;
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private String accountBsdkUrl = ChannelConstants.BSDK_SERVER_URL;
    private String accountGsdkUrl = ChannelConstants.GSDK_SERVER_URL;
    private boolean accountMergeHost = true;
    private boolean useAccountPrivatizationHost = false;

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public void initHost(SdkConfig sdkConfig) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "initHost", new String[]{"com.bytedance.ttgame.core.SdkConfig"}, Constants.VOID);
        this.sdkConfig = sdkConfig;
        if (FlavorUtilKt.isCnFlavor()) {
            return;
        }
        boolean z = sdkConfig.account_use_privatization_host;
        this.useAccountPrivatizationHost = z;
        if (z && sdkConfig.serverRegion == 10 && !sdkConfig.mIsBoe) {
            this.accountBsdkUrl = sdkConfig.mIsSandBox ? a.a(a.f20019f) : a.a(a.f20020g);
            this.accountGsdkUrl = sdkConfig.mIsSandBox ? a.a(a.f20019f) : a.a(a.f20020g);
        } else {
            this.useAccountPrivatizationHost = false;
        }
        this.accountBsdkUrl = ChannelConstants.BSDK_SERVER_URL;
        this.accountGsdkUrl = ChannelConstants.GSDK_SERVER_URL;
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createNewRetrofit(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createNewRetrofit", new String[]{"java.lang.String"}, "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return new TTRetrofit(str);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createAccountBsdkRetrofit() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createAccountBsdkRetrofit", new String[0], "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return new TTRetrofit(this.accountBsdkUrl);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public IRetrofit createAccountGsdkRetrofit() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "createAccountGsdkRetrofit", new String[0], "com.bytedance.ttgame.main.internal.net.IRetrofit");
        return new TTRetrofit(this.accountGsdkUrl);
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public String getAccountGsdkUrl() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "getAccountGsdkUrl", new String[0], "java.lang.String");
        return this.accountGsdkUrl;
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public String getAccountBsdkUrl() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "getAccountBsdkUrl", new String[0], "java.lang.String");
        return this.accountBsdkUrl;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x004a, code lost:
        if (r7.accountMergeHost != false) goto L6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x001e, code lost:
        if (r7.accountMergeHost != false) goto L6;
     */
    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.String getHostToPassport() {
        /*
            r7 = this;
            com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor r0 = r7.moduleApiMonitor
            r1 = 0
            java.lang.String[] r5 = new java.lang.String[r1]
            java.lang.String r1 = "core:impl:DEFAULT"
            java.lang.String r2 = "com.bytedance.ttgame.main.internal.net.IRetrofitService"
            java.lang.String r3 = "com.bytedance.ttgame.core.net.RetrofitService"
            java.lang.String r4 = "getHostToPassport"
            java.lang.String r6 = "java.lang.String"
            r0.onApiEnter(r1, r2, r3, r4, r5, r6)
            boolean r0 = com.bytedance.ttgame.framework.module.util.FlavorUtilKt.isCnFlavor()
            java.lang.String r1 = "passport_host_merge"
            java.lang.String r2 = "passport_host"
            if (r0 == 0) goto L21
            boolean r0 = r7.accountMergeHost
            if (r0 == 0) goto L4d
            goto L4e
        L21:
            boolean r0 = com.bytedance.ttgame.framework.module.util.FlavorUtilKt.isI18nFlavor()
            if (r0 == 0) goto L3a
            com.bytedance.ttgame.core.SdkConfig r0 = r7.sdkConfig
            int r0 = r0.serverRegion
            r3 = 20
            if (r0 != r3) goto L3a
            boolean r0 = r7.accountMergeHost
            if (r0 == 0) goto L36
            java.lang.String r0 = "passport_host_merge_va"
            goto L38
        L36:
            java.lang.String r0 = "passport_host_va"
        L38:
            r1 = r0
            goto L4e
        L3a:
            boolean r0 = com.bytedance.ttgame.framework.module.util.FlavorUtilKt.isI18nFlavor()
            if (r0 == 0) goto L4d
            com.bytedance.ttgame.core.SdkConfig r0 = r7.sdkConfig
            int r0 = r0.serverRegion
            r3 = 10
            if (r0 != r3) goto L4d
            boolean r0 = r7.accountMergeHost
            if (r0 == 0) goto L4d
            goto L4e
        L4d:
            r1 = r2
        L4e:
            java.lang.String r0 = gsdk.impl.core.DEFAULT.a.a(r1)
            android.util.Log.d(r2, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.core.net.RetrofitService.getHostToPassport():java.lang.String");
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public void mergeAccountHost(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "mergeAccountHost", new String[]{"boolean"}, Constants.VOID);
        this.accountMergeHost = z;
        if (z) {
            this.accountBsdkUrl = this.accountGsdkUrl;
        }
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public boolean useAccountPrivatizationHost() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "useAccountPrivatizationHost", new String[0], "boolean");
        return this.useAccountPrivatizationHost && FlavorUtilKt.isI18nFlavor();
    }

    @Override // com.bytedance.ttgame.main.internal.net.IRetrofitService
    public boolean needMergeAccountHost() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.net.IRetrofitService", "com.bytedance.ttgame.core.net.RetrofitService", "needMergeAccountHost", new String[0], "boolean");
        return this.accountMergeHost;
    }
}
