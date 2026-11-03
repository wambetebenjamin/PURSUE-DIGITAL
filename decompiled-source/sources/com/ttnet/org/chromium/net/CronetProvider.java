package com.ttnet.org.chromium.net;

import android.content.Context;
import android.util.Log;
import com.ttnet.org.chromium.net.CronetEngine;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/* loaded from: classes9.dex */
public abstract class CronetProvider {
    private static final String GMS_CORE_CRONET_PROVIDER_CLASS = "com.google.android.gms.net.GmsCoreCronetProvider";
    private static final String JAVA_CRONET_PROVIDER_CLASS = "com.ttnet.org.chromium.net.impl.JavaCronetProvider";
    private static final String NATIVE_CRONET_PROVIDER_CLASS = "com.ttnet.org.chromium.net.impl.NativeCronetProvider";
    private static final String PLAY_SERVICES_CRONET_PROVIDER_CLASS = "com.google.android.gms.net.PlayServicesCronetProvider";
    public static final String PROVIDER_NAME_APP_PACKAGED = "App-Packaged-Cronet-Provider";
    public static final String PROVIDER_NAME_FALLBACK = "Fallback-Cronet-Provider";
    private static final String RES_KEY_CRONET_IMPL_CLASS = "CronetProviderClassName";
    private static final String TAG = "CronetProvider";
    protected final Context mContext;

    public abstract CronetEngine.Builder createBuilder(boolean z);

    public abstract String getName();

    public abstract String getVersion();

    public abstract boolean isEnabled();

    /* JADX INFO: Access modifiers changed from: protected */
    public CronetProvider(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null");
        }
        this.mContext = context;
    }

    public String toString() {
        return "[class=" + getClass().getName() + ", name=" + getName() + ", version=" + getVersion() + ", enabled=" + isEnabled() + "]";
    }

    public static List<CronetProvider> getAllProviders(Context context) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        addCronetProviderImplByClassName(context, NATIVE_CRONET_PROVIDER_CLASS, linkedHashSet, false);
        return Collections.unmodifiableList(new ArrayList(linkedHashSet));
    }

    private static boolean addCronetProviderImplByClassName(Context context, String str, Set<CronetProvider> set, boolean z) {
        try {
            set.add((CronetProvider) context.getClassLoader().loadClass(str).asSubclass(CronetProvider.class).getConstructor(Context.class).newInstance(context));
            return true;
        } catch (ClassNotFoundException e2) {
            logReflectiveOperationException(str, z, e2);
            return false;
        } catch (IllegalAccessException e3) {
            logReflectiveOperationException(str, z, e3);
            return false;
        } catch (InstantiationException e4) {
            logReflectiveOperationException(str, z, e4);
            return false;
        } catch (NoSuchMethodException e5) {
            logReflectiveOperationException(str, z, e5);
            return false;
        } catch (InvocationTargetException e6) {
            logReflectiveOperationException(str, z, e6);
            return false;
        }
    }

    private static void logReflectiveOperationException(String str, boolean z, Exception exc) {
        if (z) {
            Log.e(TAG, "Unable to load provider class: " + str, exc);
            return;
        }
        String str2 = TAG;
        if (Log.isLoggable(str2, 3)) {
            Log.d(str2, "Tried to load " + str + " provider class but it wasn't included in the app classpath");
        }
    }

    private static boolean addCronetProviderFromResourceFile(Context context, Set<CronetProvider> set) {
        int identifier = context.getResources().getIdentifier(RES_KEY_CRONET_IMPL_CLASS, "string", context.getPackageName());
        boolean z = false;
        if (identifier == 0) {
            return false;
        }
        String string = context.getResources().getString(identifier);
        if (string != null && !string.equals(PLAY_SERVICES_CRONET_PROVIDER_CLASS) && !string.equals(GMS_CORE_CRONET_PROVIDER_CLASS) && !string.equals(JAVA_CRONET_PROVIDER_CLASS) && !string.equals(NATIVE_CRONET_PROVIDER_CLASS)) {
            z = true;
            if (!addCronetProviderImplByClassName(context, string, set, true)) {
                Log.e(TAG, "Unable to instantiate Cronet implementation class " + string + " that is listed as in the app string resource file under " + RES_KEY_CRONET_IMPL_CLASS + " key");
            }
        }
        return z;
    }
}
