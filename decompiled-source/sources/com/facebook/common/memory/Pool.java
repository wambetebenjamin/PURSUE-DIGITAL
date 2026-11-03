package com.facebook.common.memory;

import com.facebook.common.references.ResourceReleaser;
/* loaded from: classes8.dex */
public interface Pool<V> extends MemoryTrimmable, ResourceReleaser<V> {
    V get(int i2);

    @Override // com.facebook.common.references.ResourceReleaser
    void release(V v);
}
