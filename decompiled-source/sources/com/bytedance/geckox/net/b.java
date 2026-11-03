package com.bytedance.geckox.net;

import android.util.Pair;
import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
/* compiled from: DefaultNetWork.java */
/* loaded from: classes2.dex */
public class b implements c {

    /* renamed from: a  reason: collision with root package name */
    protected OkHttpClient f9237a = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS).build();

    /* renamed from: b  reason: collision with root package name */
    protected OkHttpClient f9238b = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).build();

    @Override // com.bytedance.geckox.net.c
    public d doGet(String str) {
        Response execute = this.f9237a.newCall(new Request.Builder().get().url(str).build()).execute();
        return new d(a(execute.headers()), execute.code() == 200 ? execute.body().string() : null, execute.code(), execute.message());
    }

    @Override // com.bytedance.geckox.net.c
    public d doPost(String str, List<Pair<String, String>> list) {
        FormBody.Builder builder = new FormBody.Builder();
        if (list != null) {
            for (Pair<String, String> pair : list) {
                builder.add((String) pair.first, (String) pair.second);
            }
        }
        Response execute = this.f9237a.newCall(new Request.Builder().url(str).post(builder.build()).build()).execute();
        return new d(a(execute.headers()), execute.code() == 200 ? execute.body().string() : null, execute.code(), execute.message());
    }

    @Override // com.bytedance.geckox.net.c
    public d doPost(String str, String str2) {
        Response execute = this.f9237a.newCall(new Request.Builder().url(str).post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), str2)).build()).execute();
        return new d(a(execute.headers()), execute.code() == 200 ? execute.body().string() : null, execute.code(), execute.message());
    }

    @Override // com.bytedance.geckox.net.c
    public void downloadFile(String str, long j2, com.bytedance.geckox.buffer.stream.b bVar) {
        int i2;
        Exception e2;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            try {
                Response execute = this.f9238b.newCall(new Request.Builder().get().url(str).build()).execute();
                i2 = execute.code();
                try {
                    bufferedInputStream = new BufferedInputStream(execute.body().byteStream());
                } catch (Exception e3) {
                    e2 = e3;
                }
            } catch (Exception e4) {
                i2 = 0;
                e2 = e4;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = bufferedInputStream.read(bArr, 0, 2048);
                if (read != -1) {
                    bVar.write(bArr, 0, read);
                } else {
                    com.bytedance.geckox.utils.d.close(bufferedInputStream);
                    return;
                }
            }
        } catch (Exception e5) {
            e2 = e5;
            bufferedInputStream2 = bufferedInputStream;
            throw new RuntimeException("downloadFile failed, code: " + i2 + ", url:" + str + ", caused by:" + e2.getMessage(), e2);
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream2 = bufferedInputStream;
            com.bytedance.geckox.utils.d.close(bufferedInputStream2);
            throw th;
        }
    }

    private Map<String, String> a(Headers headers) {
        if (headers == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : headers.names()) {
            hashMap.put(str, headers.get(str));
        }
        return hashMap;
    }
}
