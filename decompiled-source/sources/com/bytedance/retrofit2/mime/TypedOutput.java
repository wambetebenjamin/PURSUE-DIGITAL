package com.bytedance.retrofit2.mime;

import java.io.OutputStream;
/* loaded from: classes2.dex */
public interface TypedOutput {
    String fileName();

    long length();

    String md5Stub();

    String mimeType();

    void writeTo(OutputStream outputStream);
}
