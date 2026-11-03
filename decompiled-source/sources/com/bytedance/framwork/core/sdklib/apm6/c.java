package com.bytedance.framwork.core.sdklib.apm6;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LogFile.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private int f8597a;

    /* renamed from: b  reason: collision with root package name */
    private int f8598b;

    /* renamed from: c  reason: collision with root package name */
    private File f8599c;

    /* renamed from: d  reason: collision with root package name */
    private List<d> f8600d;

    public c(int i2, int i3, List<d> list) {
        this.f8597a = i2;
        this.f8598b = i3;
        this.f8600d = list;
    }

    public int a() {
        return this.f8597a;
    }

    public int b() {
        return this.f8598b;
    }

    public List<d> c() {
        return this.f8600d;
    }

    public File d() {
        return this.f8599c;
    }

    public static c a(ByteBuffer byteBuffer) {
        try {
            if (byteBuffer.getInt() != 1095781686) {
                return null;
            }
            byteBuffer.getInt();
            int i2 = byteBuffer.getInt();
            int i3 = byteBuffer.getInt();
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = byteBuffer.getInt();
                i4 += i6;
                if (i4 > i3) {
                    com.bytedance.framwork.core.sdklib.apm6.safety.d.e(a.f8583a, "construct LogFile failed.");
                    return null;
                }
                long j2 = byteBuffer.getLong();
                long j3 = byteBuffer.getLong();
                byte[] bArr = new byte[i6];
                byteBuffer.get(bArr);
                arrayList.add(new d(bArr, j2, j3));
            }
            return new c(i2, i3, arrayList);
        } catch (Throwable th) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "construct LogFile failed.", th);
            return null;
        }
    }

    public static c a(File file) {
        try {
            byte[] readFileToByteArray = com.bytedance.apm6.util.d.readFileToByteArray(file);
            if (readFileToByteArray != null) {
                c a2 = a(ByteBuffer.wrap(readFileToByteArray));
                if (a2 != null) {
                    a2.f8599c = file;
                } else {
                    com.bytedance.framwork.core.sdklib.apm6.safety.d.e(a.f8583a, "fromMemory bytes is null");
                }
                return a2;
            }
            com.bytedance.framwork.core.sdklib.apm6.safety.d.e(a.f8583a, "fromFile bytes is null");
            return null;
        } catch (Throwable th) {
            com.bytedance.framwork.core.sdklib.apm6.safety.d.b(a.f8583a, "fromFile", th);
            return null;
        }
    }

    public String toString() {
        return "LogFile{totalCount=" + this.f8597a + ", totalBytes=" + this.f8598b + ", source=" + this.f8599c + ", logList=" + this.f8600d + '}';
    }
}
