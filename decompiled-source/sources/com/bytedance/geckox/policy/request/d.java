package com.bytedance.geckox.policy.request;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.bytedance.apm.constant.k;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
/* compiled from: RetryRequestPolicy.java */
/* loaded from: classes2.dex */
public class d implements b {

    /* renamed from: a  reason: collision with root package name */
    private static final int f9282a = 2;

    /* renamed from: f  reason: collision with root package name */
    private static ConcurrentHashMap<String, Long> f9283f = new ConcurrentHashMap<>();

    /* renamed from: g  reason: collision with root package name */
    private static AtomicLong f9284g = new AtomicLong(System.currentTimeMillis());

    /* renamed from: b  reason: collision with root package name */
    private Handler f9285b = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.geckox.policy.request.d.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 2) {
                if (d.f9283f.contains(Long.valueOf(((Long) message.obj).longValue())) && d.this.f9289h != null) {
                    d.this.f9289h.a();
                }
            }
        }
    };

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f9286c;

    /* renamed from: d  reason: collision with root package name */
    private boolean f9287d;

    /* renamed from: e  reason: collision with root package name */
    private String f9288e;

    /* renamed from: h  reason: collision with root package name */
    private a f9289h;

    /* compiled from: RetryRequestPolicy.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a();
    }

    @Override // com.bytedance.geckox.policy.request.b
    public boolean b() {
        return false;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void c() {
    }

    public d(boolean z, boolean z2, String str, a aVar) {
        this.f9286c = new AtomicBoolean(z);
        this.f9287d = z2;
        this.f9288e = str;
        this.f9289h = aVar;
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void a() {
        if (!this.f9286c.get() && f9283f.containsKey(this.f9288e)) {
            f9283f.remove(this.f9288e, Long.valueOf(f9283f.get(this.f9288e).longValue()));
        } else if (this.f9286c.get()) {
            f9283f.remove(this.f9288e);
        }
    }

    @Override // com.bytedance.geckox.policy.request.b
    public void d() {
        if (!this.f9286c.get() && this.f9287d && System.currentTimeMillis() - f9284g.get() <= k.LAST_STOP_INTERVAL) {
            long currentTimeMillis = System.currentTimeMillis();
            Message obtainMessage = this.f9285b.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = Long.valueOf(currentTimeMillis);
            this.f9285b.sendMessageDelayed(obtainMessage, 60000L);
            f9283f.put(this.f9288e, Long.valueOf(currentTimeMillis));
            com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, this.f9288e + ">>gecko update request retry hit", null);
        } else if (this.f9286c.get()) {
            f9284g.set(System.currentTimeMillis());
        }
    }

    public void a(boolean z) {
        this.f9286c.set(z);
    }
}
