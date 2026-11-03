package com.ttnet.org.chromium.base.metrics;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.metrics.StatisticsRecorderAndroid;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class StatisticsRecorderAndroidJni implements StatisticsRecorderAndroid.Natives {
    public static final JniStaticTestMocker<StatisticsRecorderAndroid.Natives> TEST_HOOKS = new JniStaticTestMocker<StatisticsRecorderAndroid.Natives>() { // from class: com.ttnet.org.chromium.base.metrics.StatisticsRecorderAndroidJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(StatisticsRecorderAndroid.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                StatisticsRecorderAndroid.Natives unused = StatisticsRecorderAndroidJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static StatisticsRecorderAndroid.Natives testInstance;

    StatisticsRecorderAndroidJni() {
    }

    @Override // com.ttnet.org.chromium.base.metrics.StatisticsRecorderAndroid.Natives
    public String toJson(int i2) {
        return GEN_JNI.com_ttnet_org_chromium_base_metrics_StatisticsRecorderAndroid_toJson(i2);
    }

    public static StatisticsRecorderAndroid.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            StatisticsRecorderAndroid.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.metrics.StatisticsRecorderAndroid.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new StatisticsRecorderAndroidJni();
    }
}
