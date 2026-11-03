package com.bytedance.ttgame.channel.osdk;

import android.app.Activity;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.SdkUserInfo;
import com.bytedance.ttgame.channel.account.UserSdkData;
import com.bytedance.ttgame.channel.osdk.IOSDKDelegate;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import com.bytedance.ttgame.rocketapi.account.UserInfoResult;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
/* compiled from: OSDKDelegate.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\u0006\u001a\u00020\u0004H\u0016J\u001c\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0007J\u0012\u0010\r\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007¨\u0006\u000e"}, d2 = {"Lcom/bytedance/ttgame/channel/osdk/OSDKDelegate;", "Lcom/bytedance/ttgame/channel/osdk/IOSDKDelegate;", "()V", "isDouyinActive", "", "isDouyinActiveAndCloudGame", "isOSDKLoginChannel", "reportAccountLogin", "", "userSdkData", "Lcom/bytedance/ttgame/channel/account/UserSdkData;", "result", "Lcom/bytedance/ttgame/rocketapi/account/UserInfoResult;", "reportAccountRegister", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class OSDKDelegate implements IOSDKDelegate {
    public static final OSDKDelegate INSTANCE = new OSDKDelegate();

    @JvmStatic
    public static final void reportAccountLogin(UserSdkData userSdkData, UserInfoResult userInfoResult) {
    }

    @JvmStatic
    public static final void reportAccountRegister(UserSdkData userSdkData) {
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public boolean isDouyinActive() {
        return false;
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public boolean isDouyinActiveAndCloudGame() {
        return false;
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public boolean isOSDKLoginChannel() {
        return false;
    }

    private OSDKDelegate() {
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public String getAppId() {
        return IOSDKDelegate.DefaultImpls.getAppId(this);
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public void login(Activity activity, SdkUserInfo sdkUserInfo, ICallback<GSDKError> iCallback) {
        IOSDKDelegate.DefaultImpls.login(this, activity, sdkUserInfo, iCallback);
    }

    @Override // com.bytedance.ttgame.channel.osdk.IOSDKDelegate
    public void logout() {
        IOSDKDelegate.DefaultImpls.logout(this);
    }
}
