package com.bytedance.common.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.Display;
import android.view.WindowManager;
/* compiled from: HoneyCombMR2V13Compat.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    static final a f5160a;

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: HoneyCombMR2V13Compat.java */
    /* loaded from: classes2.dex */
    public static class a {
        private a() {
        }

        public void getDisplaySize(Display display, Point point) {
            point.x = display.getWidth();
            point.y = display.getHeight();
        }
    }

    /* compiled from: HoneyCombMR2V13Compat.java */
    /* renamed from: com.bytedance.common.util.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0071b extends a {
        private C0071b() {
            super();
        }

        @Override // com.bytedance.common.util.b.a
        public void getDisplaySize(Display display, Point point) {
            display.getSize(point);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 13) {
            f5160a = new C0071b();
        } else {
            f5160a = new a();
        }
    }

    public static void getDisplaySize(Display display, Point point) {
        f5160a.getDisplaySize(display, point);
    }

    public static int getDisplayWidth(Display display) {
        Point point = new Point();
        getDisplaySize(display, point);
        return point.x;
    }

    public static void getDisplaySize(Context context, Point point) {
        getDisplaySize(((WindowManager) context.getSystemService("window")).getDefaultDisplay(), point);
    }
}
