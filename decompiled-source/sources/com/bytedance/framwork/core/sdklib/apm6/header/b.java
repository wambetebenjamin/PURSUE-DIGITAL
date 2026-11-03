package com.bytedance.framwork.core.sdklib.apm6.header;

import com.bytedance.apm6.util.d;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import org.json.JSONObject;
/* compiled from: HeaderStoreManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f8674a = ".bin";

    /* renamed from: b  reason: collision with root package name */
    private static final String f8675b = "_";

    /* renamed from: c  reason: collision with root package name */
    private static final long f8676c = 604800000;

    private static long a(long j2) {
        if (j2 < 0) {
            return 0L;
        }
        return j2 >> 16;
    }

    public static b a() {
        return a.f8678a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, JSONObject jSONObject) {
        File f2 = com.bytedance.framwork.core.sdklib.apm6.b.f();
        if (f2 == null || jSONObject == null) {
            return;
        }
        File file = new File(f2, b(str));
        FileChannel fileChannel = null;
        try {
            fileChannel = new FileOutputStream(file).getChannel();
            fileChannel.write(ByteBuffer.wrap(jSONObject.toString().getBytes()));
        } finally {
            try {
            } finally {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject a(String str) {
        byte[] readFileToByteArray;
        File f2 = com.bytedance.framwork.core.sdklib.apm6.b.f();
        if (f2 != null && (readFileToByteArray = d.readFileToByteArray(new File(f2, b(str)))) != null) {
            try {
                return new JSONObject(new String(readFileToByteArray));
            } catch (Exception unused) {
            }
        }
        return null;
    }

    private String b(String str) {
        return str + f8674a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void b() {
        File f2 = com.bytedance.framwork.core.sdklib.apm6.b.f();
        if (f2 == null) {
            return;
        }
        f2.listFiles(new FileFilter() { // from class: com.bytedance.framwork.core.sdklib.apm6.header.b.1
            @Override // java.io.FileFilter
            public boolean accept(File file) {
                if (b.this.a(file)) {
                    d.deleteFile(file);
                    return false;
                }
                return false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(File file) {
        try {
            String name = file.getName();
            int lastIndexOf = name.lastIndexOf(f8674a);
            if (lastIndexOf == -1) {
                return true;
            }
            String[] split = name.substring(0, lastIndexOf).split("_");
            if (split.length < 2) {
                return true;
            }
            return System.currentTimeMillis() - a(Long.parseLong(split[1])) >= 604800000;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* compiled from: HeaderStoreManager.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final b f8678a = new b();

        private a() {
        }
    }
}
