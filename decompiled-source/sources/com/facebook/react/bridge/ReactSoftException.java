package com.facebook.react.bridge;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
@Deprecated
/* loaded from: classes8.dex */
public class ReactSoftException {
    private static final List<ReactSoftExceptionListener> sListeners = new CopyOnWriteArrayList();

    /* loaded from: classes8.dex */
    public interface ReactSoftExceptionListener {
        void logSoftException(String str, Throwable th);
    }

    public static void addListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (list.contains(reactSoftExceptionListener)) {
            return;
        }
        list.add(reactSoftExceptionListener);
    }

    public static void removeListener(ReactSoftExceptionListener reactSoftExceptionListener) {
        sListeners.remove(reactSoftExceptionListener);
    }

    public static void clearListeners() {
        sListeners.clear();
    }

    public static void logSoftException(String str, Throwable th) {
        List<ReactSoftExceptionListener> list = sListeners;
        if (list.size() > 0) {
            for (ReactSoftExceptionListener reactSoftExceptionListener : list) {
                reactSoftExceptionListener.logSoftException(str, th);
            }
            return;
        }
        FLog.e(str, "Unhandled SoftException", th);
    }
}
