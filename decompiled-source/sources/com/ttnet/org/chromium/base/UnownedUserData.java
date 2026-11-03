package com.ttnet.org.chromium.base;
/* loaded from: classes9.dex */
public interface UnownedUserData {
    default boolean informOnDetachmentFromHost() {
        return true;
    }

    default void onDetachedFromHost(UnownedUserDataHost unownedUserDataHost) {
    }
}
