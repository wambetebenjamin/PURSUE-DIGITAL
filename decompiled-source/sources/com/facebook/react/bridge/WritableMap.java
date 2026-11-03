package com.facebook.react.bridge;
/* loaded from: classes8.dex */
public interface WritableMap extends ReadableMap {
    WritableMap copy();

    void merge(ReadableMap readableMap);

    void putArray(String str, ReadableArray readableArray);

    void putBoolean(String str, boolean z);

    void putDouble(String str, double d2);

    void putInt(String str, int i2);

    void putMap(String str, ReadableMap readableMap);

    void putNull(String str);

    void putString(String str, String str2);
}
