package com.bytedance.ttgame.module.account;

import android.app.Activity;
import com.bytedance.ttgame.rocketapi.account.ITanwanService;
import com.meituan.robust.Constants;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class Proxy__TanwanService implements ITanwanService {
    private TanwanService proxy = new TanwanService();

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void eventReport(Activity activity, String str, HashMap hashMap) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "eventReport", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        this.proxy.eventReport(activity, str, hashMap);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void eventTrack(Activity activity, String str, HashMap hashMap) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "eventTrack", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        this.proxy.eventTrack(activity, str, hashMap);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "sendGameEventLog", new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT}, Constants.VOID);
        this.proxy.sendGameEventLog(str, str2, str3, str4, str5, str6, i2);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void ttEventReport(Activity activity, String str, HashMap hashMap) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "ttEventReport", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        this.proxy.ttEventReport(activity, str, hashMap);
    }

    public ITanwanService getProxy() {
        return this.proxy;
    }
}
