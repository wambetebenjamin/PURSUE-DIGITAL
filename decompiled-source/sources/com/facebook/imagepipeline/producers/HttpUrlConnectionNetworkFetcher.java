package com.facebook.imagepipeline.producers;

import android.net.Uri;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Locale;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
/* loaded from: classes8.dex */
public class HttpUrlConnectionNetworkFetcher extends BaseNetworkFetcher<FetchState> {
    public static final int HTTP_DEFAULT_TIMEOUT = 30000;
    public static final int HTTP_PERMANENT_REDIRECT = 308;
    public static final int HTTP_TEMPORARY_REDIRECT = 307;
    private static final int MAX_REDIRECTS = 5;
    private static final int NUM_NETWORK_THREADS = 3;
    private final ExecutorService mExecutorService;
    private int mHttpConnectionTimeout;

    private static boolean isHttpRedirect(int i2) {
        if (i2 == 307 || i2 == 308) {
            return true;
        }
        switch (i2) {
            case 300:
            case 301:
            case 302:
            case 303:
                return true;
            default:
                return false;
        }
    }

    private static boolean isHttpSuccess(int i2) {
        return i2 >= 200 && i2 < 300;
    }

    public HttpUrlConnectionNetworkFetcher() {
        this(Executors.newFixedThreadPool(3));
    }

    public HttpUrlConnectionNetworkFetcher(int i2) {
        this(Executors.newFixedThreadPool(3));
        this.mHttpConnectionTimeout = i2;
    }

    HttpUrlConnectionNetworkFetcher(ExecutorService executorService) {
        this.mExecutorService = executorService;
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public FetchState createFetchState(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        return new FetchState(consumer, producerContext);
    }

    @Override // com.facebook.imagepipeline.producers.NetworkFetcher
    public void fetch(final FetchState fetchState, final NetworkFetcher.Callback callback) {
        final Future<?> submit = this.mExecutorService.submit(new Runnable() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.1
            @Override // java.lang.Runnable
            public void run() {
                HttpUrlConnectionNetworkFetcher.this.fetchSync(fetchState, callback);
            }
        });
        fetchState.getContext().addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                if (submit.cancel(false)) {
                    callback.onCancellation();
                }
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.facebook.imagepipeline.producers.FetchState] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.net.HttpURLConnection] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    void fetchSync(com.facebook.imagepipeline.producers.FetchState r3, com.facebook.imagepipeline.producers.NetworkFetcher.Callback r4) {
        /*
            r2 = this;
            r0 = 0
            android.net.Uri r3 = r3.getUri()     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L22
            r1 = 5
            java.net.HttpURLConnection r3 = r2.downloadFrom(r3, r1)     // Catch: java.lang.Throwable -> L1f java.io.IOException -> L22
            if (r3 == 0) goto L17
            java.io.InputStream r0 = r3.getInputStream()     // Catch: java.io.IOException -> L15 java.lang.Throwable -> L32
            r1 = -1
            r4.onResponse(r0, r1)     // Catch: java.io.IOException -> L15 java.lang.Throwable -> L32
            goto L17
        L15:
            r1 = move-exception
            goto L24
        L17:
            if (r0 == 0) goto L1c
            r0.close()     // Catch: java.io.IOException -> L1c
        L1c:
            if (r3 == 0) goto L31
            goto L2e
        L1f:
            r4 = move-exception
            r3 = r0
            goto L33
        L22:
            r1 = move-exception
            r3 = r0
        L24:
            r4.onFailure(r1)     // Catch: java.lang.Throwable -> L32
            if (r0 == 0) goto L2c
            r0.close()     // Catch: java.io.IOException -> L2c
        L2c:
            if (r3 == 0) goto L31
        L2e:
            r3.disconnect()
        L31:
            return
        L32:
            r4 = move-exception
        L33:
            if (r0 == 0) goto L38
            r0.close()     // Catch: java.io.IOException -> L38
        L38:
            if (r3 == 0) goto L3d
            r3.disconnect()
        L3d:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher.fetchSync(com.facebook.imagepipeline.producers.FetchState, com.facebook.imagepipeline.producers.NetworkFetcher$Callback):void");
    }

    private HttpURLConnection downloadFrom(Uri uri, int i2) {
        HttpURLConnection openConnectionTo = openConnectionTo(uri);
        openConnectionTo.setConnectTimeout(this.mHttpConnectionTimeout);
        int responseCode = openConnectionTo.getResponseCode();
        if (isHttpSuccess(responseCode)) {
            return openConnectionTo;
        }
        if (isHttpRedirect(responseCode)) {
            String headerField = openConnectionTo.getHeaderField("Location");
            openConnectionTo.disconnect();
            Uri parse = headerField == null ? null : Uri.parse(headerField);
            String scheme = uri.getScheme();
            if (i2 > 0 && parse != null && !parse.getScheme().equals(scheme)) {
                return downloadFrom(parse, i2 - 1);
            }
            throw new IOException(i2 == 0 ? error("URL %s follows too many redirects", uri.toString()) : error("URL %s returned %d without a valid redirect", uri.toString(), Integer.valueOf(responseCode)));
        }
        openConnectionTo.disconnect();
        throw new IOException(String.format("Image URL %s returned HTTP code %d", uri.toString(), Integer.valueOf(responseCode)));
    }

    static HttpURLConnection openConnectionTo(Uri uri) {
        return (HttpURLConnection) UriUtil.uriToUrl(uri).openConnection();
    }

    private static String error(String str, Object... objArr) {
        return String.format(Locale.getDefault(), str, objArr);
    }
}
