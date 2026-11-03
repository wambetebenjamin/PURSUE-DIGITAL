package com.bytedance.ttgame.module.usercenter;

import android.app.Activity;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService;
import com.meituan.robust.Constants;
/* loaded from: classes7.dex */
public class Proxy__UserCenterService implements IUserCenterService {
    private UserCenterService proxy = new UserCenterService();

    @Override // com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService
    public int showUserCenter(Activity activity, IAccountCallback iAccountCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService", "com.bytedance.ttgame.module.usercenter.UserCenterService", "showUserCenter", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.INT);
        return this.proxy.showUserCenter(activity, iAccountCallback);
    }

    public IUserCenterService getProxy() {
        return this.proxy;
    }
}
