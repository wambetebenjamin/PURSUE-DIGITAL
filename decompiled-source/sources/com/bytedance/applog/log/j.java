package com.bytedance.applog.log;

import com.meituan.robust.Constants;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/* compiled from: LogInfo.java */
/* loaded from: classes.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final ThreadLocal<SimpleDateFormat> f2745a = new ThreadLocal<SimpleDateFormat>() { // from class: com.bytedance.applog.log.j.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        /* renamed from: a */
        public SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private String f2746b;

    /* renamed from: c  reason: collision with root package name */
    private String f2747c;

    /* renamed from: f  reason: collision with root package name */
    private List<String> f2750f;

    /* renamed from: g  reason: collision with root package name */
    private String f2751g;

    /* renamed from: i  reason: collision with root package name */
    private Throwable f2753i;

    /* renamed from: d  reason: collision with root package name */
    private int f2748d = 1;

    /* renamed from: e  reason: collision with root package name */
    private int f2749e = 0;

    /* renamed from: h  reason: collision with root package name */
    private long f2752h = System.currentTimeMillis();

    /* compiled from: LogInfo.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public static final int f2754a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f2755b = 1;

        /* renamed from: c  reason: collision with root package name */
        public static final int f2756c = 2;

        /* renamed from: d  reason: collision with root package name */
        public static final int f2757d = 3;

        /* renamed from: e  reason: collision with root package name */
        public static final int f2758e = 4;

        /* renamed from: f  reason: collision with root package name */
        public static final int f2759f = 5;

        /* renamed from: g  reason: collision with root package name */
        public static final int f2760g = 6;

        /* renamed from: h  reason: collision with root package name */
        public static final int f2761h = 7;

        /* renamed from: i  reason: collision with root package name */
        public static final int f2762i = 8;

        /* renamed from: j  reason: collision with root package name */
        public static final int f2763j = 9;
        public static final int k = 10;
        public static final int l = 11;
        public static final int m = 12;
        public static final int n = 13;
        public static final int o = 14;
        public static final int p = 15;
    }

    /* compiled from: LogInfo.java */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        public static final int f2764a = 0;

        /* renamed from: b  reason: collision with root package name */
        public static final int f2765b = 1;

        /* renamed from: c  reason: collision with root package name */
        public static final int f2766c = 2;

        /* renamed from: d  reason: collision with root package name */
        public static final int f2767d = 3;

        /* renamed from: e  reason: collision with root package name */
        public static final int f2768e = 4;

        /* renamed from: f  reason: collision with root package name */
        public static final int f2769f = 5;
    }

    public static k a() {
        return new k();
    }

    public long b() {
        return this.f2752h;
    }

    public void a(long j2) {
        this.f2752h = j2;
    }

    public String c() {
        return this.f2746b;
    }

    public void a(String str) {
        this.f2746b = str;
    }

    public String d() {
        return this.f2747c;
    }

    public void b(String str) {
        this.f2747c = str;
    }

    public int e() {
        return this.f2748d;
    }

    public void a(int i2) {
        this.f2748d = i2;
    }

    public int f() {
        return this.f2749e;
    }

    public void b(int i2) {
        this.f2749e = i2;
    }

    public List<String> g() {
        return this.f2750f;
    }

    public void a(List<String> list) {
        this.f2750f = list;
    }

    public String h() {
        return this.f2751g;
    }

    public void c(String str) {
        this.f2751g = str;
    }

    public Throwable i() {
        return this.f2753i;
    }

    public void a(Throwable th) {
        this.f2753i = th;
    }

    public String j() {
        int i2 = this.f2748d;
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 4 ? i2 != 5 ? "" : "ASSERT" : "ERROR" : "WARN" : "INFO" : "DEBUG" : "VERBOSE";
    }

    public String k() {
        switch (this.f2749e) {
            case 1:
                return "DEVICE_REGISTER";
            case 2:
                return "ABTEST";
            case 3:
                return "ALINK";
            case 4:
                return "EVENT";
            case 5:
                return "DATABASE";
            case 6:
                return "EVENT_VERIFY";
            case 7:
                return "VIEW_EXPOSURE";
            case 8:
                return "MONITOR";
            case 9:
                return "USER_PROFILE";
            case 10:
                return "PICKER";
            case 11:
                return "REQUEST";
            case 12:
                return "EVENT_SAMPLING";
            case 13:
                return "EVENT_PRIORITY";
            case 14:
                return "COMPRESS";
            case 15:
                return "ONE_ID";
            default:
                return "DEFAULT";
        }
    }

    public String l() {
        List<String> list = this.f2750f;
        if (list == null || list.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.f2750f.size(); i2++) {
            sb.append(this.f2750f.get(i2));
            if (i2 < this.f2750f.size() - 1) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public String m() {
        return this.f2752h > 0 ? f2745a.get().format(new Date(this.f2752h)) : "--";
    }

    public String toString() {
        String str = Constants.ARRAY_TYPE + m() + "][" + j() + "][" + a((Object) this.f2746b) + "][" + a((Object) this.f2747c) + "][" + k() + "][" + l() + "] " + a((Object) this.f2751g);
        return i() != null ? str + "\nstacktrace: " + b(i()) : str;
    }

    public String n() {
        return Constants.ARRAY_TYPE + j() + "][" + a((Object) this.f2746b) + "] " + a((Object) this.f2751g);
    }

    public String o() {
        return a((Object) this.f2751g);
    }

    private String a(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    private String b(Throwable th) {
        StringBuilder sb = new StringBuilder();
        while (th != null) {
            a(sb, th);
            th = th.getCause();
        }
        return sb.toString();
    }

    private void a(StringBuilder sb, Throwable th) {
        sb.append(th.toString());
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            sb.append("\n\tat ").append(stackTraceElement);
        }
    }
}
