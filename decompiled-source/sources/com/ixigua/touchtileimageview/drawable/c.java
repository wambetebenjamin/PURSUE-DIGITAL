package com.ixigua.touchtileimageview.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextPaint;
import android.util.Log;
import androidx.collection.ArrayMap;
import androidx.collection.LruCache;
import androidx.core.internal.view.SupportMenu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
/* compiled from: BitmapRegionDecoderDrawable.java */
/* loaded from: classes9.dex */
public final class c extends Drawable {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17073a = "BitmapRegionDecoder";
    private boolean A;
    private boolean B;
    private l C;
    private l D;
    private LruCache<k, Bitmap> E;
    private final e<Bitmap> F;
    private final Set<k> G;
    private volatile boolean H;
    private volatile boolean I;

    /* renamed from: J  reason: collision with root package name */
    private final a f17074J;
    private Bitmap.Config K;
    private Runnable L;
    private TextPaint M;
    private Rect N;

    /* renamed from: e  reason: collision with root package name */
    private final ExecutorService f17078e;

    /* renamed from: f  reason: collision with root package name */
    private final List<g> f17079f;

    /* renamed from: g  reason: collision with root package name */
    private final int f17080g;

    /* renamed from: h  reason: collision with root package name */
    private final int f17081h;

    /* renamed from: i  reason: collision with root package name */
    private final int f17082i;
    private final Matrix n;
    private final boolean o;
    private final Paint p;
    private final Paint q;
    private final Paint r;
    private final Paint s;
    private final Rect t;
    private final Matrix u;
    private int v;
    private final AtomicReference<f> w;
    private final BlockingQueue<k> x;
    private List<Future<?>> y;
    private boolean z;

    /* renamed from: b  reason: collision with root package name */
    private boolean f17075b = false;

    /* renamed from: c  reason: collision with root package name */
    private final Handler f17076c = new Handler(Looper.getMainLooper());

    /* renamed from: d  reason: collision with root package name */
    private final com.ixigua.touchtileimageview.drawable.b f17077d = new com.ixigua.touchtileimageview.drawable.b(Looper.getMainLooper());

    /* renamed from: j  reason: collision with root package name */
    private final Matrix f17083j = new Matrix();
    private final Matrix k = new Matrix();
    private final RectF l = new RectF();
    private final RectF m = new RectF();

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    public c(List<g> list, int i2, Bitmap.Config config, ExecutorService executorService) {
        Matrix matrix = new Matrix();
        this.n = matrix;
        this.p = new Paint();
        Paint paint = new Paint();
        this.q = paint;
        Paint paint2 = new Paint();
        this.r = paint2;
        Paint paint3 = new Paint();
        this.s = paint3;
        this.t = new Rect();
        this.u = new Matrix();
        this.v = -1;
        this.w = new AtomicReference<>();
        this.x = new j();
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = new e<>(10);
        this.G = Collections.newSetFromMap(new ConcurrentHashMap());
        this.H = false;
        this.I = false;
        this.f17074J = new a();
        this.K = null;
        this.L = new Runnable() { // from class: com.ixigua.touchtileimageview.drawable.c.1
            @Override // java.lang.Runnable
            public void run() {
                c.this.invalidateSelf();
                c.this.a("invalidateSelf");
            }
        };
        this.M = new TextPaint();
        this.N = new Rect();
        ArrayList arrayList = new ArrayList(list);
        this.f17079f = arrayList;
        int width = ((g) arrayList.get(0)).getWidth();
        this.f17080g = width;
        int height = ((g) arrayList.get(0)).getHeight();
        this.f17081h = height;
        this.f17082i = i2;
        this.K = config;
        this.f17078e = executorService;
        paint.setColor(922746624);
        paint2.setColor(922681344);
        paint3.setColor(905969919);
        if (i2 != 0) {
            matrix.setRotate(i2);
            RectF rectF = new RectF(0.0f, 0.0f, width, height);
            matrix.mapRect(rectF, rectF);
            matrix.postTranslate(-rectF.left, -rectF.top);
            this.o = true;
            return;
        }
        this.o = false;
    }

    private void a() {
        int i2;
        int i3;
        if (this.v != -1) {
            return;
        }
        int min = Math.min(Math.max(1, Math.round(Math.min(Math.max(this.f17080g, this.f17081h) / 4.0f, Math.max(this.m.width(), this.m.height()) / 2.0f) / 256.0f)), 4);
        if (min <= 0) {
            throw new IllegalArgumentException("tileScaleValue must > 0");
        }
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 26) {
            long b2 = b();
            while (true) {
                i2 = min * 256;
                if (b2 >= a(i2, 4) || min - 1 < 1) {
                    break;
                }
                min = i3;
            }
            if (b2 < a(i2, 4)) {
                a("current free memory " + ((b2 / 1024) / 1024) + " mb is < " + ((a(i2, 4) / 1024) / 1024) + " mb, use RGB_565 to decode");
                if (this.K == Bitmap.Config.ARGB_8888) {
                    this.K = Bitmap.Config.RGB_565;
                }
                if (b2 < a(i2, 2)) {
                    a("free memory is too small, even use RGB_565, will throw OOM");
                }
            }
            if (min <= 0) {
                throw new IllegalArgumentException("tileScaleValue must > 0");
            }
        }
        this.v = min * 256;
        a("Tile size " + this.v);
    }

    private long a(int i2, int i3) {
        float f2 = i2;
        return (((int) Math.ceil(Math.min(getIntrinsicWidth(), this.m.width() * 2.0f) / f2)) + 1) * (((int) Math.ceil(Math.min(getIntrinsicHeight(), this.m.height() * 2.0f) / f2)) + 1) * i2 * i2 * i3;
    }

    private static long b() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.maxMemory() - (runtime.totalMemory() - runtime.freeMemory());
    }

    public void setDebug(boolean z) {
        this.f17075b = z;
        invalidateSelf();
    }

    public void setUseInBitmap(boolean z) {
        if (this.B == z) {
            return;
        }
        this.B = z;
        if (z) {
            return;
        }
        h();
    }

    public void setUseLruCache(boolean z) {
        if (this.z == z) {
            return;
        }
        this.z = z;
        LruCache<k, Bitmap> lruCache = this.E;
        if (lruCache != null) {
            lruCache.resize(c());
        }
    }

    public void setUsePrefetch(boolean z) {
        if (this.A == z) {
            return;
        }
        this.A = z;
        LruCache<k, Bitmap> lruCache = this.E;
        if (lruCache != null) {
            lruCache.resize(c());
        }
    }

    public void updateVisibleAreaIfNeeded(Matrix matrix, Matrix matrix2, RectF rectF, RectF rectF2) {
        this.f17083j.set(this.n);
        this.f17083j.postConcat(matrix);
        this.f17083j.postConcat(matrix2);
        this.f17083j.invert(this.k);
        if (rectF2 != null) {
            RectF rectF3 = new RectF(rectF);
            if (rectF3.intersect(rectF2)) {
                this.k.mapRect(this.l, rectF3);
            } else {
                this.l.set(0.0f, 0.0f, 0.0f, 0.0f);
            }
        } else {
            this.k.mapRect(this.l, rectF);
        }
        this.f17083j.set(matrix);
        this.f17083j.postConcat(matrix2);
        int i2 = 1;
        float scaleValue = com.ixigua.touchtileimageview.utils.h.scaleValue(this.f17083j);
        if (1.0f > scaleValue) {
            while (0.5f / i2 >= scaleValue) {
                i2 *= 2;
            }
        }
        int i3 = i2;
        int i4 = this.v * i3;
        float min = Math.min(Math.max(0.0f, this.l.left), this.f17080g);
        float min2 = Math.min(Math.max(0.0f, this.l.top), this.f17081h);
        float max = Math.max(Math.min(this.f17080g, this.l.right), 0.0f);
        float max2 = Math.max(Math.min(this.f17081h, this.l.bottom), 0.0f);
        double d2 = i4;
        float f2 = i4;
        int floor = (int) (Math.floor(min / f2) * d2);
        int floor2 = (int) (Math.floor(min2 / f2) * d2);
        int ceil = (int) (Math.ceil(max / f2) * d2);
        int ceil2 = (int) (d2 * Math.ceil(max2 / f2));
        f fVar = this.w.get();
        if (fVar == null || fVar.f17113a != floor || fVar.f17114b != floor2 || fVar.f17115c != ceil || fVar.f17116d != ceil2 || fVar.f17117e != i3) {
            if (fVar == null || fVar.f17117e != i3) {
                g();
                this.D = this.C;
                this.C = new l(i3);
            }
            this.w.set(new f(floor, floor2, ceil, ceil2, i3));
        }
        if (!this.m.equals(rectF)) {
            this.m.set(rectF);
            LruCache<k, Bitmap> lruCache = this.E;
            if (lruCache == null) {
                this.E = new C0307c(c());
            } else {
                lruCache.resize(c());
            }
        }
        a();
        d();
    }

    /* compiled from: BitmapRegionDecoderDrawable.java */
    /* renamed from: com.ixigua.touchtileimageview.drawable.c$c  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    private class C0307c extends LruCache<k, Bitmap> {
        private C0307c(int i2) {
            super(i2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.collection.LruCache
        /* renamed from: a */
        public void entryRemoved(boolean z, k kVar, Bitmap bitmap, Bitmap bitmap2) {
            super.entryRemoved(z, kVar, bitmap, bitmap2);
            if (z) {
                c.this.a(kVar, bitmap);
            } else if (bitmap == null || bitmap2 == null) {
            } else {
                bitmap.recycle();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(k kVar, Bitmap bitmap) {
        if (this.B && bitmap.isMutable() && bitmap.getWidth() == this.v && bitmap.getHeight() == this.v) {
            a("inBitmap try to recycle invisible Bitmap to pool " + bitmap.toString() + " tile " + kVar.toString());
            if (this.F.release(bitmap)) {
                a("inBitmap recycle invisible Bitmap to pool success " + bitmap.toString() + " tile " + kVar.toString());
            } else {
                a("inBitmap recycle invisible Bitmap to pool fail, call Bitmap recycle");
                bitmap.recycle();
            }
        } else {
            bitmap.recycle();
        }
        k.b(kVar);
    }

    private int c() {
        int round;
        int round2;
        if (this.z || this.A) {
            if (this.A) {
                round = Math.round(getIntrinsicWidth() / this.v) + 1;
                round2 = Math.round(getIntrinsicHeight() / this.v);
            } else {
                float min = Math.min(getIntrinsicWidth(), this.m.width() * 2.0f);
                float min2 = Math.min(getIntrinsicHeight(), this.m.height() * 2.0f);
                round = Math.round(min / this.v) + 1;
                round2 = Math.round(min2 / this.v);
            }
            return round * (round2 + 1);
        }
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(f fVar, k kVar) {
        return kVar.f17128e == fVar.f17117e && kVar.f17126c > fVar.f17113a && kVar.f17127d > fVar.f17114b && kVar.f17124a < fVar.f17115c && kVar.f17125b < fVar.f17116d;
    }

    private void d() {
        if (this.y != null) {
            return;
        }
        a("submit decode task");
        this.y = new ArrayList();
        for (g gVar : this.f17079f) {
            this.y.add(this.f17078e.submit(new b(gVar)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BitmapRegionDecoderDrawable.java */
    /* loaded from: classes9.dex */
    public class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        final Rect f17092a;

        /* renamed from: b  reason: collision with root package name */
        final BitmapFactory.Options f17093b;

        /* renamed from: c  reason: collision with root package name */
        final g f17094c;

        private b(g gVar) {
            this.f17092a = new Rect();
            this.f17093b = new BitmapFactory.Options();
            this.f17094c = gVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a();
            } catch (Throwable th) {
                c.this.f17076c.post(new Runnable() { // from class: com.ixigua.touchtileimageview.drawable.c.b.1
                    @Override // java.lang.Runnable
                    public void run() {
                        throw new RuntimeException(th);
                    }
                });
            }
            if (c.this.H) {
                this.f17094c.recycle();
            }
        }

        private void a() {
            while (true) {
                int i2 = 4;
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        final k kVar = (k) c.this.x.take();
                        if (c.this.I) {
                            c.this.a("TouchBaseImageView is in disappear animation, skip decode");
                        } else {
                            f fVar = (f) c.this.w.get();
                            if (kVar.f17129f && kVar.f17128e != fVar.f17117e) {
                                k.b(kVar);
                            } else if (kVar.f17129f || c.b(fVar, kVar)) {
                                c.this.G.add(k.a(kVar));
                                final Bitmap a2 = c.this.a(this.f17094c, kVar, this.f17092a, this.f17093b);
                                if (c.this.H && a2 != null) {
                                    a2.recycle();
                                    a2 = null;
                                    c.this.G.remove(kVar);
                                    k.b(kVar);
                                }
                                if (a2 != null) {
                                    c.this.G.remove(kVar);
                                    if (!c.b((f) c.this.w.get(), kVar)) {
                                        c.this.a("after decode, tile is not in visible area or scale changed, recycle");
                                        c.this.d(kVar, a2);
                                    } else {
                                        c.this.G.add(k.a(kVar));
                                        c.this.f17077d.postAsyncIfNeeded(new Runnable() { // from class: com.ixigua.touchtileimageview.drawable.c.b.2
                                            @Override // java.lang.Runnable
                                            public void run() {
                                                c.this.G.remove(kVar);
                                                if (!c.this.H) {
                                                    if (c.this.C == null || c.this.C.f17130a != kVar.f17128e || c.this.C.f17131b.get(kVar) != null) {
                                                        c.this.a(kVar, a2);
                                                        return;
                                                    } else {
                                                        c.this.C.f17131b.put(kVar, a2);
                                                        return;
                                                    }
                                                }
                                                k.b(kVar);
                                                a2.recycle();
                                            }
                                        });
                                        c.this.a("put Tile to tileMap" + kVar.toString());
                                        i2--;
                                    }
                                }
                                if (i2 <= 0 || c.this.x.isEmpty()) {
                                    c.this.f17076c.post(c.this.L);
                                }
                            } else {
                                c.this.a("before decode, tile is not in visible area or scale changed, skip");
                                k.b(kVar);
                            }
                        }
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap a(g gVar, k kVar, Rect rect, BitmapFactory.Options options) {
        Bitmap bitmap;
        Bitmap remove;
        a("decode Tile " + kVar.toString());
        LruCache<k, Bitmap> lruCache = this.E;
        if (lruCache != null && (remove = lruCache.remove(kVar)) != null) {
            a("find tile from lrucache, return");
            return remove;
        }
        rect.set(kVar.f17124a, kVar.f17125b, Math.min(this.f17080g, kVar.f17126c), Math.min(this.f17081h, kVar.f17127d));
        options.inSampleSize = kVar.f17128e;
        options.inPreferredConfig = this.K;
        boolean z = false;
        options.inMutable = false;
        options.inBitmap = null;
        if (this.B && rect.width() == this.v && rect.height() == this.v) {
            options.inBitmap = a(kVar, options);
            if (options.inBitmap != null) {
                options.inMutable = true;
            }
            z = true;
        }
        try {
            bitmap = gVar.decodeRegion(rect, options);
        } catch (OutOfMemoryError e2) {
            e2.printStackTrace();
            e();
            bitmap = null;
        }
        if (bitmap != null) {
            a("decode Tile success " + bitmap.toString() + " tile " + kVar.toString());
            if (z && options.inBitmap != null && bitmap != options.inBitmap) {
                options.inBitmap.recycle();
                options.inBitmap = null;
            }
        } else {
            a("decode Tile failed  tile " + kVar.toString());
            if (z) {
                a("decode Tile failed, retry without inBitmap  tile " + kVar.toString());
                if (options.inBitmap != null) {
                    options.inBitmap.recycle();
                    options.inBitmap = null;
                }
                try {
                    bitmap = gVar.decodeRegion(rect, options);
                } catch (OutOfMemoryError e3) {
                    e3.printStackTrace();
                    e();
                }
                if (bitmap != null) {
                    a("decode Tile failed, retry without inBitmap success " + bitmap.toString() + " tile " + kVar.toString());
                } else {
                    a("decode Tile failed, retry without inBitmap failed  tile " + kVar.toString());
                }
            }
        }
        return bitmap;
    }

    private void e() {
        h();
        this.f17076c.post(new Runnable() { // from class: com.ixigua.touchtileimageview.drawable.c.2
            @Override // java.lang.Runnable
            public void run() {
                c.this.a("releaseCacheBecauseOfOOM");
                c.this.g();
                c.this.f();
                c.this.h();
            }
        });
    }

    private void a(Canvas canvas, k kVar, int i2) {
        if (this.f17075b) {
            int i3 = kVar.f17124a;
            int i4 = kVar.f17125b;
            int i5 = kVar.f17128e;
            this.M.setColor(SupportMenu.CATEGORY_MASK);
            this.M.setTextSize(170.0f);
            String valueOf = String.valueOf(i2);
            this.M.getTextBounds(valueOf, 0, valueOf.length(), this.N);
            canvas.drawText(valueOf, (i3 + ((this.v * i5) / 2)) - this.N.centerX(), (i4 + ((this.v * i5) / 2)) - this.N.centerY(), this.M);
        }
    }

    private int a(int i2, int i3, int i4, int i5, int i6, int i7) {
        int i8 = 0;
        if (i7 > 0 && i2 < i4 && i3 < i5) {
            int i9 = this.v * i6;
            while (i3 < i5) {
                int i10 = i2;
                while (i10 < i4) {
                    if (i8 >= i7) {
                        return i8;
                    }
                    int i11 = i10 + i9;
                    int i12 = i3 + i9;
                    if (i10 < i11 && i3 < i12) {
                        k a2 = k.a(i10, i3, i11, i12, i6, true);
                        if (this.E.get(a2) != null) {
                            k.b(a2);
                        } else {
                            this.x.add(a2);
                            a("add Tile to queue for fill invisible area prefetch, current size " + this.x.size());
                            i8++;
                        }
                    }
                    i10 = i11;
                }
                i3 += i9;
            }
            return i8;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: BitmapRegionDecoderDrawable.java */
    /* loaded from: classes9.dex */
    public class a {

        /* renamed from: b  reason: collision with root package name */
        private ArrayMap<Integer, C0306a> f17087b;

        private a() {
            this.f17087b = new ArrayMap<>();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* compiled from: BitmapRegionDecoderDrawable.java */
        /* renamed from: com.ixigua.touchtileimageview.drawable.c$a$a  reason: collision with other inner class name */
        /* loaded from: classes9.dex */
        public class C0306a {

            /* renamed from: a  reason: collision with root package name */
            final long f17088a;

            /* renamed from: b  reason: collision with root package name */
            long f17089b;

            /* renamed from: c  reason: collision with root package name */
            boolean f17090c;

            private C0306a(long j2) {
                this.f17090c = false;
                this.f17088a = j2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(int i2, long j2) {
            if (this.f17087b.get(Integer.valueOf(i2)) == null) {
                this.f17087b.put(Integer.valueOf(i2), new C0306a(j2));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b(int i2, long j2) {
            C0306a c0306a = this.f17087b.get(Integer.valueOf(i2));
            if (c0306a == null || c0306a.f17089b != 0) {
                return;
            }
            c0306a.f17089b = j2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            for (Integer num : this.f17087b.keySet()) {
                C0306a c0306a = this.f17087b.get(num);
                if (c0306a.f17089b != 0 && !c0306a.f17090c) {
                    c.this.a("performance scale " + num + " fill cost " + (c0306a.f17089b - c0306a.f17088a));
                    c0306a.f17090c = true;
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Bitmap bitmap;
        ArrayMap<k, Bitmap> arrayMap;
        int i2;
        int i3;
        Canvas canvas2;
        int i4;
        Canvas canvas3 = canvas;
        a("draw");
        if (this.o) {
            canvas.save();
            canvas3.concat(this.n);
        }
        if (this.f17075b) {
            canvas.drawRect(0.0f, 0.0f, this.f17080g, this.f17081h, this.q);
        }
        f fVar = this.w.get();
        int i5 = fVar.f17117e;
        int i6 = fVar.f17113a;
        int i7 = fVar.f17114b;
        int i8 = fVar.f17115c;
        int i9 = fVar.f17116d;
        if (this.f17075b) {
            this.f17074J.a(i5, System.currentTimeMillis());
        }
        b(i6, i7, i8, i9, i5);
        ArrayMap<k, Bitmap> arrayMap2 = this.C.f17131b;
        k[] a2 = a(i6, i7, i8, i9, i5);
        int length = a2.length;
        int i10 = 0;
        int i11 = 4;
        int i12 = 0;
        int i13 = 0;
        while (i13 < length) {
            int i14 = i9;
            k kVar = a2[i13];
            k[] kVarArr = a2;
            int i15 = kVar.f17124a;
            int i16 = i8;
            int i17 = kVar.f17125b;
            int i18 = i7;
            int i19 = kVar.f17126c;
            int i20 = i6;
            int i21 = kVar.f17127d;
            Bitmap bitmap2 = arrayMap2.get(kVar);
            if (bitmap2 == null) {
                bitmap = this.E.remove(kVar);
                if (bitmap != null) {
                    arrayMap2.put(k.a(kVar), bitmap);
                    a("reuse LruCache bitmap, put to TileMap");
                }
            } else {
                bitmap = bitmap2;
            }
            if (bitmap != null) {
                this.u.reset();
                this.u.postTranslate(kVar.f17124a, kVar.f17125b);
                this.u.preScale(kVar.f17128e, kVar.f17128e);
                canvas3.drawBitmap(bitmap, this.u, this.p);
                i4 = i10;
                i2 = i13;
                i3 = length;
                canvas2 = canvas3;
                arrayMap = arrayMap2;
            } else {
                int i22 = i10;
                int i23 = i11;
                arrayMap = arrayMap2;
                i2 = i13;
                i3 = length;
                int a3 = a(canvas, i15, i17, i19, i21, i5) + i12;
                if (i23 < 0 || this.G.contains(kVar)) {
                    i11 = i23;
                } else {
                    if (this.I) {
                        a("TouchBaseImageView is in disappear animation, skip add Tile to queue");
                    } else {
                        this.x.add(k.a(i15, i17, i19, i21, i5, false));
                        a("add Tile to queue for fill visible area, current size " + this.x.size());
                    }
                    i11 = i23 - 1;
                }
                if (this.f17075b) {
                    this.u.reset();
                    this.u.postTranslate(i15, i17);
                    float f2 = i5;
                    this.u.preScale(f2, f2);
                    this.t.set(0, 0, i19 - i15, i21 - i17);
                    canvas.save();
                    canvas2 = canvas;
                    canvas2.concat(this.u);
                    canvas2.drawRect(this.t, this.r);
                    canvas.restore();
                } else {
                    canvas2 = canvas;
                }
                i12 = a3;
                i4 = i22;
            }
            a(canvas2, kVar, i4);
            i10 = i4 + 1;
            k.b(kVar);
            i13 = i2 + 1;
            canvas3 = canvas2;
            i9 = i14;
            a2 = kVarArr;
            i8 = i16;
            i7 = i18;
            i6 = i20;
            arrayMap2 = arrayMap;
            length = i3;
        }
        int i24 = i6;
        int i25 = i7;
        int i26 = i8;
        int i27 = i9;
        int i28 = i11;
        a("reuse " + i12 + " previous tiles to fill visible area");
        if (i28 == 4) {
            g();
        }
        if (i28 == 4 && this.A) {
            c(i24, i25, i26, i27, i5);
        }
        if (this.o) {
            canvas.restore();
        }
        if (i28 == 4 && this.f17075b) {
            this.f17074J.b(i5, System.currentTimeMillis());
            this.f17074J.a();
        }
        i();
    }

    private k[] a(int i2, int i3, int i4, int i5, int i6) {
        int i7 = this.v * i6;
        float f2 = i7;
        k[] kVarArr = new k[((int) Math.ceil((i4 - i2) / f2)) * ((int) Math.ceil((i5 - i3) / f2))];
        int i8 = 0;
        while (i3 < i5) {
            int i9 = i2;
            while (i9 < i4) {
                int i10 = i9 + i7;
                kVarArr[i8] = k.a(i9, i3, i10, i3 + i7, i6, false);
                i8++;
                i9 = i10;
            }
            i3 += i7;
        }
        return kVarArr;
    }

    private int a(Canvas canvas, int i2, int i3, int i4, int i5, int i6) {
        l lVar = this.D;
        int i7 = 0;
        if (lVar == null) {
            return 0;
        }
        int i8 = lVar.f17130a;
        int i9 = this.v * i8;
        int i10 = (i2 / i9) * i9;
        canvas.save();
        canvas.clipRect(i2, i3, i4, i5);
        for (int i11 = (i3 / i9) * i9; i11 < i5; i11 += i9) {
            int i12 = i10;
            while (i12 < i4) {
                int i13 = i12 + i9;
                k a2 = k.a(i12, i11, i13, i11 + i9, i8, false);
                Bitmap bitmap = this.D.f17131b.get(a2);
                if (bitmap != null) {
                    this.u.reset();
                    this.u.postTranslate(a2.f17124a, a2.f17125b);
                    this.u.preScale(a2.f17128e, a2.f17128e);
                    canvas.drawBitmap(bitmap, this.u, this.p);
                    i7++;
                }
                k.b(a2);
                i12 = i13;
            }
        }
        canvas.restore();
        return i7;
    }

    private void b(int i2, int i3, int i4, int i5, int i6) {
        l lVar = this.C;
        if (lVar != null && lVar.f17131b.size() > 0) {
            Iterator<Map.Entry<k, Bitmap>> it = this.C.f17131b.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<k, Bitmap> next = it.next();
                k key = next.getKey();
                Bitmap value = next.getValue();
                if (key.f17126c <= i2 || key.f17127d <= i3 || key.f17124a >= i4 || key.f17125b >= i5 || key.f17128e != i6) {
                    b(key, value);
                    it.remove();
                }
            }
        }
        l lVar2 = this.D;
        if (lVar2 == null || lVar2.f17131b.size() <= 0) {
            return;
        }
        Iterator<Map.Entry<k, Bitmap>> it2 = this.D.f17131b.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry<k, Bitmap> next2 = it2.next();
            k key2 = next2.getKey();
            Bitmap value2 = next2.getValue();
            if (key2.f17126c <= i2 || key2.f17127d <= i3 || key2.f17124a >= i4 || key2.f17125b >= i5) {
                c(key2, value2);
                it2.remove();
            }
        }
    }

    private void c(int i2, int i3, int i4, int i5, int i6) {
        int a2 = a(0, 0, this.f17080g, i3, i6, 4);
        int a3 = a2 + a(0, i3, i2, this.f17081h, i6, 4 - a2);
        a(i2, i5, i4, this.f17081h, i6, 4 - (a3 + a(i4, i3, this.f17080g, this.f17081h, i6, 4 - a3)));
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i2) {
        if (i2 != this.p.getAlpha()) {
            this.p.setAlpha(i2);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        return this.p.getAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.p.setColorFilter(colorFilter);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        int i2 = this.f17082i;
        return (i2 == 0 || i2 == 180) ? this.f17080g : this.f17081h;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        int i2 = this.f17082i;
        return (i2 == 0 || i2 == 180) ? this.f17081h : this.f17080g;
    }

    public void recycle() {
        this.H = true;
        List<Future<?>> list = this.y;
        if (list != null) {
            for (Future<?> future : list) {
                future.cancel(true);
            }
            this.y = null;
        }
        if (this.C != null) {
            for (int i2 = 0; i2 < this.C.f17131b.size(); i2++) {
                k.b(this.C.f17131b.keyAt(i2));
                this.C.f17131b.valueAt(i2).recycle();
            }
            this.C = null;
        }
        g();
        f();
        h();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        a("clearInvisibleSameScaleTileCache");
        LruCache<k, Bitmap> lruCache = this.E;
        if (lruCache != null) {
            lruCache.evictAll();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        a("clearVisibleDifferentScaleTileMap");
        if (this.D != null) {
            for (int i2 = 0; i2 < this.D.f17131b.size(); i2++) {
                a(this.D.f17131b.keyAt(i2), this.D.f17131b.valueAt(i2));
            }
            this.D = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        a("clearInBitmapReusePool");
        while (true) {
            Bitmap acquire = this.F.acquire();
            if (acquire == null) {
                return;
            }
            acquire.recycle();
        }
    }

    private Bitmap a(k kVar, BitmapFactory.Options options) {
        Bitmap acquire = this.F.acquire();
        if (acquire != null) {
            a("inBitmap acquire " + acquire.toString() + " tile " + kVar.toString());
            acquire.eraseColor(0);
        } else {
            try {
                int i2 = this.v;
                acquire = Bitmap.createBitmap(i2, i2, options.inPreferredConfig);
                if (acquire != null) {
                    a("inBitmap create new Bitmap " + acquire.toString() + " tile " + kVar.toString());
                }
            } catch (OutOfMemoryError e2) {
                e2.printStackTrace();
                e();
            }
        }
        return acquire;
    }

    private void b(k kVar, Bitmap bitmap) {
        if (this.z || this.A) {
            this.E.put(k.a(kVar), bitmap);
            k.b(kVar);
            return;
        }
        a(kVar, bitmap);
    }

    private void c(k kVar, Bitmap bitmap) {
        a(kVar, bitmap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(k kVar, Bitmap bitmap) {
        if (kVar.f17128e != this.w.get().f17117e) {
            a(kVar, bitmap);
        } else if (this.z || this.A) {
            this.E.put(k.a(kVar), bitmap);
            k.b(kVar);
        } else {
            a(kVar, bitmap);
        }
    }

    public void prepareToDisappear() {
        this.I = true;
    }

    private void i() {
        if (this.f17075b) {
            int size = this.C.f17131b.size();
            l lVar = this.D;
            int size2 = lVar != null ? lVar.f17131b.size() : 0;
            LruCache<k, Bitmap> lruCache = this.E;
            int size3 = lruCache != null ? lruCache.size() : 0;
            int a2 = this.F.a();
            int i2 = size + size2 + size3 + a2;
            int i3 = this.v;
            a("current tile count visible " + size + " visibleDifferentScaleTileCount " + size2 + " lruCache " + size3 + " inBitmap pool " + a2 + " total " + i2 + " heap " + (((((i2 * i3) * i3) * 4) / 1024) / 1024) + " mb");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        if (this.f17075b) {
            Log.d(f17073a, str);
        }
    }
}
