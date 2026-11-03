package com.ttnet.org.chromium.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;
import com.bytedance.librarian.c;
import com.ttnet.org.chromium.base.compat.ApiHelperForO;
import com.ttnet.org.chromium.build.BuildConfig;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes9.dex */
public class BundleUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LOADED_SPLITS_KEY = "split_compat_loaded_splits";
    private static final String TAG = "BundleUtils";
    private static Boolean sIsBundle;
    private static SplitCompatClassLoader sSplitCompatClassLoaderInstance;
    private static ArrayList<String> sSplitsToRestore;
    private static final Object sSplitLock = new Object();
    private static final SimpleArrayMap<String, ClassLoader> sCachedClassLoaders = new SimpleArrayMap<>();
    private static final Map<String, ClassLoader> sInflationClassLoaders = Collections.synchronizedMap(new ArrayMap());

    public static Context createIsolatedSplitContext(Context context, String str) {
        return context;
    }

    public static String getNativeLibraryPath(String str, String str2) {
        return "";
    }

    public static boolean isBundleForNative() {
        return isBundle();
    }

    public static boolean isBundle() {
        if (BuildConfig.BUNDLES_SUPPORTED) {
            return sIsBundle.booleanValue();
        }
        return false;
    }

    public static void setIsBundle(boolean z) {
        sIsBundle = Boolean.valueOf(z);
    }

    public static boolean isolatedSplitsEnabled() {
        return BuildConfig.ISOLATED_SPLITS_ENABLED;
    }

    public static boolean isIsolatedSplitInstalled(Context context, String str) {
        String[] splitNames;
        return Build.VERSION.SDK_INT >= 26 && (splitNames = ApiHelperForO.getSplitNames(context.getApplicationInfo())) != null && Arrays.asList(splitNames).contains(str);
    }

    public static Object getSplitContextLock() {
        return sSplitLock;
    }

    public static void replaceClassLoader(Context context, ClassLoader classLoader) {
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        try {
            Field declaredField = context.getClass().getDeclaredField("mClassLoader");
            declaredField.setAccessible(true);
            declaredField.set(context, classLoader);
        } catch (ReflectiveOperationException e2) {
            throw new RuntimeException("Error setting ClassLoader.", e2);
        }
    }

    public static String getNativeLibraryPath(String str) {
        return getNativeLibraryPath(str, "");
    }

    public static void checkContextClassLoader(Context context, Activity activity) {
        ClassLoader classLoader = activity.getClass().getClassLoader();
        if (classLoader != context.getClassLoader()) {
            Log.w(TAG, "Mismatched ClassLoaders between Activity and context (fixing): %s", activity.getClass());
            replaceClassLoader(context, classLoader);
        }
    }

    public static Object newInstance(Context context, String str) {
        Context applicationContext = ContextUtils.getApplicationContext();
        if (applicationContext != null && canLoadClass(applicationContext.getClassLoader(), str)) {
            context = applicationContext;
        }
        try {
            return context.getClassLoader().loadClass(str).newInstance();
        } catch (ReflectiveOperationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static Context createContextForInflation(Context context, String str) {
        if (isIsolatedSplitInstalled(context, str)) {
            final ClassLoader registerSplitClassLoaderForInflation = registerSplitClassLoaderForInflation(str);
            return new ContextWrapper(context) { // from class: com.ttnet.org.chromium.base.BundleUtils.1
                @Override // android.content.ContextWrapper, android.content.Context
                public ClassLoader getClassLoader() {
                    return registerSplitClassLoaderForInflation;
                }

                @Override // android.content.ContextWrapper, android.content.Context
                public Object getSystemService(String str2) {
                    Object systemService = super.getSystemService(str2);
                    return "layout_inflater".equals(str2) ? ((LayoutInflater) systemService).cloneInContext(this) : systemService;
                }
            };
        }
        return context;
    }

    public static ClassLoader registerSplitClassLoaderForInflation(String str) {
        Map<String, ClassLoader> map = sInflationClassLoaders;
        ClassLoader classLoader = map.get(str);
        if (classLoader == null) {
            ClassLoader classLoader2 = createIsolatedSplitContext(ContextUtils.getApplicationContext(), str).getClassLoader();
            map.put(str, classLoader2);
            return classLoader2;
        }
        return classLoader;
    }

    public static boolean canLoadClass(ClassLoader classLoader, String str) {
        try {
            Class.forName(str, false, classLoader);
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public static ClassLoader getSplitCompatClassLoader() {
        if (sSplitCompatClassLoaderInstance == null) {
            sSplitCompatClassLoaderInstance = new SplitCompatClassLoader();
        }
        return sSplitCompatClassLoaderInstance;
    }

    public static void saveLoadedSplits(Bundle bundle) {
        bundle.putStringArrayList(LOADED_SPLITS_KEY, new ArrayList<>(sInflationClassLoaders.keySet()));
    }

    public static void restoreLoadedSplits(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        sSplitsToRestore = bundle.getStringArrayList(LOADED_SPLITS_KEY);
    }

    /* loaded from: classes9.dex */
    private static class SplitCompatClassLoader extends ClassLoader {
        public SplitCompatClassLoader() {
            super(ContextUtils.getApplicationContext().getClassLoader());
        }

        private Class<?> checkSplitsClassLoaders(String str) {
            for (ClassLoader classLoader : BundleUtils.sInflationClassLoaders.values()) {
                try {
                    return classLoader.loadClass(str);
                } catch (ClassNotFoundException unused) {
                }
            }
            return null;
        }

        @Override // java.lang.ClassLoader
        public Class<?> findClass(String str) {
            Class<?> checkSplitsClassLoaders = checkSplitsClassLoaders(str);
            if (checkSplitsClassLoaders != null) {
                return checkSplitsClassLoaders;
            }
            if (BundleUtils.sSplitsToRestore != null && !str.startsWith("android.")) {
                restoreSplitsClassLoaders();
                Class<?> checkSplitsClassLoaders2 = checkSplitsClassLoaders(str);
                if (checkSplitsClassLoaders2 != null) {
                    return checkSplitsClassLoaders2;
                }
            }
            throw new ClassNotFoundException(str);
        }

        private void restoreSplitsClassLoaders() {
            Iterator it = BundleUtils.sSplitsToRestore.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (!BundleUtils.sInflationClassLoaders.containsKey(str)) {
                    BundleUtils.registerSplitClassLoaderForInflation(str);
                }
            }
            ArrayList unused = BundleUtils.sSplitsToRestore = null;
        }
    }

    private static String getSplitApkLibraryPath(String str, String str2) {
        ApplicationInfo applicationInfo;
        String[] splitNames;
        int binarySearch;
        if (Build.VERSION.SDK_INT >= 26 && (splitNames = ApiHelperForO.getSplitNames((applicationInfo = ContextUtils.getApplicationContext().getApplicationInfo()))) != null && (binarySearch = Arrays.binarySearch(splitNames, str2)) >= 0) {
            try {
                return applicationInfo.splitSourceDirs[binarySearch] + "!/lib/" + ((String) applicationInfo.getClass().getField("primaryCpuAbi").get(applicationInfo)) + c.a.SEPARATOR + System.mapLibraryName(str);
            } catch (ReflectiveOperationException e2) {
                throw new RuntimeException(e2);
            }
        }
        return null;
    }

    private static boolean isApplicationContext(Context context) {
        while (context instanceof ContextWrapper) {
            if (context instanceof Application) {
                return true;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return false;
    }
}
