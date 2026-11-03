package com.bytedance.ttgame.core.gamesdkconfig;

import android.content.Context;
import com.bytedance.ttgame.main.internal.IGameSdkConfigService;
import com.bytedance.ttgame.module.appsflyer.bridge.AppsFlyerModule;
import com.meituan.robust.Constants;
import java.util.HashMap;
/* loaded from: classes3.dex */
public class Proxy__GameSdkConfigService implements IGameSdkConfigService {
    private GameSdkConfigService proxy = new GameSdkConfigService();

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAccessToken() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAccessToken", new String[0], "java.lang.String");
        return this.proxy.getAccessToken();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAdid() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAdid", new String[0], "java.lang.String");
        return this.proxy.getAdid();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public int getAgeType() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAgeType", new String[0], Constants.INT);
        return this.proxy.getAgeType();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAppsFlyerUID() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", AppsFlyerModule.GetAppsFlyerUID, new String[0], "java.lang.String");
        return this.proxy.getAppsFlyerUID();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getBoeHeader() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getBoeHeader", new String[0], "java.lang.String");
        return this.proxy.getBoeHeader();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getFirstLoginShowTime() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getFirstLoginShowTime", new String[0], "long");
        return this.proxy.getFirstLoginShowTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGMPatchVersion() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGMPatchVersion", new String[0], "java.lang.String");
        return this.proxy.getGMPatchVersion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGlobalSdkVersion() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGlobalSdkVersion", new String[0], "java.lang.String");
        return this.proxy.getGlobalSdkVersion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGoogleGiftId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGoogleGiftId", new String[0], "java.lang.String");
        return this.proxy.getGoogleGiftId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginEventSeq() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginEventSeq", new String[0], "java.lang.String");
        return this.proxy.getLoginEventSeq();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginId", new String[0], "java.lang.String");
        return this.proxy.getLoginId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginPage() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginPage", new String[0], "java.lang.String");
        return this.proxy.getLoginPage();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginType() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginType", new String[0], "java.lang.String");
        return this.proxy.getLoginType();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginWay() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginWay", new String[0], "java.lang.String");
        return this.proxy.getLoginWay();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public HashMap getPassInfoFromWebview() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getPassInfoFromWebview", new String[0], "java.util.HashMap");
        return this.proxy.getPassInfoFromWebview();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getRoleId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getRoleId", new String[0], "java.lang.String");
        return this.proxy.getRoleId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getRoleName() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getRoleName", new String[0], "java.lang.String");
        return this.proxy.getRoleName();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getSdkVersion() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getSdkVersion", new String[0], "java.lang.String");
        return this.proxy.getSdkVersion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getServerId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getServerId", new String[0], "java.lang.String");
        return this.proxy.getServerId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getStartSendLoginCallTime() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getStartSendLoginCallTime", new String[0], "long");
        return this.proxy.getStartSendLoginCallTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getStoreRegion() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getStoreRegion", new String[0], "java.lang.String");
        return this.proxy.getStoreRegion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getTtToken() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getTtToken", new String[0], "java.lang.String");
        return this.proxy.getTtToken();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public int getUiFlag() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUiFlag", new String[0], Constants.INT);
        return this.proxy.getUiFlag();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getUniqueId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUniqueId", new String[0], "java.lang.String");
        return this.proxy.getUniqueId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getUserCreateTime() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUserCreateTime", new String[0], "java.lang.String");
        return this.proxy.getUserCreateTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean getsIsVerify() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsIsVerify", new String[0], "boolean");
        return this.proxy.getsIsVerify();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getsUniqueIdLast() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsUniqueIdLast", new String[0], "java.lang.String");
        return this.proxy.getsUniqueIdLast();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getsUserId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsUserId", new String[0], "long");
        return this.proxy.getsUserId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void init(Context context) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "init", new String[]{"android.content.Context"}, Constants.VOID);
        this.proxy.init(context);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsCreateVisitor() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsCreateVisitor", new String[0], "boolean");
        return this.proxy.isIsCreateVisitor();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsExitLogout() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsExitLogout", new String[0], "boolean");
        return this.proxy.isIsExitLogout();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsSwitchLogout() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsSwitchLogout", new String[0], "boolean");
        return this.proxy.isIsSwitchLogout();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isLogining() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isLogining", new String[0], "boolean");
        return this.proxy.isLogining();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isLogoff() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isLogoff", new String[0], "boolean");
        return this.proxy.isLogoff();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void resetUserInfo() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "resetUserInfo", new String[0], Constants.VOID);
        this.proxy.resetUserInfo();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAccessToken(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAccessToken", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setAccessToken(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAdid(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAdid", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setAdid(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAgeType(int i2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAgeType", new String[]{Constants.INT}, Constants.VOID);
        this.proxy.setAgeType(i2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAppsFlyerUID(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAppsFlyerUID", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setAppsFlyerUID(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setBoeHeader(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setBoeHeader", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setBoeHeader(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setFirstLoginShowTime(long j2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setFirstLoginShowTime", new String[]{"long"}, Constants.VOID);
        this.proxy.setFirstLoginShowTime(j2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setGMPatchVersion(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setGMPatchVersion", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setGMPatchVersion(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setGoogleGiftId(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setGoogleGiftId", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setGoogleGiftId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsCreateVisitor(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsCreateVisitor", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setIsCreateVisitor(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsExitLogout(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsExitLogout", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setIsExitLogout(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsLogining(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsLogining", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setIsLogining(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsLogoff(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsLogoff", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setIsLogoff(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsSwitchLogout(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsSwitchLogout", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setIsSwitchLogout(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginEventSeq() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginEventSeq", new String[0], Constants.VOID);
        this.proxy.setLoginEventSeq();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginId() {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginId", new String[0], Constants.VOID);
        this.proxy.setLoginId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginPage(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginPage", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setLoginPage(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginType(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginType", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setLoginType(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginWay(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginWay", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setLoginWay(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setPassInfoFromWebview(HashMap hashMap) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setPassInfoFromWebview", new String[]{"java.util.HashMap"}, Constants.VOID);
        this.proxy.setPassInfoFromWebview(hashMap);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setRoleId(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setRoleId", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setRoleId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setRoleName(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setRoleName", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setRoleName(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setServerId(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setServerId", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setServerId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setStartSendLoginCallTime(long j2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setStartSendLoginCallTime", new String[]{"long"}, Constants.VOID);
        this.proxy.setStartSendLoginCallTime(j2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setStoreRegion(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setStoreRegion", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setStoreRegion(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setTtToken(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setTtToken", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setTtToken(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setUiFlag(int i2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUiFlag", new String[]{Constants.INT}, Constants.VOID);
        this.proxy.setUiFlag(i2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean setUniqueId(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUniqueId", new String[]{"java.lang.String"}, "boolean");
        return this.proxy.setUniqueId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setUserCreateTime(String str) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUserCreateTime", new String[]{"java.lang.String"}, Constants.VOID);
        this.proxy.setUserCreateTime(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setsIsVerify(boolean z) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setsIsVerify", new String[]{"boolean"}, Constants.VOID);
        this.proxy.setsIsVerify(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setsUserId(long j2) {
        this.proxy.moduleApiMonitor.onProxyApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setsUserId", new String[]{"long"}, Constants.VOID);
        this.proxy.setsUserId(j2);
    }

    public IGameSdkConfigService getProxy() {
        return this.proxy;
    }
}
