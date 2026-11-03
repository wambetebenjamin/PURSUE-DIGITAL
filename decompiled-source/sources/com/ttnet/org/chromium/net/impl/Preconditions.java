package com.ttnet.org.chromium.net.impl;

import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
public final class Preconditions {
    private Preconditions() {
    }

    public static void checkDirect(ByteBuffer byteBuffer) {
        if (!byteBuffer.isDirect()) {
            throw new IllegalArgumentException("byteBuffer must be a direct ByteBuffer.");
        }
    }

    public static void checkHasRemaining(ByteBuffer byteBuffer) {
        if (!byteBuffer.hasRemaining()) {
            throw new IllegalArgumentException("ByteBuffer is already full.");
        }
    }
}
