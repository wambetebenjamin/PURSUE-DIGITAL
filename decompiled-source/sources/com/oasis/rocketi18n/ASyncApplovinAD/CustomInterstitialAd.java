package com.oasis.rocketi18n.ASyncApplovinAD;

import android.app.Activity;
import android.util.Log;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxInterstitialAd;
import com.oasis.openAD.InterstitialAdListener;
import com.oasis.openAD.ParallelAggregateADAgent;
import com.oasis.openAD.SerialAdListener;
/* loaded from: classes9.dex */
public class CustomInterstitialAd implements MaxAdListener {
    public static final int AdType = 2;
    private InterstitialAdListener interListener;
    private MaxInterstitialAd interstitialAd;
    private boolean isSerial;
    private SerialAdListener serialAdListener;
    private final String unitId;

    public CustomInterstitialAd(Activity activity, String str) {
        this.interListener = null;
        this.serialAdListener = null;
        this.unitId = str;
        MaxInterstitialAd maxInterstitialAd = new MaxInterstitialAd(str, activity);
        this.interstitialAd = maxInterstitialAd;
        maxInterstitialAd.setListener(this);
        if (ParallelAggregateADAgent.getInstance() != null) {
            this.interListener = ParallelAggregateADAgent.getInstance().getInterstitialAdListener();
        }
    }

    public CustomInterstitialAd(Activity activity, String str, boolean z) {
        this(activity, str);
        this.isSerial = z;
        this.serialAdListener = ParallelAggregateADAgent.getInstance().getSerialAdListener();
    }

    public void onAdLoaded(MaxAd maxAd) {
        Log.i("InterstitialAd", "onAdLoaded");
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdLoaded(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdLoaded(this.unitId);
        }
    }

    public void onAdLoadFailed(String str, MaxError maxError) {
        Log.i("InterstitialAd", "onAdLoadFailed code:" + maxError.getCode() + " message:" + maxError.getMessage());
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdLoadFailed(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdLoadFailed(this.unitId);
        }
    }

    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {
        Log.i("InterstitialAd", "onAdDisplayFailed code:" + maxError.getCode() + " message:" + maxError.getMessage());
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdDisplayFailed(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdDisplayFailed(this.unitId);
        }
    }

    public void onAdDisplayed(MaxAd maxAd) {
        Log.i("InterstitialAd", "onAdDisplayed");
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdDisplayed(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdDisplayed(this.unitId);
        }
    }

    public void onAdClicked(MaxAd maxAd) {
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdClicked(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdClicked(this.unitId);
        }
    }

    public void onAdHidden(MaxAd maxAd) {
        if (this.isSerial) {
            SerialAdListener serialAdListener = this.serialAdListener;
            if (serialAdListener != null) {
                serialAdListener.onAdHidden(2);
                return;
            }
            return;
        }
        InterstitialAdListener interstitialAdListener = this.interListener;
        if (interstitialAdListener != null) {
            interstitialAdListener.onInterstitialAdHidden(this.unitId);
        }
    }

    public void showInterstitialAd() {
        this.interstitialAd.showAd();
    }

    public void showInterstitialAd(String str) {
        this.interstitialAd.showAd(str);
    }

    public boolean isInterstitialReady() {
        MaxInterstitialAd maxInterstitialAd = this.interstitialAd;
        if (maxInterstitialAd != null) {
            return maxInterstitialAd.isReady();
        }
        return false;
    }

    public void loadInterstitialAd() {
        MaxInterstitialAd maxInterstitialAd = this.interstitialAd;
        if (maxInterstitialAd != null) {
            maxInterstitialAd.loadAd();
        }
    }
}
