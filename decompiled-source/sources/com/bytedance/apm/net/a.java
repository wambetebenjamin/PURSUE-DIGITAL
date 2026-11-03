package com.bytedance.apm.net;

import android.text.TextUtils;
import com.bytedance.services.apm.api.d;
import com.bytedance.services.apm.api.n;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.zip.GZIPOutputStream;
/* compiled from: MultipartUtility.java */
/* loaded from: classes.dex */
class a implements n {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1225a = "\r\n";

    /* renamed from: b  reason: collision with root package name */
    private static final int f1226b = 8192;

    /* renamed from: c  reason: collision with root package name */
    private final String f1227c;

    /* renamed from: d  reason: collision with root package name */
    private HttpURLConnection f1228d;

    /* renamed from: e  reason: collision with root package name */
    private String f1229e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f1230f;

    /* renamed from: g  reason: collision with root package name */
    private DataOutputStream f1231g;

    /* renamed from: h  reason: collision with root package name */
    private GZIPOutputStream f1232h;

    /* renamed from: i  reason: collision with root package name */
    private Map<String, String> f1233i;

    public a(String str, String str2, boolean z, Map<String, String> map) {
        this.f1229e = str2;
        this.f1230f = z;
        this.f1233i = map;
        String str3 = "AAA" + System.currentTimeMillis() + "AAA";
        this.f1227c = str3;
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        this.f1228d = httpURLConnection;
        httpURLConnection.setUseCaches(false);
        this.f1228d.setDoOutput(true);
        this.f1228d.setDoInput(true);
        this.f1228d.setRequestMethod("POST");
        this.f1228d.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + str3);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                this.f1228d.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        this.f1228d.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.f1227c);
        if (z) {
            this.f1228d.setRequestProperty("Content-Encoding", "gzip");
            this.f1232h = new GZIPOutputStream(this.f1228d.getOutputStream());
            return;
        }
        this.f1231g = new DataOutputStream(this.f1228d.getOutputStream());
    }

    @Override // com.bytedance.services.apm.api.n
    public void addFormField(String str, String str2) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("--").append(this.f1227c).append(f1225a).append("Content-Disposition: form-data; name=\"").append(str).append("\"").append(f1225a).append("Content-Type: text/plain; charset=").append(this.f1229e).append(f1225a).append(f1225a).append(str2).append(f1225a);
        try {
            if (this.f1230f) {
                this.f1232h.write(sb.toString().getBytes());
            } else {
                this.f1231g.write(sb.toString().getBytes());
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.bytedance.services.apm.api.n
    public void a(String str, File file, String str2, Map<String, String> map) {
        String name = file.getName();
        StringBuilder sb = new StringBuilder(100);
        sb.append("--").append(this.f1227c).append(f1225a).append("Content-Disposition: form-data; name=\"").append(str).append("\"; filename=\"").append(name).append("\"");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("; ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(f1225a).append("Content-Type: ").append(str2).append(f1225a);
        }
        sb.append(f1225a).append("Content-Transfer-Encoding: binary").append(f1225a).append(f1225a);
        if (this.f1230f) {
            this.f1232h.write(sb.toString().getBytes());
        } else {
            this.f1231g.write(sb.toString().getBytes());
        }
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bArr = new byte[8192];
        while (true) {
            int read = fileInputStream.read(bArr);
            if (read == -1) {
                break;
            } else if (this.f1230f) {
                this.f1232h.write(bArr, 0, read);
            } else {
                this.f1231g.write(bArr, 0, read);
            }
        }
        fileInputStream.close();
        if (this.f1230f) {
            this.f1232h.write(f1225a.getBytes());
            return;
        }
        this.f1231g.write(f1225a.getBytes());
        this.f1231g.flush();
    }

    @Override // com.bytedance.services.apm.api.n
    public void a(String str, String str2, String str3, Map<String, String> map) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("--").append(this.f1227c).append(f1225a).append("Content-Disposition: form-data; name=\"").append(str).append("\"; filename=\"").append(str).append("\"");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("; ").append(entry.getKey()).append("=\"").append(entry.getValue()).append("\"");
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(f1225a).append("Content-Type: ").append(str3).append(f1225a);
        }
        sb.append(f1225a);
        if (this.f1230f) {
            this.f1232h.write(sb.toString().getBytes());
        } else {
            this.f1231g.write(sb.toString().getBytes());
        }
        if (str2 == null) {
            str2 = "";
        }
        if (this.f1230f) {
            this.f1232h.write(str2.getBytes());
        } else {
            this.f1231g.write(str2.getBytes());
        }
        if (this.f1230f) {
            this.f1232h.write(f1225a.getBytes());
            return;
        }
        this.f1231g.write(f1225a.getBytes());
        this.f1231g.flush();
    }

    @Override // com.bytedance.services.apm.api.n
    public d a() {
        ArrayList<String> arrayList = new ArrayList();
        byte[] bytes = ("\r\n--" + this.f1227c + "--" + f1225a).getBytes();
        if (this.f1230f) {
            this.f1232h.write(bytes);
            this.f1232h.finish();
            this.f1232h.close();
        } else {
            this.f1231g.write(bytes);
            this.f1231g.flush();
            this.f1231g.close();
        }
        int responseCode = this.f1228d.getResponseCode();
        if (responseCode == 200) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f1228d.getInputStream()));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    break;
                }
                arrayList.add(readLine);
            }
            bufferedReader.close();
            this.f1228d.disconnect();
            StringBuilder sb = new StringBuilder();
            for (String str : arrayList) {
                sb.append(str);
            }
            return new d(responseCode, sb.toString().getBytes());
        }
        throw new IOException("Server returned non-OK status: " + responseCode);
    }
}
