package com.oasis.openAD;

import com.oasis.Logger.Logger;
/* loaded from: classes9.dex */
public class a extends AggregateADAgent {

    /* renamed from: e  reason: collision with root package name */
    private static String f17565e = "DefaultAggregateADAgent";

    @Override // com.oasis.openAD.AggregateADAgent
    public void initSDK(AdInitListener adInitListener, String str) {
        Logger.i(f17565e, "initSDK");
        if (adInitListener != null) {
            adInitListener.onInit(false, "");
        }
    }

    @Override // com.oasis.openAD.AggregateADAgent
    public boolean isAdReady(int i2, String str) {
        return false;
    }

    @Override // com.oasis.openAD.AggregateADAgent
    public void loadAd(int i2, String str, String str2) {
        Logger.i(f17565e, "loadAd");
    }

    @Override // com.oasis.openAD.AggregateADAgent
    public void setAdListener(AggregateAdListener aggregateAdListener) {
        Logger.i(f17565e, "setAdListener");
    }

    @Override // com.oasis.openAD.AggregateADAgent
    public void showAd(int i2, String str) {
        Logger.i(f17565e, "showAd");
    }
}
