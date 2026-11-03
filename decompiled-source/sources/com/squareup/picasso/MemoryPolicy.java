package com.squareup.picasso;
/* loaded from: classes9.dex */
public enum MemoryPolicy {
    NO_CACHE(1),
    NO_STORE(2);
    
    final int index;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldReadFromMemoryCache(int i2) {
        return (i2 & NO_CACHE.index) == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean shouldWriteToMemoryCache(int i2) {
        return (i2 & NO_STORE.index) == 0;
    }

    MemoryPolicy(int i2) {
        this.index = i2;
    }
}
