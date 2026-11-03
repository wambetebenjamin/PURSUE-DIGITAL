package com.bytedance.frameworks.baselib.network.http.io;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/* compiled from: SafeObjectInputStream.java */
/* loaded from: classes2.dex */
public class a extends ObjectInputStream {

    /* renamed from: a  reason: collision with root package name */
    private final Set<String> f7922a;

    public a(InputStream inputStream, Collection<String> collection) {
        super(inputStream);
        HashSet hashSet = new HashSet();
        this.f7922a = hashSet;
        if (collection != null) {
            hashSet.addAll(collection);
        }
    }

    public a(InputStream inputStream, String... strArr) {
        super(inputStream);
        HashSet hashSet = new HashSet();
        this.f7922a = hashSet;
        if (strArr != null) {
            hashSet.addAll(Arrays.asList(strArr));
        }
    }

    public a(InputStream inputStream, Class<?>... clsArr) {
        super(inputStream);
        this.f7922a = new HashSet();
        if (clsArr != null) {
            for (Class<?> cls : clsArr) {
                this.f7922a.add(cls.getName());
            }
        }
    }

    @Override // java.io.ObjectInputStream
    protected Class<?> resolveClass(ObjectStreamClass objectStreamClass) {
        if (objectStreamClass == null || objectStreamClass.getName() == null) {
            return super.resolveClass(objectStreamClass);
        }
        String name = objectStreamClass.getName();
        if (this.f7922a.contains(name)) {
            return super.resolveClass(objectStreamClass);
        }
        return Class.forName(name, true, ClassLoader.getSystemClassLoader().getParent());
    }
}
