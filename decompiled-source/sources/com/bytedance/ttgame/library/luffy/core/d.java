package com.bytedance.ttgame.library.luffy.core;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
/* compiled from: IInternalService.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\u0003H&¨\u0006\t"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/IInternalService;", "Lcom/bytedance/ttgame/library/luffy/adapter/IConfig;", "addRemoteUpdateListener", "", "key", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/library/luffy/core/CallBack;", "update", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface d extends com.bytedance.ttgame.library.luffy.adapter.a {
    void addRemoteUpdateListener(String str, a aVar);

    void update();
}
