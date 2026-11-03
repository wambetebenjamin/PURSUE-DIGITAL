package com.squareup.picasso;

import android.content.Context;
import android.net.Uri;
import com.bytedance.apm.constant.r;
import com.squareup.okhttp.CacheControl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;
import com.squareup.picasso.Downloader;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public class OkHttpDownloader implements Downloader {
    private final OkHttpClient client;

    private static OkHttpClient defaultOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout((long) r.APP_START_MAX_LIMIT_MS, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout((long) r.PAGE_LOAD_MAX_LIMIT_MS, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout((long) r.PAGE_LOAD_MAX_LIMIT_MS, TimeUnit.MILLISECONDS);
        return okHttpClient;
    }

    public OkHttpDownloader(Context context) {
        this(Utils.createDefaultCacheDir(context));
    }

    public OkHttpDownloader(File file) {
        this(file, Utils.calculateDiskCacheSize(file));
    }

    public OkHttpDownloader(Context context, long j2) {
        this(Utils.createDefaultCacheDir(context), j2);
    }

    public OkHttpDownloader(File file, long j2) {
        this(defaultOkHttpClient());
        try {
            this.client.setCache(new com.squareup.okhttp.Cache(file, j2));
        } catch (IOException unused) {
        }
    }

    public OkHttpDownloader(OkHttpClient okHttpClient) {
        this.client = okHttpClient;
    }

    protected final OkHttpClient getClient() {
        return this.client;
    }

    @Override // com.squareup.picasso.Downloader
    public Downloader.Response load(Uri uri, int i2) throws IOException {
        CacheControl cacheControl;
        if (i2 == 0) {
            cacheControl = null;
        } else if (NetworkPolicy.isOfflineOnly(i2)) {
            cacheControl = CacheControl.FORCE_CACHE;
        } else {
            CacheControl.Builder builder = new CacheControl.Builder();
            if (!NetworkPolicy.shouldReadFromDiskCache(i2)) {
                builder.noCache();
            }
            if (!NetworkPolicy.shouldWriteToDiskCache(i2)) {
                builder.noStore();
            }
            cacheControl = builder.build();
        }
        Request.Builder url = new Request.Builder().url(uri.toString());
        if (cacheControl != null) {
            url.cacheControl(cacheControl);
        }
        Response execute = this.client.newCall(url.build()).execute();
        int code = execute.code();
        if (code >= 300) {
            execute.body().close();
            throw new Downloader.ResponseException(code + " " + execute.message(), i2, code);
        }
        boolean z = execute.cacheResponse() != null;
        ResponseBody body = execute.body();
        return new Downloader.Response(body.byteStream(), z, body.contentLength());
    }

    @Override // com.squareup.picasso.Downloader
    public void shutdown() {
        com.squareup.okhttp.Cache cache = this.client.getCache();
        if (cache != null) {
            try {
                cache.close();
            } catch (IOException unused) {
            }
        }
    }
}
