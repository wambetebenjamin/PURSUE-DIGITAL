package com.bytedance.react.framework.floatingview;

import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
/* loaded from: classes2.dex */
public interface IFloatingView {
    FloatingView add();

    FloatingView attach(Window window);

    FloatingView attach(FrameLayout frameLayout);

    FloatingView customView(int i2);

    FloatingView customView(FloatingMagnetView floatingMagnetView);

    FloatingView detach(Window window);

    FloatingView detach(FrameLayout frameLayout);

    FloatingMagnetView getView();

    FloatingView icon(int i2);

    FloatingView layoutParams(ViewGroup.LayoutParams layoutParams);

    FloatingView listener(MagnetViewListener magnetViewListener);

    FloatingView remove();
}
