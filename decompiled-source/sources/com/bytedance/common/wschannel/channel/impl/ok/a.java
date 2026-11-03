package com.bytedance.common.wschannel.channel.impl.ok;

import android.os.Handler;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.meituan.robust.Constants;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.Response;
import okio.ByteString;
/* compiled from: HeartBeatProcessor.java */
/* loaded from: classes2.dex */
class a {

    /* renamed from: a  reason: collision with root package name */
    private final Handler f5403a;

    /* renamed from: b  reason: collision with root package name */
    private final InterfaceC0079a f5404b;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.common.wschannel.channel.impl.ok.ws.b f5408f;

    /* renamed from: c  reason: collision with root package name */
    private long f5405c = com.bytedance.common.wschannel.heartbeat.normal.a.f5664a;

    /* renamed from: d  reason: collision with root package name */
    private long f5406d = com.bytedance.common.wschannel.heartbeat.normal.a.f5664a;

    /* renamed from: e  reason: collision with root package name */
    private long f5407e = com.bytedance.common.wschannel.heartbeat.normal.a.f5664a;

    /* renamed from: g  reason: collision with root package name */
    private AtomicBoolean f5409g = new AtomicBoolean(false);

    /* renamed from: h  reason: collision with root package name */
    private Runnable f5410h = new Runnable() { // from class: com.bytedance.common.wschannel.channel.impl.ok.a.1
        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f5409g.getAndSet(false)) {
                a.this.a();
                if (a.this.f5404b != null) {
                    a.this.f5404b.a();
                }
            }
        }
    };

    /* renamed from: i  reason: collision with root package name */
    private Runnable f5411i = new Runnable() { // from class: com.bytedance.common.wschannel.channel.impl.ok.a.2
        @Override // java.lang.Runnable
        public void run() {
            a.this.d();
            a.this.c();
        }
    };

    /* renamed from: j  reason: collision with root package name */
    private long f5412j = 5000;

    /* compiled from: HeartBeatProcessor.java */
    /* renamed from: com.bytedance.common.wschannel.channel.impl.ok.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    interface InterfaceC0079a {
        void a();
    }

    void a(boolean z) {
    }

    a(Handler handler, InterfaceC0079a interfaceC0079a) {
        this.f5403a = handler;
        this.f5404b = interfaceC0079a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        long j2 = this.f5405c;
        Logger.d(b.f5415a, "interval :" + j2 + " ms,the next heartbeat is: " + com.bytedance.common.wschannel.utils.e.a(System.currentTimeMillis() + j2));
        this.f5403a.removeCallbacks(this.f5411i);
        this.f5403a.postDelayed(this.f5411i, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        try {
            if (this.f5408f != null) {
                Logger.d(b.f5415a, "send ping");
                this.f5408f.h();
                e();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void e() {
        this.f5409g.set(true);
        this.f5403a.removeCallbacks(this.f5410h);
        this.f5403a.postDelayed(this.f5410h, this.f5412j);
    }

    void a(long j2) {
        this.f5412j = j2;
    }

    void a() {
        this.f5403a.removeCallbacks(this.f5410h);
        this.f5403a.removeCallbacks(this.f5411i);
        this.f5409g.set(false);
    }

    void a(com.bytedance.common.wschannel.channel.impl.ok.ws.b bVar, ByteString byteString) {
        Logger.d(b.f5415a, "receive pong");
        this.f5409g.set(false);
        this.f5403a.removeCallbacks(this.f5410h);
    }

    long b() {
        return this.f5407e;
    }

    void a(com.bytedance.common.wschannel.channel.impl.ok.ws.b bVar, Response response) {
        this.f5408f = bVar;
        a(response);
        c();
    }

    private void a(Response response) {
        String header;
        String[] split;
        if (response == null || (header = response.header("Handshake-Options")) == null) {
            return;
        }
        for (String str : header.split(Constants.PACKNAME_END)) {
            if (!TextUtils.isEmpty(str)) {
                String[] split2 = str.split("=");
                if ("ping-interval".equals(split2[0])) {
                    try {
                        this.f5405c = Long.parseLong(split2[1]) * 1000;
                        return;
                    } catch (NumberFormatException unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
    }
}
