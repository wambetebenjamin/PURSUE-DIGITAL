package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public abstract class Features {
    private final String mName;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean getFieldTrialParamByFeatureAsBoolean(long j2, String str, boolean z);

        boolean isEnabled(long j2);
    }

    protected abstract long getFeaturePointer();

    protected Features(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    public boolean isEnabled() {
        Boolean testValueForFeature = FeatureList.getTestValueForFeature(getName());
        if (testValueForFeature != null) {
            return testValueForFeature.booleanValue();
        }
        return FeaturesJni.get().isEnabled(getFeaturePointer());
    }

    public boolean getFieldTrialParamByFeatureAsBoolean(String str, boolean z) {
        return FeaturesJni.get().getFieldTrialParamByFeatureAsBoolean(getFeaturePointer(), str, z);
    }
}
