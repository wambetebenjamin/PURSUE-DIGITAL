package com.bytedance.common.jato.memory;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.math.BigInteger;
import java.util.ArrayList;
/* compiled from: WeakRefUnblockTest.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    static final boolean f4900a = false;

    /* renamed from: b  reason: collision with root package name */
    static final int f4901b = 15;

    /* renamed from: c  reason: collision with root package name */
    static final int f4902c = 10;

    /* renamed from: d  reason: collision with root package name */
    static final int f4903d = 20000;

    /* renamed from: e  reason: collision with root package name */
    static final int f4904e = 10;

    /* renamed from: f  reason: collision with root package name */
    static final boolean f4905f = true;

    /* renamed from: g  reason: collision with root package name */
    static final int f4906g = 10;

    /* renamed from: h  reason: collision with root package name */
    static final int f4907h = 300000;

    /* renamed from: i  reason: collision with root package name */
    static final int f4908i = 30000;

    /* renamed from: j  reason: collision with root package name */
    static volatile boolean f4909j = false;
    static Runnable l = new Runnable() { // from class: com.bytedance.common.jato.memory.c.1
        @Override // java.lang.Runnable
        public void run() {
            ArrayList arrayList = new ArrayList(10);
            for (int i2 = 0; i2 < 10; i2++) {
                arrayList.add(new SoftReference(a.a(15)));
            }
            for (int i3 = 0; i3 < 10; i3++) {
                b.a();
            }
            BigInteger bigInteger = BigInteger.ZERO;
            while (!c.f4909j) {
                for (int i4 = 0; i4 < 10000; i4++) {
                    bigInteger = bigInteger.add(BigInteger.TEN);
                }
                if (bigInteger.longValue() % 10 != 0) {
                    System.out.println("Bad allocFinalizable counter value: " + bigInteger);
                }
                Runtime.getRuntime().gc();
            }
            a aVar = (a) ((SoftReference) arrayList.get(5)).get();
            if (aVar != null) {
                aVar.a(15, 47);
            }
        }
    };
    ArrayList<WeakReference<Integer>> k = new ArrayList<>((int) f4907h);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: WeakRefUnblockTest.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        a f4910a;

        /* renamed from: b  reason: collision with root package name */
        a f4911b;

        a(a aVar, a aVar2) {
            this.f4910a = aVar;
            this.f4911b = aVar2;
        }

        static a a(int i2) {
            if (i2 == 0) {
                return null;
            }
            int i3 = i2 - 1;
            return new a(a(i3), a(i3));
        }

        void a(int i2, int i3) {
            int i4 = 0;
            a aVar = this;
            while (i4 < i2) {
                if ((i3 & 1) == 0) {
                    aVar = aVar.f4910a;
                } else {
                    aVar = aVar.f4911b;
                }
                i4++;
                i3 >>>= 1;
            }
            if (aVar != null) {
                System.out.println("Complete binary tree path too long");
            }
        }
    }

    /* compiled from: WeakRefUnblockTest.java */
    /* loaded from: classes.dex */
    static class b {

        /* renamed from: b  reason: collision with root package name */
        static b[] f4912b = new b[2];

        /* renamed from: c  reason: collision with root package name */
        static int f4913c;

        /* renamed from: a  reason: collision with root package name */
        a f4914a = a.a(15);

        b() {
        }

        static synchronized void a() {
            synchronized (b.class) {
                b[] bVarArr = f4912b;
                int i2 = f4913c + 1;
                f4913c = i2;
                bVarArr[i2 % 2] = new b();
                int i3 = f4913c;
                if (i3 > 1) {
                    f4912b[(i3 + 1) % 2].f4914a.a(15, i3);
                }
            }
        }

        protected void finalize() {
            this.f4914a.a(15, 42);
            a();
        }
    }

    void a() {
        for (int i2 = 0; i2 < f4907h; i2++) {
            this.k.add(null);
        }
    }

    long b() {
        long j2 = 0;
        int i2 = 0;
        while (i2 < 10) {
            for (int i3 = 0; i3 < 30000; i3++) {
                int i4 = (i2 * 30000) + i3;
                this.k.set(i4, new WeakReference<>(new Integer(i4)));
            }
            try {
                Thread.sleep(50L);
            } catch (InterruptedException unused) {
                System.out.println("Unexpected exception");
            }
            int i5 = i2 + 1;
            for (int i6 = ((i5 * 30000) - i2) - 1; i6 >= 0; i6 -= 10) {
                WeakReference<Integer> weakReference = this.k.get(i6);
                if (weakReference != null) {
                    long nanoTime = System.nanoTime();
                    Integer num = weakReference.get();
                    long nanoTime2 = System.nanoTime() - nanoTime;
                    if (num == null) {
                        this.k.set(i6, null);
                    }
                    j2 = Math.max(j2, nanoTime2);
                    if (num != null && num.intValue() != i6) {
                        System.out.println("Unexpected referent; expected " + i6 + " got " + num.intValue());
                    }
                }
            }
            i2 = i5;
        }
        return j2;
    }

    long c() {
        long b2 = b();
        Runtime.getRuntime().gc();
        System.runFinalization();
        for (int i2 = 0; i2 < f4907h; i2++) {
            if (this.k.get(i2) != null && this.k.get(i2).get() != null) {
                System.out.println("WeakReference to " + i2 + " wasn't cleared");
            }
        }
        return b2;
    }

    long d() {
        int i2;
        int i3;
        Integer[] numArr = new Integer[f4907h];
        long j2 = 0;
        int i4 = 0;
        while (i4 < 10) {
            int i5 = i4 * 30000;
            while (true) {
                i2 = i4 + 1;
                i3 = i2 * 30000;
                if (i5 >= i3) {
                    break;
                }
                Integer num = new Integer(i5);
                numArr[i5] = num;
                this.k.set(i5, new WeakReference<>(num));
                i5++;
            }
            for (int i6 = i3 - 1; i6 >= 0; i6--) {
                long nanoTime = System.nanoTime();
                Integer num2 = this.k.get(i6).get();
                j2 = Math.max(j2, System.nanoTime() - nanoTime);
                if (num2 == null) {
                    System.out.println("Unexpectedly cleared referent at " + i6);
                } else if (num2.intValue() != i6) {
                    System.out.println("Unexpected reachable referent; expected " + i6 + " got " + num2.intValue());
                }
            }
            i4 = i2;
        }
        Reference.reachabilityFence(numArr);
        return j2;
    }

    void e() {
        System.out.println("Starting");
        a();
        long c2 = c();
        long d2 = d();
        String format = String.format("%,.3f", Double.valueOf(c2 / 1000000.0d));
        String format2 = String.format("%,.3f", Double.valueOf(d2 / 1000000.0d));
        System.out.println("Max time for WeakReference.get (unreachable): " + format);
        System.out.println("Max time for WeakReference.get (reachable): " + format2);
        if (c2 > 10000000000L) {
            System.out.println("WeakReference.get (unreachable) time unreasonably long");
        }
        if (d2 > 10000000000L) {
            System.out.println("WeakReference.get (reachable) time unreasonably long");
        }
    }

    public static void a(String[] strArr) {
        c cVar = new c();
        Thread thread = new Thread(l);
        thread.setDaemon(true);
        thread.start();
        cVar.e();
        f4909j = true;
        thread.join();
        System.out.println("Finished");
    }
}
