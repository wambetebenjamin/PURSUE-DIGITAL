package com.google.android.material.progressindicator;

import android.animation.Animator;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes6.dex */
public abstract class IndeterminateAnimatorDelegate<T extends Animator> {
    protected IndeterminateDrawable drawable;
    protected final int[] segmentColors;
    protected final float[] segmentPositions;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void cancelAnimatorImmediately();

    /* JADX INFO: Access modifiers changed from: protected */
    public float getFractionInRange(int i2, int i3, int i4) {
        return (i2 - i3) / i4;
    }

    public abstract void invalidateSpecValues();

    public abstract void registerAnimatorsCompleteCallback(Animatable2Compat.AnimationCallback animationCallback);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void requestCancelAnimatorAfterCurrentCycle();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void startAnimator();

    public abstract void unregisterAnimatorsCompleteCallback();

    /* JADX INFO: Access modifiers changed from: protected */
    public IndeterminateAnimatorDelegate(int i2) {
        this.segmentPositions = new float[i2 * 2];
        this.segmentColors = new int[i2];
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void registerDrawable(IndeterminateDrawable indeterminateDrawable) {
        this.drawable = indeterminateDrawable;
    }
}
