package com.bytedancehttpdns.httpdns.util;

import android.text.TextUtils;
import com.ttnet.org.chromium.net.NetError;
import java.io.IOException;
import java.net.BindException;
import java.net.ConnectException;
import java.net.HttpRetryException;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.PortUnreachableException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLKeyException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
/* loaded from: classes8.dex */
public class HttpsURLConnClient {
    private static final String KHTTPDNSKeyTimeStamp = "X-HTTPDNS-Key-Timestamp";
    private static final String KHTTPDNSTmpKey = "X-HTTPDNS-Tmp-Key";
    private static final String TAG = "veHttpDns";

    /* JADX WARN: Removed duplicated region for block: B:57:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x013e A[Catch: IOException -> 0x013a, TRY_LEAVE, TryCatch #0 {IOException -> 0x013a, blocks: (B:59:0x0136, B:63:0x013e), top: B:67:0x0136 }] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0136 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedancehttpdns.httpdns.HttpDnsResponse executeGet(java.lang.String r8, java.lang.String r9) {
        /*
            Method dump skipped, instructions count: 326
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedancehttpdns.httpdns.util.HttpsURLConnClient.executeGet(java.lang.String, java.lang.String):com.bytedancehttpdns.httpdns.HttpDnsResponse");
    }

    public static int convertExceptionToErrorCode(IOException iOException) {
        if (iOException instanceof SSLHandshakeException) {
            return NetError.ERR_SSL_HANDSHAKE_NOT_COMPLETED;
        }
        if (iOException instanceof SSLKeyException) {
            return NetError.ERR_SSL_BAD_PEER_PUBLIC_KEY;
        }
        if (iOException instanceof SSLProtocolException) {
            return NetError.ERR_SSL_PROTOCOL_ERROR;
        }
        if (iOException instanceof SSLPeerUnverifiedException) {
            return NetError.ERR_SSL_DECRYPT_ERROR_ALERT;
        }
        if (iOException instanceof UnknownHostException) {
            return NetError.ERR_NAME_NOT_RESOLVED;
        }
        if (iOException instanceof ConnectException) {
            return convertConnectException(iOException, -104);
        }
        if (iOException instanceof PortUnreachableException) {
            return NetError.ERR_ADDRESS_INVALID;
        }
        if (iOException instanceof NoRouteToHostException) {
            return NetError.ERR_ADDRESS_UNREACHABLE;
        }
        if (iOException instanceof BindException) {
            return NetError.ERR_ADDRESS_IN_USE;
        }
        if (iOException instanceof SocketException) {
            return convertConnectException(iOException, -15);
        }
        if (iOException instanceof MalformedURLException) {
            return NetError.ERR_INVALID_URL;
        }
        if (iOException instanceof SocketTimeoutException) {
            return NetError.ERR_CONNECTION_TIMED_OUT;
        }
        if (iOException instanceof ProtocolException) {
            return -901;
        }
        if (!(iOException instanceof HttpRetryException)) {
            return iOException instanceof UnknownServiceException ? -902 : -1;
        }
        String message = iOException.getMessage();
        return (TextUtils.isEmpty(message) || !message.contains("Too many follow-up requests")) ? NetError.ERR_TTNET_UNSUPPORTED_REDIRECT : NetError.ERR_TOO_MANY_REDIRECTS;
    }

    private static int convertConnectException(IOException iOException, int i2) {
        if (iOException == null) {
            return i2;
        }
        try {
            int convertConnectionExceptionInternal = convertConnectionExceptionInternal(iOException.getMessage(), i2);
            if (convertConnectionExceptionInternal != i2) {
                return convertConnectionExceptionInternal;
            }
            try {
                Throwable cause = iOException.getCause();
                if (cause == null) {
                    return convertConnectionExceptionInternal;
                }
                String message = cause.getMessage();
                return TextUtils.isEmpty(message) ? convertConnectionExceptionInternal : convertConnectionExceptionInternal(message, i2);
            } catch (Throwable unused) {
                i2 = convertConnectionExceptionInternal;
                return i2;
            }
        } catch (Throwable unused2) {
        }
    }

    private static int convertConnectionExceptionInternal(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return i2;
        }
        if (str.contains("ECONNRESET") || str.contains("Connection reset") || str.contains("reset by peer")) {
            return -101;
        }
        if (str.contains("ECONNREFUSED")) {
            return -102;
        }
        if (str.contains("CONNECTION_ABORTED") || str.contains("connection abort")) {
            return -103;
        }
        return (str.contains("ENETDOWN") || str.contains("Network is unreachable")) ? NetError.ERR_INTERNET_DISCONNECTED : (str.contains("EHOSTUNREACH") || str.contains("ENETUNREACH")) ? NetError.ERR_ADDRESS_UNREACHABLE : str.contains("EADDRNOTAVAIL") ? NetError.ERR_ADDRESS_INVALID : str.contains("EADDRINUSE") ? NetError.ERR_ADDRESS_IN_USE : i2;
    }
}
