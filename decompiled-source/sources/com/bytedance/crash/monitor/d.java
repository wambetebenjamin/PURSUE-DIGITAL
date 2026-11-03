package com.bytedance.crash.monitor;

import android.text.TextUtils;
import com.bytedance.crash.CrashInfoCallback;
import com.bytedance.crash.CrashType;
import com.bytedance.crash.Global;
import com.bytedance.crash.IANRCallback;
import com.bytedance.crash.IAnrUploadCallback;
import com.bytedance.crash.ICrashCallback;
import com.bytedance.crash.ICrashFilter;
import com.bytedance.crash.IOOMCallback;
import com.bytedance.crash.IPreANRCallback;
import com.bytedance.crash.UserDataCenter;
import java.util.ArrayList;
import java.util.Iterator;
/* compiled from: CrashListener.java */
/* loaded from: classes2.dex */
public class d extends f {
    private static final ArrayList<d> o = new ArrayList<>();

    /* renamed from: a  reason: collision with root package name */
    protected final CacheManager f6895a;
    private c n;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(UserDataCenter userDataCenter) {
        super(userDataCenter);
        o.add(this);
        this.f6895a = new CacheManager(Global.e());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(UserDataCenter userDataCenter, final String str, final String str2, final long j2) {
        super(userDataCenter, str, str2, j2, 0L, 0L);
        o.add(this);
        CacheManager cacheManager = new CacheManager(Global.e(), str);
        this.f6895a = cacheManager;
        if (com.bytedance.crash.runtime.b.c()) {
            cacheManager.b(str);
            cacheManager.a(j2, 0L, 0L, str2);
            return;
        }
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.monitor.d.1
            @Override // java.lang.Runnable
            public void run() {
                d.this.f6895a.b(str);
                d.this.f6895a.a(j2, 0L, 0L, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(final String str, final String str2, final long j2) {
        super(str, str2, j2, 0L, 0L);
        o.add(this);
        CacheManager cacheManager = new CacheManager(Global.e(), str);
        this.f6895a = cacheManager;
        if (com.bytedance.crash.runtime.b.c()) {
            cacheManager.b(str);
            cacheManager.a(j2, 0L, 0L, str2);
            return;
        }
        com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.monitor.d.2
            @Override // java.lang.Runnable
            public void run() {
                d.this.f6895a.b(str);
                d.this.f6895a.a(j2, 0L, 0L, str2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ArrayList<d> e() {
        return o;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static d a(String str) {
        Iterator<d> it = o.iterator();
        while (it.hasNext()) {
            d next = it.next();
            if (str.equals(next.f())) {
                return next;
            }
        }
        return null;
    }

    @Override // com.bytedance.crash.monitor.f
    public String f() {
        return this.f6915e != null ? this.f6915e : this.f6895a.b();
    }

    @Override // com.bytedance.crash.monitor.f
    public String g() {
        return this.f6918h != null ? this.f6918h : this.f6895a.d();
    }

    @Override // com.bytedance.crash.monitor.f
    public String b(String str) {
        if (!TextUtils.isEmpty(this.f6917g)) {
            return this.f6917g;
        }
        String a2 = this.f6895a.a();
        return TextUtils.isEmpty(a2) ? str : a2;
    }

    @Override // com.bytedance.crash.monitor.f
    public long h() {
        return this.f6916f != 0 ? this.f6916f : this.f6895a.c();
    }

    @Override // com.bytedance.crash.monitor.f
    public c i() {
        c cVar = this.n;
        if (cVar != null) {
            return cVar;
        }
        if (this.f6919i != null && this.f6920j != 0) {
            this.n = new c(this.f6920j, this.l, this.k, this.f6919i);
        } else {
            this.n = this.f6895a.e();
        }
        return this.n;
    }

    @Override // com.bytedance.crash.monitor.f
    public long j() {
        return i().b();
    }

    @Override // com.bytedance.crash.monitor.f
    public long k() {
        return i().a();
    }

    @Override // com.bytedance.crash.monitor.f
    public long l() {
        return i().c();
    }

    @Override // com.bytedance.crash.monitor.f
    public String m() {
        return i().d();
    }

    public String b(long j2) {
        return this.f6895a.b(j2);
    }

    public String c(long j2) {
        return this.f6895a.e(j2);
    }

    public String d(long j2) {
        String a2 = this.f6895a.a(j2);
        return TextUtils.isEmpty(a2) ? "0" : a2;
    }

    public long e(long j2) {
        return this.f6895a.c(j2);
    }

    public c f(long j2) {
        return this.f6895a.f(j2);
    }

    @Override // com.bytedance.crash.monitor.f
    public void c(String str) {
        this.f6918h = str;
        this.f6895a.c(str);
    }

    @Override // com.bytedance.crash.monitor.f
    public void d(String str) {
        this.f6917g = str;
        this.f6895a.a(str);
    }

    @Override // com.bytedance.crash.monitor.f
    public void g(long j2) {
        this.f6916f = j2;
        this.f6895a.d(j2);
    }

    public void a(String str, String str2) {
        this.f6914d.c(str, str2);
    }

    public void a(int i2, String str) {
        this.f6914d.a(i2, str);
    }

    public void a(ICrashCallback iCrashCallback, CrashType crashType) {
        this.f6914d.a(iCrashCallback, crashType);
    }

    public void a(CrashInfoCallback crashInfoCallback, CrashType crashType) {
        this.f6914d.a(crashInfoCallback, crashType);
    }

    public void a(IOOMCallback iOOMCallback) {
        this.f6914d.a(iOOMCallback);
    }

    public void a(IANRCallback iANRCallback) {
        this.f6914d.a(iANRCallback);
    }

    public void b(IOOMCallback iOOMCallback) {
        this.f6914d.c(iOOMCallback);
    }

    public void c(IOOMCallback iOOMCallback) {
        this.f6914d.d(iOOMCallback);
    }

    public void b(ICrashCallback iCrashCallback, CrashType crashType) {
        this.f6914d.b(iCrashCallback, crashType);
    }

    public void a(IOOMCallback iOOMCallback, CrashType crashType) {
        this.f6914d.b(iOOMCallback);
    }

    public void a(ICrashFilter iCrashFilter) {
        this.f6914d.a(iCrashFilter);
    }

    public void a(IPreANRCallback iPreANRCallback) {
        this.f6914d.a(iPreANRCallback);
    }

    public void b(IPreANRCallback iPreANRCallback) {
        this.f6914d.b(iPreANRCallback);
    }

    public void a(IAnrUploadCallback iAnrUploadCallback) {
        this.f6914d.a(iAnrUploadCallback);
    }
}
