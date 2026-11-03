package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Dynamic32Structure.java */
/* loaded from: classes2.dex */
public class a extends c.a {
    public a(f fVar, c.b bVar, long j2, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = j2 + (i2 * 8);
        this.tag = fVar.c(allocate, j3);
        this.val = fVar.c(allocate, j3 + 4);
    }
}
