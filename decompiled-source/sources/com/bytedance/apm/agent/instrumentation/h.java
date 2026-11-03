package com.bytedance.apm.agent.instrumentation;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.conn.ConnectTimeoutException;
/* compiled from: TransactionStateUtil.java */
/* loaded from: classes.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final com.bytedance.apm.agent.logging.a f245a = com.bytedance.apm.agent.logging.b.getAgentLog();

    public static void inspectAndInstrument(com.bytedance.apm.agent.instrumentation.transaction.b bVar, HttpURLConnection httpURLConnection) {
        bVar.setUrl(httpURLConnection.getURL().toString());
        bVar.setStartTime(System.currentTimeMillis());
        bVar.setCarrier("");
    }

    public static void inspectAndInstrumentResponse(com.bytedance.apm.agent.instrumentation.transaction.b bVar, HttpURLConnection httpURLConnection) {
        int contentLength = httpURLConnection.getContentLength();
        if (contentLength >= 0) {
            bVar.setBytesReceived(contentLength);
        }
        int i2 = 0;
        try {
            i2 = httpURLConnection.getResponseCode();
        } catch (IOException | NullPointerException unused) {
        }
        bVar.setStatusCode(i2);
    }

    public static void setErrorCodeFromException(com.bytedance.apm.agent.instrumentation.transaction.b bVar, Exception exc) {
        if (exc instanceof UnknownHostException) {
            bVar.setErrorCode(-10);
        } else if ((exc instanceof SocketTimeoutException) || (exc instanceof ConnectTimeoutException)) {
            bVar.setErrorCode(-11);
        } else if (exc instanceof ConnectException) {
            bVar.setErrorCode(-12);
        } else if (exc instanceof MalformedURLException) {
            bVar.setErrorCode(-13);
        } else if (exc instanceof SSLException) {
            bVar.setErrorCode(-14);
        } else {
            bVar.setErrorCode(-1);
        }
    }
}
