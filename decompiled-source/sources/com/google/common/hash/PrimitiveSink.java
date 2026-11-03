package com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
@ElementTypesAreNonnullByDefault
/* loaded from: classes9.dex */
public interface PrimitiveSink {
    PrimitiveSink putBoolean(boolean z);

    PrimitiveSink putByte(byte b2);

    PrimitiveSink putBytes(ByteBuffer byteBuffer);

    PrimitiveSink putBytes(byte[] bArr);

    PrimitiveSink putBytes(byte[] bArr, int i2, int i3);

    PrimitiveSink putChar(char c2);

    PrimitiveSink putDouble(double d2);

    PrimitiveSink putFloat(float f2);

    PrimitiveSink putInt(int i2);

    PrimitiveSink putLong(long j2);

    PrimitiveSink putShort(short s);

    PrimitiveSink putString(CharSequence charSequence, Charset charset);

    PrimitiveSink putUnencodedChars(CharSequence charSequence);
}
