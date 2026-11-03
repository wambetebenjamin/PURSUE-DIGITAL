package com.bytedance.common.util;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
/* compiled from: JellyBeanMR1V16Compat.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private static a f5169a;

    /* compiled from: JellyBeanMR1V16Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        private a() {
        }

        public void setBackground(View view, Drawable drawable) {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* compiled from: JellyBeanMR1V16Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.e.a
        public void setBackground(View view, Drawable drawable) {
            view.setBackground(drawable);
        }
    }

    public static void setBackgroundForView(View view, Drawable drawable) {
        if (view == null) {
            return;
        }
        if (f5169a == null) {
            if (Build.VERSION.SDK_INT >= 16) {
                f5169a = new b();
            } else {
                f5169a = new a();
            }
        }
        f5169a.setBackground(view, drawable);
    }
}
