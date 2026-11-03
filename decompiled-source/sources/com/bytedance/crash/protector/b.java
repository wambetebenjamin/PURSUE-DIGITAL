package com.bytedance.crash.protector;

import com.bytedance.crash.Global;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.y;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.json.JSONObject;
/* compiled from: CrashProtector.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6986a = "NPTH_PROT";

    /* renamed from: b  reason: collision with root package name */
    private static final String f6987b = "crash_protector.config";

    public static void a(JSONObject jSONObject) {
        c cVar = new c(jSONObject);
        a(cVar.b(), cVar.c());
    }

    private static boolean a(byte[] bArr, int i2) {
        File file;
        try {
            file = new File(Global.f(), f6987b);
        } catch (Throwable unused) {
        }
        if (a(file, bArr, i2)) {
            y.i(f6986a, "config not changed");
            return true;
        }
        if (file.exists()) {
            file.delete();
        }
        if (bArr != null && i2 > 0) {
            File file2 = new File(Global.f(), "crash_protector.config.tmp");
            if (!file2.exists() && !file2.createNewFile()) {
                return false;
            }
            FileOutputStream fileOutputStream = null;
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2, false);
                try {
                    fileOutputStream2.write(bArr, 0, i2);
                    fileOutputStream2.flush();
                    k.a(fileOutputStream2);
                    if (file2.renameTo(file)) {
                        y.i(f6986a, "update config file done");
                        return true;
                    }
                    y.e(f6986a, "failed to update config file");
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    k.a(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x004c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(java.io.File r8, byte[] r9, int r10) {
        /*
            r0 = 0
            if (r9 == 0) goto L4d
            if (r10 <= 0) goto L4d
            boolean r1 = r8.exists()
            if (r1 == 0) goto L4d
            long r1 = r8.length()
            long r3 = (long) r10
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto L15
            goto L4d
        L15:
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Exception -> L44
            r2.<init>(r8)     // Catch: java.lang.Exception -> L44
            r8 = 4096(0x1000, float:5.74E-42)
            byte[] r8 = new byte[r8]     // Catch: java.lang.Exception -> L41
            r1 = r0
        L20:
            int r3 = r2.read(r8)     // Catch: java.lang.Exception -> L3f
            if (r3 <= 0) goto L47
            int r4 = r1 + r3
            if (r4 <= r10) goto L2b
            goto L47
        L2b:
            r5 = r0
        L2c:
            if (r5 >= r3) goto L3d
            r6 = r8[r5]     // Catch: java.lang.Exception -> L3f
            int r7 = r1 + r5
            r7 = r9[r7]     // Catch: java.lang.Exception -> L3f
            if (r6 == r7) goto L3a
            r2.close()     // Catch: java.lang.Exception -> L3f
            return r0
        L3a:
            int r5 = r5 + 1
            goto L2c
        L3d:
            r1 = r4
            goto L20
        L3f:
            r8 = r1
            goto L42
        L41:
            r8 = r0
        L42:
            r1 = r2
            goto L45
        L44:
            r8 = r0
        L45:
            r2 = r1
            r1 = r8
        L47:
            com.bytedance.crash.util.k.a(r2)
            if (r1 != r10) goto L4d
            r0 = 1
        L4d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.crash.protector.b.a(java.io.File, byte[], int):boolean");
    }

    public static void a(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        String str2 = null;
        try {
            str2 = a(new File(str));
        } catch (Throwable unused) {
        }
        if (str2 == null) {
            return;
        }
        try {
            JSONObject optJSONObject = new JSONObject(str2).optJSONObject("protector");
            if (optJSONObject != null) {
                c cVar = new c(optJSONObject);
                a(cVar.b(), cVar.c());
            }
        } catch (Throwable unused2) {
        }
    }

    private static String a(File file) {
        if (file == null) {
            return null;
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[4096];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    byteArrayOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return byteArrayOutputStream.toString();
                }
            }
        } catch (IOException unused) {
            return null;
        }
    }
}
