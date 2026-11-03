package com.oasis.screenrecord;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityListener;
import com.oasis.android.ActivityManager;
import com.oasis.android.ApplicationListener;
import com.oasis.android.OasisApplication;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public abstract class SimpleProcessVideoAgent implements ApplicationListener, ActivityListener {

    /* renamed from: a  reason: collision with root package name */
    private static SimpleProcessVideoAgent f17619a = null;

    /* renamed from: b  reason: collision with root package name */
    private static boolean f17620b = false;

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17621c = true;

    public SimpleProcessVideoAgent() {
        OasisApplication.registerListener(this);
        ActivityManager.registerListener(this);
    }

    public static SimpleProcessVideoAgent createInstance(Context context) {
        if (f17621c || f17619a == null) {
            try {
                String string = context.getString(R.string.simple_process_video_agent_class);
                if (string.length() > 0) {
                    Class<?> cls = Class.forName(string);
                    Logger.i("SimpleProcessVideoAgent", "Create the SimpleProcessVideoAgent:" + string);
                    f17619a = (SimpleProcessVideoAgent) cls.newInstance();
                }
            } catch (Exception e2) {
                Logger.e("SimpleProcessVideoAgent", "Create ScreenRecord agent failed.", e2);
            }
            if (f17619a == null) {
                Logger.i("SimpleProcessVideoAgent", "Create default SimpleProcessVideoAgent.");
                f17619a = new a();
                f17620b = true;
            }
            return f17619a;
        }
        throw new AssertionError();
    }

    public static SimpleProcessVideoAgent getInstance() {
        if (f17621c || f17619a != null) {
            return f17619a;
        }
        throw new AssertionError();
    }

    public String assetInfoForType(int i2, String str) {
        Logger.d("SimpleProcessVideoAgent", String.format("AssetInfoForType(type: %type, File)", Integer.valueOf(i2), str));
        return null;
    }

    @Override // com.oasis.android.ApplicationListener
    public void attachBaseContext(Context context) {
    }

    public void finalize() {
        ActivityManager.unregisterListener(this);
        OasisApplication.unregisterListener(this);
    }

    public boolean isUseDefault() {
        return f17620b;
    }

    public String lightProcess(String str, IProgressResultListener iProgressResultListener) {
        Log.d("SimpleProcessVideoAgent", "lightProcess() called with: params = [" + str + "], listener = [" + iProgressResultListener + "]");
        return null;
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

    public String simpleAddBGM(String str, IProgressResultListener iProgressResultListener) {
        Log.d("SimpleProcessVideoAgent", "simpleAddBGM() called with: params = [" + str + "], listener = [" + iProgressResultListener + "]");
        return null;
    }

    public String simpleAddSticker(String str, IProgressResultListener iProgressResultListener) {
        Logger.d("SimpleProcessVideoAgent", String.format("SimpleAddSticker(params: %s)", str));
        return null;
    }

    public String simpleClipVideo(String str, IProgressResultListener iProgressResultListener) {
        Logger.d("SimpleProcessVideoAgent", String.format("SimpleClipVideo(params: %s)", str));
        return null;
    }

    public String simpleProcessVideo(String str, IProgressResultListener iProgressResultListener) {
        Logger.d("SimpleProcessVideoAgent", String.format("SimpleProcessVideo(params: %s)", str));
        return null;
    }
}
