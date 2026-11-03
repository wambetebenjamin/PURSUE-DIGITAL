package com.facebook.cache.disk;

import com.facebook.binaryresource.BinaryResource;
import com.facebook.cache.common.WriterCallback;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public interface DiskStorage {

    /* loaded from: classes8.dex */
    public static class DiskDumpInfo {
        public List<DiskDumpInfoEntry> entries = new ArrayList();
        public Map<String, Integer> typeCounts = new HashMap();
    }

    /* loaded from: classes8.dex */
    public interface Entry {
        String getId();

        BinaryResource getResource();

        long getSize();

        long getTimestamp();
    }

    /* loaded from: classes8.dex */
    public interface Inserter {
        boolean cleanUp();

        BinaryResource commit(Object obj);

        void writeData(WriterCallback writerCallback, Object obj);
    }

    void clearAll();

    boolean contains(String str, Object obj);

    Collection<Entry> getConfigEntries();

    List<Entry> getDiskCacheEntries();

    DiskDumpInfo getDumpInfo();

    Collection<Entry> getEntries();

    BinaryResource getResource(String str, Object obj);

    Map<String, String> getResourceConfig(String str, Object obj);

    String getStorageName();

    Inserter insert(String str, Object obj);

    boolean isEnabled();

    boolean isExternal();

    void purgeUnexpectedResources();

    long remove(Entry entry);

    long remove(String str);

    long removeConfig(String str);

    boolean touch(String str, Object obj);

    /* loaded from: classes8.dex */
    public static class DiskDumpInfoEntry {
        public final String firstBits;
        public final String path;
        public final float size;
        public final String type;

        /* JADX INFO: Access modifiers changed from: protected */
        public DiskDumpInfoEntry(String str, String str2, float f2, String str3) {
            this.path = str;
            this.type = str2;
            this.size = f2;
            this.firstBits = str3;
        }
    }
}
