package com.bytedance.ttgame.sdk.module.account;

import android.content.Context;
import com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi;
import com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo;
import com.bytedance.ttgame.module.apimonitor.ModuleApiMonitor;
import com.meituan.robust.Constants;
import gsdk.impl.account.toutiao.ct;
import gsdk.impl.account.toutiao.g;
import gsdk.impl.account.toutiao.h;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\n\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\f"}, d2 = {"Lcom/bytedance/ttgame/sdk/module/account/TTAccountInternalService;", "Lcom/bytedance/ttgame/module/account/toutiao/account/api/ITTAccountInternalApi;", "()V", "getTTUserInfo", "Lcom/bytedance/ttgame/module/account/toutiao/account/api/TTUserInfo;", "initAuthService", "", "context", "Landroid/content/Context;", "initSpManager", "setTTUserInfo", "ttUserInfo", "account_impl_toutiao_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes2.dex */
public final class TTAccountInternalService implements ITTAccountInternalApi {
    public final ModuleApiMonitor moduleApiMonitor = new ModuleApiMonitor();

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void setTTUserInfo(TTUserInfo tTUserInfo) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "setTTUserInfo", new String[]{"com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo"}, Constants.VOID);
        h.a().b(tTUserInfo);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public TTUserInfo getTTUserInfo() {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "getTTUserInfo", new String[0], "com.bytedance.ttgame.module.account.toutiao.account.api.TTUserInfo");
        TTUserInfo b2 = h.a().b();
        Intrinsics.checkNotNullExpressionValue(b2, "getInstance().ttUserInfo");
        return b2;
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void initSpManager(Context context) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "initSpManager", new String[]{"android.content.Context"}, Constants.VOID);
        ct.a(context);
    }

    @Override // com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi
    public void initAuthService(Context context) {
        this.moduleApiMonitor.onApiEnter("account:impl:toutiao", "com.bytedance.ttgame.module.account.toutiao.account.api.ITTAccountInternalApi", "com.bytedance.ttgame.sdk.module.account.TTAccountInternalService", "initAuthService", new String[]{"android.content.Context"}, Constants.VOID);
        g.a().a(context);
    }
}
