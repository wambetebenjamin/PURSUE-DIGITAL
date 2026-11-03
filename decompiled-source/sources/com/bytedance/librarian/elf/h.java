package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Program64Header.java */
/* loaded from: classes2.dex */
public class h extends c.AbstractC0136c {
    public h(f fVar, c.b bVar, long j2) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(bVar.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = bVar.phoff + (j2 * bVar.phentsize);
        this.type = fVar.c(allocate, j3);
        this.offset = fVar.b(allocate, 8 + j3);
        this.vaddr = fVar.b(allocate, 16 + j3);
        this.memsz = fVar.b(allocate, j3 + 40);
    }
}
