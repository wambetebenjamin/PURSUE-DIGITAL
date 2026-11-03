package com.bytedance.ttgame.library.luffy.impl;

import android.util.Log;
import com.bytedance.frameworks.baselib.network.e;
import com.bytedance.ttgame.library.luffy.adapter.b;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: DefaultLogger.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/impl/DefaultLogger;", "Lcom/bytedance/ttgame/library/luffy/adapter/ILogger;", "()V", "d", "", "content", "", e.f7575a, BaseSwitches.V, DownloadFileUtils.MODE_WRITE, "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class a implements b {
    @Override // com.bytedance.ttgame.library.luffy.adapter.b
    public void d(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Log.d(com.bytedance.ttgame.library.luffy.core.b.TAG, content);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.b
    public void e(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, content);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.b
    public void w(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Log.w(com.bytedance.ttgame.library.luffy.core.b.TAG, content);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.b
    public void v(String content) {
        Intrinsics.checkNotNullParameter(content, "content");
        Log.v(com.bytedance.ttgame.library.luffy.core.b.TAG, content);
    }
}
