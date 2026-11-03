package com.bytedance.applog.log;

import java.util.List;
/* compiled from: LoggerImpl.java */
/* loaded from: classes.dex */
public class o extends b {

    /* renamed from: a  reason: collision with root package name */
    private static final a<h> f2794a = new a<h>() { // from class: com.bytedance.applog.log.o.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.applog.log.a
        /* renamed from: a */
        public h create(Object... objArr) {
            return new g();
        }
    };

    public static h a() {
        return f2794a.get(new Object[0]);
    }

    @Override // com.bytedance.applog.log.h
    public void b(String str, Object... objArr) {
        a((List<String>) null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(List<String> list, String str, Object... objArr) {
        a(0, list, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(int i2, String str, Object... objArr) {
        a(i2, (List<String>) null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(int i2, List<String> list, String str, Object... objArr) {
        a(i2, 0, list, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void c(String str, Object... objArr) {
        b((List<String>) null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void b(List<String> list, String str, Object... objArr) {
        b(0, list, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void b(int i2, String str, Object... objArr) {
        b(i2, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void b(int i2, List<String> list, String str, Object... objArr) {
        a(i2, 2, list, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void d(String str, Object... objArr) {
        c((List<String>) null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void c(List<String> list, String str, Object... objArr) {
        c(0, list, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void c(int i2, String str, Object... objArr) {
        c(i2, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void c(int i2, List<String> list, String str, Object... objArr) {
        a(i2, 1, list, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void e(String str, Object... objArr) {
        d((List<String>) null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void d(List<String> list, String str, Object... objArr) {
        d(0, list, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void d(int i2, String str, Object... objArr) {
        d(i2, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void d(int i2, List<String> list, String str, Object... objArr) {
        a(i2, 3, list, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(String str, Throwable th, Object... objArr) {
        a((List<String>) null, str, th, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(List<String> list, String str, Throwable th, Object... objArr) {
        a(0, list, str, th, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(int i2, String str, Throwable th, Object... objArr) {
        a(i2, null, str, th, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void a(int i2, List<String> list, String str, Throwable th, Object... objArr) {
        a(i2, 4, list, th, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void f(String str, Object... objArr) {
        a(str, (Throwable) null, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void e(List<String> list, String str, Object... objArr) {
        a(list, str, (Throwable) null, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void e(int i2, String str, Object... objArr) {
        e(i2, null, str, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void e(int i2, List<String> list, String str, Object... objArr) {
        a(i2, list, str, null, objArr);
    }

    @Override // com.bytedance.applog.log.h
    public void b(String str, Throwable th, Object... objArr) {
        a(0, 5, null, th, str, objArr);
    }
}
