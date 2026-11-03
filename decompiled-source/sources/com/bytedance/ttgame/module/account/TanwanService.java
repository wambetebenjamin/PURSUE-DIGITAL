package com.bytedance.ttgame.module.account;

import android.app.Activity;
import com.bytedance.ttgame.channel.TanwanDelegate;
import com.bytedance.ttgame.channelapi.IChannelTanwan;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.rocketapi.account.ITanwanService;
import com.meituan.robust.Constants;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: TanwanService.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002J2\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016J2\u0010\u0018\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016JL\u0010\u0019\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u00142\b\u0010\u001b\u001a\u0004\u0018\u00010\u00142\b\u0010\u001c\u001a\u0004\u0018\u00010\u00142\b\u0010\u001d\u001a\u0004\u0018\u00010\u00142\b\u0010\u001e\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001f\u001a\u00020 H\u0016J2\u0010!\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0014\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0016H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/module/account/TanwanService;", "Lcom/bytedance/ttgame/rocketapi/account/ITanwanService;", "()V", "realChannelTanwan", "Lcom/bytedance/ttgame/channelapi/IChannelTanwan;", "getRealChannelTanwan", "()Lcom/bytedance/ttgame/channelapi/IChannelTanwan;", "setRealChannelTanwan", "(Lcom/bytedance/ttgame/channelapi/IChannelTanwan;)V", "tanwanDelegate", "Lcom/bytedance/ttgame/channel/TanwanDelegate;", "getTanwanDelegate", "()Lcom/bytedance/ttgame/channel/TanwanDelegate;", "setTanwanDelegate", "(Lcom/bytedance/ttgame/channel/TanwanDelegate;)V", "eventReport", "", "activity", "Landroid/app/Activity;", "eventName", "", "params", "Ljava/util/HashMap;", "", "eventTrack", "sendGameEventLog", "serverId", "serverName", "roleId", "roleName", "roleLevel", "moneyNum", "", "ttEventReport", "Companion", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TanwanService implements ITanwanService {
    public static final a Companion = new a(null);
    public static final String TAG = "gsdk_tanwan_service";
    public static final String TAG_INIT = "gsdk_tanwan_init";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private IChannelTanwan realChannelTanwan;
    private TanwanDelegate tanwanDelegate;

    public TanwanService() {
        Object newInstance;
        try {
            newInstance = Class.forName("com.bytedance.ttgame.channel.adapter.ChannelAdapterTanwan").getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (newInstance == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.bytedance.ttgame.channelapi.IChannelTanwan");
        }
        this.realChannelTanwan = (IChannelTanwan) newInstance;
        this.tanwanDelegate = new TanwanDelegate(this.realChannelTanwan);
    }

    /* compiled from: TanwanService.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/module/account/TanwanService$Companion;", "", "()V", "TAG", "", "TAG_INIT", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes2.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }
    }

    public final IChannelTanwan getRealChannelTanwan() {
        return this.realChannelTanwan;
    }

    public final void setRealChannelTanwan(IChannelTanwan iChannelTanwan) {
        this.realChannelTanwan = iChannelTanwan;
    }

    public final TanwanDelegate getTanwanDelegate() {
        return this.tanwanDelegate;
    }

    public final void setTanwanDelegate(TanwanDelegate tanwanDelegate) {
        this.tanwanDelegate = tanwanDelegate;
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void sendGameEventLog(String str, String str2, String str3, String str4, String str5, String str6, int i2) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "sendGameEventLog", new String[]{"java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", "java.lang.String", Constants.INT}, Constants.VOID);
        TanwanDelegate tanwanDelegate = this.tanwanDelegate;
        if (tanwanDelegate != null) {
            tanwanDelegate.sendGameEventLog(str, str2, str3, str4, str5, str6, i2);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void eventTrack(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "eventTrack", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        TanwanDelegate tanwanDelegate = this.tanwanDelegate;
        if (tanwanDelegate != null) {
            tanwanDelegate.eventTrack(activity, str, hashMap);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void eventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "eventReport", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        TanwanDelegate tanwanDelegate = this.tanwanDelegate;
        if (tanwanDelegate != null) {
            tanwanDelegate.eventReport(activity, str, hashMap);
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.account.ITanwanService
    public void ttEventReport(Activity activity, String str, HashMap<String, Object> hashMap) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.account.ITanwanService", "com.bytedance.ttgame.module.account.TanwanService", "ttEventReport", new String[]{"android.app.Activity", "java.lang.String", "java.util.HashMap"}, Constants.VOID);
        TanwanDelegate tanwanDelegate = this.tanwanDelegate;
        if (tanwanDelegate != null) {
            tanwanDelegate.ttEventReport(activity, str, hashMap);
        }
    }
}
