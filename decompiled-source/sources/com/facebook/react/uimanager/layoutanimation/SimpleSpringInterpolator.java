package com.facebook.react.uimanager.layoutanimation;

import android.view.animation.Interpolator;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes8.dex */
public class SimpleSpringInterpolator implements Interpolator {
    private static final float FACTOR = 0.5f;
    public static final String PARAM_SPRING_DAMPING = "springDamping";
    private final float mSpringDamping;

    public static float getSpringDamping(ReadableMap readableMap) {
        if (readableMap.getType(PARAM_SPRING_DAMPING).equals(ReadableType.Number)) {
            return (float) readableMap.getDouble(PARAM_SPRING_DAMPING);
        }
        return 0.5f;
    }

    public SimpleSpringInterpolator() {
        this.mSpringDamping = 0.5f;
    }

    public SimpleSpringInterpolator(float f2) {
        this.mSpringDamping = f2;
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f2) {
        double pow = Math.pow(2.0d, (-10.0f) * f2);
        float f3 = this.mSpringDamping;
        return (float) ((pow * Math.sin((((f2 - (f3 / 4.0f)) * 3.141592653589793d) * 2.0d) / f3)) + 1.0d);
    }
}
