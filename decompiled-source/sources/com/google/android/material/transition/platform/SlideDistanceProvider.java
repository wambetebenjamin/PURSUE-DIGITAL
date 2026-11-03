package com.google.android.material.transition.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* loaded from: classes6.dex */
public final class SlideDistanceProvider implements VisibilityAnimatorProvider {
    private static final int DEFAULT_DISTANCE = -1;
    private int slideDistance = -1;
    private int slideEdge;

    @Retention(RetentionPolicy.SOURCE)
    /* loaded from: classes6.dex */
    public @interface GravityFlag {
    }

    public SlideDistanceProvider(int i2) {
        this.slideEdge = i2;
    }

    public int getSlideEdge() {
        return this.slideEdge;
    }

    public void setSlideEdge(int i2) {
        this.slideEdge = i2;
    }

    public int getSlideDistance() {
        return this.slideDistance;
    }

    public void setSlideDistance(int i2) {
        if (i2 < 0) {
            throw new IllegalArgumentException("Slide distance must be positive. If attempting to reverse the direction of the slide, use setSlideEdge(int) instead.");
        }
        this.slideDistance = i2;
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createAppear(ViewGroup viewGroup, View view) {
        return createTranslationAppearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    @Override // com.google.android.material.transition.platform.VisibilityAnimatorProvider
    public Animator createDisappear(ViewGroup viewGroup, View view) {
        return createTranslationDisappearAnimator(viewGroup, view, this.slideEdge, getSlideDistanceOrDefault(view.getContext()));
    }

    private int getSlideDistanceOrDefault(Context context) {
        int i2 = this.slideDistance;
        return i2 != -1 ? i2 : context.getResources().getDimensionPixelSize(R.dimen.mtrl_transition_shared_axis_slide_distance);
    }

    private static Animator createTranslationAppearAnimator(View view, View view2, int i2, int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 48) {
                    if (i2 != 80) {
                        if (i2 == 8388611) {
                            return createTranslationXAnimator(view2, isRtl(view) ? i3 + translationX : translationX - i3, translationX, translationX);
                        } else if (i2 == 8388613) {
                            return createTranslationXAnimator(view2, isRtl(view) ? translationX - i3 : i3 + translationX, translationX, translationX);
                        } else {
                            throw new IllegalArgumentException("Invalid slide direction: " + i2);
                        }
                    }
                    return createTranslationYAnimator(view2, i3 + translationY, translationY, translationY);
                }
                return createTranslationYAnimator(view2, translationY - i3, translationY, translationY);
            }
            return createTranslationXAnimator(view2, translationX - i3, translationX, translationX);
        }
        return createTranslationXAnimator(view2, i3 + translationX, translationX, translationX);
    }

    private static Animator createTranslationDisappearAnimator(View view, View view2, int i2, int i3) {
        float translationX = view2.getTranslationX();
        float translationY = view2.getTranslationY();
        if (i2 != 3) {
            if (i2 != 5) {
                if (i2 != 48) {
                    if (i2 != 80) {
                        if (i2 == 8388611) {
                            return createTranslationXAnimator(view2, translationX, isRtl(view) ? translationX - i3 : i3 + translationX, translationX);
                        } else if (i2 == 8388613) {
                            return createTranslationXAnimator(view2, translationX, isRtl(view) ? i3 + translationX : translationX - i3, translationX);
                        } else {
                            throw new IllegalArgumentException("Invalid slide direction: " + i2);
                        }
                    }
                    return createTranslationYAnimator(view2, translationY, translationY - i3, translationY);
                }
                return createTranslationYAnimator(view2, translationY, i3 + translationY, translationY);
            }
            return createTranslationXAnimator(view2, translationX, i3 + translationX, translationX);
        }
        return createTranslationXAnimator(view2, translationX, translationX - i3, translationX);
    }

    private static Animator createTranslationXAnimator(final View view, float f2, float f3, final float f4) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.TRANSLATION_X, f2, f3));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.platform.SlideDistanceProvider.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationX(f4);
            }
        });
        return ofPropertyValuesHolder;
    }

    private static Animator createTranslationYAnimator(final View view, float f2, float f3, final float f4) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat(View.TRANSLATION_Y, f2, f3));
        ofPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.transition.platform.SlideDistanceProvider.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                view.setTranslationY(f4);
            }
        });
        return ofPropertyValuesHolder;
    }

    private static boolean isRtl(View view) {
        return ViewCompat.getLayoutDirection(view) == 1;
    }
}
