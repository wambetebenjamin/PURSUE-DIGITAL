package com.twitter.sdk.android.core.internal.scribe;

import java.io.IOException;
/* loaded from: classes10.dex */
public interface EventTransform<T> {
    byte[] toBytes(T t) throws IOException;
}
