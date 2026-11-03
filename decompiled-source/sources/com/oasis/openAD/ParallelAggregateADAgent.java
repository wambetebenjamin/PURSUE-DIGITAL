package com.oasis.openAD;

import android.content.Context;
import com.oasis.Logger.Logger;
import com.oasis.android.R;
/* loaded from: classes9.dex */
public class ParallelAggregateADAgent {
    public static String Tag = "com.oasis.openAD.ParallelAggregateADAgent";

    /* renamed from: b  reason: collision with root package name */
    static final /* synthetic */ boolean f17563b = true;
    public static ParallelAggregateADAgent instance;
    public static boolean isUseDefaultClass;

    /* renamed from: a  reason: collision with root package name */
    private SerialAdListener f17564a;
    public InterstitialAdListener interstitialAdListener;
    public RewardedAdListener rewardedAdListener;

    public static ParallelAggregateADAgent createInstance(Context context) {
        if (f17563b || instance == null) {
            try {
                String string = context.getString(R.string.parallel_aggregate_ad_agent_class);
                if (!string.isEmpty()) {
                    instance = (ParallelAggregateADAgent) Class.forName(string).newInstance();
                    Logger.i(Tag, "Create the ParallelAggregateADAgent: " + string);
                    isUseDefaultClass = false;
                }
            } catch (Exception e2) {
                Logger.e(Tag, "createInstance: load class error: " + e2.toString());
                e2.printStackTrace();
            }
            if (instance == null) {
                instance = new ParallelAggregateADAgent();
                isUseDefaultClass = true;
                Logger.e(Tag, "Create default ParallelAggregateADAgent.");
            }
            Tag = instance.getClass().getName();
            return instance;
        }
        throw new AssertionError();
    }

    public static ParallelAggregateADAgent getInstance() {
        return instance;
    }

    public void createAd(int i2, String str) {
        Logger.d(Tag, String.format("createAd(videoType: %d, unitId: %s)", Integer.valueOf(i2), str));
    }

    public void createInterstitialAd(String str) {
        Logger.d(Tag, String.format("createInterstitialAd(unitId: %s)", str));
    }

    public void createRewardedAd(String str) {
        Logger.d(Tag, String.format("createRewardedAd(unitId: %s)", str));
    }

    public final InterstitialAdListener getInterstitialAdListener() {
        Logger.d(Tag, "getInterstitialAdListener()");
        return this.interstitialAdListener;
    }

    public final RewardedAdListener getRewardedAdListener() {
        Logger.d(Tag, "getRewardedAdListener()");
        return this.rewardedAdListener;
    }

    public final SerialAdListener getSerialAdListener() {
        Logger.d(Tag, "getSerialAdListener()");
        return this.f17564a;
    }

    public void initMaxAd(ParallelAdInitListener parallelAdInitListener) {
        Logger.d(Tag, "initMaxAd");
    }

    public boolean isInterstitialAdReady(String str) {
        Logger.d(Tag, String.format("isInterstitialAdReady(unitId: %s)", str));
        return false;
    }

    public boolean isRewardedAdReady(String str) {
        Logger.d(Tag, String.format("isRewardedAdReady(unitId: %s)", str));
        return false;
    }

    public boolean isVideoReady(int i2) {
        Logger.d(Tag, String.format("isVideoReady(videoType: %d)", Integer.valueOf(i2)));
        return false;
    }

    public void loadAd(int i2) {
        Logger.d(Tag, String.format("loadAd(videoType: %d)", Integer.valueOf(i2)));
    }

    public void loadInterstitialAd(String str) {
        Logger.d(Tag, String.format("loadInterstitialAd(unitId: %s)", str));
    }

    public void loadRewardedAd(String str) {
        Logger.d(Tag, String.format("loadRewardedAd(unitId: %s)", str));
    }

    public void reportUserIDtoAD(String str) {
        Logger.d(Tag, "reportUserIDtoAD(userId: " + str + ")");
    }

    public void setApplovinDoNotSell(boolean z) {
        Logger.d(Tag, "setApplovinDoNotSell(consent: " + z + ")");
    }

    public void setApplovinHasUserConsent(boolean z) {
        Logger.d(Tag, "setApplovinHasUserConsent(consent: " + z + ")");
    }

    public void setApplovinRestrictedUser(boolean z) {
        Logger.d(Tag, "setApplovinRestrictedUser(consent: " + z + ")");
    }

    public final void setInterstitialAdListener(InterstitialAdListener interstitialAdListener) {
        Logger.d(Tag, "setInterstitialAdListener()");
        this.interstitialAdListener = interstitialAdListener;
    }

    public final void setRewardedAdListener(RewardedAdListener rewardedAdListener) {
        Logger.d(Tag, "setRewardedAdListener()");
        this.rewardedAdListener = rewardedAdListener;
    }

    public final void setSerialAdListener(SerialAdListener serialAdListener) {
        this.f17564a = serialAdListener;
    }

    public void showADDebug() {
        Logger.d(Tag, "showADDebug showMediationDebugger");
    }

    public void showInterstitialAd(String str) {
        Logger.d(Tag, String.format("showInterstitialAd(unitId: %s)", str));
    }

    public void showInterstitialAd(String str, String str2) {
        Logger.d(Tag, String.format("showInterstitialAd(unitId: %s, placement: %s)", str, str2));
    }

    public void showInterstitialAdVideo(String str) {
        Logger.d(Tag, String.format("showInterstitialAdVideo(placement: %s)", str));
    }

    public void showRewardAdVideo(String str) {
        Logger.d(Tag, String.format("showRewardAdVideo(placement: %s)", str));
    }

    public void showRewardedAd(String str) {
        Logger.d(Tag, String.format("showRewardedAd(unitId: %s)", str));
    }

    public void showRewardedAd(String str, String str2) {
        Logger.d(Tag, String.format("showRewardedAd(unitId: %s, placement: %s)", str, str2));
    }
}
