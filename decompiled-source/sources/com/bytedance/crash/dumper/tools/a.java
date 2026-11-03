package com.bytedance.crash.dumper.tools;
/* compiled from: BaseStringDumper.java */
/* loaded from: classes2.dex */
abstract class a {

    /* renamed from: a  reason: collision with root package name */
    d f6604a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(char c2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(long j2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(byte[] bArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void a(char[] cArr);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void b();

    /* JADX INFO: Access modifiers changed from: package-private */
    public d a(String str, int i2) {
        g a2 = g.a(str, i2);
        return a2 != null ? a2 : e.b(str);
    }
}
