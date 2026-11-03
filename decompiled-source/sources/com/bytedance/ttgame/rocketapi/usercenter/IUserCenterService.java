package com.bytedance.ttgame.rocketapi.usercenter;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.framework.module.spi.IModuleApi;
import com.bytedance.ttgame.rocketapi.account.IAccountCallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import kotlin.Metadata;
/* compiled from: IUserCenterService.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/rocketapi/usercenter/IUserCenterService;", "Lcom/bytedance/ttgame/framework/module/spi/IModuleApi;", "showUserCenter", "", "activity", "Landroid/app/Activity;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/rocketapi/account/IAccountCallback;", "Lcom/bytedance/ttgame/rocketapi/account/UserInfoResult;", "account_api_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes7.dex */
public interface IUserCenterService extends IModuleApi {
    int showUserCenter(Activity activity, IAccountCallback<UserInfoResult> iAccountCallback);
}
