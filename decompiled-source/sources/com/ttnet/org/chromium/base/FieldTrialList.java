package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.annotations.MainDex;
@MainDex
/* loaded from: classes9.dex */
public class FieldTrialList {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        boolean createFieldTrial(String str, String str2);

        String findFullName(String str);

        String getVariationParameter(String str, String str2);

        void logActiveTrials();

        boolean trialExists(String str);
    }

    private FieldTrialList() {
    }

    public static String findFullName(String str) {
        return FieldTrialListJni.get().findFullName(str);
    }

    public static boolean trialExists(String str) {
        return FieldTrialListJni.get().trialExists(str);
    }

    public static String getVariationParameter(String str, String str2) {
        return FieldTrialListJni.get().getVariationParameter(str, str2);
    }

    public static void logActiveTrials() {
        FieldTrialListJni.get().logActiveTrials();
    }

    public static boolean createFieldTrial(String str, String str2) {
        return FieldTrialListJni.get().createFieldTrial(str, str2);
    }
}
