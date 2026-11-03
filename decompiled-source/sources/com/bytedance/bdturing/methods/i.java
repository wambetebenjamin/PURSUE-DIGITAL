package com.bytedance.bdturing.methods;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.bytedance.bdturing.h;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: JsBridgeModule.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: b  reason: collision with root package name */
    private static final String f4258b = "JsBridgeModule";

    /* renamed from: c  reason: collision with root package name */
    private static final String f4259c = "__callback_id";

    /* renamed from: d  reason: collision with root package name */
    private static final String f4260d = "__params";

    /* renamed from: a  reason: collision with root package name */
    private WebView f4261a;

    /* renamed from: f  reason: collision with root package name */
    private Handler f4262f;

    /* compiled from: JsBridgeModule.java */
    /* loaded from: classes.dex */
    private class a {

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.bdturing.methods.a f4269b;

        public a(com.bytedance.bdturing.methods.a aVar) {
            this.f4269b = aVar;
        }

        @JavascriptInterface
        public void callMethodParams(final String str) {
            h.d(i.f4258b, "JS called method ======= callMethodParams(" + str + ")");
            if (i.this.f4262f != null) {
                i.this.f4262f.post(new Runnable() { // from class: com.bytedance.bdturing.methods.i.a.1
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.f4269b.b(new l(i.this, str));
                    }
                });
            } else {
                h.i(i.f4258b, "uihandler is null");
            }
        }

        @JavascriptInterface
        public void onMethodParams(String str) {
            h.d(i.f4258b, "JS called method ======= onMethodParams(" + str + ")");
            try {
                new JSONObject(str).getString(i.f4259c);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }

        @JavascriptInterface
        public void offMethodParams(String str) {
            h.d(i.f4258b, "JS called method ======= offMethodParams(" + str + ")");
        }
    }

    /* compiled from: JsBridgeModule.java */
    /* loaded from: classes.dex */
    public class b {

        /* renamed from: b  reason: collision with root package name */
        private com.bytedance.bdturing.methods.a f4273b;

        public b(com.bytedance.bdturing.methods.a aVar) {
            this.f4273b = aVar;
        }

        @JavascriptInterface
        public String _invokeMethod(final String str) {
            h.d(i.f4258b, "JS called method ======= _invokeMethod(" + str + ")");
            if (i.this.f4262f != null) {
                i.this.f4262f.post(new Runnable() { // from class: com.bytedance.bdturing.methods.i.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        b.this.f4273b.b(new l(i.this, str));
                    }
                });
                return null;
            }
            h.i(i.f4258b, "uihandler is null");
            return null;
        }
    }

    public i(com.bytedance.bdturing.methods.a aVar, WebView webView) {
        this.f4262f = null;
        this.f4261a = webView;
        if (webView == null) {
            return;
        }
        webView.getSettings().setJavaScriptEnabled(true);
        this.f4261a.addJavascriptInterface(new a(aVar), "androidJsBridge");
        this.f4262f = new Handler(Looper.getMainLooper());
    }

    public void callJsCode(final String str) {
        Handler handler;
        if (str == null || this.f4261a == null || (handler = this.f4262f) == null) {
            return;
        }
        handler.post(new Runnable() { // from class: com.bytedance.bdturing.methods.i.1

            /* renamed from: a  reason: collision with root package name */
            WebView f4263a;

            {
                this.f4263a = i.this.f4261a;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT >= 19) {
                    this.f4263a.evaluateJavascript("javascript:window.Native2JSBridge._handleMessageFromApp(" + str + ")", null);
                    h.w(i.f4258b, "callJsCode ====== " + str);
                    return;
                }
                this.f4263a.loadUrl("javascript:window.Native2JSBridge._handleMessageFromApp('" + str + "')");
            }
        });
        h.d(f4258b, "callJsCode ====== " + str);
    }

    public void clearJsBridgeResources() {
        if (this.f4261a == null) {
            return;
        }
        this.f4262f.post(new Runnable() { // from class: com.bytedance.bdturing.methods.i.2

            /* renamed from: a  reason: collision with root package name */
            WebView f4266a;

            {
                this.f4266a = i.this.f4261a;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.f4266a.stopLoading();
                this.f4266a.loadUrl("about:blank");
                this.f4266a.clearCache(true);
                this.f4266a.clearHistory();
                ViewParent parent = this.f4266a.getParent();
                if (parent != null && (parent instanceof ViewGroup)) {
                    ((ViewGroup) parent).removeView(this.f4266a);
                }
                this.f4266a.destroy();
            }
        });
        this.f4262f = null;
        this.f4261a = null;
    }
}
