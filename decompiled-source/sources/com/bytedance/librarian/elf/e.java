package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Elf64Header.java */
/* loaded from: classes2.dex */
public class e extends c.b {

    /* renamed from: a  reason: collision with root package name */
    private final f f9485a;

    public e(boolean z, f fVar) {
        this.bigEndian = z;
        this.f9485a = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = fVar.d(allocate, 16L);
        this.phoff = fVar.b(allocate, 32L);
        this.shoff = fVar.b(allocate, 40L);
        this.phentsize = fVar.d(allocate, 54L);
        this.phnum = fVar.d(allocate, 56L);
        this.shentsize = fVar.d(allocate, 58L);
        this.shnum = fVar.d(allocate, 60L);
        this.shstrndx = fVar.d(allocate, 62L);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.d getSectionHeader(int i2) {
        return new j(this.f9485a, this, i2);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.AbstractC0136c getProgramHeader(long j2) {
        return new h(this.f9485a, this, j2);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.a getDynamicStructure(long j2, int i2) {
        return new b(this.f9485a, this, j2, i2);
    }
}
