package com.bytedance.librarian.elf;
/* compiled from: Elf.java */
/* loaded from: classes2.dex */
public interface c {

    /* compiled from: Elf.java */
    /* loaded from: classes2.dex */
    public static abstract class a {
        public static final int DT_NEEDED = 1;
        public static final int DT_NULL = 0;
        public static final int DT_STRTAB = 5;
        public long tag;
        public long val;
    }

    /* compiled from: Elf.java */
    /* loaded from: classes2.dex */
    public static abstract class b {
        public static final int ELFCLASS32 = 1;
        public static final int ELFCLASS64 = 2;
        public static final int ELFDATA2MSB = 2;
        public boolean bigEndian;
        public int phentsize;
        public int phnum;
        public long phoff;
        public int shentsize;
        public int shnum;
        public long shoff;
        public int shstrndx;
        public int type;

        public abstract a getDynamicStructure(long j2, int i2);

        public abstract AbstractC0136c getProgramHeader(long j2);

        public abstract d getSectionHeader(int i2);
    }

    /* compiled from: Elf.java */
    /* renamed from: com.bytedance.librarian.elf.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static abstract class AbstractC0136c {
        public static final int PT_DYNAMIC = 2;
        public static final int PT_LOAD = 1;
        public long memsz;
        public long offset;
        public long type;
        public long vaddr;
    }

    /* compiled from: Elf.java */
    /* loaded from: classes2.dex */
    public static abstract class d {
        public long info;
    }
}
