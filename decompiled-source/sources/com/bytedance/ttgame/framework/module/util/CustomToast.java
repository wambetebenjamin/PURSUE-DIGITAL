package com.bytedance.ttgame.framework.module.util;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bytedance.ttgame.gsdk_base_utils.R;
/* loaded from: classes3.dex */
public class CustomToast {
    public static void showToast(Context context, int i2) {
        showToast(context, context.getResources().getText(i2));
    }

    public static void showToast(Context context, CharSequence charSequence) {
        if (context == null || TextUtils.isEmpty(charSequence)) {
            return;
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_custom_toast, (ViewGroup) null);
        setShowAnim(inflate);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_custom_toast);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        if (charSequence.length() > 16) {
            layoutParams.width = DpUtils.dip2px(context, 248.0f);
            layoutParams.height = -2;
        }
        textView.setGravity(17);
        textView.setLayoutParams(layoutParams);
        textView.setText(charSequence);
        Toast toast = new Toast(context);
        toast.setGravity(17, 0, 0);
        toast.setDuration(0);
        toast.setView(inflate);
        toast.show();
    }

    private static void setShowAnim(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f);
        ofFloat.setDuration(100L);
        ofFloat.start();
    }
}
