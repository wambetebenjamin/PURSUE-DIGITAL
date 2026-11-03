package com.bytedance.react.framework;

import android.os.Build;
import android.os.Bundle;
import com.facebook.react.BaseReactActivity;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* loaded from: classes2.dex */
public class GReactNativeActivity extends BaseReactActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.BaseReactActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // com.facebook.react.BaseReactActivity
    public void hideBottomUIMenu() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            getWindow().getDecorView().setSystemUiVisibility(8);
        } else if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(3846);
            getWindow().addFlags(DownloadExpSwitchCode.BUGFIX_SIGBUS_24_25);
        }
    }

    @Override // com.facebook.react.BaseReactActivity
    protected String getMainComponentName() {
        return this.mComponentName;
    }
}
