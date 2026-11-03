package com.ttnet.org.chromium.base;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import com.bytedance.apm.battery.config.c;
import com.ttnet.org.chromium.base.TTLifeCycleMonitor;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.compat.ApiHelperForQ;
import com.ttnet.org.chromium.build.BuildConfig;
@JNINamespace("base::android")
/* loaded from: classes9.dex */
public class PowerMonitor implements TTLifeCycleMonitor.AppStateListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static String CRONET_ACTION_BACK = null;
    private static String CRONET_ACTION_FORE = null;
    private static final String TAG = "PowerMonitor";
    private static final long TIME_TO_ENTER_SAME_STATE = 10000;
    private static String WSCHANNEL_ACTION_BACK;
    private static String WSCHANNEL_ACTION_FORE;
    private static TTLifeCycleMonitor mLifeCycleMonitor = new TTLifeCycleMonitor();
    private static boolean mListenAppStateIndependently;
    private static PowerMonitor sInstance;
    private static String sPackageName;
    private static long sReceiveBackAction;
    private static long sReceiveForeAction;
    private static AppStateReceiver sReceiver;
    private boolean mIsBatteryPower;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void onBatteryChargingChanged();

        void onInstantResume();

        void onInstantSuspend();

        void onResume();

        void onSuspend();

        void onThermalStatusChanged(int i2);
    }

    public static void setListenAppStateIndependently(boolean z) {
        mListenAppStateIndependently = z;
    }

    public static void mockAppResumeForTesting() {
        if (BuildConfig.DCHECK_IS_ON) {
            PowerMonitorJni.get().onResume();
        }
    }

    public static void mockAppSuspendForTesting() {
        if (BuildConfig.DCHECK_IS_ON) {
            PowerMonitorJni.get().onSuspend();
        }
    }

    @Override // com.ttnet.org.chromium.base.TTLifeCycleMonitor.AppStateListener
    public void onEnterToForeground() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (TTProcessUtils.isMainProcess(applicationContext)) {
            Intent intent = new Intent();
            intent.setAction(CRONET_ACTION_FORE);
            if (!TextUtils.isEmpty(sPackageName)) {
                intent.setPackage(sPackageName);
            }
            try {
                applicationContext.sendBroadcast(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        PowerMonitorJni.get().onResume();
    }

    @Override // com.ttnet.org.chromium.base.TTLifeCycleMonitor.AppStateListener
    public void onEnterToBackground() {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (TTProcessUtils.isMainProcess(applicationContext)) {
            Intent intent = new Intent();
            intent.setAction(CRONET_ACTION_BACK);
            if (!TextUtils.isEmpty(sPackageName)) {
                intent.setPackage(sPackageName);
            }
            try {
                applicationContext.sendBroadcast(intent);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        PowerMonitorJni.get().onSuspend();
    }

    @Override // com.ttnet.org.chromium.base.TTLifeCycleMonitor.AppStateListener
    public void onInstantEnterToForeground() {
        PowerMonitorJni.get().onInstantResume();
    }

    @Override // com.ttnet.org.chromium.base.TTLifeCycleMonitor.AppStateListener
    public void onInstantEnterToBackground() {
        PowerMonitorJni.get().onInstantSuspend();
    }

    /* loaded from: classes9.dex */
    public static class AppStateReceiver extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            long currentTimeMillis = System.currentTimeMillis();
            String action = intent.getAction();
            if (action.equals(PowerMonitor.CRONET_ACTION_FORE) || action.equals(PowerMonitor.WSCHANNEL_ACTION_FORE)) {
                long j2 = currentTimeMillis - PowerMonitor.sReceiveForeAction;
                long unused = PowerMonitor.sReceiveForeAction = currentTimeMillis;
                if (j2 > PowerMonitor.TIME_TO_ENTER_SAME_STATE) {
                    PowerMonitorJni.get().onResume();
                }
            } else if (action.equals(PowerMonitor.CRONET_ACTION_BACK) || action.equals(PowerMonitor.WSCHANNEL_ACTION_BACK)) {
                long j3 = currentTimeMillis - PowerMonitor.sReceiveBackAction;
                long unused2 = PowerMonitor.sReceiveBackAction = currentTimeMillis;
                if (j3 > PowerMonitor.TIME_TO_ENTER_SAME_STATE) {
                    PowerMonitorJni.get().onSuspend();
                }
            }
        }
    }

    public static void createForTests() {
        sInstance = new PowerMonitor();
    }

    public static void create() {
        if (sInstance != null) {
            return;
        }
        Context applicationContext = ContextUtils.getApplicationContext();
        sInstance = new PowerMonitor();
        Intent registerNonExportedBroadcastReceiver = ContextUtils.registerNonExportedBroadcastReceiver(applicationContext, null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerNonExportedBroadcastReceiver != null) {
            onBatteryChargingChanged(registerNonExportedBroadcastReceiver.getIntExtra("plugged", 0) == 0);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        ContextUtils.registerNonExportedBroadcastReceiver(applicationContext, new BroadcastReceiver() { // from class: com.ttnet.org.chromium.base.PowerMonitor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                PowerMonitor.onBatteryChargingChanged(intent.getAction().equals("android.intent.action.ACTION_POWER_DISCONNECTED"));
            }
        }, intentFilter);
        sPackageName = applicationContext.getPackageName();
        CRONET_ACTION_BACK = sPackageName + ".cronet.APP_BACKGROUND";
        CRONET_ACTION_FORE = sPackageName + ".cronet.APP_FOREGROUND";
        WSCHANNEL_ACTION_BACK = sPackageName + ".wschannel.APP_BACKGROUND";
        WSCHANNEL_ACTION_FORE = sPackageName + ".wschannel.APP_FOREGROUND";
        if (TTProcessUtils.isMainProcess(applicationContext) || mListenAppStateIndependently) {
            if (applicationContext instanceof Application) {
                mLifeCycleMonitor.setAppStateChangedListener(sInstance);
                ((Application) applicationContext).registerActivityLifecycleCallbacks(mLifeCycleMonitor);
                return;
            }
            return;
        }
        sReceiver = new AppStateReceiver();
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction(CRONET_ACTION_BACK);
        intentFilter2.addAction(CRONET_ACTION_FORE);
        intentFilter2.addAction(WSCHANNEL_ACTION_BACK);
        intentFilter2.addAction(WSCHANNEL_ACTION_FORE);
        ContextUtils.registerNonExportedBroadcastReceiver(applicationContext, sReceiver, intentFilter2);
    }

    private PowerMonitor() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onBatteryChargingChanged(boolean z) {
        sInstance.mIsBatteryPower = z;
        PowerMonitorJni.get().onBatteryChargingChanged();
    }

    private static boolean isBatteryPower() {
        if (sInstance == null) {
            create();
        }
        return sInstance.mIsBatteryPower;
    }

    private static int getRemainingBatteryCapacity() {
        if (Build.VERSION.SDK_INT < 21) {
            return 0;
        }
        if (sInstance == null) {
            create();
        }
        return getRemainingBatteryCapacityImpl();
    }

    private static int getRemainingBatteryCapacityImpl() {
        return ((BatteryManager) ContextUtils.getApplicationContext().getSystemService("batterymanager")).getIntProperty(1);
    }

    private static int getCurrentThermalStatus() {
        if (Build.VERSION.SDK_INT < 29) {
            return -1;
        }
        if (sInstance == null) {
            create();
        }
        PowerManager powerManager = (PowerManager) ContextUtils.getApplicationContext().getSystemService(c.BATTERY_POWER_LOCK);
        if (powerManager == null) {
            return -1;
        }
        return ApiHelperForQ.getCurrentThermalStatus(powerManager);
    }
}
