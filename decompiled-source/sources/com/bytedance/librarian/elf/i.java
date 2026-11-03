package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Section32Header.java */
/* loaded from: classes2.dex */
public class i extends c.d {
    public i(f fVar, c.b bVar, int i2) {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(bVar.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.info = fVar.c(allocate, bVar.shoff + (i2 * bVar.shentsize) + 28);
    }
}
