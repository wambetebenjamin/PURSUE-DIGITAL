package com.ttnet.org.chromium.base;

import android.os.Process;
import java.util.HashMap;
/* loaded from: classes9.dex */
public final class UserDataHost {
    private final long mThreadId = Process.myTid();
    private HashMap<Class<? extends UserData>, UserData> mUserDataMap = new HashMap<>();

    private static void checkArgument(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("Neither key nor object of UserDataHost can be null.");
        }
    }

    private void checkThreadAndState() {
        if (this.mThreadId != Process.myTid()) {
            throw new IllegalStateException("UserData must only be used on a single thread.");
        }
        if (this.mUserDataMap == null) {
            throw new IllegalStateException("Operation is not allowed after destroy().");
        }
    }

    public <T extends UserData> T setUserData(Class<T> cls, T t) {
        checkThreadAndState();
        checkArgument((cls == null || t == null) ? false : true);
        this.mUserDataMap.put(cls, t);
        return (T) getUserData(cls);
    }

    public <T extends UserData> T getUserData(Class<T> cls) {
        checkThreadAndState();
        checkArgument(cls != null);
        return cls.cast(this.mUserDataMap.get(cls));
    }

    public <T extends UserData> T removeUserData(Class<T> cls) {
        checkThreadAndState();
        checkArgument(cls != null);
        if (!this.mUserDataMap.containsKey(cls)) {
            throw new IllegalStateException("UserData for the key is not present.");
        }
        return cls.cast(this.mUserDataMap.remove(cls));
    }

    public void destroy() {
        checkThreadAndState();
        HashMap<Class<? extends UserData>, UserData> hashMap = this.mUserDataMap;
        this.mUserDataMap = null;
        for (UserData userData : hashMap.values()) {
            userData.destroy();
        }
    }
}
