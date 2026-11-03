package com.ttnet.org.chromium.base.supplier;
/* loaded from: classes9.dex */
public interface Supplier<T> {

    /* renamed from: com.ttnet.org.chromium.base.supplier.Supplier$1  reason: invalid class name */
    /* loaded from: classes9.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ boolean $assertionsDisabled = false;
    }

    T get();

    static {
        boolean z = AnonymousClass1.$assertionsDisabled;
    }

    default boolean hasValue() {
        T t = get();
        if (AnonymousClass1.$assertionsDisabled || t == get()) {
            return t != null;
        }
        throw new AssertionError("Value provided by #get() must not change.");
    }
}
