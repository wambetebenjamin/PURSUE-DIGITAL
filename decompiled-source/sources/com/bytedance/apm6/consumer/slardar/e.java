package com.bytedance.apm6.consumer.slardar;

import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
/* compiled from: SeqNumGenerator.java */
/* loaded from: classes.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private MappedByteBuffer f1780a;

    /* renamed from: b  reason: collision with root package name */
    private long f1781b = 0;

    public static e getInstance() {
        return a.f1782a;
    }

    public long next() {
        if (this.f1780a == null) {
            try {
                File file = new File(c.getWorkingDirectory(), com.bytedance.apm6.foundation.context.a.getCurrentProcessName().replace(c.a.DOT, "_").replace(CertificateUtil.DELIMITER, "-") + "_seq_num.txt");
                boolean exists = file.exists();
                if (!exists) {
                    file.createNewFile();
                }
                MappedByteBuffer map = new RandomAccessFile(file, "rw").getChannel().map(FileChannel.MapMode.READ_WRITE, 0L, 8L);
                this.f1780a = map;
                if (!exists) {
                    map.putLong(0, 0L);
                    return 0L;
                }
            } catch (Throwable th) {
                com.bytedance.apm6.util.log.b.e(b.TAG, "prepare seq_number fail.", th);
            }
        }
        MappedByteBuffer mappedByteBuffer = this.f1780a;
        if (mappedByteBuffer != null) {
            long j2 = mappedByteBuffer.getLong(0) + 1;
            this.f1781b = j2;
            this.f1780a.putLong(0, j2);
            return this.f1781b;
        }
        long j3 = this.f1781b;
        this.f1781b = 1 + j3;
        return j3;
    }

    /* compiled from: SeqNumGenerator.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final e f1782a = new e();

        private a() {
        }
    }
}
