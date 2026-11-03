package com.oasis.udp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class UDPAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static UDPAgent f17631a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17632b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17633c = true;

    public UDPAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static UDPAgent createInstance(Context context) {
        if (f17633c || f17631a == null) {
            try {
                String string = context.getString(R.string.udp_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("UDPAgent", "Create the UDPAgent:" + string);
                    f17631a = (UDPAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("UDPAgent", "Create UDPAgent failed.", e2);
            }
            if (f17631a == null) {
                Logger.i("UDPAgent", "Create default UDPAgent.");
                f17631a = new b();
                f17632b = true;
            }
            return f17631a;
        }
        throw new AssertionError();
    }

    public static UDPAgent getInstance() {
        if (f17633c || f17631a != null) {
            return f17631a;
        }
        throw new AssertionError();
    }

    public abstract void EndManually();

    public abstract void StartManually(String str, String str2);

    @Override // com.oasis.android.ApplicationListener
    public void attachBaseContext(Context context) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17632b;
    }

    @Override // com.oasis.android.ActivityListener
    public void onActivityResult(int i2, int i3, Intent intent) {
    }

    @Override // com.oasis.android.ApplicationListener
    public void onApplicationCreate(Application application) {
    }

    @Override // com.oasis.android.ActivityListener
    public void onBackPressed() {
    }

    @Override // com.oasis.android.ActivityListener
    public void onConfigurationChanged(Configuration configuration) {
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

    @Override // com.oasis.android.ApplicationListener
    public void onLowMemory(Application application) {
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

    @Override // com.oasis.android.ApplicationListener
    public void onTerminate(Application application) {
    }

    @Override // com.oasis.android.ApplicationListener
    public void onTrimMemory(Application application, int i2) {
    }

    public abstract void setExtraParams(String str);
}
