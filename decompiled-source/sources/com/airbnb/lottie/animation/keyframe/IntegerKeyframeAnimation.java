package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import java.util.List;
/* loaded from: classes.dex */
public class IntegerKeyframeAnimation extends KeyframeAnimation<Integer> {
    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f2) {
        return getValue((Keyframe<Integer>) keyframe, f2);
    }

    public IntegerKeyframeAnimation(List<Keyframe<Integer>> list) {
        super(list);
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    Integer getValue(Keyframe<Integer> keyframe, float f2) {
        return Integer.valueOf(getIntValue(keyframe, f2));
    }

    int getIntValue(Keyframe<Integer> keyframe, float f2) {
        Integer num;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        if (this.valueCallback != null && (num = (Integer) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), keyframe.startValue, keyframe.endValue, f2, getLinearCurrentKeyframeProgress(), getProgress())) != null) {
            return num.intValue();
        }
        return MiscUtils.lerp(keyframe.getStartValueInt(), keyframe.getEndValueInt(), f2);
    }

    public int getIntValue() {
        return getIntValue(getCurrentKeyframe(), getInterpolatedCurrentKeyframeProgress());
    }
}
