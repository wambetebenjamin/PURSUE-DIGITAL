package com.bytedance.frameworks.gpm;

import android.net.Uri;
import com.google.common.net.HttpHeaders;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import org.json.JSONObject;
/* compiled from: HttpRequest.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static int f8539a = 20000;

    /* renamed from: b  reason: collision with root package name */
    public static int f8540b = 120000;

    /* renamed from: c  reason: collision with root package name */
    public static String f8541c = "dDA4MJXBcTIaxroojAl8";

    /* renamed from: d  reason: collision with root package name */
    public static int f8542d = 1048576;

    /* renamed from: e  reason: collision with root package name */
    public static int f8543e = 1048576;

    public static String b(String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(a(str, str2, str3, str4));
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setConnectTimeout(f8539a);
            httpURLConnection.setReadTimeout(f8540b);
            httpURLConnection.connect();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                }
                bufferedReader.close();
            } else {
                GPMMonitor.loge(url.toString() + " response error:" + responseCode);
            }
        } catch (Throwable th) {
            GPMMonitor.loge("http get error:" + th.toString());
        }
        return sb.toString();
    }

    private static String a(String str, String str2, String str3, String str4) {
        Uri.Builder builder = new Uri.Builder();
        try {
            JSONObject jSONObject = new JSONObject(str4);
            builder.scheme(str).authority(str2).path(str3);
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                builder.appendQueryParameter(next, jSONObject.get(next).toString());
            }
        } catch (Throwable th) {
            GPMMonitor.loge("http url error:" + th.toString());
        }
        return builder.build().toString();
    }

    public static String a(String str, String str2, String str3, String str4, String str5) {
        File file;
        StringBuilder sb = new StringBuilder();
        try {
            file = new File(str5);
        } catch (Throwable th) {
            GPMMonitor.loge("http upload file error:" + th.toString());
            sb.append(th.toString());
        }
        if (!file.exists()) {
            throw new Exception("file doesn't exits, " + str5);
        }
        URL url = new URL(a(str, str2, str3, str4));
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(f8539a);
        httpURLConnection.setReadTimeout(f8540b);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setChunkedStreamingMode(f8543e);
        httpURLConnection.setRequestProperty(HttpHeaders.CONNECTION, HttpHeaders.KEEP_ALIVE);
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + f8541c);
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        byte[] bytes = ("--" + f8541c + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"\r\nContent-Type: application/octet-stream; charset=utf-8\r\n\r\n").getBytes();
        dataOutputStream.write(bytes, 0, bytes.length);
        dataOutputStream.flush();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[f8542d];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            }
            dataOutputStream.write(bArr, 0, read);
        }
        byte[] bytes2 = ("\r\n--" + f8541c + "--\r\n").getBytes();
        dataOutputStream.write(bytes2, 0, bytes2.length);
        dataOutputStream.flush();
        dataOutputStream.close();
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                sb.append(readLine);
            }
            bufferedReader.close();
            return sb.toString();
        }
        throw new Exception(url.toString() + " response error:" + responseCode);
    }
}
