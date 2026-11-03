package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class FloatKeyframeAnimation extends KeyframeAnimation<Float> {
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f2) {
        return getValue((Keyframe<Float>) keyframe, f2);
    }

    public FloatKeyframeAnimation(List<Keyframe<Float>> list) {
        super(list);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Float getValue(Keyframe<Float> keyframe, float f2) {
        return Float.valueOf(getFloatValue(keyframe, f2));
    }

    float getFloatValue(Keyframe<Float> keyframe, float f2) {
        Float f3;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if (this.valueCallback != null && (f3 = (Float) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), keyframe.startValue, keyframe.endValue, f2, getLinearCurrentKeyframeProgress(), getProgress())) != null) {
            return f3.floatValue();
        }
        return MiscUtils.lerp(keyframe.getStartValueFloat(), keyframe.getEndValueFloat(), f2);
    }

    public float getFloatValue() {
        return getFloatValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
