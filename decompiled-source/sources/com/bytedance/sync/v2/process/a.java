package com.bytedance.sync.v2.process;

import com.bytedance.sync.interfaze.f;
import com.bytedance.sync.v2.intf.j;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DataErrorService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0016J\u0016\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/bytedance/sync/v2/process/DataErrorService;", "Lcom/bytedance/sync/v2/intf/IDataErrorService;", "()V", "clearLocalData", "", "syncId", "", "deleteFiles", "pollErrorSyncId", "", "pendingRetryPollSyncId", "", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class a implements com.bytedance.sync.v2.intf.c {
    @Override // com.bytedance.sync.v2.intf.c
    public boolean clearLocalData(String syncId, boolean z) {
        Intrinsics.checkParameterIsNotNull(syncId, "syncId");
        f fVar = (f) com.ss.android.ug.bus.b.getService(f.class);
        if (z) {
            fVar.deleteAllFiles(Long.parseLong(syncId));
        }
        return ((com.bytedance.sync.v2.intf.b) com.ss.android.ug.bus.b.getService(com.bytedance.sync.v2.intf.b.class)).deleteLocalData(syncId);
    }

    @Override // com.bytedance.sync.v2.intf.c
    public void pollErrorSyncId(Set<String> pendingRetryPollSyncId) {
        Intrinsics.checkParameterIsNotNull(pendingRetryPollSyncId, "pendingRetryPollSyncId");
        j.a.sendPollMsg$default((j) com.ss.android.ug.bus.b.getService(j.class), false, 1, null);
    }
}
