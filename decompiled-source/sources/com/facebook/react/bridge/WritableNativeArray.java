package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
/* loaded from: classes8.dex */
public class WritableNativeArray extends ReadableNativeArray implements WritableArray {
    private static native HybridData initHybrid();

    private native void pushNativeArray(WritableNativeArray writableNativeArray);

    private native void pushNativeMap(WritableNativeMap writableNativeMap);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushBoolean(boolean z);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushDouble(double d2);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushInt(int i2);

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushNull();

    @Override // com.facebook.react.bridge.WritableArray
    public native void pushString(String str);

    static {
        ReactBridge.staticInit();
    }

    public WritableNativeArray() {
        super(initHybrid());
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushArray(ReadableArray readableArray) {
        Assertions.assertCondition(readableArray == null || (readableArray instanceof WritableNativeArray), "Illegal type provided");
        pushNativeArray((WritableNativeArray) readableArray);
    }

    @Override // com.facebook.react.bridge.WritableArray
    public void pushMap(ReadableMap readableMap) {
        Assertions.assertCondition(readableMap == null || (readableMap instanceof WritableNativeMap), "Illegal type provided");
        pushNativeMap((WritableNativeMap) readableMap);
    }
}
