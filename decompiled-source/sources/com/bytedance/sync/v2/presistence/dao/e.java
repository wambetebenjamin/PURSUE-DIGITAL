package com.bytedance.sync.v2.presistence.dao;

import androidx.room.Update;
import com.bytedance.apm.constant.i;
import com.bytedance.sync.v2.protocal.g;
import java.util.List;
import kotlin.Metadata;
/* compiled from: UploadDao.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H'J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H'J\u0012\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH'J\u001e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\f2\u0006\u0010\u0005\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH'J&\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000fH'J.\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u000fH'J\u0016\u0010\u0017\u001a\u00020\u000f2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\n0\u0011H'¨\u0006\u0019"}, d2 = {"Lcom/bytedance/sync/v2/presistence/dao/UploadDao;", "", "deleteUpStreamMsgIfBusinessNotExist", "", "deleteUploadData", "syncId", "", "cursor", "insert", "item", "Lcom/bytedance/sync/v2/presistence/table/UploadItem;", "queryDistributeMsgs", "", "", "limit", "", "queryUploadMsgByDeviceInfo", "", "bucket", "Lcom/bytedance/sync/v2/protocal/Bucket;", "did", i.PERF_DISK_FILE_SIZE, "uid", "update", "undistributedUploads", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface e {
    void deleteUpStreamMsgIfBusinessNotExist();

    void deleteUploadData(long j2, long j3);

    long insert(com.bytedance.sync.v2.presistence.table.e eVar);

    List<com.bytedance.sync.v2.presistence.table.e> queryDistributeMsgs(String str, int i2);

    List<com.bytedance.sync.v2.presistence.table.e> queryUploadMsgByDeviceInfo(g gVar, String str, int i2);

    List<com.bytedance.sync.v2.presistence.table.e> queryUploadMsgByDeviceInfo(g gVar, String str, String str2, int i2);

    @Update(onConflict = 1)
    int update(List<? extends com.bytedance.sync.v2.presistence.table.e> list);
}
