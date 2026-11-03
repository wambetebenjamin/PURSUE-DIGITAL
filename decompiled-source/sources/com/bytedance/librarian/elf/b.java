package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Dynamic64Structure.java */
/* loaded from: classes2.dex */
public class b extends c.a {
    public b(f fVar, c.b bVar, long j2, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = j2 + (i2 * 16);
        this.tag = fVar.b(allocate, j3);
        this.val = fVar.b(allocate, j3 + 8);
    }
}
