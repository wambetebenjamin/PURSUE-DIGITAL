package com.airbnb.lottie.animation.keyframe;

import android.graphics.PointF;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.LottieValueCallback;
import java.util.Collections;
/* loaded from: classes.dex */
public class SplitDimensionPathKeyframeAnimation extends BaseKeyframeAnimation<PointF, PointF> {
    private final PointF point;
    private final PointF pointWithCallbackValues;
    private final BaseKeyframeAnimation<Float, Float> xAnimation;
    protected LottieValueCallback<Float> xValueCallback;
    private final BaseKeyframeAnimation<Float, Float> yAnimation;
    protected LottieValueCallback<Float> yValueCallback;

    public SplitDimensionPathKeyframeAnimation(BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation, BaseKeyframeAnimation<Float, Float> baseKeyframeAnimation2) {
        super(Collections.emptyList());
        this.point = new PointF();
        this.pointWithCallbackValues = new PointF();
        this.xAnimation = baseKeyframeAnimation;
        this.yAnimation = baseKeyframeAnimation2;
        setProgress(getProgress());
    }

    public void setXValueCallback(LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback<Float> lottieValueCallback2 = this.xValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.xValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    public void setYValueCallback(LottieValueCallback<Float> lottieValueCallback) {
        LottieValueCallback<Float> lottieValueCallback2 = this.yValueCallback;
        if (lottieValueCallback2 != null) {
            lottieValueCallback2.setAnimation(null);
        }
        this.yValueCallback = lottieValueCallback;
        if (lottieValueCallback != null) {
            lottieValueCallback.setAnimation(this);
        }
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public void setProgress(float f2) {
        this.xAnimation.setProgress(f2);
        this.yAnimation.setProgress(f2);
        this.point.set(this.xAnimation.getValue().floatValue(), this.yAnimation.getValue().floatValue());
        for (int i2 = 0; i2 < this.listeners.size(); i2++) {
            this.listeners.get(i2).onValueChanged();
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue() {
        return getValue((Keyframe<PointF>) null, 0.0f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public PointF getValue(Keyframe<PointF> keyframe, float f2) {
        Float f3;
        Keyframe<Float> currentKeyframe;
        Keyframe<Float> currentKeyframe2;
        Float f4 = null;
        if (this.xValueCallback == null || (currentKeyframe2 = this.xAnimation.getCurrentKeyframe()) == null) {
            f3 = null;
        } else {
            float interpolatedCurrentKeyframeProgress = this.xAnimation.getInterpolatedCurrentKeyframeProgress();
            Float f5 = currentKeyframe2.endFrame;
            f3 = this.xValueCallback.getValueInternal(currentKeyframe2.startFrame, f5 == null ? currentKeyframe2.startFrame : f5.floatValue(), currentKeyframe2.startValue, currentKeyframe2.endValue, f2, f2, interpolatedCurrentKeyframeProgress);
        }
        if (this.yValueCallback != null && (currentKeyframe = this.yAnimation.getCurrentKeyframe()) != null) {
            float interpolatedCurrentKeyframeProgress2 = this.yAnimation.getInterpolatedCurrentKeyframeProgress();
            Float f6 = currentKeyframe.endFrame;
            f4 = this.yValueCallback.getValueInternal(currentKeyframe.startFrame, f6 == null ? currentKeyframe.startFrame : f6.floatValue(), currentKeyframe.startValue, currentKeyframe.endValue, f2, f2, interpolatedCurrentKeyframeProgress2);
        }
        if (f3 == null) {
            this.pointWithCallbackValues.set(this.point.x, 0.0f);
        } else {
            this.pointWithCallbackValues.set(f3.floatValue(), 0.0f);
        }
        if (f4 == null) {
            PointF pointF = this.pointWithCallbackValues;
            pointF.set(pointF.x, this.point.y);
        } else {
            PointF pointF2 = this.pointWithCallbackValues;
            pointF2.set(pointF2.x, f4.floatValue());
        }
        return this.pointWithCallbackValues;
    }
}
