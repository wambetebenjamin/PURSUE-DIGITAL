package com.bytedance.common.util;

import android.os.Build;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
/* compiled from: LollipopV21Compat.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static a f5175a;

    /* compiled from: LollipopV21Compat.java */
    /* loaded from: classes2.dex */
    private static class a {
        public void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        }

        public void setMixedContentMode(WebSettings webSettings, int i2) {
        }

        private a() {
        }
    }

    /* compiled from: LollipopV21Compat.java */
    /* loaded from: classes2.dex */
    private static class b extends a {
        private b() {
            super();
        }

        @Override // com.bytedance.common.util.i.a
        public void setMixedContentMode(WebSettings webSettings, int i2) {
            if (webSettings != null) {
                try {
                    webSettings.setMixedContentMode(i2);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        @Override // com.bytedance.common.util.i.a
        public void setAcceptThirdPartyCookies(WebView webView, boolean z) {
            if (webView != null) {
                try {
                    CookieManager.getInstance().setAcceptThirdPartyCookies(webView, z);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    static {
        if (Build.VERSION.SDK_INT >= 21) {
            f5175a = new b();
        } else {
            f5175a = new a();
        }
    }

    public static void setMixedContentMode(WebSettings webSettings, int i2) {
        f5175a.setMixedContentMode(webSettings, i2);
    }

    public static void setAcceptThirdPartyCookies(WebView webView, boolean z) {
        f5175a.setAcceptThirdPartyCookies(webView, z);
    }
}
