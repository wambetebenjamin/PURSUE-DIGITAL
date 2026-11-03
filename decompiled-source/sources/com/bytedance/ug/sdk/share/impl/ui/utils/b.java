package com.bytedance.ug.sdk.share.impl.ui.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import com.bytedance.ug.sdk.share.impl.utils.j;
/* compiled from: UIUtils.java */
/* loaded from: classes8.dex */
public class b {
    private static boolean a(int i2) {
        return i2 == 0 || i2 == 8 || i2 == 4;
    }

    public static final void setViewVisibility(View view, int i2) {
        if (view == null || view.getVisibility() == i2 || !a(i2)) {
            return;
        }
        view.setVisibility(i2);
    }

    public static void setViewBackgroundWithPadding(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        view.setBackgroundDrawable(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static void displayToastWithIcon(Context context, int i2, int i3) {
        a(context, i2, context.getString(i3));
    }

    private static void a(Context context, int i2, String str) {
        b(context, i2, str, 0, 17);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(final Context context, final int i2, final String str, final int i3, final int i4) {
        if (context == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (!a()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.ug.sdk.share.impl.ui.utils.b.1
                @Override // java.lang.Runnable
                public void run() {
                    b.b(context, i2, str, i3, i4);
                }
            });
            return;
        }
        try {
            Toast makeText = Toast.makeText(context, str, i3);
            if (makeText != null) {
                makeText.setGravity(i4, 0, 0);
                makeText.show();
            }
        } catch (Exception e2) {
            j.e(e2.toString());
        }
    }

    private static boolean a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static float dip2Px(Context context, float f2) {
        return (f2 * context.getResources().getDisplayMetrics().density) + 0.5f;
    }
}
