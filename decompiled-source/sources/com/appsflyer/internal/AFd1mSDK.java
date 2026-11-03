package com.appsflyer.internal;

import com.appsflyer.AFLogger;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.TextStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\b&\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\n\u001a\u00020\r\u0012\u0014\u0010\u001a\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u001b\u001a\u00020\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0006\u001a\u00020\u0005*\u0004\u0018\u00010\u0005H'¢\u0006\u0004\b\u0006\u0010\u0007J\u001b\u0010\u000b\u001a\u00020\u0002*\u00020\b2\u0006\u0010\n\u001a\u00020\tH\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0012\u0010\u0003\u001a\u00020\rX\u0087\u0002¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u000b\u001a\u00020\u00108'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0011R \u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0012X\u0087\u0002¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0013\u001a\u00020\u00028\u0017X\u0097D¢\u0006\f\n\u0004\b\u000b\u0010\u0015\u001a\u0004\b\u0013\u0010\u0004R\u0011\u0010\u000e\u001a\u00020\u0016X\u0007¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00058'X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0018"}, d2 = {"Lcom/appsflyer/internal/AFd1mSDK;", "", "", "AFInAppEventParameterName", "()Z", "", "AFInAppEventType", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/net/HttpURLConnection;", "", "p0", "AFKeystoreWrapper", "(Ljava/net/HttpURLConnection;J)Z", "", "values", "[B", "Lcom/appsflyer/internal/AFe1uSDK;", "()Lcom/appsflyer/internal/AFe1uSDK;", "", "valueOf", "Ljava/util/Map;", "Z", "", "I", "()Ljava/lang/String;", "registerClient", "p1", "p2", "<init>", "([BLjava/util/Map;I)V"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AFd1mSDK {
    public int AFInAppEventType;
    private final boolean AFKeystoreWrapper;
    public Map<String, String> valueOf;
    public byte[] values;

    public abstract String AFInAppEventType();

    public abstract String AFInAppEventType(String str);

    public abstract AFe1uSDK values();

    public AFd1mSDK(byte[] bArr, Map<String, String> map, int i2) {
        Intrinsics.checkNotNullParameter(bArr, "");
        this.values = bArr;
        this.valueOf = map;
        this.AFInAppEventType = i2;
        this.AFKeystoreWrapper = true;
    }

    public boolean valueOf() {
        return this.AFKeystoreWrapper;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0093, code lost:
        if (r4 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0096, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean AFInAppEventParameterName() {
        /*
            r8 = this;
            long r0 = java.lang.System.currentTimeMillis()
            r2 = 0
            r3 = 0
            java.lang.String r4 = r8.AFInAppEventType()     // Catch: java.lang.Throwable -> L31
            java.lang.String r5 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r5)     // Catch: java.lang.Throwable -> L31
            java.net.URL r5 = new java.net.URL     // Catch: java.lang.Throwable -> L31
            r5.<init>(r4)     // Catch: java.lang.Throwable -> L31
            java.net.URLConnection r4 = r5.openConnection()     // Catch: java.lang.Throwable -> L31
            if (r4 == 0) goto L29
            java.net.HttpURLConnection r4 = (java.net.HttpURLConnection) r4     // Catch: java.lang.Throwable -> L31
            boolean r2 = r8.AFKeystoreWrapper(r4, r0)     // Catch: java.lang.Throwable -> L27
            if (r4 == 0) goto L96
        L22:
            r4.disconnect()
            goto L96
        L27:
            r3 = move-exception
            goto L35
        L29:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException     // Catch: java.lang.Throwable -> L31
            java.lang.String r5 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            r4.<init>(r5)     // Catch: java.lang.Throwable -> L31
            throw r4     // Catch: java.lang.Throwable -> L31
        L31:
            r4 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
        L35:
            long r5 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L97
            long r5 = r5 - r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = "error: "
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r0.append(r3)     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = "\n\ttook "
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r0.append(r5)     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = "ms\n\t"
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L97
            java.lang.String r1 = r3.getMessage()     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r0.append(r1)     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L97
            java.lang.String r3 = "HTTP: ["
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L97
            if (r4 == 0) goto L6f
            int r3 = r4.hashCode()     // Catch: java.lang.Throwable -> L97
            goto L70
        L6f:
            r3 = r2
        L70:
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> L97
            java.lang.String r3 = "] "
            java.lang.StringBuilder r1 = r1.append(r3)     // Catch: java.lang.Throwable -> L97
            java.lang.StringBuilder r0 = r1.append(r0)     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L97
            java.lang.String r0 = r8.AFInAppEventType(r0)     // Catch: java.lang.Throwable -> L97
            boolean r1 = r8.valueOf()     // Catch: java.lang.Throwable -> L97
            if (r1 == 0) goto L90
            com.appsflyer.AFLogger.afRDLog(r0)     // Catch: java.lang.Throwable -> L97
            goto L93
        L90:
            com.appsflyer.AFLogger.afVerboseLog(r0)     // Catch: java.lang.Throwable -> L97
        L93:
            if (r4 == 0) goto L96
            goto L22
        L96:
            return r2
        L97:
            r0 = move-exception
            if (r4 == 0) goto L9d
            r4.disconnect()
        L9d:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFd1mSDK.AFInAppEventParameterName():boolean");
    }

    private final boolean AFKeystoreWrapper(HttpURLConnection httpURLConnection, long j2) {
        InputStream errorStream;
        String str = "";
        httpURLConnection.setRequestMethod("POST");
        StringBuilder sb = new StringBuilder(new StringBuilder().append(httpURLConnection.getRequestMethod()).append(':').append(httpURLConnection.getURL()).toString());
        sb.append("\n length: ").append(new String(this.values, Charsets.UTF_8).length());
        Map<String, String> map = this.valueOf;
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                sb.append("\n ").append(entry.getKey()).append(": ").append(entry.getValue());
            }
        }
        String AFInAppEventType = AFInAppEventType(new StringBuilder("HTTP: [").append(httpURLConnection.hashCode()).append("] ").append((Object) sb).toString());
        if (valueOf()) {
            AFLogger.afRDLog(AFInAppEventType);
        } else {
            AFLogger.afVerboseLog(AFInAppEventType);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setReadTimeout(this.AFInAppEventType);
        httpURLConnection.setConnectTimeout(this.AFInAppEventType);
        httpURLConnection.addRequestProperty("Content-Type", values().AFInAppEventParameterName);
        Map<String, String> map2 = this.valueOf;
        if (map2 != null) {
            for (Map.Entry<String, String> entry2 : map2.entrySet()) {
                httpURLConnection.addRequestProperty(entry2.getKey(), entry2.getValue());
            }
        }
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.values.length));
        OutputStream outputStream = httpURLConnection.getOutputStream();
        Intrinsics.checkNotNullExpressionValue(outputStream, "");
        BufferedOutputStream bufferedOutputStream = outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, 8192);
        bufferedOutputStream.write(this.values);
        bufferedOutputStream.close();
        if (AFd1eSDK.values(httpURLConnection)) {
            errorStream = httpURLConnection.getInputStream();
        } else {
            errorStream = httpURLConnection.getErrorStream();
        }
        if (errorStream != null) {
            Intrinsics.checkNotNullExpressionValue(errorStream, "");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream, Charsets.UTF_8), 8192);
            String joinToString$default = CollectionsKt.joinToString$default(TextStreamsKt.readLines(bufferedReader), null, null, null, 0, null, null, 63, null);
            bufferedReader.close();
            if (joinToString$default != null) {
                str = joinToString$default;
            }
        }
        String AFInAppEventType2 = AFInAppEventType(new StringBuilder("HTTP: [").append(httpURLConnection.hashCode()).append("] ").append(new StringBuilder("response code:").append(httpURLConnection.getResponseCode()).append(' ').append(httpURLConnection.getResponseMessage()).append("\n\tbody:").append(str).append("\n\ttook ").append(System.currentTimeMillis() - j2).append("ms").toString()).toString());
        if (valueOf()) {
            AFLogger.afRDLog(AFInAppEventType2);
        } else {
            AFLogger.afVerboseLog(AFInAppEventType2);
        }
        return AFd1eSDK.values(httpURLConnection);
    }
}
