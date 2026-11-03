package com.ttnet.org.chromium.base.memory;

import android.os.Debug;
import com.ttnet.org.chromium.base.Log;
import java.io.IOException;
/* loaded from: classes9.dex */
public final class JavaHeapDumpGenerator {
    private static final String TAG = "JavaHprofGenerator";

    private JavaHeapDumpGenerator() {
    }

    public static boolean generateHprof(String str) {
        try {
            Debug.dumpHprofData(str);
            return true;
        } catch (IOException e2) {
            Log.i(TAG, "Error writing to file " + str + ". Error: " + e2.getMessage(), new Object[0]);
            return false;
        }
    }
}
