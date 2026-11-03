package com.google.common.base;

import java.nio.Buffer;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
final class Java8Compatibility {
    static void clear(Buffer buffer) {
        buffer.clear();
    }

    static void flip(Buffer buffer) {
        buffer.flip();
    }

    static void limit(Buffer buffer, int i2) {
        buffer.limit(i2);
    }

    static void position(Buffer buffer, int i2) {
        buffer.position(i2);
    }

    private Java8Compatibility() {
    }
}
