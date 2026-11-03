package com.ttnet.org.chromium.base.metrics;
/* loaded from: classes9.dex */
public class UmaRecorderHolder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static boolean sNativeInitialized;
    private static CachingUmaRecorder sRecorder = new CachingUmaRecorder();
    private static boolean sSetUpNativeUmaRecorder = true;

    private UmaRecorderHolder() {
    }

    public static UmaRecorder get() {
        return sRecorder;
    }

    public static void setNonNativeDelegate(UmaRecorder umaRecorder) {
        sRecorder.setDelegate(umaRecorder);
    }

    public static void setUpNativeUmaRecorder(boolean z) {
        sSetUpNativeUmaRecorder = z;
    }

    public static void onLibraryLoaded() {
        if (sSetUpNativeUmaRecorder) {
            sNativeInitialized = true;
            sRecorder.setDelegate(new NativeUmaRecorder());
        }
    }

    public static void resetForTesting() {
        if (sNativeInitialized) {
            return;
        }
        sRecorder = new CachingUmaRecorder();
    }
}
