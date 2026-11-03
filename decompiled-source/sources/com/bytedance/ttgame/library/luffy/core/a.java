package com.bytedance.ttgame.library.luffy.core;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;
/* compiled from: CallBack.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/CallBack;", "Lcom/bytedance/ttgame/library/luffy/core/RemoteConfigListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "callbackOnMain", "", "(Lcom/bytedance/ttgame/library/luffy/core/RemoteConfigListener;Z)V", "onUpdate", "", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class a implements m {

    /* renamed from: a  reason: collision with root package name */
    private final m f14177a;

    /* renamed from: b  reason: collision with root package name */
    private final boolean f14178b;

    public a(m mVar, boolean z) {
        this.f14177a = mVar;
        this.f14178b = z;
    }

    /* compiled from: CallBack.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
    /* renamed from: com.bytedance.ttgame.library.luffy.core.a$a  reason: collision with other inner class name */
    /* loaded from: classes3.dex */
    static final class RunnableC0229a implements Runnable {
        RunnableC0229a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            m mVar = a.this.f14177a;
            if (mVar != null) {
                mVar.onUpdate();
            }
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.core.m
    public void onUpdate() {
        if (this.f14178b) {
            new Handler(Looper.getMainLooper()).post(new RunnableC0229a());
            return;
        }
        m mVar = this.f14177a;
        if (mVar != null) {
            mVar.onUpdate();
        }
    }
}
