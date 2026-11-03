package com.bytedance.sync.v2.presistence.dao;

import com.bytedance.apm.constant.i;
import com.bytedance.sync.v2.protocal.h;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
/* compiled from: SyncDao.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0016\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH'J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH'J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH'J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0005H'J\u0016\u0010\u0012\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\u0014H'J,\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\t2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00100\u00172\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H'J\u0018\u0010\u001a\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u0010H'J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00050\t2\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0007H'J.\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u0007H'¨\u0006!"}, d2 = {"Lcom/bytedance/sync/v2/presistence/dao/SyncDao;", "", "changeSnapshotToNotified", "", "it", "Lcom/bytedance/sync/v2/presistence/table/Snapshot;", "delete", "", "obj", "", "Lcom/bytedance/sync/v2/presistence/table/SyncLog;", "deleteSnapshot", "syncId", "", "deleteSyncId", "insertOrReplaceSnapshot", "", com.bytedance.sync.interfaze.f.TYPE_SNAPSHOT, "insertSyncLogs", com.bytedance.tracing.internal.e.KEY_LOGS, "Ljava/util/ArrayList;", "queryAllLogs", "syncIds", "", i.PERF_DISK_FILE_SIZE, "offset", "querySnapshot", "business", "querySnapshots", "limit", "querySyncLogs", "consumeType", "Lcom/bytedance/sync/v2/protocal/ConsumeType;", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface c {
    void changeSnapshotToNotified(com.bytedance.sync.v2.presistence.table.b bVar);

    int delete(List<? extends com.bytedance.sync.v2.presistence.table.d> list);

    void deleteSnapshot(String str);

    void deleteSyncId(String str);

    long insertOrReplaceSnapshot(com.bytedance.sync.v2.presistence.table.b bVar);

    void insertSyncLogs(ArrayList<com.bytedance.sync.v2.presistence.table.d> arrayList);

    List<com.bytedance.sync.v2.presistence.table.d> queryAllLogs(Set<Long> set, int i2, int i3);

    com.bytedance.sync.v2.presistence.table.b querySnapshot(String str, long j2);

    List<com.bytedance.sync.v2.presistence.table.b> querySnapshots(int i2, int i3);

    List<com.bytedance.sync.v2.presistence.table.d> querySyncLogs(long j2, int i2, h hVar, int i3);
}
