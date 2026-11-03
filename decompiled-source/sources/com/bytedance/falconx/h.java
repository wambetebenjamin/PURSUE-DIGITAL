package com.bytedance.falconx;

import android.webkit.WebView;
import com.bytedance.falconx.statistic.InterceptorModel;
/* compiled from: WebOfflineGlobalMonitor.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static a f7388a;

    /* compiled from: WebOfflineGlobalMonitor.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(WebView webView, InterceptorModel interceptorModel, boolean z);
    }

    public static void a(a aVar) {
        f7388a = aVar;
    }

    public static void a(WebView webView, InterceptorModel interceptorModel, boolean z) {
        a aVar = f7388a;
        if (aVar != null) {
            aVar.a(webView, interceptorModel, z);
        }
    }
}
