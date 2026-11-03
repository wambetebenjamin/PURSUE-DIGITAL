package com.bytedance.crash.dumper.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
/* compiled from: JavaStringDumperImpl.java */
/* loaded from: classes2.dex */
class e implements d {

    /* renamed from: a  reason: collision with root package name */
    private final FileOutputStream f6614a;

    public static e b(String str) {
        return new e(str);
    }

    private e(String str) {
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(str));
        } catch (IOException unused) {
            fileOutputStream = null;
        }
        this.f6614a = fileOutputStream;
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(char[] cArr, int i2) {
        a(new StringBuilder().append(cArr).toString());
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(byte[] bArr, int i2) {
        FileOutputStream fileOutputStream = this.f6614a;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(bArr);
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(String str) {
        FileOutputStream fileOutputStream = this.f6614a;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.write(str.getBytes());
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(int i2) {
        a(Integer.toString(i2));
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(long j2) {
        a(Long.toString(j2));
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a() {
        FileOutputStream fileOutputStream = this.f6614a;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void b() {
        FileOutputStream fileOutputStream = this.f6614a;
        if (fileOutputStream != null) {
            try {
                fileOutputStream.flush();
            } catch (IOException unused) {
            }
        }
    }
}
