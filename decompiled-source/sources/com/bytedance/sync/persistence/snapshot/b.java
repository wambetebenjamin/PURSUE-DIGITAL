package com.bytedance.sync.persistence.snapshot;

import java.util.List;
/* compiled from: SnapshotDao.java */
/* loaded from: classes3.dex */
public interface b {
    void deleteSyncId(long j2);

    List<a> queryNotNotifiedSnapshots();

    a querySnapshot(long j2, long j3);

    List<a> querySnapshot(long j2);
}
