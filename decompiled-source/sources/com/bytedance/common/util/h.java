package com.bytedance.common.util;

import android.os.Build;
import android.webkit.WebView;
/* compiled from: KitKatV19Compat.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static a f5174a;

    /* compiled from: KitKatV19Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        public void setWebContentsDebuggingEnabled(boolean z) {
        }

        private a() {
        }
    }

    /* compiled from: KitKatV19Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.h.a
        public void setWebContentsDebuggingEnabled(boolean z) {
            try {
                WebView.setWebContentsDebuggingEnabled(z);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 19) {
            f5174a = new b();
        } else {
            f5174a = new a();
        }
    }

    public static void setWebContentsDebuggingEnabled(boolean z) {
        f5174a.setWebContentsDebuggingEnabled(z);
    }
}
