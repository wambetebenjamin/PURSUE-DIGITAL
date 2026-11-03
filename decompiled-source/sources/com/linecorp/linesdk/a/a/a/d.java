package com.linecorp.linesdk.a.a.a;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
/* loaded from: classes9.dex */
public final class d implements c<String> {

    /* renamed from: a  reason: collision with root package name */
    private final String f17250a;

    public d() {
        this("UTF-8");
    }

    public d(String str) {
        this.f17250a = str;
    }

    @Override // com.linecorp.linesdk.a.a.a.c
    /* renamed from: b */
    public final String a(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStream, this.f17250a));
            while (true) {
                try {
                    String readLine = bufferedReader2.readLine();
                    if (readLine != null) {
                        sb.append(readLine);
                    } else {
                        bufferedReader2.close();
                        return sb.toString();
                    }
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
