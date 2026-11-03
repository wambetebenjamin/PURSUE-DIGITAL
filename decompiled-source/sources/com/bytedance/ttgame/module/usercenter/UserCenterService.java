package com.bytedance.ttgame.module.usercenter;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.channel.LoginDelegate;
import com.bytedance.ttgame.channelapi.IChannelAccount;
import com.bytedance.ttgame.library.module_manager.ModuleManager;
import com.bytedance.ttgame.main.internal.IGameSdkConfigService;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.IAccountService;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService;
import com.bytedance.ttgame.sdk.module.utils.AntiShakeUtil;
import com.meituan.robust.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
/* compiled from: UserCenterService.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH\u0016R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/bytedance/ttgame/module/usercenter/UserCenterService;", "Lcom/bytedance/ttgame/rocketapi/usercenter/IUserCenterService;", "()V", "realChannelAccount", "Lcom/bytedance/ttgame/channelapi/IChannelAccount;", "showUserCenter", "", "activity", "Landroid/app/Activity;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/rocketapi/account/IAccountCallback;", "Lcom/bytedance/ttgame/rocketapi/account/UserInfoResult;", "Companion", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class UserCenterService implements IUserCenterService {
    public static final a Companion = new a(null);
    public static final String TAG = "gsdk_account_user_center_service";
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();
    private IChannelAccount realChannelAccount;

    public UserCenterService() {
        try {
            Object newInstance = Class.forName("com.bytedance.ttgame.channel.adapter.ChannelAdapterAccount").getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.bytedance.ttgame.channelapi.IChannelAccount");
            }
            this.realChannelAccount = (IChannelAccount) newInstance;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* compiled from: UserCenterService.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/bytedance/ttgame/module/usercenter/UserCenterService$Companion;", "", "()V", "TAG", "", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes7.dex */
    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private a() {
        }
    }

    @Override // com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService
    public int showUserCenter(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:DEFAULT", "com.bytedance.ttgame.rocketapi.usercenter.IUserCenterService", "com.bytedance.ttgame.module.usercenter.UserCenterService", "showUserCenter", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.INT);
        boolean z = false;
        if (AntiShakeUtil.isFastDoubleClick()) {
            return 0;
        }
        IGameSdkConfigService iGameSdkConfigService = (IGameSdkConfigService) ModuleManager.getService$default(ModuleManager.INSTANCE, IGameSdkConfigService.class, false, (String) null, 6, (Object) null);
        if (iGameSdkConfigService != null) {
            iGameSdkConfigService.setUiFlag(0);
        }
        IAccountService iAccountService = (IAccountService) ModuleManager.getService$default(ModuleManager.INSTANCE, IAccountService.class, false, (String) null, 6, (Object) null);
        if (iAccountService != null && !iAccountService.isLogin()) {
            z = true;
        }
        if (z) {
            return -109800;
        }
        return new LoginDelegate(this.realChannelAccount).openUserCenter(activity, iAccountCallback);
    }
}
