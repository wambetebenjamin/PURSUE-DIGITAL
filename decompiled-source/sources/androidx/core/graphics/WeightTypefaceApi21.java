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
final class WeightTypefaceApi21 {
    private static final String NATIVE_CREATE_FROM_TYPEFACE_METHOD = "nativeCreateFromTypeface";
    private static final String NATIVE_CREATE_WEIGHT_ALIAS_METHOD = "nativeCreateWeightAlias";
    private static final String NATIVE_INSTANCE_FIELD = "native_instance";
    private static final String TAG = "WeightTypeface";
    private static final Constructor<Typeface> sConstructor;
    private static final Method sNativeCreateFromTypeface;
    private static final Method sNativeCreateWeightAlias;
    private static final Field sNativeInstance;
    private static final Object sWeightCacheLock;
    private static final LongSparseArray<SparseArray<Typeface>> sWeightTypefaceCache;

    static {
        Method method;
        Method method2;
        Constructor<Typeface> constructor;
        Field field = null;
        try {
            Field declaredField = Typeface.class.getDeclaredField(NATIVE_INSTANCE_FIELD);
            method = Typeface.class.getDeclaredMethod(NATIVE_CREATE_FROM_TYPEFACE_METHOD, Long.TYPE, Integer.TYPE);
            method.setAccessible(true);
            method2 = Typeface.class.getDeclaredMethod(NATIVE_CREATE_WEIGHT_ALIAS_METHOD, Long.TYPE, Integer.TYPE);
            method2.setAccessible(true);
            constructor = Typeface.class.getDeclaredConstructor(Long.TYPE);
            constructor.setAccessible(true);
            field = declaredField;
        } catch (NoSuchFieldException | NoSuchMethodException e2) {
            Log.e(TAG, e2.getClass().getName(), e2);
            method = null;
            method2 = null;
            constructor = null;
        }
        sNativeInstance = field;
        sNativeCreateFromTypeface = method;
        sNativeCreateWeightAlias = method2;
        sConstructor = constructor;
        sWeightTypefaceCache = new LongSparseArray<>(3);
        sWeightCacheLock = new Object();
    }

    private static boolean isPrivateApiAvailable() {
        return sNativeInstance != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Typeface createWeightStyle(Typeface typeface, int i2, boolean z) {
        Typeface create;
        if (isPrivateApiAvailable()) {
            int i3 = (i2 << 1) | z;
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
                if (z == typeface.isItalic()) {
                    create = create(nativeCreateWeightAlias(nativeInstance, i2));
                } else {
                    create = create(nativeCreateFromTypefaceWithExactStyle(nativeInstance, i2, z));
                }
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
            return ((Long) sNativeCreateWeightAlias.invoke(null, Long.valueOf(((Long) sNativeCreateFromTypeface.invoke(null, Long.valueOf(j2), Integer.valueOf(z ? 2 : 0))).longValue()), Integer.valueOf(i2))).longValue();
        } catch (IllegalAccessException e2) {
            throw new RuntimeException(e2);
        } catch (InvocationTargetException e3) {
            throw new RuntimeException(e3);
        }
    }

    private static long nativeCreateWeightAlias(long j2, int i2) {
        try {
            return ((Long) sNativeCreateWeightAlias.invoke(null, Long.valueOf(j2), Integer.valueOf(i2))).longValue();
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

    private WeightTypefaceApi21() {
    }
}
