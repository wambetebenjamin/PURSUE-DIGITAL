package com.bytedance.ttgame.module.account;

import android.app.Activity;
import android.content.Context;
import com.bytedance.bdturing.methods.l;
import com.bytedance.react.framework.RNConfig;
import com.bytedance.ttgame.channel.ZlongDelegate;
import com.bytedance.ttgame.channelapi.IChannelZlong;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.rocketapi.account.IResultCallback;
import com.bytedance.ttgame.rocketapi.account.IZlongService;
import com.meituan.robust.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: ZlongService.kt */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 G2\u00020\u0001:\u0001GB\u0005¢\u0006\u0002\u0010\u0002Jw\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\u0014\u001a\u0004\u0018\u00010\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u001b\u001a\u00020\u00192\b\u0010\u001c\u001a\u0004\u0018\u00010\u00122\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0012H\u0016J\u0012\u0010!\u001a\u00020\u00122\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0012H\u0016J4\u0010%\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00192\b\u0010'\u001a\u0004\u0018\u00010\u00122\u0018\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020+0*0)H\u0016J\u0012\u0010,\u001a\u00020\u00102\b\u0010-\u001a\u0004\u0018\u00010\u0012H\u0016J1\u0010.\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\u00192\u0010\u00100\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u0012\u0018\u0001012\b\u00102\u001a\u0004\u0018\u000103H\u0016¢\u0006\u0002\u00104J>\u00105\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00192\b\u00107\u001a\u0004\u0018\u00010\u00122\b\u00108\u001a\u0004\u0018\u00010\u00122\u0018\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020+0*0)H\u0016J$\u00109\u001a\u00020\u00102\u001a\u0010(\u001a\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020+0:\u0018\u00010)H\u0016J&\u0010;\u001a\u00020\u00102\b\u0010<\u001a\u0004\u0018\u00010=2\b\u0010>\u001a\u0004\u0018\u00010\u00122\b\u00108\u001a\u0004\u0018\u00010\u0012H\u0016J6\u0010?\u001a\u00020\u00122\b\u0010@\u001a\u0004\u0018\u00010\u00122\b\u00107\u001a\u0004\u0018\u00010\u00122\u0018\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020+0:0)H\u0016J\u001c\u0010A\u001a\u00020\u00102\b\u0010B\u001a\u0004\u0018\u00010\u00122\b\u0010C\u001a\u0004\u0018\u00010\u0012H\u0016J\b\u0010D\u001a\u00020\u0010H\u0016J,\u0010E\u001a\u00020\u00102\b\u0010F\u001a\u0004\u0018\u00010\u00122\u0018\u0010(\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020+0*0)H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006H"}, d2 = {"Lcom/bytedance/ttgame/module/account/ZlongService;", "Lcom/bytedance/ttgame/rocketapi/account/IZlongService;", "()V", "realChannelZlong", "Lcom/bytedance/ttgame/channelapi/IChannelZlong;", "getRealChannelZlong", "()Lcom/bytedance/ttgame/channelapi/IChannelZlong;", "setRealChannelZlong", "(Lcom/bytedance/ttgame/channelapi/IChannelZlong;)V", "zlongDelegate", "Lcom/bytedance/ttgame/channel/ZlongDelegate;", "getZlongDelegate", "()Lcom/bytedance/ttgame/channel/ZlongDelegate;", "setZlongDelegate", "(Lcom/bytedance/ttgame/channel/ZlongDelegate;)V", "gameStarted", "", "strRole", "", "strGameUid", "roleLevel", "serverId", "serverName", "roleName", "balance", "", "partyName", "vipLevel", "pushInfo", "roleCreateTime", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;ILjava/lang/String;Ljava/lang/Long;)V", "getChannelID", "getDeviceId", "app", "Landroid/content/Context;", "getECID", "getNoticeState", "mode", "nodeName", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/rocketapi/account/IResultCallback;", "", "", "initBBS", "bbsparams", "onRequestPermissionsResult", "requestCode", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "openBBS", RNConfig.ORIENTATION_KEY, "type", "customparams", "registerRewardsOutside", "", "rewardsPurchaseOutside", "activity", "Landroid/app/Activity;", "giftid", "sendExtAction", "extraString", "sendGameEventLog", "eventID", "extra", "showUserCenter", "startQRLoginPC", "customParams", "Companion", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class ZlongService implements IZlongService {
    public static final a Companion = new a(null);
    public static final String TAG = "gsdk_zlong_service";
    public static final String TAG_INIT = "gsdk_zlong_init";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private IChannelZlong realChannelZlong;
    private ZlongDelegate zlongDelegate;

    public ZlongService() {
        Object newInstance;
        try {
            newInstance = Class.forName("com.bytedance.ttgame.channel.adapter.ChannelAdapterZlong").getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (newInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.bytedance.ttgame.channelapi.IChannelZlong");
        }
        this.realChannelZlong = (IChannelZlong) newInstance;
        this.zlongDelegate = new ZlongDelegate(this.realChannelZlong);
    }

    /* compiled from: ZlongService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/account/ZlongService$Companion;", "", "()V", "TAG", "", "TAG_INIT", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }
    }

    public final IChannelZlong getRealChannelZlong() {
        return this.realChannelZlong;
    }

    public final void setRealChannelZlong(IChannelZlong iChannelZlong) {
        this.realChannelZlong = iChannelZlong;
    }

    public final ZlongDelegate getZlongDelegate() {
        return this.zlongDelegate;
    }

    public final void setZlongDelegate(ZlongDelegate zlongDelegate) {
        this.zlongDelegate = zlongDelegate;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getDeviceId(Context context) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getDeviceId", new String[]{"android.content.Context"}, "java.lang.String");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        String deviceId = zlongDelegate != null ? zlongDelegate.getDeviceId(context) : null;
        return deviceId == null ? "" : deviceId;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getChannelID() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getChannelID", new String[0], "java.lang.String");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        String channelID = zlongDelegate != null ? zlongDelegate.getChannelID() : null;
        return channelID == null ? "" : channelID;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String getECID() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getECID", new String[0], "java.lang.String");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        String ecid = zlongDelegate != null ? zlongDelegate.getECID() : null;
        return ecid == null ? "" : ecid;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void gameStarted(String str, String str2, String str3, String str4, String str5, String str6, int i2, String str7, int i3, String str8, Long l) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "gameStarted", new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT, "java.lang.String", Constants.INT, "java.lang.String", Constants.LANG_LONG}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.gameStarted(str, str2, str3, str4, str5, str6, i2, str7, i3, str8, l);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void sendGameEventLog(String str, String str2) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "sendGameEventLog", new String[]{"java.lang.String", "java.lang.String"}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.sendGameEventLog(str, str2);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void showUserCenter() {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "showUserCenter", new String[0], Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.showUserCenter();
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public String sendExtAction(String str, String str2, IResultCallback<Map<String, Object>> callback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "sendExtAction", new String[]{"java.lang.String", "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, "java.lang.String");
        Intrinsics.checkNotNullParameter(callback, "callback");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        String sendExtAction = zlongDelegate != null ? zlongDelegate.sendExtAction(str, str2, callback) : null;
        return sendExtAction == null ? "" : sendExtAction;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void startQRLoginPC(String str, IResultCallback<Map<String, Object>> callback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "startQRLoginPC", new String[]{"java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(callback, "callback");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.startQRLoginPC(str, callback);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void getNoticeState(int i2, String str, IResultCallback<Map<String, Object>> callback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "getNoticeState", new String[]{Constants.INT, "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(callback, "callback");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.getNoticeState(i2, str, callback);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void rewardsPurchaseOutside(Activity activity, String str, String str2) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "rewardsPurchaseOutside", new String[]{"android.app.Activity", "java.lang.String", "java.lang.String"}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.rewardsPurchaseOutside(activity, str, str2);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void initBBS(String str) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "initBBS", new String[]{"java.lang.String"}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.initBBS(str);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void openBBS(int i2, String str, String str2, IResultCallback<Map<String, Object>> callback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "openBBS", new String[]{Constants.INT, "java.lang.String", "java.lang.String", "com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        Intrinsics.checkNotNullParameter(callback, "callback");
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.openBBS(i2, str, str2, callback);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void registerRewardsOutside(IResultCallback<Map<String, Object>> iResultCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "registerRewardsOutside", new String[]{"com.bytedance.ttgame.rocketapi.account.IResultCallback"}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.registerRewardsOutside(iResultCallback);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.IZlongService
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.IZlongService", "com.bytedance.ttgame.module.account.ZlongService", "onRequestPermissionsResult", new String[]{Constants.INT, "java.lang.String[]", "int[]"}, Constants.VOID);
        ZlongDelegate zlongDelegate = this.zlongDelegate;
        if (zlongDelegate != null) {
            zlongDelegate.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }
}
