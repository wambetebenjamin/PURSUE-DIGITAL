package com.bytedance.sync.persistence.intermediate;

import com.bytedance.sync.protocal.g;
import com.bytedance.sync.protocal.i;
import java.util.List;
/* compiled from: SyncLogDao.java */
/* loaded from: classes3.dex */
public abstract class c {
    protected abstract void a(String str, String str2, long j2, g gVar, long j3);

    protected abstract void a(String str, String str2, long j2, g gVar, Long l);

    public abstract void deleteDownloadAckLog(long j2);

    public abstract void deleteSyncId(long j2);

    public abstract int deleteSyncLog(b[] bVarArr);

    public abstract List<b> getAllSyncLog(int i2);

    public abstract long insertCursor(a aVar);

    public abstract long insertOrReplaceSnapshot(com.bytedance.sync.persistence.snapshot.a aVar);

    public abstract void insertSyncLogs(b... bVarArr);

    public abstract List<a> queryCursorByDid(String str);

    public abstract a queryCursorLog(String str);

    public abstract a queryCursorWithDidAndUid(g gVar, String str, String str2);

    public abstract long queryReportCursor(String str);

    public abstract List<b> querySyncLogByDidAndBusinessAsc(String str, String str2, long j2, i iVar);

    public abstract List<b> querySyncLogWithSyncId(long j2, int i2, int i3);

    public abstract long updateClientCursor(long j2, long j3);

    public abstract void updateReportCursor(long j2, long j3);

    public void insertSyncLogAndAck(a aVar, b... bVarArr) {
        insertSyncLogs(bVarArr);
        insertOrUpdateServerCursor(aVar);
    }

    public void insertOrUpdateReportCursorInfo(a aVar) {
        if (insertCursor(aVar) < 0) {
            a(aVar.did, aVar.uid, aVar.syncId, aVar.bucket, Long.valueOf(aVar.reportCursor));
        }
    }

    public void insertOrUpdateServerCursor(a aVar) {
        if (insertCursor(aVar) < 0) {
            a(aVar.did, aVar.uid, aVar.syncId, aVar.bucket, aVar.serverCursor);
        }
    }

    public boolean updateSnapshotAndDeleteSyncLog(com.bytedance.sync.persistence.snapshot.a aVar, b[] bVarArr) {
        boolean z = insertOrReplaceSnapshot(aVar) > 0 && deleteSyncLog(bVarArr) > 0;
        updateClientCursor(aVar.syncId, aVar.cursor);
        return z;
    }
}
