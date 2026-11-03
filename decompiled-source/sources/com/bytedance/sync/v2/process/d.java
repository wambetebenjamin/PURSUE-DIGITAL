package com.bytedance.sync.v2.process;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.sync.v2.protocal.f;
import java.util.List;
import kotlin.Metadata;
/* compiled from: ProtocolProcessor.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH&¨\u0006\u000e"}, d2 = {"Lcom/bytedance/sync/v2/process/IMsgHandler;", ExifInterface.GPS_DIRECTION_TRUE, "", "canHandle", "", "obj", "(Ljava/lang/Object;)Z", "handle", "", "header", "Lcom/bytedance/sync/v2/protocal/BsyncHeader;", "topics", "", "Lcom/bytedance/sync/v2/protocal/BsyncTopic;", "sync-sdk_release"}, k = 1, mv = {1, 1, 15})
/* loaded from: classes3.dex */
public interface d<T> {
    boolean canHandle(T t);

    void handle(com.bytedance.sync.v2.protocal.b bVar, List<f> list);
}
