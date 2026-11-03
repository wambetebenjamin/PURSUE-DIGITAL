package com.bytedance.sync.v2.process.ctrl;

import com.bytedance.sync.v2.process.d;
import com.bytedance.sync.v2.protocal.f;
import com.bytedance.sync.v2.protocal.i;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: HeartbeatMsgHandler.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¨\u0006\u000e"}, d2 = {"Lcom/bytedance/sync/v2/process/ctrl/HeartbeatMsgHandler;", "Lcom/bytedance/sync/v2/process/IMsgHandler;", "Lcom/bytedance/sync/v2/protocal/Ctrl;", "()V", "canHandle", "", "ctrl", "handle", "", "header", "Lcom/bytedance/sync/v2/protocal/BsyncHeader;", "topics", "", "Lcom/bytedance/sync/v2/protocal/BsyncTopic;", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public final class a implements d<i> {
    @Override // com.bytedance.sync.v2.process.d
    public void handle(com.bytedance.sync.v2.protocal.b header, List<f> topics) {
        Intrinsics.checkParameterIsNotNull(header, "header");
        Intrinsics.checkParameterIsNotNull(topics, "topics");
    }

    @Override // com.bytedance.sync.v2.process.d
    public boolean canHandle(i ctrl) {
        Intrinsics.checkParameterIsNotNull(ctrl, "ctrl");
        return ctrl == i.HeartbeatPong;
    }
}
