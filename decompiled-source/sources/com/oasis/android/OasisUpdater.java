package com.oasis.android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class OasisUpdater implements ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static OasisUpdater f17390a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17391b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17392c = true;

    /* JADX INFO: Access modifiers changed from: protected */
    public OasisUpdater() {
        ActivityManager.registerListener(this);
    }

    public static OasisUpdater createInstance(Context context) {
        if (f17392c || f17390a == null) {
            try {
                String string = context.getString(R.string.oasis_updater_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("OasisUpdater", "Create the OasisUpdater:" + string);
                    f17390a = (OasisUpdater) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("OasisUpdater", "Create OasisUpdater failed.", e2);
            }
            if (f17390a == null) {
                Logger.i("OasisUpdater", "Create default OasisUpdater.");
                f17390a = new OasisUpdater();
                f17391b = true;
            }
            return f17390a;
        }
        throw new AssertionError();
    }

    public static OasisUpdater getInstance() {
        if (f17392c || f17390a != null) {
            return f17390a;
        }
        throw new AssertionError();
    }

    public void finalize() {
        ActivityManager.registerListener(this);
    }

    public void installApkFromDownloadId(long j2) {
    }

    public void installApkFromPath(String str) {
    }

    public boolean isUseDefault() {
        return f17391b;
    }

    @Override // com.oasis.android.ActivityListener
    public void onActivityResult(int i2, int i3, Intent intent) {
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

    public void setUpdateListener(OasisUpdaterDownloadListener oasisUpdaterDownloadListener) {
    }

    public void startUpdate(String str) {
    }

    public void stopUpdate() {
    }
}
