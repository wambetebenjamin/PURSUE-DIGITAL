package com.bytedance.router.net;

import android.net.Uri;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONObject;
/* compiled from: NetClient.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12211a = "application/json; charset=UTF-8";

    /* renamed from: b  reason: collision with root package name */
    private static final boolean f12212b = false;

    /* renamed from: c  reason: collision with root package name */
    private static final int f12213c = 8192;

    /* renamed from: d  reason: collision with root package name */
    private static final int f12214d = 6;

    /* renamed from: e  reason: collision with root package name */
    private static final int f12215e = 500;

    public static b post(String str, Map<String, String> map, JSONObject jSONObject) {
        int i2;
        StringBuilder sb;
        int i3;
        b bVar = new b();
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        String builder = buildUpon.toString();
        int i4 = 0;
        while (true) {
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    httpURLConnection = a(builder);
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    if (jSONObject != null) {
                        String jSONObject2 = jSONObject.toString();
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        dataOutputStream.writeBytes(jSONObject2);
                        dataOutputStream.flush();
                        dataOutputStream.close();
                    }
                    bVar.errorCode = httpURLConnection.getResponseCode();
                } finally {
                    if (i3 != r12) {
                        return bVar;
                    }
                }
            } catch (Exception unused) {
                bVar.errorCode = -1;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bVar.errorCode == 200) {
                    continue;
                } else if (i4 >= 6) {
                    return bVar;
                } else {
                    i2 = i4 + 1;
                    try {
                        Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                    } catch (InterruptedException unused2) {
                    }
                    sb = new StringBuilder();
                }
            }
            if (200 == httpURLConnection.getResponseCode()) {
                bVar.content = new String(a(httpURLConnection.getInputStream()), "UTF-8");
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bVar.errorCode != 200 && i4 < 6) {
                    int i5 = i4 + 1;
                    try {
                        Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                    } catch (InterruptedException unused3) {
                    }
                    com.bytedance.router.util.b.w("retry connect url: " + i5 + ": " + str);
                    return bVar;
                }
            } else {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (bVar.errorCode == 200) {
                    continue;
                } else if (i4 >= 6) {
                    break;
                } else {
                    i2 = i4 + 1;
                    try {
                        Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                    } catch (InterruptedException unused4) {
                    }
                    sb = new StringBuilder();
                    com.bytedance.router.util.b.w(sb.append("retry connect url: ").append(i2).append(": ").append(str).toString());
                    i4 = i2;
                }
            }
        }
    }

    public static b get(String str, Map<String, String> map) {
        int i2;
        int i3;
        StringBuilder sb;
        b bVar = new b();
        Uri.Builder buildUpon = Uri.parse(str).buildUpon();
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                buildUpon.appendQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        String builder = buildUpon.toString();
        int i4 = 0;
        while (true) {
            HttpURLConnection httpURLConnection = null;
            try {
                try {
                    httpURLConnection = a(builder);
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.connect();
                    bVar.errorCode = httpURLConnection.getResponseCode();
                } catch (Exception unused) {
                    bVar.errorCode = -1;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (bVar.errorCode == 200) {
                        continue;
                    } else if (i4 >= 6) {
                        break;
                    } else {
                        i3 = i4 + 1;
                        try {
                            Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                        } catch (InterruptedException unused2) {
                        }
                        sb = new StringBuilder();
                    }
                }
                if (200 == httpURLConnection.getResponseCode()) {
                    bVar.content = new String(a(httpURLConnection.getInputStream()), "UTF-8");
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (bVar.errorCode != 200 && i4 < 6) {
                        int i5 = i4 + 1;
                        try {
                            Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                        } catch (InterruptedException unused3) {
                        }
                        com.bytedance.router.util.b.w("retry connect url: " + i5);
                        return bVar;
                    }
                } else {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (bVar.errorCode == 200) {
                        continue;
                    } else if (i4 >= 6) {
                        break;
                    } else {
                        i3 = i4 + 1;
                        try {
                            Thread.sleep(((long) Math.pow(2.0d, i4)) * 500);
                        } catch (InterruptedException unused4) {
                        }
                        sb = new StringBuilder();
                        com.bytedance.router.util.b.w(sb.append("retry connect url: ").append(i3).toString());
                        i4 = i3;
                    }
                }
            } finally {
                if (i2 != r8) {
                    return bVar;
                }
            }
        }
        return bVar;
    }

    private static HttpURLConnection a(String str) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestProperty("Content-Type", f12211a);
        httpURLConnection.setUseCaches(false);
        boolean z = httpURLConnection instanceof HttpsURLConnection;
        return httpURLConnection;
    }

    private static byte[] a(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read != -1) {
                bufferedOutputStream.write(bArr, 0, read);
            } else {
                bufferedOutputStream.flush();
                bufferedInputStream.close();
                bufferedOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
