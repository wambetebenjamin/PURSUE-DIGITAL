package com.ttnet.org.chromium.net;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import com.ttnet.org.chromium.base.ApplicationStatus;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.ThreadUtils;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
@JNINamespace("net::android")
/* loaded from: classes9.dex */
public class AndroidCellularSignalStrength {
    private static final AndroidCellularSignalStrength sInstance = new AndroidCellularSignalStrength();
    private volatile int mSignalLevel = Integer.MIN_VALUE;

    /* loaded from: classes9.dex */
    private class CellStateListener extends PhoneStateListener implements ApplicationStatus.ApplicationStateListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final TelephonyManager mTelephonyManager;

        CellStateListener() {
            ThreadUtils.assertOnBackgroundThread();
            TelephonyManager telephonyManager = (TelephonyManager) ContextUtils.getApplicationContext().getSystemService("phone");
            this.mTelephonyManager = telephonyManager;
            if (telephonyManager.getSimState() != 5) {
                return;
            }
            ApplicationStatus.registerApplicationStateListener(this);
            onApplicationStateChange(ApplicationStatus.getStateForApplication());
        }

        private void register() {
            this.mTelephonyManager.listen(this, 256);
        }

        private void unregister() {
            AndroidCellularSignalStrength.this.mSignalLevel = Integer.MIN_VALUE;
            this.mTelephonyManager.listen(this, 0);
        }

        @Override // android.telephony.PhoneStateListener
        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            if (ApplicationStatus.getStateForApplication() != 1) {
                return;
            }
            try {
                AndroidCellularSignalStrength.this.mSignalLevel = signalStrength.getLevel();
            } catch (SecurityException unused) {
                AndroidCellularSignalStrength.this.mSignalLevel = Integer.MIN_VALUE;
            }
        }

        @Override // com.ttnet.org.chromium.base.ApplicationStatus.ApplicationStateListener
        public void onApplicationStateChange(int i2) {
            if (i2 == 1) {
                register();
            } else if (i2 == 2) {
                unregister();
            }
        }
    }

    private AndroidCellularSignalStrength() {
        if (Build.VERSION.SDK_INT >= 23 && !TTAppSecurityManager.getInstance().isUserPrivacyRestrictionEnabled()) {
            HandlerThread handlerThread = new HandlerThread("AndroidCellularSignalStrength");
            handlerThread.start();
            new Handler(handlerThread.getLooper()).post(new Runnable() { // from class: com.ttnet.org.chromium.net.AndroidCellularSignalStrength.1
                @Override // java.lang.Runnable
                public void run() {
                    new CellStateListener();
                }
            });
        }
    }

    private static int getSignalStrengthLevel() {
        return sInstance.mSignalLevel;
    }
}
