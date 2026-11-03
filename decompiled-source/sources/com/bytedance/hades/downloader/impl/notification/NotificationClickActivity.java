package com.bytedance.hades.downloader.impl.notification;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import com.bytedance.hades.downloader.impl.DownloaderImpl;
import com.bytedance.hades.downloader.impl.settings.DownloadSettingKeys;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NotificationClickActivity extends Activity {

    /* renamed from: a  reason: collision with root package name */
    public static ComponentName f9407a;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        Intent launchIntentForPackage;
        super.onCreate(bundle);
        if (!DownloaderImpl.b()) {
            Log.w("NotificationClickActivity", "onCreate, downloader not initialized, return");
            finish();
            return;
        }
        if (f9407a != null) {
            launchIntentForPackage = new Intent().setComponent(f9407a);
        } else {
            launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(getPackageName());
        }
        if (launchIntentForPackage != null) {
            String className = launchIntentForPackage.getComponent().getClassName();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("name", className);
                DownloaderImpl.a().onEvent(DownloadSettingKeys.NOTIFICATION_CLICK, jSONObject.toString());
            } catch (Throwable th) {
                th.printStackTrace();
            }
            try {
                startActivity(launchIntentForPackage);
            } catch (Throwable th2) {
                th2.printStackTrace();
            }
        }
        finish();
    }
}
