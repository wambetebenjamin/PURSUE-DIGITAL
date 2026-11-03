package com.oasis.ui;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
/* loaded from: classes9.dex */
public class ScreenListener implements ActivityListener {

    /* renamed from: b  reason: collision with root package name */
    private static ScreenListener f17645b;

    /* renamed from: a  reason: collision with root package name */
    private ScreenProxy f17646a;

    public ScreenListener() {
        ActivityManager.registerListener(this);
    }

    public static ScreenListener createInstance() {
        ScreenListener screenListener = f17645b;
        if (screenListener != null) {
            return screenListener;
        }
        ScreenListener screenListener2 = new ScreenListener();
        f17645b = screenListener2;
        return screenListener2;
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
    }

    @Override // com.oasis.android.ActivityListener
    public void onActivityResult(int i2, int i3, Intent intent) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onBackPressed() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onConfigurationChanged(Configuration configuration) {
        ScreenProxy screenProxy = this.f17646a;
        if (screenProxy == null) {
            return;
        }
        screenProxy.change();
    }

    @Override // com.oasis.android.ActivityListener
    public void onCreate(Bundle bundle) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onDestroy() {
    }

    @Override // com.oasis.android.ActivityListener
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.oasis.android.ActivityListener
    public void onNewIntent(Intent intent) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onPause() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onRestart() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onRestoreInstanceState(Bundle bundle) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onResume() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onSaveInstanceState(Bundle bundle) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onStart() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onStop() {
    }

    public void setScreenProxy(ScreenProxy screenProxy) {
        this.f17646a = screenProxy;
    }
}
