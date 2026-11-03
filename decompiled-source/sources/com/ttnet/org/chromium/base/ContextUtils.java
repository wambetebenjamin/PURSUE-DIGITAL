package com.ttnet.org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.preference.PreferenceManager;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.compat.ApiHelperForM;
import com.ttnet.org.chromium.base.compat.ApiHelperForO;
import com.ttnet.org.chromium.build.BuildConfig;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class ContextUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int RECEIVER_EXPORTED = 2;
    public static final int RECEIVER_NOT_EXPORTED = 4;
    private static final String TAG = "ContextUtils";
    private static Context sApplicationContext;

    static /* synthetic */ SharedPreferences access$000() {
        return fetchAppSharedPreferences();
    }

    /* loaded from: classes9.dex */
    private static class Holder {
        private static SharedPreferences sSharedPreferences = ContextUtils.access$000();

        private Holder() {
        }
    }

    public static Context getApplicationContext() {
        return sApplicationContext;
    }

    public static void initApplicationContext(Context context) {
        initJavaSideApplicationContext(context);
    }

    private static SharedPreferences fetchAppSharedPreferences() {
        StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
        try {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(sApplicationContext);
            if (allowDiskWrites != null) {
                allowDiskWrites.close();
            }
            return defaultSharedPreferences;
        } catch (Throwable th) {
            if (allowDiskWrites != null) {
                try {
                    allowDiskWrites.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public static SharedPreferences getAppSharedPreferences() {
        return Holder.sSharedPreferences;
    }

    public static void initApplicationContextForTests(Context context) {
        initJavaSideApplicationContext(context);
        SharedPreferences unused = Holder.sSharedPreferences = fetchAppSharedPreferences();
    }

    public static void clearApplicationContextForTests() {
        sApplicationContext = null;
        SharedPreferences unused = Holder.sSharedPreferences = null;
    }

    private static void initJavaSideApplicationContext(Context context) {
        if (BuildConfig.ENABLE_ASSERTS && (context instanceof Application)) {
            context = new ContextWrapper(context);
        }
        sApplicationContext = context;
    }

    public static AssetManager getApplicationAssets() {
        Context applicationContext = getApplicationContext();
        while (applicationContext instanceof ContextWrapper) {
            applicationContext = ((ContextWrapper) applicationContext).getBaseContext();
        }
        return applicationContext.getAssets();
    }

    public static boolean isIsolatedProcess() {
        return Process.isIsolated();
    }

    public static boolean isSdkSandboxProcess() {
        if (Build.VERSION.SDK_INT >= 33) {
            return Process.isSdkSandbox();
        }
        return false;
    }

    public static String getProcessName() {
        return ApiCompatibilityUtils.getProcessName();
    }

    public static boolean isProcess64Bit() {
        if (Build.VERSION.SDK_INT >= 23) {
            return ApiHelperForM.isProcess64Bit();
        }
        return Build.SUPPORTED_64_BIT_ABIS.length > 0 && Build.SUPPORTED_64_BIT_ABIS[0].equals(Build.CPU_ABI);
    }

    public static Activity activityFromContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }

    public static Intent registerExportedBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str) {
        return registerBroadcastReceiver(context, broadcastReceiver, intentFilter, str, null, 2);
    }

    public static Intent registerNonExportedBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        return registerBroadcastReceiver(context, broadcastReceiver, intentFilter, null, null, 4);
    }

    public static Intent registerNonExportedBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, Handler handler) {
        return registerBroadcastReceiver(context, broadcastReceiver, intentFilter, null, handler, 4);
    }

    private static Intent registerBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            return ApiHelperForO.registerReceiver(context, broadcastReceiver, intentFilter, str, handler, i2);
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, str, handler);
    }
}
