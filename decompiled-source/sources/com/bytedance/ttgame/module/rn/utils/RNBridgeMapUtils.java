package com.bytedance.ttgame.module.rn.utils;

import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes6.dex */
public class RNBridgeMapUtils {
    public static String getString(ReadableMap readableMap, String str) {
        if (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) {
            return null;
        }
        return readableMap.getString(str);
    }

    public static ReadableMap getMap(ReadableMap readableMap, String str) {
        if (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) {
            return null;
        }
        return readableMap.getMap(str);
    }

    public static ReadableArray getArray(ReadableMap readableMap, String str) {
        if (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) {
            return null;
        }
        return readableMap.getArray(str);
    }

    public static int getInt(ReadableMap readableMap, String str) {
        if (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) {
            return 0;
        }
        return readableMap.getInt(str);
    }

    public static double getDouble(ReadableMap readableMap, String str) {
        return (readableMap == null || TextUtils.isEmpty(str) || !readableMap.hasKey(str)) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : readableMap.getDouble(str);
    }
}
