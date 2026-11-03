package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import com.facebook.react.uimanager.IllegalViewOperationException;
/* loaded from: classes8.dex */
abstract class BaseLayoutAnimation extends AbstractLayoutAnimation {
    abstract boolean isReverse();

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0 && this.mAnimatedProperty != null;
    }

    /* renamed from: com.facebook.react.uimanager.layoutanimation.BaseLayoutAnimation$1  reason: invalid class name */
    /* loaded from: classes8.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType;

        static {
            int[] iArr = new int[AnimatedPropertyType.values().length];
            $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType = iArr;
            try {
                iArr[AnimatedPropertyType.OPACITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_XY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_X.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[AnimatedPropertyType.SCALE_Y.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    Animation createAnimationImpl(View view, int i2, int i3, int i4, int i5) {
        if (this.mAnimatedProperty != null) {
            int i6 = AnonymousClass1.$SwitchMap$com$facebook$react$uimanager$layoutanimation$AnimatedPropertyType[this.mAnimatedProperty.ordinal()];
            if (i6 == 1) {
                return new OpacityAnimation(view, isReverse() ? view.getAlpha() : 0.0f, isReverse() ? 0.0f : view.getAlpha());
            } else if (i6 == 2) {
                float f2 = isReverse() ? 1.0f : 0.0f;
                float f3 = isReverse() ? 0.0f : 1.0f;
                return new ScaleAnimation(f2, f3, f2, f3, 1, 0.5f, 1, 0.5f);
            } else if (i6 == 3) {
                return new ScaleAnimation(isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1.0f, 1.0f, 1, 0.5f, 1, 0.0f);
            } else if (i6 == 4) {
                return new ScaleAnimation(1.0f, 1.0f, isReverse() ? 1.0f : 0.0f, isReverse() ? 0.0f : 1.0f, 1, 0.0f, 1, 0.5f);
            } else {
                throw new IllegalViewOperationException("Missing animation for property : " + this.mAnimatedProperty);
            }
        }
        throw new IllegalViewOperationException("Missing animated property from animation config");
    }
}
