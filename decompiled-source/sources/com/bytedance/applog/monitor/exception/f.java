package com.bytedance.applog.monitor.exception;

import com.bytedance.applog.monitor.v3.m;
import com.facebook.internal.security.CertificateUtil;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/* compiled from: HighFrequencyTracker.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private final long f2856a;

    /* renamed from: b  reason: collision with root package name */
    private final long f2857b;

    /* renamed from: c  reason: collision with root package name */
    private final b f2858c;

    /* renamed from: d  reason: collision with root package name */
    private final LinkedList<a> f2859d = new LinkedList<>();

    /* renamed from: e  reason: collision with root package name */
    private final Map<String, Integer> f2860e = new HashMap();

    /* compiled from: HighFrequencyTracker.java */
    /* loaded from: classes.dex */
    public interface b {
        void a(Map<String, Integer> map);
    }

    public f(long j2, long j3, b bVar) {
        this.f2856a = j2;
        this.f2857b = j3;
        this.f2858c = bVar;
    }

    public synchronized void a(m mVar, String str, long j2) {
        this.f2859d.add(new a(mVar, str, j2));
        String str2 = mVar + CertificateUtil.DELIMITER + str;
        Integer num = this.f2860e.get(str2);
        if (num == null) {
            this.f2860e.put(str2, 1);
        } else {
            this.f2860e.put(str2, Integer.valueOf(num.intValue() + 1));
        }
        a(j2);
        if (this.f2859d.size() > this.f2857b) {
            b();
        }
    }

    private void a(long j2) {
        while (!this.f2859d.isEmpty() && this.f2859d.getFirst().f2863c < j2 - this.f2856a) {
            a removeFirst = this.f2859d.removeFirst();
            String str = removeFirst.f2861a + CertificateUtil.DELIMITER + removeFirst.f2862b;
            Integer num = this.f2860e.get(str);
            if (num != null) {
                if (num.intValue() > 1) {
                    this.f2860e.put(str, Integer.valueOf(num.intValue() - 1));
                } else {
                    this.f2860e.remove(str);
                }
            }
        }
    }

    public int a() {
        return this.f2859d.size();
    }

    private void b() {
        HashMap hashMap = new HashMap(this.f2860e);
        this.f2860e.clear();
        this.f2859d.clear();
        b bVar = this.f2858c;
        if (bVar != null) {
            bVar.a(hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: HighFrequencyTracker.java */
    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        m f2861a;

        /* renamed from: b  reason: collision with root package name */
        String f2862b;

        /* renamed from: c  reason: collision with root package name */
        long f2863c;

        public a(m mVar, String str, long j2) {
            this.f2861a = mVar;
            this.f2862b = str;
            this.f2863c = j2;
        }
    }
}
