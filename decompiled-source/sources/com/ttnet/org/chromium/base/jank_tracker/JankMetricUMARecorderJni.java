package com.ttnet.org.chromium.base.jank_tracker;

import com.ttnet.org.chromium.base.JniStaticTestMocker;
import com.ttnet.org.chromium.base.NativeLibraryLoadedStatus;
import com.ttnet.org.chromium.base.jank_tracker.JankMetricUMARecorder;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* loaded from: classes9.dex */
public class JankMetricUMARecorderJni implements JankMetricUMARecorder.Natives {
    public static final JniStaticTestMocker<JankMetricUMARecorder.Natives> TEST_HOOKS = new JniStaticTestMocker<JankMetricUMARecorder.Natives>() { // from class: com.ttnet.org.chromium.base.jank_tracker.JankMetricUMARecorderJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(JankMetricUMARecorder.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                JankMetricUMARecorder.Natives unused = JankMetricUMARecorderJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static JankMetricUMARecorder.Natives testInstance;

    @Override // com.ttnet.org.chromium.base.jank_tracker.JankMetricUMARecorder.Natives
    public void recordJankMetrics(String str, long[] jArr, long[] jArr2, long[] jArr3, int i2) {
        GEN_JNI.com_ttnet_org_chromium_base_jank_1tracker_JankMetricUMARecorder_recordJankMetrics(str, jArr, jArr2, jArr3, i2);
    }

    public static JankMetricUMARecorder.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            JankMetricUMARecorder.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.jank_tracker.JankMetricUMARecorder.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(false);
        return new JankMetricUMARecorderJni();
    }
}
