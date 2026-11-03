package com.bytedance.sync.v2.intf;

import java.util.List;
import kotlin.Metadata;
/* compiled from: ISyncMsgSender.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J&\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\u0003H&J\u0012\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\u0003H&J\u0016\u0010\u000e\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H&J\u0016\u0010\u0012\u001a\u00020\u00052\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0010H&J\b\u0010\u0015\u001a\u00020\u0005H&¨\u0006\u0016"}, d2 = {"Lcom/bytedance/sync/v2/intf/ISyncMsgSender;", "Lcom/ss/android/ug/bus/IUgBusService;", "isPendingPayloadToSend", "", "sendPayload", "", "syncId", "", "dataList", "", "Lcom/bytedance/sync/v2/presistence/table/UploadItem;", "sendWithHttps", "sendPollMsg", "forceHttp", "sendSnapshotFinMsg", "snapshots", "", "Lcom/bytedance/sync/v2/presistence/table/Snapshot;", "sendSyncLogFinMsg", "syncLog", "Lcom/bytedance/sync/v2/presistence/table/SyncLog;", "sendSyncMsg", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface j extends com.ss.android.ug.bus.a {
    boolean isPendingPayloadToSend();

    void sendPayload(String str, List<com.bytedance.sync.v2.presistence.table.e> list, boolean z);

    void sendPollMsg(boolean z);

    void sendSnapshotFinMsg(List<? extends com.bytedance.sync.v2.presistence.table.b> list);

    void sendSyncLogFinMsg(List<? extends com.bytedance.sync.v2.presistence.table.d> list);

    void sendSyncMsg();

    /* compiled from: ISyncMsgSender.kt */
    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 15})
    /* loaded from: classes3.dex */
    public static final class a {
        public static /* synthetic */ void sendPollMsg$default(j jVar, boolean z, int i2, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendPollMsg");
            }
            if ((i2 & 1) != 0) {
                z = false;
            }
            jVar.sendPollMsg(z);
        }
    }
}
