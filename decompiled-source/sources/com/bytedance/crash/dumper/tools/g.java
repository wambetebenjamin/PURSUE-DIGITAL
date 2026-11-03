package com.bytedance.crash.dumper.tools;

import com.bytedance.crash.jni.NativeBridge;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: NativeStringDumperImpl.java */
/* loaded from: classes2.dex */
public class g implements d {

    /* renamed from: a  reason: collision with root package name */
    final long f6615a;

    g(long j2) {
        this.f6615a = j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static g a(String str, int i2) {
        long a2 = NativeBridge.a(str, i2);
        if (a2 != 0) {
            return new g(a2);
        }
        return null;
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void b() {
        NativeBridge.f(this.f6615a);
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a() {
        NativeBridge.e(this.f6615a);
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
    public void a(String str) {
        NativeBridge.a(this.f6615a, str, str.length());
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(char[] cArr, int i2) {
        NativeBridge.a(this.f6615a, cArr, i2);
    }

    @Override // com.bytedance.crash.dumper.tools.d
    public void a(byte[] bArr, int i2) {
        NativeBridge.a(this.f6615a, bArr, i2);
    }
}
