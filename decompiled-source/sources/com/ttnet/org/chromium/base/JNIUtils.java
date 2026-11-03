package com.ttnet.org.chromium.base;

import android.content.Context;
import android.text.TextUtils;
import com.ttnet.org.chromium.base.annotations.MainDex;
import java.util.Map;
@MainDex
/* loaded from: classes9.dex */
public class JNIUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static ClassLoader sJniClassLoader;
    private static Boolean sSelectiveJniRegistrationEnabled;

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = sJniClassLoader;
        return classLoader == null ? JNIUtils.class.getClassLoader() : classLoader;
    }

    public static ClassLoader getSplitClassLoader(String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (!TextUtils.isEmpty(str) && BundleUtils.isIsolatedSplitInstalled(applicationContext, str)) {
            return BundleUtils.createIsolatedSplitContext(applicationContext, str).getClassLoader();
        }
        return getClassLoader();
    }

    public static void setClassLoader(ClassLoader classLoader) {
        sJniClassLoader = classLoader;
    }

    public static boolean isSelectiveJniRegistrationEnabled() {
        if (sSelectiveJniRegistrationEnabled == null) {
            sSelectiveJniRegistrationEnabled = false;
        }
        return sSelectiveJniRegistrationEnabled.booleanValue();
    }

    public static void enableSelectiveJniRegistration() {
        sSelectiveJniRegistrationEnabled = true;
    }

    public static <K, V> void splitMap(Map<K, V> map, K[] kArr, V[] vArr) {
        int i2 = 0;
        for (Map.Entry<K, V> entry : map.entrySet()) {
            kArr[i2] = entry.getKey();
            vArr[i2] = entry.getValue();
            i2++;
        }
    }
}
