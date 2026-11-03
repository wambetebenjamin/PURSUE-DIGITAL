package com.bumptech.glide.load;

import com.bumptech.glide.load.engine.Resource;
import java.io.IOException;
/* loaded from: classes.dex */
public interface ResourceDecoder<T, Z> {
    Resource<Z> decode(T t, int i2, int i3, Options options) throws IOException;

    boolean handles(T t, Options options) throws IOException;
}
