package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.TraceEvent;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.natives.GEN_JNI;
/* JADX INFO: Access modifiers changed from: package-private */
@MainDex
/* loaded from: classes9.dex */
public class TraceEventJni implements TraceEvent.Natives {
    public static final JniStaticTestMocker<TraceEvent.Natives> TEST_HOOKS = new JniStaticTestMocker<TraceEvent.Natives>() { // from class: com.ttnet.org.chromium.base.TraceEventJni.1
        @Override // com.ttnet.org.chromium.base.JniStaticTestMocker
        public void setInstanceForTesting(TraceEvent.Natives natives) {
            if (GEN_JNI.TESTING_ENABLED) {
                TraceEvent.Natives unused = TraceEventJni.testInstance = natives;
                return;
            }
            throw new RuntimeException("Tried to set a JNI mock when mocks aren't enabled!");
        }
    };
    private static TraceEvent.Natives testInstance;

    TraceEventJni() {
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void registerEnabledObserver() {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_registerEnabledObserver();
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void startATrace(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_startATrace(str);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void stopATrace() {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_stopATrace();
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void setupATraceStartupTrace(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_setupATraceStartupTrace(str);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void instant(String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_instant(str, str2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void begin(String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_begin(str, str2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void end(String str, String str2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_end(str, str2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void beginToplevel(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_beginToplevel(str);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void endToplevel(String str) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_endToplevel(str);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void startAsync(String str, long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_startAsync(str, j2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void finishAsync(String str, long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_finishAsync(str, j2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public boolean viewHierarchyDumpEnabled() {
        return GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_viewHierarchyDumpEnabled();
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void initViewHierarchyDump() {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_initViewHierarchyDump();
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public long startActivityDump(String str, long j2) {
        return GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_startActivityDump(str, j2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void addViewDump(int i2, int i3, boolean z, boolean z2, String str, String str2, long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_addViewDump(i2, i3, z, z2, str, str2, j2);
    }

    @Override // com.ttnet.org.chromium.base.TraceEvent.Natives
    public void instantAndroidIPC(String str, long j2) {
        GEN_JNI.com_ttnet_org_chromium_base_TraceEvent_instantAndroidIPC(str, j2);
    }

    public static TraceEvent.Natives get() {
        if (GEN_JNI.TESTING_ENABLED) {
            TraceEvent.Natives natives = testInstance;
            if (natives != null) {
                return natives;
            }
            if (GEN_JNI.REQUIRE_MOCK) {
                throw new UnsupportedOperationException("No mock found for the native implementation for com.ttnet.org.chromium.base.TraceEvent.Natives. The current configuration requires all native implementations to have a mock instance.");
            }
        }
        NativeLibraryLoadedStatus.checkLoaded(true);
        return new TraceEventJni();
    }
}
