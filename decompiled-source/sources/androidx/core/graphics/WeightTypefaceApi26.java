package androidx.core.graphics;

import android.graphics.Typeface;
import android.util.Log;
import android.util.SparseArray;
import androidx.collection.LongSparseArray;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/* loaded from: classes.dex */
final class WeightTypefaceApi26 {
    private static final String NATIVE_CREATE_FROM_TYPEFACE_WITH_EXACT_STYLE_METHOD = "nativeCreateFromTypefaceWithExactStyle";
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Constructor<Typeface> sConstructor;
    private static final Method sNativeCreateFromTypefaceWithExactStyle;
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray<SparseArray<Typeface>> sWeightTypefaceCache;

    static {
        Method method;
        Constructor<Typeface> constructor;
        Field field = null;
        try {
            Field declaredField = Typeface.class.getDeclaredField(NATIVE_INSTANCE_FIELD);
            method = Typeface.class.getDeclaredMethod(NATIVE_CREATE_FROM_TYPEFACE_WITH_EXACT_STYLE_METHOD, Long.TYPE, Integer.TYPE, Boolean.TYPE);
            method.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(Long.TYPE);
            constructor.setAccessible(true);
            field = declaredField;
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e(TAG, e2.getClass().getName(), e2);
            method = null;
            constructor = null;
        }
        sNativeInstance = field;
        sNativeCreateFromTypefaceWithExactStyle = method;
        sConstructor = constructor;
        sWeightTypefaceCache = new LongSparseArray<>(3);
        sWeightCacheLock = new Object();
    }

    private static boolean isPrivateApiAvailable() {
        return sNativeInstance != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface createWeightStyle(Typeface typeface, int i2, boolean z) {
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
                Typeface create = create(nativeCreateFromTypefaceWithExactStyle(nativeInstance, i2, z));
                sparseArray.put(i3, create);
                return create;
            }
        }
        return null;
    }

    private static long getNativeInstance(Typeface typeface) {
        try {
            return sNativeInstance.getLong(typeface);
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static long nativeCreateFromTypefaceWithExactStyle(long j2, int i2, boolean z) {
        try {
            return ((Long) sNativeCreateFromTypefaceWithExactStyle.invoke(null, Long.valueOf(j2), Integer.valueOf(i2), Boolean.valueOf(z))).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static Typeface create(long j2) {
        try {
            return sConstructor.newInstance(Long.valueOf(j2));
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
            return null;
        }
    }

    private WeightTypefaceApi26() {
    }
}
