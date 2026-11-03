package com.bytedance.ttgame.channel.osdk;

import android.app.Activity;
import com.bytedance.bdturing.methods.l;
import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.channel.account.SdkUserInfo;
import com.bytedance.ttgame.framework.module.callback.ICallback;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: IOSDKDelegate.kt */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\n\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&J\b\u0010\u0007\u001a\u00020\u0005H&J*\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH\u0016J\b\u0010\u0011\u001a\u00020\tH\u0016¨\u0006\u0012"}, d2 = {"Lcom/bytedance/ttgame/channel/osdk/IOSDKDelegate;", "", "getAppId", "", "isDouyinActive", "", "isDouyinActiveAndCloudGame", "isOSDKLoginChannel", "login", "", "activity", "Landroid/app/Activity;", "sdkUserInfo", "Lcom/bytedance/ttgame/channel/account/SdkUserInfo;", l.VALUE_CALLBACK, "Lcom/bytedance/ttgame/framework/module/callback/ICallback;", "Lcom/bytedance/ttgame/base/GSDKError;", "logout", "account_impl_i18nRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface IOSDKDelegate {

    /* compiled from: IOSDKDelegate.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    /* loaded from: classes3.dex */
    public static final class DefaultImpls {
        public static String getAppId(IOSDKDelegate iOSDKDelegate) {
            return null;
        }

        public static void login(IOSDKDelegate iOSDKDelegate, Activity activity, SdkUserInfo sdkUserInfo, ICallback<GSDKError> callback) {
            Intrinsics.checkNotNullParameter(callback, "callback");
        }

        public static void logout(IOSDKDelegate iOSDKDelegate) {
        }
    }

    String getAppId();

    boolean isDouyinActive();

    boolean isDouyinActiveAndCloudGame();

    boolean isOSDKLoginChannel();

    void login(Activity activity, SdkUserInfo sdkUserInfo, ICallback<GSDKError> iCallback);

    void logout();
}
