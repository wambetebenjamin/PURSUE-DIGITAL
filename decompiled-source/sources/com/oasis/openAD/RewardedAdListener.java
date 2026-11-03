package com.oasis.openAD;
/* loaded from: classes9.dex */
public interface RewardedAdListener {
    void onRewardedAdClicked(String str);

    void onRewardedAdDisplayFailed(String str);

    void onRewardedAdDisplayed(String str);

    void onRewardedAdHidden(String str);

    void onRewardedAdLoadFailed(String str);

    void onRewardedAdLoaded(String str);

    void onRewardedCompleted(String str);

    void onRewardedShowAdFail(String str);

    void onRewardedUserRewarded(String str);

    void onRewardedVideoStarted(String str);
}
