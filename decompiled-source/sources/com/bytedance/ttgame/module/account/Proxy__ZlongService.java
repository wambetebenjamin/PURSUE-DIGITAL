package com.bytedance.ttgame.module.account;

import android.app.Activity;
import android.content.Context;
import com.bytedance.ttgame.rocketapi.account.IResultCallback;
import com.bytedance.ttgame.rocketapi.account.IZlongService;
import com.meituan.robust.Constants;
/* loaded from: classes2.dex */
public class Proxy__ZlongService implements IZlongService {
    private ZlongService proxy = new ZlongService();

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "gameStarted", new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "java.lang.String", Constants.INT, "java.lang.String", Constants.LANG_LONG}, Constants.VOID);
        this.proxy.gameStarted(str, str2, str3, str4, str5, str6, i2, str7, i3, str8, l);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getChannelID() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getChannelID", new String[0], "java.lang.String");
        return this.proxy.getChannelID();
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getDeviceId(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getDeviceId", new String[]{"android.content.Context"}, "java.lang.String");
        return this.proxy.getDeviceId(context);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getECID() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getECID", new String[0], "java.lang.String");
        return this.proxy.getECID();
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void getNoticeState(int i2, String str, IResultCallback iResultCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getNoticeState", new String[]{Constants.INT, "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        this.proxy.getNoticeState(i2, str, iResultCallback);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void initBBS(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "initBBS", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.initBBS(str);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "onRequestPermissionsResult", new String[]{Constants.INT, "java.lang.String[]", "int[]"}, Constants.VOID);
        this.proxy.onRequestPermissionsResult(i2, strArr, iArr);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void openBBS(int i2, String str, String str2, IResultCallback iResultCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "openBBS", new String[]{Constants.INT, "java.lang.String", "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        this.proxy.openBBS(i2, str, str2, iResultCallback);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void registerRewardsOutside(IResultCallback iResultCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "registerRewardsOutside", new String[]{"com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        this.proxy.registerRewardsOutside(iResultCallback);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void rewardsPurchaseOutside(Activity activity, String str, String str2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "rewardsPurchaseOutside", new String[]{"android.app.Activity", "java.lang.String", "java.lang.String"}, Constants.VOID);
        this.proxy.rewardsPurchaseOutside(activity, str, str2);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String sendExtAction(String str, String str2, IResultCallback iResultCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "sendExtAction", new String[]{"java.lang.String", "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, "java.lang.String");
        return this.proxy.sendExtAction(str, str2, iResultCallback);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void sendGameEventLog(String str, String str2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "sendGameEventLog", new String[]{"java.lang.String", "java.lang.String"}, Constants.VOID);
        this.proxy.sendGameEventLog(str, str2);
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void showUserCenter() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "showUserCenter", new String[0], Constants.VOID);
        this.proxy.showUserCenter();
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void startQRLoginPC(String str, IResultCallback iResultCallback) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "startQRLoginPC", new String[]{"java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        this.proxy.startQRLoginPC(str, iResultCallback);
    }

    public IZlongService getProxy() {
        return this.proxy;
    }
}
