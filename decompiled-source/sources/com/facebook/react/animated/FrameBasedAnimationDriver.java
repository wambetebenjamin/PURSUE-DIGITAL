package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes8.dex */
class FrameBasedAnimationDriver extends AnimationDriver {
    private static final double FRAME_TIME_MILLIS = 16.666666666666668d;
    private int mCurrentLoop;
    private double[] mFrames;
    private double mFromValue;
    private int mIterations;
    private long mStartFrameTimeNanos;
    private double mToValue;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FrameBasedAnimationDriver(ReadableMap readableMap) {
        resetConfig(readableMap);
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void resetConfig(ReadableMap readableMap) {
        ReadableArray array = readableMap.getArray("frames");
        int size = array.size();
        double[] dArr = this.mFrames;
        if (dArr == null || dArr.length != size) {
            this.mFrames = new double[size];
        }
        for (int i2 = 0; i2 < size; i2++) {
            this.mFrames[i2] = array.getDouble(i2);
        }
        boolean hasKey = readableMap.hasKey("toValue");
        double d2 = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        if (hasKey) {
            if (readableMap.getType("toValue") == ReadableType.Number) {
                d2 = readableMap.getDouble("toValue");
            }
            this.mToValue = d2;
        } else {
            this.mToValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        if (readableMap.hasKey("iterations")) {
            this.mIterations = readableMap.getType("iterations") == ReadableType.Number ? readableMap.getInt("iterations") : 1;
        } else {
            this.mIterations = 1;
        }
        this.mCurrentLoop = 1;
        this.mHasFinished = this.mIterations == 0;
        this.mStartFrameTimeNanos = -1L;
    }

    @Override // com.facebook.react.animated.AnimationDriver
    public void runAnimationStep(long j2) {
        double d2;
        if (this.mStartFrameTimeNanos < 0) {
            this.mStartFrameTimeNanos = j2;
            if (this.mCurrentLoop == 1) {
                this.mFromValue = this.mAnimatedValue.mValue;
            }
        }
        int round = (int) Math.round(((j2 - this.mStartFrameTimeNanos) / 1000000) / FRAME_TIME_MILLIS);
        if (round < 0) {
            throw new IllegalStateException("Calculated frame index should never be lower than 0");
        }
        if (this.mHasFinished) {
            return;
        }
        double[] dArr = this.mFrames;
        if (round >= dArr.length - 1) {
            d2 = this.mToValue;
            int i2 = this.mIterations;
            if (i2 == -1 || this.mCurrentLoop < i2) {
                this.mStartFrameTimeNanos = -1L;
                this.mCurrentLoop++;
            } else {
                this.mHasFinished = true;
            }
        } else {
            double d3 = this.mFromValue;
            d2 = (dArr[round] * (this.mToValue - d3)) + d3;
        }
        this.mAnimatedValue.mValue = d2;
    }
}
