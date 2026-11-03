package com.bytedance.react.framework.modules;

import com.bytedance.react.framework.core.BRNWindowManager;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import javax.annotation.Nonnull;
/* loaded from: classes2.dex */
public class RNWindowManagerModule extends ReactContextBaseJavaModule {
    private static final String NAME = "WindowManagerModule";

    @Override // com.facebook.react.bridge.NativeModule
    @Nonnull
    public String getName() {
        return NAME;
    }

    public RNWindowManagerModule(@Nonnull ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void closeRNWindow(String str, final Promise promise) {
        BridgeLogUtil.reportBegin(getName() + "-closeRNWindow", str);
        BRNWindowManager.closeWindow(getCurrentActivity(), str, new BRNWindowManager.IOperationWindowListener() { // from class: com.bytedance.react.framework.modules.RNWindowManagerModule.1
            @Override // com.bytedance.react.framework.core.BRNWindowManager.IOperationWindowListener
            public void onOperationResult(boolean z) {
                Promise promise2 = promise;
                if (promise2 != null) {
                    promise2.resolve(Boolean.valueOf(z));
                }
            }
        });
        BridgeLogUtil.reportEnd(getName() + "-closeRNWindow", str);
    }

    @ReactMethod
    public void hideRNWindow(String str, final Promise promise) {
        BridgeLogUtil.reportBegin(getName() + "-hideRNWindow", str);
        BRNWindowManager.hide(getCurrentActivity(), str, new BRNWindowManager.IOperationWindowListener() { // from class: com.bytedance.react.framework.modules.RNWindowManagerModule.2
            @Override // com.bytedance.react.framework.core.BRNWindowManager.IOperationWindowListener
            public void onOperationResult(boolean z) {
                Promise promise2 = promise;
                if (promise2 != null) {
                    promise2.resolve(Boolean.valueOf(z));
                }
            }
        });
        BridgeLogUtil.reportEnd(getName() + "-hideRNWindow", str);
    }

    @ReactMethod
    public void showRNWindow(String str, final Promise promise) {
        BridgeLogUtil.reportBegin(getName() + "-showRNWindow", str);
        BRNWindowManager.show(getCurrentActivity(), str, new BRNWindowManager.IOperationWindowListener() { // from class: com.bytedance.react.framework.modules.RNWindowManagerModule.3
            @Override // com.bytedance.react.framework.core.BRNWindowManager.IOperationWindowListener
            public void onOperationResult(boolean z) {
                Promise promise2 = promise;
                if (promise2 != null) {
                    promise2.resolve(Boolean.valueOf(z));
                }
            }
        });
        BridgeLogUtil.reportEnd(getName() + "-showRNWindow", str);
    }
}
