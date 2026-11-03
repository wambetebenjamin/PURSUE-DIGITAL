package com.facebook.binaryresource;

import java.io.InputStream;
/* loaded from: classes8.dex */
public interface BinaryResource {
    InputStream openStream();

    byte[] read();

    long size();
}
