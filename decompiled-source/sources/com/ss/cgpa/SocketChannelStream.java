package com.ss.cgpa;

import java.nio.ByteBuffer;
/* loaded from: classes9.dex */
interface SocketChannelStream {
    void close();

    ByteBuffer getReadByteBuffer();

    ByteBuffer getWriteByteBuffer();

    boolean procByteBuffer();
}
