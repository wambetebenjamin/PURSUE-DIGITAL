package com.ttnet.org.chromium.base;

import android.os.PowerManager;
/* loaded from: classes9.dex */
public final class PowerMonitorForQ {
    private PowerMonitorForQ() {
    }

    public static void addThermalStatusListener(PowerManager powerManager) {
        powerManager.addThermalStatusListener(new PowerManager.OnThermalStatusChangedListener() { // from class: com.ttnet.org.chromium.base.PowerMonitorForQ.1
            @Override // android.os.PowerManager.OnThermalStatusChangedListener
            public void onThermalStatusChanged(int i2) {
                PowerMonitorJni.get().onThermalStatusChanged(i2);
            }
        });
    }
}
