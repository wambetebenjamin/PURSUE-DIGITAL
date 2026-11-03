package com.bytedance.framwork.core.sdklib.apm6.http;

import android.text.TextUtils;
import com.bytedance.apm6.util.e;
import com.bytedance.framwork.core.sdkmonitor.j;
import com.bytedance.services.apm.api.d;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
/* compiled from: DefaultHttpService.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static String f8679a = "POST";

    public static d a(String str, byte[] bArr, Map<String, String> map) {
        return a(str, bArr, f8679a, map);
    }

    private static d a(String str, byte[] bArr, String str2, Map<String, String> map) {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        byte[] byteArray;
        if (str2 == null) {
            throw new IllegalArgumentException("request method is not null");
        }
        try {
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        if (entry != null) {
                            httpURLConnection.setRequestProperty(entry.getKey(), entry.getValue());
                        }
                    }
                }
                httpURLConnection.setDoOutput(TextUtils.equals(str2, f8679a));
                httpURLConnection.setRequestMethod(str2);
                if (bArr != null && bArr.length > 0) {
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(bArr);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode == 200) {
                    inputStream = httpURLConnection.getInputStream();
                    try {
                        String contentEncoding = httpURLConnection.getContentEncoding();
                        if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.equalsIgnoreCase("gzip")) {
                            GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
                            byteArray = j.toByteArray(gZIPInputStream);
                            gZIPInputStream.close();
                        } else {
                            byteArray = j.toByteArray(inputStream);
                        }
                        Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                        HashMap hashMap = new HashMap();
                        for (Map.Entry<String, List<String>> entry2 : headerFields.entrySet()) {
                            String key = entry2.getKey();
                            List<String> value = entry2.getValue();
                            if (value != null && value.size() > 0) {
                                hashMap.put(key, value.get(0));
                            }
                        }
                        d dVar = new d(responseCode, hashMap, byteArray);
                        e.closeQuietly(inputStream);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused) {
                            }
                        }
                        return dVar;
                    } catch (Throwable unused2) {
                        e.closeQuietly(inputStream);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused3) {
                            }
                        }
                        return null;
                    }
                }
                d dVar2 = new d(responseCode, null);
                e.closeQuietly(null);
                if (httpURLConnection != null) {
                    try {
                        httpURLConnection.disconnect();
                    } catch (Exception unused4) {
                    }
                }
                return dVar2;
            } catch (Throwable unused5) {
                inputStream = null;
            }
        } catch (Throwable unused6) {
            httpURLConnection = null;
            inputStream = null;
        }
    }
}
