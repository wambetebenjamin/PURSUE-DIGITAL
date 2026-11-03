package com.bytedance.gameprotect;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.bytedance.gameprotect.WafHttpSecurity;
import com.facebook.react.uimanager.ViewProps;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class WafHttpSecurity {
    private static final String DISPLAY_KEY = "X-Waf-Sdk-Displaymode";
    private static final String DYN_URI = "/waf/sdk/v1/dytoken?token=";
    private static final String DY_TOKEN = "dytoken";
    private static final String HEADER_FLAG = "X-Waf-Client-Type";
    private static final String TOKEN_KEY = "X-Waf-Sdk-Token";
    private static final String TYPE_KEY = "X-Waf-Sdk-Type";
    private static final String WAF_URI = "/waf/sdk/v1/verification?token=";
    private static final Map<String, Map<String, String>> HostHeaders = new HashMap();
    private static final Map<String, WebView> TokenWebViewList = new HashMap();
    private static long ResponseSuccessTime = 0;

    /* loaded from: classes2.dex */
    public interface HandleResult {
        void onVerificationCompleted(String str);

        void onVerificationFailed(String str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class a extends WebViewClient {
        a() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            webView.loadUrl(str);
            return true;
        }
    }

    private static void addHostHeaders(String str, String str2, String str3) {
        Map<String, Map<String, String>> map = HostHeaders;
        synchronized (map) {
            if (map.containsKey(str)) {
                map.get(str).put(str2, str3);
            } else {
                HashMap hashMap = new HashMap();
                hashMap.put(str2, str3);
                map.put(str, hashMap);
            }
        }
    }

    private static boolean addHostView(String str, WebView webView) {
        Map<String, WebView> map = TokenWebViewList;
        synchronized (map) {
            if (map.containsKey(str)) {
                return false;
            }
            map.put(str, webView);
            return true;
        }
    }

    private static WebView createWebViewObject(Context context, String str, Map<String, String> map) {
        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowUniversalAccessFromFileURLs(true);
        if (str != null) {
            settings.setUserAgentString(str);
        }
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        if (Build.VERSION.SDK_INT >= 21) {
            cookieManager.setAcceptThirdPartyCookies(webView, true);
        }
        webView.setVisibility(8);
        webView.setWebViewClient(new a());
        return webView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void destoryWebView(final WebView webView) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.gameprotect.WafHttpSecurity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                WafHttpSecurity.lambda$destoryWebView$2(webView);
            }
        });
    }

    private static Map<String, String> getHostHeaders(String str) {
        Map<String, Map<String, String>> map = HostHeaders;
        synchronized (map) {
            if (map.containsKey(str)) {
                return map.get(str);
            }
            return null;
        }
    }

    public static Map<String, String> getRequestHeaders(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(HEADER_FLAG, "app");
        Map<String, String> hostHeaders = getHostHeaders(str);
        if (hostHeaders != null) {
            hashMap.putAll(hostHeaders);
        }
        return hashMap;
    }

    public static void handleResponseHeaders(final Activity activity, final String str, final String str2, final Map<String, String> map, final HandleResult handleResult) {
        if (map.get(TOKEN_KEY) == null) {
            return;
        }
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            handleResponseHeadersInUI(activity, str, str2, map, handleResult);
        } else {
            new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.bytedance.gameprotect.WafHttpSecurity$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    WafHttpSecurity.handleResponseHeadersInUI(activity, str, str2, map, handleResult);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void handleResponseHeadersInUI(Activity activity, String str, String str2, Map<String, String> map, HandleResult handleResult) {
        String str3 = map.get(TYPE_KEY);
        if (str3 == null) {
            handleVerification(activity, str, map, handleResult);
        } else if (DY_TOKEN.equalsIgnoreCase(str3)) {
            handleTokenBackgroud(activity, str, str2, map);
        }
    }

    private static void handleTokenBackgroud(Activity activity, final String str, String str2, Map<String, String> map) {
        if (hostViewExist(str)) {
            return;
        }
        WebView createWebViewObject = createWebViewObject(activity.getApplicationContext(), str2, map);
        if (addHostView(str, createWebViewObject)) {
            createWebViewObject.addJavascriptInterface(new Object() { // from class: com.bytedance.gameprotect.WafHttpSecurity.2
                @JavascriptInterface
                public void postMessage(String str3) {
                    WafHttpSecurity.parseWafJsonMessage(str, str3);
                }
            }, "ReactNativeWebView");
            createWebViewObject.loadUrl(str + DYN_URI + map.get(TOKEN_KEY));
        }
    }

    private static synchronized void handleVerification(Activity activity, final String str, Map<String, String> map, final HandleResult handleResult) {
        synchronized (WafHttpSecurity.class) {
            if (System.currentTimeMillis() - ResponseSuccessTime < 5000) {
                handleResult.onVerificationFailed("resend");
                return;
            }
            ResponseSuccessTime = System.currentTimeMillis();
            boolean equalsIgnoreCase = ViewProps.HIDDEN.equalsIgnoreCase(map.get(DISPLAY_KEY));
            final WebView createWebViewObject = createWebViewObject(activity, null, map);
            final Runnable runnable = new Runnable() { // from class: com.bytedance.gameprotect.WafHttpSecurity$$ExternalSyntheticLambda2
                @Override // java.lang.Runnable
                public final void run() {
                    WafHttpSecurity.lambda$handleVerification$1(WafHttpSecurity.HandleResult.this, createWebViewObject);
                }
            };
            final Handler handler = new Handler();
            handler.postDelayed(runnable, 60000L);
            if (!equalsIgnoreCase) {
                createWebViewObject.setVisibility(0);
            }
            createWebViewObject.addJavascriptInterface(new Object() { // from class: com.bytedance.gameprotect.WafHttpSecurity.3
                @JavascriptInterface
                public void postMessage(String str2) {
                    String parseWafJsonMessage = WafHttpSecurity.parseWafJsonMessage(str, str2);
                    if (createWebViewObject.getVisibility() == 0) {
                        long unused = WafHttpSecurity.ResponseSuccessTime = System.currentTimeMillis();
                    }
                    HandleResult handleResult2 = handleResult;
                    if (parseWafJsonMessage == null) {
                        parseWafJsonMessage = "resend";
                    }
                    handleResult2.onVerificationCompleted(parseWafJsonMessage);
                    handler.removeCallbacks(runnable);
                    WafHttpSecurity.destoryWebView(createWebViewObject);
                }
            }, "ReactNativeWebView");
            createWebViewObject.loadUrl(str + WAF_URI + map.get(TOKEN_KEY));
            activity.addContentView(createWebViewObject, new RelativeLayout.LayoutParams(-1, -1));
        }
    }

    private static boolean hostViewExist(String str) {
        boolean containsKey;
        Map<String, WebView> map = TokenWebViewList;
        synchronized (map) {
            containsKey = map.containsKey(str);
        }
        return containsKey;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$destoryWebView$2(WebView webView) {
        webView.setVisibility(8);
        webView.removeAllViews();
        webView.destroy();
        ViewGroup viewGroup = (ViewGroup) webView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(webView);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$handleVerification$1(HandleResult handleResult, WebView webView) {
        handleResult.onVerificationFailed("timeout");
        destoryWebView(webView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String parseWafJsonMessage(String str, String str2) {
        try {
            JSONObject jSONObject = new JSONObject(str2);
            if (jSONObject.has("setheaders")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("setheaders");
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    addHostHeaders(str, next, jSONObject2.get(next).toString());
                }
            }
            if (jSONObject.has("action")) {
                return jSONObject.getString("action");
            }
        } catch (JSONException unused) {
        }
        return null;
    }
}
