package com.appsflyer.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
/* loaded from: classes.dex */
public final class AFe1sSDK {
    private final int AFKeystoreWrapper;

    public AFe1sSDK(int i2) {
        this.AFKeystoreWrapper = i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x0268  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final com.appsflyer.internal.AFe1pSDK<java.lang.String> valueOf(com.appsflyer.internal.AFe1mSDK r21) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsflyer.internal.AFe1sSDK.valueOf(com.appsflyer.internal.AFe1mSDK):com.appsflyer.internal.AFe1pSDK");
    }

    private static String AFInAppEventParameterName(HttpURLConnection httpURLConnection, boolean z) throws IOException {
        BufferedReader bufferedReader;
        InputStream errorStream;
        InputStreamReader inputStreamReader = null;
        try {
            if (z) {
                errorStream = httpURLConnection.getInputStream();
            } else {
                errorStream = httpURLConnection.getErrorStream();
            }
            if (errorStream == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            InputStreamReader inputStreamReader2 = new InputStreamReader(errorStream, Charset.defaultCharset());
            try {
                BufferedReader bufferedReader2 = new BufferedReader(inputStreamReader2);
                boolean z2 = true;
                while (true) {
                    try {
                        String readLine = bufferedReader2.readLine();
                        if (readLine != null) {
                            if (!z2) {
                                sb.append('\n');
                            }
                            sb.append(readLine);
                            z2 = false;
                        } else {
                            String obj = sb.toString();
                            inputStreamReader2.close();
                            bufferedReader2.close();
                            return obj;
                        }
                    } catch (Throwable th) {
                        inputStreamReader = inputStreamReader2;
                        bufferedReader = bufferedReader2;
                        th = th;
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedReader = null;
                inputStreamReader = inputStreamReader2;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }
}
