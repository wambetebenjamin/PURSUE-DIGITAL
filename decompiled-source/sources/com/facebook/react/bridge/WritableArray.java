package com.facebook.react.bridge;
/* loaded from: classes8.dex */
public interface WritableArray extends ReadableArray {
    void pushArray(ReadableArray readableArray);

    void pushBoolean(boolean z);

    void pushDouble(double d2);

    void pushInt(int i2);

    void pushMap(ReadableMap readableMap);

    void pushNull();

    void pushString(String str);
}
