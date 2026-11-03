package com.bytedance.sync.persistence;

import com.bytedance.sync.protocal.g;
import java.util.Collection;
import java.util.List;
import java.util.Set;
/* compiled from: IDBService.java */
/* loaded from: classes3.dex */
public interface c extends com.ss.android.ug.bus.a {
    void bindUploadSyncId(com.bytedance.sync.persistence.intermediate.a aVar, com.bytedance.sync.persistence.business.a[] aVarArr);

    void changeSnapshotToNotified(com.bytedance.sync.persistence.snapshot.a aVar);

    boolean clearDownloadSyncId(long j2);

    void deleteMsgWhichBusinessNotExist();

    boolean deleteSyncLog(com.bytedance.sync.persistence.intermediate.b bVar);

    void deleteUploadData(long j2, long j3);

    void insertSyncLogAndAck(com.bytedance.sync.persistence.intermediate.b[] bVarArr, com.bytedance.sync.persistence.intermediate.a aVar);

    void insertUploadPayload(com.bytedance.sync.persistence.upload.c cVar);

    List<com.bytedance.sync.persistence.business.a> queryBusinessInfo(Set<Long> set);

    Collection<com.bytedance.sync.model.c> queryCurrentSyncIdAndCursor(String str, String str2);

    com.bytedance.sync.persistence.intermediate.a queryCursorWithDid(g gVar, String str, String str2);

    List<com.bytedance.sync.persistence.upload.c> queryDistributeMsgs(long j2, int i2);

    com.bytedance.sync.persistence.intermediate.a queryLocalCursor(String str);

    List<com.bytedance.sync.persistence.intermediate.b> queryOneByOneSyncLog(String str, String str2, long j2);

    com.bytedance.sync.persistence.snapshot.a querySnapshot(long j2, long j3);

    List<com.bytedance.sync.persistence.snapshot.a> querySnapshot();

    List<com.bytedance.sync.persistence.snapshot.a> querySnapshot(long j2);

    List<com.bytedance.sync.persistence.intermediate.b> querySyncLogWithSyncId(long j2, int i2, int i3);

    List<com.bytedance.sync.persistence.upload.c> queryUploadMsgByDeviceInfo(g gVar, String str, int i2);

    List<com.bytedance.sync.persistence.upload.c> queryUploadMsgByDeviceInfo(g gVar, String str, String str2, int i2);

    void updateClientCursor(long j2, long j3);

    boolean updateSnapshotAndDeleteSyncLog(com.bytedance.sync.persistence.snapshot.a aVar, com.bytedance.sync.persistence.intermediate.b bVar);

    boolean updateSnapshotAndDeleteSyncLog(com.bytedance.sync.persistence.snapshot.a aVar, com.bytedance.sync.persistence.intermediate.b[] bVarArr);

    boolean updateUploadCursor(List<com.bytedance.sync.persistence.upload.c> list, com.bytedance.sync.persistence.intermediate.a aVar);
}
