package com.bytedance.ttgame.sdk.module.account;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginResult;
import com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo;
import com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService;
import com.bytedance.ttgame.sdk.module.account.api.TTUserInfoResult;
import com.bytedance.ttgame.sdk.module.account.platform.api.AccountConstants;
import com.meituan.robust.Constants;
import gsdk.impl.account.toutiao.bb;
import kotlin.Metadata;
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\"\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\bH\u0016J\"\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\bH\u0016J,\u0010\u0012\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\bH\u0016¨\u0006\u0015"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/TTSeamlessLoginService;", "Lcom/bytedance/ttgame/sdk/module/account/api/ITTSeamlessLoginService;", "()V", "getSeamlessLoginAccounts", "", "activity", "Landroid/app/Activity;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/rocketapi/account/IAccountCallback;", "Lcom/bytedance/ttgame/rocketapi/seamless/pojo/SeamlessLoginResult;", "isSeamlessLoginEnabled", "", "linkSeamlessAccount", "Lcom/bytedance/ttgame/base/GSDKError;", "seamlessLogin", "loginInfo", "Lcom/bytedance/ttgame/rocketapi/seamless/pojo/SeamlessLoginInfo;", "Lcom/bytedance/ttgame/sdk/module/account/api/TTUserInfoResult;", "unlinkSeamlessAccount", "unlinkInfo", "Lcom/bytedance/ttgame/rocketapi/seamless/pojo/SeamlessUnlinkInfo;", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TTSeamlessLoginService implements ITTSeamlessLoginService {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public boolean isSeamlessLoginEnabled() {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "isSeamlessLoginEnabled", new String[0], "boolean");
        return AccountConstants.AuthConfig.isSeamlessLoginEnabled;
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void getSeamlessLoginAccounts(Activity activity, IAccountCallback<SeamlessLoginResult> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "getSeamlessLoginAccounts", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        bb.f19363a.a(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void seamlessLogin(SeamlessLoginInfo seamlessLoginInfo, IAccountCallback<TTUserInfoResult> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "seamlessLogin", new String[]{"com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessLoginInfo", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        bb.f19363a.a(seamlessLoginInfo, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void linkSeamlessAccount(Activity activity, IAccountCallback<GSDKError> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "linkSeamlessAccount", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        bb.f19363a.b(activity, iAccountCallback);
    }

    @Override // com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService
    public void unlinkSeamlessAccount(Activity activity, SeamlessUnlinkInfo seamlessUnlinkInfo, IAccountCallback<GSDKError> iAccountCallback) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.sdk.module.account.api.ITTSeamlessLoginService", "com.bytedance.ttgame.sdk.module.account.TTSeamlessLoginService", "unlinkSeamlessAccount", new String[]{"android.app.Activity", "com.bytedance.ttgame.rocketapi.seamless.pojo.SeamlessUnlinkInfo", "com.bytedance.ttgame.rocketapi.account.IAccountCallback"}, Constants.VOID);
        bb.f19363a.a(activity, seamlessUnlinkInfo, iAccountCallback);
    }
}
