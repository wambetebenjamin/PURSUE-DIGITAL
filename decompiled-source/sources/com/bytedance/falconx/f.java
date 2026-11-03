package com.bytedance.falconx;

import android.os.SystemClock;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.bytedance.falconx.statistic.InterceptorModel;
import java.util.List;
/* compiled from: WebOffline.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7364a = "WebOffline-falcon";

    /* renamed from: b  reason: collision with root package name */
    private g f7365b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f7366c = true;

    /* renamed from: d  reason: collision with root package name */
    private d f7367d;

    public f(g gVar) {
        if (gVar == null) {
            throw new IllegalArgumentException("config == null");
        }
        this.f7365b = gVar;
        com.bytedance.falconx.statistic.c.a().a(this.f7365b);
        this.f7367d = new d(this.f7365b);
        com.bytedance.falconx.debug.a.a(this.f7365b);
    }

    public WebResourceResponse a(WebView webView, String str) {
        if (a()) {
            try {
                List<e> h2 = this.f7365b.h();
                if (h2 != null && !h2.isEmpty()) {
                    InterceptorModel interceptorModel = new InterceptorModel();
                    for (e eVar : h2) {
                        interceptorModel.startTime = Long.valueOf(SystemClock.uptimeMillis());
                        WebResourceResponse a2 = eVar.a(webView, str);
                        if (a2 != null) {
                            interceptorModel.loadFinish(true);
                            interceptorModel.url = str;
                            interceptorModel.mimeType = a2.getMimeType();
                            h.a(webView, interceptorModel, false);
                            return a2;
                        }
                    }
                }
                return this.f7367d.a(webView, str);
            } catch (Throwable th) {
                com.bytedance.geckox.logger.b.w(f7364a, "shouldInterceptRequest:", th);
                return null;
            }
        }
        return null;
    }

    public boolean a() {
        return this.f7366c;
    }

    public void a(boolean z) {
        this.f7366c = z;
    }

    public void b() {
        this.f7367d.a();
        com.bytedance.falconx.debug.a.c();
    }
}
