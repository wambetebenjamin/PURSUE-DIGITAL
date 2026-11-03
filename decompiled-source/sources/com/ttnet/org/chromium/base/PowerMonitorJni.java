package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.PowerMonitor;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class PowerMonitorJni implements PowerMonitor.Natives {
    public static final JniStaticTestMocker<PowerMonitor.Natives> TEST_HOOKS = new JniStaticTestMocker<PowerMonitor.Natives>() { // from class: com.ttnet.org.chromium.base.PowerMonitorJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(PowerMonitor.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                PowerMonitor.Natives unused = PowerMonitorJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static PowerMonitor.Natives testInstance;

    PowerMonitorJni() {
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onBatteryChargingChanged() {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onBatteryChargingChanged();
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onSuspend() {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onSuspend();
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onResume() {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onResume();
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onInstantSuspend() {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onInstantSuspend();
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onInstantResume() {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onInstantResume();
    }

    @Override // com.ttnet.org.chromium.base.PowerMonitor.Natives
    public void onThermalStatusChanged(int i2) {
        GEN_JNI.com_ttnet_org_chromium_base_PowerMonitor_onThermalStatusChanged(i2);
    }

    public static PowerMonitor.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            PowerMonitor.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.PowerMonitor.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new PowerMonitorJni();
    }
}
