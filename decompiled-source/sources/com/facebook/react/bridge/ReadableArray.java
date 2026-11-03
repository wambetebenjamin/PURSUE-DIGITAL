package com.facebook.react.bridge;

import java.util.ArrayList;
/* loaded from: classes8.dex */
public interface ReadableArray {
    ReadableArray getArray(int i2);

    boolean getBoolean(int i2);

    double getDouble(int i2);

    Dynamic getDynamic(int i2);

    int getInt(int i2);

    ReadableMap getMap(int i2);

    String getString(int i2);

    ReadableType getType(int i2);

    boolean isNull(int i2);

    int size();

    ArrayList<Object> toArrayList();
}
