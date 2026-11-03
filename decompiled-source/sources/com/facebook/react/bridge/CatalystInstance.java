package com.facebook.react.bridge;

import android.os.Bundle;
import com.facebook.react.bridge.queue.ReactQueueConfiguration;
import com.facebook.react.turbomodule.core.interfaces.CallInvokerHolder;
import java.util.Collection;
import java.util.List;
/* loaded from: classes8.dex */
public interface CatalystInstance extends MemoryPressureListener, JSInstance, JSBundleLoaderDelegate {
    void addBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void addJSIModules(List<JSIModuleSpec> list);

    void callFunction(String str, String str2, NativeArray nativeArray);

    void clearRegistry(boolean z);

    void destroy();

    void extendNativeModules(NativeModuleRegistry nativeModuleRegistry);

    Bundle getBundleInfo();

    CallInvokerHolder getJSCallInvokerHolder();

    JSIModule getJSIModule(JSIModuleType jSIModuleType);

    <T extends JavaScriptModule> T getJSModule(Class<T> cls);

    JavaScriptContextHolder getJavaScriptContextHolder();

    CallInvokerHolder getNativeCallInvokerHolder();

    <T extends NativeModule> T getNativeModule(Class<T> cls);

    NativeModule getNativeModule(String str);

    Collection<NativeModule> getNativeModules();

    ReactQueueConfiguration getReactQueueConfiguration();

    String getSourceURL();

    <T extends NativeModule> boolean hasNativeModule(Class<T> cls);

    boolean hasRunJSBundle();

    void initialize();

    @Override // com.facebook.react.bridge.JSInstance
    void invokeCallback(int i2, NativeArrayInterface nativeArrayInterface);

    boolean isDestroyed();

    void registerSegment(int i2, String str);

    void removeBridgeIdleDebugListener(NotThreadSafeBridgeIdleDebugListener notThreadSafeBridgeIdleDebugListener);

    void runJSBundle();

    void setBundleInfo(Bundle bundle);

    void setGlobalVariable(String str, String str2);

    void setTurboModuleManager(JSIModule jSIModule);

    void showDebugOption();
}
