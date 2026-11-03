package com.ttnet.org.chromium.base;

import com.ttnet.org.chromium.base.UnownedUserData;
import com.ttnet.org.chromium.build.BuildConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.WeakHashMap;
/* loaded from: classes9.dex */
public final class UnownedUserDataKey<T extends UnownedUserData> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Class<T> mClazz;
    private final Set<UnownedUserDataHost> mWeakHostAttachments = Collections.newSetFromMap(new WeakHashMap());

    public UnownedUserDataKey(Class<T> cls) {
        this.mClazz = cls;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Class<T> getValueClass() {
        return this.mClazz;
    }

    public final void attachToHost(UnownedUserDataHost unownedUserDataHost, T t) {
        Objects.requireNonNull(t);
        unownedUserDataHost.set(this, t);
        if (isAttachedToHost(unownedUserDataHost)) {
            return;
        }
        this.mWeakHostAttachments.add(unownedUserDataHost);
    }

    public final T retrieveDataFromHost(UnownedUserDataHost unownedUserDataHost) {
        assertNoDestroyedAttachments();
        for (UnownedUserDataHost unownedUserDataHost2 : this.mWeakHostAttachments) {
            if (unownedUserDataHost.equals(unownedUserDataHost2)) {
                return (T) unownedUserDataHost.get(this);
            }
        }
        return null;
    }

    public final void detachFromHost(UnownedUserDataHost unownedUserDataHost) {
        assertNoDestroyedAttachments();
        Iterator it = new ArrayList(this.mWeakHostAttachments).iterator();
        while (it.hasNext()) {
            UnownedUserDataHost unownedUserDataHost2 = (UnownedUserDataHost) it.next();
            if (unownedUserDataHost.equals(unownedUserDataHost2)) {
                removeHostAttachment(unownedUserDataHost2);
            }
        }
    }

    public final void detachFromAllHosts(T t) {
        assertNoDestroyedAttachments();
        Iterator it = new ArrayList(this.mWeakHostAttachments).iterator();
        while (it.hasNext()) {
            UnownedUserDataHost unownedUserDataHost = (UnownedUserDataHost) it.next();
            if (t.equals(unownedUserDataHost.get(this))) {
                removeHostAttachment(unownedUserDataHost);
            }
        }
    }

    public final boolean isAttachedToHost(UnownedUserDataHost unownedUserDataHost) {
        return retrieveDataFromHost(unownedUserDataHost) != null;
    }

    public final boolean isAttachedToAnyHost(T t) {
        return getHostAttachmentCount(t) > 0;
    }

    int getHostAttachmentCount(T t) {
        assertNoDestroyedAttachments();
        int i2 = 0;
        for (UnownedUserDataHost unownedUserDataHost : this.mWeakHostAttachments) {
            if (t.equals(unownedUserDataHost.get(this))) {
                i2++;
            }
        }
        return i2;
    }

    private void removeHostAttachment(UnownedUserDataHost unownedUserDataHost) {
        unownedUserDataHost.remove(this);
        this.mWeakHostAttachments.remove(unownedUserDataHost);
    }

    private void assertNoDestroyedAttachments() {
        if (BuildConfig.ENABLE_ASSERTS) {
            for (UnownedUserDataHost unownedUserDataHost : this.mWeakHostAttachments) {
                if (unownedUserDataHost.isDestroyed()) {
                    throw new IllegalStateException();
                }
            }
        }
    }
}
