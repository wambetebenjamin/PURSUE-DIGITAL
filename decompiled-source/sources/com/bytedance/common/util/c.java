package com.bytedance.common.util;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Method;
/* compiled from: HoneyCombV11Compat.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static Method f5161a = null;

    /* renamed from: b  reason: collision with root package name */
    static Method f5162b = null;

    /* renamed from: c  reason: collision with root package name */
    static boolean f5163c = false;

    /* compiled from: HoneyCombV11Compat.java */
    /* loaded from: classes2.dex */
    static class a {
        a() {
        }

        public static Context getContext(AlertDialog.Builder builder) {
            return builder.getContext();
        }

        public static int getLargeMemoryClass(ActivityManager activityManager) {
            return activityManager.getLargeMemoryClass();
        }

        public static void setDisplayZoomControl(WebSettings webSettings, boolean z) {
            webSettings.setDisplayZoomControls(z);
        }

        public static void pauseWebView(WebView webView) {
            webView.onPause();
        }

        public static void resumeWebView(WebView webView) {
            webView.onResume();
        }
    }

    public static Context getContext(Context context, AlertDialog.Builder builder) {
        return Build.VERSION.SDK_INT >= 11 ? a.getContext(builder) : context;
    }

    public static void setDisplayZoomControl(WebSettings webSettings, boolean z) {
        if (Build.VERSION.SDK_INT >= 11) {
            a.setDisplayZoomControl(webSettings, z);
        }
    }

    public static void pauseWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            a.pauseWebView(webView);
            return;
        }
        a();
        Method method = f5161a;
        if (method != null) {
            try {
                Object[] objArr = null;
                method.invoke(webView, null);
            } catch (Exception unused) {
            }
        }
    }

    public static void resumeWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        if (Build.VERSION.SDK_INT >= 11) {
            a.resumeWebView(webView);
            return;
        }
        a();
        Method method = f5162b;
        if (method != null) {
            try {
                Object[] objArr = null;
                method.invoke(webView, null);
            } catch (Exception unused) {
            }
        }
    }

    private static void a() {
        if (f5163c) {
            return;
        }
        f5163c = true;
        try {
            Class[] clsArr = null;
            f5161a = WebView.class.getMethod("onPause", null);
            Class[] clsArr2 = null;
            f5162b = WebView.class.getMethod("onResume", null);
        } catch (Exception unused) {
        }
    }

    public static int getLargeMemoryClass(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 11) {
            try {
                return a.getLargeMemoryClass(activityManager);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }
}
