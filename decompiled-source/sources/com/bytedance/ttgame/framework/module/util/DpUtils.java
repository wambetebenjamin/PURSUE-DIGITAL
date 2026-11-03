package com.bytedance.ttgame.framework.module.util;

import android.content.Context;
import android.view.View;
/* loaded from: classes3.dex */
public class DpUtils {
    public static int dip2px(Context context, float f2) {
        if (context != null) {
            return (int) ((f2 * context.getResources().getDisplayMetrics().density) + 0.5f);
        }
        return 0;
    }

    public static int unDisplayViewHeight(View view) {
        view.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        return view.getMeasuredHeight();
    }
}
