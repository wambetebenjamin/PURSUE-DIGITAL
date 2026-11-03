package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Program32Header.java */
/* loaded from: classes2.dex */
public class g extends c.AbstractC0136c {
    public g(f fVar, c.b bVar, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = bVar.phoff + (j2 * bVar.phentsize);
        this.type = fVar.c(allocate, j3);
        this.offset = fVar.c(allocate, 4 + j3);
        this.vaddr = fVar.c(allocate, 8 + j3);
        this.memsz = fVar.c(allocate, j3 + 20);
    }
}
