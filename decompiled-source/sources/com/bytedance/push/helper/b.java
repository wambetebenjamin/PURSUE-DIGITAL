package com.bytedance.push.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/* compiled from: CommandHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* compiled from: CommandHelper.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(String str);
    }

    public static String a(String str, a aVar, a aVar2) {
        try {
            System.out.println("exec cmd: " + str);
            Process exec = Runtime.getRuntime().exec(str);
            C0168b c0168b = new C0168b(exec.getErrorStream(), "ERROR", aVar2);
            C0168b c0168b2 = new C0168b(exec.getInputStream(), "OUTPUT", aVar);
            c0168b.start();
            c0168b2.start();
            return exec.waitFor() == 0 ? "success" : "failed";
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    /* compiled from: CommandHelper.java */
    /* renamed from: com.bytedance.push.helper.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0168b extends Thread {

        /* renamed from: a  reason: collision with root package name */
        InputStream f11352a;

        /* renamed from: b  reason: collision with root package name */
        String f11353b;

        /* renamed from: c  reason: collision with root package name */
        a f11354c;

        C0168b(InputStream inputStream, String str, a aVar) {
            this.f11352a = inputStream;
            this.f11353b = str;
            this.f11354c = aVar;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.f11352a));
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                System.out.println(stringBuffer.toString());
                a aVar = this.f11354c;
                if (aVar != null) {
                    aVar.a(stringBuffer.toString());
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
}
