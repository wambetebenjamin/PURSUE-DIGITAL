package com.unity3d.player;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
/* renamed from: com.unity3d.player.c  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
abstract class AbstractC0399c {

    /* renamed from: a  reason: collision with root package name */
    private static final int[] f18950a = {16842874, 16843401, 16842873, 16843400};

    /* renamed from: b  reason: collision with root package name */
    private static ProgressBar f18951b = null;

    /* renamed from: c  reason: collision with root package name */
    private static View f18952c = null;

    /* renamed from: d  reason: collision with root package name */
    private static int f18953d = -1;

    public static void a() {
        ProgressBar progressBar = f18951b;
        if (progressBar != null) {
            if (progressBar != null && f18952c != null) {
                ViewGroup viewGroup = (ViewGroup) progressBar.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(f18951b);
                    viewGroup.removeView(f18952c);
                }
                f18951b = null;
                f18952c = null;
            }
            f18953d = -1;
        }
    }

    public static void a(Activity activity, UnityPlayer unityPlayer, int i2) {
        if (i2 >= 0) {
            int[] iArr = f18950a;
            if (i2 >= 4) {
                return;
            }
            if (unityPlayer == null) {
                throw new IllegalArgumentException("ViewGroup cannot be null.");
            }
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                return;
            }
            if (f18951b != null) {
                if (f18953d == i2) {
                    return;
                }
                a();
            }
            f18953d = i2;
            ProgressBar progressBar = new ProgressBar(activity, null, iArr[i2]);
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            progressBar.setLayoutParams(layoutParams);
            progressBar.setBackground(new ColorDrawable(0));
            progressBar.setIndeterminate(true);
            f18951b = progressBar;
            View view = new View(activity);
            view.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
            view.setBackgroundColor(0);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.setOnTouchListener(new View$OnTouchListenerC0395a());
            view.setOnKeyListener(new View$OnKeyListenerC0397b());
            f18952c = view;
            unityPlayer.addView(view);
            unityPlayer.addView(f18951b);
        }
    }
}
