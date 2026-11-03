package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* compiled from: Elf32Header.java */
/* loaded from: classes2.dex */
public class d extends c.b {

    /* renamed from: a  reason: collision with root package name */
    private final f f9484a;

    public d(boolean z, f fVar) {
        this.bigEndian = z;
        this.f9484a = fVar;
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.order(z ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        this.type = fVar.d(allocate, 16L);
        this.phoff = fVar.c(allocate, 28L);
        this.shoff = fVar.c(allocate, 32L);
        this.phentsize = fVar.d(allocate, 42L);
        this.phnum = fVar.d(allocate, 44L);
        this.shentsize = fVar.d(allocate, 46L);
        this.shnum = fVar.d(allocate, 48L);
        this.shstrndx = fVar.d(allocate, 50L);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.d getSectionHeader(int i2) {
        return new i(this.f9484a, this, i2);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.AbstractC0136c getProgramHeader(long j2) {
        return new g(this.f9484a, this, j2);
    }

    @Override // com.bytedance.librarian.elf.c.b
    public c.a getDynamicStructure(long j2, int i2) {
        return new a(this.f9484a, this, j2, i2);
    }
}
