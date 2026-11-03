package com.bytedance.retrofit2.mime;

import java.io.InputStream;
/* loaded from: classes2.dex */
public interface TypedInput {
    InputStream in();

    long length();

    String mimeType();
}
