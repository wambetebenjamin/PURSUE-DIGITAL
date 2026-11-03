package com.facebook.hermes.reactexecutor;

import com.facebook.hermes.instrumentation.HermesSamplingProfiler;
import com.facebook.react.bridge.JavaScriptExecutor;
import com.facebook.react.bridge.JavaScriptExecutorFactory;
/* loaded from: classes8.dex */
public class HermesExecutorFactory implements JavaScriptExecutorFactory {
    private static final String TAG = "Hermes";
    private static HermesExecutor mHermesExecutor;
    private final RuntimeConfig mConfig;

    public String toString() {
        return "JSIExecutor+HermesRuntime";
    }

    public HermesExecutorFactory() {
        this(null);
    }

    public HermesExecutorFactory(RuntimeConfig runtimeConfig) {
        this.mConfig = runtimeConfig;
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public JavaScriptExecutor create() {
        HermesExecutor hermesExecutor = mHermesExecutor;
        if (hermesExecutor != null) {
            mHermesExecutor = null;
            return hermesExecutor;
        }
        return new HermesExecutor(this.mConfig);
    }

    public static void prelodHermesExecutor() {
        if (mHermesExecutor != null) {
            return;
        }
        mHermesExecutor = new HermesExecutor(null);
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void startSamplingProfiler() {
        HermesSamplingProfiler.enable();
    }

    @Override // com.facebook.react.bridge.JavaScriptExecutorFactory
    public void stopSamplingProfiler(String str) {
        HermesSamplingProfiler.dumpSampledTraceToFile(str);
        HermesSamplingProfiler.disable();
    }
}
