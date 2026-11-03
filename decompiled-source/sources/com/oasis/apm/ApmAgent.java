package com.oasis.apm;

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
public abstract class ApmAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static ApmAgent f17469a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17470b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17471c = true;
    protected static Context context;

    public ApmAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static ApmAgent createInstance(Context context2) {
        if (f17471c || f17469a == null) {
            context = context2;
            try {
                String string = context2.getString(R.string.apm_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("ApmAgent", "Create the ApmAgent:" + string);
                    f17469a = (ApmAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("ApmAgent", "Create ApmAgent failed.", e2);
            }
            if (f17469a == null) {
                Logger.i("ApmAgent", "Create default ApmAgent.");
                f17469a = new DefaultApmAgent();
                f17470b = true;
            }
            return f17469a;
        }
        throw new AssertionError();
    }

    public static ApmAgent getInstance() {
        if (f17471c || f17469a != null) {
            return f17469a;
        }
        throw new AssertionError();
    }

    @Override // com.oasis.android.ApplicationListener
    public void attachBaseContext(Context context2) {
    }

    public abstract void endSpan(String str, String str2);

    public void finalize() {
        OasisApplication.unregisterListener(this);
        ActivityManager.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17470b;
    }

    public abstract void monitorEvent(String str, String str2, String str3);

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

    public abstract void startSpan(String str, String str2, boolean z);

    public abstract void uploadALogInternal(long j2, long j3, String str);
}
