package com.bytedance.react.framework.floatingview;

import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.bytedance.react.framework.R;
import com.bytedance.react.framework.core.BRNManager;
import com.google.android.material.badge.BadgeDrawable;
import java.lang.ref.WeakReference;
/* loaded from: classes2.dex */
public class FloatingView implements IFloatingView {
    private static volatile FloatingView mInstance;
    private WeakReference<FrameLayout> mContainer;
    private FloatingMagnetView mEnFloatingView;
    private int mLayoutId = R.layout.en_floating_view;
    private int mIconRes = R.drawable.settings;
    private ViewGroup.LayoutParams mLayoutParams = getParams();

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView remove() {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.react.framework.floatingview.FloatingView.1
            @Override // java.lang.Runnable
            public void run() {
                if (FloatingView.this.mEnFloatingView == null) {
                    return;
                }
                if (ViewCompat.isAttachedToWindow(FloatingView.this.mEnFloatingView) && FloatingView.this.getContainer() != null) {
                    FloatingView.this.getContainer().removeView(FloatingView.this.mEnFloatingView);
                }
                FloatingView.this.mEnFloatingView = null;
            }
        });
        return this;
    }

    private void ensureFloatingView() {
        synchronized (this) {
            if (this.mEnFloatingView != null) {
                return;
            }
            EnFloatingView enFloatingView = new EnFloatingView(BRNManager.newInstance().getAppContext(), this.mLayoutId);
            this.mEnFloatingView = enFloatingView;
            enFloatingView.setLayoutParams(this.mLayoutParams);
            enFloatingView.setIconImage(this.mIconRes);
            addViewToWindow(enFloatingView);
        }
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView add() {
        ensureFloatingView();
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView attach(Window window) {
        attach(getActivityRoot(window));
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView attach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView;
        if (frameLayout == null || (floatingMagnetView = this.mEnFloatingView) == null) {
            this.mContainer = new WeakReference<>(frameLayout);
            return this;
        } else if (floatingMagnetView.getParent() == frameLayout) {
            return this;
        } else {
            if (this.mEnFloatingView.getParent() != null) {
                ((ViewGroup) this.mEnFloatingView.getParent()).removeView(this.mEnFloatingView);
            }
            this.mContainer = new WeakReference<>(frameLayout);
            frameLayout.addView(this.mEnFloatingView);
            return this;
        }
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView detach(Window window) {
        detach(getActivityRoot(window));
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView detach(FrameLayout frameLayout) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null && frameLayout != null && ViewCompat.isAttachedToWindow(floatingMagnetView)) {
            frameLayout.removeView(this.mEnFloatingView);
        }
        if (getContainer() == frameLayout) {
            this.mContainer = null;
        }
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingMagnetView getView() {
        return this.mEnFloatingView;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView icon(int i2) {
        this.mIconRes = i2;
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView customView(FloatingMagnetView floatingMagnetView) {
        this.mEnFloatingView = floatingMagnetView;
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView customView(int i2) {
        this.mLayoutId = i2;
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView layoutParams(ViewGroup.LayoutParams layoutParams) {
        this.mLayoutParams = layoutParams;
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setLayoutParams(layoutParams);
        }
        return this;
    }

    @Override // com.bytedance.react.framework.floatingview.IFloatingView
    public FloatingView listener(MagnetViewListener magnetViewListener) {
        FloatingMagnetView floatingMagnetView = this.mEnFloatingView;
        if (floatingMagnetView != null) {
            floatingMagnetView.setMagnetViewListener(magnetViewListener);
        }
        return this;
    }

    private void addViewToWindow(View view) {
        if (getContainer() == null) {
            return;
        }
        getContainer().addView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FrameLayout getContainer() {
        WeakReference<FrameLayout> weakReference = this.mContainer;
        if (weakReference == null) {
            return null;
        }
        return weakReference.get();
    }

    private FrameLayout.LayoutParams getParams() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = BadgeDrawable.BOTTOM_START;
        layoutParams.setMargins(13, layoutParams.topMargin, layoutParams.rightMargin, 500);
        return layoutParams;
    }

    private FrameLayout getActivityRoot(Window window) {
        if (window == null) {
            return null;
        }
        try {
            return (FrameLayout) window.getDecorView().findViewById(16908290);
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }
}
