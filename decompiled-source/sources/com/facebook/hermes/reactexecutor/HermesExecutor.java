package com.facebook.hermes.reactexecutor;

import com.facebook.jni.HybridData;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.soloader.SoLoader;
/* loaded from: classes8.dex */
public class HermesExecutor extends JavaScriptExecutor {
    private static String mode_;

    public static native boolean canLoadFile(String str);

    private static native HybridData initHybrid(long j2, boolean z, int i2);

    private static native HybridData initHybridDefaultConfig();

    static {
        SoLoader.loadLibrary("hermes");
        try {
            SoLoader.loadLibrary("hermes-executor-release");
            mode_ = "Release";
        } catch (UnsatisfiedLinkError unused) {
            SoLoader.loadLibrary("hermes-executor-release");
            mode_ = "Release";
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public HermesExecutor(@javax.annotation.Nullable com.facebook.hermes.reactexecutor.RuntimeConfig r4) {
        /*
            r3 = this;
            if (r4 != 0) goto L7
            com.facebook.jni.HybridData r4 = initHybridDefaultConfig()
            goto L11
        L7:
            long r0 = r4.heapSizeMB
            boolean r2 = r4.es6Symbol
            int r4 = r4.bytecodeWarmupPercent
            com.facebook.jni.HybridData r4 = initHybrid(r0, r2, r4)
        L11:
            r3.<init>(r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.hermes.reactexecutor.HermesExecutor.<init>(com.facebook.hermes.reactexecutor.RuntimeConfig):void");
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "HermesExecutor" + mode_;
    }
}
