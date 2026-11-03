package com.facebook.react.bridge;
/* loaded from: classes8.dex */
public class DefaultNativeModuleCallExceptionHandler implements NativeModuleCallExceptionHandler {
    @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        exc.printStackTrace();
    }
}
