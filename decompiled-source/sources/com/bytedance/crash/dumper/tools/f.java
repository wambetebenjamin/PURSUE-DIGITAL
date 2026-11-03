package com.bytedance.crash.dumper.tools;

import kotlin.text.Typography;
/* compiled from: JsonDumper.java */
/* loaded from: classes2.dex */
public class f extends i {
    public f(String str) {
        super(str);
    }

    public f a() {
        b('{');
        return this;
    }

    public f b() {
        b('}');
        return this;
    }

    public f c() {
        b('[');
        return this;
    }

    public f d() {
        b(']');
        return this;
    }

    public f e() {
        b(',');
        return this;
    }

    public f a(String str) {
        b(Typography.quote).c(str).b(Typography.quote).b(':').b(' ');
        return this;
    }

    public f a(boolean z) {
        b(z);
        return this;
    }

    public f a(long j2) {
        b(j2);
        return this;
    }

    public f a(int i2) {
        b(i2);
        return this;
    }

    public f a(char c2) {
        b(c2);
        return this;
    }

    public f b(String str) {
        b(Typography.quote);
        int length = str == null ? 0 : str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt == '\f') {
                c("\\f");
            } else if (charAt == '\r') {
                c("\\r");
            } else if (charAt == '\"' || charAt == '\\') {
                b('\\');
                b(charAt);
            } else if (charAt == 8232 || charAt == 8233) {
                c(String.format("\\u%04x", Integer.valueOf(charAt)));
            } else {
                switch (charAt) {
                    case '\b':
                        c("\\b");
                        continue;
                    case '\t':
                        c("\\t");
                        continue;
                    case '\n':
                        c("\\n");
                        continue;
                    default:
                        if (charAt > 31) {
                            b(charAt);
                            break;
                        } else {
                            c(String.format("\\u%04x", Integer.valueOf(charAt)));
                            continue;
                        }
                }
            }
        }
        b(Typography.quote);
        return this;
    }
}
