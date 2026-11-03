package com.ss.android.push;

import java.lang.reflect.Field;
/* compiled from: Smith.java */
/* loaded from: classes9.dex */
public class d<T> {

    /* renamed from: a  reason: collision with root package name */
    private Object f18461a;

    /* renamed from: b  reason: collision with root package name */
    private String f18462b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f18463c;

    /* renamed from: d  reason: collision with root package name */
    private Field f18464d;

    public d(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException("obj cannot be null");
        }
        this.f18461a = obj;
        this.f18462b = str;
    }

    private void a() {
        if (this.f18463c) {
            return;
        }
        this.f18463c = true;
        Class<?> cls = this.f18461a.getClass();
        while (cls != null) {
            try {
                Field declaredField = cls.getDeclaredField(this.f18462b);
                declaredField.setAccessible(true);
                this.f18464d = declaredField;
                return;
            } catch (Exception unused) {
            } finally {
                cls.getSuperclass();
            }
        }
    }

    public T get() {
        a();
        Field field = this.f18464d;
        if (field == null) {
            throw new NoSuchFieldException();
        }
        try {
            return (T) field.get(this.f18461a);
        } catch (ClassCastException unused) {
            throw new IllegalArgumentException("unable to cast object");
        }
    }

    public void set(T t) {
        a();
        Field field = this.f18464d;
        if (field == null) {
            throw new NoSuchFieldException();
        }
        field.set(this.f18461a, t);
    }
}
