package com.bytedance.falconx;

import android.text.TextUtils;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.File;
/* compiled from: CommercializationPreload.java */
/* loaded from: classes2.dex */
public class c implements e {

    /* renamed from: a  reason: collision with root package name */
    private File f7334a;

    /* renamed from: b  reason: collision with root package name */
    private String f7335b;

    /* renamed from: c  reason: collision with root package name */
    private String f7336c;

    public c(File file, String str, String str2) {
        this.f7334a = file;
        this.f7335b = str;
        this.f7336c = str2;
    }

    @Override // com.bytedance.falconx.e
    public WebResourceResponse a(WebView webView, String str) {
        TextUtils.isEmpty(str);
        return null;
    }
}
