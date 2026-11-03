package com.ss.android.ugc.aweme.framework.services;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.Objects;
/* compiled from: Defaults.java */
/* loaded from: classes9.dex */
public final class d {

    /* renamed from: a  reason: collision with root package name */
    private static final Double f18642a = Double.valueOf((double) FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE);

    /* renamed from: b  reason: collision with root package name */
    private static final Float f18643b = Float.valueOf(0.0f);

    private d() {
    }

    public static <T> T a(Class<T> cls) {
        a(cls);
        if (cls == Boolean.TYPE) {
            return (T) Boolean.FALSE;
        }
        if (cls == Character.TYPE) {
            return (T) (char) 0;
        }
        if (cls == Byte.TYPE) {
            return (T) (byte) 0;
        }
        if (cls == Short.TYPE) {
            return (T) (short) 0;
        }
        if (cls == Integer.TYPE) {
            return (T) 0;
        }
        if (cls == Long.TYPE) {
            return (T) 0L;
        }
        if (cls == Float.TYPE) {
            return (T) f18643b;
        }
        if (cls == Double.TYPE) {
            return (T) f18642a;
        }
        return null;
    }

    public static <T> T a(T t) {
        Objects.requireNonNull(t);
        return t;
    }
}
