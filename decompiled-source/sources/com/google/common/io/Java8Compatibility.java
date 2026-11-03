package com.google.common.io;

import java.nio.Buffer;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
final class Java8Compatibility {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear(Buffer buffer) {
        buffer.clear();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void flip(Buffer buffer) {
        buffer.flip();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void limit(Buffer buffer, int i2) {
        buffer.limit(i2);
    }

    static void mark(Buffer buffer) {
        buffer.mark();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void position(Buffer buffer, int i2) {
        buffer.position(i2);
    }

    static void reset(Buffer buffer) {
        buffer.reset();
    }

    private Java8Compatibility() {
    }
}
