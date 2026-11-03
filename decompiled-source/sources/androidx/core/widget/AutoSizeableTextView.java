package androidx.core.widget;

import android.os.Build;
/* loaded from: classes.dex */
public interface AutoSizeableTextView {
    @Deprecated
    public static final boolean PLATFORM_SUPPORTS_AUTOSIZE;

    int getAutoSizeMaxTextSize();

    int getAutoSizeMinTextSize();

    int getAutoSizeStepGranularity();

    int[] getAutoSizeTextAvailableSizes();

    int getAutoSizeTextType();

    void setAutoSizeTextTypeUniformWithConfiguration(int i2, int i3, int i4, int i5) throws IllegalArgumentException;

    void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i2) throws IllegalArgumentException;

    void setAutoSizeTextTypeWithDefaults(int i2);

    static {
        PLATFORM_SUPPORTS_AUTOSIZE = Build.VERSION.SDK_INT >= 27;
    }
}
