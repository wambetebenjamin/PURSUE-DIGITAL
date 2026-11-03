package com.bytedance.ttgame.module.account.channel.toutiao;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.account.api.IAccountErrorHandleService;
import com.meituan.robust.Constants;
/* loaded from: classes4.dex */
public class Proxy__AccountErrorHandleService implements IAccountErrorHandleService {
    private AccountErrorHandleService proxy = new AccountErrorHandleService();

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public GSDKError convertError(int i2, String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "convertError", new String[]{Constants.INT, "java.lang.String"}, "com.bytedance.ttgame.base.GSDKError");
        return this.proxy.convertError(i2, str);
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public GSDKError convertOSDKError(int i2, String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "convertOSDKError", new String[]{Constants.INT, "java.lang.String"}, "com.bytedance.ttgame.base.GSDKError");
        return this.proxy.convertOSDKError(i2, str);
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.CancelError getCancelError() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getCancelError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$CancelError");
        return this.proxy.getCancelError();
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.ClientError getClientError() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getClientError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$ClientError");
        return this.proxy.getClientError();
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.CloseError getCloseError() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getCloseError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$CloseError");
        return this.proxy.getCloseError();
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.NetworkError getNetworkError() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getNetworkError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$NetworkError");
        return this.proxy.getNetworkError();
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.SharkError getSharkError() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getSharkError", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$SharkError");
        return this.proxy.getSharkError();
    }

    @Override // com.bytedance.ttgame.module.account.api.IAccountErrorHandleService
    public IAccountErrorHandleService.Success getSuccess() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService", "com.bytedance.ttgame.module.account.channel.toutiao.AccountErrorHandleService", "getSuccess", new String[0], "com.bytedance.ttgame.module.account.api.IAccountErrorHandleService$Success");
        return this.proxy.getSuccess();
    }

    public IAccountErrorHandleService getProxy() {
        return this.proxy;
    }
}
