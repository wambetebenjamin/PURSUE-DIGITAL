package com.bytedance.ttgame.module.account;

import com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer;
import com.meituan.robust.Constants;
/* loaded from: classes2.dex */
public class Proxy__AccountAppLogTransfer implements IAccountAppLogTransfer {
    private AccountAppLogTransfer proxy = new AccountAppLogTransfer();

    @Override // com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer
    public void catchSQLiteException(String str, Exception exc) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.module.account.api.IAccountAppLogTransfer", "com.bytedance.ttgame.module.account.AccountAppLogTransfer", "catchSQLiteException", new String[]{"java.lang.String", "java.lang.Exception"}, Constants.VOID);
        this.proxy.catchSQLiteException(str, exc);
    }

    public IAccountAppLogTransfer getProxy() {
        return this.proxy;
    }
}
