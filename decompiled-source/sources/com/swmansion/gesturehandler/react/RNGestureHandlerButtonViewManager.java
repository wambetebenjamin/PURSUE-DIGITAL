package com.swmansion.gesturehandler.react;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.PaintDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
/* loaded from: classes9.dex */
public class RNGestureHandlerButtonViewManager extends ViewGroupManager<ButtonViewGroup> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return "RNGestureHandlerButton";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static class ButtonViewGroup extends ViewGroup {
        static TypedValue sResolveOutValue = new TypedValue();
        static ButtonViewGroup sResponder;
        int mBackgroundColor;
        float mBorderRadius;
        boolean mNeedBackgroundUpdate;
        Integer mRippleColor;
        boolean mUseBorderless;
        boolean mUseForeground;

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDrawableHotspotChanged(float f2, float f3) {
        }

        @Override // android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        }

        public ButtonViewGroup(Context context) {
            super(context);
            this.mBackgroundColor = 0;
            this.mUseForeground = false;
            this.mUseBorderless = false;
            this.mBorderRadius = 0.0f;
            this.mNeedBackgroundUpdate = false;
            setClickable(true);
            setFocusable(true);
            this.mNeedBackgroundUpdate = true;
        }

        @Override // android.view.View
        public void setBackgroundColor(int i2) {
            this.mBackgroundColor = i2;
            this.mNeedBackgroundUpdate = true;
        }

        public void setRippleColor(Integer num) {
            this.mRippleColor = num;
            this.mNeedBackgroundUpdate = true;
        }

        public void setBorderRadius(float f2) {
            this.mBorderRadius = f2 * getResources().getDisplayMetrics().density;
            this.mNeedBackgroundUpdate = true;
        }

        private Drawable applyRippleEffectWhenNeeded(Drawable drawable) {
            if (this.mRippleColor != null && drawable != null && Build.VERSION.SDK_INT >= 21 && (drawable instanceof RippleDrawable)) {
                ((RippleDrawable) drawable).setColor(new ColorStateList(new int[][]{new int[]{16842910}}, new int[]{this.mRippleColor.intValue()}));
            }
            return drawable;
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (super.onInterceptTouchEvent(motionEvent)) {
                return true;
            }
            onTouchEvent(motionEvent);
            return isPressed();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void updateBackground() {
            if (this.mNeedBackgroundUpdate) {
                this.mNeedBackgroundUpdate = false;
                if (this.mBackgroundColor == 0) {
                    setBackground(null);
                }
                if (Build.VERSION.SDK_INT >= 23) {
                    setForeground(null);
                }
                if (this.mUseForeground && Build.VERSION.SDK_INT >= 23) {
                    setForeground(applyRippleEffectWhenNeeded(createSelectableDrawable()));
                    int i2 = this.mBackgroundColor;
                    if (i2 != 0) {
                        setBackgroundColor(i2);
                    }
                } else if (this.mBackgroundColor == 0 && this.mRippleColor == null) {
                    setBackground(createSelectableDrawable());
                } else {
                    PaintDrawable paintDrawable = new PaintDrawable(this.mBackgroundColor);
                    Drawable createSelectableDrawable = createSelectableDrawable();
                    float f2 = this.mBorderRadius;
                    if (f2 != 0.0f) {
                        paintDrawable.setCornerRadius(f2);
                        if (Build.VERSION.SDK_INT >= 21 && (createSelectableDrawable instanceof RippleDrawable)) {
                            PaintDrawable paintDrawable2 = new PaintDrawable(-1);
                            paintDrawable2.setCornerRadius(this.mBorderRadius);
                            ((RippleDrawable) createSelectableDrawable).setDrawableByLayerId(16908334, paintDrawable2);
                        }
                    }
                    applyRippleEffectWhenNeeded(createSelectableDrawable);
                    setBackground(new LayerDrawable(new Drawable[]{paintDrawable, createSelectableDrawable}));
                }
            }
        }

        public void setUseDrawableOnForeground(boolean z) {
            this.mUseForeground = z;
            this.mNeedBackgroundUpdate = true;
        }

        public void setUseBorderlessDrawable(boolean z) {
            this.mUseBorderless = z;
        }

        private Drawable createSelectableDrawable() {
            int i2 = Build.VERSION.SDK_INT;
            getContext().getTheme().resolveAttribute(getResources().getIdentifier((!this.mUseBorderless || i2 < 21) ? "selectableItemBackground" : "selectableItemBackgroundBorderless", "attr", "android"), sResolveOutValue, true);
            if (i2 >= 21) {
                return getResources().getDrawable(sResolveOutValue.resourceId, getContext().getTheme());
            }
            return getResources().getDrawable(sResolveOutValue.resourceId);
        }

        @Override // android.view.View
        public void drawableHotspotChanged(float f2, float f3) {
            ButtonViewGroup buttonViewGroup = sResponder;
            if (buttonViewGroup == null || buttonViewGroup == this) {
                super.drawableHotspotChanged(f2, f3);
            }
        }

        @Override // android.view.View
        public void setPressed(boolean z) {
            if (z && sResponder == null) {
                sResponder = this;
            }
            if (!z || sResponder == this) {
                super.setPressed(z);
            }
            if (z || sResponder != this) {
                return;
            }
            sResponder = null;
        }
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public ButtonViewGroup createViewInstance(ThemedReactContext themedReactContext) {
        return new ButtonViewGroup(themedReactContext);
    }

    @ReactProp(name = "foreground")
    public void setForeground(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseDrawableOnForeground(z);
    }

    @ReactProp(name = "borderless")
    public void setBorderless(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setUseBorderlessDrawable(z);
    }

    @ReactProp(name = ViewProps.ENABLED)
    public void setEnabled(ButtonViewGroup buttonViewGroup, boolean z) {
        buttonViewGroup.setEnabled(z);
    }

    @ReactProp(name = ViewProps.BORDER_RADIUS)
    public void setBorderRadius(ButtonViewGroup buttonViewGroup, float f2) {
        buttonViewGroup.setBorderRadius(f2);
    }

    @ReactProp(name = "rippleColor")
    public void setRippleColor(ButtonViewGroup buttonViewGroup, Integer num) {
        buttonViewGroup.setRippleColor(num);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(ButtonViewGroup buttonViewGroup) {
        buttonViewGroup.updateBackground();
    }
}
