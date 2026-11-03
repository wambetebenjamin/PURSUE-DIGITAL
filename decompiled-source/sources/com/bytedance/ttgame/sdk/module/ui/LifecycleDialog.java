package com.bytedance.ttgame.sdk.module.ui;

import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.animation.PathInterpolatorCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import com.bytedance.ttgame.sdk.module.skin.SkinManager;
import com.bytedance.ttgame.sdk.module.ui.LifecycleDialog;
/* loaded from: classes8.dex */
public abstract class LifecycleDialog extends Dialog implements LifecycleObserver {
    private static String TAG = "gsdk_lifecycle_dialog";
    protected static LifecycleDialog sDialog;
    private final int animationDuringTime;
    protected View contextViewForAnimation;
    protected Activity mActivity;
    private boolean mStatusDestroyed;

    public abstract int getRootResId();

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onActivityDestroy() {
        dismissLifecycleDialog();
        this.mStatusDestroyed = true;
    }

    public LifecycleDialog(Activity activity) {
        super(activity);
        this.contextViewForAnimation = null;
        this.animationDuringTime = 100;
        init(activity);
    }

    public LifecycleDialog(Activity activity, int i2) {
        super(activity, i2);
        this.contextViewForAnimation = null;
        this.animationDuringTime = 100;
        init(activity);
    }

    private void init(Activity activity) {
        this.mActivity = activity;
        if (activity instanceof AppCompatActivity) {
            ((AppCompatActivity) activity).getLifecycle().addObserver(this);
        }
        setCancelable(false);
        setCanceledOnTouchOutside(false);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        if (getWindow() != null) {
            SkinManager.INSTANCE.registerDialogView(this.mActivity, getWindow().getDecorView(), getRootResId());
        }
        super.onStart();
    }

    public void showLifecycleDialog() {
        try {
            try {
                getWindow().setFlags(8, 8);
                Activity activity = this.mActivity;
                if (activity != null && !activity.isFinishing() && !this.mStatusDestroyed && !isShowing()) {
                    if (getContentViewForAnimation() != null) {
                        getContentViewForAnimation().startAnimation(getEnterAnimation());
                    }
                    show();
                }
                setFullScreen();
                getWindow().getDecorView().setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() { // from class: com.bytedance.ttgame.sdk.module.ui.LifecycleDialog.1
                    @Override // android.view.View.OnSystemUiVisibilityChangeListener
                    public void onSystemUiVisibilityChange(int i2) {
                        LifecycleDialog.this.setFullScreen();
                    }
                });
            } catch (Exception e2) {
                Log.e(TAG, "LifecycleDialog showLifecycleDialog: " + e2.getMessage());
            }
        } finally {
            getWindow().clearFlags(8);
        }
    }

    public void dismissLifecycleDialog() {
        Activity activity = this.mActivity;
        if (activity == null || activity.isFinishing() || this.mStatusDestroyed || !isShowing()) {
            return;
        }
        if (getContentViewForAnimation() != null) {
            getContentViewForAnimation().startAnimation(getExitAnimation());
        } else {
            dismiss();
        }
    }

    public Animation getEnterAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(PathInterpolatorCompat.create(0.14f, 1.0f, 0.34f, 1.0f));
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(100L);
        return animationSet;
    }

    public Animation getExitAnimation() {
        AnimationSet animationSet = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setInterpolator(new LinearInterpolator());
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setInterpolator(PathInterpolatorCompat.create(0.14f, 1.0f, 0.34f, 1.0f));
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.setDuration(100L);
        animationSet.setFillAfter(true);
        animationSet.setAnimationListener(new AnonymousClass2());
        return animationSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.bytedance.ttgame.sdk.module.ui.LifecycleDialog$2  reason: invalid class name */
    /* loaded from: classes8.dex */
    public class AnonymousClass2 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass2() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            if (LifecycleDialog.this.getContentViewForAnimation() != null) {
                LifecycleDialog.this.getContentViewForAnimation().post(new Runnable() { // from class: com.bytedance.ttgame.sdk.module.ui.LifecycleDialog$2$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        LifecycleDialog.AnonymousClass2.this.m280xe53db2cd();
                    }
                });
                LifecycleDialog.sDialog = null;
            }
        }

        /* renamed from: lambda$onAnimationEnd$0$com-bytedance-ttgame-sdk-module-ui-LifecycleDialog$2  reason: not valid java name */
        public /* synthetic */ void m280xe53db2cd() {
            LifecycleDialog.super.dismiss();
        }
    }

    public View getContentViewForAnimation() {
        return this.contextViewForAnimation;
    }

    public void setContextViewForAnimation(View view) {
        this.contextViewForAnimation = view;
    }

    public boolean isDestroyed() {
        return this.mStatusDestroyed;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFullScreen() {
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}
