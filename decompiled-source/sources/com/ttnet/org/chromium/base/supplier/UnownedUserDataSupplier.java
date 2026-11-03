package com.ttnet.org.chromium.base.supplier;

import com.ttnet.org.chromium.base.UnownedUserData;
import com.ttnet.org.chromium.base.UnownedUserDataHost;
import com.ttnet.org.chromium.base.UnownedUserDataKey;
import com.ttnet.org.chromium.base.lifetime.DestroyChecker;
import com.ttnet.org.chromium.base.lifetime.Destroyable;
/* loaded from: classes9.dex */
public abstract class UnownedUserDataSupplier<E> extends ObservableSupplierImpl<E> implements UnownedUserData, Destroyable {
    private final DestroyChecker mDestroyChecker = new DestroyChecker();
    private final UnownedUserDataKey<UnownedUserDataSupplier<E>> mUudKey;

    /* JADX WARN: Multi-variable type inference failed */
    protected UnownedUserDataSupplier(UnownedUserDataKey<? extends UnownedUserDataSupplier<E>> unownedUserDataKey) {
        this.mUudKey = unownedUserDataKey;
    }

    public void attach(UnownedUserDataHost unownedUserDataHost) {
        this.mDestroyChecker.checkNotDestroyed();
        this.mUudKey.attachToHost(unownedUserDataHost, this);
    }

    @Override // com.ttnet.org.chromium.base.lifetime.Destroyable
    public void destroy() {
        this.mDestroyChecker.destroy();
        this.mUudKey.detachFromAllHosts(this);
    }
}
