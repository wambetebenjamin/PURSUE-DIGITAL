package com.ss.alog.middleware;

import android.content.Intent;
import android.os.Bundle;
/* compiled from: IALogProtocol.java */
/* loaded from: classes9.dex */
public interface c {

    /* renamed from: c  reason: collision with root package name */
    public static final int f17874c = 2;

    /* renamed from: d  reason: collision with root package name */
    public static final int f17875d = 3;

    /* renamed from: e  reason: collision with root package name */
    public static final int f17876e = 4;

    /* renamed from: f  reason: collision with root package name */
    public static final int f17877f = 5;

    /* renamed from: g  reason: collision with root package name */
    public static final int f17878g = 6;

    void b(int i2, String str, Intent intent);

    void b(int i2, String str, Bundle bundle);

    void b(int i2, String str, Thread thread);

    void b(int i2, String str, Throwable th);

    void b(int i2, String str, StackTraceElement[] stackTraceElementArr);

    boolean b(int i2);

    void c(int i2);

    void c(int i2, String str, String str2);

    @Deprecated
    void c(String str);

    void c(String str, String str2, Throwable th);

    void c(String str, Throwable th);

    void d(int i2, String str, String str2);

    void d(String str, String str2, Throwable th);

    void d(String str, Throwable th);

    boolean d(String str);

    void f(String str, String str2);

    void g();

    void g(String str, String str2);

    void h();

    void h(String str, String str2);

    void i();

    void i(String str, String str2);

    void j();

    void j(String str, String str2);

    void k();
}
