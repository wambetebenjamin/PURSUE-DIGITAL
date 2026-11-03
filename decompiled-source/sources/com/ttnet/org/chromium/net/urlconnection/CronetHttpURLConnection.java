package com.ttnet.org.chromium.net.urlconnection;

import android.net.TrafficStats;
import android.os.Build;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.retrofit2.HttpMethodContrants;
import com.bytedance.sdk.account.utils.k;
import com.ss.android.ugc.quota.tag.a;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.Logger;
import com.ttnet.org.chromium.base.ParamChecker;
import com.ttnet.org.chromium.net.CronetEngine;
import com.ttnet.org.chromium.net.CronetException;
import com.ttnet.org.chromium.net.ExperimentalUrlRequest;
import com.ttnet.org.chromium.net.NetworkException;
import com.ttnet.org.chromium.net.RequestFinishedInfo;
import com.ttnet.org.chromium.net.UrlRequest;
import com.ttnet.org.chromium.net.UrlResponseInfo;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.http.conn.ConnectTimeoutException;
/* loaded from: classes3.dex */
public class CronetHttpURLConnection extends HttpURLConnection {
    private static final String BYPASS_COOKIE_HEADER = "x-ttnet-bypass-cookie";
    private static final String CONTENT_LENGTH = "Content-Length";
    private static final int DO_NOT_SEND_COOKIE = 2;
    private static final String GET_REQUEST_COOKIE_SOURCE_FLAG = "x-tt-get-cookie-source";
    public static final String SS_COOKIE = "X-SS-Cookie";
    private static final String TAG = "CronetHttpURLConnection";
    private static final String WEBVIEW_COOKIE = "webview-origin-url";
    private static final int WEBVIEW_REQUEST = 1;
    private String mAuthCertPassword;
    private String mAuthCertUsername;
    private CookieHandler mCookieHandler;
    private final CronetEngine mCronetEngine;
    private final AtomicBoolean mDisconnected;
    private IOException mException;
    private boolean mFixCancelRequestReport;
    private boolean mHasResponseHeadersOrCompleted;
    private CronetInputStream mInputStream;
    private int mInputStreamBufferSize;
    private final MessageLoop mMessageLoop;
    private boolean mOnRedirectCalled;
    private CronetOutputStream mOutputStream;
    private int mOutputStreamBufferSize;
    private UrlRequest mRequest;
    private final AtomicBoolean mRequestCompleted;
    private int mRequestCookieSource;
    private RequestFinishedInfo.Listener mRequestFinishedListener;
    private int mRequestFlag;
    private final List<Pair<String, String>> mRequestHeaders;
    private int mRequestPriority;
    private int mRequestTimeout;
    private int mRequestTypeFlags;
    private List<Map.Entry<String, String>> mResponseHeadersList;
    private Map<String, List<String>> mResponseHeadersMap;
    private UrlResponseInfo mResponseInfo;
    private int mSocketConnectTimeout;
    private int mSocketReadTimeout;
    private int mSocketWriteTimeout;
    private long mThrottleNetSpeed;
    private int mTrafficStatsTag;
    private boolean mTrafficStatsTagSet;
    private int mTrafficStatsUid;
    private boolean mTrafficStatsUidSet;

    @Override // java.net.HttpURLConnection
    public boolean usingProxy() {
        return false;
    }

    public CronetHttpURLConnection(URL url, CronetEngine cronetEngine, boolean z) {
        super(url);
        this.mDisconnected = new AtomicBoolean(false);
        this.mRequestCompleted = new AtomicBoolean(false);
        this.mFixCancelRequestReport = false;
        this.mCronetEngine = cronetEngine;
        this.mMessageLoop = new MessageLoop();
        if (z) {
            this.mInputStream = new CronetInputStreamWithTTBuffer(this);
        } else {
            this.mInputStream = new CronetInputStream(this);
        }
        this.mRequestHeaders = new ArrayList();
        this.mCookieHandler = CookieHandler.getDefault();
        this.mRequestCookieSource = -1;
    }

    @Override // java.net.URLConnection
    public void connect() {
        getOutputStream();
        startRequest();
    }

    @Override // java.net.HttpURLConnection
    public void disconnect() {
        UrlRequest urlRequest;
        if (!this.connected || (urlRequest = this.mRequest) == null) {
            return;
        }
        urlRequest.cancel();
        try {
            if (this.mDisconnected.compareAndSet(false, true) && this.mFixCancelRequestReport && !this.mRequestCompleted.get() && this.mMessageLoop.onValidThread() && !this.mMessageLoop.isRunning()) {
                this.mMessageLoop.loop(50);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // java.net.HttpURLConnection
    public String getResponseMessage() {
        getResponse();
        return this.mResponseInfo.getHttpStatusText();
    }

    @Override // java.net.HttpURLConnection
    public int getResponseCode() {
        getResponse();
        return this.mResponseInfo.getHttpStatusCode();
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getHeaderFields() {
        try {
            getResponse();
            return getAllHeaders();
        } catch (IOException unused) {
            return Collections.emptyMap();
        }
    }

    @Override // java.net.URLConnection
    public final String getHeaderField(String str) {
        try {
            getResponse();
            Map<String, List<String>> allHeaders = getAllHeaders();
            if (allHeaders.containsKey(str)) {
                List<String> list = allHeaders.get(str);
                return list.get(list.size() - 1);
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final String getHeaderFieldKey(int i2) {
        Map.Entry<String, String> headerFieldEntry = getHeaderFieldEntry(i2);
        if (headerFieldEntry == null) {
            return null;
        }
        return headerFieldEntry.getKey();
    }

    @Override // java.net.HttpURLConnection, java.net.URLConnection
    public final String getHeaderField(int i2) {
        Map.Entry<String, String> headerFieldEntry = getHeaderFieldEntry(i2);
        if (headerFieldEntry == null) {
            return null;
        }
        return headerFieldEntry.getValue();
    }

    @Override // java.net.URLConnection
    public InputStream getInputStream() {
        getResponse();
        if (!this.instanceFollowRedirects && this.mOnRedirectCalled) {
            throw new IOException("Cannot read response body of a redirect.");
        }
        if (this.mResponseInfo.getHttpStatusCode() >= 400) {
            throw new FileNotFoundException(this.url.toString());
        }
        return this.mInputStream;
    }

    @Override // java.net.URLConnection
    public OutputStream getOutputStream() {
        if (this.mOutputStream == null && this.doOutput) {
            if (this.connected) {
                throw new ProtocolException("Cannot write to OutputStream after receiving response.");
            }
            if (isChunkedUpload()) {
                this.mOutputStream = new CronetChunkedOutputStream(this, this.chunkLength, this.mMessageLoop);
                startRequest();
            } else {
                long streamingModeContentLength = getStreamingModeContentLength();
                if (streamingModeContentLength != -1) {
                    CronetFixedModeOutputStream cronetFixedModeOutputStream = new CronetFixedModeOutputStream(this, streamingModeContentLength, this.mOutputStreamBufferSize, this.mMessageLoop);
                    this.mOutputStream = cronetFixedModeOutputStream;
                    if (streamingModeContentLength == 0) {
                        setFixedLengthStreamingMode((int) cronetFixedModeOutputStream.getUploadDataProvider().getLength());
                        if (getRequestProperty("Content-Length") == null) {
                            addRequestProperty("Content-Length", Long.toString(this.mOutputStream.getUploadDataProvider().getLength()));
                        } else {
                            setRequestProperty("Content-Length", Long.toString(this.mOutputStream.getUploadDataProvider().getLength()));
                        }
                    }
                    startRequest();
                } else {
                    Log.d(TAG, "Outputstream is being buffered in memory.", new Object[0]);
                    String requestProperty = getRequestProperty("Content-Length");
                    if (requestProperty == null) {
                        this.mOutputStream = new CronetBufferedOutputStream(this);
                    } else {
                        this.mOutputStream = new CronetBufferedOutputStream(this, Long.parseLong(requestProperty));
                    }
                }
            }
        }
        return this.mOutputStream;
    }

    private long getStreamingModeContentLength() {
        return this.fixedContentLengthLong != -1 ? this.fixedContentLengthLong : this.fixedContentLength;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, String> GetCookieMap(URI uri) {
        HashMap hashMap = new HashMap();
        if ((this.mRequestTypeFlags & 2) > 0 || !isAllowCredentials()) {
            if (Logger.debug()) {
                Logger.d(TAG, "Do not read cookie from TTNet, url:" + uri);
            }
            return hashMap;
        }
        HashMap hashMap2 = new HashMap();
        List<Pair<String, String>> list = this.mRequestHeaders;
        if (list != null) {
            for (Pair<String, String> pair : list) {
                String str = (String) pair.first;
                String str2 = (String) pair.second;
                List list2 = (List) hashMap2.get(str);
                if (list2 == null) {
                    list2 = new LinkedList();
                }
                list2.add(str2);
                hashMap2.put(str, list2);
            }
        }
        hashMap2.put(GET_REQUEST_COOKIE_SOURCE_FLAG, new LinkedList());
        Map<String, List<String>> map = null;
        try {
            if (this.mCookieHandler == null) {
                this.mCookieHandler = CookieHandler.getDefault();
            }
            CookieHandler cookieHandler = this.mCookieHandler;
            if (cookieHandler != null) {
                map = cookieHandler.get(uri, hashMap2);
            }
        } catch (Exception e2) {
            if (Logger.debug()) {
                Logger.d(TAG, "Loading cookies failed for " + getURL().toString() + " e = " + e2.getMessage());
            }
        }
        if (map != null && map.size() > 0) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                String key = entry.getKey();
                int i2 = 0;
                if (GET_REQUEST_COOKIE_SOURCE_FLAG.equals(key) && !entry.getValue().isEmpty()) {
                    try {
                        this.mRequestCookieSource = Integer.parseInt(entry.getValue().get(0));
                    } catch (Throwable unused) {
                        this.mRequestCookieSource = -1;
                    }
                }
                StringBuilder sb = new StringBuilder();
                if ("X-SS-Cookie".equalsIgnoreCase(key) || "Cookie".equalsIgnoreCase(key) || "Cookie2".equalsIgnoreCase(key)) {
                    if (!entry.getValue().isEmpty()) {
                        for (String str3 : entry.getValue()) {
                            if (i2 > 0) {
                                sb.append("; ");
                            }
                            sb.append(str3);
                            i2++;
                        }
                        hashMap.put(key, sb.toString());
                    }
                }
            }
        }
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveResponseCookie(String str, Map<String, List<String>> map) {
        if (!isAllowCredentials()) {
            if (Logger.debug()) {
                Logger.d(TAG, "Do not write cookie from TTNet, url:" + str);
                return;
            }
            return;
        }
        if (Logger.debug()) {
            Logger.d(TAG, "Saving cookies for url: " + (TextUtils.isEmpty(str) ? getURL().toString() : str));
        }
        try {
            if (this.mCookieHandler == null) {
                this.mCookieHandler = CookieHandler.getDefault();
            }
            if (this.mCookieHandler != null) {
                if (TextUtils.isEmpty(str)) {
                    this.mCookieHandler.put(uri(getURL()), map);
                    return;
                }
                URI uri = uri(new URL(str));
                if ((this.mRequestTypeFlags & 1) == 1) {
                    TreeMap treeMap = new TreeMap(map);
                    treeMap.put("webview-origin-url", Collections.singletonList(getURL().getHost()));
                    this.mCookieHandler.put(uri, treeMap);
                    return;
                }
                this.mCookieHandler.put(uri, map);
            }
        } catch (Exception e2) {
            if (Logger.debug()) {
                String str2 = TAG;
                StringBuilder append = new StringBuilder().append("Saving cookies failed for ");
                if (TextUtils.isEmpty(str)) {
                    str = getURL().toString();
                }
                Logger.d(str2, append.append(str).append(" e = ").append(e2.getMessage()).toString());
            }
        }
    }

    private boolean isAllowCredentials() {
        String requestProperty = getRequestProperty("x-ttnet-bypass-cookie");
        return TextUtils.isEmpty(requestProperty) || !"0".equals(requestProperty);
    }

    public void configureConnection(String str, Object... objArr) {
        str.hashCode();
        char c2 = 65535;
        switch (str.hashCode()) {
            case -2098478356:
                if (str.equals("setSocketConnectTimeout")) {
                    c2 = 0;
                    break;
                }
                break;
            case -1400774487:
                if (str.equals("setInputStreamBufferSize")) {
                    c2 = 1;
                    break;
                }
                break;
            case -1262337831:
                if (str.equals("setRequestFlag")) {
                    c2 = 2;
                    break;
                }
                break;
            case -976756188:
                if (str.equals("setOutputStreamBufferSize")) {
                    c2 = 3;
                    break;
                }
                break;
            case -722926986:
                if (str.equals("setThrottleNetSpeed")) {
                    c2 = 4;
                    break;
                }
                break;
            case -534987466:
                if (str.equals("setSocketReadTimeout")) {
                    c2 = 5;
                    break;
                }
                break;
            case -107284268:
                if (str.equals("setRequestTimeout")) {
                    c2 = 6;
                    break;
                }
                break;
            case 166051698:
                if (str.equals("setAuthCredentials")) {
                    c2 = 7;
                    break;
                }
                break;
            case 423591456:
                if (str.equals("setRequestTypeFlags")) {
                    c2 = '\b';
                    break;
                }
                break;
            case 1221363607:
                if (str.equals("setSocketWriteTimeout")) {
                    c2 = '\t';
                    break;
                }
                break;
            case 1880243345:
                if (str.equals("setRequestPriority")) {
                    c2 = '\n';
                    break;
                }
                break;
            case 2141877079:
                if (str.equals("enableFixCancelRequestReport")) {
                    c2 = 11;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mSocketConnectTimeout = ((Integer) objArr[0]).intValue();
                return;
            case 1:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mInputStreamBufferSize = ((Integer) objArr[0]).intValue();
                return;
            case 2:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mRequestFlag = ((Integer) objArr[0]).intValue();
                return;
            case 3:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mOutputStreamBufferSize = ((Integer) objArr[0]).intValue();
                return;
            case 4:
                ParamChecker.check(new Class[]{Long.TYPE}, objArr);
                long longValue = ((Long) objArr[0]).longValue();
                this.mThrottleNetSpeed = longValue;
                UrlRequest urlRequest = this.mRequest;
                if (urlRequest != null) {
                    urlRequest.setThrottleNetSpeed(longValue);
                    return;
                }
                return;
            case 5:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mSocketReadTimeout = ((Integer) objArr[0]).intValue();
                return;
            case 6:
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mRequestTimeout = ((Integer) objArr[0]).intValue();
                return;
            case 7:
                ParamChecker.check(new Class[]{String.class, String.class}, objArr);
                this.mAuthCertUsername = (String) objArr[0];
                this.mAuthCertPassword = (String) objArr[1];
                return;
            case '\b':
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mRequestTypeFlags = ((Integer) objArr[0]).intValue();
                return;
            case '\t':
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mSocketWriteTimeout = ((Integer) objArr[0]).intValue();
                return;
            case '\n':
                ParamChecker.check(new Class[]{Integer.TYPE}, objArr);
                this.mRequestPriority = ((Integer) objArr[0]).intValue();
                return;
            case 11:
                ParamChecker.check(new Class[0], objArr);
                this.mFixCancelRequestReport = true;
                return;
            default:
                throw new IllegalArgumentException("Unknown method name: " + str);
        }
    }

    public void setRequestPriority(int i2) {
        this.mRequestPriority = i2;
    }

    public void setSocketConnectTimeout(int i2) {
        this.mSocketConnectTimeout = i2;
    }

    public void setSocketReadTimeout(int i2) {
        this.mSocketReadTimeout = i2;
    }

    public void setSocketWriteTimeout(int i2) {
        this.mSocketWriteTimeout = i2;
    }

    public void setRequestTimeout(int i2) {
        this.mRequestTimeout = i2;
    }

    public void setThrottleNetSpeed(long j2) {
        this.mThrottleNetSpeed = j2;
        UrlRequest urlRequest = this.mRequest;
        if (urlRequest != null) {
            urlRequest.setThrottleNetSpeed(j2);
        }
    }

    public void setRequestTypeFlags(int i2) {
        this.mRequestTypeFlags = i2 | this.mRequestTypeFlags;
    }

    public void setInputStreamBufferSize(int i2) {
        this.mInputStreamBufferSize = i2;
    }

    public void setOutputStreamBufferSize(int i2) {
        this.mOutputStreamBufferSize = i2;
    }

    public int getInputStreamBufferSize() {
        return this.mInputStreamBufferSize;
    }

    public String getFinalUrl() {
        return this.mRequest.getFinalUrl();
    }

    public void onUploadTimeout() {
        UrlRequest urlRequest = this.mRequest;
        if (urlRequest != null) {
            urlRequest.appTimeout();
        }
    }

    public void setRequestFlag(int i2) {
        this.mRequestFlag = i2;
    }

    public void setAuthCredentials(String str, String str2) {
        this.mAuthCertUsername = str;
        this.mAuthCertPassword = str2;
    }

    public void enableFixCancelRequestReport() {
        this.mFixCancelRequestReport = true;
    }

    public void setRequestFinishedListener(RequestFinishedInfo.Listener listener) {
        this.mRequestFinishedListener = listener;
    }

    public int getCronetInternalErrorCode() {
        IOException iOException = this.mException;
        if (iOException instanceof NetworkException) {
            return ((NetworkException) iOException).getCronetInternalErrorCode();
        }
        if (iOException instanceof TTRequestCanceledException) {
            return ((TTRequestCanceledException) iOException).getCronetInternalErrorCode();
        }
        return 0;
    }

    @Override // java.net.HttpURLConnection
    public void setRequestMethod(String str) {
        if (HttpMethodContrants.PATCH.equals(str)) {
            if (this.connected) {
                throw new ProtocolException("Can't reset method: already connected");
            }
            this.method = str;
            return;
        }
        super.setRequestMethod(str);
    }

    public URI uri(URL url) {
        if (url == null) {
            return null;
        }
        String url2 = url.toString();
        try {
            return new URI(url2);
        } catch (URISyntaxException e2) {
            try {
                return URI.create(url2.replaceAll("[\\u0000-\\u001F\\u007F-\\u009F\\p{javaWhitespace}]", ""));
            } catch (Exception unused) {
                try {
                    return URI.create(url2.substring(0, url2.indexOf("?")));
                } catch (Exception unused2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public IOException tryConvertCronetException(CronetException cronetException) {
        IOException unknownHostException;
        if (cronetException instanceof NetworkException) {
            switch (((NetworkException) cronetException).getErrorCode()) {
                case 1:
                    unknownHostException = new UnknownHostException(cronetException.getMessage());
                    break;
                case 2:
                case 3:
                    unknownHostException = new ConnectException(cronetException.getMessage());
                    break;
                case 4:
                    unknownHostException = new SocketTimeoutException(cronetException.getMessage());
                    break;
                case 5:
                case 7:
                    unknownHostException = new ConnectException(cronetException.getMessage());
                    break;
                case 6:
                    unknownHostException = new ConnectTimeoutException(cronetException.getMessage());
                    break;
                case 8:
                    unknownHostException = new SocketException("reset by peer " + cronetException.getMessage());
                    break;
                case 9:
                    unknownHostException = new NoRouteToHostException(cronetException.getMessage());
                    break;
                default:
                    return cronetException;
            }
            return unknownHostException;
        }
        return cronetException;
    }

    private void executeMessageLoop(int i2) {
        try {
            this.mMessageLoop.loop(i2);
        } catch (SocketTimeoutException unused) {
            UrlRequest urlRequest = this.mRequest;
            if (urlRequest != null) {
                urlRequest.appTimeout();
                this.mMessageLoop.reset();
                this.mMessageLoop.loop(i2 / 2);
            }
        } catch (Exception e2) {
            cancelWithException("Unexpected request usage, caught in CronetHttpURLConnection.", e2);
            this.mMessageLoop.reset();
            this.mMessageLoop.loop(i2 / 2);
        }
    }

    public void cancelWithException(String str, Throwable th) {
        UrlRequest urlRequest = this.mRequest;
        if (urlRequest != null) {
            urlRequest.cancelWithException(str, th);
        }
    }

    private void startRequest() {
        String str;
        if (this.connected) {
            return;
        }
        if (Logger.debug()) {
            Logger.d(TAG, "start request url = " + getURL().toString());
        }
        ExperimentalUrlRequest.Builder builder = (ExperimentalUrlRequest.Builder) this.mCronetEngine.newUrlRequestBuilder(getURL().toString(), new CronetUrlRequestCallback(), this.mMessageLoop);
        if (this.doOutput) {
            if (this.method.equals("GET")) {
                this.method = "POST";
            }
            CronetOutputStream cronetOutputStream = this.mOutputStream;
            if (cronetOutputStream != null) {
                builder.setUploadDataProvider(cronetOutputStream.getUploadDataProvider(), (Executor) this.mMessageLoop);
                if (getRequestProperty("Content-Length") == null && !isChunkedUpload()) {
                    addRequestProperty("Content-Length", Long.toString(this.mOutputStream.getUploadDataProvider().getLength()));
                }
                this.mOutputStream.setConnected();
            } else if (getRequestProperty("Content-Length") == null) {
                addRequestProperty("Content-Length", "0");
            }
            if (getRequestProperty("Content-Type") == null) {
                addRequestProperty("Content-Type", k.CONTENT_TYPE);
            }
        }
        long nanoTime = System.nanoTime();
        Map<String, String> GetCookieMap = GetCookieMap(uri(getURL()));
        if (GetCookieMap != null && !GetCookieMap.isEmpty()) {
            for (Map.Entry<String, String> entry : GetCookieMap.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
        long nanoTime2 = (System.nanoTime() - nanoTime) / 1000;
        for (Pair<String, String> pair : this.mRequestHeaders) {
            builder.addHeader((String) pair.first, (String) pair.second);
        }
        if (!getUseCaches()) {
            builder.disableCache();
        }
        builder.setHttpMethod(this.method);
        if (checkTrafficStatsTag()) {
            builder.setTrafficStatsTag(this.mTrafficStatsTag);
        }
        if (checkTrafficStatsUid()) {
            builder.setTrafficStatsUid(this.mTrafficStatsUid);
        }
        builder.setPriority(this.mRequestPriority);
        builder.setSocketConnectTimeout(this.mSocketConnectTimeout);
        builder.setSocketReadTimeout(this.mSocketReadTimeout);
        builder.setSocketWriteTimeout(this.mSocketWriteTimeout);
        builder.setRequestTimeout(this.mRequestTimeout);
        builder.setRequestFinishedListener(this.mRequestFinishedListener);
        ExperimentalUrlRequest build = builder.build();
        this.mRequest = build;
        int i2 = this.mRequestFlag;
        if (i2 != 0) {
            build.setRequestFlag(i2);
        }
        int i3 = this.mRequestTypeFlags;
        if (i3 > 0) {
            this.mRequest.setRequestTypeFlags(i3);
        }
        String str2 = this.mAuthCertUsername;
        if (str2 != null && (str = this.mAuthCertPassword) != null) {
            this.mRequest.setAuthCredentials(str2, str);
        }
        this.mRequest.setAddCookieCostUs(nanoTime2);
        this.mRequest.setRequestCookieSource(this.mRequestCookieSource);
        this.mRequest.start();
        long j2 = this.mThrottleNetSpeed;
        if (j2 > 0) {
            this.mRequest.setThrottleNetSpeed(j2);
        }
        this.connected = true;
    }

    private boolean checkTrafficStatsTag() {
        if (this.mTrafficStatsTagSet) {
            return true;
        }
        int threadStatsTag = TrafficStats.getThreadStatsTag();
        if (threadStatsTag != -1) {
            this.mTrafficStatsTag = threadStatsTag;
            this.mTrafficStatsTagSet = true;
        }
        return this.mTrafficStatsTagSet;
    }

    private boolean checkTrafficStatsUid() {
        if (this.mTrafficStatsUidSet) {
            return true;
        }
        if (Build.VERSION.SDK_INT < 28) {
            return false;
        }
        int threadStatsUid = TrafficStats.getThreadStatsUid();
        if (threadStatsUid != -1) {
            this.mTrafficStatsUid = threadStatsUid;
            this.mTrafficStatsUidSet = true;
        }
        return this.mTrafficStatsUidSet;
    }

    @Override // java.net.HttpURLConnection
    public InputStream getErrorStream() {
        try {
            getResponse();
            if (this.mResponseInfo.getHttpStatusCode() >= 400) {
                return this.mInputStream;
            }
            return null;
        } catch (IOException unused) {
            return null;
        }
    }

    @Override // java.net.URLConnection
    public final void addRequestProperty(String str, String str2) {
        setRequestPropertyInternal(str, str2, false);
    }

    @Override // java.net.URLConnection
    public final void setRequestProperty(String str, String str2) {
        setRequestPropertyInternal(str, str2, true);
    }

    private final void setRequestPropertyInternal(String str, String str2, boolean z) {
        if (this.connected) {
            throw new IllegalStateException("Cannot modify request property after connection is made.");
        }
        int findRequestProperty = findRequestProperty(str);
        if (findRequestProperty >= 0) {
            if (z) {
                this.mRequestHeaders.remove(findRequestProperty);
            } else {
                throw new UnsupportedOperationException("Cannot add multiple headers of the same key, " + str + ". crbug.com/432719.");
            }
        }
        this.mRequestHeaders.add(Pair.create(str, str2));
    }

    @Override // java.net.URLConnection
    public Map<String, List<String>> getRequestProperties() {
        if (this.connected) {
            throw new IllegalStateException("Cannot access request headers after connection is set.");
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Pair<String, String> pair : this.mRequestHeaders) {
            if (treeMap.containsKey(pair.first)) {
                throw new IllegalStateException("Should not have multiple values.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add((String) pair.second);
            treeMap.put((String) pair.first, Collections.unmodifiableList(arrayList));
        }
        return Collections.unmodifiableMap(treeMap);
    }

    @Override // java.net.URLConnection
    public String getRequestProperty(String str) {
        int findRequestProperty = findRequestProperty(str);
        if (findRequestProperty >= 0) {
            return (String) this.mRequestHeaders.get(findRequestProperty).second;
        }
        return null;
    }

    @Override // java.net.URLConnection
    public void setConnectTimeout(int i2) {
        super.setConnectTimeout(i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void getMoreData(ByteBuffer byteBuffer) {
        UrlRequest urlRequest = this.mRequest;
        if (urlRequest == null) {
            return;
        }
        urlRequest.read(byteBuffer);
        executeMessageLoop(getReadTimeout());
    }

    public void setTrafficStatsTag(int i2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot modify traffic stats tag after connection is made.");
        }
        this.mTrafficStatsTagSet = true;
        this.mTrafficStatsTag = i2;
    }

    public void setTrafficStatsUid(int i2) {
        if (this.connected) {
            throw new IllegalStateException("Cannot modify traffic stats UID after connection is made.");
        }
        this.mTrafficStatsUidSet = true;
        this.mTrafficStatsUid = i2;
    }

    private int findRequestProperty(String str) {
        for (int i2 = 0; i2 < this.mRequestHeaders.size(); i2++) {
            if (((String) this.mRequestHeaders.get(i2).first).equalsIgnoreCase(str)) {
                return i2;
            }
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes3.dex */
    public class CronetUrlRequestCallback extends UrlRequest.Callback {
        public CronetUrlRequestCallback() {
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            CronetHttpURLConnection.this.mHasResponseHeadersOrCompleted = true;
            if (CronetHttpURLConnection.this.mResponseInfo != null) {
                long nanoTime = System.nanoTime();
                CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
                cronetHttpURLConnection.saveResponseCookie(str, cronetHttpURLConnection.getAllHeaders());
                long nanoTime2 = (System.nanoTime() - nanoTime) / 1000;
                if (urlRequest != null) {
                    urlRequest.setSaveCookieCostUs(nanoTime2);
                }
            }
            if (Logger.debug()) {
                Logger.d(CronetHttpURLConnection.TAG, "onResponseStarted loop quit url = " + CronetHttpURLConnection.this.getURL().toString());
            }
            CronetHttpURLConnection.this.mMessageLoop.quit();
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            CronetHttpURLConnection.this.mMessageLoop.quit();
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str, String str2) {
            boolean z = true;
            CronetHttpURLConnection.this.mOnRedirectCalled = true;
            if (urlResponseInfo != null) {
                CronetHttpURLConnection.this.saveResponseCookie(str2, urlResponseInfo.getAllHeaders());
            }
            URL url = CronetHttpURLConnection.this.url;
            if (!TextUtils.isEmpty(str2)) {
                try {
                    url = new URL(str2);
                } catch (Throwable unused) {
                }
            }
            try {
                URL url2 = new URL(str);
                if (CronetHttpURLConnection.this.instanceFollowRedirects) {
                    CronetHttpURLConnection.this.url = url2;
                }
                if (CronetHttpURLConnection.this.instanceFollowRedirects) {
                    if (CronetHttpURLConnection.this.mRequest != null) {
                        if (!"http".equals(url2.getProtocol()) || !"https".equals(url.getProtocol())) {
                            z = false;
                        }
                        if (!url2.getHost().equals(url.getHost()) || z) {
                            CronetHttpURLConnection.this.mRequest.RemoveRequestCookieHeader();
                            URL url3 = new URL(str);
                            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
                            Map GetCookieMap = cronetHttpURLConnection.GetCookieMap(cronetHttpURLConnection.uri(url3));
                            if (GetCookieMap != null && !GetCookieMap.isEmpty()) {
                                for (Map.Entry entry : GetCookieMap.entrySet()) {
                                    CronetHttpURLConnection.this.mRequest.AddRequestCookieHeader((String) entry.getKey(), (String) entry.getValue());
                                }
                            }
                        }
                        CronetHttpURLConnection.this.mRequest.followRedirect();
                        return;
                    }
                    return;
                }
            } catch (MalformedURLException unused2) {
            }
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            if (CronetHttpURLConnection.this.mRequest != null) {
                CronetHttpURLConnection.this.mRequest.cancel();
            }
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            setResponseDataCompleted(null);
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            if (cronetException == null) {
                throw new IllegalStateException("Exception cannot be null in onFailed.");
            }
            CronetHttpURLConnection cronetHttpURLConnection = CronetHttpURLConnection.this;
            cronetHttpURLConnection.mException = cronetHttpURLConnection.tryConvertCronetException(cronetException);
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            setResponseDataCompleted(cronetException);
        }

        @Override // com.ttnet.org.chromium.net.UrlRequest.Callback
        public void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
            CronetHttpURLConnection.this.mResponseInfo = urlResponseInfo;
            if (Logger.debug()) {
                Logger.d(CronetHttpURLConnection.TAG, "onCanceled loop quit url = " + CronetHttpURLConnection.this.getURL().toString());
            }
            if (CronetHttpURLConnection.this.mException == null && !CronetHttpURLConnection.this.mOnRedirectCalled) {
                StringBuilder sb = new StringBuilder("request canceled");
                sb.append(", ErrorCode=").append(11).append(", InternalErrorCode=").append(a.UNSPECIFIED);
                CronetHttpURLConnection.this.mException = new TTRequestCanceledException(sb.toString());
            }
            setResponseDataCompleted(CronetHttpURLConnection.this.mException);
        }

        private void setResponseDataCompleted(IOException iOException) {
            CronetHttpURLConnection.this.mException = iOException;
            if (CronetHttpURLConnection.this.mInputStream != null) {
                CronetHttpURLConnection.this.mInputStream.setResponseDataCompleted(iOException);
            }
            if (CronetHttpURLConnection.this.mOutputStream != null) {
                CronetHttpURLConnection.this.mOutputStream.setRequestCompleted(iOException);
            }
            CronetHttpURLConnection.this.mHasResponseHeadersOrCompleted = true;
            CronetHttpURLConnection.this.mRequestCompleted.set(true);
            CronetHttpURLConnection.this.mMessageLoop.quit();
        }
    }

    private void getResponse() {
        CronetOutputStream cronetOutputStream = this.mOutputStream;
        if (cronetOutputStream != null) {
            cronetOutputStream.checkReceivedEnoughContent();
            if (isChunkedUpload()) {
                this.mOutputStream.close();
            }
        }
        if (!this.mHasResponseHeadersOrCompleted) {
            startRequest();
            if (Logger.debug()) {
                Logger.d(TAG, "getResponse loop url = " + getURL().toString());
            }
            executeMessageLoop(getConnectTimeout() + getReadTimeout());
            this.mHasResponseHeadersOrCompleted = true;
        }
        checkHasResponseHeaders();
    }

    private void checkHasResponseHeaders() {
        if (!this.mHasResponseHeadersOrCompleted) {
            throw new IllegalStateException("No response.");
        }
        IOException iOException = this.mException;
        if (iOException != null) {
            throw iOException;
        }
        Objects.requireNonNull(this.mResponseInfo, "Response info is null when there is no exception.");
    }

    private Map.Entry<String, String> getHeaderFieldEntry(int i2) {
        try {
            getResponse();
            List<Map.Entry<String, String>> allHeadersAsList = getAllHeadersAsList();
            if (i2 >= allHeadersAsList.size()) {
                return null;
            }
            return allHeadersAsList.get(i2);
        } catch (IOException unused) {
            return null;
        }
    }

    private boolean isChunkedUpload() {
        return this.chunkLength > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, List<String>> getAllHeaders() {
        Map<String, List<String>> map = this.mResponseHeadersMap;
        if (map != null) {
            return map;
        }
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (Map.Entry<String, String> entry : getAllHeadersAsList()) {
            ArrayList arrayList = new ArrayList();
            if (treeMap.containsKey(entry.getKey())) {
                arrayList.addAll((Collection) treeMap.get(entry.getKey()));
            }
            arrayList.add(entry.getValue());
            treeMap.put(entry.getKey(), Collections.unmodifiableList(arrayList));
        }
        Map<String, List<String>> unmodifiableMap = Collections.unmodifiableMap(treeMap);
        this.mResponseHeadersMap = unmodifiableMap;
        return unmodifiableMap;
    }

    private List<Map.Entry<String, String>> getAllHeadersAsList() {
        List<Map.Entry<String, String>> list = this.mResponseHeadersList;
        if (list != null) {
            return list;
        }
        this.mResponseHeadersList = new ArrayList();
        for (Map.Entry<String, String> entry : this.mResponseInfo.getAllHeadersAsList()) {
            this.mResponseHeadersList.add(new AbstractMap.SimpleImmutableEntry(entry));
        }
        List<Map.Entry<String, String>> unmodifiableList = Collections.unmodifiableList(this.mResponseHeadersList);
        this.mResponseHeadersList = unmodifiableList;
        return unmodifiableList;
    }
}
