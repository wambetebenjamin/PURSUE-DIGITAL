package com.bytedance.common.jato;

import android.content.Context;
import java.util.concurrent.ExecutorService;
/* loaded from: classes.dex */
public class JatoXLConfig {
    long mBlockInterval;
    Context mContext;
    ExecutorService mExecuteService;
    boolean mIsAddref;
    c mMonitor;
    int mPriority;
    boolean mUseByteHook;
    boolean mUseInterpreterBridge;
    boolean mUseJitBlock;
    boolean mUseLogCut;
    boolean mUseSoLoad;
    int mLogCutType = 0;
    int mPrioVersion = 1;
    boolean mResetForNewThread = false;
    boolean mEnabledThreadPrioProtect = false;
    boolean mIsEnabledDalvikGcBlocker = false;
    boolean mIsEnabledArtGcBlocker = true;
    int mMaxGcBlockTime = 3000;
    int mGcHeapLimit = -1;
    boolean mIsSupportApex = false;
    boolean isEnabledCpuSetFeature = true;
    boolean mIsDebug = false;

    public boolean isDebug() {
        return this.mIsDebug;
    }

    public boolean isEnabledDalvikGcBlocker() {
        return this.mIsEnabledDalvikGcBlocker;
    }

    public boolean isEnabledArtGcBlocker() {
        return this.mIsEnabledArtGcBlocker;
    }

    public int getMaxGcBlockTimeout() {
        return this.mMaxGcBlockTime;
    }

    public int getGcHeapLimit() {
        return this.mGcHeapLimit;
    }

    public boolean getIsSupportApex() {
        return this.mIsSupportApex;
    }

    public boolean isLargeHeapApp() {
        return (this.mContext.getApplicationInfo().flags & 1048576) != 0;
    }

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final JatoXLConfig f4492a = new JatoXLConfig();

        public a a(boolean z) {
            this.f4492a.mUseJitBlock = z;
            return this;
        }

        public a b(boolean z) {
            this.f4492a.mIsAddref = z;
            return this;
        }

        public a a(long j2) {
            this.f4492a.mBlockInterval = j2;
            return this;
        }

        public a a(int i2) {
            this.f4492a.mPriority = i2;
            return this;
        }

        public a c(boolean z) {
            this.f4492a.mUseLogCut = z;
            return this;
        }

        public a b(int i2) {
            this.f4492a.mLogCutType = i2;
            return this;
        }

        public a a(c cVar) {
            this.f4492a.mMonitor = cVar;
            return this;
        }

        public a d(boolean z) {
            this.f4492a.mUseSoLoad = z;
            return this;
        }

        public a a(ExecutorService executorService) {
            this.f4492a.mExecuteService = executorService;
            return this;
        }

        public a e(boolean z) {
            this.f4492a.mIsDebug = z;
            return this;
        }

        public a a(boolean z, boolean z2) {
            this.f4492a.mIsEnabledDalvikGcBlocker = z;
            this.f4492a.mIsEnabledArtGcBlocker = z2;
            return this;
        }

        public a c(int i2) {
            if (i2 >= 0) {
                this.f4492a.mMaxGcBlockTime = i2;
            }
            return this;
        }

        public a d(int i2) {
            if (i2 > 0) {
                this.f4492a.mGcHeapLimit = i2;
            }
            return this;
        }

        public a f(boolean z) {
            this.f4492a.mIsSupportApex = z;
            return this;
        }

        public a g(boolean z) {
            this.f4492a.isEnabledCpuSetFeature = z;
            return this;
        }

        public a h(boolean z) {
            this.f4492a.mUseInterpreterBridge = z;
            return this;
        }

        public a a(int i2, boolean z, boolean z2) {
            this.f4492a.mPrioVersion = i2;
            this.f4492a.mResetForNewThread = z;
            this.f4492a.mEnabledThreadPrioProtect = z2;
            return this;
        }

        public a a(Context context) {
            this.f4492a.mContext = context;
            return this;
        }

        public JatoXLConfig a() {
            if (b()) {
                return this.f4492a;
            }
            return null;
        }

        private boolean b() {
            return (this.f4492a.mExecuteService == null || this.f4492a.mContext == null) ? false : true;
        }
    }
}
