package com.bumptech.glide.load.engine.cache;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.bumptech.glide.util.Preconditions;
/* loaded from: classes.dex */
public final class MemorySizeCalculator {
    static final int BYTES_PER_ARGB_8888_PIXEL = 4;
    private static final int LOW_MEMORY_BYTE_ARRAY_POOL_DIVISOR = 2;
    private static final String TAG = "MemorySizeCalculator";
    private final int arrayPoolSize;
    private final int bitmapPoolSize;
    private final Context context;
    private final int memoryCacheSize;

    /* loaded from: classes.dex */
    interface ScreenDimensions {
        int getHeightPixels();

        int getWidthPixels();
    }

    MemorySizeCalculator(Builder builder) {
        int i2;
        this.context = builder.context;
        if (isLowMemoryDevice(builder.activityManager)) {
            i2 = builder.arrayPoolSizeBytes / 2;
        } else {
            i2 = builder.arrayPoolSizeBytes;
        }
        this.arrayPoolSize = i2;
        int maxSize = getMaxSize(builder.activityManager, builder.maxSizeMultiplier, builder.lowMemoryMaxSizeMultiplier);
        float widthPixels = builder.screenDimensions.getWidthPixels() * builder.screenDimensions.getHeightPixels() * 4;
        int round = Math.round(builder.bitmapPoolScreens * widthPixels);
        int round2 = Math.round(widthPixels * builder.memoryCacheScreens);
        int i3 = maxSize - i2;
        int i4 = round2 + round;
        if (i4 <= i3) {
            this.memoryCacheSize = round2;
            this.bitmapPoolSize = round;
        } else {
            float f2 = i3 / (builder.bitmapPoolScreens + builder.memoryCacheScreens);
            this.memoryCacheSize = Math.round(builder.memoryCacheScreens * f2);
            this.bitmapPoolSize = Math.round(f2 * builder.bitmapPoolScreens);
        }
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "Calculation complete, Calculated memory cache size: " + toMb(this.memoryCacheSize) + ", pool size: " + toMb(this.bitmapPoolSize) + ", byte array size: " + toMb(i2) + ", memory class limited? " + (i4 > maxSize) + ", max size: " + toMb(maxSize) + ", memoryClass: " + builder.activityManager.getMemoryClass() + ", isLowMemoryDevice: " + isLowMemoryDevice(builder.activityManager));
        }
    }

    public int getMemoryCacheSize() {
        return this.memoryCacheSize;
    }

    public int getBitmapPoolSize() {
        return this.bitmapPoolSize;
    }

    public int getArrayPoolSizeInBytes() {
        return this.arrayPoolSize;
    }

    private static int getMaxSize(ActivityManager activityManager, float f2, float f3) {
        boolean isLowMemoryDevice = isLowMemoryDevice(activityManager);
        float memoryClass = activityManager.getMemoryClass() * 1024 * 1024;
        if (isLowMemoryDevice) {
            f2 = f3;
        }
        return Math.round(memoryClass * f2);
    }

    private String toMb(int i2) {
        return Formatter.formatFileSize(this.context, i2);
    }

    static boolean isLowMemoryDevice(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        static final int ARRAY_POOL_SIZE_BYTES = 4194304;
        static final int BITMAP_POOL_TARGET_SCREENS;
        static final float LOW_MEMORY_MAX_SIZE_MULTIPLIER = 0.33f;
        static final float MAX_SIZE_MULTIPLIER = 0.4f;
        static final int MEMORY_CACHE_TARGET_SCREENS = 2;
        ActivityManager activityManager;
        float bitmapPoolScreens;
        final Context context;
        ScreenDimensions screenDimensions;
        float memoryCacheScreens = 2.0f;
        float maxSizeMultiplier = MAX_SIZE_MULTIPLIER;
        float lowMemoryMaxSizeMultiplier = LOW_MEMORY_MAX_SIZE_MULTIPLIER;
        int arrayPoolSizeBytes = 4194304;

        static {
            BITMAP_POOL_TARGET_SCREENS = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public Builder(Context context) {
            this.bitmapPoolScreens = BITMAP_POOL_TARGET_SCREENS;
            this.context = context;
            this.activityManager = (ActivityManager) context.getSystemService("activity");
            this.screenDimensions = new DisplayMetricsScreenDimensions(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !MemorySizeCalculator.isLowMemoryDevice(this.activityManager)) {
                return;
            }
            this.bitmapPoolScreens = 0.0f;
        }

        public Builder setMemoryCacheScreens(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.memoryCacheScreens = f2;
            return this;
        }

        public Builder setBitmapPoolScreens(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.bitmapPoolScreens = f2;
            return this;
        }

        public Builder setMaxSizeMultiplier(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Size multiplier must be between 0 and 1");
            this.maxSizeMultiplier = f2;
            return this;
        }

        public Builder setLowMemoryMaxSizeMultiplier(float f2) {
            Preconditions.checkArgument(f2 >= 0.0f && f2 <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.lowMemoryMaxSizeMultiplier = f2;
            return this;
        }

        public Builder setArrayPoolSize(int i2) {
            this.arrayPoolSizeBytes = i2;
            return this;
        }

        Builder setActivityManager(ActivityManager activityManager) {
            this.activityManager = activityManager;
            return this;
        }

        Builder setScreenDimensions(ScreenDimensions screenDimensions) {
            this.screenDimensions = screenDimensions;
            return this;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }
    }

    /* loaded from: classes.dex */
    private static final class DisplayMetricsScreenDimensions implements ScreenDimensions {
        private final DisplayMetrics displayMetrics;

        DisplayMetricsScreenDimensions(DisplayMetrics displayMetrics) {
            this.displayMetrics = displayMetrics;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getWidthPixels() {
            return this.displayMetrics.widthPixels;
        }

        @Override // com.bumptech.glide.load.engine.cache.MemorySizeCalculator.ScreenDimensions
        public int getHeightPixels() {
            return this.displayMetrics.heightPixels;
        }
    }
}
