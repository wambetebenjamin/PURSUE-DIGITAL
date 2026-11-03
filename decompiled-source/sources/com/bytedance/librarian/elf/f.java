package com.bytedance.librarian.elf;

import com.bytedance.librarian.elf.c;
import com.facebook.soloader.MinElf;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.UShort;
/* compiled from: ElfParser.java */
/* loaded from: classes2.dex */
public class f implements c, Closeable {

    /* renamed from: a  reason: collision with root package name */
    private final int f9486a = MinElf.ELF_MAGIC;

    /* renamed from: b  reason: collision with root package name */
    private final FileChannel f9487b;

    public f(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.f9487b = new FileInputStream(file).getChannel();
    }

    public c.b parseHeader() {
        this.f9487b.position(0L);
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(ByteOrder.LITTLE_ENDIAN);
        if (c(allocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short e2 = e(allocate, 4L);
        boolean z = e(allocate, 5L) == 2;
        if (e2 == 1) {
            return new d(z, this);
        }
        if (e2 == 2) {
            return new e(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    public List<String> parseNeededDependencies() {
        long j2;
        this.f9487b.position(0L);
        ArrayList arrayList = new ArrayList();
        c.b parseHeader = parseHeader();
        ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.order(parseHeader.bigEndian ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j3 = parseHeader.phnum;
        int i2 = 0;
        if (j3 == 65535) {
            j3 = parseHeader.getSectionHeader(0).info;
        }
        long j4 = 0;
        while (true) {
            if (j4 >= j3) {
                j2 = 0;
                break;
            }
            c.AbstractC0136c programHeader = parseHeader.getProgramHeader(j4);
            if (programHeader.type == 2) {
                j2 = programHeader.offset;
                break;
            }
            j4++;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList<Long> arrayList2 = new ArrayList();
        long j5 = 0;
        while (true) {
            c.a dynamicStructure = parseHeader.getDynamicStructure(j2, i2);
            if (dynamicStructure.tag == 1) {
                arrayList2.add(Long.valueOf(dynamicStructure.val));
            } else if (dynamicStructure.tag == 5) {
                j5 = dynamicStructure.val;
            }
            i2++;
            if (dynamicStructure.tag == 0) {
                break;
            }
        }
        if (j5 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long a2 = a(parseHeader, j3, j5);
        for (Long l : arrayList2) {
            arrayList.add(a(allocate, l.longValue() + a2));
        }
        return arrayList;
    }

    private long a(c.b bVar, long j2, long j3) {
        for (long j4 = 0; j4 < j2; j4++) {
            c.AbstractC0136c programHeader = bVar.getProgramHeader(j4);
            if (programHeader.type == 1 && programHeader.vaddr <= j3 && j3 <= programHeader.vaddr + programHeader.memsz) {
                return (j3 - programHeader.vaddr) + programHeader.offset;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.f9487b.close();
    }

    protected String a(ByteBuffer byteBuffer, long j2) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j3 = 1 + j2;
            short e2 = e(byteBuffer, j2);
            if (e2 != 0) {
                sb.append((char) e2);
                j2 = j3;
            } else {
                return sb.toString();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long b(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 8);
        return byteBuffer.getLong();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public long c(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 4);
        return byteBuffer.getInt() & 4294967295L;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int d(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 2);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    protected short e(ByteBuffer byteBuffer, long j2) {
        a(byteBuffer, j2, 1);
        return (short) (byteBuffer.get() & 255);
    }

    protected void a(ByteBuffer byteBuffer, long j2, int i2) {
        byteBuffer.position(0);
        byteBuffer.limit(i2);
        long j3 = 0;
        while (j3 < i2) {
            int read = this.f9487b.read(byteBuffer, j2 + j3);
            if (read == -1) {
                throw new EOFException();
            }
            j3 += read;
        }
        byteBuffer.position(0);
    }
}
