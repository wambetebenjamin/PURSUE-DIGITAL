package com.bytedance.ttgame.core.gamesdkconfig;

import android.content.Context;
import com.bytedance.sdk.account.platform.base.i;
import com.bytedance.ttgame.library.module_manager.Component;
import com.bytedance.ttgame.library.module_manager.ImplModule;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IGameSdkConfigService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.module.appsflyer.bridge.AppsFlyerModule;
import com.meituan.robust.Constants;
import gsdk.impl.account.toutiao.f;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import timber.log.Timber;
/* compiled from: GameSdkConfigService.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b?\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\r\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u000e\u001a\u00020\u0004H\u0016J\b\u0010\u000f\u001a\u00020\u0004H\u0016J\n\u0010\u0010\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u0011\u001a\u00020\u0004H\u0016J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J(\u0010\u0014\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016\u0018\u0001`\u0017H\u0016J\n\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\u0019\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u001a\u001a\u00020\u0004H\u0016J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u001c\u001a\u00020\u000bH\u0016J\n\u0010\u001d\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016J\b\u0010\u001f\u001a\u00020\u0007H\u0016J\n\u0010 \u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\n\u0010$\u001a\u0004\u0018\u00010\u0004H\u0016J\b\u0010%\u001a\u00020\u000bH\u0016J\u0012\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010*\u001a\u00020#H\u0016J\b\u0010+\u001a\u00020#H\u0016J\b\u0010,\u001a\u00020#H\u0016J\b\u0010-\u001a\u00020#H\u0016J\b\u0010.\u001a\u00020#H\u0016J\b\u0010/\u001a\u00020'H\u0016J\u0012\u00100\u001a\u00020'2\b\u00101\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u00102\u001a\u00020'2\b\u00103\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u00104\u001a\u00020'2\u0006\u00105\u001a\u00020\u0007H\u0016J\u0012\u00106\u001a\u00020'2\b\u00107\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u00108\u001a\u00020'2\b\u00109\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020\u000bH\u0016J\u0012\u0010<\u001a\u00020'2\b\u0010=\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010>\u001a\u00020'2\b\u0010?\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010@\u001a\u00020'2\u0006\u0010A\u001a\u00020#H\u0016J\u0010\u0010B\u001a\u00020'2\u0006\u0010C\u001a\u00020#H\u0016J\u0010\u0010D\u001a\u00020'2\u0006\u0010-\u001a\u00020#H\u0016J\u0010\u0010E\u001a\u00020'2\u0006\u0010.\u001a\u00020#H\u0016J\u0010\u0010F\u001a\u00020'2\u0006\u0010G\u001a\u00020#H\u0016J\b\u0010H\u001a\u00020'H\u0016J\b\u0010I\u001a\u00020'H\u0016J\u0012\u0010J\u001a\u00020'2\b\u0010K\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010L\u001a\u00020'2\b\u0010M\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010N\u001a\u00020'2\b\u0010O\u001a\u0004\u0018\u00010\u0004H\u0016J0\u0010P\u001a\u00020'2&\u0010Q\u001a\"\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0016\u0018\u0001`\u0017H\u0016J\u0012\u0010R\u001a\u00020'2\b\u0010S\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010T\u001a\u00020'2\b\u0010U\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010V\u001a\u00020'2\b\u0010W\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010X\u001a\u00020'2\u0006\u0010Y\u001a\u00020\u000bH\u0016J\u0012\u0010Z\u001a\u00020'2\b\u0010[\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010\\\u001a\u00020'2\b\u0010]\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010^\u001a\u00020'2\u0006\u0010_\u001a\u00020\u0007H\u0016J\u0012\u0010`\u001a\u00020#2\b\u0010a\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010b\u001a\u00020'2\b\u0010c\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010d\u001a\u00020'2\u0006\u0010e\u001a\u00020#H\u0016J\u0010\u0010f\u001a\u00020'2\u0006\u0010g\u001a\u00020\u000bH\u0016¨\u0006h"}, d2 = {"Lcom/bytedance/ttgame/core/gamesdkconfig/GameSdkConfigService;", "Lcom/bytedance/ttgame/main/internal/IGameSdkConfigService;", "()V", "getAccessToken", "", "getAdid", "getAgeType", "", AppsFlyerModule.GetAppsFlyerUID, "getBoeHeader", "getFirstLoginShowTime", "", "getGMPatchVersion", "getGlobalSdkVersion", "getGoogleGiftId", "getLoginEventSeq", "getLoginId", "getLoginPage", "getLoginType", "getLoginWay", "getPassInfoFromWebview", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getRoleId", "getRoleName", "getSdkVersion", "getServerId", "getStartSendLoginCallTime", "getStoreRegion", "getTtToken", "getUiFlag", "getUniqueId", "getUserCreateTime", "getsIsVerify", "", "getsUniqueIdLast", "getsUserId", "init", "", "context", "Landroid/content/Context;", "isIsCreateVisitor", "isIsExitLogout", "isIsSwitchLogout", "isLogining", "isLogoff", "resetUserInfo", "setAccessToken", i.CT_KEY_ACCESS_TOKEN, "setAdid", "adidId", "setAgeType", "ageType", "setAppsFlyerUID", "appsFlyerID", "setBoeHeader", "boeHeader", "setFirstLoginShowTime", "firstLoginShowTime", "setGMPatchVersion", "gmPathVersion", "setGoogleGiftId", "googleGiftId", "setIsCreateVisitor", "isCreateVisitor", "setIsExitLogout", "isExitLogout", "setIsLogining", "setIsLogoff", "setIsSwitchLogout", "isSwitchLogout", "setLoginEventSeq", "setLoginId", "setLoginPage", "loginPage", "setLoginType", "loginType", "setLoginWay", "loginWay", "setPassInfoFromWebview", "map", "setRoleId", "roleId", "setRoleName", "roleName", "setServerId", "serverId", "setStartSendLoginCallTime", "startSendLoginCallTime", "setStoreRegion", "storeRegion", "setTtToken", "ttToken", "setUiFlag", "uiFlag", "setUniqueId", "uniqueId", "setUserCreateTime", "userCreateTime", "setsIsVerify", "sIsVerify", "setsUserId", f.v, "core_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GameSdkConfigService implements IGameSdkConfigService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void init(Context context) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "init", new String[]{"android.content.Context"}, Constants.VOID);
        GameSdkConfig.init(context);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsSwitchLogout() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsSwitchLogout", new String[0], "boolean");
        return GameSdkConfig.isIsSwitchLogout();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsSwitchLogout(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsSwitchLogout", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setIsSwitchLogout(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsExitLogout() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsExitLogout", new String[0], "boolean");
        return GameSdkConfig.isIsExitLogout();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsExitLogout(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsExitLogout", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setIsExitLogout(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isLogoff() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isLogoff", new String[0], "boolean");
        return GameSdkConfig.isIsLogOff();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsLogoff(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsLogoff", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setIsLogOff(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean setUniqueId(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUniqueId", new String[]{"java.lang.String"}, "boolean");
        return GameSdkConfig.setUniqueId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getUniqueId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUniqueId", new String[0], "java.lang.String");
        return GameSdkConfig.getUniqueId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAccessToken() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAccessToken", new String[0], "java.lang.String");
        return GameSdkConfig.getAccessToken();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAccessToken(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAccessToken", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setAccessToken(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getStoreRegion() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getStoreRegion", new String[0], "java.lang.String");
        return GameSdkConfig.getStoreRegion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setStoreRegion(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setStoreRegion", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setStoreRegion(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getTtToken() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getTtToken", new String[0], "java.lang.String");
        String ttToken = GameSdkConfig.getTtToken();
        Intrinsics.checkNotNullExpressionValue(ttToken, "getTtToken()");
        return ttToken;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setTtToken(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setTtToken", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setTtToken(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAdid(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAdid", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setAdid(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAdid() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAdid", new String[0], "java.lang.String");
        return GameSdkConfig.getAdid();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAppsFlyerUID(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAppsFlyerUID", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setAppsFlyerUID(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getAppsFlyerUID() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", AppsFlyerModule.GetAppsFlyerUID, new String[0], "java.lang.String");
        return GameSdkConfig.getAppsFlyerUID();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getsUniqueIdLast() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsUniqueIdLast", new String[0], "java.lang.String");
        return GameSdkConfig.getsUniqueIdLast();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getsUserId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsUserId", new String[0], "long");
        return GameSdkConfig.getsUserId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setsUserId(long j2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setsUserId", new String[]{"long"}, Constants.VOID);
        GameSdkConfig.setsUserId(j2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginWay() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginWay", new String[0], "java.lang.String");
        return GameSdkConfig.getLoginWay();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginWay(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginWay", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setLoginWay(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getUserCreateTime() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUserCreateTime", new String[0], "java.lang.String");
        return GameSdkConfig.getUserCreateTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setUserCreateTime(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUserCreateTime", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setUserCreateTime(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean getsIsVerify() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getsIsVerify", new String[0], "boolean");
        return GameSdkConfig.getsIsVerify();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setsIsVerify(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setsIsVerify", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setsIsVerify(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public int getAgeType() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getAgeType", new String[0], Constants.INT);
        return GameSdkConfig.getAgeType();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setAgeType(int i2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setAgeType", new String[]{Constants.INT}, Constants.VOID);
        GameSdkConfig.setAgeType(i2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setGMPatchVersion(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setGMPatchVersion", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setGMPatchVersion(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGMPatchVersion() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGMPatchVersion", new String[0], "java.lang.String");
        return GameSdkConfig.getGMPatchVersion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginId", new String[0], "java.lang.String");
        return GameSdkConfig.getLoginId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginId", new String[0], Constants.VOID);
        GameSdkConfig.setLoginId();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginEventSeq() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginEventSeq", new String[0], "java.lang.String");
        String loginEventSeq = GameSdkConfig.getLoginEventSeq();
        Intrinsics.checkNotNullExpressionValue(loginEventSeq, "getLoginEventSeq()");
        return loginEventSeq;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginEventSeq() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginEventSeq", new String[0], Constants.VOID);
        GameSdkConfig.setLoginEventSeq();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public int getUiFlag() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getUiFlag", new String[0], Constants.INT);
        return GameSdkConfig.getUiFlag();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setUiFlag(int i2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setUiFlag", new String[]{Constants.INT}, Constants.VOID);
        GameSdkConfig.setUiFlag(i2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isIsCreateVisitor() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isIsCreateVisitor", new String[0], "boolean");
        return GameSdkConfig.isIsCreateVisitor();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsCreateVisitor(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsCreateVisitor", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setIsCreateVisitor(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginType(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginType", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setLoginType(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginType() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginType", new String[0], "java.lang.String");
        return GameSdkConfig.getLoginType();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getStartSendLoginCallTime() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getStartSendLoginCallTime", new String[0], "long");
        return GameSdkConfig.getStartSendLoginCallTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setStartSendLoginCallTime(long j2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setStartSendLoginCallTime", new String[]{"long"}, Constants.VOID);
        GameSdkConfig.setStartSendLoginCallTime(j2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public long getFirstLoginShowTime() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getFirstLoginShowTime", new String[0], "long");
        return GameSdkConfig.getFirstLoginShowTime();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setFirstLoginShowTime(long j2) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setFirstLoginShowTime", new String[]{"long"}, Constants.VOID);
        GameSdkConfig.setFirstLoginShowTime(j2);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public boolean isLogining() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "isLogining", new String[0], "boolean");
        return GameSdkConfig.isLogining();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setIsLogining(boolean z) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setIsLogining", new String[]{"boolean"}, Constants.VOID);
        GameSdkConfig.setIsLogining(z);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void resetUserInfo() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "resetUserInfo", new String[0], Constants.VOID);
        GameSdkConfig.resetUserInfo();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGoogleGiftId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGoogleGiftId", new String[0], "java.lang.String");
        String googleGiftId = GameSdkConfig.getGoogleGiftId();
        Intrinsics.checkNotNullExpressionValue(googleGiftId, "getGoogleGiftId()");
        return googleGiftId;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setGoogleGiftId(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setGoogleGiftId", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setGoogleGiftId(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getSdkVersion() {
        Map<String, ImplModule> impls;
        ImplModule implModule;
        String version;
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getSdkVersion", new String[0], "java.lang.String");
        String suiteFamily = ModuleManager.INSTANCE.getGSDKSuite().getSuiteFamily();
        if (suiteFamily.length() == 0) {
            Timber.tag("GSDK").w("Fail to fetch sdk version from ModuleManager suite info.", new Object[0]);
            Component component = ModuleManager.INSTANCE.getComponent("core");
            return (component == null || (impls = component.getImpls()) == null || (implModule = impls.get("DEFAULT")) == null || (version = implModule.getVersion()) == null) ? "" : version;
        }
        return suiteFamily;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getGlobalSdkVersion() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getGlobalSdkVersion", new String[0], "java.lang.String");
        return getSdkVersion();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setBoeHeader(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setBoeHeader", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.boeHeader = str;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getBoeHeader() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getBoeHeader", new String[0], "java.lang.String");
        return GameSdkConfig.boeHeader;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getRoleId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getRoleId", new String[0], "java.lang.String");
        return GameSdkConfig.roleId;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getServerId() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getServerId", new String[0], "java.lang.String");
        return GameSdkConfig.serverID;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setRoleId(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setRoleId", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.roleId = str;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setServerId(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setServerId", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.serverID = str;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getRoleName() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getRoleName", new String[0], "java.lang.String");
        return GameSdkConfig.roleName;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setRoleName(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setRoleName", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.roleName = str;
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setPassInfoFromWebview(HashMap<String, Object> hashMap) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setPassInfoFromWebview", new String[]{"java.util.HashMap"}, Constants.VOID);
        GameSdkConfig.setPassInfo(hashMap);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public HashMap<String, Object> getPassInfoFromWebview() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getPassInfoFromWebview", new String[0], "java.util.HashMap");
        return GameSdkConfig.getPassInfo();
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public void setLoginPage(String str) {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "setLoginPage", new String[]{"java.lang.String"}, Constants.VOID);
        GameSdkConfig.setLoginPage(str);
    }

    @Override // com.bytedance.ttgame.main.internal.IGameSdkConfigService
    public String getLoginPage() {
        this.moduleApiMonitor.onApiEnter("core:impl:DEFAULT", "com.bytedance.ttgame.main.internal.IGameSdkConfigService", "com.bytedance.ttgame.core.gamesdkconfig.GameSdkConfigService", "getLoginPage", new String[0], "java.lang.String");
        String loginPage = GameSdkConfig.getLoginPage();
        Intrinsics.checkNotNullExpressionValue(loginPage, "getLoginPage()");
        return loginPage;
    }
}
