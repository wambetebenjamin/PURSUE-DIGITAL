package com.airbnb.lottie.value;

import com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation;
/* loaded from: classes.dex */
public class LottieValueCallback<T> {
    private BaseKeyframeAnimation<?, ?> animation;
    private final LottieFrameInfo<T> frameInfo;
    protected T value;

    public LottieValueCallback() {
        this.frameInfo = new LottieFrameInfo<>();
        this.value = null;
    }

    public LottieValueCallback(T t) {
        this.frameInfo = new LottieFrameInfo<>();
        this.value = null;
        this.value = t;
    }

    public T getValue(LottieFrameInfo<T> lottieFrameInfo) {
        return this.value;
    }

    public final void setValue(T t) {
        this.value = t;
        BaseKeyframeAnimation<?, ?> baseKeyframeAnimation = this.animation;
        if (baseKeyframeAnimation != null) {
            baseKeyframeAnimation.notifyListeners();
        }
    }

    public final T getValueInternal(float f2, float f3, T t, T t2, float f4, float f5, float f6) {
        return getValue(this.frameInfo.set(f2, f3, t, t2, f4, f5, f6));
    }

    public final void setAnimation(BaseKeyframeAnimation<?, ?> baseKeyframeAnimation) {
        this.animation = baseKeyframeAnimation;
    }
}
