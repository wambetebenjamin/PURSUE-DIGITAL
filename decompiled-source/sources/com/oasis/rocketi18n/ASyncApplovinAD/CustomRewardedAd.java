package com.oasis.rocketi18n.ASyncApplovinAD;

import android.app.Activity;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.oasis.Logger.Logger;
import com.oasis.openAD.ParallelAggregateADAgent;
import com.oasis.openAD.RewardedAdListener;
import com.oasis.openAD.SerialAdListener;
/* loaded from: classes9.dex */
public class CustomRewardedAd implements MaxRewardedAdListener {
    public static final int AdType = 1;
    private boolean isSerial;
    private RewardedAdListener rewardListener;
    private final MaxRewardedAd rewardedAd;
    private SerialAdListener serialAdListener;
    private final String unitId;

    public CustomRewardedAd(Activity activity, String str) {
        this.rewardListener = null;
        this.serialAdListener = null;
        this.unitId = str;
        MaxRewardedAd maxRewardedAd = MaxRewardedAd.getInstance(str, activity);
        this.rewardedAd = maxRewardedAd;
        maxRewardedAd.setListener(this);
        if (ParallelAggregateADAgent.getInstance() != null) {
            this.rewardListener = ParallelAggregateADAgent.getInstance().getRewardedAdListener();
        }
    }

    public CustomRewardedAd(Activity activity, String str, boolean z) {
        this(activity, str);
        this.isSerial = z;
        if (z) {
            this.serialAdListener = ParallelAggregateADAgent.getInstance().getSerialAdListener();
        }
    }

    public void onAdLoaded(MaxAd maxAd) {
        String networkName = maxAd.getNetworkName();
        Logger.i("RewardedAdActivity " + maxAd.getAdUnitId(), "onAdLoaded(isSerial: " + this.isSerial + ") " + networkName);
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdLoaded(1);
            }
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoLoaded " + networkName);
            this.rewardListener.onRewardedAdLoaded(this.unitId);
        }
    }

    public void onAdLoadFailed(String str, MaxError maxError) {
        Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoLoadFailed, error code:" + maxError.getCode());
        if (this.isSerial) {
            this.serialAdListener.onAdLoadFailed(1);
            return;
        }
        RewardedAdListener rewardedAdListener = this.rewardListener;
        if (rewardedAdListener != null) {
            rewardedAdListener.onRewardedAdLoadFailed(this.unitId);
        }
    }

    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        Logger.i("RewardedAdActivity" + this.unitId, "onRewardedDisplayFailed, error code:" + maxError.getCode());
        if (this.isSerial) {
            this.serialAdListener.onAdDisplayFailed(1);
            return;
        }
        RewardedAdListener rewardedAdListener = this.rewardListener;
        if (rewardedAdListener != null) {
            rewardedAdListener.onRewardedAdDisplayFailed(this.unitId);
        }
    }

    public void onAdDisplayed(MaxAd maxAd) {
        Logger.i("RewardedAdActivity" + this.unitId, "onAdDisplayed");
        if (this.isSerial) {
            this.serialAdListener.onAdDisplayed(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedDisplayed");
            this.rewardListener.onRewardedAdDisplayed(this.unitId);
        }
    }

    public void onAdClicked(MaxAd maxAd) {
        Logger.i("RewardedAdActivity" + this.unitId, "onAdClicked");
        if (this.isSerial) {
            this.serialAdListener.onAdClicked(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedClicked");
            this.rewardListener.onRewardedAdClicked(this.unitId);
        }
    }

    public void onAdHidden(MaxAd maxAd) {
        Logger.i("RewardedAdActivity" + this.unitId, "onAdHidden");
        if (this.isSerial) {
            this.serialAdListener.onAdHidden(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedHidden");
            this.rewardListener.onRewardedAdHidden(this.unitId);
        }
    }

    public void onRewardedVideoStarted(MaxAd maxAd) {
        Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoStarted");
        if (this.isSerial) {
            this.serialAdListener.onVideoStarted(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoStarted");
            this.rewardListener.onRewardedVideoStarted(this.unitId);
        }
    }

    public void onRewardedVideoCompleted(MaxAd maxAd) {
        Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoCompleted 1");
        if (this.isSerial) {
            this.serialAdListener.onVideoCompleted(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onRewardedVideoCompleted");
            this.rewardListener.onRewardedCompleted(this.unitId);
        }
    }

    public void onUserRewarded(MaxAd maxAd, MaxReward maxReward) {
        if (this.isSerial) {
            this.serialAdListener.onUserRewarded(1);
        } else if (this.rewardListener != null) {
            Logger.i("RewardedAdActivity" + this.unitId, "onUserRewarded");
            this.rewardListener.onRewardedUserRewarded(this.unitId);
        }
    }

    public void showRewardAd() {
        MaxRewardedAd maxRewardedAd = this.rewardedAd;
        if (maxRewardedAd != null) {
            maxRewardedAd.showAd();
        }
    }

    public void showRewardAd(String str) {
        MaxRewardedAd maxRewardedAd = this.rewardedAd;
        if (maxRewardedAd != null) {
            maxRewardedAd.showAd(str);
        }
    }

    public boolean isRewardReady() {
        MaxRewardedAd maxRewardedAd = this.rewardedAd;
        if (maxRewardedAd != null) {
            return maxRewardedAd.isReady();
        }
        return false;
    }

    public void loadRewardAd() {
        MaxRewardedAd maxRewardedAd = this.rewardedAd;
        if (maxRewardedAd != null) {
            maxRewardedAd.loadAd();
        }
    }
}
