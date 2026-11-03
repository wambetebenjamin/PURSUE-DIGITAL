package com.bytedance.common.wschannel.heartbeat;

import android.os.Handler;
import com.bytedance.common.utility.Logger;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: CompensateHeartBeatManager.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final long f5647a = 5000;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.common.wschannel.heartbeat.model.a f5648b;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.common.wschannel.channel.impl.ok.ws.b f5650d;

    /* renamed from: e  reason: collision with root package name */
    private a f5651e;

    /* renamed from: f  reason: collision with root package name */
    private Handler f5652f;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f5649c = new AtomicBoolean(false);

    /* renamed from: g  reason: collision with root package name */
    private Runnable f5653g = new Runnable() { // from class: com.bytedance.common.wschannel.heartbeat.b.1
        @Override // java.lang.Runnable
        public void run() {
            if (b.this.f5649c.getAndSet(false)) {
                Logger.d(com.bytedance.common.wschannel.channel.impl.ok.b.f5415a, "Compensate for ping timeout, prepare to reconnect");
                if (b.this.f5651e != null) {
                    b.this.f5651e.a();
                }
            }
        }
    };

    /* compiled from: CompensateHeartBeatManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();
    }

    public b(a aVar, Handler handler) {
        this.f5651e = aVar;
        this.f5652f = handler;
    }

    public void a(com.bytedance.common.wschannel.channel.impl.ok.ws.b bVar) {
        this.f5650d = bVar;
    }

    public void a(com.bytedance.common.wschannel.heartbeat.model.a aVar) {
        if (!this.f5649c.get()) {
            boolean z = false;
            if (b(aVar)) {
                Logger.d(com.bytedance.common.wschannel.channel.impl.ok.b.f5415a, "Switch the foreground in the background, send compensation ping");
                z = true;
            }
            if (z) {
                try {
                    com.bytedance.common.wschannel.channel.impl.ok.ws.b bVar = this.f5650d;
                    if (bVar != null) {
                        bVar.h();
                        c();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        this.f5648b = aVar;
    }

    public boolean a() {
        return this.f5649c.get();
    }

    public void b() {
        Logger.d(com.bytedance.common.wschannel.channel.impl.ok.b.f5415a, "After compensating for ping, received pong");
        this.f5649c.set(false);
        this.f5652f.removeCallbacks(this.f5653g);
    }

    private boolean b(com.bytedance.common.wschannel.heartbeat.model.a aVar) {
        return this.f5648b == com.bytedance.common.wschannel.heartbeat.model.a.STATE_BACKGROUND && aVar == com.bytedance.common.wschannel.heartbeat.model.a.STATE_FOREGROUND;
    }

    private void c() {
        this.f5649c.set(true);
        this.f5652f.removeCallbacks(this.f5653g);
        this.f5652f.postDelayed(this.f5653g, 5000L);
    }
}
