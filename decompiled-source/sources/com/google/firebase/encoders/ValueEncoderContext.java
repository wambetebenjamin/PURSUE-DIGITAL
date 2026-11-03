package com.google.firebase.encoders;

import java.io.IOException;
/* loaded from: classes9.dex */
public interface ValueEncoderContext {
    ValueEncoderContext add(double d2) throws IOException;

    ValueEncoderContext add(float f2) throws IOException;

    ValueEncoderContext add(int i2) throws IOException;

    ValueEncoderContext add(long j2) throws IOException;

    ValueEncoderContext add(String str) throws IOException;

    ValueEncoderContext add(boolean z) throws IOException;

    ValueEncoderContext add(byte[] bArr) throws IOException;
}
