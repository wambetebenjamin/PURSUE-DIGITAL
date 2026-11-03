package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.CacheKey;
import com.facebook.cache.common.WriterCallback;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.common.disk.DiskTrimmable;
import java.util.Map;
/* loaded from: classes8.dex */
public interface FileCache extends DiskTrimmable {
    void clearAll();

    void clearByPercentage(Double d2);

    long clearOldEntries(long j2);

    long getCount();

    DiskStorage.DiskDumpInfo getDumpInfo();

    BinaryResource getResource(CacheKey cacheKey);

    Map<String, String> getResourceConfig(CacheKey cacheKey);

    long getSize();

    boolean hasKey(CacheKey cacheKey);

    boolean hasKeySync(CacheKey cacheKey);

    BinaryResource insert(CacheKey cacheKey, WriterCallback writerCallback);

    boolean isEnabled();

    boolean probe(CacheKey cacheKey);

    void remove(CacheKey cacheKey);
}
