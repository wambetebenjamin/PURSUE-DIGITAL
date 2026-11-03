package com.ttnet.org.chromium.base.library_loader;

import com.ttnet.org.chromium.base.CommandLine;
import com.ttnet.org.chromium.base.ContextUtils;
import com.ttnet.org.chromium.base.SysUtils;
import com.ttnet.org.chromium.base.TraceEvent;
import com.ttnet.org.chromium.base.annotations.JNINamespace;
import com.ttnet.org.chromium.base.annotations.MainDex;
import com.ttnet.org.chromium.base.metrics.RecordHistogram;
import com.ttnet.org.chromium.base.task.PostTask;
import com.ttnet.org.chromium.base.task.TaskTraits;
import java.util.concurrent.atomic.AtomicBoolean;
@JNINamespace("base::android")
@MainDex
/* loaded from: classes9.dex */
public class LibraryPrefetcher {
    private static final String TAG = "LibraryPrefetcher";
    private static final AtomicBoolean sPrefetchLibraryHasBeenCalled = new AtomicBoolean();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public interface Natives {
        void forkAndPrefetchNativeLibrary();

        int percentageOfResidentNativeLibraryCode();

        void periodicallyCollectResidency();
    }

    public static void asyncPrefetchLibrariesToMemory() {
        SysUtils.logPageFaultCountToTracing();
        final boolean compareAndSet = sPrefetchLibraryHasBeenCalled.compareAndSet(false, true);
        if (compareAndSet && CommandLine.getInstance().hasSwitch("log-native-library-residency")) {
            new Thread(new Runnable() { // from class: com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    LibraryPrefetcherJni.get().periodicallyCollectResidency();
                }
            }).start();
        } else {
            PostTask.postTask(TaskTraits.USER_BLOCKING, new Runnable() { // from class: com.ttnet.org.chromium.base.library_loader.LibraryPrefetcher$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    LibraryPrefetcher.lambda$asyncPrefetchLibrariesToMemory$1(compareAndSet);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$asyncPrefetchLibrariesToMemory$1(boolean z) {
        int percentageOfResidentNativeLibraryCode = LibraryPrefetcherJni.get().percentageOfResidentNativeLibraryCode();
        TraceEvent scoped = TraceEvent.scoped("LibraryPrefetcher.asyncPrefetchLibrariesToMemory", Integer.toString(percentageOfResidentNativeLibraryCode));
        if (z && percentageOfResidentNativeLibraryCode < 90) {
            try {
                LibraryPrefetcherJni.get().forkAndPrefetchNativeLibrary();
            } catch (Throwable th) {
                if (scoped != null) {
                    try {
                        scoped.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
        if (percentageOfResidentNativeLibraryCode != -1) {
            RecordHistogram.recordPercentageHistogram("LibraryLoader.PercentageOfResidentCodeBeforePrefetch" + (z ? ".ColdStartup" : ".WarmStartup"), percentageOfResidentNativeLibraryCode);
        }
        if (scoped != null) {
            scoped.close();
        }
        ContextUtils.getAppSharedPreferences().edit().remove("dont_prefetch_libraries").apply();
    }
}
