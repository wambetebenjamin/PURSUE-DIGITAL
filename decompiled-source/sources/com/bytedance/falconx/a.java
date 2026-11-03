package com.bytedance.falconx;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Pattern;
/* compiled from: BaseRequestIntercept.java */
/* loaded from: classes2.dex */
public abstract class a implements e {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7326a = "BaseRequestIntercept";

    protected abstract List<String> a();

    protected abstract InputStream b(String str);

    protected String b() {
        return "";
    }

    @Override // com.bytedance.falconx.e
    public WebResourceResponse a(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return c(str);
    }

    private WebResourceResponse c(String str) {
        WebResourceResponse a2 = a(str) ? com.bytedance.falconx.utils.c.a(com.bytedance.falconx.utils.b.a(str), b(), b(str)) : null;
        Log.e(f7326a, "getResponse response = " + a2);
        return a2;
    }

    protected boolean a(String str) {
        List<String> a2 = a();
        if (a2 == null || a2.isEmpty()) {
            return false;
        }
        for (String str2 : a2) {
            Log.e(f7326a, "regexStr = " + str2);
            if (!TextUtils.isEmpty(str2)) {
                return Pattern.compile(str2).matcher(str).find();
            }
        }
        return false;
    }
}
