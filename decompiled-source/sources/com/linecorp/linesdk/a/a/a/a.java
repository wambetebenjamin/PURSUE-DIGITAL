package com.linecorp.linesdk.a.a.a;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.bytedance.sdk.account.utils.k;
import com.linecorp.linesdk.LineApiError;
import com.linecorp.linesdk.LineApiResponse;
import com.linecorp.linesdk.LineApiResponseCode;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
/* loaded from: classes9.dex */
public final class a {

    /* renamed from: a  reason: collision with root package name */
    private static final byte[] f17244a = new byte[0];

    /* renamed from: b  reason: collision with root package name */
    private final e f17245b;

    /* renamed from: c  reason: collision with root package name */
    private final d f17246c;

    /* renamed from: d  reason: collision with root package name */
    private int f17247d;

    /* renamed from: e  reason: collision with root package name */
    private int f17248e;

    public a(Context context, String str) {
        this(new e(context, str));
    }

    private a(e eVar) {
        this.f17245b = eVar;
        this.f17246c = new d("UTF-8");
        this.f17247d = 90000;
        this.f17248e = 90000;
    }

    public final <T> LineApiResponse<T> a(Uri uri, Map<String, String> map, Map<String, String> map2, c<T> cVar) {
        HttpURLConnection a2;
        byte[] a3 = a(map2);
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                int length = a3.length;
                a2 = a(uri);
                a2.setInstanceFollowRedirects(true);
                a2.setRequestProperty("User-Agent", this.f17245b.a());
                a2.setRequestProperty("Accept-Encoding", "gzip");
                a2.setRequestProperty("Content-Type", k.CONTENT_TYPE);
                a2.setRequestProperty("Content-Length", String.valueOf(length));
                a2.setConnectTimeout(this.f17247d);
                a2.setReadTimeout(this.f17248e);
                a2.setRequestMethod("POST");
                a2.setDoOutput(true);
            } catch (Throwable th) {
                th = th;
            }
        } catch (IOException e2) {
            e = e2;
        }
        try {
            a(a2, map);
            a2.connect();
            OutputStream outputStream = a2.getOutputStream();
            outputStream.write(a3);
            outputStream.flush();
            LineApiResponse<T> a4 = a(a2, cVar, this.f17246c);
            if (a2 != null) {
                a2.disconnect();
            }
            return a4;
        } catch (IOException e3) {
            e = e3;
            httpURLConnection = a2;
            LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return createAsError;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = a2;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    public final <T> LineApiResponse<T> b(Uri uri, Map<String, String> map, Map<String, String> map2, c<T> cVar) {
        HttpURLConnection a2;
        Uri.Builder buildUpon = uri.buildUpon();
        for (Map.Entry<String, String> entry : map2.entrySet()) {
            buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                a2 = a(buildUpon.build());
                a2.setInstanceFollowRedirects(true);
                a2.setRequestProperty("User-Agent", this.f17245b.a());
                a2.setRequestProperty("Accept-Encoding", "gzip");
                a2.setConnectTimeout(this.f17247d);
                a2.setReadTimeout(this.f17248e);
                a2.setRequestMethod("GET");
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            a(a2, map);
            a2.connect();
            LineApiResponse<T> a3 = a(a2, cVar, this.f17246c);
            if (a2 != null) {
                a2.disconnect();
            }
            return a3;
        } catch (IOException e3) {
            e = e3;
            httpURLConnection = a2;
            LineApiResponse<T> createAsError = LineApiResponse.createAsError(LineApiResponseCode.NETWORK_ERROR, new LineApiError(e));
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return createAsError;
        } catch (Throwable th2) {
            th = th2;
            httpURLConnection = a2;
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }

    private static HttpURLConnection a(Uri uri) {
        URLConnection openConnection = new URL(uri.toString()).openConnection();
        if (!(openConnection instanceof HttpsURLConnection)) {
            throw new IllegalArgumentException("The scheme of the server url must be https." + uri);
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return (HttpURLConnection) openConnection;
        }
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) openConnection;
        httpsURLConnection.setSSLSocketFactory(new com.linecorp.a.a.a(httpsURLConnection.getSSLSocketFactory()));
        return httpsURLConnection;
    }

    private static void a(HttpURLConnection httpURLConnection, Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    private static byte[] a(Map<String, String> map) {
        if (map.isEmpty()) {
            return f17244a;
        }
        Uri.Builder builder = new Uri.Builder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.appendQueryParameter(entry.getKey(), entry.getValue());
        }
        try {
            return builder.build().getEncodedQuery().getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static <T> LineApiResponse<T> a(HttpURLConnection httpURLConnection, c<T> cVar, c<String> cVar2) {
        GZIPInputStream errorStream;
        if (httpURLConnection.getResponseCode() < 400) {
            errorStream = httpURLConnection.getInputStream();
        } else {
            errorStream = httpURLConnection.getErrorStream();
        }
        List<String> list = httpURLConnection.getHeaderFields().get("Content-Encoding");
        boolean z = false;
        if (list != null && !list.isEmpty()) {
            int i2 = 0;
            while (true) {
                if (i2 >= list.size()) {
                    break;
                } else if (list.get(i2).equalsIgnoreCase("gzip")) {
                    z = true;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (z) {
            errorStream = new GZIPInputStream(errorStream);
        }
        int responseCode = httpURLConnection.getResponseCode();
        try {
            if (responseCode != 200) {
                return LineApiResponse.createAsError(LineApiResponseCode.SERVER_ERROR, new LineApiError(responseCode, cVar2.a(errorStream)));
            }
            return LineApiResponse.createAsSuccess(cVar.a(errorStream));
        } catch (IOException e2) {
            return LineApiResponse.createAsError(LineApiResponseCode.INTERNAL_ERROR, new LineApiError(responseCode, e2));
        }
    }
}
