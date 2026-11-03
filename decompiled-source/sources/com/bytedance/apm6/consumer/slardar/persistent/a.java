package com.bytedance.apm6.consumer.slardar.persistent;

import com.bytedance.apm6.util.d;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LogFile.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private long f1815a;

    /* renamed from: b  reason: collision with root package name */
    private int f1816b;

    /* renamed from: c  reason: collision with root package name */
    private int f1817c;

    /* renamed from: d  reason: collision with root package name */
    private File f1818d;

    /* renamed from: e  reason: collision with root package name */
    private List<b> f1819e;

    public a(long j2, int i2, int i3, List<b> list) {
        this.f1815a = j2;
        this.f1816b = i2;
        this.f1817c = i3;
        this.f1819e = list;
    }

    public long getStartID() {
        return this.f1815a;
    }

    public int getTotalCount() {
        return this.f1816b;
    }

    public int getTotalBytes() {
        return this.f1817c;
    }

    public List<b> getLogList() {
        return this.f1819e;
    }

    public File getSource() {
        return this.f1818d;
    }

    public static a fromMemory(ByteBuffer byteBuffer) {
        try {
            if (byteBuffer.getShort() != 2082) {
                return null;
            }
            long j2 = byteBuffer.getLong();
            int i2 = byteBuffer.getInt();
            int i3 = byteBuffer.getInt();
            ArrayList arrayList = new ArrayList();
            int i4 = 0;
            for (int i5 = 0; i5 < i2; i5++) {
                int i6 = byteBuffer.getInt();
                i4 += i6;
                if (i4 > i3) {
                    return null;
                }
                byte[] bArr = new byte[i6];
                byteBuffer.get(bArr);
                arrayList.add(new b(bArr));
            }
            return new a(j2, i2, i3, arrayList);
        } catch (Throwable unused) {
            return null;
        }
    }

    public static a fromFile(File file) {
        try {
            byte[] readFileToByteArray = d.readFileToByteArray(file);
            if (readFileToByteArray != null) {
                a fromMemory = fromMemory(ByteBuffer.wrap(readFileToByteArray));
                if (fromMemory != null) {
                    fromMemory.f1818d = file;
                } else {
                    com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.consumer.slardar.b.TAG, "fromMemory bytes is null");
                }
                return fromMemory;
            }
            com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.consumer.slardar.b.TAG, "fromFile bytes is null");
            return null;
        } catch (Throwable th) {
            com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.consumer.slardar.b.TAG, "fromFile", th);
            return null;
        }
    }

    public String toString() {
        return "LogFile{startID=" + this.f1815a + ", totalCount=" + this.f1816b + ", totalBytes=" + this.f1817c + ", source=" + this.f1818d + ", logList=" + this.f1819e + '}';
    }
}
