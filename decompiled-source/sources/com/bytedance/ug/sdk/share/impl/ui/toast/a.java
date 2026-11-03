package com.bytedance.ug.sdk.share.impl.ui.toast;

import android.content.Context;
import android.widget.Toast;
import com.bytedance.ug.sdk.share.impl.ui.utils.b;
/* compiled from: ToastUtils.java */
/* loaded from: classes8.dex */
public class a {
    public static void showToast(Context context, int i2) {
        if (context == null) {
            return;
        }
        Toast.makeText(context.getApplicationContext(), i2, 0).show();
    }

    public static boolean showToastWithIcon(Context context, int i2, int i3) {
        if (context == null) {
            return false;
        }
        b.displayToastWithIcon(context.getApplicationContext(), i2, i3);
        return true;
    }
}
