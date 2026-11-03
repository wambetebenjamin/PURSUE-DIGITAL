package com.ss.cgpa;

import android.util.Log;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
/* loaded from: classes9.dex */
public class c extends com.ss.cgpa.a {

    /* renamed from: a  reason: collision with root package name */
    private int f18766a;

    /* renamed from: b  reason: collision with root package name */
    private int f18767b;

    /* renamed from: c  reason: collision with root package name */
    private int f18768c;

    /* renamed from: d  reason: collision with root package name */
    private int f18769d;

    /* renamed from: e  reason: collision with root package name */
    private int f18770e;

    /* renamed from: f  reason: collision with root package name */
    private SelectionKey f18771f;

    /* renamed from: g  reason: collision with root package name */
    private SocketChannel f18772g;

    /* renamed from: h  reason: collision with root package name */
    private DataClientHelp f18773h;

    /* renamed from: i  reason: collision with root package name */
    private String f18774i;

    /* renamed from: j  reason: collision with root package name */
    private boolean f18775j;
    private int k;
    private String l;

    /* loaded from: classes9.dex */
    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private static final c f18776a = new c(100, 300, 36666);
    }

    private c(int i2, int i3, int i4) {
        this(i2, i3, i4, i4 + 10);
    }

    private c(int i2, int i3, int i4, int i5) {
        this.f18771f = null;
        this.f18772g = null;
        this.f18773h = null;
        this.f18774i = null;
        this.f18775j = false;
        this.k = -1;
        this.l = getClass().getName();
        b(i2, i3);
        a(i4, i5);
        super.a(this.l);
    }

    private boolean e() {
        try {
            if (this.f18772g == null) {
                SocketChannel open = SocketChannel.open();
                this.f18772g = open;
                open.configureBlocking(false);
            }
        } catch (Exception e2) {
            Log.e(this.l, e2.toString());
            f();
        }
        return this.f18772g != null;
    }

    private void f() {
        SocketChannel socketChannel = this.f18772g;
        if (socketChannel != null) {
            try {
                socketChannel.close();
            } catch (Exception e2) {
                Log.e(this.l, e2.toString());
            }
            this.f18772g = null;
        }
    }

    private int g() {
        while (this.f18770e < this.f18769d) {
            try {
            } catch (Exception e2) {
                Log.e(this.l, e2.toString());
                f();
            }
            if (!e()) {
                return -1;
            }
            SocketChannel socketChannel = this.f18772g;
            int i2 = this.f18770e;
            this.f18770e = i2 + 1;
            if (!socketChannel.connect(new InetSocketAddress(i2))) {
                this.f18771f = this.f18772g.register(d(), 8);
                return 0;
            } else if (i()) {
                return 1;
            }
        }
        return -1;
    }

    public static final c h() {
        return b.f18776a;
    }

    private boolean i() {
        try {
            if (this.f18772g.finishConnect()) {
                DataClientHelp dataClientHelp = new DataClientHelp(this.f18774i);
                this.f18771f.interestOps(5);
                this.f18771f.attach(dataClientHelp);
                this.f18773h = dataClientHelp;
                return true;
            }
        } catch (Exception e2) {
            Log.e(this.l, e2.toString());
        }
        f();
        return false;
    }

    private boolean k() {
        this.f18770e = this.f18768c;
        while (true) {
            int g2 = g();
            if (g2 > 0) {
                return true;
            }
            if (g2 < 0) {
                return false;
            }
            if (a(this.f18767b) > 0) {
                Iterator<SelectionKey> it = c().iterator();
                while (it.hasNext()) {
                    SelectionKey next = it.next();
                    it.remove();
                    if (next.isConnectable() && i()) {
                        return true;
                    }
                    next.cancel();
                }
                continue;
            }
            this.f18771f.cancel();
            f();
        }
    }

    public void a(int i2, int i3) {
        this.f18768c = i2;
        this.f18769d = i3;
    }

    public void b(int i2, int i3) {
        this.f18766a = i2;
        this.f18767b = i3;
    }

    public void b(String str) {
        c(str);
        j();
        this.k = 0;
    }

    public void c(String str) {
        this.f18774i = str;
    }

    public void j() {
        if (this.f18775j) {
            return;
        }
        this.f18775j = true;
        if (this.f18774i == null) {
            this.k = 0;
        } else if (!b()) {
            this.k = 0;
        } else if (!k()) {
            this.k = 0;
            a();
            f();
            DataClientHelp dataClientHelp = this.f18773h;
            if (dataClientHelp != null) {
                dataClientHelp.close();
            }
        } else {
            this.k = 1;
            while (true) {
                int a2 = a(this.f18766a);
                if (a2 == 0) {
                    if (this.f18773h.procWriteCgpMsg() > 0) {
                        SelectionKey selectionKey = this.f18771f;
                        selectionKey.interestOps(selectionKey.interestOps() | 4);
                    }
                } else if (a2 < 0) {
                    break;
                } else {
                    Iterator<SelectionKey> it = c().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            SelectionKey next = it.next();
                            it.remove();
                            if (next.isReadable() && !a(next)) {
                                a();
                                break;
                            } else if (next.isValid() && next.isWritable()) {
                                b(next);
                            }
                        }
                    }
                }
            }
            a();
            f();
            if (this.f18771f != null) {
                this.f18771f = null;
            }
            DataClientHelp dataClientHelp2 = this.f18773h;
            if (dataClientHelp2 != null) {
                dataClientHelp2.close();
                this.f18773h = null;
            }
        }
    }
}
