package com.bytedance.ttgame.library.boot_manager;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: GraphicHasCircleException.kt */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lcom/bytedance/ttgame/library/boot_manager/GraphicHasCircleException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "msg", "", "(Ljava/lang/String;)V", "boot_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GraphicHasCircleException extends RuntimeException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GraphicHasCircleException(String msg) {
        super(msg);
        Intrinsics.checkNotNullParameter(msg, "msg");
    }
}
