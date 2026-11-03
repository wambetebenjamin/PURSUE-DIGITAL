package com.bytedance.common.util;

import android.os.Build;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
/* compiled from: IceCreamSandwichV14Compat.java */
/* loaded from: classes2.dex */
public class d {
    public static final int FONT_SIZE_BIG = 3;
    public static final int FONT_SIZE_LARGE = 4;
    public static final int FONT_SIZE_NORMAL = 2;
    public static final int FONT_SIZE_SMALL = 1;
    public static final int UIOPTION_SPLIT_ACTION_BAR_WHEN_NARROW = 1;

    /* renamed from: a  reason: collision with root package name */
    static final a f5164a;

    /* renamed from: b  reason: collision with root package name */
    private static final int f5165b = 100;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5166c = 75;

    /* renamed from: d  reason: collision with root package name */
    private static final int f5167d = 125;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5168e = 150;

    /* compiled from: IceCreamSandwichV14Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        public void setUiOptions(Window window, int i2) {
        }

        public void setUiOptions(Window window, int i2, int i3) {
        }

        private a() {
        }

        public void setWebViewTextSize(WebView webView, int i2) {
            WebSettings.TextSize textSize = WebSettings.TextSize.NORMAL;
            if (i2 == 3) {
                textSize = WebSettings.TextSize.LARGER;
            } else if (i2 == 1) {
                textSize = WebSettings.TextSize.SMALLER;
            } else if (i2 == 4) {
                textSize = WebSettings.TextSize.LARGEST;
            }
            webView.getSettings().setTextSize(textSize);
        }
    }

    /* compiled from: IceCreamSandwichV14Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.d.a
        public void setWebViewTextSize(WebView webView, int i2) {
            int i3 = i2 == 1 ? 75 : 100;
            if (i2 == 3) {
                i3 = 125;
            }
            if (i2 == 4) {
                i3 = 150;
            }
            webView.getSettings().setTextZoom(i3);
        }

        @Override // com.bytedance.common.util.d.a
        public void setUiOptions(Window window, int i2) {
            window.setUiOptions(i2);
        }

        @Override // com.bytedance.common.util.d.a
        public void setUiOptions(Window window, int i2, int i3) {
            window.setUiOptions(i2, i3);
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 14) {
            f5164a = new b();
        } else {
            f5164a = new a();
        }
    }

    public static void setWebViewTextSize(WebView webView, int i2) {
        f5164a.setWebViewTextSize(webView, i2);
    }

    public static void setUiOptions(Window window, int i2, int i3) {
        f5164a.setUiOptions(window, i2, i3);
    }

    public static void setUiOptions(Window window, int i2) {
        f5164a.setUiOptions(window, i2);
    }
}
