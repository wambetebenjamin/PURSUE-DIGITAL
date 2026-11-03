package com.airbnb.lottie.animation.keyframe;

import com.airbnb.lottie.utils.MiscUtils;
import com.airbnb.lottie.value.Keyframe;
import com.airbnb.lottie.value.ScaleXY;
import java.util.List;
/* loaded from: classes.dex */
public class ScaleKeyframeAnimation extends KeyframeAnimation<ScaleXY> {
    private final ScaleXY scaleXY;

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public /* bridge */ /* synthetic */ Object getValue(Keyframe keyframe, float f2) {
        return getValue((Keyframe<ScaleXY>) keyframe, f2);
    }

    public ScaleKeyframeAnimation(List<Keyframe<ScaleXY>> list) {
        super(list);
        this.scaleXY = new ScaleXY();
    }

    @Override // com.airbnb.lottie.animation.keyframe.BaseKeyframeAnimation
    public ScaleXY getValue(Keyframe<ScaleXY> keyframe, float f2) {
        ScaleXY scaleXY;
        if (keyframe.startValue == null || keyframe.endValue == null) {
            throw new IllegalStateException("Missing values for keyframe.");
        }
        ScaleXY scaleXY2 = keyframe.startValue;
        ScaleXY scaleXY3 = keyframe.endValue;
        if (this.valueCallback == null || (scaleXY = (ScaleXY) this.valueCallback.getValueInternal(keyframe.startFrame, keyframe.endFrame.floatValue(), scaleXY2, scaleXY3, f2, getLinearCurrentKeyframeProgress(), getProgress())) == null) {
            this.scaleXY.set(MiscUtils.lerp(scaleXY2.getScaleX(), scaleXY3.getScaleX(), f2), MiscUtils.lerp(scaleXY2.getScaleY(), scaleXY3.getScaleY(), f2));
            return this.scaleXY;
        }
        return scaleXY;
    }
}
