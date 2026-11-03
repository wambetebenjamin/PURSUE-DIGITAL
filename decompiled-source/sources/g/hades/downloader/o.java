package g.hades.downloader;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import javax.net.ssl.SSLHandshakeException;
import org.json.JSONObject;
/* loaded from: classes10.dex */
public class o implements m {

    /* renamed from: c  reason: collision with root package name */
    public static int f19101c = 10;

    /* renamed from: d  reason: collision with root package name */
    public static int f19102d = 10;

    /* renamed from: a  reason: collision with root package name */
    public HttpURLConnection f19103a;

    /* renamed from: b  reason: collision with root package name */
    public int f19104b;

    @Override // g.hades.downloader.m
    public void a(String str, JSONObject jSONObject, String str2) {
        try {
            this.f19103a = (HttpURLConnection) new URL(str).openConnection();
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                this.f19103a.setRequestProperty(next, jSONObject.optString(next));
            }
            this.f19103a.setRequestMethod("GET");
            this.f19103a.setConnectTimeout(f19101c);
            this.f19103a.setReadTimeout(f19102d);
            this.f19103a.setDoInput(true);
            this.f19103a.connect();
            this.f19104b = this.f19103a.getResponseCode();
        } catch (SSLHandshakeException e2) {
            Log.w("Hades-J-URLRCon", "connect handshakeException " + e2 + ", try to use http retry");
            if (str.startsWith("https")) {
                a("http" + str.substring(5), jSONObject, str2);
                return;
            }
            throw e2;
        }
    }

    @Override // g.hades.downloader.m
    public void b() {
        HttpURLConnection httpURLConnection = this.f19103a;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // g.hades.downloader.m
    public InputStream c() {
        HttpURLConnection httpURLConnection = this.f19103a;
        if (httpURLConnection != null) {
            return httpURLConnection.getInputStream();
        }
        throw new IOException("connection is null");
    }

    @Override // g.hades.downloader.m
    public void d() {
        HttpURLConnection httpURLConnection = this.f19103a;
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
    }

    @Override // g.hades.downloader.m
    public int a() {
        return this.f19104b;
    }

    @Override // g.hades.downloader.m
    public String a(String str) {
        HttpURLConnection httpURLConnection = this.f19103a;
        return httpURLConnection == null ? "" : httpURLConnection.getHeaderField(str);
    }

    public static JSONObject a(String str, JSONObject jSONObject) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        Throwable th;
        BufferedReader bufferedReader;
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(f19101c);
                httpURLConnection.setReadTimeout(f19102d);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    httpURLConnection.setRequestProperty(next, jSONObject.optString(next));
                }
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                InputStream inputStream2 = httpURLConnection.getInputStream();
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream2, "UTF-8"));
                    try {
                        StringBuffer stringBuffer = new StringBuffer();
                        while (true) {
                            String readLine = bufferedReader.readLine();
                            if (readLine != null) {
                                stringBuffer.append(readLine);
                            } else {
                                String stringBuffer2 = stringBuffer.toString();
                                JSONObject jSONObject2 = new JSONObject();
                                jSONObject2.put("code", 0);
                                jSONObject2.put(com.bytedance.apm.perf.traffic.a.TTNET_RESPONSE, stringBuffer2);
                                h.a(bufferedReader);
                                h.a(inputStream2);
                                httpURLConnection.disconnect();
                                return jSONObject2;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            throw th;
                        } catch (Throwable th3) {
                            h.a(bufferedReader);
                            h.a(inputStream2);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            throw th3;
                        }
                    }
                } catch (Throwable th4) {
                    inputStream = inputStream2;
                    th = th4;
                    th = th;
                    inputStream2 = inputStream;
                    bufferedReader = null;
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                inputStream = null;
            }
        } catch (Throwable th6) {
            th = th6;
            httpURLConnection = null;
            inputStream = null;
        }
    }
}
