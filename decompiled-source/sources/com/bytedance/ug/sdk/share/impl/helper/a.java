package com.bytedance.ug.sdk.share.impl.helper;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Looper;
import com.bytedance.ug.sdk.share.impl.utils.j;
/* compiled from: ClipboardHelper.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15727a = "ClipboardHelper";

    public void setText(Context context, CharSequence charSequence, CharSequence charSequence2) {
        ClipboardManager a2 = a(context);
        if (a2 != null) {
            a2.setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
        }
    }

    public String getClipBoardText(Context context) {
        CharSequence text;
        ClipboardManager a2 = a(context);
        if (a2 == null) {
            return "";
        }
        try {
            if (a2.hasPrimaryClip() && (a2.getPrimaryClipDescription().hasMimeType("text/plain") || a2.getPrimaryClipDescription().hasMimeType("text/html"))) {
                ClipData.Item itemAt = a2.getPrimaryClip().getItemAt(0);
                return (itemAt.getText() == null || (text = itemAt.getText()) == null) ? "" : text.toString();
            }
        } catch (Throwable th) {
            j.e(th.toString());
        }
        return "";
    }

    private ClipboardManager a(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return (ClipboardManager) context.getSystemService(com.bytedance.ug.sdk.share.impl.network.request.a.CLIPBOARD_TOKEN);
        } catch (Throwable unused) {
            j.d(f15727a, "Can't create handler inside thread that has not called Looper.prepare() ");
            if (Looper.myLooper() == null) {
                Looper.prepare();
                return (ClipboardManager) context.getSystemService(com.bytedance.ug.sdk.share.impl.network.request.a.CLIPBOARD_TOKEN);
            }
            return null;
        }
    }
}
