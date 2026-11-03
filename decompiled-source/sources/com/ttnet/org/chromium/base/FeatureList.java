package com.ttnet.org.chromium.base;

import com.facebook.internal.security.CertificateUtil;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.library_loader.LibraryLoader;
import java.util.HashMap;
import java.util.Map;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public class FeatureList {
    private static boolean sTestCanUseDefaults;
    private static TestValues sTestFeatures;

    /* loaded from: classes9.dex */
    public interface Natives {
        boolean isInitialized();
    }

    /* loaded from: classes9.dex */
    public static class TestValues {
        private Map<String, Boolean> mFeatureFlags = new HashMap();
        private Map<String, String> mFieldTrialParams = new HashMap();

        public void setFeatureFlagsOverride(Map<String, Boolean> map) {
            this.mFeatureFlags = map;
        }

        public void addFeatureFlagOverride(String str, boolean z) {
            this.mFeatureFlags.put(str, Boolean.valueOf(z));
        }

        public void addFieldTrialParamOverride(String str, String str2, String str3) {
            this.mFieldTrialParams.put(makeKey(str, str2), str3);
        }

        Boolean getFeatureFlagOverride(String str) {
            return this.mFeatureFlags.get(str);
        }

        String getFieldTrialParamOverride(String str, String str2) {
            return this.mFieldTrialParams.get(makeKey(str, str2));
        }

        private static String makeKey(String str, String str2) {
            return str + CertificateUtil.DELIMITER + str2;
        }
    }

    private FeatureList() {
    }

    public static boolean isInitialized() {
        return hasTestFeatures() || isNativeInitialized();
    }

    public static boolean isNativeInitialized() {
        if (LibraryLoader.getInstance().isInitialized()) {
            return FeatureListJni.get().isInitialized();
        }
        return false;
    }

    public static void setTestCanUseDefaultsForTesting() {
        sTestCanUseDefaults = true;
    }

    public static void resetTestCanUseDefaultsForTesting() {
        sTestCanUseDefaults = false;
    }

    public static void setTestFeatures(Map<String, Boolean> map) {
        if (map == null) {
            setTestValues(null);
            return;
        }
        TestValues testValues = new TestValues();
        testValues.setFeatureFlagsOverride(map);
        setTestValues(testValues);
    }

    public static void setTestValues(TestValues testValues) {
        sTestFeatures = testValues;
    }

    public static boolean hasTestFeatures() {
        return sTestFeatures != null;
    }

    public static boolean hasTestFeature(String str) {
        return hasTestFeatures() && sTestFeatures.mFeatureFlags.containsKey(str);
    }

    public static Boolean getTestValueForFeature(String str) {
        if (hasTestFeatures()) {
            Boolean featureFlagOverride = sTestFeatures.getFeatureFlagOverride(str);
            if (featureFlagOverride != null) {
                return featureFlagOverride;
            }
            if (sTestCanUseDefaults) {
                return null;
            }
            throw new IllegalArgumentException("No test value configured for " + str + " and native is not available to provide a default value. Use @EnableFeatures or @DisableFeatures to provide test values for the flag.");
        }
        return null;
    }

    public static String getTestValueForFieldTrialParam(String str, String str2) {
        String fieldTrialParamOverride;
        if (!hasTestFeatures() || (fieldTrialParamOverride = sTestFeatures.getFieldTrialParamOverride(str, str2)) == null) {
            return null;
        }
        return fieldTrialParamOverride;
    }
}
