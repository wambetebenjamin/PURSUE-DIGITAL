package com.bytedance.common.util;

import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
/* compiled from: JellyBeanMR1V17Compat.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f5170a = false;

    /* renamed from: b  reason: collision with root package name */
    private static final long f5171b = 1000000;

    /* renamed from: c  reason: collision with root package name */
    private static a f5172c;

    /* compiled from: JellyBeanMR1V17Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        public String getWebViewDefaultUserAgent(Context context) {
            return null;
        }

        public void setMediaPlaybackRequiresUserGesture(WebSettings webSettings, boolean z) {
        }

        private a() {
        }

        public float getWebViewScale(WebView webView) {
            if (webView == null) {
                return 1.0f;
            }
            try {
                return webView.getScale();
            } catch (Exception e2) {
                e2.printStackTrace();
                return 1.0f;
            }
        }
    }

    /* compiled from: JellyBeanMR1V17Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.f.a
        public void setMediaPlaybackRequiresUserGesture(WebSettings webSettings, boolean z) {
            try {
                webSettings.setMediaPlaybackRequiresUserGesture(z);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }

        @Override // com.bytedance.common.util.f.a
        public float getWebViewScale(WebView webView) {
            if (webView == null) {
                return 1.0f;
            }
            try {
                return webView.getScale();
            } catch (Exception e2) {
                e2.printStackTrace();
                return 1.0f;
            }
        }

        @Override // com.bytedance.common.util.f.a
        public String getWebViewDefaultUserAgent(Context context) {
            try {
                return WebSettings.getDefaultUserAgent(context);
            } catch (Throwable th) {
                th.printStackTrace();
                return null;
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 17) {
            f5172c = new b();
        } else {
            f5172c = new a();
        }
    }

    public static void setMediaPlaybackRequiresUserGesture(WebSettings webSettings, boolean z) {
        f5172c.setMediaPlaybackRequiresUserGesture(webSettings, z);
    }

    public static float getWebViewScale(WebView webView) {
        return f5172c.getWebViewScale(webView);
    }

    public static String getWebViewDefaultUserAgent(Context context) {
        return f5172c.getWebViewDefaultUserAgent(context);
    }
}
