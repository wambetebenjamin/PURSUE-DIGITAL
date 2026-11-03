package androidx.dynamicanimation.animation;

import android.os.Looper;
import android.util.AndroidRuntimeException;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.dynamicanimation.animation.AnimationHandler;
import androidx.dynamicanimation.animation.DynamicAnimation;
import com.bytedance.ttgame.module.webview.api.WebViewConfig;
import com.facebook.react.uimanager.ViewProps;
import java.util.ArrayList;
/* loaded from: classes.dex */
public abstract class DynamicAnimation<T extends DynamicAnimation<T>> implements AnimationHandler.AnimationFrameCallback {
    public static final float MIN_VISIBLE_CHANGE_ALPHA = 0.00390625f;
    public static final float MIN_VISIBLE_CHANGE_PIXELS = 1.0f;
    public static final float MIN_VISIBLE_CHANGE_ROTATION_DEGREES = 0.1f;
    public static final float MIN_VISIBLE_CHANGE_SCALE = 0.002f;
    private static final float THRESHOLD_MULTIPLIER = 0.75f;
    private static final float UNSET = Float.MAX_VALUE;
    private final ArrayList<OnAnimationEndListener> mEndListeners;
    private long mLastFrameTime;
    float mMaxValue;
    float mMinValue;
    private float mMinVisibleChange;
    final FloatPropertyCompat mProperty;
    boolean mRunning;
    boolean mStartValueIsSet;
    final Object mTarget;
    private final ArrayList<OnAnimationUpdateListener> mUpdateListeners;
    float mValue;
    float mVelocity;
    public static final ViewProperty TRANSLATION_X = new ViewProperty("translationX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.1
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setTranslationX(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getTranslationX();
        }
    };
    public static final ViewProperty TRANSLATION_Y = new ViewProperty("translationY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.2
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setTranslationY(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getTranslationY();
        }
    };
    public static final ViewProperty TRANSLATION_Z = new ViewProperty("translationZ") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.3
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            ViewCompat.setTranslationZ(view, f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return ViewCompat.getTranslationZ(view);
        }
    };
    public static final ViewProperty SCALE_X = new ViewProperty(ViewProps.SCALE_X) { // from class: androidx.dynamicanimation.animation.DynamicAnimation.4
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setScaleX(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScaleX();
        }
    };
    public static final ViewProperty SCALE_Y = new ViewProperty(ViewProps.SCALE_Y) { // from class: androidx.dynamicanimation.animation.DynamicAnimation.5
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setScaleY(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScaleY();
        }
    };
    public static final ViewProperty ROTATION = new ViewProperty(ViewProps.ROTATION) { // from class: androidx.dynamicanimation.animation.DynamicAnimation.6
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setRotation(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotation();
        }
    };
    public static final ViewProperty ROTATION_X = new ViewProperty("rotationX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.7
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setRotationX(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotationX();
        }
    };
    public static final ViewProperty ROTATION_Y = new ViewProperty("rotationY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.8
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setRotationY(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getRotationY();
        }
    };
    public static final ViewProperty X = new ViewProperty(WebViewConfig.LOCATION_X) { // from class: androidx.dynamicanimation.animation.DynamicAnimation.9
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setX(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getX();
        }
    };
    public static final ViewProperty Y = new ViewProperty(WebViewConfig.LOCATION_Y) { // from class: androidx.dynamicanimation.animation.DynamicAnimation.10
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setY(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getY();
        }
    };
    public static final ViewProperty Z = new ViewProperty("z") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.11
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            ViewCompat.setZ(view, f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return ViewCompat.getZ(view);
        }
    };
    public static final ViewProperty ALPHA = new ViewProperty("alpha") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.12
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setAlpha(f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getAlpha();
        }
    };
    public static final ViewProperty SCROLL_X = new ViewProperty("scrollX") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.13
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setScrollX((int) f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScrollX();
        }
    };
    public static final ViewProperty SCROLL_Y = new ViewProperty("scrollY") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.14
        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(View view, float f2) {
            view.setScrollY((int) f2);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(View view) {
            return view.getScrollY();
        }
    };

    /* loaded from: classes.dex */
    static class MassState {
        float mValue;
        float mVelocity;
    }

    /* loaded from: classes.dex */
    public interface OnAnimationEndListener {
        void onAnimationEnd(DynamicAnimation dynamicAnimation, boolean z, float f2, float f3);
    }

    /* loaded from: classes.dex */
    public interface OnAnimationUpdateListener {
        void onAnimationUpdate(DynamicAnimation dynamicAnimation, float f2, float f3);
    }

    abstract float getAcceleration(float f2, float f3);

    abstract boolean isAtEquilibrium(float f2, float f3);

    abstract void setValueThreshold(float f2);

    abstract boolean updateValueAndVelocity(long j2);

    /* loaded from: classes.dex */
    public static abstract class ViewProperty extends FloatPropertyCompat<View> {
        private ViewProperty(String str) {
            super(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DynamicAnimation(final FloatValueHolder floatValueHolder) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -Float.MAX_VALUE;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList<>();
        this.mUpdateListeners = new ArrayList<>();
        this.mTarget = null;
        this.mProperty = new FloatPropertyCompat("FloatValueHolder") { // from class: androidx.dynamicanimation.animation.DynamicAnimation.15
            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public float getValue(Object obj) {
                return floatValueHolder.getValue();
            }

            @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
            public void setValue(Object obj, float f2) {
                floatValueHolder.setValue(f2);
            }
        };
        this.mMinVisibleChange = 1.0f;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public <K> DynamicAnimation(K k, FloatPropertyCompat<K> floatPropertyCompat) {
        this.mVelocity = 0.0f;
        this.mValue = Float.MAX_VALUE;
        this.mStartValueIsSet = false;
        this.mRunning = false;
        this.mMaxValue = Float.MAX_VALUE;
        this.mMinValue = -Float.MAX_VALUE;
        this.mLastFrameTime = 0L;
        this.mEndListeners = new ArrayList<>();
        this.mUpdateListeners = new ArrayList<>();
        this.mTarget = k;
        this.mProperty = floatPropertyCompat;
        if (floatPropertyCompat == ROTATION || floatPropertyCompat == ROTATION_X || floatPropertyCompat == ROTATION_Y) {
            this.mMinVisibleChange = 0.1f;
        } else if (floatPropertyCompat == ALPHA) {
            this.mMinVisibleChange = 0.00390625f;
        } else if (floatPropertyCompat == SCALE_X || floatPropertyCompat == SCALE_Y) {
            this.mMinVisibleChange = 0.00390625f;
        } else {
            this.mMinVisibleChange = 1.0f;
        }
    }

    public T setStartValue(float f2) {
        this.mValue = f2;
        this.mStartValueIsSet = true;
        return this;
    }

    public T setStartVelocity(float f2) {
        this.mVelocity = f2;
        return this;
    }

    public T setMaxValue(float f2) {
        this.mMaxValue = f2;
        return this;
    }

    public T setMinValue(float f2) {
        this.mMinValue = f2;
        return this;
    }

    public T addEndListener(OnAnimationEndListener onAnimationEndListener) {
        if (!this.mEndListeners.contains(onAnimationEndListener)) {
            this.mEndListeners.add(onAnimationEndListener);
        }
        return this;
    }

    public void removeEndListener(OnAnimationEndListener onAnimationEndListener) {
        removeEntry(this.mEndListeners, onAnimationEndListener);
    }

    public T addUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        if (isRunning()) {
            throw new UnsupportedOperationException("Error: Update listeners must be added beforethe animation.");
        }
        if (!this.mUpdateListeners.contains(onAnimationUpdateListener)) {
            this.mUpdateListeners.add(onAnimationUpdateListener);
        }
        return this;
    }

    public void removeUpdateListener(OnAnimationUpdateListener onAnimationUpdateListener) {
        removeEntry(this.mUpdateListeners, onAnimationUpdateListener);
    }

    public T setMinimumVisibleChange(float f2) {
        if (f2 <= 0.0f) {
            throw new IllegalArgumentException("Minimum visible change must be positive.");
        }
        this.mMinVisibleChange = f2;
        setValueThreshold(f2 * 0.75f);
        return this;
    }

    public float getMinimumVisibleChange() {
        return this.mMinVisibleChange;
    }

    private static <T> void removeNullEntries(ArrayList<T> arrayList) {
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    private static <T> void removeEntry(ArrayList<T> arrayList, T t) {
        int indexOf = arrayList.indexOf(t);
        if (indexOf >= 0) {
            arrayList.set(indexOf, null);
        }
    }

    public void start() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be started on the main thread");
        }
        if (this.mRunning) {
            return;
        }
        startAnimationInternal();
    }

    public void cancel() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new AndroidRuntimeException("Animations may only be canceled on the main thread");
        }
        if (this.mRunning) {
            endAnimationInternal(true);
        }
    }

    public boolean isRunning() {
        return this.mRunning;
    }

    private void startAnimationInternal() {
        if (this.mRunning) {
            return;
        }
        this.mRunning = true;
        if (!this.mStartValueIsSet) {
            this.mValue = getPropertyValue();
        }
        float f2 = this.mValue;
        if (f2 > this.mMaxValue || f2 < this.mMinValue) {
            throw new IllegalArgumentException("Starting value need to be in between min value and max value");
        }
        AnimationHandler.getInstance().addAnimationFrameCallback(this, 0L);
    }

    @Override // androidx.dynamicanimation.animation.AnimationHandler.AnimationFrameCallback
    public boolean doAnimationFrame(long j2) {
        long j3 = this.mLastFrameTime;
        if (j3 == 0) {
            this.mLastFrameTime = j2;
            setPropertyValue(this.mValue);
            return false;
        }
        this.mLastFrameTime = j2;
        boolean updateValueAndVelocity = updateValueAndVelocity(j2 - j3);
        float min = Math.min(this.mValue, this.mMaxValue);
        this.mValue = min;
        float max = Math.max(min, this.mMinValue);
        this.mValue = max;
        setPropertyValue(max);
        if (updateValueAndVelocity) {
            endAnimationInternal(false);
        }
        return updateValueAndVelocity;
    }

    private void endAnimationInternal(boolean z) {
        this.mRunning = false;
        AnimationHandler.getInstance().removeCallback(this);
        this.mLastFrameTime = 0L;
        this.mStartValueIsSet = false;
        for (int i2 = 0; i2 < this.mEndListeners.size(); i2++) {
            if (this.mEndListeners.get(i2) != null) {
                this.mEndListeners.get(i2).onAnimationEnd(this, z, this.mValue, this.mVelocity);
            }
        }
        removeNullEntries(this.mEndListeners);
    }

    void setPropertyValue(float f2) {
        this.mProperty.setValue(this.mTarget, f2);
        for (int i2 = 0; i2 < this.mUpdateListeners.size(); i2++) {
            if (this.mUpdateListeners.get(i2) != null) {
                this.mUpdateListeners.get(i2).onAnimationUpdate(this, this.mValue, this.mVelocity);
            }
        }
        removeNullEntries(this.mUpdateListeners);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public float getValueThreshold() {
        return this.mMinVisibleChange * 0.75f;
    }

    private float getPropertyValue() {
        return this.mProperty.getValue(this.mTarget);
    }
}
