package com.bytedance.applog.log;

import java.util.List;
/* compiled from: IAppLogLogger.java */
/* loaded from: classes.dex */
public interface h {
    void a(int i2, String str, Throwable th, Object... objArr);

    void a(int i2, String str, Object... objArr);

    void a(int i2, List<String> list, String str, Throwable th, Object... objArr);

    void a(int i2, List<String> list, String str, Object... objArr);

    void a(String str);

    void a(String str, Throwable th, Object... objArr);

    void a(List<String> list, String str, Throwable th, Object... objArr);

    void a(List<String> list, String str, Object... objArr);

    void b(int i2, String str, Object... objArr);

    void b(int i2, List<String> list, String str, Object... objArr);

    void b(String str, Throwable th, Object... objArr);

    void b(String str, Object... objArr);

    void b(List<String> list, String str, Object... objArr);

    void c(int i2, String str, Object... objArr);

    void c(int i2, List<String> list, String str, Object... objArr);

    void c(String str, Object... objArr);

    void c(List<String> list, String str, Object... objArr);

    void d(int i2, String str, Object... objArr);

    void d(int i2, List<String> list, String str, Object... objArr);

    void d(String str, Object... objArr);

    void d(List<String> list, String str, Object... objArr);

    void e(int i2, String str, Object... objArr);

    void e(int i2, List<String> list, String str, Object... objArr);

    void e(String str, Object... objArr);

    void e(List<String> list, String str, Object... objArr);

    void f(String str, Object... objArr);
}
