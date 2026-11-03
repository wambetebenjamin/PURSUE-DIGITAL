package com.bytedance.frameworks.baselib.network.http.impl;

import android.text.TextUtils;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* compiled from: RequestTagManager.java */
/* loaded from: classes2.dex */
public class k {

    /* renamed from: a  reason: collision with root package name */
    private static final String f7915a = "x-tt-request-tag";

    /* renamed from: b  reason: collision with root package name */
    private static final String f7916b = ";";

    /* renamed from: c  reason: collision with root package name */
    private static final String f7917c = "=";

    /* renamed from: d  reason: collision with root package name */
    private static final String f7918d = "bdr-cnt";

    /* renamed from: e  reason: collision with root package name */
    private static final String f7919e = "cr-cnt";

    /* renamed from: f  reason: collision with root package name */
    private static final String f7920f = "cr-nerr";

    /* renamed from: g  reason: collision with root package name */
    private static final AtomicBoolean f7921g = new AtomicBoolean(false);

    public static Request a(Request request, int i2) {
        if (!f7921g.get() || request == null) {
            return request;
        }
        Header firstHeader = request.getFirstHeader(f7915a);
        String a2 = a(f7918d, String.valueOf(i2), firstHeader != null ? firstHeader.getValue() : null);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Header(f7915a, a2));
        List<Header> headers = request.getHeaders();
        if (headers != null) {
            for (Header header : headers) {
                if (header != null && !f7915a.equalsIgnoreCase(header.getName())) {
                    arrayList.add(header);
                }
            }
        }
        return request.newBuilder().headers(arrayList).build();
    }

    public static void a(Request request, HttpURLConnection httpURLConnection, int i2, int i3) {
        Header firstHeader;
        if (!f7921g.get() || httpURLConnection == null) {
            return;
        }
        String str = null;
        if (request != null && (firstHeader = request.getFirstHeader(f7915a)) != null) {
            str = firstHeader.getValue();
        }
        httpURLConnection.setRequestProperty(f7915a, a(f7920f, String.valueOf(i3), a(f7919e, String.valueOf(i2), str)));
    }

    private static String a(String str, String str2, String str3) {
        String str4 = str + f7917c + str2;
        return TextUtils.isEmpty(str3) ? str4 : str3 + ";" + str4;
    }

    public static void a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        f7921g.set(jSONObject.optInt("request_tag_enabled", -1) == 1);
    }
}
