package com.bytedance.gameprotect;

import android.util.DisplayMetrics;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.View;
import com.bytedance.apm.constant.k;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import tt.g.p.f.n.l;
/* loaded from: classes2.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private final ArrayList f8856a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f8857b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f8858c;

    /* renamed from: d  reason: collision with root package name */
    private int f8859d;

    /* renamed from: e  reason: collision with root package name */
    private long f8860e;

    /* renamed from: f  reason: collision with root package name */
    private int f8861f;

    /* renamed from: g  reason: collision with root package name */
    private int f8862g;

    /* renamed from: h  reason: collision with root package name */
    private int f8863h;

    /* renamed from: i  reason: collision with root package name */
    private long f8864i;

    /* renamed from: j  reason: collision with root package name */
    private String f8865j;
    private boolean k;
    private int l;
    private long m;
    private int n;
    private boolean o;
    private e p;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class b {

        /* renamed from: b  reason: collision with root package name */
        private int f8867b;

        /* renamed from: c  reason: collision with root package name */
        private int f8868c;

        /* renamed from: d  reason: collision with root package name */
        private int f8869d;

        /* renamed from: e  reason: collision with root package name */
        private int f8870e;

        /* renamed from: f  reason: collision with root package name */
        private int f8871f;

        /* renamed from: g  reason: collision with root package name */
        private long f8872g;

        /* renamed from: h  reason: collision with root package name */
        private float f8873h;

        /* renamed from: i  reason: collision with root package name */
        private int f8874i;

        public b(int i2, int i3, int i4, int i5, long j2, float f2, int i6, int i7) {
            this.f8867b = i2;
            this.f8868c = i3;
            this.f8869d = i4;
            this.f8870e = i5;
            this.f8872g = j2;
            this.f8873h = f2;
            this.f8871f = i6;
            this.f8874i = i7;
        }

        public int a() {
            return this.f8867b;
        }

        public int b() {
            return this.f8868c;
        }

        public int c() {
            return this.f8869d;
        }

        public int d() {
            return this.f8870e;
        }

        public long e() {
            return this.f8872g;
        }

        public float f() {
            return this.f8873h;
        }

        public int g() {
            return this.f8871f;
        }

        public int h() {
            return this.f8874i;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class c implements View.OnTouchListener {

        /* renamed from: b  reason: collision with root package name */
        private View.OnTouchListener f8876b;

        public c(View.OnTouchListener onTouchListener) {
            this.f8876b = onTouchListener;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (d.this.p != null) {
                d.this.p.a(d.this.n, (int) motionEvent.getX(), (int) motionEvent.getY());
            }
            if (d.this.a(motionEvent)) {
                d.this.b(motionEvent);
            }
            View.OnTouchListener onTouchListener = this.f8876b;
            if (onTouchListener != null) {
                return onTouchListener.onTouch(view, motionEvent);
            }
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: com.bytedance.gameprotect.d$d  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public enum EnumC0126d {
        INSTANCE;
        

        /* renamed from: c  reason: collision with root package name */
        private final d f8879c = new d();

        EnumC0126d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public d a() {
            return this.f8879c;
        }
    }

    /* loaded from: classes2.dex */
    public interface e {
        void a(int i2, int i3, int i4);
    }

    private d() {
        this.f8856a = new ArrayList(100);
        this.f8857b = false;
        this.f8858c = false;
        this.f8859d = 8;
        this.f8860e = k.LAST_STOP_INTERVAL;
        this.f8861f = 100;
        this.f8862g = 0;
        this.f8863h = 0;
        this.f8864i = 0L;
        this.f8865j = "null";
        this.k = true;
        this.l = 1;
        this.m = 0L;
        this.n = 0;
        this.o = false;
        this.p = null;
    }

    public static d a() {
        return EnumC0126d.INSTANCE.a();
    }

    private void a(View view, String str, String str2, String str3) {
        try {
            Method declaredMethod = View.class.getDeclaredMethod(str2, new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(view, new Object[0]);
            Field declaredField = Class.forName(str).getDeclaredField(str3);
            declaredField.setAccessible(true);
            declaredField.set(invoke, new c((View.OnTouchListener) declaredField.get(invoke)));
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 2 || this.f8858c) {
            boolean z = this.k;
            if (!z || this.n >= this.f8861f) {
                if (z || this.l >= this.f8859d || motionEvent.getEventTime() - this.m <= this.f8860e) {
                    return false;
                }
                this.k = true;
                this.l++;
                return true;
            }
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MotionEvent motionEvent) {
        int i2 = this.n + 1;
        this.n = i2;
        if (i2 == 1) {
            this.f8864i = System.currentTimeMillis();
            InputDevice device = motionEvent.getDevice();
            if (device != null) {
                this.f8865j = device.getName();
            }
        }
        synchronized (this.f8856a) {
            this.f8856a.add(new b(motionEvent.getAction(), motionEvent.getDeviceId(), (int) motionEvent.getX(), (int) motionEvent.getY(), motionEvent.getEventTime(), motionEvent.getPressure(), motionEvent.getHistorySize(), motionEvent.getFlags()));
        }
        if (this.n >= this.f8861f) {
            c(motionEvent);
        }
    }

    private void c(MotionEvent motionEvent) {
        this.k = false;
        this.m = motionEvent.getEventTime();
        this.n = 0;
        this.o = true;
    }

    public void a(e eVar) {
        this.p = eVar;
    }

    public boolean a(String str, String str2, String str3, String str4, int i2, int i3, int i4, boolean z) {
        if (this.f8857b) {
            return false;
        }
        this.f8858c = z;
        if (i2 > 0) {
            this.f8859d = i2;
        }
        if (i3 > 0) {
            this.f8860e = i3 * 60 * 1000;
        }
        if (i4 > 0 && i4 < 7) {
            this.f8861f = i4 * 50;
        }
        DisplayMetrics c2 = l.c();
        if (c2 != null) {
            this.f8862g = c2.widthPixels;
            this.f8863h = c2.heightPixels;
        }
        View a2 = l.a(str);
        if (a2 != null) {
            a(a2, str2, str3, str4);
            this.f8857b = true;
            return true;
        }
        return false;
    }

    public Object b() {
        String str;
        HashMap hashMap = new HashMap();
        if (this.o) {
            this.o = false;
            str = "ok";
        } else {
            str = "unOk";
        }
        hashMap.put("result", str);
        return hashMap;
    }

    public Object c() {
        HashMap hashMap = new HashMap();
        hashMap.put("CollectTimes", String.valueOf(this.l));
        hashMap.put("FirstEventTime", String.valueOf(this.f8864i));
        hashMap.put("WidthPixels", String.valueOf(this.f8862g));
        hashMap.put("HeightPixels", String.valueOf(this.f8863h));
        hashMap.put("InputDeviceName", this.f8865j);
        return hashMap;
    }

    public Object d() {
        HashMap hashMap = new HashMap();
        synchronized (this.f8856a) {
            if (this.f8856a.size() > 0) {
                b bVar = (b) this.f8856a.get(0);
                hashMap.put("Action", String.valueOf(bVar.a()));
                hashMap.put("X", String.valueOf(bVar.c()));
                hashMap.put("Y", String.valueOf(bVar.d()));
                hashMap.put("HistorySize", String.valueOf(bVar.g()));
                hashMap.put("EventTime", String.valueOf(bVar.e()));
                hashMap.put("DeviceId", String.valueOf(bVar.b()));
                hashMap.put("Pressure", String.valueOf(bVar.f()));
                hashMap.put("Flags", String.valueOf(bVar.h()));
                this.f8856a.remove(0);
            }
        }
        return hashMap;
    }
}
