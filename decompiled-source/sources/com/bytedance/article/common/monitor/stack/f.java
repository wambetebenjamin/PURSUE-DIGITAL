package com.bytedance.article.common.monitor.stack;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.common.wschannel.WsConstants;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedList;
import java.util.Map;
import java.util.UUID;
import java.util.zip.Deflater;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
/* compiled from: HttpUtil.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final boolean f3645a = false;

    /* compiled from: HttpUtil.java */
    /* loaded from: classes.dex */
    public enum a {
        NONE(0),
        GZIP(1),
        DEFLATER(2);
        

        /* renamed from: a  reason: collision with root package name */
        final int f3647a;

        a(int i2) {
            this.f3647a = i2;
        }
    }

    /* compiled from: HttpUtil.java */
    /* loaded from: classes.dex */
    public enum b {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);
        

        /* renamed from: a  reason: collision with root package name */
        final int f3649a;

        b(int i2) {
            this.f3649a = i2;
        }

        public int getValue() {
            return this.f3649a;
        }
    }

    public static byte[] getRequest(String str, String str2, boolean z) {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return excuteRequest(str, null, str2, null, "GET", false, z);
    }

    public static byte[] excuteRequest(String str, byte[] bArr, String str2, String str3, String str4, boolean z, boolean z2) {
        HttpURLConnection httpURLConnection;
        URL url;
        byte[] byteArray;
        InputStream inputStream = null;
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
                    httpURLConnection = httpURLConnection2;
                    th = th;
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (0 != 0) {
                            try {
                                inputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        if (httpURLConnection != null) {
                            try {
                                httpURLConnection.disconnect();
                            } catch (Exception unused2) {
                            }
                        }
                        throw th2;
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
            httpURLConnection2.setRequestProperty("Version-Code", "1");
            if (str4 == null) {
                throw new IllegalArgumentException("request method is not null");
            }
            httpURLConnection2.setRequestMethod(str4);
            if (bArr != null && bArr.length > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                dataOutputStream.write(bArr);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            int responseCode = httpURLConnection2.getResponseCode();
            if (responseCode == 200) {
                InputStream inputStream2 = httpURLConnection2.getInputStream();
                String contentEncoding = httpURLConnection2.getContentEncoding();
                if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.equalsIgnoreCase("gzip")) {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream2);
                    byteArray = toByteArray(gZIPInputStream);
                    gZIPInputStream.close();
                } else {
                    byteArray = toByteArray(inputStream2);
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (Exception unused3) {
                    }
                }
                if (httpURLConnection2 != null) {
                    try {
                        httpURLConnection2.disconnect();
                    } catch (Exception unused4) {
                    }
                }
                return byteArray;
            }
            throw new e(responseCode, httpURLConnection2.getResponseMessage());
        } catch (Throwable th3) {
            th = th3;
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

    public static byte[] excutePost(long j2, String str, byte[] bArr, a aVar, String str2, boolean z) {
        String str3 = null;
        if (str == null) {
            return null;
        }
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = bArr.length;
        if (a.GZIP == aVar && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
                str3 = "gzip";
            } catch (Throwable unused) {
                gZIPOutputStream.close();
                return null;
            }
        } else if (a.DEFLATER == aVar && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(8192);
            Deflater deflater = new Deflater();
            deflater.setInput(bArr);
            deflater.finish();
            byte[] bArr2 = new byte[8192];
            while (!deflater.finished()) {
                byteArrayOutputStream2.write(bArr2, 0, deflater.deflate(bArr2));
            }
            deflater.end();
            bArr = byteArrayOutputStream2.toByteArray();
            str3 = "deflate";
        }
        String str4 = str3;
        byte[] bArr3 = bArr;
        if (z) {
            byte[] encrypt = com.bytedance.frameworks.core.encrypt.c.encrypt(bArr3, bArr3.length);
            if (encrypt != null) {
                if (TextUtils.isEmpty(new URL(str).getQuery())) {
                    if (!str.endsWith("?")) {
                        str = str + "?";
                    }
                } else if (!str.endsWith("&")) {
                    str = str + "&";
                }
                str = str + "tt_data=a";
                str2 = "application/octet-stream;tt-data=a";
                bArr3 = encrypt;
            }
            return excuteRequest(str, bArr3, str2, str4, "POST", true, true);
        }
        return excuteRequest(str, bArr3, str2, str4, "POST", true, false);
    }

    public static String uploadFile(String str, File file, Map<String, String> map, String str2, boolean z) {
        InputStream inputStream;
        HttpURLConnection httpURLConnection;
        FileInputStream fileInputStream;
        File file2;
        byte[] byteArray;
        if (TextUtils.isEmpty(str) || file == null || !file.exists()) {
            throw new IllegalArgumentException("url and file not be null ");
        }
        String str3 = "tt_file_upload" + UUID.randomUUID().toString();
        File file3 = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(str).openConnection();
            try {
                httpURLConnection2.setReadTimeout(30000);
                httpURLConnection2.setConnectTimeout(30000);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setRequestMethod("POST");
                httpURLConnection2.setRequestProperty("Charset", str2);
                httpURLConnection2.setRequestProperty(WsConstants.KEY_CONNECTION, "keep-alive");
                httpURLConnection2.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + str3);
                if (z) {
                    httpURLConnection2.setRequestProperty("Content-Encoding", "gzip");
                }
                if (map != null && !map.isEmpty()) {
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
                            httpURLConnection2.setRequestProperty(key, value);
                        }
                    }
                }
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection2.getOutputStream());
                if (file == null) {
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception unused) {
                        }
                    }
                    return null;
                }
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("--");
                stringBuffer.append(str3);
                stringBuffer.append("\r\n");
                if (z) {
                    stringBuffer.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + (file.getName().endsWith(".zip") ? file.getName() : file.getName() + ".zip") + "\"\r\n");
                }
                stringBuffer.append("Content-Type: application/octet-stream; charset=" + str2 + "\r\n");
                stringBuffer.append("\r\n");
                dataOutputStream.write(stringBuffer.toString().getBytes());
                if (z && !file.getName().endsWith(".zip")) {
                    file2 = new File(file.getAbsolutePath() + "_tmp.zip");
                    try {
                        if (file2.exists()) {
                            file2.delete();
                        }
                        zipFile(file, file2);
                        fileInputStream = new FileInputStream(file2);
                    } catch (Throwable th) {
                        th = th;
                        httpURLConnection = httpURLConnection2;
                        inputStream = null;
                        file3 = file2;
                        try {
                            th.printStackTrace();
                            throw th;
                        } catch (Throwable th2) {
                            if (file3 != null && file3.exists()) {
                                file3.delete();
                            }
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            if (httpURLConnection != null) {
                                try {
                                    httpURLConnection.disconnect();
                                } catch (Exception unused3) {
                                }
                            }
                            throw th2;
                        }
                    }
                } else {
                    fileInputStream = new FileInputStream(file);
                    file2 = null;
                }
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    dataOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                dataOutputStream.write("\r\n".getBytes());
                dataOutputStream.write(("--" + str3 + "--\r\n").getBytes());
                dataOutputStream.flush();
                dataOutputStream.close();
                int responseCode = httpURLConnection2.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream2 = httpURLConnection2.getInputStream();
                    String contentEncoding = httpURLConnection2.getContentEncoding();
                    if (!TextUtils.isEmpty(contentEncoding) && contentEncoding.equalsIgnoreCase("gzip")) {
                        GZIPInputStream gZIPInputStream = new GZIPInputStream(inputStream2);
                        byteArray = toByteArray(gZIPInputStream);
                        gZIPInputStream.close();
                    } else {
                        byteArray = toByteArray(inputStream2);
                    }
                    String str4 = new String(byteArray);
                    if (file2 != null && file2.exists()) {
                        file2.delete();
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Exception unused4) {
                        }
                    }
                    if (httpURLConnection2 != null) {
                        try {
                            httpURLConnection2.disconnect();
                        } catch (Exception unused5) {
                        }
                    }
                    return str4;
                }
                throw new e(responseCode, httpURLConnection2.getResponseMessage());
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = httpURLConnection2;
                inputStream = null;
            }
        } catch (Throwable th4) {
            th = th4;
            inputStream = null;
            httpURLConnection = null;
        }
    }

    public static void zipFile(File file, File file2) {
        GZIPOutputStream gZIPOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                fileOutputStream = new FileOutputStream(file2);
                try {
                    gZIPOutputStream = new GZIPOutputStream(fileOutputStream);
                    try {
                        byte[] bArr = new byte[1024];
                        while (true) {
                            int read = fileInputStream.read(bArr);
                            if (read != -1) {
                                gZIPOutputStream.write(bArr, 0, read);
                            } else {
                                gZIPOutputStream.close();
                                fileOutputStream.close();
                                fileInputStream.close();
                                return;
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (gZIPOutputStream != null) {
                            gZIPOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    gZIPOutputStream = null;
                }
            } catch (Throwable th4) {
                gZIPOutputStream = null;
                th = th4;
                fileOutputStream = null;
            }
        } catch (Throwable th5) {
            gZIPOutputStream = null;
            fileInputStream = null;
            th = th5;
            fileOutputStream = null;
        }
    }
}
