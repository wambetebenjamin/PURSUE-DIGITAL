package com.bytedance.ttgame.module.compliance.api.realname;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import kotlin.Metadata;
/* compiled from: IRealNameService.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&J\"\u0010\t\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H&¨\u0006\u000b"}, d2 = {"Lcom/bytedance/ttgame/module/compliance/api/realname/IRealNameService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "hasVerified", "", "activity", "Landroid/app/Activity;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/module/compliance/api/realname/IRealNameCallback;", "Lcom/bytedance/ttgame/module/compliance/api/realname/RealVerifyInfo;", "verifyWithUI", "Lcom/bytedance/ttgame/base/GSDKError;", "compliance_api_realname_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes4.dex */
public interface IRealNameService extends IModuleApi {
    void hasVerified(Activity activity, IRealNameCallback<RealVerifyInfo> iRealNameCallback);

    void verifyWithUI(Activity activity, IRealNameCallback<GSDKError> iRealNameCallback);
}
