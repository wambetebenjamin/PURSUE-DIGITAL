package com.ss.android.agilelogger;

import android.content.Context;
import android.text.TextUtils;
/* compiled from: ALogConfig.java */
/* loaded from: classes9.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private Context f17980a;

    /* renamed from: b  reason: collision with root package name */
    private int f17981b;

    /* renamed from: c  reason: collision with root package name */
    private int f17982c;

    /* renamed from: d  reason: collision with root package name */
    private int f17983d;

    /* renamed from: e  reason: collision with root package name */
    private String f17984e;

    /* renamed from: f  reason: collision with root package name */
    private String f17985f;

    /* renamed from: g  reason: collision with root package name */
    private boolean f17986g;

    /* renamed from: h  reason: collision with root package name */
    private boolean f17987h;

    /* renamed from: i  reason: collision with root package name */
    private int f17988i;

    /* renamed from: j  reason: collision with root package name */
    private String f17989j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private boolean o;
    private int p;
    private float q;
    private boolean r;

    private b() {
    }

    public Context getContext() {
        return this.f17980a;
    }

    public void setContext(Context context) {
        this.f17980a = context;
    }

    public int getLogFileExpDays() {
        return this.f17981b;
    }

    public void setLogFileExpDays(int i2) {
        this.f17981b = i2;
    }

    public int getMaxDirSize() {
        return this.f17982c;
    }

    public void setMaxDirSize(int i2) {
        this.f17982c = i2;
    }

    public void a(float f2) {
        this.q = f2;
    }

    public float a() {
        return this.q;
    }

    public int getPerSize() {
        return this.f17983d;
    }

    public void setPerSize(int i2) {
        this.f17983d = i2;
    }

    public String getBufferDirPath() {
        return this.f17984e;
    }

    public void setBufferDirPath(String str) {
        this.f17984e = str;
    }

    public String getLogDirPath() {
        return this.f17985f;
    }

    public void setLogDirPath(String str) {
        this.f17985f = str;
    }

    public boolean isCompress() {
        return this.f17986g;
    }

    public void setCompress(boolean z) {
        this.f17986g = z;
    }

    public boolean isEncrypt() {
        return this.f17987h;
    }

    public void setEncrypt(boolean z) {
        this.f17987h = z;
    }

    public int getLevel() {
        return this.f17988i;
    }

    public void setLevel(int i2) {
        this.f17988i = i2;
    }

    public String getPubKey() {
        return this.f17989j;
    }

    public void setPubKey(String str) {
        this.f17989j = str;
    }

    public boolean isMainThreadSpeedUp() {
        return this.k;
    }

    public void setMainThreadSpeedUp(boolean z) {
        this.k = z;
    }

    public void setOffloadMainThreadWrite(boolean z) {
        this.l = z;
    }

    public boolean isOffloadMainThreadWrite() {
        return this.l;
    }

    public void a(boolean z) {
        this.m = z;
    }

    public boolean b() {
        return this.m;
    }

    public void b(boolean z) {
        this.o = z;
    }

    public boolean c() {
        return this.o;
    }

    public void c(boolean z) {
        this.n = z;
    }

    public void d(boolean z) {
        this.r = z;
    }

    public boolean d() {
        return this.r;
    }

    public boolean e() {
        return this.n;
    }

    public int f() {
        return this.p;
    }

    public void a(int i2) {
        this.p = i2;
    }

    /* compiled from: ALogConfig.java */
    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Context f17990a;

        /* renamed from: e  reason: collision with root package name */
        private String f17994e;

        /* renamed from: f  reason: collision with root package name */
        private String f17995f;

        /* renamed from: b  reason: collision with root package name */
        private int f17991b = 14;

        /* renamed from: c  reason: collision with root package name */
        private int f17992c = 20971520;
        private float m = 0.1f;

        /* renamed from: d  reason: collision with root package name */
        private int f17993d = 1048576;

        /* renamed from: g  reason: collision with root package name */
        private boolean f17996g = true;

        /* renamed from: h  reason: collision with root package name */
        private boolean f17997h = true;

        /* renamed from: i  reason: collision with root package name */
        private int f17998i = 3;

        /* renamed from: j  reason: collision with root package name */
        private String f17999j = "fecbb32b759120b672045f74edc41d159b6a426ffc863b9e0be9ad4be12824546f549959b838993a430344f15197221e87bd362298814c75f5068148b980306f";
        private boolean k = true;
        private boolean l = false;
        private boolean n = false;
        private boolean o = false;
        private boolean p = false;
        private int q = 256;
        private boolean r = false;

        public a(Context context) {
            if (context == null) {
                throw new RuntimeException("context must not be null");
            }
            Context applicationContext = context.getApplicationContext();
            this.f17990a = applicationContext != null ? applicationContext : context;
        }

        public a setLogFileExpDays(int i2) {
            this.f17991b = i2;
            return this;
        }

        public a setMaxDirSize(int i2) {
            this.f17992c = i2;
            return this;
        }

        public a a(float f2) {
            this.m = f2;
            return this;
        }

        public a setPerSize(int i2) {
            this.f17993d = i2;
            return this;
        }

        public a a(boolean z) {
            this.r = z;
            return this;
        }

        public a setBufferDirPath(String str) {
            this.f17994e = str;
            return this;
        }

        public a setLogDirPath(String str) {
            this.f17995f = str;
            return this;
        }

        public a setCompress(boolean z) {
            this.f17996g = z;
            return this;
        }

        public a setEncrypt(boolean z) {
            this.f17997h = z;
            return this;
        }

        public a setLevel(int i2) {
            this.f17998i = i2;
            return this;
        }

        public a setPubKey(String str) {
            this.f17999j = str;
            return this;
        }

        public a setMainThreadSpeedUp(boolean z) {
            this.k = z;
            return this;
        }

        public a setOffloadMainThreadWrite(boolean z) {
            this.l = z;
            return this;
        }

        public a b(boolean z) {
            this.n = z;
            return this;
        }

        public a c(boolean z) {
            this.p = z;
            return this;
        }

        public a d(boolean z) {
            this.o = z;
            return this;
        }

        public a a(int i2) {
            this.q = i2;
            return this;
        }

        public b build() {
            b bVar = new b();
            bVar.setContext(this.f17990a);
            bVar.setLogFileExpDays(this.f17991b);
            bVar.setMaxDirSize(this.f17992c);
            bVar.a(this.m);
            bVar.setPerSize(this.f17993d);
            bVar.setBufferDirPath(TextUtils.isEmpty(this.f17994e) ? com.ss.android.agilelogger.utils.a.getBufferDirPath(this.f17990a) : this.f17994e);
            bVar.setLogDirPath(TextUtils.isEmpty(this.f17995f) ? com.ss.android.agilelogger.utils.a.getDefaultLogDir(this.f17990a).getAbsolutePath() : this.f17995f);
            bVar.setCompress(this.f17996g);
            bVar.setEncrypt(this.f17997h);
            bVar.setLevel(this.f17998i);
            bVar.setPubKey(this.f17999j);
            bVar.setMainThreadSpeedUp(this.k);
            bVar.setOffloadMainThreadWrite(this.l);
            bVar.a(this.n);
            bVar.b(this.p);
            bVar.c(this.o);
            bVar.a(this.q);
            bVar.d(this.r);
            return bVar;
        }
    }
}
