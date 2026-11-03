package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.EarlyTraceEvent;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class EarlyTraceEventJni implements EarlyTraceEvent.Natives {
    public static final JniStaticTestMocker<EarlyTraceEvent.Natives> TEST_HOOKS = new JniStaticTestMocker<EarlyTraceEvent.Natives>() { // from class: com.ttnet.org.chromium.base.EarlyTraceEventJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(EarlyTraceEvent.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                EarlyTraceEvent.Natives unused = EarlyTraceEventJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static EarlyTraceEvent.Natives testInstance;

    EarlyTraceEventJni() {
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyBeginEvent(String str, long j2, int i2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyBeginEvent(str, j2, i2, j3);
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyEndEvent(String str, long j2, int i2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyEndEvent(str, j2, i2, j3);
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyToplevelBeginEvent(String str, long j2, int i2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyToplevelBeginEvent(str, j2, i2, j3);
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyToplevelEndEvent(String str, long j2, int i2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyToplevelEndEvent(str, j2, i2, j3);
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyAsyncBeginEvent(String str, long j2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyAsyncBeginEvent(str, j2, j3);
    }

    @Override // com.ttnet.org.chromium.base.EarlyTraceEvent.Natives
    public void recordEarlyAsyncEndEvent(String str, long j2, long j3) {
        GEN_JNI.com_ttnet_org_chromium_base_EarlyTraceEvent_recordEarlyAsyncEndEvent(str, j2, j3);
    }

    public static EarlyTraceEvent.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            EarlyTraceEvent.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.EarlyTraceEvent.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new EarlyTraceEventJni();
    }
}
