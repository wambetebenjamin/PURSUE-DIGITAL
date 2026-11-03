package g.hades.downloader;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
/* loaded from: classes10.dex */
public class a implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final String f19082a;

    /* renamed from: b  reason: collision with root package name */
    public final AtomicInteger f19083b = new AtomicInteger();

    /* renamed from: c  reason: collision with root package name */
    public final boolean f19084c;

    public a(String str, boolean z) {
        this.f19082a = str;
        this.f19084c = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, this.f19082a + "-" + this.f19083b.incrementAndGet());
        if (!this.f19084c) {
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
        }
        return thread;
    }
}
