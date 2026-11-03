package com.bytedance.ttgame.library.module_manager.internal;

import com.bytedance.ttgame.library.module_manager.GSDKWrapper;
import java.util.Map;
import kotlin.Metadata;
/* compiled from: WrappersLoader.kt */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\b`\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003H&J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\u0004H&¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/library/module_manager/internal/WrappersLoader;", "", "getAllWrappers", "", "", "Lcom/bytedance/ttgame/library/module_manager/GSDKWrapper;", "getWrapper", "mavenNotionId", "module_manager_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface WrappersLoader {
    Map<String, GSDKWrapper> getAllWrappers();

    GSDKWrapper getWrapper(String str);
}
