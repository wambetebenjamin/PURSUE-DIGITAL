package tt.g.p.f.n;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public class as {

    /* renamed from: a  reason: collision with root package name */
    private String f21159a;

    /* renamed from: b  reason: collision with root package name */
    private String f21160b;

    /* renamed from: c  reason: collision with root package name */
    private byte[] f21161c;

    /* renamed from: d  reason: collision with root package name */
    private String f21162d;

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Object> f21163e;

    /* renamed from: f  reason: collision with root package name */
    private Map<String, Object> f21164f;

    /* renamed from: g  reason: collision with root package name */
    private Map<String, String> f21165g;

    /* renamed from: h  reason: collision with root package name */
    private ay f21166h;

    /* renamed from: i  reason: collision with root package name */
    private HttpURLConnection f21167i;

    private byte[] a(int i2) {
        HttpURLConnection httpURLConnection = this.f21167i;
        if (httpURLConnection == null) {
            throw new Exception("no connection");
        }
        InputStream inputStream = (i2 == 200 || i2 == 201 || i2 == 202) ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
        if (inputStream == null) {
            throw new Exception("error get input stream");
        }
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = bufferedInputStream.read(bArr);
            if (read <= 0) {
                bufferedInputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, read);
        }
    }

    private void c() {
        if (this.f21162d.equals("GET")) {
            e.a(this.f21160b, this.f21165g);
        } else if (!this.f21162d.equals("POST")) {
            throw new RuntimeException("Unsupported http method");
        } else {
            e.a(this.f21160b, this.f21165g, this.f21161c);
        }
    }

    private void d() {
        if (this.f21167i != null) {
            throw new Exception("already connected");
        }
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f21160b).openConnection();
        this.f21167i = httpURLConnection;
        httpURLConnection.setRequestMethod(this.f21162d);
        this.f21167i.setConnectTimeout(ba.a());
        this.f21167i.setReadTimeout(ba.b());
        this.f21167i.setInstanceFollowRedirects(false);
        this.f21167i.setUseCaches(false);
        Map<String, String> map = this.f21165g;
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, String> entry : this.f21165g.entrySet()) {
                this.f21167i.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        if (this.f21162d.equals("POST")) {
            this.f21167i.setDoOutput(true);
            this.f21167i.setDoInput(true);
            if (this.f21161c != null) {
                if (!this.f21165g.containsKey("Content-Type")) {
                    this.f21167i.setRequestProperty("Content-Type", "application/octet-stream");
                }
                this.f21167i.setFixedLengthStreamingMode(this.f21161c.length);
                OutputStream outputStream = this.f21167i.getOutputStream();
                outputStream.write(this.f21161c);
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private void e() {
        HttpURLConnection httpURLConnection = this.f21167i;
        if (httpURLConnection == null) {
            throw new Exception("no connection");
        }
        int responseCode = httpURLConnection.getResponseCode();
        this.f21166h = new ay(responseCode, a(responseCode), g());
    }

    private void f() {
        String str = this.f21162d;
        if (str == null) {
            throw new Exception("No method");
        }
        if (!str.equals("GET") && !this.f21162d.equals("POST")) {
            throw new Exception("Unknown http method");
        }
    }

    private HashMap<String, String> g() {
        if (this.f21167i != null) {
            HashMap<String, String> hashMap = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : this.f21167i.getHeaderFields().entrySet()) {
                hashMap.put(entry.getKey(), this.f21167i.getHeaderField(entry.getKey()));
            }
            return hashMap;
        }
        throw new Exception("no connection");
    }

    public ay a() {
        return this.f21166h;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2, Map<String, Object> map, Map<String, Object> map2, Map<String, Object> map3) {
        this.f21159a = str2;
        this.f21162d = str;
        this.f21163e = map;
        this.f21164f = map2;
        this.f21165g = b.b(map3);
        f();
        this.f21160b = b.a(this.f21159a, map);
        this.f21161c = b.a(map2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, byte[] bArr, Map<String, Object> map, Map<String, Object> map2) {
        this.f21159a = str;
        this.f21162d = "POST";
        this.f21163e = map;
        this.f21161c = bArr;
        this.f21165g = b.b(map2);
        this.f21160b = b.a(this.f21159a, map);
    }

    public void b() {
        c();
        d();
        this.f21167i.connect();
        e();
    }
}
