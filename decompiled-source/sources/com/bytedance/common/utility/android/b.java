package com.bytedance.common.utility.android;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.text.ClipboardManager;
/* compiled from: ClipboardCompat.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final InterfaceC0073b f5183a;

    /* compiled from: ClipboardCompat.java */
    /* renamed from: com.bytedance.common.utility.android.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    interface InterfaceC0073b {
        void setText(Context context, CharSequence charSequence, CharSequence charSequence2);
    }

    /* compiled from: ClipboardCompat.java */
    /* loaded from: classes2.dex */
    static class a implements InterfaceC0073b {
        a() {
        }

        @Override // com.bytedance.common.utility.android.b.InterfaceC0073b
        public void setText(Context context, CharSequence charSequence, CharSequence charSequence2) {
            ((ClipboardManager) context.getSystemService(com.bytedance.ug.sdk.share.impl.network.request.a.CLIPBOARD_TOKEN)).setText(charSequence2);
        }
    }

    /* compiled from: ClipboardCompat.java */
    /* loaded from: classes2.dex */
    static class c implements InterfaceC0073b {
        c() {
        }

        @Override // com.bytedance.common.utility.android.b.InterfaceC0073b
        public void setText(Context context, CharSequence charSequence, CharSequence charSequence2) {
            ((android.content.ClipboardManager) context.getSystemService(com.bytedance.ug.sdk.share.impl.network.request.a.CLIPBOARD_TOKEN)).setPrimaryClip(ClipData.newPlainText(charSequence, charSequence2));
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 11) {
            f5183a = new c();
        } else {
            f5183a = new a();
        }
    }

    public static void setText(Context context, CharSequence charSequence, CharSequence charSequence2) {
        if (context != null && charSequence != null && charSequence2 != null) {
            try {
                f5183a.setText(context, charSequence, charSequence2);
            } catch (Throwable unused) {
            }
        }
    }
}
