package com.ss.android.socialbase.downloader.network;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes9.dex */
public class ExponentialGeometricAverage {
    private int mCount;
    private final int mCutover;
    private final double mDecayConstant;
    private double mValue = -1.0d;

    public ExponentialGeometricAverage(double d2) {
        this.mDecayConstant = d2;
        this.mCutover = d2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? Integer.MAX_VALUE : (int) Math.ceil(1.0d / d2);
    }

    public void addMeasurement(double d2) {
        double d3 = 1.0d - this.mDecayConstant;
        int i2 = this.mCount;
        if (i2 > this.mCutover) {
            this.mValue = Math.exp((d3 * Math.log(this.mValue)) + (this.mDecayConstant * Math.log(d2)));
        } else if (i2 > 0) {
            double d4 = (d3 * i2) / (i2 + 1.0d);
            this.mValue = Math.exp((d4 * Math.log(this.mValue)) + ((1.0d - d4) * Math.log(d2)));
        } else {
            this.mValue = d2;
        }
        this.mCount++;
    }

    public double getAverage() {
        return this.mValue;
    }

    public void reset() {
        this.mValue = -1.0d;
        this.mCount = 0;
    }
}
