package com.oasis.rocketi18n.ASyncApplovinAD;

import android.app.Activity;
import android.util.Log;
import com.applovin.sdk.AppLovinPrivacySettings;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.oasis.Logger.Logger;
import com.oasis.android.ActivityManager;
import com.oasis.openAD.ParallelAdInitListener;
import com.oasis.openAD.ParallelAggregateADAgent;
import java.util.HashMap;
/* loaded from: classes9.dex */
public class RocketI18NParallelApplovinADAgent extends ParallelAggregateADAgent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private CustomInterstitialAd interstitialAd;
    private CustomRewardedAd rewardedAd;
    private final HashMap<String, CustomRewardedAd> rewardedDic = new HashMap<>();
    private final HashMap<String, CustomInterstitialAd> interstitialDic = new HashMap<>();

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void initMaxAd(final ParallelAdInitListener parallelAdInitListener) {
        super.initMaxAd(parallelAdInitListener);
        ActivityManager.getActivity().runOnUiThread(new Runnable() { // from class: com.oasis.rocketi18n.ASyncApplovinAD.RocketI18NParallelApplovinADAgent$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                RocketI18NParallelApplovinADAgent.lambda$initMaxAd$1(ParallelAdInitListener.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initMaxAd$1(final ParallelAdInitListener parallelAdInitListener) {
        Logger.i(Tag, "initMaxAd");
        Activity activity = ActivityManager.getActivity();
        AppLovinSdk.getInstance(activity).setMediationProvider("max");
        AppLovinSdk.initializeSdk(activity, new AppLovinSdk.SdkInitializationListener() { // from class: com.oasis.rocketi18n.ASyncApplovinAD.RocketI18NParallelApplovinADAgent$$ExternalSyntheticLambda0
            public final void onSdkInitialized(AppLovinSdkConfiguration appLovinSdkConfiguration) {
                RocketI18NParallelApplovinADAgent.lambda$initMaxAd$0(ParallelAdInitListener.this, appLovinSdkConfiguration);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initMaxAd$0(ParallelAdInitListener parallelAdInitListener, AppLovinSdkConfiguration appLovinSdkConfiguration) {
        int ordinal = appLovinSdkConfiguration.getConsentDialogState().ordinal();
        Logger.i(Tag, "initMaxAd init AppLovinSdk onSuccess, ordinal: " + ordinal);
        AppLovinSdk.getInstance(ActivityManager.getActivity()).setMediationProvider("max");
        parallelAdInitListener.onInitFinished(ordinal);
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showADDebug() {
        super.showADDebug();
        AppLovinSdk.getInstance(ActivityManager.getActivity()).showMediationDebugger();
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void reportUserIDtoAD(String str) {
        super.reportUserIDtoAD(str);
        AppLovinSdk.getInstance(ActivityManager.getActivity()).setUserIdentifier(str);
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void setApplovinHasUserConsent(boolean z) {
        super.setApplovinHasUserConsent(z);
        AppLovinPrivacySettings.setHasUserConsent(z, ActivityManager.getActivity());
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void setApplovinRestrictedUser(boolean z) {
        super.setApplovinRestrictedUser(z);
        AppLovinPrivacySettings.setIsAgeRestrictedUser(z, ActivityManager.getActivity());
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void setApplovinDoNotSell(boolean z) {
        super.setApplovinDoNotSell(z);
        AppLovinPrivacySettings.setDoNotSell(z, ActivityManager.getActivity());
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void createRewardedAd(String str) {
        super.createRewardedAd(str);
        if (this.rewardedDic.containsKey(str)) {
            Logger.i(Tag, "createRewardedAd already exist! unitId: " + str);
            return;
        }
        Log.i(Tag, "CreateAd with unit id: " + str);
        this.rewardedDic.put(str, new CustomRewardedAd(ActivityManager.getActivity(), str));
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void loadRewardedAd(String str) {
        super.loadRewardedAd(str);
        if (!this.rewardedDic.containsKey(str)) {
            Logger.i(Tag, "loadRewardedAd not exist! unitId: " + str);
        } else {
            this.rewardedDic.get(str).loadRewardAd();
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public boolean isRewardedAdReady(String str) {
        super.isRewardedAdReady(str);
        if (!this.rewardedDic.containsKey(str)) {
            Logger.i(Tag, "isRewardedAdReady not exist! unitId: " + str);
            return false;
        }
        return this.rewardedDic.get(str).isRewardReady();
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showRewardedAd(String str) {
        super.showRewardedAd(str);
        if (!this.rewardedDic.containsKey(str)) {
            Logger.i(Tag, "showRewardedAd not exist unitId: " + str);
        } else {
            this.rewardedDic.get(str).showRewardAd();
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showRewardedAd(String str, String str2) {
        super.showRewardedAd(str, str2);
        if (!this.rewardedDic.containsKey(str)) {
            Logger.i(Tag, "showRewardedAd not exist unitId: " + str);
        } else {
            this.rewardedDic.get(str).showRewardAd(str2);
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void createInterstitialAd(String str) {
        super.createInterstitialAd(str);
        if (this.interstitialDic.containsKey(str)) {
            Logger.i(Tag, "createInterstitialAd already exist unitId: " + str);
            return;
        }
        Logger.i(Tag, "CreateAd with unit id: " + str);
        this.interstitialDic.put(str, new CustomInterstitialAd(ActivityManager.getActivity(), str));
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void loadInterstitialAd(String str) {
        super.loadInterstitialAd(str);
        if (!this.interstitialDic.containsKey(str)) {
            Logger.i(Tag, "loadInterstitialAd not exist unitId: " + str);
        } else {
            this.interstitialDic.get(str).loadInterstitialAd();
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public boolean isInterstitialAdReady(String str) {
        super.isInterstitialAdReady(str);
        if (!this.interstitialDic.containsKey(str)) {
            Logger.i(Tag, "isInterstitialAdReady not exist unitId: " + str);
            return false;
        }
        return this.interstitialDic.get(str).isInterstitialReady();
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showInterstitialAd(String str) {
        super.showInterstitialAd(str);
        if (!this.interstitialDic.containsKey(str)) {
            Logger.i(Tag, "showInterstitialAd not exist unitId: " + str);
        } else {
            this.interstitialDic.get(str).showInterstitialAd();
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showInterstitialAd(String str, String str2) {
        super.showInterstitialAd(str, str2);
        if (!this.interstitialDic.containsKey(str)) {
            Logger.i(Tag, "showInterstitialAd not exist unitId: " + str);
        } else {
            this.interstitialDic.get(str).showInterstitialAd(str2);
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void createAd(int i2, String str) {
        super.createAd(i2, str);
        Activity activity = ActivityManager.getActivity();
        if (1 == i2) {
            this.rewardedAd = new CustomRewardedAd(activity, str, true);
        } else if (2 == i2) {
            this.interstitialAd = new CustomInterstitialAd(activity, str, true);
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void loadAd(int i2) {
        super.loadAd(i2);
        if (1 == i2) {
            CustomRewardedAd customRewardedAd = this.rewardedAd;
            if (customRewardedAd == null) {
                Logger.w(Tag, "loadAd: not create rewarded ad!");
            } else {
                customRewardedAd.loadRewardAd();
            }
        } else if (2 == i2) {
            CustomInterstitialAd customInterstitialAd = this.interstitialAd;
            if (customInterstitialAd == null) {
                Logger.w(Tag, "loadAd: not create interstitial ad!");
            } else {
                customInterstitialAd.loadInterstitialAd();
            }
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public boolean isVideoReady(int i2) {
        CustomInterstitialAd customInterstitialAd;
        super.isVideoReady(i2);
        if (1 != i2) {
            return 2 == i2 && (customInterstitialAd = this.interstitialAd) != null && customInterstitialAd.isInterstitialReady();
        }
        CustomRewardedAd customRewardedAd = this.rewardedAd;
        return customRewardedAd != null && customRewardedAd.isRewardReady();
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showRewardAdVideo(String str) {
        super.showRewardAdVideo(str);
        if (this.rewardedAd == null) {
            Logger.w(Tag, "showRewardAdVideo: not create rewarded ad!");
        } else if (str != null && !str.isEmpty()) {
            this.rewardedAd.showRewardAd(str);
        } else {
            this.rewardedAd.showRewardAd();
        }
    }

    @Override // com.oasis.openAD.ParallelAggregateADAgent
    public void showInterstitialAdVideo(String str) {
        super.showInterstitialAdVideo(str);
        if (this.interstitialAd == null) {
            Logger.w(Tag, "showInterstitialAdVideo: not create interstitialAd ad!");
        } else if (str != null && !str.isEmpty()) {
            this.interstitialAd.showInterstitialAd(str);
        } else {
            this.interstitialAd.showInterstitialAd();
        }
    }
}
