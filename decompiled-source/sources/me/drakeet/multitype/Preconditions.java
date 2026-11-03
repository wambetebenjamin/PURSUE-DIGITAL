package me.drakeet.multitype;

import java.util.Objects;
/* loaded from: classes10.dex */
public final class Preconditions {
    public static <T> T checkNotNull(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    private Preconditions() {
    }
}
