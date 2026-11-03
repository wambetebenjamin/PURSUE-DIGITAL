package com.google.common.base;

import javax.annotation.CheckForNull;
@ElementTypesAreNonnullByDefault
/* loaded from: classes6.dex */
public interface Function<F, T> {
    @ParametricNullness
    T apply(@ParametricNullness F f2);

    boolean equals(@CheckForNull Object obj);
}
