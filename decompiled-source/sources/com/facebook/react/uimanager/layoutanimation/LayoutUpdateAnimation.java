package com.facebook.react.uimanager.layoutanimation;

import android.view.View;
import android.view.animation.Animation;
/* loaded from: classes8.dex */
class LayoutUpdateAnimation extends AbstractLayoutAnimation {
    private static final boolean USE_TRANSLATE_ANIMATION = false;

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    boolean isValid() {
        return this.mDurationMs > 0;
    }

    @Override // com.facebook.react.uimanager.layoutanimation.AbstractLayoutAnimation
    Animation createAnimationImpl(View view, int i2, int i3, int i4, int i5) {
        boolean z = false;
        boolean z2 = (view.getX() == ((float) i2) && view.getY() == ((float) i3)) ? false : true;
        if (view.getWidth() != i4 || view.getHeight() != i5) {
            z = true;
        }
        if (z2 || z) {
            return new PositionAndSizeAnimation(view, i2, i3, i4, i5);
        }
        return null;
    }
}
