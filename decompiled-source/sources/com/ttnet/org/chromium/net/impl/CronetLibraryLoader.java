package com.ttnet.org.chromium.net.impl;

import android.content.Context;
import android.os.ConditionVariable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.Log;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.net.NetworkChangeNotifier;
import java.io.File;
@JNINamespace("cronet")
/* loaded from: classes9.dex */
public class CronetLibraryLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String LIBRARY_NAME = "sscronet";
    private static final String TAG = "CronetLibraryLoader";
    private static volatile boolean sInitThreadInitDone;
    private static final Object sLoadLock = new Object();
    private static final HandlerThread sInitThread = new HandlerThread("CronetInit");
    private static volatile boolean sLibraryLoaded = false;
    private static final ConditionVariable sWaitForLibLoad = new ConditionVariable();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void createNetworkChangeNotifierOnInitThread();

        void cronetInitOnInitThread();

        String getCronetVersion();
    }

    public static void ensureInitialized(Context context, CronetEngineBuilderImpl cronetEngineBuilderImpl) {
        synchronized (sLoadLock) {
            if (!sInitThreadInitDone) {
                ContextUtils.initApplicationContext(context);
                HandlerThread handlerThread = sInitThread;
                if (!handlerThread.isAlive()) {
                    handlerThread.start();
                }
                postToInitThread(new Runnable() { // from class: com.ttnet.org.chromium.net.impl.CronetLibraryLoader.1
                    @Override // java.lang.Runnable
                    public void run() {
                        CronetLibraryLoader.ensureInitializedOnInitThread();
                    }
                });
            }
            if (!sLibraryLoaded) {
                TTCronetInitTimingInfo.sLoadCronetSoStart = System.nanoTime();
                loadCronetLibrary(cronetEngineBuilderImpl);
                TTCronetInitTimingInfo.sLoadCronetSoEnd = System.nanoTime();
                String cronetVersion = ImplVersion.getCronetVersion();
                if (cronetEngineBuilderImpl != null && cronetEngineBuilderImpl.optInit()) {
                    if (!cronetVersion.equals("107.0.5273.2")) {
                        throw new RuntimeException(String.format("Expected Cronet version number %s, actual version number %s.", cronetVersion, "107.0.5273.2"));
                    }
                } else if (!cronetVersion.equals(CronetLibraryLoaderJni.get().getCronetVersion())) {
                    throw new RuntimeException(String.format("Expected Cronet version number %s, actual version number %s.", cronetVersion, CronetLibraryLoaderJni.get().getCronetVersion()));
                } else {
                    Log.i(TAG, "Cronet version: %s, arch: %s", cronetVersion, System.getProperty("os.arch"));
                }
                sLibraryLoaded = true;
                sWaitForLibLoad.open();
            }
        }
    }

    private static void loadCronetLibrary(CronetEngineBuilderImpl cronetEngineBuilderImpl) {
        String cronetSoPath = cronetEngineBuilderImpl.getCronetSoPath();
        if (!TextUtils.isEmpty(cronetSoPath)) {
            File file = new File(cronetSoPath);
            if (file.exists() && !file.isDirectory()) {
                Log.i(TAG, "cronet so load: %s", cronetSoPath);
                System.load(cronetSoPath);
                return;
            }
        }
        if (cronetEngineBuilderImpl.libraryLoader() != null) {
            cronetEngineBuilderImpl.libraryLoader().loadLibrary(LIBRARY_NAME);
        } else {
            System.loadLibrary(LIBRARY_NAME);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void createNetworkChangeNotifierOnInitThread() {
        CronetLibraryLoaderJni.get().createNetworkChangeNotifierOnInitThread();
    }

    private static boolean onInitThread() {
        return sInitThread.getLooper() == Looper.myLooper();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void ensureInitializedOnInitThread() {
        if (sInitThreadInitDone) {
            return;
        }
        TTCronetInitTimingInfo.sInitThreadStart = System.nanoTime();
        NetworkChangeNotifier.init();
        NetworkChangeNotifier.registerToReceiveNotificationsAlways();
        sWaitForLibLoad.block();
        CronetLibraryLoaderJni.get().cronetInitOnInitThread();
        sInitThreadInitDone = true;
        TTCronetInitTimingInfo.sInitThreadEnd = System.nanoTime();
    }

    public static void postToInitThread(Runnable runnable) {
        if (onInitThread()) {
            runnable.run();
        } else {
            new Handler(sInitThread.getLooper()).post(runnable);
        }
    }

    private static String getDefaultUserAgent() {
        return UserAgent.from(ContextUtils.getApplicationContext());
    }

    private static void ensureInitializedFromNative() {
        synchronized (sLoadLock) {
            sLibraryLoaded = true;
            sWaitForLibLoad.open();
        }
        ensureInitialized(ContextUtils.getApplicationContext(), null);
    }

    private static void setNetworkThreadPriorityOnNetworkThread(int i2) {
        Process.setThreadPriority(i2);
    }
}
