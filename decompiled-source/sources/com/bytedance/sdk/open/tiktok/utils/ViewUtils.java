package com.bytedance.sdk.open.tiktok.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
/* loaded from: classes3.dex */
public final class ViewUtils {
    public static void setTranslucentStatusBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = activity.getWindow();
            window.clearFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
            window.getDecorView().setSystemUiVisibility(1280);
            window.addFlags(Integer.MIN_VALUE);
            window.setStatusBarColor(0);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
        }
    }

    public static void setStatusBarColor(Activity activity, int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            activity.getWindow().setStatusBarColor(i2);
        } else if (Build.VERSION.SDK_INT >= 19) {
            activity.getWindow().addFlags(DownloadExpSwitchCode.BACK_BUGFIX_SIGBUS);
            ((ViewGroup) activity.getWindow().getDecorView()).addView(createStatusBarView(activity, i2));
            ViewGroup viewGroup = (ViewGroup) activity.findViewById(16908290);
            ViewGroup viewGroup2 = (ViewGroup) viewGroup.getChildAt(viewGroup.getChildCount() - 1);
            viewGroup2.setFitsSystemWindows(true);
            viewGroup2.setClipToPadding(true);
        }
    }

    private static View createStatusBarView(Activity activity, int i2) {
        View view = new View(activity);
        view.setLayoutParams(new LinearLayout.LayoutParams(-1, getStatusBarHeight(activity)));
        view.setBackgroundColor(i2);
        return view;
    }

    public static int getStatusBarHeight(Context context) {
        return context.getResources().getDimensionPixelSize(context.getResources().getIdentifier("status_bar_height", "dimen", "android"));
    }

    private ViewUtils() {
        throw new IllegalStateException("no instance.");
    }
}
