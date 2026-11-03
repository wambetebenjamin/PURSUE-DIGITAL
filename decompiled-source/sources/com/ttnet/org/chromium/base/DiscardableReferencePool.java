package com.ttnet.org.chromium.base;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes9.dex */
public class DiscardableReferencePool {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Set<DiscardableReference<?>> mPool = Collections.newSetFromMap(new WeakHashMap());

    /* loaded from: classes9.dex */
    public static class DiscardableReference<T> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private T mPayload;

        private DiscardableReference(T t) {
            this.mPayload = t;
        }

        public T get() {
            return this.mPayload;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void discard() {
            this.mPayload = null;
        }
    }

    public <T> DiscardableReference<T> put(T t) {
        DiscardableReference<T> discardableReference = new DiscardableReference<>(t);
        this.mPool.add(discardableReference);
        return discardableReference;
    }

    public void remove(DiscardableReference<?> discardableReference) {
        if (this.mPool.contains(discardableReference)) {
            discardableReference.discard();
            this.mPool.remove(discardableReference);
        }
    }

    public void drain() {
        for (DiscardableReference<?> discardableReference : this.mPool) {
            discardableReference.discard();
        }
        this.mPool.clear();
    }
}
