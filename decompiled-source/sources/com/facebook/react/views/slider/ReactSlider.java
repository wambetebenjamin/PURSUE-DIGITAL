package com.facebook.react.views.slider;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatSeekBar;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes8.dex */
public class ReactSlider extends AppCompatSeekBar {
    private static int DEFAULT_TOTAL_STEPS = 128;
    private double mMaxValue;
    private double mMinValue;
    private double mStep;
    private double mStepCalculated;
    private double mValue;

    public ReactSlider(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.mMinValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mMaxValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mStep = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mStepCalculated = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        disableStateListAnimatorIfNeeded();
    }

    private void disableStateListAnimatorIfNeeded() {
        if (Build.VERSION.SDK_INT < 23 || Build.VERSION.SDK_INT >= 26) {
            return;
        }
        super.setStateListAnimator(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMaxValue(double d2) {
        this.mMaxValue = d2;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setMinValue(double d2) {
        this.mMinValue = d2;
        updateAll();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setValue(double d2) {
        this.mValue = d2;
        updateValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setStep(double d2) {
        this.mStep = d2;
        updateAll();
    }

    public double toRealProgress(int i2) {
        if (i2 == getMax()) {
            return this.mMaxValue;
        }
        return (i2 * getStepValue()) + this.mMinValue;
    }

    private void updateAll() {
        if (this.mStep == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            this.mStepCalculated = (this.mMaxValue - this.mMinValue) / DEFAULT_TOTAL_STEPS;
        }
        setMax(getTotalSteps());
        updateValue();
    }

    private void updateValue() {
        double d2 = this.mValue;
        double d3 = this.mMinValue;
        setProgress((int) Math.round(((d2 - d3) / (this.mMaxValue - d3)) * getTotalSteps()));
    }

    private int getTotalSteps() {
        return (int) Math.ceil((this.mMaxValue - this.mMinValue) / getStepValue());
    }

    private double getStepValue() {
        double d2 = this.mStep;
        return d2 > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE ? d2 : this.mStepCalculated;
    }
}
