package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import androidx.core.content.res.FontResourcesParserCompat;
import java.lang.reflect.Field;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class WeightTypefaceApi14 {
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray<SparseArray<Typeface>> sWeightTypefaceCache;

    static {
        Field field;
        try {
            field = Typeface.class.getDeclaredField(NATIVE_INSTANCE_FIELD);
            field.setAccessible(true);
        } catch (Exception e2) {
            Log.e(TAG, e2.getClass().getName(), e2);
            field = null;
        }
        sNativeInstance = field;
        sWeightTypefaceCache = new LongSparseArray<>(3);
        sWeightCacheLock = new Object();
    }

    private static boolean isPrivateApiAvailable() {
        return sNativeInstance != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface createWeightStyle(TypefaceCompatBaseImpl typefaceCompatBaseImpl, Context context, Typeface typeface, int i2, boolean z) {
        if (isPrivateApiAvailable()) {
            int i3 = (i2 << 1) | (z ? 1 : 0);
            synchronized (sWeightCacheLock) {
                long nativeInstance = getNativeInstance(typeface);
                LongSparseArray<SparseArray<Typeface>> longSparseArray = sWeightTypefaceCache;
                SparseArray<Typeface> sparseArray = longSparseArray.get(nativeInstance);
                if (sparseArray == null) {
                    sparseArray = new SparseArray<>(4);
                    longSparseArray.put(nativeInstance, sparseArray);
                } else {
                    Typeface typeface2 = sparseArray.get(i3);
                    if (typeface2 != null) {
                        return typeface2;
                    }
                }
                Typeface bestFontFromFamily = getBestFontFromFamily(typefaceCompatBaseImpl, context, typeface, i2, z);
                if (bestFontFromFamily == null) {
                    bestFontFromFamily = platformTypefaceCreate(typeface, i2, z);
                }
                sparseArray.put(i3, bestFontFromFamily);
                return bestFontFromFamily;
            }
        }
        return null;
    }

    private static Typeface platformTypefaceCreate(Typeface typeface, int i2, boolean z) {
        int i3 = 1;
        boolean z2 = i2 >= 600;
        if (!z2 && !z) {
            i3 = 0;
        } else if (!z2) {
            i3 = 2;
        } else if (z) {
            i3 = 3;
        }
        return Typeface.create(typeface, i3);
    }

    private static Typeface getBestFontFromFamily(TypefaceCompatBaseImpl typefaceCompatBaseImpl, Context context, Typeface typeface, int i2, boolean z) {
        FontResourcesParserCompat.FontFamilyFilesResourceEntry fontFamily = typefaceCompatBaseImpl.getFontFamily(typeface);
        if (fontFamily == null) {
            return null;
        }
        return typefaceCompatBaseImpl.createFromFontFamilyFilesResourceEntry(context, fontFamily, context.getResources(), i2, z);
    }

    private static long getNativeInstance(Typeface typeface) {
        try {
            return ((Number) sNativeInstance.get(typeface)).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    private WeightTypefaceApi14() {
    }
}
