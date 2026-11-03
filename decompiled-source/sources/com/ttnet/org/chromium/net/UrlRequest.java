package com.ttnet.org.chromium.net;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
/* loaded from: classes9.dex */
public abstract class UrlRequest {

    /* loaded from: classes9.dex */
    public static abstract class Builder {
        public static final int REQUEST_PRIORITY_HIGHEST = 4;
        public static final int REQUEST_PRIORITY_IDLE = 0;
        public static final int REQUEST_PRIORITY_LOW = 2;
        public static final int REQUEST_PRIORITY_LOWEST = 1;
        public static final int REQUEST_PRIORITY_MEDIUM = 3;

        public abstract Builder addHeader(String str, String str2);

        public abstract Builder allowDirectExecutor();

        public abstract UrlRequest build();

        public abstract Builder disableCache();

        public abstract Builder setHttpMethod(String str);

        public abstract Builder setPriority(int i2);

        public abstract Builder setRequestTimeout(int i2);

        public abstract Builder setSocketConnectTimeout(int i2);

        public abstract Builder setSocketReadTimeout(int i2);

        public abstract Builder setSocketWriteTimeout(int i2);

        public abstract Builder setUploadDataProvider(UploadDataProvider uploadDataProvider, Executor executor);
    }

    /* loaded from: classes9.dex */
    public static abstract class Callback {
        public void onCanceled(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo) {
        }

        public abstract void onFailed(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, CronetException cronetException);

        public abstract void onReadCompleted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer);

        public abstract void onRedirectReceived(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str, String str2);

        public abstract void onResponseStarted(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo, String str);

        public abstract void onSucceeded(UrlRequest urlRequest, UrlResponseInfo urlResponseInfo);
    }

    /* loaded from: classes9.dex */
    public static abstract class StatusListener {
        public abstract void onStatus(int i2);
    }

    public abstract void AddRequestCookieHeader(String str, String str2);

    public abstract void RemoveRequestCookieHeader();

    public void appTimeout() {
    }

    public abstract void cancel();

    public void cancelWithException(String str, Throwable th) {
    }

    public abstract void followRedirect();

    public abstract String getFinalUrl();

    public abstract void getStatus(StatusListener statusListener);

    public abstract boolean isDone();

    public abstract void read(ByteBuffer byteBuffer);

    public abstract void setAddCookieCostUs(long j2);

    public abstract void setAuthCredentials(String str, String str2);

    public abstract void setRequestCookieSource(int i2);

    public void setRequestFlag(int i2) {
    }

    public abstract void setRequestTypeFlags(int i2);

    public abstract void setSaveCookieCostUs(long j2);

    public abstract void setThrottleNetSpeed(long j2);

    public abstract void start();

    /* loaded from: classes9.dex */
    public static class Status {
        public static final int CONNECTING = 10;
        public static final int DOWNLOADING_PAC_FILE = 5;
        public static final int ESTABLISHING_PROXY_TUNNEL = 8;
        public static final int IDLE = 0;
        public static final int INVALID = -1;
        public static final int READING_RESPONSE = 14;
        public static final int RESOLVING_HOST = 9;
        public static final int RESOLVING_HOST_IN_PAC_FILE = 7;
        public static final int RESOLVING_PROXY_FOR_URL = 6;
        public static final int SENDING_REQUEST = 12;
        public static final int SSL_HANDSHAKE = 11;
        public static final int WAITING_FOR_AVAILABLE_SOCKET = 2;
        public static final int WAITING_FOR_CACHE = 4;
        public static final int WAITING_FOR_DELEGATE = 3;
        public static final int WAITING_FOR_RESPONSE = 13;
        public static final int WAITING_FOR_STALLED_SOCKET_POOL = 1;

        private Status() {
        }
    }
}
