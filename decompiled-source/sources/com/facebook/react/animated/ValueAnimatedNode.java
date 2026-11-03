package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* loaded from: classes8.dex */
class ValueAnimatedNode extends AnimatedNode {
    Object mAnimatedObject;
    double mOffset;
    double mValue;
    private AnimatedNodeValueListener mValueListener;

    public ValueAnimatedNode() {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public ValueAnimatedNode(ReadableMap readableMap) {
        this.mAnimatedObject = null;
        this.mValue = Double.NaN;
        this.mOffset = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        this.mValue = readableMap.getDouble("value");
        this.mOffset = readableMap.getDouble("offset");
    }

    public double getValue() {
        if (Double.isNaN(this.mOffset + this.mValue)) {
            update();
        }
        return this.mOffset + this.mValue;
    }

    public Object getAnimatedObject() {
        return this.mAnimatedObject;
    }

    public void flattenOffset() {
        this.mValue += this.mOffset;
        this.mOffset = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public void extractOffset() {
        this.mOffset += this.mValue;
        this.mValue = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    public void onValueUpdate() {
        AnimatedNodeValueListener animatedNodeValueListener = this.mValueListener;
        if (animatedNodeValueListener == null) {
            return;
        }
        animatedNodeValueListener.onValueUpdate(getValue());
    }

    public void setValueListener(AnimatedNodeValueListener animatedNodeValueListener) {
        this.mValueListener = animatedNodeValueListener;
    }
}
