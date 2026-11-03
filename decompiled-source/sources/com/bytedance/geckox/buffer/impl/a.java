package com.bytedance.geckox.buffer.impl;

import com.bytedance.geckox.d;
import com.bytedance.geckox.f;
import java.io.File;
import java.io.IOException;
/* compiled from: BufferPolicy.java */
/* loaded from: classes2.dex */
public class a {
    public static com.bytedance.geckox.buffer.a a(f fVar, File file, long j2) {
        com.bytedance.geckox.buffer.a b2 = b(fVar, file, j2);
        com.bytedance.geckox.logger.b.a(d.f8984a, "buffer type:" + b2.getClass());
        return b2;
    }

    private static com.bytedance.geckox.buffer.a b(f fVar, File file, long j2) {
        if (j2 <= 0) {
            try {
                return new b(file);
            } catch (Exception e2) {
                throw new IOException("create FileBuffer failed! file:" + file.getAbsolutePath() + " caused by:" + e2.getMessage(), e2);
            }
        }
        if (fVar.q()) {
            try {
                return new MMapBuffer(j2, file);
            } catch (Exception e3) {
                com.bytedance.geckox.logger.b.w(d.f8984a, "mmap failed:", e3);
            }
        }
        try {
            return new c(j2, file);
        } catch (Exception e4) {
            throw new RuntimeException("create random access file failed! file:" + file.getAbsolutePath() + " caused by:" + e4.getMessage(), e4);
        }
    }
}
