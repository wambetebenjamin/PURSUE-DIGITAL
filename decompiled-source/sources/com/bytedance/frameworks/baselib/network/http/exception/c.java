package com.bytedance.frameworks.baselib.network.http.exception;

import com.bytedance.frameworks.baselib.network.http.util.p;
import java.net.HttpURLConnection;
import java.net.URI;
import okhttp3.Request;
/* compiled from: HttpResponseException.java */
/* loaded from: classes2.dex */
public class c extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7831a = -7186627969477257933L;

    /* renamed from: b  reason: collision with root package name */
    private final int f7832b;

    /* renamed from: c  reason: collision with root package name */
    private final String f7833c;

    public c(int i2, String str) {
        super(str);
        this.f7832b = i2;
        this.f7833c = null;
    }

    public c(int i2, String str, String str2) {
        super((str == null ? "" : str) + " " + (str2 == null ? "" : str2));
        this.f7832b = i2;
        this.f7833c = null;
    }

    public c(int i2, String str, String str2, String str3) {
        super((str == null ? "" : str) + " " + (str2 == null ? "" : str2));
        this.f7832b = i2;
        this.f7833c = str3;
    }

    public int a() {
        return this.f7832b;
    }

    public static String a(HttpURLConnection httpURLConnection) {
        return (httpURLConnection == null || httpURLConnection.getURL() == null || httpURLConnection.getURL().getPath() == null) ? "" : httpURLConnection.getURL().getPath();
    }

    public static String a(String str) {
        URI a2 = p.a(str);
        return a2 != null ? a2.getPath() : "";
    }

    public static String a(Request request) {
        return (request == null || request.url() == null) ? "" : request.url().url().getPath();
    }

    public String b() {
        return this.f7833c;
    }
}
