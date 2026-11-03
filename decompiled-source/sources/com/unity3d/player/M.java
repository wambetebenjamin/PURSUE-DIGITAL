package com.unity3d.player;

import java.lang.reflect.Member;
/* loaded from: classes10.dex */
final class M {

    /* renamed from: a  reason: collision with root package name */
    private final Class f18848a;

    /* renamed from: b  reason: collision with root package name */
    private final String f18849b;

    /* renamed from: c  reason: collision with root package name */
    private final String f18850c;

    /* renamed from: d  reason: collision with root package name */
    private final int f18851d;

    /* renamed from: e  reason: collision with root package name */
    public volatile Member f18852e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public M(Class cls, String str, String str2) {
        this.f18848a = cls;
        this.f18849b = str;
        this.f18850c = str2;
        int hashCode = str.hashCode();
        this.f18851d = str2.hashCode() + ((hashCode + ((cls.hashCode() + 527) * 31)) * 31);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof M) {
            M m = (M) obj;
            return this.f18851d == m.f18851d && this.f18850c.equals(m.f18850c) && this.f18849b.equals(m.f18849b) && this.f18848a.equals(m.f18848a);
        }
        return false;
    }

    public final int hashCode() {
        return this.f18851d;
    }
}
