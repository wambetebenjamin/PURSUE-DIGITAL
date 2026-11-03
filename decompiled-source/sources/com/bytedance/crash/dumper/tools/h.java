package com.bytedance.crash.dumper.tools;
/* compiled from: StackBuilder.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final char[] f6616a = {'(', 'N', 'a', 't', 'i', 'v', 'e', ' ', 'M', 'e', 't', 'h', 'o', 'd', ')'};

    /* renamed from: b  reason: collision with root package name */
    private static final char[] f6617b = {'(', 'U', 'n', 'k', 'n', 'o', 'w', 'n', ' ', 'S', 'o', 'u', 'r', 'c', 'e', ')'};

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f6618c = {'(', 'U', 'n', 'k', 'n', 'o', 'w', 'n', ' ', 'S', 'o', 'u', 'r', 'c', 'e', ':'};

    /* renamed from: d  reason: collision with root package name */
    private final i f6619d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(i iVar) {
        this.f6619d = iVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h a(int i2) {
        this.f6619d.b(i2);
        return this;
    }

    h a(String str) {
        if (str.length() != 0) {
            this.f6619d.c(str);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h a(char c2) {
        this.f6619d.b(c2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h a(char[] cArr) {
        if (cArr.length != 0) {
            this.f6619d.a(cArr);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h a(Throwable th) {
        this.f6619d.a(th);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h a(StackTraceElement stackTraceElement) {
        String className = stackTraceElement.getClassName();
        String methodName = stackTraceElement.getMethodName();
        String fileName = stackTraceElement.getFileName();
        int lineNumber = stackTraceElement.getLineNumber();
        boolean isNativeMethod = stackTraceElement.isNativeMethod();
        a(className).a('.').a(methodName);
        if (isNativeMethod) {
            a(f6616a);
        } else if (fileName != null) {
            if (stackTraceElement.getLineNumber() >= 0) {
                a('(').a(fileName).a(':').a(lineNumber).a(')');
            } else {
                a('(').a(fileName).a(')');
            }
        } else if (lineNumber >= 0) {
            a(f6618c).a(lineNumber).a(')');
        } else {
            a(f6617b);
        }
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public h b(int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            this.f6619d.b('\t');
        }
        return this;
    }
}
