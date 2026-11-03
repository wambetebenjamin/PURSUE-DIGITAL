package com.bytedance.common.jato.boost;
/* loaded from: classes.dex */
public class CpusetManager {
    public static final int CLUSTOR_TYPE_BIG = 2;
    public static final int CLUSTOR_TYPE_LITTLE = 1;
    private static int[] bigCoreNum = null;
    private static int[] littleCoreNum = null;
    private static boolean mHasInited = false;
    private static final Object mInitLock = new Object();

    private static native void resetCpuSetTid(int i2);

    private static native void setCpuSetTid(int i2, int[] iArr);

    public static void init() {
        boolean fetch = d.fetch();
        synchronized (mInitLock) {
            if (mHasInited) {
                return;
            }
            if (fetch) {
                littleCoreNum = d.getSmallCoreNum();
                bigCoreNum = d.getBigCoreNum();
            }
            mHasInited = true;
        }
    }

    public static boolean isReady() {
        return (littleCoreNum == null || bigCoreNum == null) ? false : true;
    }

    public static void bindBigCore() {
        bindCore(-1, 2);
    }

    public static void bindBigCore(int i2) {
        bindCore(i2, 2);
    }

    public static void bindLittleCore() {
        bindCore(-1, 1);
    }

    public static void bindLittleCore(int i2) {
        bindCore(i2, 1);
    }

    public static void resetCoreBind() {
        resetCoreBind(-1);
    }

    private static void bindCore(int i2, int i3) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            synchronized (mInitLock) {
                int[] iArr = null;
                try {
                    if (i3 == 2) {
                        iArr = bigCoreNum;
                    } else if (i3 == 1) {
                        iArr = littleCoreNum;
                    }
                    if (iArr != null && isReady()) {
                        setCpuSetTid(i2, iArr);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static void resetCoreBind(int i2) {
        if (com.bytedance.common.jato.d.loadLibrary()) {
            synchronized (mInitLock) {
                if (isReady()) {
                    resetCpuSetTid(i2);
                }
            }
        }
    }
}
