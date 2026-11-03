package com.bumptech.glide.util;

import com.bumptech.glide.ListPreloader;
/* loaded from: classes.dex */
public class FixedPreloadSizeProvider<T> implements ListPreloader.PreloadSizeProvider<T> {
    private final int[] size;

    public FixedPreloadSizeProvider(int i2, int i3) {
        this.size = new int[]{i2, i3};
    }

    @Override // com.bumptech.glide.ListPreloader.PreloadSizeProvider
    public int[] getPreloadSize(T t, int i2, int i3) {
        return this.size;
    }
}
