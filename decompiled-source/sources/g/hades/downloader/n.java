package g.hades.downloader;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
/* loaded from: classes10.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    public Object f19094a = new Object();

    /* renamed from: b  reason: collision with root package name */
    public Queue<b> f19095b = new ConcurrentLinkedQueue();

    /* renamed from: c  reason: collision with root package name */
    public a f19096c;

    /* renamed from: d  reason: collision with root package name */
    public Handler f19097d;

    /* loaded from: classes10.dex */
    public class a extends HandlerThread {
        public a(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        public void onLooperPrepared() {
            super.onLooperPrepared();
            Looper looper = getLooper();
            synchronized (n.this.f19094a) {
                n.this.f19097d = new Handler(looper);
            }
            while (!n.this.f19095b.isEmpty()) {
                b poll = n.this.f19095b.poll();
                n.this.f19097d.postDelayed(poll.f19099a, poll.f19100b);
            }
        }
    }

    /* loaded from: classes10.dex */
    public class b {

        /* renamed from: a  reason: collision with root package name */
        public Runnable f19099a;

        /* renamed from: b  reason: collision with root package name */
        public long f19100b;

        public b(n nVar, Runnable runnable, long j2) {
            this.f19099a = runnable;
            this.f19100b = j2;
        }
    }

    public n(String str) {
        this.f19096c = new a(str);
    }

    public void a(Runnable runnable, long j2) {
        if (this.f19097d == null) {
            synchronized (this.f19094a) {
                if (this.f19097d == null) {
                    this.f19095b.add(new b(this, runnable, j2));
                    return;
                }
            }
        }
        this.f19097d.postDelayed(runnable, j2);
    }
}
