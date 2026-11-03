package com.bytedance.ttgame.module.rn.modules;

import android.text.TextUtils;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes6.dex */
public class RNSafeMapUtils {
    public static String getString(HashMap hashMap, String str) {
        if (hashMap == null || TextUtils.isEmpty(str) || !hashMap.containsKey(str)) {
            return null;
        }
        return String.valueOf(hashMap.get(str));
    }

    public static HashMap getMap(HashMap hashMap, String str) {
        if (hashMap == null || TextUtils.isEmpty(str) || !hashMap.containsKey(str)) {
            return null;
        }
        return (HashMap) hashMap.get(str);
    }

    public static List getArray(HashMap hashMap, String str) {
        if (hashMap == null || TextUtils.isEmpty(str) || !hashMap.containsKey(str)) {
            return null;
        }
        return (List) hashMap.get(str);
    }

    public static int getInt(HashMap hashMap, String str) {
        if (hashMap == null || TextUtils.isEmpty(str) || !hashMap.containsKey(str)) {
            return 0;
        }
        return ((Integer) hashMap.get(str)).intValue();
    }

    public static double getDouble(HashMap hashMap, String str) {
        return (hashMap == null || TextUtils.isEmpty(str) || !hashMap.containsKey(str)) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : ((Double) hashMap.get(str)).doubleValue();
    }
}
