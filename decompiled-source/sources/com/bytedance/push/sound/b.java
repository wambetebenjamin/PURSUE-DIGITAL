package com.bytedance.push.sound;

import com.bytedance.common.utility.Logger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
/* compiled from: UrlConnectionSoundDownloader.java */
/* loaded from: classes2.dex */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private static final int f11913a = 20000;

    /* renamed from: b  reason: collision with root package name */
    private static final int f11914b = 15000;

    /* renamed from: c  reason: collision with root package name */
    private final String f11915c = "UrlConnectionDownloader";

    @Override // com.bytedance.push.sound.a
    public boolean downLoadSound(String str, String str2) {
        Logger.i("UrlConnectionDownloader", "download url:" + str + " targetPath:" + str2);
        try {
            return a(str, str2);
        } catch (Throwable unused) {
            return false;
        }
    }

    private boolean a(String str, String str2) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(15000);
        httpURLConnection.setReadTimeout(20000);
        httpURLConnection.setUseCaches(true);
        int responseCode = httpURLConnection.getResponseCode();
        if (responseCode >= 300) {
            httpURLConnection.disconnect();
            throw new IOException(responseCode + " " + httpURLConnection.getResponseMessage());
        }
        InputStream inputStream = httpURLConnection.getInputStream();
        if (inputStream == null) {
            return false;
        }
        if (httpURLConnection.getHeaderFieldInt("Content-Length", -1) == 0) {
            com.bytedance.common.utility.io.b.close(inputStream);
            throw new IOException("Received response with 0 content-length header.");
        }
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(str2);
            if (file.exists() || file.createNewFile()) {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[2048];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            fileOutputStream2.write(bArr, 0, read);
                        } else {
                            fileOutputStream2.flush();
                            fileOutputStream2.close();
                            Logger.i("UrlConnectionDownloader", "download success");
                            com.bytedance.common.utility.io.b.close(inputStream);
                            com.bytedance.common.utility.io.b.close(fileOutputStream2);
                            return true;
                        }
                    }
                } catch (Throwable unused) {
                    fileOutputStream = fileOutputStream2;
                    com.bytedance.common.utility.io.b.close(inputStream);
                    if (fileOutputStream != null) {
                        com.bytedance.common.utility.io.b.close(fileOutputStream);
                    }
                    return false;
                }
            } else {
                com.bytedance.common.utility.io.b.close(inputStream);
                return false;
            }
        } catch (Throwable unused2) {
        }
    }
}
