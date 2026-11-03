package com.bytedance.sync.interfaze;

import java.io.File;
/* compiled from: IFileDataCacheService.java */
/* loaded from: classes3.dex */
public interface f extends com.ss.android.ug.bus.a {
    public static final String TYPE_SNAPSHOT = "snapshot";
    public static final String TYPE_SYNC_LOG = "synclog";

    boolean deleteAllFiles(long j2);

    void deleteFile(String str);

    File getCachedDir();

    String putData(String str, long j2, long j3, byte[] bArr);

    String putData(String str, long j2, long j3, byte[] bArr, String str2);

    String readData(long j2, String str);
}
