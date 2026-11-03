package com.twitter.sdk.android.tweetui.internal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewPropertyAnimator;
/* loaded from: classes10.dex */
class AnimationUtils {
    AnimationUtils() {
    }

    public static ViewPropertyAnimator fadeOut(final View view, int i2) {
        if (view.getVisibility() == 0) {
            view.clearAnimation();
            ViewPropertyAnimator animate = view.animate();
            animate.alpha(0.0f).setDuration(i2).setListener(new AnimatorListenerAdapter() { // from class: com.twitter.sdk.android.tweetui.internal.AnimationUtils.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    view.setVisibility(4);
                    view.setAlpha(1.0f);
                }
            });
            return animate;
        }
        return null;
    }

    public static ViewPropertyAnimator fadeIn(View view, int i2) {
        if (view.getVisibility() != 0) {
            view.setAlpha(0.0f);
            view.setVisibility(0);
        }
        view.clearAnimation();
        ViewPropertyAnimator animate = view.animate();
        animate.alpha(1.0f).setDuration(i2).setListener(null);
        return animate;
    }
}
