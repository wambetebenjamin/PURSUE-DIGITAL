package com.google.common.hash;

import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
abstract class AbstractStreamingHasher extends AbstractHasher {
    private final ByteBuffer buffer;
    private final int bufferSize;
    private final int chunkSize;

    protected abstract HashCode makeHash();

    protected abstract void process(ByteBuffer byteBuffer);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractStreamingHasher(int i2) {
        this(i2, i2);
    }

    protected AbstractStreamingHasher(int i2, int i3) {
        Preconditions.checkArgument(i3 % i2 == 0);
        this.buffer = ByteBuffer.allocate(i3 + 7).order(ByteOrder.LITTLE_ENDIAN);
        this.bufferSize = i3;
        this.chunkSize = i2;
    }

    protected void processRemaining(ByteBuffer byteBuffer) {
        Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        Java8Compatibility.limit(byteBuffer, this.chunkSize + 7);
        while (true) {
            int position = byteBuffer.position();
            int i2 = this.chunkSize;
            if (position < i2) {
                byteBuffer.putLong(0L);
            } else {
                Java8Compatibility.limit(byteBuffer, i2);
                Java8Compatibility.flip(byteBuffer);
                process(byteBuffer);
                return;
            }
        }
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(byte[] bArr, int i2, int i3) {
        return putBytesInternal(ByteBuffer.wrap(bArr, i2, i3).order(ByteOrder.LITTLE_ENDIAN));
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putBytes(ByteBuffer byteBuffer) {
        ByteOrder order = byteBuffer.order();
        try {
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return putBytesInternal(byteBuffer);
        } finally {
            byteBuffer.order(order);
        }
    }

    private Hasher putBytesInternal(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() <= this.buffer.remaining()) {
            this.buffer.put(byteBuffer);
            munchIfFull();
            return this;
        }
        int position = this.bufferSize - this.buffer.position();
        for (int i2 = 0; i2 < position; i2++) {
            this.buffer.put(byteBuffer.get());
        }
        munch();
        while (byteBuffer.remaining() >= this.chunkSize) {
            process(byteBuffer);
        }
        this.buffer.put(byteBuffer);
        return this;
    }

    @Override // com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putByte(byte b2) {
        this.buffer.put(b2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putShort(short s) {
        this.buffer.putShort(s);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putChar(char c2) {
        this.buffer.putChar(c2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putInt(int i2) {
        this.buffer.putInt(i2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.AbstractHasher, com.google.common.hash.Hasher, com.google.common.hash.PrimitiveSink
    public final Hasher putLong(long j2) {
        this.buffer.putLong(j2);
        munchIfFull();
        return this;
    }

    @Override // com.google.common.hash.Hasher
    public final HashCode hash() {
        munch();
        Java8Compatibility.flip(this.buffer);
        if (this.buffer.remaining() > 0) {
            processRemaining(this.buffer);
            ByteBuffer byteBuffer = this.buffer;
            Java8Compatibility.position(byteBuffer, byteBuffer.limit());
        }
        return makeHash();
    }

    private void munchIfFull() {
        if (this.buffer.remaining() < 8) {
            munch();
        }
    }

    private void munch() {
        Java8Compatibility.flip(this.buffer);
        while (this.buffer.remaining() >= this.chunkSize) {
            process(this.buffer);
        }
        this.buffer.compact();
    }
}
