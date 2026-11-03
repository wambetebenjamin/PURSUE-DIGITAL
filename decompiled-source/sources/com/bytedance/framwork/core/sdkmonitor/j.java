package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.apm6.util.e;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/* compiled from: MonitorNetUtil.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    public static final String f8826a = "Content-Encoding";

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static byte[] getRequest(String str, String str2, boolean z) {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return excuteRequest(str, null, str2, null, "GET", false, z).getResponseBytes();
    }

    public static com.bytedance.services.apm.api.d excuteRequest(String str, byte[] bArr, String str2, String str3, String str4, boolean z, boolean z2) {
        HttpURLConnection httpURLConnection;
        URL url;
        byte[] byteArray;
        DataOutputStream dataOutputStream;
        GZIPInputStream gZIPInputStream = null;
        try {
            LinkedList<Pair> linkedList = new LinkedList();
            if (z2) {
                url = new URL(com.bytedance.frameworks.core.encrypt.b.tryEncryptRequest(str, linkedList));
            } else {
                url = new URL(str);
            }
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
            if (z2) {
                try {
                    if (!linkedList.isEmpty()) {
                        for (Pair pair : linkedList) {
                            if (pair != null) {
                                httpURLConnection2.setRequestProperty((String) pair.first, (String) pair.second);
                            }
                        }
                    }
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    httpURLConnection = httpURLConnection2;
                    th = th2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        e.closeQuietly(gZIPInputStream);
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused) {
                            }
                        }
                        throw th3;
                    }
                }
            }
            if (z) {
                httpURLConnection2.setDoOutput(true);
            } else {
                httpURLConnection2.setDoOutput(false);
            }
            if (str2 != null) {
                httpURLConnection2.setRequestProperty("Content-Type", str2);
            }
            if (str3 != null) {
                httpURLConnection2.setRequestProperty("Content-Encoding", str3);
            }
            httpURLConnection2.setRequestProperty("Accept-Encoding", "gzip");
            if (str4 == null) {
                throw new IllegalArgumentException("request method is not null");
            }
            httpURLConnection2.setRequestMethod(str4);
            if (bArr != null && bArr.length > 0) {
                try {
                    dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                    try {
                        dataOutputStream.write(bArr);
                        dataOutputStream.flush();
                        e.closeQuietly(dataOutputStream);
                    } catch (Throwable th4) {
                        th = th4;
                        e.closeQuietly(dataOutputStream);
                        throw th;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    dataOutputStream = null;
                }
            }
            int responseCode = httpURLConnection2.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream = httpURLConnection2.getInputStream();
                try {
                    String contentEncoding = httpURLConnection2.getContentEncoding();
                    if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.equalsIgnoreCase("gzip")) {
                        try {
                            GZIPInputStream gZIPInputStream2 = new GZIPInputStream(inputStream);
                            try {
                                byteArray = toByteArray(gZIPInputStream2);
                                e.closeQuietly(gZIPInputStream2);
                            } catch (Throwable th6) {
                                th = th6;
                                gZIPInputStream = gZIPInputStream2;
                                e.closeQuietly(gZIPInputStream);
                                throw th;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                        }
                    } else {
                        byteArray = toByteArray(inputStream);
                    }
                    Map<String, List<String>> headerFields = httpURLConnection2.getHeaderFields();
                    HashMap hashMap = new HashMap();
                    for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
                        String key = entry.getKey();
                        List<String> value = entry.getValue();
                        if (value != null && !com.bytedance.framwork.core.sdklib.util.d.isEmpty(value)) {
                            hashMap.put(key, value.get(0));
                        }
                    }
                    com.bytedance.services.apm.api.d dVar = new com.bytedance.services.apm.api.d(responseCode, hashMap, byteArray);
                    e.closeQuietly(inputStream);
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception unused2) {
                        }
                    }
                    return dVar;
                } catch (Throwable th8) {
                    th = th8;
                    gZIPInputStream = inputStream;
                    Throwable th22 = th;
                    httpURLConnection = httpURLConnection2;
                    th = th22;
                    throw th;
                }
            }
            throw new d(responseCode, httpURLConnection2.getResponseMessage());
        } catch (Throwable th9) {
            th = th9;
            httpURLConnection = null;
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

    public static byte[] compress(byte[] bArr, Map<String, String> map) {
        if (bArr.length > 128) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.close();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    map.put("Content-Encoding", "gzip");
                    return byteArray;
                } catch (IOException e2) {
                    throw e2;
                }
            } catch (Throwable th) {
                gZIPOutputStream.close();
                throw th;
            }
        }
        return bArr;
    }
}
