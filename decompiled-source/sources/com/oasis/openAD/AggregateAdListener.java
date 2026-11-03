package com.oasis.openAD;
/* loaded from: classes9.dex */
public interface AggregateAdListener {
    void onAdCached(int i2, String str);

    void onAdClicked(int i2, String str);

    void onAdDisplayFailed(int i2, String str);

    void onAdDisplayed(int i2, String str);

    void onAdHidden(int i2, String str);

    void onAdLoadedResult(int i2, boolean z, String str);

    void onAdSkipped(int i2, String str);

    void onUserRewarded(int i2, String str);

    void onVideoCompleted(int i2, String str);

    void onVideoStarted(int i2, String str);
}
