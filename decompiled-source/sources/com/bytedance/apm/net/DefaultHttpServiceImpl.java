package com.bytedance.apm.net;

import android.text.TextUtils;
import com.bytedance.apm.util.ag;
import com.bytedance.apm.util.h;
import com.bytedance.apm.util.m;
import com.bytedance.services.apm.api.IHttpService;
import com.bytedance.services.apm.api.d;
import com.bytedance.services.apm.api.n;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
/* loaded from: classes.dex */
public class DefaultHttpServiceImpl implements IHttpService {
    private static String METHOD_GET = "GET";
    private static String METHOD_POST = "POST";

    @Override // com.bytedance.services.apm.api.IHttpService
    public d doPost(String str, byte[] bArr, Map<String, String> map) {
        return doRequest(str, bArr, METHOD_POST, map);
    }

    @Override // com.bytedance.services.apm.api.IHttpService
    public d doGet(String str, Map<String, String> map) {
        return doRequest(str, null, METHOD_GET, map);
    }

    @Override // com.bytedance.services.apm.api.IHttpService
    public d uploadFiles(String str, List<File> list, Map<String, String> map) {
        return h.uploadFiles(str, list, map);
    }

    @Override // com.bytedance.services.apm.api.IHttpService
    public n buildMultipartUpload(String str, String str2, boolean z) {
        return new a(str, str2, z, null);
    }

    @Override // com.bytedance.services.apm.api.IHttpService
    public n buildMultipartUpload(String str, String str2, boolean z, Map<String, String> map) {
        return new a(str, str2, z, map);
    }

    private d doRequest(String str, byte[] bArr, String str2, Map<String, String> map) {
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
                if (TextUtils.equals(str2, METHOD_POST)) {
                    httpURLConnection.setDoOutput(true);
                } else {
                    httpURLConnection.setDoOutput(false);
                }
                httpURLConnection.setRequestMethod(str2);
                if (bArr != null && bArr.length > 0) {
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(bArr);
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                int responseCode = httpURLConnection.getResponseCode();
                if (responseCode != 200) {
                    d dVar = new d(responseCode, null);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused) {
                        }
                    }
                    return dVar;
                }
                inputStream = httpURLConnection.getInputStream();
                try {
                    String contentEncoding = httpURLConnection.getContentEncoding();
                    if (TextUtils.isEmpty(contentEncoding) || !contentEncoding.equalsIgnoreCase("gzip")) {
                        byteArray = toByteArray(inputStream);
                    } else {
                        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream);
                        byteArray = toByteArray(gZIPInputStream);
                        gZIPInputStream.close();
                    }
                    Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
                    HashMap hashMap = new HashMap();
                    for (String str3 : headerFields.keySet()) {
                        List<String> list = headerFields.get(str3);
                        if (list != null && !m.isEmpty(list)) {
                            hashMap.put(str3, list.get(0));
                        }
                    }
                    d dVar2 = new d(responseCode, hashMap, byteArray);
                    if (httpURLConnection != null) {
                        try {
                            httpURLConnection.disconnect();
                        } catch (Exception unused2) {
                        }
                    }
                    return dVar2;
                } catch (Throwable th) {
                    th = th;
                    try {
                        com.bytedance.apm.logging.a.i(com.bytedance.frameworks.baselib.log.a.TAG_ALOG, "http request msg: " + th.getMessage() + " ,stack: " + ag.getStackLimit(th, 10));
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused3) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused4) {
                            }
                        }
                        return null;
                    } finally {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (Exception unused5) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused6) {
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                inputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    public static byte[] toByteArray(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int read = inputStream.read(bArr);
            if (-1 == read) {
                break;
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
        if (inputStream != null) {
            inputStream.close();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
