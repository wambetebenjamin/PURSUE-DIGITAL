package com.oasis.android;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Process;
import android.view.KeyEvent;
import com.oasis.Logger.Logger;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class ActivityManager {

    /* renamed from: a  reason: collision with root package name */
    private static Activity f17347a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final List<ActivityListener> f17348b = new ArrayList();

    /* renamed from: c  reason: collision with root package name */
    static final /* synthetic */ boolean f17349c = true;

    public static Activity getActivity() {
        if (f17347a != null) {
            Logger.i("ActivityManager", "activity not null!");
            return f17347a;
        }
        Logger.e("ActivityManager", "activity is null!");
        throw new NullPointerException("The activity in ActivityManager is null.");
    }

    public static boolean hasActivity() {
        return f17347a != null;
    }

    public static boolean isMainActivityCreated() {
        return f17347a != null;
    }

    public static void onActivityResult(int i2, int i3, Intent intent) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onActivityResult(i2, i3, intent);
        }
    }

    public static void onBackPressed() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onBackPressed();
        }
    }

    public static void onConfigurationChanged(Configuration configuration) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onConfigurationChanged(configuration);
        }
    }

    public static void onCreate(Activity activity, Bundle bundle) {
        if (!f17349c && f17347a != null) {
            throw new AssertionError();
        }
        f17347a = activity;
        for (ActivityListener activityListener : f17348b) {
            activityListener.onCreate(bundle);
            activityListener.onCreate(activity, bundle);
        }
    }

    public static void onCreateForEnder(Activity activity, Bundle bundle) {
        f17347a = activity;
        for (ActivityListener activityListener : f17348b) {
            activityListener.onCreate(bundle);
        }
    }

    public static void onDestroy() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onDestroy();
        }
    }

    public static boolean onKeyDown(int i2, KeyEvent keyEvent) {
        boolean z = false;
        for (ActivityListener activityListener : f17348b) {
            if (activityListener.onKeyDown(i2, keyEvent)) {
                z = true;
            }
        }
        return z;
    }

    public static void onNewIntent(Intent intent) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onNewIntent(intent);
        }
    }

    public static void onPause() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onPause();
        }
    }

    public static void onRequestPermissionsResult(int i2, String[] strArr, int[] iArr) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onRequestPermissionsResult(i2, strArr, iArr);
        }
    }

    public static void onRestart() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onRestart();
        }
    }

    public static void onRestoreInstanceState(Bundle bundle) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onRestoreInstanceState(bundle);
        }
    }

    public static void onResume() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onResume();
        }
    }

    public static void onSaveInstanceState(Bundle bundle) {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onSaveInstanceState(bundle);
        }
    }

    public static void onStart() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onStart();
        }
    }

    public static void onStop() {
        for (ActivityListener activityListener : f17348b) {
            activityListener.onStop();
        }
    }

    public static void quitApplication() {
        Process.killProcess(Process.myPid());
    }

    public static void registerListener(ActivityListener activityListener) {
        f17348b.add(activityListener);
    }

    public static void unregisterListener(ActivityListener activityListener) {
        f17348b.remove(activityListener);
    }
}
