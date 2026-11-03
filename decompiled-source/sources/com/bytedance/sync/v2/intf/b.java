package com.bytedance.sync.v2.intf;

import com.bytedance.sync.user.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
/* compiled from: IDBServiceV2.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010#\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH&J\u0016\u0010\n\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J\b\u0010\u000e\u001a\u00020\u0003H&J\u0018\u0010\u000f\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H&J\u001e\u0010\u0012\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u00142\u0006\u0010\u0015\u001a\u00020\u0016H&J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0019H&J\u000e\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bH&J\u001e\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u001b2\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u001eH&J\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001bH&J\u0016\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00160\f2\u0006\u0010\"\u001a\u00020#H&J\u0012\u0010$\u001a\u0004\u0018\u00010\u00162\u0006\u0010\b\u001a\u00020\u0010H&J\u001c\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\t0\fH&J\u001a\u0010'\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u0010H&J\u001e\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00050\f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001eH&J,\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00100,2\u0006\u0010-\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001eH&J&\u0010.\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010(\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010*\u001a\u00020\u001eH&J&\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u001eH&J.\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\t2\u0006\u00103\u001a\u00020\t2\u0006\u0010-\u001a\u00020\u001eH&J\u001e\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u00052\f\u00106\u001a\b\u0012\u0004\u0012\u00020\r0\fH&J2\u00107\u001a\u00020\u00032\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\t0\f2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020 0\fH&J\u001e\u0010;\u001a\u00020\u00072\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00190\f2\u0006\u0010=\u001a\u00020\u0016H&¨\u0006>"}, d2 = {"Lcom/bytedance/sync/v2/intf/IDBServiceV2;", "Lcom/ss/android/ug/bus/IUgBusService;", "changeSnapshotToNotified", "", "it", "Lcom/bytedance/sync/v2/presistence/table/Snapshot;", "deleteLocalData", "", "syncId", "", "deleteSyncLog", "obj", "", "Lcom/bytedance/sync/v2/presistence/table/SyncLog;", "deleteUpStreamMsgIfBusinessNotExist", "deleteUploadData", "", "cursor", "insertSyncLogAndCursor", com.bytedance.tracing.internal.e.KEY_LOGS, "Ljava/util/ArrayList;", "newCursor", "Lcom/bytedance/sync/v2/presistence/table/SyncCursor;", "insertUpStreamItem", "item", "Lcom/bytedance/sync/v2/presistence/table/UploadItem;", "queryAllLocalSyncCursorInfo", "", "queryDistributeMsgs", "limit", "", "queryLocalBusinesses", "Lcom/bytedance/sync/v2/presistence/table/Business;", "queryLocalSyncCursorInfoWithDeviceInfo", "deviceInfo", "Lcom/bytedance/sync/user/AccountEventSynchronizer$DeviceInfo;", "queryLocalSyncCursorInfoWithSyncId", "queryLocalSyncCursorInfoWithSyncIds", "syncIds", "querySnapshot", "business", "querySnapshots", "offset", "querySyncLogs", "", com.bytedance.apm.constant.i.PERF_DISK_FILE_SIZE, "querySyncLogsByBusiness", "queryUploadMsgByDeviceInfo", "bucket", "Lcom/bytedance/sync/v2/protocal/Bucket;", "did", "uid", "updateSnapshotAndDeleteSyncLog", com.bytedance.sync.interfaze.f.TYPE_SNAPSHOT, "syncLogs", "updateSyncCursorAndBusiness", "syncCursors", "pendingDelete", "businesses", "updateUploadCursor", "undistributedUploads", "syncCursor", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface b extends com.ss.android.ug.bus.a {
    void changeSnapshotToNotified(com.bytedance.sync.v2.presistence.table.b bVar);

    boolean deleteLocalData(String str);

    boolean deleteSyncLog(List<? extends com.bytedance.sync.v2.presistence.table.d> list);

    void deleteUpStreamMsgIfBusinessNotExist();

    void deleteUploadData(long j2, long j3);

    void insertSyncLogAndCursor(ArrayList<com.bytedance.sync.v2.presistence.table.d> arrayList, com.bytedance.sync.v2.presistence.table.c cVar);

    long insertUpStreamItem(com.bytedance.sync.v2.presistence.table.e eVar);

    List<com.bytedance.sync.v2.presistence.table.c> queryAllLocalSyncCursorInfo();

    List<com.bytedance.sync.v2.presistence.table.e> queryDistributeMsgs(String str, int i2);

    List<com.bytedance.sync.v2.presistence.table.a> queryLocalBusinesses();

    List<com.bytedance.sync.v2.presistence.table.c> queryLocalSyncCursorInfoWithDeviceInfo(a.C0209a c0209a);

    com.bytedance.sync.v2.presistence.table.c queryLocalSyncCursorInfoWithSyncId(long j2);

    List<com.bytedance.sync.v2.presistence.table.c> queryLocalSyncCursorInfoWithSyncIds(List<String> list);

    com.bytedance.sync.v2.presistence.table.b querySnapshot(String str, long j2);

    List<com.bytedance.sync.v2.presistence.table.b> querySnapshots(int i2, int i3);

    List<com.bytedance.sync.v2.presistence.table.d> querySyncLogs(Set<Long> set, int i2, int i3);

    List<com.bytedance.sync.v2.presistence.table.d> querySyncLogsByBusiness(long j2, int i2, int i3);

    List<com.bytedance.sync.v2.presistence.table.e> queryUploadMsgByDeviceInfo(com.bytedance.sync.v2.protocal.g gVar, String str, int i2);

    List<com.bytedance.sync.v2.presistence.table.e> queryUploadMsgByDeviceInfo(com.bytedance.sync.v2.protocal.g gVar, String str, String str2, int i2);

    boolean updateSnapshotAndDeleteSyncLog(com.bytedance.sync.v2.presistence.table.b bVar, List<? extends com.bytedance.sync.v2.presistence.table.d> list);

    void updateSyncCursorAndBusiness(List<com.bytedance.sync.v2.presistence.table.c> list, List<String> list2, List<? extends com.bytedance.sync.v2.presistence.table.a> list3);

    boolean updateUploadCursor(List<? extends com.bytedance.sync.v2.presistence.table.e> list, com.bytedance.sync.v2.presistence.table.c cVar);
}
