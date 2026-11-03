package com.bytedance.common.wschannel.server;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.p;
import com.bytedance.common.wschannel.server.f;
/* compiled from: WsChannelEnableObserver.java */
/* loaded from: classes2.dex */
class l implements f {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f5980a;

    /* renamed from: b  reason: collision with root package name */
    private final Context f5981b;

    /* renamed from: c  reason: collision with root package name */
    private f.a f5982c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f5983d;

    /* renamed from: e  reason: collision with root package name */
    private ContentObserver f5984e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Context context, Handler handler) {
        this.f5980a = handler;
        this.f5981b = context;
    }

    @Override // com.bytedance.common.wschannel.server.f
    public void a() {
        c();
        this.f5984e = new ContentObserver(this.f5980a) { // from class: com.bytedance.common.wschannel.server.l.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                if (Logger.debug()) {
                    Logger.d("WsChannelService", "KEY_FRONTIER_ENABLED");
                }
                l.this.e();
            }
        };
        d();
    }

    private void c() {
        try {
            boolean c2 = p.a(this.f5981b).c();
            if (Logger.debug()) {
                Logger.d("WsChannelService", "pushSdkEnabled = " + c2);
            }
            if (c2 != this.f5983d) {
                this.f5983d = c2;
            }
        } catch (Throwable unused) {
        }
    }

    private void d() {
        try {
            this.f5981b.getContentResolver().registerContentObserver(WsChannelMultiProcessSharedProvider.getContentUri(this.f5981b, WsConstants.KEY_FRONTIER_ENABLED, "boolean"), true, this.f5984e);
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        f.a aVar;
        try {
            boolean z = this.f5983d;
            c();
            boolean z2 = this.f5983d;
            if (z == z2 || (aVar = this.f5982c) == null) {
                return;
            }
            aVar.a(z2);
        } catch (Exception unused) {
        }
    }

    @Override // com.bytedance.common.wschannel.server.f
    public boolean b() {
        return this.f5983d;
    }

    @Override // com.bytedance.common.wschannel.server.f
    public void a(f.a aVar) {
        this.f5982c = aVar;
    }
}
