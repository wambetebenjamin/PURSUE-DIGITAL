package com.bytedance.common.util;

import android.app.ActivityManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
/* compiled from: JellyBeanV16Compat.java */
/* loaded from: classes2.dex */
public class g {

    /* renamed from: a  reason: collision with root package name */
    private static a f5173a;

    /* compiled from: JellyBeanV16Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        public long getTotalMem(ActivityManager.MemoryInfo memoryInfo) {
            return 0L;
        }

        private a() {
        }

        public void setViewBackground(View view, Drawable drawable) {
            view.setBackgroundDrawable(drawable);
        }
    }

    /* compiled from: JellyBeanV16Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.g.a
        public void setViewBackground(View view, Drawable drawable) {
            try {
                view.setBackground(drawable);
            } catch (Throwable unused) {
                view.setBackgroundDrawable(drawable);
            }
        }

        @Override // com.bytedance.common.util.g.a
        public long getTotalMem(ActivityManager.MemoryInfo memoryInfo) {
            return memoryInfo.totalMem;
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 16) {
            f5173a = new b();
        } else {
            f5173a = new a();
        }
    }

    public static void setViewBackground(View view, Drawable drawable) {
        f5173a.setViewBackground(view, drawable);
    }

    public static long getTotalMem(ActivityManager.MemoryInfo memoryInfo) {
        return f5173a.getTotalMem(memoryInfo);
    }
}
